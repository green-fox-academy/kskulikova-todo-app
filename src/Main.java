import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    String file = "tasks.txt";

    if (args.length == 0) {
      System.out.println("Command Line Todo application\n" + "=============================\n\n" +
          "Command line arguments:\n" + "-l   Lists all the tasks\n" + "-a   Adds a new task\n" +
          "-r   Removes an task\n" + "-c   Completes an task\n");
    } else if (args[0].equals("-l")) {
      ListTasks(file);
    }

  }

  public static String toNumberedItems(ArrayList<Task> tasks) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
      result.append(i + 1).append(". ").append(tasks.get(i).getText()).append("\n");
    }
    return result.toString();

  }


  private static void ListTasks(String file) {

    try {
      Path src = Paths.get(file);
      ArrayList<Task> tasks = new ArrayList<>();

      List<String> content = Files.readAllLines(src);
      if (content.size() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        for (String line : content) {
          tasks.add(new Task(line));
        }
        System.out.println(toNumberedItems(tasks));
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Unable to read file: tasks.txt");
    }

  }

}


