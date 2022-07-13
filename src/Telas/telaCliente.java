package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Classes.Cliente;
import Classes.Funcoes;
import Telas.telaInicio2;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.SwingConstants;

public class telaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textContato;
	private JLabel cidadeCli;
	private JTextField textBairro;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JLabel lblTitulo;
	private JTextField textCidade;
	private JButton btnIncluir;
	private JButton btnAtualizar;
	private Funcoes func = new Funcoes();
	public telaInicio2 telaInicio;
	public telaListarCliente telaTabela;

	public void setTelaInicio(telaInicio2 telaInicio) {
		this.telaInicio = telaInicio;
	}
	public void setTelaTabela(telaListarCliente telaTabela) {
		this.telaTabela = telaTabela;
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCliente frame = new telaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public telaCliente(Cliente cli){
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		setBounds(100, 100, 700, 402);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nomeCli = new JLabel("Nome");
		nomeCli.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCli.setBounds(26, 74, 63, 30);
		nomeCli.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(nomeCli);
		
		textNome = new JTextField();
		textNome.setBounds(98, 80, 546, 20);
		textNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		textNome.setEnabled(false);
		textNome.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel cpfCli = new JLabel("CPF");
		cpfCli.setHorizontalAlignment(SwingConstants.RIGHT);
		cpfCli.setBounds(36, 115, 50, 30);
		cpfCli.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(cpfCli);
		
		textCPF = new JTextField();
		textCPF.setBounds(98, 121, 546, 20);
		textCPF.setEnabled(false);
		textCPF.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textCPF);
		textCPF.setColumns(10);
		
		JLabel contatoCli = new JLabel("Contato");
		contatoCli.setHorizontalAlignment(SwingConstants.RIGHT);
		contatoCli.setBounds(0, 156, 89, 30);
		contatoCli.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(contatoCli);
		
		textContato = new JTextField();
		textContato.setBounds(98, 162, 546, 20);
		textContato.setEnabled(false);
		textContato.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textContato);
		textContato.setColumns(10);
		
		JLabel bairroCli = new JLabel("Bairro");
		bairroCli.setHorizontalAlignment(SwingConstants.RIGHT);
		bairroCli.setBounds(22, 197, 67, 30);
		bairroCli.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(bairroCli);
		
		textBairro = new JTextField();
		textBairro.setBounds(98, 203, 546, 20);
		textBairro.setEnabled(false);
		textBairro.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textBairro);
		textBairro.setColumns(10);
		
		cidadeCli = new JLabel("Cidade");
		cidadeCli.setHorizontalAlignment(SwingConstants.RIGHT);
		cidadeCli.setBounds(9, 238, 80, 30);
		cidadeCli.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(cidadeCli);
		
		textCidade = new JTextField();
		textCidade.setBounds(98, 244, 546, 20);
		textCidade.setEnabled(false);
		textCidade.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(textCidade);
		textCidade.setColumns(10);
		
		if(Objects.isNull(cli)) {
			lblTitulo = new JLabel("Cadastro - Cliente");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setBounds(5, 11, 669, 47);
			lblTitulo.setFont(new Font("Arial", Font.BOLD, 27));
			contentPane.add(lblTitulo);
			btnIncluir = new JButton("Incluir");
			btnIncluir.setBounds(98, 277, 286, 37);
			btnIncluir.setFont(new Font("Arial", Font.PLAIN, 20));
			btnIncluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HabilitarCampos();				
				}
			});
			contentPane.add(btnIncluir);
			//btn
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(98, 319, 546, 37);
			btnConfirmar.setEnabled(false);
			btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 24));
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ValidarCamposVazios() /*&& func.verificaCpf(textCPF.getText())*/) {
						textNome.setText(func.VerificaCaracteresEspeciais(textNome.getText(), "O Nome do Cliente"));
						
						textCidade.setText(func.VerificaCaracteresEspeciais(textNome.getText(), "O Nome da Cidade"));					
						
						Cliente cli = new Cliente();

						cli.setsNomeCliente(textNome.getText()); 
						cli.setsCpfCliente(textCPF.getText());
						cli.setsTelefoneCliente(textContato.getText());
						cli.setsBairroCliente(textBairro.getText());
						cli.setsCidadeCliente(textCidade.getText());
						cli.CadastrarCliente();

						LimparCampos();
						
					} 
				}
			});
		}else {
			lblTitulo = new JLabel("Edição - Cliente");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setBounds(5, 11, 669, 47);
			lblTitulo.setFont(new Font("Arial", Font.BOLD, 27));
			contentPane.add(lblTitulo);
			
			btnIncluir = new JButton("Alterar");
			btnIncluir.setBounds(98, 277, 286, 37);
			btnIncluir.setFont(new Font("Arial", Font.PLAIN, 20));
			btnIncluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HabilitarCampos();				
				}
			});
			contentPane.add(btnIncluir);
			
			textNome.setText((cli.getsNomeCliente()));
			textCPF.setText((cli.getsCpfCliente()));
			textContato.setText((cli.getsTelefoneCliente()));
			textBairro.setText((cli.getsBairroCliente()));
			textCidade.setText((cli.getsCidadeCliente()));
			String id = ((cli.getsCidadeCliente()));
		
			btnConfirmar = new JButton("Confirmar alterações");
			btnConfirmar.setBounds(98, 319, 546, 37);
			btnConfirmar.setEnabled(false);
			btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 24));
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ValidarCamposVazios() /*&& func.verificaCpf(textCPF.getText())*/) {
						textNome.setText(func.VerificaCaracteresEspeciais(textNome.getText(), "O Nome do Cliente"));					
						textCidade.setText(func.VerificaCaracteresEspeciais(textCidade.getText(), "O Nome da Cidade"));	
						
						btnIncluir = new JButton("Alterar");
						btnIncluir.setBounds(98, 277, 286, 37);
						contentPane.add(btnConfirmar);
						btnIncluir.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								HabilitarCampos();				
							}
						});
						
						btnIncluir.setFont(new Font("Arial", Font.PLAIN, 20));
						contentPane.add(btnIncluir);
						
						cli.setsNomeCliente(textNome.getText());
						cli.setsCpfCliente(textCPF.getText());
						cli.setsTelefoneCliente(textContato.getText());
						cli.setsBairroCliente(textBairro.getText());
						cli.setsCidadeCliente(textCidade.getText());			
						
						try {
							cli.AtualizarCliente();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					


						LimparCampos();
						
					} 
				}
			});
			
		}
		
		
		
		
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(389, 277, 255, 37);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.fecharTela();
			}
		});
		contentPane.add(btnCancelar);
		contentPane.add(btnConfirmar);
		
	
		
	}



	protected void LimparCampos() {
		// TODO Auto-generated method stub
		textNome.setText("");
		textCPF.setText("");
		textContato.setText("");
		textBairro.setText("");
		textCidade.setText("");
	}
	

	protected void HabilitarCampos() {
		// TODO Auto-generated method stub
		textNome.setEnabled(true);
		textCPF.setEnabled(true);
		textBairro.setEnabled(true);
		textContato.setEnabled(true);
		textCidade.setEnabled(true);
		btnConfirmar.setEnabled(true);
		textNome.setEditable(true);
		textCPF.setEditable(true);
		textBairro.setEditable(true);
		textContato.setEditable(true);
		textCidade.setEditable(true);
		btnIncluir.setEnabled(false);
		
	}
	
	private boolean ValidarCamposVazios() {
		if( textNome.getText().equals("") || 
			textCPF.getText().equals("") || 
			textBairro.getText().equals("") ||
			textContato.getText().equals("") ||
			textCidade.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Verifique se todos os campos foram preenchidos corretamente!");
			return false;
		}
		return true;
	}
}
	
	

