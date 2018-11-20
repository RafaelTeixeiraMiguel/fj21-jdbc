package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try {
			String enderecoBanco = "jdbc:mysql://localhost/fj21?useSSL=false";
			String usuarioBanco = "root";
			String senhaBanco = "donamaria";
			
			Connection connection = DriverManager.getConnection(enderecoBanco, usuarioBanco, senhaBanco);
			
			return connection;
		}catch(SQLException sqlException){
			throw new RuntimeException(sqlException);
		}
	}
}
