package it.polito.tdp.alien.model;

public class Word {
	
	//memorizza ASSOCIAZIONE TRA PAROLA ALIENA E TRADUZIONE
	
	private String alienWorld;
	private String translation;
	String letters [] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	
	public boolean equals (Word w) {
		if(w.getAlienWorld().contains("?")) {
			//salvo la parte di parola prima e dopo il ?
			String e[] = w.getAlienWorld().split("?");
			
			for(int i=0;i<letters.length;i++) {
				//ricompongo la parola sotituendo ? con ognuna delle lettere dell'alfabeto
				String newWord = e[0]+letters[i]+e[1];
				//vedo se la nuova parola è uguale 
				if(alienWorld.toLowerCase().equals(newWord.toLowerCase()))
					return true;
			}
			return false; //se nesssuna sotituzione è andata bene
		}
		else { 
			if(alienWorld.toLowerCase().equals(w.getAlienWorld().toLowerCase()))
				return true;
			else
				return false;
		}
	}

	public Word(String alienWorld, String translation) {
		super();
		this.alienWorld = alienWorld;
		this.translation = translation;
	}

	public String getAlienWorld() {
		return alienWorld;
	}

	public void setAlienWorld(String alienWorld) {
		this.alienWorld = alienWorld;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
	public String toString() {
		return alienWorld+" = "+translation;
	}
	
	

}
