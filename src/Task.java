import java.util.ArrayList;
import java.util.List;

public class Task {
  String text;
  boolean completed;

  public void complete() {
    this.completed = true;
  }

  public Task(String text) {
    this.text = text;
  }

  public void fill(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
