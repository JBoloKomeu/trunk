package TesteReport;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import Classes.ConnectionFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class teste {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
private ConnectionFactory con;
	
		public teste(ConnectionFactory con) {
		this.con = con;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teste window = new teste();
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
	public teste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					JasperDesign jdesing = JRXmlLoader.load("E:\\eclipse_p\\TCC_SGBG\\src\\TesteReport\\2.jrxml");
					String query = "select * from tbcliente";
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(query);
					jdesing.setQuery(updateQuery);
					 
					JasperReport jreport = JasperCompileManager.compileReport(jdesing);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, null );
					JasperViewer.viewReport(jprint);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				JasperReport jasperReport = null;
				try {
					jasperReport = JasperCompileManager.compileReport("E:\\eclipse_p\\TCC_SGBG\\src\\TesteReport\\2.jrxml");
				} catch (JRException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Map<String, Object> parameters = new HashMap<String, Object>();
				//parameters.put("parameter_name", value); //only if you want to pass any parameters

				JRDataSource dataSource = new JREmptyDataSource(); //your db connection

				try {
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(34, 42, 89, 23);
		frame.getContentPane().add(btnNewButton);
		/*
		int variavel = 58999;
		JTextArea txtrTeste = new JTextArea();
		txtrTeste.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrTeste.setText("                                Ticket Saida \r\n\r\n\r\nID Ticket: 58999");
		txtrTeste.setBounds(39, 70, 700, 400);
		frame.getContentPane().add(txtrTeste);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtrTeste.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});*/
		
		
	}
}
