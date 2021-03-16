package it.polito.tdp.alien.model;


import java.util.LinkedList;

public class WordEnhanced {
	
	public WordEnhanced(String alienWorld, String translation) {
		this.alienWorld=alienWorld;
		translations=new LinkedList <String>();
		translations.add(translation);
	}

	//memorizza ASSOCIAZIONI MULTIPLE TRA PAROLA ALIENA E TRADUZIONE
	
		private String alienWorld;
		private LinkedList <String> translations;
		
		
		public boolean equals (WordEnhanced w) {
		
				if(alienWorld.toLowerCase().equals(w.getAlienWorld().toLowerCase()))
					return true;
				else
					return false;
			
		}


		public String getAlienWorld() {
			return alienWorld;
		}

		public void setAlienWorld(String alienWorld) {
			this.alienWorld = alienWorld;
		}

		public String getTranslations() {
			String result ="";
			for(String si: translations)
				result+=si+", ";
			return result;
		}

		public void addTranslation(String translation) {
			translations.add(translation);
		}
		
		public String toString() {
			return alienWorld+" = "+this.getTranslations();
		}
		

}
