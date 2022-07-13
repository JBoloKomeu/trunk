package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaConfig extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField txtDawdawdaw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaConfig frame = new telaConfig();
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
	public telaConfig() {
		setBounds(100, 100, 666, 666);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Coisar");
		tglbtnNewToggleButton.setBounds(5, 5, 116, 23);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				COISIAR();
			}

			private void COISIAR() {
				int i = 1;
				

				Object[] choices = {"COISIAR", "COISAR"};
				Object defaultChoice = choices[0];
				while(i > 0) {
					JOptionPane.showOptionDialog(null, "COISIADO WITH SUCESSO!",  "COISA", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,  null, choices, defaultChoice);	
				}						
			}
		});
		contentPane.setLayout(null);
		contentPane.add(tglbtnNewToggleButton);
		
		JLabel lblNewLabel = new JLabel("Master KEy");
		lblNewLabel.setBounds(325, 9, 54, 14);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(384, 6, 260, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("\u00C1gua");
		btnNewButton.setBounds(183, 33, 57, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(167, 61, 89, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(2, 2));
				
				JTextField text1 = new JTextField(10);
			    //JTextField text2 = new JTextField(10);
			    //JTextField text3 = new JTextField(10);
			    //JTextField text4 = new JTextField(10);
			    
			    text1.setText("dawdawdaw");
			    text1.setEditable(false);
			    text1.setBounds(103, 430, 50, 20);
			    panel.add(text1);
			    //panel.add(text2);
			    //panel.add(text3);
			    //panel.add(text4);
			    
			    JOptionPane.showMessageDialog(null, panel);
				
			}
		});
		contentPane.add(btnNewButton_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New ");
		chckbxNewCheckBox.setBounds(12, 89, 49, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(384, 90, 260, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Anti-Tank", "Jypsiliun-247", "Fiat Uno", "Raid Multi-Insetos", "Water gelada"}));
		contentPane.add(comboBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("check");
		chckbxNewCheckBox_1.setBounds(10, 117, 53, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		txtDawdawdaw = new JTextField();
		txtDawdawdaw.setBounds(103, 137, 54, 20);
		txtDawdawdaw.setEditable(false);
		txtDawdawdaw.setText("dawdawdaw");
		contentPane.add(txtDawdawdaw);
		txtDawdawdaw.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("box");
		chckbxNewCheckBox_2.setBounds(15, 170, 43, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Bolo");
		chckbxNewCheckBox_3.setBounds(14, 198, 45, 23);
		contentPane.add(chckbxNewCheckBox_3);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(441, 202, 146, 14);
		contentPane.add(progressBar);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Ligar Oxigineio");
		tglbtnNewToggleButton_1.setBounds(160, 226, 103, 23);
		contentPane.add(tglbtnNewToggleButton_1);
		
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("Ligar Luz");
		tglbtnNewToggleButton_2.setBounds(174, 254, 75, 23);
		contentPane.add(tglbtnNewToggleButton_2);
		
		JToggleButton tglbtnNewToggleButton_3 = new JToggleButton("Ligar 190");
		tglbtnNewToggleButton_3.setBounds(173, 282, 77, 23);
		contentPane.add(tglbtnNewToggleButton_3);
		
		JButton btnNewButton_1 = new JButton("CoNfiRmAr");
		btnNewButton_1.setBounds(384, 310, 260, 25);
		btnNewButton_1.setFont(new Font("SimSun-ExtB", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(btnNewButton_1);
		
		JTextPane txtpnCoisaCoisaCoisa = new JTextPane();
		txtpnCoisaCoisaCoisa.setBounds(103, 430, 217, 192);
		txtpnCoisaCoisaCoisa.setText("Coisa -\r\n\r\nCoisa - Coisa -\r\nCoisa - Coisa -\r\nCoisa - Coisa -\r\nCoisa - Coisa -\r\nCoisa - Coisa -\r\nCoisa - ");
		contentPane.add(txtpnCoisaCoisaCoisa);
	}

}
