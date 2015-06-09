package net.codus.exam.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ohad Serfaty on 6/9/15.
 */
public class SimpleSearchEngine implements SearchEngine {

  List<Document> documents = new ArrayList<>();

  @Override
  public void add(Document document) {
    documents.add(document);
  }

  public List<DocumentId> search(String... terms) {
    return search(Arrays.asList(terms));
  }

  @Override
  public List<DocumentId> search(List<String> terms) {
    return documents.stream().filter(document -> Arrays.asList(document.getTerms()).containsAll(terms))
        .map(Document::getId).collect(Collectors.toList());
  }
}
