package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.contatos.helper.MySqlConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable {

	@FXML TextField txtnome;
	@FXML TextField txttelefone;
	@FXML Button btninserir;
	@FXML ListView lstcontatos;


	@FXML public void inserirContato() {
		Connection con = MySqlConnect.ConectarDb(); //Chama a conexão
		String sql ="insert into contact(name, phone) values(? , ?)"; //String para a inserção no mysql
		PreparedStatement parametros; //Classe de parametros
		try {
			parametros = con.prepareStatement(sql);	//Chama a conexão com os parametros passando a string
			parametros.setString(1,txtnome.getText());
			parametros.setString(2, txttelefone.getText());
			parametros.executeUpdate(); //Executa o comando sql
			con.close();//Fecha a conexão
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		preencherLista();

		txtnome.clear();
		txttelefone.clear();

		System.out.println("Não funciona essa joça");
	}

	private void preencherLista(){

		//função para preencher lista
		lstcontatos.getItems().clear();
		Connection con = MySqlConnect.ConectarDb();
		String sql="Select * from contact";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				String contato = "";
				contato = rs.getString("name");
				contato +=" - ";
				contato += rs.getString("phone");

				lstcontatos.getItems().add(contato);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		preencherLista();
	}
}
