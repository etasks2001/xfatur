package com.xfatur.web.controller.security;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/novo/cadastro/usuario")
    public String cadastroPorAdminParaAdminMedicoPaciente(UsuarioDTO usuario) {

	return "usuario/cadastro";

    }

    @GetMapping("/lista")
    public String listarUsuarios() {

	return "usuario/lista";
    }

    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listarUsuariosDataTables(HttpServletRequest request) {

	return ResponseEntity.ok(service.buscarTodos(request));
    }

    @PostMapping("/cadastro/salvar")
    public String salvarUsuarios(UsuarioDTO usuarioDTO, RedirectAttributes attr) throws MessagingException {

	if (usuarioDTO.getId() == null) {
	    Boolean hasEmail = service.hasEmail(usuarioDTO.getEmail());

	    if (hasEmail) {
		attr.addFlashAttribute("falha", "E-mail já existe.");

		attr.addFlashAttribute("usuarioDTO", usuarioDTO);
	    } else {

		String senha = String.valueOf((int) (Math.random() * 1000000));

		usuarioDTO.setSenha(senha);

		service.salvarUsuario(usuarioDTO);

		attr.addFlashAttribute("sucesso", "Operação realizadao com sucesso. A senha é ");
	    }
	} else {

	    service.salvarUsuario(usuarioDTO);
	    service.emailDeConfirmacaoDeCadastro(usuarioDTO.getEmail());

	    attr.addFlashAttribute("sucesso", "Operação realizadao com sucesso. Foi enviado um e-mail para confirmar o cadastro.");
	}

	return "redirect:/u/novo/cadastro/usuario";
    }

    @GetMapping("/editar/credenciais/usuario/{id}")
    public ModelAndView editarCredenciais(@PathVariable("id") Integer id) {
	UsuarioDTO usuarioDTO = service.buscaPorId(id);

	return new ModelAndView("usuario/cadastro", "usuarioDTO", usuarioDTO);
    }

    @GetMapping("/editar/senha")
    public String editarSenha() {

	return "usuario/editar-senha";

    }

    @PostMapping("/confirmar/senha")
    public String editarSenha(@RequestParam("senha1") String senha1, @RequestParam("senha2") String senha2, @RequestParam("senha3") String senha3, @AuthenticationPrincipal User user,
	    RedirectAttributes attr) {

	if (!senha1.equals(senha2)) {
	    attr.addFlashAttribute("falha", "Senha não confere.");
	    return "redirect:/u/editar/senha";
	}

	Usuario usuario = service.buscarPorEmail(user.getUsername());

	if (!UsuarioService.isSenhaCorreta(senha3, usuario.getSenha())) {
	    attr.addFlashAttribute("falha", "Senha atual não confere.");
	    return "redirect:/u/editar/senha";
	}

	service.alterarSenha(usuario, senha1);
	attr.addFlashAttribute("sucesso", "Senha alterada com sucesso.");
	return "redirect:/u/editar/senha";

    }

    @GetMapping("/novo/cadastro")
    public String novoCadastro(UsuarioDTO usuarioDTO) {
	return "usuario/cadastro";
    }

    @GetMapping("/cadastro/realizado")
    public String cadastroRealizado() {

	return "fragments/mensagem";
    }

    @GetMapping("/p/redefinir/senha")
    public String pedidoRedefinirSenha() {

	return "usuario/pedido-recuperar-senha";
    }

    @GetMapping("/p/recuperar/senha")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {

	service.redefinicaoDeSenha(email);

	model.addAttribute("sucesso", "Em instantes você receberá um e-mail para redefinir sua senha.");
	model.addAttribute("usuario", new Usuario(email));

	return "usuario/recuperar-senha";
    }

    @PostMapping("/p/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model) {
	Usuario u = service.buscarPorEmail(usuario.getEmail());

	if (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
	    model.addAttribute("falha", "Código verificador não confere.");
	    return "usuario/recuperar-senha";
	}
	u.setCodigoVerificador(null);

	service.alterarSenha(u, usuario.getSenha());
	model.addAttribute("alerta", "Sucesso");
	model.addAttribute("titulo", "Senha redefinica.");
	model.addAttribute("texto", "Você já pode logar no sistema.");

	return "login";

    }

}