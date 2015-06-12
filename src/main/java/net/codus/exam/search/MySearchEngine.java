package main.java.net.codus.exam.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class MySearchEngine implements SearchEngine {

	private ArrayList<Document> documents;
	private HashMap<String, BitSet> words;
	private int num_of_documents;

	// Constructor
	public MySearchEngine() {
		documents = new  ArrayList<Document>();
		words = new HashMap<String, BitSet>();
		num_of_documents = 0;
	}

	@Override
	public void add(Document document) {

		// Add the document to the documents list
		documents.add(document);

		// Update the 'terms' of the added document in 'words'
		for (String term : document.terms){
			BitSet value = new BitSet();

			// If the term is already exist in 'words'- update the current document
			if (words.containsKey(term)){
				value = words.get(term);
			} 

			// set the new value and insert to words
			value.set(num_of_documents);
			words.put(term, value);
		}

		num_of_documents++;
	}

	public List<DocumentId> search(String... terms) {
		return search(Arrays.asList(terms));
	}

	@Override
	public ArrayList<DocumentId> search(List<String> terms) {

		ArrayList<DocumentId> result = new ArrayList<DocumentId>();
		ArrayList<BitSet> binary_terms = new ArrayList<BitSet>();
		BitSet bits_result = new BitSet();

		// If there's no terms - return empty list
		if (terms.size() == 0){
			return result;
		}

		// For every term - if the terms exist in 'words', insert to 'binary_terms' in binary representation 
		for (int i = 0; i < terms.size(); i++) {

			// If the term doesn't exist in 'words' - return empty list
			if (!words.containsKey(terms.get(i))){
				return result;
			} 

			binary_terms.add(words.get(terms.get(i)));
		}

		bits_result = binary_terms.get(0);

		// Perform AND operation between all the binaries
		for (int i = 1; i < binary_terms.size(); i++) {
			bits_result.and(binary_terms.get(i));
		}

		//Insert all the relevant indices into 'result'
		for (int i = bits_result.nextSetBit(0); i != -1; i = bits_result.nextSetBit(i + 1)) {
			result.add(new DocumentId(i));
		}
		 
		return result;
	}

}
