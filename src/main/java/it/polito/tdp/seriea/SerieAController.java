/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SeasonSpecific;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxSquadra"
    private ChoiceBox<Team> boxSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="btnSelezionaSquadra"
    private Button btnSelezionaSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaAnnataOro"
    private Button btnTrovaAnnataOro; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaCamminoVirtuoso"
    private Button btnTrovaCamminoVirtuoso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doSelezionaSquadra(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Team t=this.boxSquadra.getValue();
    	
    	   if(t == null) {
           	txtResult.clear();
           	txtResult.appendText("Seleziona una squadra!\n");
           	return ;
       	}
    	   List<Integer> punti=model.getPuntiTot(t);
    	   
    	   txtResult.appendText("La squadra "+t+" ha avuto un punteggio nelle stagioni pari a: \n");
    	   for(Integer p: punti) {
    			txtResult.appendText(p.toString()+"\n");
    	   }
    }

    @FXML
    void doTrovaAnnataOro(ActionEvent event) {
      this.txtResult.clear();
    	
    	Team t=this.boxSquadra.getValue();
    	
    	   if(t == null) {
           	txtResult.clear();
           	txtResult.appendText("Seleziona una squadra!\n");
           	return ;
       	}
    	   
    	   this.model.creaGrafo(t);
    	   txtResult.appendText("Grafo Creato!\n");
           txtResult.appendText("# Vertici: " + model.nVertici()+ "\n");
       	   txtResult.appendText("# Archi: " + model.nArchi() + "\n");
       	
       	 SeasonSpecific s=this.model.getAnnataDOro();
       	 
       	 txtResult.appendText("Annata d'oro della squadra "+t+" è "+s+" con peso "+model.getPesoSGold());
    	
    }

    @FXML
    void doTrovaCamminoVirtuoso(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSelezionaSquadra != null : "fx:id=\"btnSelezionaSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnTrovaAnnataOro != null : "fx:id=\"btnTrovaAnnataOro\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnTrovaCamminoVirtuoso != null : "fx:id=\"btnTrovaCamminoVirtuoso\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }
    
    public void setModel(Model model) {
		this.model = model;	
		this.boxSquadra.getItems().addAll(model.getTeam());
	}
    
}
