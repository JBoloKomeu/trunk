package Classes;

import java.util.InputMismatchException;
import java.util.TimeZone;
import java.util.Vector;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Telas.telaInicio2;
import Telas.telaLogin;
import Telas.telaTicketEntrada;
import Telas.telaListarCliente;
import Telas.telaListarCliente;

public class Funcoes {

	public static JFrame currentFrame;
	public static Pilha previusFrame = new Pilha();
	public static String nomeFuncLogado;
	public static int idFuncLogado;
	public static boolean admFuncLogado;
	public static int iPesoBalanca;
	public static String sPesoBalanca;


	public static class Pilha {
		static final int MAX = 1000;
		int top;
		JFrame a[] = new JFrame[MAX]; // Define tamanho máximo da pilha   

		// Construtor
		Pilha() {
			top = -1;
		}

		// Métodos da pilha
		boolean isEmpty() {
			return (top < 0);
		}
		int qtdeRegistros() {
			return top+1;
		}
		boolean adiciona(JFrame x) {
			if (top >= (MAX-1)) {
				System.out.println("Estouro de Pilha!");
				return false;
			}
			else {
				a[++top] = x;
				return true;
			}
		}
		JFrame exclui() {
			if (top < 0) {
				System.out.println("Pilha Vazia!");
				return null;
			}
			else {
				JFrame x = a[top--];
				return x;
			}
		}
		JFrame topo() {
			if (top < 0) {
				System.out.println("Pilha Vazia!");
				return null;
			}
			else {
				return a[top];
			}
		}
	}

	public String VerificaCaracteresEspeciais(String texto, String nomeTela){		
		String textoTratado = texto.replaceAll("[^a-zA-Z0-9 ]", "");  

		if (textoTratado.compareToIgnoreCase(texto) != 0) {
			if(JOptionPane.showConfirmDialog( null
					, nomeTela + " possui caracteres inválidos!\n"
							+ "Deseja substituir '" + texto + "' "
							+ "por '" + textoTratado + "' ?"
							, "Caracteres inválidos"
							, JOptionPane.OK_OPTION
							, JOptionPane.WARNING_MESSAGE) == 0) {
				return textoTratado;
			}
		}
		return texto;
	}

	public boolean verificaCpf(String CPF) {
		if (CPF.equals("00000000000") ||
				CPF.equals("11111111111") ||
				CPF.equals("22222222222") || CPF.equals("33333333333") ||
				CPF.equals("44444444444") || CPF.equals("55555555555") ||
				CPF.equals("66666666666") || CPF.equals("77777777777") ||
				CPF.equals("88888888888") || CPF.equals("99999999999") ||
				(CPF.length() != 11)) {
			JOptionPane.showMessageDialog(null, "CPF Inválido!");
			return(false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); 
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				return(true);
			}else {
				JOptionPane.showMessageDialog(null, "CPF Inválido!");
				return(false);
			}
		} catch (InputMismatchException erro) {
			JOptionPane.showMessageDialog(null, "Erro na validação do CPF!");
			return(false);
		}
	}

	public boolean RealizarLogin(String sUser, String sSenha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UsuarioSenha usuario = new UsuarioSenha();
		Funcionario func = new Funcionario();
		String x = this.CriptografarSenha(sSenha);
		usuario = usuario.RealizarLogin(sUser, sSenha);
		if(usuario.getiIdFuncionario() > 0) {
			//JOptionPane.showMessageDialog(null, "Funcionario " + func.BuscarNomeFunc(usuario.getiIdFuncionario()) + "- adm: "+ usuario.getbAdm());
			nomeFuncLogado = func.BuscarNomeFunc(usuario.getiIdFuncionario());
			idFuncLogado   = usuario.getiIdFuncionario();
			admFuncLogado  =  usuario.getbAdm();
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
			return false;
		}
	}

	public String CriptografarSenha(String sSenha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(sSenha.getBytes("UTF-8"));
		BigInteger no = new BigInteger(1, messageDigest);

		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}

	public static void setarTela(JFrame jframe) {
		currentFrame = jframe;
	}

	public static void AbrirTela(JFrame jframe) {
		currentFrame.setEnabled(false);
		previusFrame.adiciona(currentFrame);
		currentFrame = jframe;
		currentFrame.setVisible(true);
	}

	public static void fecharTela() {
		currentFrame.dispose();
		currentFrame = previusFrame.topo();
		previusFrame.exclui();
		currentFrame.setEnabled(true);
		currentFrame.setVisible(true);
		if(previusFrame.qtdeRegistros() > 0) {
			previusFrame.topo().toFront();
		}
		currentFrame.toFront();
	}

	public static String retornaDateTime(Boolean bDataInvertida) {
		DateTimeFormatter dtf;
		if(bDataInvertida){
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");  
		}else {
			dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");  
		}		
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

	public static String retornaDate(Boolean bDataInvertida) {
		DateTimeFormatter dtf;
		if(bDataInvertida){
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		}else {
			dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		}		
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

	public static String retornaTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

	public static void AbrirLogin() {
		previusFrame.adiciona(currentFrame);
		currentFrame = new telaLogin();
		currentFrame.setVisible(true);
	}

	public int buscaIntBanco(String sTabela, String sCampo, String campoWhere, String valorWhere) throws SQLException {
		int iId = 0;
		ConnectionFactory con = new ConnectionFactory();
		String sql = "select first 1 " +sCampo+ " from " +sTabela+ " where " +campoWhere+ " = '" +valorWhere+ "'";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);

		ResultSet rs = p.executeQuery();

		if(rs.next()) {
			iId = rs.getInt(sCampo);
		}

		rs.close();
		p.close();

		return iId;
	}
	public String buscaStrBanco(String sTabela, String sCampo, String campoWhere, String valorWhere) throws SQLException {
		String sTexto = "";
		ConnectionFactory con = new ConnectionFactory();
		String sql = "select first 1 " +sCampo+ " from " +sTabela+ " where " +campoWhere+ " = '" +valorWhere+ "'";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);

		ResultSet rs = p.executeQuery();

		if(rs.next()) {
			sTexto = rs.getString(sCampo);
		}

		rs.close();
		p.close();

		return sTexto;
	}

	public static String IfThen(boolean bBoolean, String texto1, String texto2) {
		if(bBoolean) {
			return texto1;
		}else {
			return texto2;
		}
	}

	public static void setarPeso(String peso) {
		iPesoBalanca = Integer.parseInt(peso.substring(1, 7));
		sPesoBalanca = String.valueOf(iPesoBalanca);
	}
	
	public String inverterData(String sData) {
		String novaData = sData.substring(8) + "/";
        novaData = novaData + sData.substring(5, 7) + "/";
        novaData = novaData + sData.substring(0, 4);
        return novaData;
	}

	

}


