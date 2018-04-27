package br.com.potierp.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CurrencyWriter {

	private static final BigInteger THOUSAND = new BigInteger("1000");
	private static final BigInteger HUNDRED = new BigInteger("100");
	private static final String CENTO = "Cento";
	private static final String CEM = "Cem";
	/**
	 * Nomes das grandezas numéricas no plural. O mapa a chave do mapa é o
	 * expoente de dez do número e o valor é o seu nome no plural. Por exemplo:
	 * para chave 3 (10^3) o valor é "mil", para a chave 6 (10^6) o valor é
	 * "milhões", e assim por diante. Os nomes foram obtidos de um artigo
	 * publicado na seção Dois Mais Dois na revista SuperInteressante nº. 15, de
	 * dezembro de 1988 (Editora Abril, São Paulo/SP), disponível em
	 * http://www.novomilenio.inf.br/idioma/19881200.htm.
	 */
	private final Map<Integer, String> grandezasPlural = new HashMap<Integer, String>();
	private final Map<Integer, String> grandezasSingular = new HashMap<Integer, String>();
	
	/** Nomes dos números. */
	private final Map<Integer, String> nomes = new HashMap<Integer, String>();
	
	private static final String MOEDA_SINGULAR = "Real";
	private static final String MOEDA_PLURAL = "Reais";
	
	private static final String FRACAO_SINGULAR = "Centavo";
	private static final String FRACAO_PLURAL = "Centavos";
	
	private static final String PARTICULA_ADITIVA = "e";
	private static final String PARTICULA_DESCRITIVA = "de";
	
	/**
	 * O conversor reconhece números até a ordem dos setilhões, portanto, o
	 * maior valor suportado atualmente é o representado abaixo.
	 */
	private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal("999999999999999999999999999.99");
	
	
	public CurrencyWriter() {
		preencherGrandezasPlural();
		preencherGrandezasSingular();
		preencherNomes();
	}

	public String write(final BigDecimal amount) {
		if (null == amount) {throw new IllegalArgumentException();}
		
		/*
		 * TODO substituir o método setScale, abaixo, pela versão cujo
		 * parâmetro de arredondamento é um enum
		 */
		BigDecimal value = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		if (value.compareTo(BigDecimal.ZERO) <= 0) {return "";}
		
		if (MAX_SUPPORTED_VALUE.compareTo(value) < 0) {
			throw new IllegalArgumentException("Valor acima do limite suportado.");
		}

		Stack<Integer> decomposed = decompose(value);

		/* Se o número estiver, digamos, na casa dos milhões, a pilha 
		 * deverá conter 4 elementos sendo os dois últimos os das 
		 * centenas e dos centavos, respectivamente. Assim, o expoente de
		 * dez que representa a grandeza no topo da pilha é o número de 
		 * (elementos - 2) * 3 */
		int expoente = 3 * (decomposed.size() - 2); // TODO usar um índice de grupos ao invés do expoente 
		
		StringBuffer sb = new StringBuffer();
		int lastNonZeroExponent = -1;
		
		while (!decomposed.empty()) {
			int valor = decomposed.pop();
			
			if (valor > 0) {
				sb.append(" ").append(PARTICULA_ADITIVA).append(" ");
				sb.append(comporNomeGrupos(valor));
				String nomeGrandeza = obterNomeGrandeza(expoente, valor);
				if (nomeGrandeza.length() > 0) {
					sb.append(" ");	
				}
				sb.append(nomeGrandeza);
				
				lastNonZeroExponent = expoente;
			}
			
			switch (expoente) { // TODO ao invés desses switches e ifs, partir para a idéia das "Pendências"; talvez implementá-las com enum
			case 0:
				BigInteger parteInteira = value.toBigInteger();

				if (BigInteger.ONE.equals(parteInteira)) {
					sb.append(" ").append(MOEDA_SINGULAR);
				} else if (parteInteira.compareTo(BigInteger.ZERO) > 0) {
					if (lastNonZeroExponent >= 6) {
						sb.append(" ").append(PARTICULA_DESCRITIVA);
					}
					sb.append(" ").append(MOEDA_PLURAL);
				}
				break;
				
			case -3:
				if (1 == valor) {
					sb.append(" ").append(FRACAO_SINGULAR);
				} else if (valor > 1) {
					sb.append(" ").append(FRACAO_PLURAL);
				}
				break;
			}
			
			expoente -= 3;
		}
		
		return sb.substring(3);
	}
	
	private StringBuffer comporNomeGrupos(final int valor) {
		StringBuffer nome = new StringBuffer();

		int centenas = valor - (valor % 100);
		int unidades = valor % 10;
		int dezenas = (valor - centenas) - unidades;
		int duasCasas = dezenas + unidades;
		
		if (centenas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			
			if (100 == centenas) {
				if (duasCasas > 0) {
					nome.append(CENTO);
				} else {
					nome.append(CEM);
				}
			} else {
				nome.append(nomes.get(centenas));
			}
		}
		
		if (duasCasas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			if (duasCasas < 20) {
				nome.append(nomes.get(duasCasas));
			} else {
				if (dezenas > 0) {
					nome.append(nomes.get(dezenas));
				}
				
				if (unidades > 0) {
					nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
					nome.append(nomes.get(unidades));
				}
			}
		}
		
		return nome.delete(0, 3);
	}

	private String obterNomeGrandeza(final int exponent, final int value) {
		if (exponent < 3) {return "";}
		
		if (1 == value) {
			return grandezasSingular.get(exponent);
		} else {
			return grandezasPlural.get(exponent);
		}
	}

	private Stack<Integer> decompose(final BigDecimal value) {
		BigInteger intermediate = value.multiply(new BigDecimal(100)).toBigInteger();
		Stack<Integer> decomposed = new Stack<Integer>();
		
		BigInteger[] result = intermediate.divideAndRemainder(HUNDRED);
		intermediate = result[0];
		decomposed.add(result[1].intValue());
		
		while (intermediate.compareTo(BigInteger.ZERO) > 0) {
			result = intermediate.divideAndRemainder(THOUSAND);
			intermediate = result[0];
			decomposed.add(result[1].intValue());
		}
		
		/*
		 * Se o valor for apenas em centavos, adicionar zero para a casa dos
		 * reais inteiros
		 */
		if (decomposed.size() == 1) {
			decomposed.add(0);
		}
		
		return decomposed;
	}

	private void preencherGrandezasPlural() {
		grandezasPlural.put(3, "Mil");
		grandezasPlural.put(6, "Milhões");
		grandezasPlural.put(9, "Bilhões");
		grandezasPlural.put(12, "Trilhões");
		grandezasPlural.put(15, "Quatrilhões");
		grandezasPlural.put(18, "Quintilhões");
		grandezasPlural.put(21, "Sextilhões");
		grandezasPlural.put(24, "Setilhões");
	}

	private void preencherGrandezasSingular() {
		grandezasSingular.put(3, "Mil");
		grandezasSingular.put(6, "Milhão");
		grandezasSingular.put(9, "Bilhão");
		grandezasSingular.put(12, "Trilhão");
		grandezasSingular.put(15, "Quatrilhão");
		grandezasSingular.put(18, "Quintilhão");
		grandezasSingular.put(21, "Sextilhão");
		grandezasSingular.put(24, "Setilhão");
	}

	private void preencherNomes() {
		nomes.put(1, "Um");
		nomes.put(2, "Dois");
		nomes.put(3, "Três");
		nomes.put(4, "Quatro");
		nomes.put(5, "Cinco");
		nomes.put(6, "Seis");
		nomes.put(7, "Sete");
		nomes.put(8, "Oito");
		nomes.put(9, "Nove");
		nomes.put(10, "Dez");
		nomes.put(11, "Onze");
		nomes.put(12, "Doze");
		nomes.put(13, "Treze");
		nomes.put(14, "Quatorze");
		nomes.put(15, "Quinze");
		nomes.put(16, "Dezesseis");
		nomes.put(17, "Dezessete");
		nomes.put(18, "Dezoito");
		nomes.put(19, "Dezenove");
		nomes.put(20, "Vinte");
		nomes.put(30, "Trinta");
		nomes.put(40, "Quarenta");
		nomes.put(50, "Cinquenta");
		nomes.put(60, "Sessenta");
		nomes.put(70, "Setenta");
		nomes.put(80, "Oitenta");
		nomes.put(90, "Noventa");
		nomes.put(200, "Duzentos");
		nomes.put(300, "Trezentos");
		nomes.put(400, "Quatrocentos");
		nomes.put(500, "Quinhentos");
		nomes.put(600, "Seiscentos");
		nomes.put(700, "Setecentos");
		nomes.put(800, "Oitocentos");
		nomes.put(900, "Novecentos");
	}
}