package com.xfatur.service.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.xfatur.model.security.Perfil;
import com.xfatur.model.security.Usuario;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.security.UsuarioRepository;
import com.xfatur.validation.dto.security.PerfilDTO;
import com.xfatur.validation.dto.security.UsuarioDTO;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Datatables datatables;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Usuario usuario = buscarPorEmailEAtivo(username).orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado."));

	return new User(

		usuario.getEmail(),

		usuario.getSenha(),

		AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))

	);
    }

    private String[] getAuthorities(List<Perfil> perfis) {

	String[] authorities = new String[perfis.size()];

	for (int i = 0; i < perfis.size(); i++) {
	    authorities[i] = perfis.get(i).getDesc();
	}

	return authorities;

    }

    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
	datatables.setRequest(request);
	datatables.setColunas(DatatablesColunas.USUARIOS);

	Page<Usuario> page = datatables.getSearch().isEmpty() ? repository.findAll(datatables.getPageable()) : repository.findPorEmailOrPerfil(datatables.getSearch(), datatables.getPageable());

	return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    public void alterarCredenciais(UsuarioDTO usuarioDTO) {

	Usuario usuario = repository.findById(usuarioDTO.getId()).get();

	List<PerfilDTO> perfisDTO = usuarioDTO.getPerfisDTO();

	List<Perfil> perfis = new ArrayList<Perfil>(perfisDTO.size());
	perfisDTO.forEach(p -> perfis.add(new Perfil(p.getId())));

	usuario.setAtivo(usuarioDTO.isAtivo());
	usuario.setPerfis(perfis);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscaPorId(Integer id) {

	Optional<Usuario> usuarioOptional = repository.findById(id);

	Usuario usuario = usuarioOptional.get();

	UsuarioDTO usuarioDTO = modelMapper.toDto(usuario);
	List<PerfilDTO> perfisDTO = modelMapper.toDto(usuario.getPerfis());

	usuarioDTO.setPerfisDTO(perfisDTO);
	return usuarioDTO;
    }

    public boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
	return bCryptPasswordEncoder.matches(senhaDigitada, senhaArmazenada);
    }

    @Transactional(readOnly = false)
    public void inserirUsuario(UsuarioDTO usuarioDTO) {

	Usuario usuario = modelMapper.toModel(usuarioDTO);

	List<PerfilDTO> perfisDTO = usuarioDTO.getPerfisDTO();

	List<Perfil> perfis = new ArrayList<Perfil>(perfisDTO.size());
	perfisDTO.forEach(p -> perfis.add(new Perfil(p.getId())));
	usuario.setPerfis(perfis);

	String crypt = bCryptPasswordEncoder.encode(usuarioDTO.getSenha());
	usuario.setSenha(crypt);

	usuario.setAtivo(usuarioDTO.isAtivo());

	repository.save(usuario);

    }

    @Transactional(readOnly = false)
    public void alterarSenha(Usuario usuario, String senha) {
	usuario.setSenha(bCryptPasswordEncoder.encode(senha));

	repository.save(usuario);

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
	return repository.findByEmail(email);

    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmailEAtivo(String email) {

	return repository.findByEmailAndAtivo(email);

    }

    public void emailDeConfirmacaoDeCadastro(String email) throws MessagingException {
	String codigo = Base64Utils.encodeToString(email.getBytes());
	emailService.enviarPedidoDeConformacaoDeCadastro(email, codigo);

    }

    @Transactional(readOnly = false)
    public void redefinicaoDeSenha(String email) throws MessagingException {
	Usuario usuario = buscarPorEmailEAtivo(email).orElseThrow(() -> new UsernameNotFoundException("Usuário " + email + " não encontrado."));

	String verificador = RandomStringUtils.randomAlphanumeric(6);

	usuario.setCodigoVerificador(verificador);

	emailService.enviarPedidoDeRedefinicaoDeSenha(email, verificador);

    }

    @Transactional(readOnly = true)
    public Boolean hasEmail(String email) {
	return repository.hasEmail(email);
    }

    public void excluirUsuario(Integer id) {
	repository.deleteById(id);
    }

}