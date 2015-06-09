package net.codus.exam.search;

import java.util.List;

/**
 * Created by Ohad Serfaty on 6/9/15.
 */
public interface SearchEngine {

  public void add(Document document);

  public List<DocumentId> search(List<String> terms);

}
