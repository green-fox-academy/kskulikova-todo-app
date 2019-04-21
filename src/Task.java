class Task {

  private String text;
  private boolean checked;

  Task(String text) {
    this(text, false);
  }

  private Task(String text, boolean checked) {
    this.text = text;
    this.checked = checked;
  }

  static Task readLineFromFile(String line) {
    String text = line.substring(4);
    boolean checked = line.startsWith("[x] ");
    return new Task(text, checked);
  }

  void setChecked() {
    this.checked = true;
  }

  @Override
  public String toString() {
    return "[" + (checked ? "x" : " ") + "] " + text;
  }
}
