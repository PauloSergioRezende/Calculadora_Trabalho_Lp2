package calculadora;

import java.util.Collections;

public class Calculos {

	private static StringBuffer operando = new StringBuffer();
	private static StringBuffer expressao = new StringBuffer();
	private static String operacao;
	private static String digito;
	private static String resultado;

	// limpa vari�veis
	public static void clean() {
		operando = new StringBuffer();
		expressao = new StringBuffer();
		operacao = "";
		digito = "";
		resultado = "";
	}

	// coleta operando
	public static void coletaOperando() {
		operando.append(digito);
		expressao.append(digito);
	}

	public static void coletaOperando(String key) {
		setDigito(key);
		coletaOperando();
	}

	// monta express�o
	public static void montaExpressao() {
		expressao.append(operacao);
		operando = new StringBuffer("");
	}

	// monta express�o
	public static void montaExpressao(String key) {
		setOperacao(key);
		montaExpressao();
	}

	public static void efetuaCalculo() {
		System.out.println("-----> INICIO: efetuaCalculo <-----");
		resultado = calculaExprecao(expressao).toString();
		System.out.println("-----> FIM: efetuaCalculo <-----");
	}

	// Getters & setters
	public static StringBuffer getOperando() {
		return operando;
	}

	public static void setOperando(StringBuffer operando) {
		Calculos.operando = operando;
	}

	public static StringBuffer getExpressao() {
		return expressao;
	}

	public static void setExpressao(StringBuffer expressao) {
		Calculos.expressao = expressao;
	}

	public static String getOperacao() {
		return operacao;
	}

	public static void setOperacao(String operacao) {
		Calculos.operacao = operacao;
	}

	public static String getDigito() {
		return digito;
	}

	public static void setDigito(String digito) {
		Calculos.digito = digito;
	}

	public static String getResultado() {
		return resultado;
	}

	public static void setResultado(Double resultado) {
		Calculos.resultado = String.valueOf(resultado);
	}

