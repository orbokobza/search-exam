# search-exam
Search Engine Exam

Your task is to code a simple efficient search engine that :
1. can be added with multiple documents
2. can search all documents that contain a certain list of terms

This is the interface that your class needs to implement :
```sh
 public interface SearchEngine {
  public void add(Document document);
  public List<DocumentId> search(List<String> terms);
}
 ```
 A Document object is composed from an id and text which is split into terms automatically :
 ```sh
 public class Document {
  final String[] terms;
  private final DocumentId id;
  
  public Document(DocumentId id, String text){
    this.id = id;
    terms = text.split(" ");
  }
  public String[] getTerms(){ return this.terms; }
  public DocumentId getId(){ return this.id; }
}
 ```
 
 The search method will return all the document ids of all the documents that contain *all* the search terms. A brute force implementation can be found in the class SimpleSearchEngine.java, and a simple demonstration of what the class does can be found in this simple unit test :
```
  @Test
  public void testSimple(){
    SimpleSearchEngine searchEngine = new SimpleSearchEngine();
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
  }
```
 
 Your task is to do the following :
 1. Fork this repository with your own github account, do commits into your own repository - I'd like to see you progress so commit every once in a while so I can see your progress
 2. Implement an efficient implementation of SearchEngine
 3. You have to use Java 8 in order to run this project. You can choose to use gradle or not - whatever you prefer
 4. Test your implementation with the two provided tests, add more tests of your own if you feel that there are corner cases that haven't been provided
 5. You can use any tool that you want or you can build the entire thing yourself - **the important thing is that you do it on your own**
 6. Would be great to compare your results against the naive algorithm - if your implementation is good it should show considerable improvement
