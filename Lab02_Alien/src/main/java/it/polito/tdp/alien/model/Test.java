package it.polito.tdp.alien.model;

public class Test {

	public static void main(String[] args) {
		AlienDictionary dic = new AlienDictionary();
		dic.addWord("Mght", "mamma");
		dic.addWord("aba", "nonna");
		dic.addWord("qwe", "casa");
		
		System.out.println("Stampo il dizionario:\n"+dic);
		
		//aggiorno traduzione di "qwe" con cane
		//dic.addWord("qwe", "cane");
		//System.out.println("Stampo il dizionario con traduzione aggiornata\n:"+dic);
		
		//aggiungo ulteriore traduzione
		dic.addWord("qwe", "cane");
		System.out.println("Stampo il dizionario con traduzione aggiornata\n:"+dic);
		
		//traduco parola esistente
		System.out.println("Traduzione della parola \"Mght\": "+dic.translateWord("Mght"));
		//traduco parola inesistente --> null
		System.out.println("Traduzione della parola \"buba\": "+dic.translateWord("buba"));
		
		//split su ?
		String s = "a?a";
		//String z = "a a";
		//String e[]= s.split("?");
		//String e[]= z.split(" ");
		int i = s.indexOf('?');
		String s1 = s.substring(0, i); //i escluso
		String s2 = s.substring(i+1, s.length()); //i escluso
		
		System.out.println("\nParola splittata in: "+s1+"  "+s2);
		
	}

}
