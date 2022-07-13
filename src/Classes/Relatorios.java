package Classes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class Relatorios {
	
    public void imprimirRelatorioTicketSaida(int iId) throws JRException,
        ClassNotFoundException, SQLException {

        String reportSrcFile = "C:/Users/Tiago/Documents/Eclipse/TCC_SGBG/src/TesteReport/relTicket.jrxml";
        
        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        ConnectionFactory connFactory = new ConnectionFactory();
        Connection conn = connFactory.GetConnection();

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        parameters.put("ID_TICKET", iId);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        // Make sure the output directory exists.
        File outDir = new File("C:/jasperoutput");
        outDir.mkdirs();

        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();

        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "C:/jasperoutput/TicketSaida.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);

        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File( "C:/jasperoutput/TicketSaida.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            	JOptionPane.showMessageDialog(null, "Não existe nenhum leitor de PDFs padrão!");
            }
        }
    }
    
    public void imprimirRelatorioOrdem(int iId) throws JRException,
    ClassNotFoundException, SQLException {
							 //C:/Users/Tiago/Documents/Eclipse/TCC_SGBG/src/TesteReport/relTicket.jrxml
    String reportSrcFile = "E:/eclipse_p/TCC_SGBG/TCC_SGBG/src/TesteReport/Blank_A4.jrxml";
    
    
    // First, compile jrxml file.
    JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

    ConnectionFactory connFactory = new ConnectionFactory();
    Connection conn = connFactory.GetConnection();

    // Parameters for report
    Map<String, Object> parameters = new HashMap<String, Object>();
    
    parameters.put("ID_TICKET", iId);

    JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

    // Make sure the output directory exists.
    File outDir = new File("C:/jasperoutput");
    outDir.mkdirs();

    // PDF Exportor.
    JRPdfExporter exporter = new JRPdfExporter();

    ExporterInput exporterInput = new SimpleExporterInput(print);
    // ExporterInput
    exporter.setExporterInput(exporterInput);

    // ExporterOutput
    OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
            "C:/jasperoutput/relOrdem.pdf");
    // Output
    exporter.setExporterOutput(exporterOutput);

    //
    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
    exporter.setConfiguration(configuration);
    exporter.exportReport();
    
    if (Desktop.isDesktopSupported()) {
        try {
            File myFile = new File( "C:/jasperoutput/relOrdem.pdf");
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            // no application registered for PDFs
        	JOptionPane.showMessageDialog(null, "Não existe nenhum leitor de PDFs padrão!");
        }
    }
    }
    
    public void imprimirRelatorioOrdemServico(int iId) throws JRException,
    ClassNotFoundException, SQLException {

    String reportSrcFile = "E:/eclipse_p/TCC_SGBG/TCC_SGBG/src/TesteReport/relaOrdemServico.jrxml";
    
    // First, compile jrxml file.
    JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

    ConnectionFactory connFactory = new ConnectionFactory();
    Connection conn = connFactory.GetConnection();

    // Parameters for report
    Map<String, Object> parameters = new HashMap<String, Object>();
    
    parameters.put("ID_ORDEM_SERVICO", iId);

    JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

    // Make sure the output directory exists.
    File outDir = new File("C:/jasperoutput");
    outDir.mkdirs();

    // PDF Exportor.
    JRPdfExporter exporter = new JRPdfExporter();

    ExporterInput exporterInput = new SimpleExporterInput(print);
    // ExporterInput
    exporter.setExporterInput(exporterInput);

    // ExporterOutput
    OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
            "C:/jasperoutput/TicketOrdemServico.pdf");
    // Output
    exporter.setExporterOutput(exporterOutput);

    //
    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
    exporter.setConfiguration(configuration);
    exporter.exportReport();
    
    if (Desktop.isDesktopSupported()) {
        try {
            File myFile = new File( "C:/jasperoutput/TicketOrdemServico.pdf");
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            // no application registered for PDFs
        	JOptionPane.showMessageDialog(null, "Não existe nenhum leitor de PDFs padrão!");
        }
    }
}

}
