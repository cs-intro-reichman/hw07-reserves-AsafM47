

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i=0; i<dictionary.length; i++){
			dictionary[i]= in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean flag = false;
		for(int i = 0; i< dictionary.length; i++){
			if(dictionary[i].equals(word)){
				flag = true;
			}
		}
		return flag;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		String nHash = "";
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			nHash = hashtag.substring(0,i);
			if(existInDictionary(nHash, dictionary) == true){
				System.out.println(nHash);
					hashtag =hashtag.substring(i);
					breakHashTag(hashtag, dictionary);
					break;
			}
        }
    }

}
