package br.com.potierp.infra.msg;

/**
 * Enum responsÃ¡vel pelo armazenamento das mensagens lanÃ§adas por Exceptions.
 *
 * @author 
 * 04/02/2010 20:41:14
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public enum MensagensExceptionEnum {

	/**
	 * Erro ao fazer lookup do Entity Manager.
	 */
	ERRO_ENTITY_MANAGER("ERRO AO FAZER O LOOKUP DO ENTITY MANAGER"),
	
	/**
	 * Erro ao efetuar a Auditoría.
	 */
	ERRO_AUDITORIA("erroEfetuarAuditoria"),

	/**
	 * Erro ao buscar o traceinfo.
	 */
	ERRO_TRACE_INFO("TRACEINFO NÃO INFORMADO"),
	
	/**
	 * Erro ao salvar um objeto removido.
	 */
	ERRO_SALVAR_ENTIDADE_REMOVIDA("Erro ao salvar um objeto removido."),
	
	/**
	 * 
	 */
	ERRO_SALVAR_OBJETO_EXISTE(""),
	
	/**
	 * 
	 */
	ERRO_SALVAR_OBJETO(""),
	
	/**
	 * 
	 */
	ERRO_SALVAR_OBJETO_REMOVIDO(""),
	
	/**
	 * 
	 */
	ERRO_SALVAR_OBJETO_CONCORRENCIA(""),
	
	/**
	 * 
	 */
	ERRO_REMOVER_ENTIDADE_RELACIONAMENTO(""),
	
	/**
	 * 
	 */
	ERRO_EXCLUIR_OBJETO_REMOVIDO(""),

	/**
	 * Erro ao buscar objeto especifico.
	 */
	ERRO_BUSCAR_OBJETO("ERRO AO BUSCAR OBJETO"),

	/**
	 * Erro durante a busca da entidade pois os criterios nÃ£o foram informados para execuÃ§Ã£o da query.
	 */
	ERRO_CRITERIO_BUSCAR_ENTIDADE("ERRO BUSCAR ENTIDADE {0} OS CRITERIOS NAO FORAM INFORMADOS PARA EXECUCAO DA QUERY"),

	/**
	 * Erro ao buscar entidade.
	 */
	ERRO_BUSCAR_ENTIDADE("ERRO AO BUSCAR O OBJETO {0} POR {1}"),

	/**
	 * Erro ao buscar lista de entidades.
	 */
	ERRO_BUSCAR_LISTA_ENTIDADES("ERRO AO BUSCAR LISTA DE ENTIDADES DO TIPO {0}"),
	
	/**
	 * Erro ao buscar lista de entidades.
	 */
	ERRO_BUSCAR_TOTAL_ENTIDADE("ERRO AO BUSCAR O TOTAL DA ENTIDADE DO TIPO {0}"),
	
	/**
	 * Erro ao criar um novo registro.
	 */
	ERRO_ADICIONAR_ENTIDADE("erroAoCriar"),

	/**
	 * Erro ao adicionar lista de entidades.
	 */
	ERRO_ADICIONAR_LISTA("ERRO AO ADICIONAR LISTA DE ENTIDADES DO TIPO {0}"),

	/**
	 * Erro ao adicionar lista de entidades.
	 */
	ERRO_ALTERAR_LISTA_ENTIDADES("ERRO AO ALTERAR LISTA DE ENTIDADES DO TIPO {0}"),
	
	/**
	 * Erro ao alterar a entidade.
	 */
	ERRO_ALTERAR_ENTIDADE("ERRO AO ALTERAR A ENTIDADE DO TIPO {0}"),
	
	/**
	 * Erro ao remover a entidade.
	 */
	ERRO_REMOVER_ENTIDADE("ERRO AO REMOVER A ENTIDADE {0}"),

	/**
	 * Erro ao remover o objeto.
	 */
	ERRO_REMOVER("ERRO AO REMOVER O OBJETO {0}"),

	/**
	 * Erro ao remover a lista de entidades.
	 */
	ERRO_REMOVER_LISTA("ERRO AO REMOVER LISTA DE ENTIDADES DO TIPO {0}"),

	/**
	 * Erro durante a inclusÃ£o do objeto.
	 */
	ERRO_ADICIONAR("ERRO AO ADICIONAR O OBJETO {0}"),

	/**
	 * Erro durante a busca do login do usuário.
	 */
	ERRO_BUSCAR_LOGIN("ERRO AO BUSCAR LOGIN DO USUÃ�RIO"),

	/**
	 * Usuario não encontrado.
	 */
	ERRO_USUARIO_NAO_ENCONTRADO("USUARIO NAO ENCONTRADO {0}"),
	
	/**
	 * O registro nÃ£o pode ser excluÃ­do pois outros dados dependem dele.
	 */
	ERRO_REGISTRO_COM_DEPENDENCIA_DE_DADOS("erroRegistroComDependenciaDeDados"),
	
	/**
	 * JÃ¡ existe uma empresa com este Cnpj.
	 */
	ERRO_JA_EXISTE_EMPRESA_COM_ESTE_CNPJ("erroJaExisteEmpresaComEsteCnpj"),
	
	/**
	 * Já existe um Dependente com este Nome para este Funcionário.
	 */
	ERRO_JA_EXISTE_UM_DEPENDENTE_COM_ESTE_NOME_PARA_ESTE_FUNCIONARIO("erroJaExisteUmDependenteComEsteNomeParaEsteFuncionario"),
	
	/**
	 * Já existe um fornecedor com este Cnpj.
	 */
	ERRO_JA_EXISTE_FORNECEDOR_COM_ESTE_CPFCNPJ("erroJaExisteFornecedorComEsteCpfCnpj"),
	
	/**
	 * J� existe feriado com esses crit�rios.
	 */
	ERRO_JA_EXISTE_FERIADO_COM_ESSES_CRITERIOS("erroJaExisteFeriadoComEssesCriterios"),
	
	/**
	 * Já existe um tipo de demissÃ£o com este nome.
	 */
	ERRO_JA_EXISTE_UM_TIPO_DEMISSAO_COM_ESTE_NOME("erroJaExisteUmTipoDemissaoComEsteNome"),
	
	/**
	 * Já existe um tipo de admissao com este nome.
	 */
	ERRO_JA_EXISTE_UM_TIPO_ADMISSAO_COM_ESTE_NOME("erroJaExisteUmTipoAdmissaoComEsteNome"),
	
	/**
	 * Já existe um vículo empregatício com este codigo.
	 */
	ERRO_JA_EXISTE_UM_VINCULOEMPREGATICIO_COM_ESTE_CODIGO("erroJaExisteUmVinculoEmpregaticioComEsteCodigo"),
	
	/**
	 * Já existe uma situação com este código.
	 */
	ERRO_JA_EXISTE_UMA_SITUACAO_COM_ESTE_CODIGO("erroJaExisteUmaSituacaComEsteCodigo"),
	
	/**
	 * Já existe um Usuário com este nome.
	 */
	ERRO_JA_EXISTE_UM_USUARIO_COM_ESTE_NOME("erroJaExisteUmUsuarioComEsteNome"),
	
	/**
	 * Já existe um tipo de desconto com este nome.
	 */
	ERRO_JA_EXISTE_UM_DESCONTO_COM_ESTE_NOME("erroJaExisteUmDescontoComEsteNome"),
	
	/**
	 * Já existe um encargo com este nome.
	 */
	ERRO_JA_EXISTE_UM_ENCARGO_COM_ESTE_NOME("erroJaExisteUmEncargoComEsteNome"),
	
	/**
	 * Já existe um adicional com este nome.
	 */
	ERRO_JA_EXISTE_UM_ADICIONAL_COM_ESTE_NOME("erroJaExisteUmAdicionalComEsteNome"),
	
	/**
	 * Já existe um tipo de vale transporte com este codigo.
	 */
	ERRO_JA_EXISTE_UM_TIPOVALETRANSPORTE_COM_ESTE_CODIGO("erroJaExisteUmTipoValeTransporteComEsteCodigo"),
	
	/**
	 * Já existe um tipo de vale refeição com este codigo.
	 */
	ERRO_JA_EXISTE_UM_TIPOVALEREFEICAO_COM_ESTE_CODIGO("erroJaExisteUmTipoValeRefeicaoComEsteCodigo"),
	
	/**
	 * Já existe um tipo de cesta básica com este codigo.
	 */
	ERRO_JA_EXISTE_UM_TIPOCESTABASICA_COM_ESTE_CODIGO("erroJaExisteUmTipoCestaBasicaComEsteCodigo"),
	
	/**
	 * Já existe uma verba com este codigo.
	 */
	ERRO_JA_EXISTE_UMA_VERBA_COM_ESTE_CODIGO("erroJaExisteUmaVerbaComEsteCodigo"),
	
	/**
	 * Já existe um tipo de beneficio com este nome.
	 */
	ERRO_JA_EXISTE_UM_BENEFICIO_COM_ESTE_NOME("erroJaExisteUmBeneficioComEsteNome"),
	
	/**
	 * J� existe um cargo com este nome.
	 */
	ERRO_JA_EXISTE_UM_CARGO_COM_ESTE_NOME("erroJaExisteUmCargoComEsteNome"),
	
	/**
	 * Já existe uma forma de pagamento com este nome.
	 */
	ERRO_JA_EXISTE_UMA_FORMAPAGAMENTO_COM_ESTE_NOME("erroJaExisteUmaFormaDePagamentoComEsteNome"),
	
	/**
	 * Já existe um Setor com este nome.
	 */
	ERRO_JA_EXISTE_UM_SETOR_COM_ESTE_NOME("erroJaExisteUmSetorComEsteNome"),
	
	/**
	 * Já existe uma Jornada de Trabalho com este nome.
	 */
	ERRO_JA_EXISTE_UMA_JORNADA_DE_TRABALHO_COM_ESTE_NOME("erroJaExisteUmaJornadaDeTrabalhoComEsteNome"),
	
	/**
	 * Já existe um cliente com este Cpf/Cnpj.
	 */
	ERRO_JA_EXISTE_CLIENTE_COM_ESTE_CPF_CNPJ("erroJaExisteClienteComEsteCpfCnpj"),
	
	/**
	 * Já existe um cliente com este código.
	 */
	ERRO_JA_EXISTE_UM_CLIENTE_COM_ESTE_CODIGO("erroJaExisteUmClienteComEsteCodigo"),
	
	/**
	 * Já existe um fornecedor com este código.
	 */
	ERRO_JA_EXISTE_UM_FORNECEDOR_COM_ESTE_CODIGO("erroJaExisteUmFornecedorComEsteCodigo"),
	
	/**
	 * Já existe um perfil com esta descrição.
	 */
	ERRO_JA_EXISTE_UM_PERFIL_COM_ESTA_DESCRICAO("erroJaExisteUmPerfilComEstaDescricao"),
	
	/**
	 * Esse funcionário já é responsável.
	 */
	ERRO_FUNCIONARIO_JA_E_RESPONSAVEL("erroFuncionarioJaEResponsavel"),
	
	/**
	 * Já existe um funcionario com este Re.
	 */
	ERRO_JA_EXISTE_FUNCIONARIO_COM_ESTE_RE("erroJaExisteUmFuncionarioComEsteRe"),
	
	/**
	 * Este Grau de Parentesco já foi cadastrado.
	 */
	ERRO_JA_EXISTE_UM_GRAUPARENTESCO_COM_ESTA_DESCRICAO("erroJaExisteUmGrauParentescoComEstaDescricao"),
	
	/**
	 * Erro ao gerar relat�rio.
	 */
	ERRO_AO_GERAR_RELATORIO("ERRO AO GERAR RELATORIO {0}"), 
	
	/**
	 * Outro Usuário esta alterando este Funcionário. Por favor, clique em editar novamente para pegar os dados mais atuais.
	 */
	ERRO_OUTRO_USUARIO_ESTA_ALTERANDO_ESTE_FUNCIONARIO("erroOutroUsuarioEstaAlterandoEsteFuncionario"),
	
	/**
	 * Já existe uma Cidade para este Estado com este nome.
	 */
	ERRO_JA_EXISTE_UMA_CIDADE_PARA_ESTE_ESTADO_COM_ESTE_NOME("erroJaExisteUmaCidadeParaEsteEstadoComEsteNome");

	/**
	 * Chave.
	 */
	private String msg;

	/**
	 * Construtor.
	 * @param msg
	 */
	private MensagensExceptionEnum(final String msg) {
		this.msg = msg;
	}

	/**
	 * @return A Chave da mensagem.
	 */
	public String getMsg() {
		return msg;
	}

}