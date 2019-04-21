import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    String file = "tasks.txt";

    if (args.length == 0) {
      printUsageInfo();
    } else if (args[0].equals("-l")) {
      System.out.println(toNumberedItems(listTasks(file)));
    } else if (args[0].equals("-a")) {
      if (args.length == 1) {
        System.out.println("Unable to add: no task provided");
      } else {
        StringBuilder newTask = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
          newTask.append(args[i]).append(" ");
        }
        addTask(file, newTask.toString());
      }
    } else if (args[0].equals("-r")) {
      if (args.length == 1) {
        System.out.println("Unable to remove: no index provided");
      } else {
        if (tryParseInt(args[1])) {
          if (checkIndexOutOfBounds(file, Integer.parseInt(args[1]))) {
            removeTask(file, Integer.parseInt(args[1]));
          } else {
            System.out.println("Unable to remove: index out of bound");
          }
        } else {
          System.out.println("Unable to remove: index is not a number");
        }
      }
    } else if (args[0].equals("-c")) {
      if (args.length == 1) {
        System.out.println("Unable to check: no index provided");
      } else {
        if (tryParseInt(args[1])) {
          if (checkIndexOutOfBounds(file, Integer.parseInt(args[1]))) {
            checkTask(file, Integer.parseInt(args[1]));
          } else {
            System.out.println("Unable to check: index out of bound");
          }
        } else {
          System.out.println("Unable to check: index is not a number");
        }
      }
    } else {
      System.out.println("Unsupported argument");
      printUsageInfo();

    }

  }

  private static void printUsageInfo() {
    System.out.println("Command Line Todo application\n" + "=============================\n\n" +
        "Command line arguments:\n" + "-l   Lists all the tasks\n" + "-a   Adds a new task\n" +
        "-r   Removes an task\n" + "-c   Completes an task\n");
  }

  private static Boolean tryParseInt(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private static Boolean checkIndexOutOfBounds(String file, int index) {
    ArrayList<Task> tasks = listTasks(file);
    return index <= tasks.size() && index >= 1;
  }

  private static String toNumberedItems(ArrayList<Task> tasks) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
      result.append(i + 1).append(". ").append("[ ] ").append(tasks.get(i).getText()).append("\n");
    }
    return result.toString();

  }

  private static List<String> openFile(String file) {
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

  private static void overwriteTasks(String file, ArrayList<Task> tasks) {
    try {
      Path src = Paths.get(file);
      Files.write(src, tasks.stream().map(Task::getText).collect(Collectors.toList()));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Unable to read file: tasks.txt");
    }
  }


  private static ArrayList<Task> listTasks(String file) {
    List<String> content = openFile(file);
    ArrayList<Task> tasks = new ArrayList<>();
    for (String line : content) {
      tasks.add(new Task(line));
    }
    return tasks;
  }

  private static void addTask(String file, String newTask) {
    ArrayList<Task> tasks = listTasks(file);
    tasks.add(new Task(newTask));
    overwriteTasks(file, tasks);
  }

  private static void removeTask(String file, int index) {
    ArrayList<Task> tasks = listTasks(file);
    tasks.remove(index - 1);
    overwriteTasks(file, tasks);
  }

  private static void checkTask(String file, int index) {
    ArrayList<Task> tasks = listTasks(file);
    System.out.println("I'm checking the task number " + index);
  }


}





