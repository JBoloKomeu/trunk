package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Classes.ConnectionFactory;
import Classes.Funcionario;
import Classes.UsuarioSenha;

public class FuncionarioDAO {
	
	private ConnectionFactory con;
	
	public FuncionarioDAO(ConnectionFactory con) {
		this.con = con;
	}
	
	public void CadastrarFuncionario(Funcionario funcionario, String sUser, String sPassword, Boolean bAdm) throws Exception{
		String sqlFunc = "insert into tbFuncionario (nome, cpf) values (?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sqlFunc);
		
		p.setString(1, funcionario.getsNomeFuncionario().toUpperCase());
		p.setString(2, funcionario.getsCpfFuncionario());
		
		p.executeUpdate();
		p.close();
		
		UsuarioSenha usuario = new UsuarioSenha();
		if(usuario.CadastrarUsuario(BuscarFuncionario(funcionario.getsCpfFuncionario()).getiIdFuncionario(), sUser, sPassword, bAdm)) {
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Houve um erro no cadastro! \nTente novamente");
			this.ExcluirFuncionario(funcionario);
		}
	}
	
	public void ExcluirFuncionario(Funcionario funcionario) throws Exception{
		String sql = "delete from tbFuncionario where idFuncionario = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, BuscarFuncionario(funcionario.getsCpfFuncionario()).getiIdFuncionario());
		
		p.executeUpdate();
		p.close();
	}
	
	public void AtualizarFuncionario(Funcionario funcionario) throws Exception{
		String sql = "update tbFuncionario (descricao) values (?, ?) where id = ?";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, funcionario.getsNomeFuncionario().toUpperCase());
		p.setString(2, funcionario.getsCpfFuncionario());
		p.setInt(3, funcionario.getiIdFuncionario());
		
		p.executeUpdate();
		p.close();
	}
	
	public ArrayList<Funcionario> BuscarFuncionarios() throws Exception{
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		String sql = "select func.idFuncionario, func.nome, func.cpf"
			       + "     , us.usuario, us.administrador"
				   + "  from tbFuncionario func"
				   + " inner join tbUsuarioSenha us"
				   + "    on us.idFuncionario = func.idFuncionario";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			Funcionario func = new Funcionario();
			func.setiIdFuncionario(rs.getInt("idFuncionario"));
			func.setsNomeFuncionario(rs.getString("nome").toUpperCase());
			func.setsCpfFuncionario(rs.getString("cpf"));
			func.setsUsuario(rs.getString("usuario").toUpperCase());
			func.setbAdmin(rs.getBoolean("administrador"));
			funcionarios.add(func);
		}
		 
		rs.close();
		p.close();
		
		return funcionarios;
	}
	 
	public Funcionario BuscarFuncionario(String sCpf) throws Exception{           
		String sql = "select first 1 * from tbFuncionario f where f.cpf = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sCpf);
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		Funcionario func = new Funcionario();
		func.setiIdFuncionario(rs.getInt("idFuncionario"));
		func.setsNomeFuncionario(rs.getString("nome").toUpperCase());
		func.setsCpfFuncionario(rs.getString("cpf"));
		
		rs.close();
		p.close();
		
		return func;
	}
	
	public String BuscarFuncionario(int iIdFunc) throws Exception{            
		String sql = "select first 1 * from tbFuncionario f where f.idFuncionario = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setInt(1, iIdFunc);
		
		ResultSet rs = p.executeQuery();
		try {		
			rs.next();
			
			return rs.getString("nome");
		}		
		finally {
			rs.close();
			p.close();
		}
		
		
	}	
}
