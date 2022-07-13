package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Cliente;
import Classes.Funcoes;
import Classes.Produto;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textDescProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaProduto frame = new telaProduto();
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
	public telaProduto() {
		
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		setBounds(100, 100, 352, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
		lblDescrioDoProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrioDoProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescrioDoProduto.setBounds(10, 99, 191, 30);
		contentPane.add(lblDescrioDoProduto);
		
		textDescProduto = new JTextField();
		textDescProduto.setToolTipText("Heyeyy");
		textDescProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		textDescProduto.setColumns(10);
		textDescProduto.setBounds(10, 132, 321, 20);
		contentPane.add(textDescProduto);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDescProduto != null) {
					Produto pro = new Produto();

					pro.setsDescricaoProduto(textDescProduto.getText()); 
					pro.CadastrarProduto();
				}
				
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnConfirmar.setBounds(10, 201, 124, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.fecharTela();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancelar.setBounds(207, 201, 124, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cadastro Produto");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(0, 25, 321, 40);
		contentPane.add(lblNewLabel_1_1);
	}
}
