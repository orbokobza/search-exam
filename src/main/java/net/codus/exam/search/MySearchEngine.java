package main.java.net.codus.exam.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySearchEngine implements SearchEngine {
	
	private ArrayList<Document> documents;
	private HashMap<String, String> words;
	private int num_of_documents;
	
	// Constructor
	public MySearchEngine() {
		documents = new  ArrayList<Document>();
		words = new HashMap<String, String>();
		num_of_documents = 0;
	}

	@Override
	public void add(Document document) {
		
		// Add the document to the documents list
		documents.add(document);
		
		// Update the terms of the added document in the HashMap(words)
		 for (String term : document.terms){
			 
			 // If the term is already exist in 'words'- update with zeros(if neccecery) and '1' in the end
			 if (words.containsKey(term)){
				String value = words.get(term);
				// Amount of zeros to add
				int diff = num_of_documents - value.length();
				
				for (int i = 0; i < diff; i++) {
					value += '0';
				}
				value += '1';
				
				words.remove(term);
				words.put(term, value);
			 } 
			 // Else - insert a new element with the relevant value
			 else {
				 String value = "";
				 
				 for (int i = 0; i < num_of_documents; i++) {
					value += '0';
				}
				 value += '1';
				 
				 words.put(term, value); 
			 }
		 }
		 num_of_documents++;
	}

	@Override
	public List<DocumentId> search(List<String> terms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public static void main(String args[]){
		
		MySearchEngine engine = new MySearchEngine();
		
        Document doc1 = new Document(new DocumentId(0) , "hello world");
        Document doc2 = new Document(new DocumentId(1) , "shalom olam dog");
        Document doc3 = new Document(new DocumentId(2) , "foo bar food");
        Document doc4 = new Document(new DocumentId(3) , "dog cat world");
		
        engine.add(doc1);
        engine.add(doc2);
        engine.add(doc3);
        engine.add(doc4);
        
        for (int i = 0; i < engine.documents.size(); i++) {
        	for (int j = 0; j < engine.documents.get(i).terms.length; j++) {
				System.out.print(engine.documents.get(i).terms[j] + " ");
			}
		}
        System.out.println();
        
        for (String word : engine.words.keySet()){
        	System.out.println("key: " + word);
        	System.out.println("value: " + engine.words.get(word));
        	System.out.println();
        }
        
	}

}
