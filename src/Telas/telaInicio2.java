package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Classes.Funcoes;
import Classes.Serial;

import java.awt.Window.Type;

public class telaInicio2 extends JFrame {

	private JPanel contentPane;
	//private static telaInicio2 frameTelaInicio = new telaInicio2();
	public Funcoes func = new Funcoes();
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				//try {				
					//telaInicio2 frameTelaInicio = new telaInicio2();
					//frameTelaInicio.setExtendedState(frameTelaInicio.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					//frameTelaInicio.setVisible(true);
					//JOptionPane.showMessageDialog(null, "aaaaaaaaa");
					//if(Funcoes.idFuncLogado < 1) {
					//	Funcoes.AbrirLogin();
						//frameTelaInicio.setVisible(true);
						//JOptionPane.showMessageDialog(null, "aaaaaaaaa");
				//	}					
				//} catch (Exception e) {
				//	e.printStackTrace();
				//}
			}
		});
	}
	
	static String time = "00/00/0000";
	

	/**
	 * Create the frame.
	 */
	

	public telaInicio2() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		Funcoes.setarTela(this);
		
		setBackground(Color.WHITE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle(" SGBG ");
		setBounds(100, 100, 1366, 768);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		JButton btnCadastro = new JButton("Cadastros");
		btnCadastro.setBounds(32, 35, 410, 55);
		btnCadastro.setEnabled(true);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCadastro.setFont(new Font("Arial", Font.BOLD, 30));
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(270, 140));
		addPopup(btnCadastro, popupMenu);
		
		JButton btnCadCliente = new JButton("Cadastro - Cliente    \u00A0         ");
		btnCadCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaCliente(null));
			}
		});
		btnCadCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu.add(btnCadCliente);
		
		JButton btnCadFuncionario = new JButton("Cadastro - Funcionario           ");
		btnCadFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaFuncionario());
			}
		});
		contentPane.setLayout(null);
		btnCadFuncionario.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu.add(btnCadFuncionario);
		
		JButton btnCadastroServio = new JButton("Cadastro - Servi\u00E7o                    ");
		btnCadastroServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaServicos());
			}
		});
		btnCadastroServio.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu.add(btnCadastroServio);
		
		JButton btnCadastroProduto = new JButton("Cadastro - Produto                    ");
		btnCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Funcoes.AbrirTela(new telaProduto());
			}
		});
		btnCadastroProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu.add(btnCadastroProduto);
		contentPane.add(btnCadastro);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rios");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRelatorio.setBounds(447, 35, 410, 55);
		btnRelatorio.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(btnRelatorio);
		
		JButton btnNewButton_2 = new JButton("Configura\u00E7\u00F5es");
		btnNewButton_2.setBounds(862, 35, 451, 55);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaConfig());
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(btnNewButton_2);
		
		JButton btnOrdem = new JButton("Ordem Servi\u00E7o");
		btnOrdem.setBounds(862, 101, 451, 55);
		btnOrdem.setFont(new Font("Arial", Font.BOLD, 30));
		btnOrdem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaOrdem());
			}
		});
		contentPane.add(btnOrdem);
		JLabel label = new JLabel(new ImageIcon("E:\\eclipse_p\\TCC_SGBG__Testes\\src\\img\\logo.png"));
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		
		JLabel lblKg = new JLabel("Kg");
		lblKg.setBounds(1186, 501, 106, 94);
		lblKg.setFont(new Font("Arial Black", Font.PLAIN, 66));
		contentPane.add(lblKg);
		
		JLabel lblTime = new JLabel(time);
		lblTime.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTime.setBounds(1186, 642, 127, 32);
		contentPane.add(lblTime);
		
		JButton btnPesagens = new JButton("Pesagens");
		btnPesagens.setFont(new Font("Arial", Font.BOLD, 30));
		btnPesagens.setEnabled(true);
		btnPesagens.setBounds(32, 101, 410, 55);
		contentPane.add(btnPesagens);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		popupMenu_1.setFont(new Font("Arial", Font.PLAIN, 21));
		addPopup(btnPesagens, popupMenu_1);
		popupMenu_1.setPopupSize(new Dimension(270, 73));
		
		JButton btnPesagemEntrada = new JButton("Pesagem - Entrada                   ");
		btnPesagemEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaTicketEntrada());
			}
		});
		btnPesagemEntrada.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_1.add(btnPesagemEntrada);
		btnPesagemEntrada.setSize(100, HEIGHT);
		
		JButton btnPesagemSaida = new JButton("Pesagem - Sa\u00EDda\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0         ");
		btnPesagemSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaTicketSaida());
			}
		});
		btnPesagemSaida.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_1.add(btnPesagemSaida);
		
		JButton btnListas = new JButton("Listas\r\n");
		btnListas.setFont(new Font("Arial", Font.BOLD, 30));
		btnListas.setBounds(447, 101, 410, 55);
		contentPane.add(btnListas);
		
		JPopupMenu popupMenu_2 = new JPopupMenu();
		addPopup(btnListas, popupMenu_2);
		popupMenu_2.setPopupSize(new Dimension(270, 207));
		
		JButton btnListaCliente = new JButton("Lista - Clientes                                               ");
		btnListaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarCliente());
			}
		});
		btnListaCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnListaCliente);
		
		JButton btnListaFuncionario = new JButton("Lista - Funcionario                                    ");
		btnListaFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarFuncionario());;
			}
		});
		btnListaFuncionario.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnListaFuncionario);
		
		JButton btnListaProduto = new JButton("Lista - Produto                               ");
		btnListaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarProduto(null));
			}
		});
		btnListaProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnListaProduto);
		
		JButton btnListaTicket = new JButton("Lista - Ticket Entrada                 ");
		btnListaTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarTicketEntrada());
			}
		});
		btnListaTicket.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnListaTicket);
		
		JButton btnTicketSaida = new JButton("Lista - Ticket Saida                     ");
		btnTicketSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarTicketSaida(null, ""));
			}
		});
		btnTicketSaida.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnTicketSaida);
		
		JButton btnListaOrdem = new JButton("Lista - Ordem                    ");
		btnListaOrdem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.AbrirTela(new telaListarOrdem());
			}
			
		});
		btnListaOrdem.setFont(new Font("Arial", Font.PLAIN, 20));
		popupMenu_2.add(btnListaOrdem);
		
		JLabel lblPeso = new JLabel("");
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeso.setFont(new Font("Arial Black", Font.PLAIN, 66));
		lblPeso.setBounds(850, 501, 326, 94);
		contentPane.add(lblPeso);
		
		
		
		/*if(Funcoes.admFuncLogado) {
			popupMenu_2.setPopupSize(new Dimension(270, 143));
			JButton btnNewButton = new JButton("Lista - Usu\u00E1rios                    ");
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
			popupMenu_2.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Funcoes.AbrirTela(new telaListarProduto());
				}
			});
		}*/
		
		Serial balanca = new Serial();
    	balanca.iniciarLeitura();
    	
    	int delay = 1000;   // delay de 5 seg.
		int interval = 200;  // intervalo de 0.2 seg.
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
		        public void run() {    	
		            lblPeso.setText(Funcoes.sPesoBalanca);
		        }
		}, delay, interval);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == e.BUTTON1) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == e.BUTTON1) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
