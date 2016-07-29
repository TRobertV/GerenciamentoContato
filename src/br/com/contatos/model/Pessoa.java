package br.com.contatos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.contatos.helper.Alerta;
import br.com.contatos.helper.MySqlConnect;

public class Pessoa {

	private String nome;
	private String tel;
	private Integer id;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + " - " + tel;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public static boolean inserir(Pessoa pessoa){
		Connection con = MySqlConnect.ConectarDb(); //Chama a conexão
		String sql ="insert into contact(name, phone) values(? , ?)"; //String para a inserção no mysql
		PreparedStatement parametros; //Classe de parametros
		try {
			parametros = con.prepareStatement(sql);	//Chama a conexão com os parametros passando a string
			parametros.setString(1,pessoa.getNome());
			parametros.setString(2, pessoa.getTel());

			parametros.executeUpdate(); //Executa o comando sql
			con.close();//Fecha a conexão


			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		/*preencherLista();

		txtnome.clear();
		txttelefone.clear();

		System.out.println("Não funciona essa joça");*/
	}

	public static boolean atualizar(Pessoa pessoa){

		Connection con = MySqlConnect.ConectarDb();

		String sql = "update contact set name=?, phone=? where id=?;";
		PreparedStatement parametros;
		try {
			parametros = con.prepareStatement(sql);
			parametros.setString(1, pessoa.getNome());
			parametros.setString(2, pessoa.getTel());
			parametros.setLong(3, pessoa.getId());

			parametros.executeUpdate(); //Executa o comando sql
			con.close();//Fecha a conexão

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	}




	}




}
