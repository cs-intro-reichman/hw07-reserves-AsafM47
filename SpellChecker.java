import java.util.Dictionary;

public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if(word2.length() == 0){
			return word1.length();
		}
		if(word1.length() ==0){
			return word2.length();
		}
		String head1 = word1.substring(0, 1);
		String head2 = word2.substring(0, 1);
		if(head1.equals(head2)){
			word1 = tail(word1);
			word2 = tail(word2);
			return levenshtein(word1, word2);
		}
		else{
			int d1 = 1 + levenshtein(word1, tail(word2));
			int d2 = 1 + levenshtein(tail(word1), word2);
			int d3 = 1+ levenshtein(tail(word1), tail(word2));
			return Math.min(Math.min(d1, d2),d3);			
			
		}


	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i=0; i<dictionary.length; i++){
			dictionary[i]= in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		String tWord = word;
		int tDis = word.length();
		int minDis = word.length(); 
		for(int i = 0; i<dictionary.length;i++){
			tDis = levenshtein(word, dictionary[i]);
			if(tDis < minDis){
				tWord = dictionary[i];
				minDis = tDis;
			}
		}
		if(minDis <= threshold){
			return tWord;
		}
		else{
			return word;
		}
		
	}

}
