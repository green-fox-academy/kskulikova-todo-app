import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    String file = "tasks.txt";

    if (args.length == 0) {
      System.out.println("Command Line Todo application\n" + "=============================\n\n" +
          "Command line arguments:\n" + "-l   Lists all the tasks\n" + "-a   Adds a new task\n" +
          "-r   Removes an task\n" + "-c   Completes an task\n");
    } else if (args[0].equals("-l")) {
      System.out.println(toNumberedItems(ListTasks(file)));
    } else if (args[0].equals("-a")) {
      if (args.length == 1) {
        System.out.println("Unable to add: no task provided");
      } else {
        StringBuilder newTask = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
          newTask.append(args[i]).append(" ");
        }
        AddTask(file, newTask.toString());
      }
    }

  }

  private static String toNumberedItems(ArrayList<Task> tasks) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
      result.append(i + 1).append(". ").append(tasks.get(i).getText()).append("\n");
    }
    return result.toString();

  }

  private static List<String> OpenFile(String file) {
    List<String> content = new ArrayList<>();
    try {
      Path src = Paths.get(file);
      content = Files.readAllLines(src);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Unable to read file: tasks.txt");
    }
    return content;
  }

  private static void OverwriteTasks(String file, ArrayList<Task> tasks) {
    try {
      Path src = Paths.get(file);
      Files.write(src, tasks.stream().map(Task::getText).collect(Collectors.toList()));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Unable to read file: tasks.txt");
    }
  }


  private static ArrayList<Task> ListTasks(String file) {
    List<String> content = OpenFile(file);
    ArrayList<Task> tasks = new ArrayList<>();
    for (String line : content) {
      tasks.add(new Task(line));
    }
    return tasks;
  }

  private static void AddTask(String file, String newTask) {
    ArrayList<Task> tasks = ListTasks(file);
    tasks.add(new Task(newTask));
    OverwriteTasks(file, tasks);
  }
}




