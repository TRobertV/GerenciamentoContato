package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.jws.Oneway;

import br.com.contatos.helper.Alerta;
import br.com.contatos.helper.MySqlConnect;
import br.com.contatos.model.Pessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable {

	@FXML TextField txtnome;
	@FXML TextField txttelefone;
	@FXML Button btninserir;
	@FXML Button btndeletar;
	@FXML Button btnatualizar;
	@FXML ListView<Pessoa> lstcontatos;


	@FXML public void inserirContato() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(txtnome.getText());
		pessoa.setTel(txttelefone.getText());

		boolean sucesso = Pessoa.inserir(pessoa);

		preencherLista();
		Alerta.showInformation("Mensagem", "Inserido com sucesso");

	}

	private void preencherLista(){

		//função para preencher lista
		lstcontatos.getItems().clear();
		Connection con = MySqlConnect.ConectarDb();
		String sql="Select * from contact";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				Pessoa p = new Pessoa();
				p.setNome(rs.getString("name"));
				p.setTel(rs.getString("phone"));
				p.setId(rs.getInt("id"));


				lstcontatos.getItems().add(p);
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

	public void deletarContato(){
		Pessoa p = new Pessoa();
		Connection con = MySqlConnect.ConectarDb();
		String sql= "delete from contact where id=? ;";
		p =(Pessoa)lstcontatos.getSelectionModel().getSelectedItem();
		PreparedStatement parametros;
			try {
				parametros = con.prepareStatement(sql);
				parametros.setLong(1, p.getId());

				parametros.executeUpdate(); //Executa o comando sql
				con.close();//Fecha a conexão
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			preencherLista();

	}
	public void editarContato(){
		Pessoa p = new Pessoa();

		Connection con = MySqlConnect.ConectarDb();
		p=lstcontatos.getSelectionModel().getSelectedItem();
		String sql = "update contact set name=?, phone=? where id=?;";
		PreparedStatement parametros;
		p.setNome(txtnome.getText());
		p.setTel(txttelefone.getText());
		p.setId(p.getId());

		boolean sucesso = Pessoa.atualizar(p);

		txtnome.clear();
		txttelefone.clear();

		preencherLista();

	}

}
