package br.com.contatos.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnect {
	public static Connection ConectarDb(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.107.134.46/contatos", "root", "root");
		}catch(Exception ex){
			System.out.println(ex.getMessage());//Msg para identificação de erros
		}
		return con;
	}

}

	//Classe com o propósito de fazer a conexão com o Banco de dados
