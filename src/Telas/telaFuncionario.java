package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Funcionario;
import Classes.Funcoes;
import Classes.UsuarioSenha;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class telaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textNomeFuncionario;
	private JTextField textCpfFuncionario;
	private JTextField textUsuario;
	private JPasswordField textSenha;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaFuncionario frame = new telaFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaFuncionario() {
		
		setResizable(false);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
	
		setBounds(100, 100, 526, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel_2 = new JLabel("Registrar Funcion\u00E1rio");
		lblNewLabel_2.setBounds(138, 11, 292, 53);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 28));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(50, 71, 52, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		textNomeFuncionario = new JTextField();
		textNomeFuncionario.setBounds(107, 75, 363, 20);
		textNomeFuncionario.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textNomeFuncionario);
		textNomeFuncionario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setBounds(69, 107, 33, 25);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		textCpfFuncionario = new JTextField();
		textCpfFuncionario.setBounds(107, 109, 363, 20);
		textCpfFuncionario.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textCpfFuncionario);
		textCpfFuncionario.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(35, 143, 67, 25);
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUsurio);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(107, 145, 363, 20);
		textUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		textUsuario.setColumns(10);
		contentPane.add(textUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(47, 172, 55, 25);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblSenha);
		
		JCheckBox cbAdm = new JCheckBox("Administrador");
		cbAdm.setBounds(107, 204, 135, 31);
		cbAdm.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(cbAdm);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(313, 242, 157, 29);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidarCamposVazios()) {
					Funcionario func = new Funcionario();
					func.setsNomeFuncionario(textNomeFuncionario.getText());
					func.setsCpfFuncionario(textCpfFuncionario.getText());
					func.CadastrarFuncionario(func, textUsuario.getText(), textSenha.getText(), cbAdm.isSelected());
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmar.setBounds(107, 242, 157, 29);
		contentPane.add(btnConfirmar);
		
		textSenha = new JPasswordField();
		textSenha.setFont(new Font("Arial", Font.PLAIN, 16));
		textSenha.setBounds(107, 176, 363, 20);
		contentPane.add(textSenha);
	}
	
	private boolean ValidarCamposVazios() {
		if( textNomeFuncionario.getText().equals("") ||
			textCpfFuncionario.getText().equals("") ||
			textUsuario.getText().equals("") ||
			textSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Verifique se todos os campos foram preenchidos corretamente!");
			return false;
		}
		return true;
	}
	
}
