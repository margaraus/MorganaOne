package br.com.potierp.infra.log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.potierp.model.TipoPerfil;
import br.com.potierp.model.Usuario;


/**
 * Objeto utilizado para rastro das aplicações.
 * 
 * @author
 *          <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class TraceInfo implements Serializable  {

	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = 4127631650953514674L;
	
	/**
	 * Usuario Logado.
	 */
	private Usuario usuario;

	/**
	 * Data de criação do TraceInfo.
	 */
	private Date data;

	/**
	 * Projeto.
	 */
	private String projeto;

	/**
	 * Sigla do Perfil.
	 */
	private String siglaPerfil;

	/**
	 * Identificador do Tipo de Perfil.
	 */
	private Long idTipoPerfil;

	/**
	 * IP do usuário.
	 */
	private String ip;

	/**
	 * Navegador.
	 */
	private String browser;

	/**
	 * Identificador da session http.
	 */
	private String idSession;

	/**
	 * Guarda o nome do último método acionado no business.
	 */
	private String methodName;

	/**
	 * Separador ':'.
	 */
	private static final String SEPARADOR = ":";


	/**
	 * @param data
	 * 			de criação.
	 * @param username
	 * 			do usuário.
	 * @param projeto
	 * 			nome projeto.
	 * @param ip
	 * 			da máquina do usuário.
	 * @param browser
	 * 			versão do navegador utilizado.
	 * @param idSession
	 * 			identificador da sessão do usuário.
	 */
	public TraceInfo(final Date data, final Usuario usuario, final String projeto, final String ip,
			final String browser, final String idSession) {
		super();
		this.data = data;
		this.usuario = usuario;
		this.projeto = projeto;
		this.ip = ip;
		this.browser = browser;
		this.idSession = idSession;
	}

	/**
	 * @param data
	 * 			de criação.
	 * @param username
	 * 			do usuário.
	 * @param projeto
	 * 			nome projeto.
	 * @param ip
	 * 			da máquina do usuário.
	 * @param browser
	 * 			versão do navegador utilizado.
	 * @param idSession
	 * 			identificador da sessão do usuário.
	 */
	public TraceInfo(final Date data, final Usuario usuario, final String projeto, final String ip,
			final String browser, final String idSession, final String siglaPerfil) {
		super();
		this.data = data;
		this.usuario = usuario;
		this.projeto = projeto;
		this.ip = ip;
		this.browser = browser;
		this.idSession = idSession;
		this.siglaPerfil = siglaPerfil;
	}

	/**
	 * Construtor recebe nome do us�rio, sigla do perfil e ip.
	 *
	 * @param username
	 * 			Do usuário.
	 * @param ip
	 * 			Da máquina do usuário.
	 * @param browser
	 * 			Versão do Navegador utilizado.
	 * @param idSession
	 * 			identificador da sessão do usuário.
	 */
	public TraceInfo(final Usuario usuario, final TipoPerfil rastreavel,
			final String ip, final String browser, final String idSession, final String projeto) {
		this(usuario, rastreavel.getSigla(), rastreavel.getId(), ip, browser, idSession, projeto);
	}

	/**
	 * @param username
	 * 			Do usuário.
	 * @param siglaPerfil
	 * 			Do Perfil Selecionado.
	 * @param idTipoPerfil
	 * 			Do Perfil Selecionado.
	 * @param ip
	 * 			Da máquina do usuário.
	 * @param browser
	 * 			Versão do Navegador utilizado.
	 * @param idSession
	 * 			identificador da sessão do usuário.
	 */
	public TraceInfo(final Usuario usuario, final String siglaPerfil, final Long idTipoPerfil,
			final String ip, final String browser, final String idSession, final String projeto) {
		this.data = new Date();
		this.usuario = usuario;
		this.siglaPerfil = siglaPerfil;
		this.idTipoPerfil = idTipoPerfil;
		this.ip = ip;
		this.browser = browser.length() > 60 ? browser.substring(0, 61): browser;
		this.idSession = idSession;
		this.projeto = projeto;
	}

	/**
	 * @return browser.
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @return data.
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @return idSession.
	 */
	public String getIdSession() {
		return idSession;
	}

	/**
	 * @return ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return siglaPerfil.
	 */
	public String getSiglaPerfil() {
		return siglaPerfil;
	}

	/**
	 * @return
	 */
	public Long getIdTipoPerfil() {
		return idTipoPerfil;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return methodName.
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName Configura methodName.
	 */
	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	/**
	 * (non-Javadoc).
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof TraceInfo))
			return false;
		TraceInfo t = (TraceInfo) obj;

		EqualsBuilder eq = new EqualsBuilder();
		eq.append(this.toString(), t.toString());
		return eq.isEquals();
	}

	/**
	 * (non-Javadoc).
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hash = new HashCodeBuilder(17, 37);
		hash.append(this.toString());
		return hash.toHashCode();
	}

	/**
	 * (non-Javadoc).
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(projeto);
		sb.append(SEPARADOR);
		sb.append(methodName);
		sb.append(SEPARADOR);
		sb.append(ip);
		sb.append(SEPARADOR);
		sb.append(usuario.getUsername().toUpperCase());
		sb.append(SEPARADOR);
		sb.append(siglaPerfil);
		sb.append(SEPARADOR);
		sb.append(new SimpleDateFormat("HHmmss").format(new Date()));

		return sb.toString();
	}

}