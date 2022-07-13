package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.Funcionario;

public class ClienteDAO {

	private ConnectionFactory con;
	
	public ClienteDAO(ConnectionFactory con) {
		this.con = con;
	}
	
	public void CadastrarCliente(Cliente cliente) throws Exception{
		String sql = "insert into tbcliente (nome, cpf, telefone, bairro, cidade) values (?,?,?,?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, cliente.getsNomeCliente().toUpperCase());
		p.setString(2, cliente.getsCpfCliente());
		p.setString(3, cliente.getsTelefoneCliente());
		p.setString(4, cliente.getsBairroCliente().toUpperCase());
		p.setString(5, cliente.getsCidadeCliente().toUpperCase());
		
		p.executeUpdate();
		p.close();
	}
	
	public void ExcluirCliente(Cliente cliente) throws Exception{
		String sql = "delete from tbcliente where idcliente = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, cliente.getiIdCliente());
		
		p.executeUpdate();
		p.close();
	}
	
	public void AtualizarCliente(Cliente cliente) throws Exception{
		String sql = "update tbcliente set nome = ?, cpf = ?, telefone = ?, bairro = ?, cidade = ? where idcliente = ?";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, cliente.getsNomeCliente().toUpperCase());
		p.setString(2, cliente.getsCpfCliente());
		p.setString(3, cliente.getsTelefoneCliente());
		p.setString(4, cliente.getsBairroCliente().toUpperCase());
		p.setString(5, cliente.getsCidadeCliente().toUpperCase());
		p.setInt(6, cliente.getiIdCliente());
		
		p.executeUpdate();
		p.close();
	}
	
	public ArrayList<Cliente> ListarClientes() throws Exception{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "select * from tbcliente";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setiIdCliente(rs.getInt("idcliente"));
			cliente.setsNomeCliente(rs.getString("nome").toUpperCase());
			cliente.setsCpfCliente(rs.getString("cpf").toUpperCase());
			cliente.setsTelefoneCliente(rs.getString("telefone"));
			cliente.setsBairroCliente(rs.getString("bairro").toUpperCase());
			cliente.setsCidadeCliente(rs.getString("cidade").toUpperCase());
			clientes.add(cliente);
		}
		 
		rs.close();
		p.close();
		
		return clientes;
	}
	
	public Cliente BuscarCliente(String sCpf) throws Exception{                //implementar a busca por id também
		String sql = "select first 1 * from tbCliente cli where cli.cpf = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sql);
		
		ResultSet rs = p.executeQuery();
		
		Cliente func = new Cliente();
		func.setiIdCliente(rs.getInt("idCliente"));
		func.setsNomeCliente(rs.getString("nome").toUpperCase());
		func.setsCpfCliente(rs.getString("cpf"));
		
		rs.close();
		p.close();
		
		return func;
	}
	
	public String[] BuscarNomesClientes() throws SQLException {
		int controle = 0;
		String sql = "select count(idcliente) as ct from tbCliente";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		rs.next();
		int qtdeClientes = rs.getInt("ct");
		String arrayClientes[] = new String[qtdeClientes];
		if(qtdeClientes > 0) {
			sql = "select nome from tbCliente";
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				arrayClientes[controle] = rs.getString("nome").toUpperCase();
				controle += 1;
			}
		}
		return arrayClientes;
	}
	
	public ArrayList<Cliente> BuscarClienteCpf(String sCpf) throws Exception{       
		
		String sql = "select * from tbCliente cli where cli.cpf like '"+sCpf+"%'";
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sql);
		
		ResultSet rs = p.executeQuery();
	
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setiIdCliente(rs.getInt("idcliente"));
			cliente.setsNomeCliente(rs.getString("nome").toUpperCase());
			cliente.setsCpfCliente(rs.getString("cpf").toUpperCase());
			cliente.setsTelefoneCliente(rs.getString("telefone"));
			cliente.setsBairroCliente(rs.getString("bairro").toUpperCase());
			cliente.setsCidadeCliente(rs.getString("cidade").toUpperCase());
			clientes.add(cliente);
		}
		
		return clientes;
	}

	
}
