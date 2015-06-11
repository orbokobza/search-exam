package main.java.net.codus.exam.search;

/**
 * Created by Ohad Serfaty on 6/9/15.
 */
public class DocumentId {

  public final int value;

  public DocumentId(int value) {
    this.value = value;
  }

  public String toString(){
    return Integer.toString(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DocumentId))
      return false;
    return ((DocumentId)obj).value == this.value;
  }
}
