package br.com.conceptmx.slipper.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.conceptmx.slipper.domain.Usuario;
import br.com.conceptmx.slipper.service.UsuarioService;
import br.com.conceptmx.slipper.util.ComponentSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4060981446284363818L;

	@Inject
	private UsuarioService service;

	@Inject
	private ComponentSession session;

	private Usuario usuario;

	public String index() {
		return "login";
	}

	public String efetuarLogin() {
		Usuario usuarioExistente = service.obterUsuario(usuario);
		if (usuarioExistente == null) {
			FacesContext.getCurrentInstance().addMessage("email", new FacesMessage("Usuario não encontrado"));
			return null;
		}
		try {
			service.validarCredenciais(usuarioExistente, usuario);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("sucesso", new FacesMessage(e.getMessage()));
			return null;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ola", "Login efetuado com sucesso"));
		session.setUsuario(usuarioExistente);
		return "sucesso";
	}

	public String sair() {
		session.sair();
		return "index";
	}

	public Usuario getusuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
