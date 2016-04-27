package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.ModelloGrafi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	private ModelloGrafi model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblNumero;

    @FXML
    private TextField numeroLettere;

    @FXML
    private Label lblParola;

    @FXML
    private TextField parola;

    @FXML
    private Button Grafo;

    @FXML
    private Button Connessi;

    @FXML
    private Button Vicini;

    @FXML
    private TextArea Risultato;

    @FXML
    private Button Cancella;

    @FXML
    void Cancella(ActionEvent event) {
    	Risultato.clear();
    	numeroLettere.clear();
    	parola.clear();
    }

    @FXML
    void GeneraGrafo(ActionEvent event) {
        Connessi.setDisable(false);
        Vicini.setDisable(false);
        model.generaGrafo(Integer.parseInt(numeroLettere.getText()));
    }

    @FXML
    void TrovaConnessi(ActionEvent event) {
    	List<String> vicini= model.trovaVicini(parola.getText());
    	Visualizza(vicini);
    }

    @FXML
    void TrovaVicini(ActionEvent event) {
    	List<String> connessi= model.trovaConnessi(parola.getText());
    	Visualizza(connessi);
    }
    private void Visualizza(List<String> elementi){
    	String s="";
    	for(String elemento : elementi)
    		s+=elemento+"\n";
    	Risultato.setText(s);
    }
    
    public void setModel(ModelloGrafi m){
    	this.model=m;
    }
    @FXML
    void initialize() {
        assert lblNumero != null : "fx:id=\"lblNumero\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert numeroLettere != null : "fx:id=\"numeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert lblParola != null : "fx:id=\"lblParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert parola != null : "fx:id=\"parola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert Grafo != null : "fx:id=\"Grafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert Connessi != null : "fx:id=\"Connessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert Vicini != null : "fx:id=\"Vicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert Risultato != null : "fx:id=\"Risultato\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert Cancella != null : "fx:id=\"Cancella\" was not injected: check your FXML file 'Dizionario.fxml'.";
        Connessi.setDisable(true);
        Vicini.setDisable(true);
    }
}
