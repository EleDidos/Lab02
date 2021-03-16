package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.alien.model.AlienDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParole;
    
    @FXML
    private TextField txtResult;
    
    @FXML
    private ImageView image;
    
    private AlienDictionary ad;

    @FXML
    void doReset(ActionEvent event) {
    	ad.clear(); //ripulisco il dizionario
    	txtResult.setText("Il dizionario è stato cancellato");
    	txtParole.clear();
    }
    
    /**
     * Sto passando DUE parole, quindi è un caso di INSERIMENTO
     * controllo che siano tutti caratteri alfabetici
     * @param s
     * @return
     */
    boolean checkWord(String s) {
    	boolean ok=true;
    	
    	for(int i=0; i<s.length();i++) {
    		if ( (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') |
    				(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')){ 
    			ok=ok&true;
    		} else {
    			ok=ok&false;
    		}
    	}
    	return ok;
    }
    
    /**
     * Sto passando UNA parola, quindi è un caso di TRADUZIONE
     * controllo che siano tutti caratteri alfabetici e al massimo un ?
     * @param s
     * @return
     */
    boolean checkWildcard(String s) {
    	boolean ok=true;
    	int cnt = 0; //numero di ?
    	
    	for(int i=0; i<s.length();i++) {
    		if ( (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') |
    				(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')){ 
    			ok=ok&true;
    		} else {
    			if(s.charAt(i)=='?') 
    				cnt++;
    			if(cnt>1 | s.charAt(i)!='?')
    				ok=ok&false;
    		}
    	}
    	return ok;
    }
    

    @FXML
    void doTranslate(ActionEvent event) {
    	
    	
    	//LEGGO DATI e FACCIO CONTROLLI
    	
    	String inserimento = txtParole.getText();
    	if(inserimento.length()==0) {
			txtResult.setText("ERRORE: nome esame vuoto");
			return; //se errore --> fermo 
		}
    	
    	
    	String campi[] = inserimento.split(" "); //mi splitto il nome e la traduzione
    	
    	//DOPPIA PAROLA
    	if(campi.length==2)
    		if(this.checkWord(campi[0])==false) {
    			txtResult.setText("ERRORE: il campo deve contenere soltanto lettere");
    			return;
    		}
    	//SINGOLA PAROLA
    	if(campi.length==1)
    		if(this.checkWildcard(campi[0])==false) {
    			txtResult.setText("ERRORE: il campo può contenere soltanto lettere e al massimo un ?");
    			return;
    		}
    	
    	//ESEGUO AZIONE DI INSERIMENTO E TRADUZIONE
    	boolean parolaAggiunta;
    	String traduzione=null;
    	boolean isTraduzione=false;
    	
    	
    	//se contiene uno spazio allora ci sono due parole 
    	//devo chiamare il metodo per AGGIUNGERE una word sul dizionario
    	if(inserimento.contains(" ")==true) {
    		
    		isTraduzione = ad.addWord(campi[0], campi[1]);
    		parolaAggiunta=true;
    	} 
    	//se contiene una sola parola devo TRADURRE
    	else {
    		traduzione = ad.translateWord(inserimento);
    		parolaAggiunta=false;
    	}
    	
    	
    	//AGGIORNO VIEW
    	if(parolaAggiunta==true) {
    		if(isTraduzione==false)
    			txtResult.setText("La nuova parola è stata aggiunta");
    		else
    			txtResult.setText("E' stata aggiornata la traduzione della parola inserita");
    		
    	} else { //devo tradurre la parola
    		if(traduzione==null)
    			txtResult.setText("La parola cercata è inesistente");
    		else
    			txtResult.setText(traduzione);
    	}
    	txtParole.clear();
    	
    }

    @FXML
    void initialize() {
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(AlienDictionary ad) {
    	this.ad=ad;
    }
}
