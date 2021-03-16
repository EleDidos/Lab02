package it.polito.tdp.alien.model;


import java.util.LinkedHashMap;


public class AlienDictionary {
	
	//LinkedList<WordEnhanced> dictionary;
	LinkedHashMap <String,WordEnhanced> dictionary;
	String letters [] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	public AlienDictionary() {
		//dictionary = new LinkedList<WordEnhanced>();
		dictionary= new LinkedHashMap <String,WordEnhanced>();
	}

	public boolean addWord(String alienWord, String translation) {
		boolean found = false;
		
		
		//se parola esiste già, aggiorno la traduzione
		if(dictionary.get(alienWord)!=null) {
			//setto nuova traduzione
			//dictionary.get(alienWord).setTranslation(translation);
			dictionary.get(alienWord).addTranslation(translation);
			found=true;
		}
			
		//se non esiste ancora, creo una nuova parola
		if(found==false) {
			//Word w = new Word (alienWord, translation);
			WordEnhanced w = new WordEnhanced (alienWord, translation);
			dictionary.put(alienWord, w);
		}
		
		if(found==true)
			return true; //ho aggiornato traduzione
		else
			return false; //ho aggiunto parola nuova
	}
	
	public String translateWord(String alienWord) {
		
		//CON ?
		String newAlienWord="";
		
		if(alienWord.contains("?")) {
			newAlienWord=this.trueName(alienWord);
			if(newAlienWord==null)
				return null; //non è stato trovato nulla
			alienWord=newAlienWord; //da qui in poi posso gestire normalmente
		}
		
		WordEnhanced w = null;
		for(String names: dictionary.keySet())
			if(this.equalsNames(names, alienWord)==true) //equals case insensitive
				w = dictionary.get(names); //mi ridai la parola corrispondente
		if(w==null)
			return null; //non è stato trovato nulla
			
		return w.getTranslations();
	}
	
	/**
	 * Mi viene passata una parola da tradurre con un ?
	 * Cerco se è compatibile con una sostituzione di un carattere con un nome esistente
	 * se lo è, restituisco quel nome e userò quello
	 * sennò NULL
	 * @param alienWord
	 * @return
	 */
	public String trueName(String alienWord) {
		
			//salvo la parte di parola prima e dopo il ?
			int idx = alienWord.indexOf('?');
			String s1 = alienWord.substring(0, idx); //index escluso
			String s2 = alienWord.substring(idx+1, alienWord.length()); //index escluso
			
			for(int i=0;i<letters.length;i++) {
				//ricompongo la parola sotituendo ? con ognuna delle lettere dell'alfabeto
				String newWord = s1+letters[i]+s2;
				//vedo se la nuova parola è contenuta nel dizionario 
				for(String names: dictionary.keySet())
					if(this.equalsNames(names, newWord)==true)
						return names; //mi ridai il nome corretto
			}
			return null; //se nessuna sotituzione è andata bene
	}
	
	/**
	 * Equals CASE-INSENSITIVE
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean equalsNames(String s1, String s2) {
		if(s1.toLowerCase().equals(s2.toLowerCase()))
			return true;
		else
			return false;
	}
	
	public String toString() {
		String result= "";
		for(WordEnhanced wi: dictionary.values()) {
			result+=wi.toString()+"\n";
		}
		return result;
	}
	
	public void clear() {
		dictionary.clear();
	}

}
