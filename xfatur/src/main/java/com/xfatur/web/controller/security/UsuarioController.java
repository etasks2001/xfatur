package com.xfatur.web.controller.security;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfatur.model.security.Usuario;
import com.xfatur.service.security.UsuarioService;
import com.xfatur.validation.dto.security.UsuarioDTO;

@Controller
@RequestMapping("u")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/cadastro/novo")
    public String novoCadastro(UsuarioDTO usuarioDTO) {
	return "usuario/cadastro";
    }

    @PostMapping("/cadastro/alterar")
    public String alterarUsuarios(UsuarioDTO usuarioDTO, RedirectAttributes attr) {
	service.alterarUsuario(usuarioDTO);

	attr.addFlashAttribute("sucesso", "Alterado com sucesso.");

	return "redirect:/u/lista";
    }

    @PostMapping("/cadastro/salvar")
    public String salvarUsuarios(UsuarioDTO usuarioDTO, RedirectAttributes attr) throws MessagingException {

	Boolean hasEmail = service.hasEmail(usuarioDTO.getEmail());

	if (hasEmail) {
	    attr.addFlashAttribute("falha", "E-mail já existe.");

	    attr.addFlashAttribute("usuarioDTO", usuarioDTO);
	} else {

	    String senha = String.valueOf((int) (Math.random() * 1000000));

	    usuarioDTO.setSenha(senha);

	    service.inserirUsuario(usuarioDTO);

	    attr.addFlashAttribute("sucesso", "Operação realizadao com sucesso. A senha é " + senha);
	}

	return "redirect:/u/cadastro/novo";
    }

    @GetMapping("/lista")
    public String listarUsuarios() {

	return "usuario/lista";
    }

    @GetMapping("/pesquisa/datatables")
    public ResponseEntity<?> listarUsuariosDataTables(HttpServletRequest request) {

	return ResponseEntity.ok(service.buscarTodos(request));
    }

    @GetMapping("/editar/credenciais/{id}")
    public ModelAndView editarCredenciais(@PathVariable("id") Integer id) {
	UsuarioDTO usuarioDTO = service.buscaPorId(id);

	return new ModelAndView("usuario/cadastro", "usuarioDTO", usuarioDTO);
    }

    @GetMapping("/redefinir/senha/pedido")
    public String pedidoRedefinirSenha() {

	return "usuario/pedido-redefinir-senha";
    }

    @GetMapping("/redefinir/confirmar")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {

	service.redefinicaoDeSenha(email);

	model.addAttribute("sucesso", "Em instantes você receberá um e-mail para redefinir sua senha.");
	model.addAttribute("usuario", new Usuario(email));

	return "usuario/redefinir-senha";
    }

    @PostMapping("/redefinir/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model) {
	Usuario u = service.buscarPorEmail(usuario.getEmail());

	if (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
	    model.addAttribute("falha", "Código verificador não confere.");
	    return "usuario/redefinir-senha";
	}
	u.setCodigoVerificador(null);

	service.alterarSenha(u, usuario.getSenha());
	model.addAttribute("alerta", "Sucesso");
	model.addAttribute("titulo", "Senha redefinida.");
	model.addAttribute("texto", "Você já pode logar no sistema.");

	return "login";
    }
}