package calculadora;

import java.util.Collections;

public class Calculos {

	private static StringBuffer operando = new StringBuffer();
	private static StringBuffer expressao = new StringBuffer();
	private static String operacao;
	private static String digito;
	private static String resultado;

	// limpa variáveis
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

	// monta expressão
	public static void montaExpressao() {
		expressao.append(operacao);
		operando = new StringBuffer("");
	}

	// monta expressão
	public static void montaExpressao(String key) {
		setOperacao(key);
		montaExpressao();
	}

	public static void efetuaCalculo() {
		System.out.println("-----> INICIO: efetuaCalculo <-----");
		if(lastIndexBordaNumero(String.valueOf(expressao.charAt(expressao.length()-1))) != -1) {
			resultado = "Expreção não terminada";
		}else {
			resultado = calculaExprecao(expressao).toString();
		}
		
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
			System.out.println("Estado da expressão pré calculo:" + key);
			key = subExprecao(key, aux);
			if (key.indexOf("a") != -1) {
				return new StringBuffer("!!Expreção invalida!!");
			}
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf(")");
		if (aux != -1) {
			return new StringBuffer("!!Expreção invalida!!");
		}

		aux = key.indexOf("l");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("logaritimo encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "l");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("r");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("Radiciação encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "r");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("^");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("Exponenciação encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "^");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("*");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("multiplicação encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "*");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("/");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("Divisão encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "/");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("-");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("subtração encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "-");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		aux = key.indexOf("+");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("soma encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "+");
			System.out.println("Estado da expressão pos calculo:" + key);
		}
		
		aux = key.indexOf("I");
		if (aux != -1 && key.indexOf("a") == -1) {
			System.out.println("soma encontrado");
			System.out.println("Estado da expressão pré calculo:" + key);
			key = calcula(key, aux, "I");
			System.out.println("Estado da expressão pos calculo:" + key);
		}

		return key;
	}

	private static StringBuffer calcula(StringBuffer key, int aux, String operador) {

		int aux2, aux3;
		Double v1, v2;

		if (aux == key.length() - 1) {
			return new StringBuffer("!!!Expreção invalida!!!");
		}

		aux2 = key.indexOf(operador, aux + 1);
		if (aux2 != -1) {
			key = calcula(key, aux2, operador);
			if (key.indexOf("a") != -1) {
				return new StringBuffer("!!!Expreção invalida!!!");
			}
		}

		if (operador.equals("+")) {
			aux2 = key.length();
		} else {
			int temp = indexBordaNumero(key.substring(aux + 1, key.length()));
			if (temp == 0) {
				if (key.toString().charAt(aux + 1) != '+' && key.toString().charAt(aux + 1) != '-') {
					return new StringBuffer("!!!Expreção invalida!!!");
				}
				temp = indexBordaNumero(key.substring(aux + 2, key.length()) + 1);
			}
			aux2 = aux + temp + 1;
		}

		if (aux + 1 == aux2) {
			v1 = 0D;
		} else {
			v1 = Double.valueOf(key.substring(aux + 1, aux2));
			if (operador.equals("r") || operador.equals("l")) {
				if (v1 < 1) {
					return new StringBuffer("!!!Expreção invalida!!!");
				}
			}
		}

		aux3 = lastIndexBordaNumero(key.substring(0, aux));

		if (aux3 + 1 == aux) {
			if (operador == "r") {
				v2 = 2D;
			} else if (operador == "l") {
				v2 = Math.E;
			} else {
				if (operador.equals("+")||operador.equals("I")) {
					v2 = 0D;
				} else{	
					return new StringBuffer("!!!Expreção invalida!!!");
				}
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
			return String.valueOf(Math.pow(v1, (1 / v2)));
		case "l":
			return String.valueOf(Math.log(v1) / Math.log(v2));
		case "I":
			return String.valueOf(1 / v1);
		default:
			return "b";
		}
	}

	private static int lastIndexBordaNumero(String substring) {
		int[] t = new int[8];
		int r = -1;

		t[0] = substring.lastIndexOf("^");
		t[1] = substring.lastIndexOf("r");
		t[2] = substring.lastIndexOf("l");
		t[3] = substring.lastIndexOf("*");
		t[4] = substring.lastIndexOf("/");
		t[5] = substring.lastIndexOf("+");
		t[6] = substring.lastIndexOf("-");
		t[7] = substring.lastIndexOf("I");

		for (int i = 0; i <= 7; i++) {
			if (t[i] != -1) {
				if (t[i] > r) {
					r = t[i];
				}
			}
		}

		return r;
	}

	private static int indexBordaNumero(String substring) {
		int[] t = new int[8];
		int r = substring.length();

		t[0] = substring.lastIndexOf("^");
		t[1] = substring.lastIndexOf("r");
		t[2] = substring.lastIndexOf("l");
		t[3] = substring.lastIndexOf("*");
		t[4] = substring.lastIndexOf("/");
		t[5] = substring.lastIndexOf("+");
		t[6] = substring.lastIndexOf("-");
		t[7] = substring.lastIndexOf("I");

		for (int i = 0; i <= 7; i++) {
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

		int aux2 = key.indexOf(")", aux);
		if (aux2 == -1) {
			aux2 = key.length();
		}
		StringBuffer nKey = new StringBuffer(key.substring(aux + 1, aux2));
		nKey = calculaExprecao(nKey);
		key = new StringBuffer(key.delete(aux, aux2 + 1));
		if (lastIndexBordaNumero(key.substring(0, aux)) != aux - 1) {
			if (key.toString().charAt(aux - 1) == '(') {
				key.insert(aux, "+" + nKey.toString());
			} else if ((lastIndexBordaNumero(key.substring(0, aux)) < aux - 1)) {
				key.insert(aux, "*" + nKey.toString());
			} else {
				key.insert(aux, nKey.toString());
			}
		} else {
			key.insert(aux, nKey.toString());
		}

		return key;
	}

	public static void Excluir() {
		if (expressao.length() > 0) {
			expressao.deleteCharAt(expressao.length() - 1);
			System.out.println(expressao);
		}
	}

}
