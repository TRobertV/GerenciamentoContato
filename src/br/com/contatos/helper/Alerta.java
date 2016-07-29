package br.com.contatos.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	public static void showInformation(String titulo, String conteudo){
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle(titulo);
		alerta.setContentText(conteudo);
		alerta.show();
	}

}

