package br.com.caelum.jdbc.dao;

public class DAOException extends RuntimeException{

	public DAOException() {
		super("Causa de Erro desconhecida");
	}
	
	public DAOException(String message) {
		super(message);
	}
}
