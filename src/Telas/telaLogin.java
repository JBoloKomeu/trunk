package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Funcoes;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class telaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField textSenha;
	private Funcoes func = new Funcoes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaLogin frame = new telaLogin();
					//frame.setLocationRelativeTo(null);
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
	public telaLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(23, 77, 67, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		textUser = new JTextField();
		textUser.setBounds(100, 79, 287, 20);
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(35, 113, 55, 25);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		textSenha = new JPasswordField();
		textSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		textSenha.setBounds(100, 118, 287, 20);
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(textSenha);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				logar();
			}
		});
		btnNewButton.setBounds(100, 153, 134, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}				
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
				}
				
			}
		});
		btnSair.setBounds(244, 153, 143, 29);
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnSair);
		
		JLabel lblNewLabel_2 = new JLabel("Tela de Login");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 11, 434, 40);
		contentPane.add(lblNewLabel_2);
	}
	
	private void logar() {
		if(ValidarCamposVazios()) {
			textUser.setText(func.VerificaCaracteresEspeciais(textUser.getText(), "O Usuário"));
			try {
				if(func.RealizarLogin(textUser.getText(), textSenha.getText())) {
					dispose();
					telaInicio2 telaInicio = new telaInicio2();
					telaInicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
					telaInicio.setVisible(true);
				}else {
					textSenha.setText("");
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private boolean ValidarCamposVazios() {
		if( textUser.getText().equals("") ||
		    textSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira dados válidos!");
			return false;
		}
		return true;
	}
	
}
