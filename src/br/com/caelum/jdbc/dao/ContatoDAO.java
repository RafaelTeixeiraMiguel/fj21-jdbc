package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDAO {
	private Connection connection;
	
	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adicionaContato(Contato contato) throws SQLException {
		String codigoSql = "insert into contatos "+
				"(nome, email, endereco, dataNascimento) "+
				"values (?,?,?,?)";
		
		try {
			PreparedStatement statementPreparado = connection.prepareStatement(codigoSql);
			
			statementPreparado.setString(1, contato.getNome());
			statementPreparado.setString(2, contato.getEmail());
			statementPreparado.setString(3, contato.getEndereco());
			statementPreparado.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			statementPreparado.execute();
			statementPreparado.close();
			
		}catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}finally {
			connection.close();
		}
	}
	
	public List<Contato> getLista(){
		try {
			String sql = "select * from contatos";
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				contatos.add(contato);
				}
			rs.close();
			stmt.close();
			return contatos;
			
			
			}catch(SQLException e){
				throw new DAOException("Houve um erro durante ao buscar Contatos. Procure o suporte \n"+e.getMessage());
			}finally {
		}
	}
	
	public List<Contato> buscaContatosId(int id){
		try {
			String sql = "SELECT * from contatos WHERE id = ?";
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				contatos.add(contato);
			}
			
			stmt.close();
			rs.close();
			return contatos;
		}catch(SQLException e){
			throw new DAOException("Houve um erro ao buscar contatos por ID. Procure o suporte \n"+e.getMessage());
		}
	}
}