	public static StringBuffer calculaExprecao(StringBuffer key) {
		int aux;

		aux = key.indexOf("(");
		if (aux != -1) {
			System.out.println("parenteses encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = subExprecao(key, aux);
			System.out.println("Estado da express�o pos calculo:" + key);
		}
		
		aux = key.indexOf(")");
		if (aux != -1) {
			return new StringBuffer("!!Expre��o invalida!!");
		}
		
		aux = key.indexOf("r");
		if (aux != -1) {
			System.out.println("Exponencia��o encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "r");
			System.out.println("Estado da express�o pos calculo:" + key);
		}
		
		aux = key.indexOf("^");
		if (aux != -1) {
			System.out.println("Exponencia��o encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "^");
			System.out.println("Estado da express�o pos calculo:" + key);
		}
		
		aux = key.indexOf("*");
		if (aux != -1) {
			System.out.println("multiplica��o encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "*");
			System.out.println("Estado da express�o pos calculo:" + key);
		}

		aux = key.indexOf("/");
		if (aux != -1) {
			System.out.println("Divis�o encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "/");
			System.out.println("Estado da express�o pos calculo:" + key);
		}

		aux = key.indexOf("+");
		if (aux != -1) {
			System.out.println("soma encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "+");
			System.out.println("Estado da express�o pos calculo:" + key);
		}

		aux = key.indexOf("-");
		if (aux != -1) {
			System.out.println("subtra��o encontrado");
			System.out.println("Estado da express�o pr� calculo:" + key);
			key = calcula(key, aux, "-");
			System.out.println("Estado da express�o pos calculo:" + key);
		}

		return key;
	}

	private static StringBuffer calcula(StringBuffer key, int aux, String operador) {
		
			int aux2, aux3;
			Double v1, v2;

			aux2 = key.indexOf(operador, aux + 1);
			if (aux2 != -1) {
				key = calcula(key, aux2, operador);
			}

			if (operador.equals("+")) {
				aux2 = key.length();
			} else{
				int temp = indexBordaNumero(key.substring(aux + 1, key.length()));
				if (temp == 0) {
					if (((key.toString().charAt(aux + 1) != '+') && (key.toString().charAt(aux + 1) != '-')|| 
							indexBordaNumero(key.substring(aux+2, key.length())) != key.substring(aux+2, key.length()).length())) {
						return key = new StringBuffer("!!!Expre��o invalida!!!");
					}
					temp = indexBordaNumero(key.substring(aux + 2, key.length())+1);
				}
				aux2 = aux + temp + 1;
			}

			if (aux + 1 == aux2) {
				v1 = 0D;
			} else {
				v1 = Double.valueOf(key.substring(aux + 1, aux2));
			}

			aux3 = lastIndexBordaNumero(key.substring(0, aux));
			if (aux3 + 1 == aux) {
				if(operador == "r") {
					v2 = 2D;
				}else {
					v2 = 0D;
				}
				
			} else {
				v2 = Double.valueOf(key.substring(aux3 + 1, aux));
			}

			key = new StringBuffer(key.delete(aux3 + 1, aux2));
			key.insert(aux3 + 1, calculos(v1, v2, operador));
			return key;
	}

	private static String calculos(Double v1, Double v2, String operador) {
		switch (operador) {
		case "+":
			return String.valueOf(v2 + v1);
		case "-":
			return String.valueOf(v2 - v1);
		case "*":
			return String.valueOf(v2 * v1);
		case "/":
			return String.valueOf(v2 / v1);
		case "^":
			return String.valueOf(Math.pow(v2, v1));
		case "r":
			
			return String.valueOf(Math.pow(v1,(1/v2)));
		default:
			return "b";
		}
	}

	private static int lastIndexBordaNumero(String substring) {
		int[] t = new int[7];
		int r = -1;

		t[0] = substring.lastIndexOf("^");
		t[1] = substring.lastIndexOf("raiz(");
		t[2] = substring.lastIndexOf("log");
		t[3] = substring.lastIndexOf("*");
		t[4] = substring.lastIndexOf("/");
		t[5] = substring.lastIndexOf("+");
		t[6] = substring.lastIndexOf("-");

		for (int i = 1; i <= 6; i++) {
			if (t[i] != -1) {
				if (t[i] > r) {
					r = t[i];
				}
			}
		}

		return r;
	}

	private static int indexBordaNumero(String substring) {
		int[] t = new int[7];
		int r = substring.length();

		t[0] = substring.indexOf("^");
		t[1] = substring.indexOf("r");
		t[2] = substring.indexOf("log");
		t[3] = substring.indexOf("*");
		t[4] = substring.indexOf("/");
		t[5] = substring.indexOf("+");
		t[6] = substring.indexOf("-");

		for (int i = 1; i <= 6; i++) {
			if (t[i] != -1) {

				if (t[i] < r) {
					r = t[i];
				}

			}
		}

		return r;
	}

	private static StringBuffer subExprecao(StringBuffer key, int aux) {

		if (aux == key.length() - 1) {
			return key.deleteCharAt(aux).append("+0");
		}

		int iaux = key.indexOf("(", aux + 1);
		if (iaux != -1) {
			key = subExprecao(key, iaux);
		} 
		
			int aux2 = key.indexOf(")");
			if (aux2 == -1) {
				aux2 = key.length();
			}
			StringBuffer nKey = new StringBuffer(key.substring(aux + 1, aux2));
			nKey = calculaExprecao(nKey);
			key = new StringBuffer(key.delete(aux, aux2 + 1));
			if(lastIndexBordaNumero(key.substring(0, aux)) != aux-1  ) {
				if(key.toString().charAt(aux-1) == '(') {
					key.insert(aux, "+"+ nKey.toString());
				}else {
					key.insert(aux, "*"+ nKey.toString());
				}
				
			}else {
				key.insert(aux, nKey.toString());
			}
			
		return key;
	}

}