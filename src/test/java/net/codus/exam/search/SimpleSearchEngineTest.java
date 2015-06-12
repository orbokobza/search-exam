package test.java.net.codus.exam.search;

import main.java.net.codus.exam.search.Document;
import main.java.net.codus.exam.search.DocumentId;
import main.java.net.codus.exam.search.MySearchEngine;
import main.java.net.codus.exam.search.SimpleSearchEngine;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ohad Serfaty on 6/9/15.
 */
public class SimpleSearchEngineTest {

  @Test
  public void testSimple(){
	  long startTime = System.nanoTime();
	  
	  
    SimpleSearchEngine searchEngine = new SimpleSearchEngine();
   // MySearchEngine searchEngine = new MySearchEngine();
    Arrays.asList(
        new Document(new DocumentId(0) , "hello world"),
        new Document(new DocumentId(1) , "shalom olam dog"),
        new Document(new DocumentId(2) , "foo bar food"),
        new Document(new DocumentId(3) , "dog cat world"),
        new Document(new DocumentId(4) , "hello cat")).forEach(searchEngine::add);

    assertEquals(Arrays.asList(), searchEngine.search(Arrays.asList("")));
    assertEquals(Arrays.asList(new DocumentId(3), new DocumentId(4)), searchEngine.search(Arrays.asList("cat")));
    assertEquals(Arrays.asList(new DocumentId(2)), searchEngine.search(Arrays.asList("foo")));
    assertEquals(Collections.emptyList(), searchEngine.search(Arrays.asList("kuku")));
    
	long endTime = System.nanoTime();
	System.out.println("Took simple "+(endTime - startTime) + " ns"); 
  }

  @Test
  public void testRandom(){
	  long startTime = System.nanoTime();
	  
    Random random = new Random();
    List<Document> documents = new ArrayList<>();
     SimpleSearchEngine searchEngine = new SimpleSearchEngine();
  //  MySearchEngine searchEngine = new MySearchEngine();

    int DOCUMENTS = 20;
    int MAX_TERMS_IN_DOC = 200;
    int TERMS = 2000;

    for (int i=0; i<DOCUMENTS; i++) {
      int terms = 1+random.nextInt(MAX_TERMS_IN_DOC);
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < terms; j++) {
        sb.append("term" + random.nextInt(TERMS));
        sb.append(' ');
      }

      Document document = new Document(new DocumentId(i), sb.toString());
      assertEquals(document.getTerms().length, terms);
      documents.add(document);
      searchEngine.add(document);
    }

    for (int i=0; i<TERMS; i++){
      String term = "term" + i;
      
      List<DocumentId> documentsWithTerm = searchEngine.search(term);
      if (i%50 == 0) {
        System.out.println(i);
        System.out.println("documentsWithTerm:" + documentsWithTerm);
      }
      for (Document document : documents){
        boolean containsTerm = Arrays.asList(document.getTerms()).contains(term);
        assertEquals(containsTerm , documentsWithTerm.contains(document.getId()));
      }
    }
	long endTime = System.nanoTime();
	System.out.println("Took random: "+(endTime - startTime) + " ns"); 
  }

}
