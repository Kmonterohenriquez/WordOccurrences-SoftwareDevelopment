import org.junit.Test;

import javafx.collections.ObservableList;

class CountsJunitTest {

	
	/* WORD OCCURRENCES
	 * 
	 * We want to make sure that all words are being accounted for; 
	 * so the occurrence of each word should be greater than 0
	 * 
	 */
	
	@Test
	void test() {
		Words test = new Words();

		ObservableList<Words> output = test.getWords("http://shakespeare.mit.edu/macbeth/full.html");
		for (Words word : output) {
			assertFalse(word.getCounts() <= 0);
		}
	}

	private void assertFalse(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
