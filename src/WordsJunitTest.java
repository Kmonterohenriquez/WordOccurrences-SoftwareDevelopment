import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.collections.ObservableList;

class WordsJunitTest {

	/* CORRECT ENTRIES
	 * 
	 * We want to make sure each word is completely removed
	 * of all HTML tags.
	 * 
	 * We also want to make sure all words are sanitized
	 * of improper punctuation.
	 * 
	 * Only whole words are allowed in the ObservableList.
	 */
	
	@Test
	void test() {
		Words test = new Words();

		ObservableList<Words> output = test.getWords("http://shakespeare.mit.edu/macbeth/full.html");
		for (Words word : output) {
			assertFalse((word.getWord().contains("<")));
			assertFalse((word.getWord().contains(">")));
			assertFalse((word.getWord().contains("\\")));
			assertFalse((word.getWord().contains("/")));
			assertFalse((word.getWord().contains(":")));
			assertFalse((word.getWord().contains("\"")));
			assertFalse((word.getWord().contains(".")));
			assertFalse((word.getWord().contains(",")));
			assertFalse((word.getWord().contains("?")));
			assertFalse((word.getWord().contains("`")));
			assertFalse((word.getWord().contains("~")));
			assertFalse((word.getWord().contains("!")));
			assertFalse((word.getWord().contains("@")));
			assertFalse((word.getWord().contains("#")));
			assertFalse((word.getWord().contains("$")));
			assertFalse((word.getWord().contains("%")));
			assertFalse((word.getWord().contains("^")));
			assertFalse((word.getWord().contains("&")));
			assertFalse((word.getWord().contains("*")));
			assertFalse((word.getWord().contains("(")));
			assertFalse((word.getWord().contains(")")));
			assertFalse((word.getWord().contains("_")));
			assertFalse((word.getWord().contains("=")));
			assertFalse((word.getWord().contains("+")));
			assertFalse((word.getWord().contains("1")));
			assertFalse((word.getWord().contains("2")));
			assertFalse((word.getWord().contains("3")));
			assertFalse((word.getWord().contains("4")));
			assertFalse((word.getWord().contains("5")));
			assertFalse((word.getWord().contains("6")));
			assertFalse((word.getWord().contains("7")));
			assertFalse((word.getWord().contains("8")));
			assertFalse((word.getWord().contains("9")));
			assertFalse((word.getWord().contains("0")));
			assertFalse((word.getWord().contains("[")));
			assertFalse((word.getWord().contains("]")));
			assertFalse((word.getWord().contains("{")));
			assertFalse((word.getWord().contains("}")));
			assertFalse((word.getWord().contains("|")));
		}
	}
}
