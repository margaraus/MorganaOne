package br.com.potierp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * Classe que representa o Usuário.
 *
 * @author renan
 *  	   <p>
 *         $LastChangedBy: felipe $
 *         <p>
 *         $LastChangedDate: 2010-10-11 10:32:30 -0300 (Seg, 11 Out 2010) $
 */
@NamedQueries(
		{
		@NamedQuery(name = Usuario.GET_ALL,
				    query = "FROM Usuario "),
	    @NamedQuery(name = Usuario.GET_USUARIO_BY_ID,
				    query = "FROM Usuario WHERE id = :id "),
		@NamedQuery(name = Usuario.GET_USUARIO_POR_CREDENCIAIS,
					query = "FROM Usuario where username = :username and password = :password"),
		@NamedQuery(name = Usuario.GET_BY_USERNAME,
			    query = "FROM Usuario u " +
			    		"WHERE u.username = :username ")
		}
)
@Entity
@Table(name="usuario")
public class Usuario extends BaseEntityPotiErp implements Comparable<Usuario> {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 8384698542500851568L;

	/**
	 * NQ para busca de todos os usuarios.
	 */
	public static final String GET_ALL = "Usuario.getAll";

	/**
	 * NQ para buscar usuario por id.
	 */
	public static final String GET_USUARIO_BY_ID = "Usuario.getUsuarioById";
	
	/**
	 * NQ para buscar usuário por credenciais (autenticação).
	 */
	public static final String GET_USUARIO_POR_CREDENCIAIS = "Usuario.getUsuarioPorCredenciais";
	
	/**
	 * NQ para buscar usuario por username.
	 */
	public static final String GET_BY_USERNAME = "Usuario.getPorUsername";

	/**
	 * Id do usuario.
	 */
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String username;

	@NotNull
	private String password;
	
	@Transient
	private String retypePassword;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataExpiraSenha;
	
	@Temporal(TemporalType.DATE)
	private Date dataUltimaAlteracao;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name ="usuario_tipoperfil",
			joinColumns = @JoinColumn(name="idUsuario"),
			inverseJoinColumns = @JoinColumn(name="idTipoPerfil"))
	private List<TipoPerfilErp> perfis;
	
	/**
	 * Pessoa atribuida ao usuário
	 */
	@OneToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;
	
	@Transient
	private boolean autenticado;
	
	@Transient
	private boolean senhaExpirada;
	
	@Transient
	private Set<Long> funcionalidades;
	
	public Usuario() {
		super();
	}

	public Usuario(final String username, final String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}
	
	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(final String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(final Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExpiraSenha() {
		return dataExpiraSenha;
	}

	public void setDataExpiraSenha(final Date dataExpiraSenha) {
		this.dataExpiraSenha = dataExpiraSenha;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(final Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public List<TipoPerfilErp> getPerfis() {
		return perfis;
	}

	public void setPerfis(final List<TipoPerfilErp> perfis) {
		this.perfis = perfis;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(final Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(final boolean autenticado) {
		this.autenticado = autenticado;
	}

	public boolean isSenhaExpirada() {
		return senhaExpirada;
	}

	public void setSenhaExpirada(final boolean senhaExpirada) {
		this.senhaExpirada = senhaExpirada;
	}

	/** (non-Javadoc).
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hash = new HashCodeBuilder(17, 37);
		hash.append(this.getUsername());
		return hash.toHashCode();
	}

	/** (non-Javadoc).
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;

		Usuario outro = (Usuario) obj;
		return new EqualsBuilder()
		.append(this.getUsername(), outro.getUsername()).isEquals();
	}

	/** (non-Javadoc).
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(this.getId())
		.append(this.getUsername())
		.append(this.getDataCadastro())
		.append(this.getDataExpiraSenha())
		.append(this.getDataUltimaAlteracao()).toString();
	}

	@Override
	public int compareTo(final Usuario usuario) {
		return this.username.compareTo(usuario.username);
	}
	
	private void carregarFuncionalidades(){
		funcionalidades = new HashSet<Long>();
		if(this.perfis != null && !this.perfis.isEmpty()){
			for(TipoPerfilErp perfil : this.perfis){
				if(perfil.getFuncionalidades() != null && !perfil.getFuncionalidades().isEmpty()){
					for(Funcionalidade funcionalidade : perfil.getFuncionalidades()){
						funcionalidades.add(funcionalidade.getCodigo());
					}
				}
			}
		}
	}
	
	public Set<Long> getFuncionalidades(){
		if(funcionalidades == null || funcionalidades.isEmpty()){
			carregarFuncionalidades();
		}
		return funcionalidades;
	}

	public boolean possuiFuncionalidade(Long idFuncionalidade){
		return this.getFuncionalidades().contains(idFuncionalidade);
	}
}