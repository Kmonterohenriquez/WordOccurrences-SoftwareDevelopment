import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Kevin Montero
 *
 */
public class Words {

	private String word;
	private int counts;

	public Words() {
		this.word = "";
		this.counts = 0;
	}

	/**
	 * Constructor for class
	 * 
	 * @param word
	 * @param counts
	 */
	public Words(String word, int counts) {
		this.word = word;
		this.counts = counts;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	/**
	 * Counts the occurrences of each word
	 * 
	 * @param html
	 * @return TreeMap
	 */
	public static Map<String, Integer> countWords(String html) {
		Map<String, Integer> wordMap = new TreeMap<>();
		try {
			Document doc = Jsoup.connect(html).get();
			String stringHTML = doc.toString();
			String sanitizedHTML = Jsoup.clean(stringHTML, Whitelist.none());

			Scanner sc = new Scanner(sanitizedHTML);

			while (sc.hasNext()) {
				String s = sc.next().toLowerCase().replaceAll("[^a-zA-Z ']", "");
				if (!wordMap.containsKey(s))
					wordMap.put(s, 1);
				else
					wordMap.put(s, wordMap.get(s) + 1);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordMap;
	}

	/**
	 * Sorts by counts of each word from highest to lowest
	 * 
	 * @param wordMap
	 * @return list
	 */
	public static List<Entry<String, Integer>> sortByOccurrence(Map<String, Integer> wordMap) {
		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		return list;
	}

	/**
	 * Gets top 20 words of highest occurrences
	 * 
	 * @param url
	 * @return ObservableList
	 */
	public static ObservableList<Words> getWords(String url) {
		ObservableList<Words> OL_wordMap = FXCollections.observableArrayList();
		Map<String, Integer> wordMap = Words.countWords(url);
		List<Entry<String, Integer>> list = Words.sortByOccurrence(wordMap);

		int count = 0;
		for (Map.Entry<String, Integer> entry : list) {
			if (count < 20) {
				OL_wordMap.add(new Words(entry.getKey(), entry.getValue()));
				count++;

				// Adds top 20 words to database
				try {
					SQLWordConnector.post(entry.getKey(), entry.getValue());
				} catch (Exception e) {
					System.out.println("Could not add word to database from Words.getWords()");
				}
			}
		}
		try {
			SQLWordConnector.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OL_wordMap;
	}
}