package calculadora;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;

public class Sudai implements ActionListener, KeyListener {

	private JFrame frame;
	private JTextField textFieldResult;
	private JButton Reset;
	private JButton soma;
	private Component subtrai;
	private Component multiplica;
	private JButton divide;
	private JButton ponto;
	private JButton raiz;
	private JButton um;
	private JButton dois;
	private JButton tres;
	private JButton sete;
	private JButton quatro;
	private JButton cinco;
	private JButton seis;
	private JButton oito;
	private JButton zero;
	private JButton nove;
	private JPanel panel_principal;
	private JPanel panel;
	private JPanel panel_teclado;
	private JButton elevado;
	private JButton aParenteses;
	private JButton fParenteses;
	private JButton igual;
	private JTextField textFieldOp;
	private JButton Erased;
	private JButton logaritimo;
	private JButton Inversão;
	private JButton Mostrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudai window = new Sudai();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sudai() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tela");
		frame.setLocationRelativeTo(null);
		frame.setSize(450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel_principal = new JPanel();
		frame.getContentPane().add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(null);

		textFieldResult = new JTextField();
		textFieldResult.setEditable(false);
		textFieldResult.setBounds(0, 40, 432, 50);
		panel_principal.add(textFieldResult);
		textFieldResult.setColumns(10);

		textFieldOp = new JTextField();
		textFieldOp.setEditable(false);
		textFieldOp.setBounds(0, 0, 432, 40);
		panel_principal.add(textFieldOp);
		textFieldOp.setColumns(10);

		panel = new JPanel();
		panel.setBounds(0, 88, 432, 65);
		panel_principal.add(panel);
		panel.setLayout(null);

		Reset = new JButton("Reset");
		Reset.setBounds(305, 0, 127, 64);
		Reset.addActionListener(this);
		panel.add(Reset);

		Erased = new JButton("Excluir");
		Erased.setBounds(0, 0, 127, 64);
		Erased.addActionListener(this);
		panel.add(Erased);
		
		Mostrar = new JButton("Mostrar");
		Mostrar.setBounds(150, 0, 127, 64);
		Mostrar.addActionListener(this);
		panel.add(Mostrar);

		panel_teclado = new JPanel();
		panel_teclado.setBounds(0, 154, 432, 299);
		panel_principal.add(panel_teclado);
		panel_teclado.setLayout(new GridLayout(0, 4, 0, 0));

		um = new JButton("1");
		panel_teclado.add(um);

		dois = new JButton("2");
		panel_teclado.add(dois);

		tres = new JButton("3");
		panel_teclado.add(tres);

		raiz = new JButton("raiz(");
		panel_teclado.add(raiz);

		quatro = new JButton("4");
		panel_teclado.add(quatro);

		cinco = new JButton("5");
		panel_teclado.add(cinco);

		seis = new JButton("6");
		panel_teclado.add(seis);

		logaritimo = new JButton("log(");
		panel_teclado.add(logaritimo);

		sete = new JButton("7");
		panel_teclado.add(sete);

		oito = new JButton("8");
		panel_teclado.add(oito);

		nove = new JButton("9");
		panel_teclado.add(nove);

		elevado = new JButton("^");
		panel_teclado.add(elevado);

		ponto = new JButton(".");
		panel_teclado.add(ponto);

		zero = new JButton("0");
		panel_teclado.add(zero);

		aParenteses = new JButton("(");
		panel_teclado.add(aParenteses);

		fParenteses = new JButton(")");
		panel_teclado.add(fParenteses);

		soma = new JButton("+");
		panel_teclado.add(soma);

		subtrai = new JButton("-");
		panel_teclado.add(subtrai);

		multiplica = new JButton("*");
		panel_teclado.add(multiplica);

		divide = new JButton("/");
		panel_teclado.add(divide);
		
		Inversão = new JButton("Inv(");
		panel_teclado.add(Inversão);

		igual = new JButton("=");
		panel_teclado.add(igual);

		for (Component c : panel_teclado.getComponents()) {
			((JButton) c).addActionListener(this);
			((JButton) c).addKeyListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText() == "Reset") {
			Calculos.clean();
			textFieldResult.setText("");
			textFieldOp.setText("");
			return;
		}
		String key = ((JButton) e.getSource()).getText();
		tratamentoTeclado(key);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		String key = String.valueOf(arg0.getKeyChar());
		tratamentoTeclado(key);

	}

	private void tratamentoTeclado(String key) {
		switch (key) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "0":
		case ".":
			System.out.println("------->>> Coleta operando <<<------- ");
			Calculos.coletaOperando(key);
			System.out.println("Digito: " + Calculos.getDigito());
			System.out.println("Operando: " + Calculos.getOperando());
			System.out.println("Expressao: " + Calculos.getExpressao());
			textFieldResult.setText(Calculos.getOperando().toString());
			textFieldOp.setText(Calculos.getExpressao().toString());
			break;
		case "raiz(":
			key = "r(";
		case "log(":
			if(key.equals("log(")){
				key = "l(";
			}
		case "Inv(":
			if(key.equals("Inv(")) {
				key = "I(";
			}
		case "+":
		case "-":
		case "*":
		case "^":
		case ")":
		case "(":
		case "/":
			System.out.println("------->>> Monta expressão <<<------- ");
			Calculos.montaExpressao(key);
			System.out.println("Expressão: " + Calculos.getExpressao());
			textFieldResult.setText(Calculos.getOperacao().toString());
			textFieldOp.setText(Calculos.getExpressao().toString());
			break;
		case "=":
			textFieldResult.setText("");
			System.out.println("------->>> Efetua cálculo <<<------- ");
			Calculos.efetuaCalculo();
			textFieldOp.setText(String.valueOf(Calculos.getResultado()));
			if(!Calculos.getResultado().equals("Expreção não terminada")) {
				Calculos.clean();
			}
			
			break;
		case "Excluir":
			Calculos.Excluir();
			textFieldOp.setText(Calculos.getExpressao().toString());
			break;
		case "Mostrar":
			System.out.println("Estado da Expreção: "+Calculos.getExpressao());
		default:
			break;
		}
	}
}
