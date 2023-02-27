import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileOutputStream;

public class FileWorker {
  private HashMap<Integer, Integer> table = new HashMap<>();
  private String inputPath;
  private String outputPath;

  FileWorker () {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter input file's path\n-> ");
    this.inputPath = in.nextLine();
    System.out.print("Enter output file's path\n-> ");
    this.outputPath = in.nextLine();

    in.close();
  }

  FileWorker (String inputPath, String outputPath) {
    this.inputPath = inputPath;
    this.outputPath = outputPath;
  }

  private boolean isLatinLetter (int c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

  private void addValue (int c) {
    if (table.containsKey(c)) {
      int count = table.get(c);
      table.put(c, ++count);
    }
    else {
      if (isLatinLetter(c)) {
        table.put(c, 1);
      }
    }
  }

  private void updateMapWtString (String str) {
    str.chars().forEach(c -> addValue(c));
  }

  private String getResultString () {
    String result = "";

    for (int a : table.keySet()) {
        result += (char)a + " - " + table.get(a) + "\n"; 
    }
    
    return result;
  }

  public void checkFile () {
    BufferedReader inputBuf;
    FileOutputStream outputStream;

      try {
        inputBuf = new BufferedReader(new FileReader(new File(inputPath)));
      }
      catch (IOException e) {
        System.out.println("Input file's path is invalid");
        return;
      }

      inputBuf.lines().forEach(str -> updateMapWtString(str));
      try {
        outputStream = new FileOutputStream(outputPath);
        outputStream.write(getResultString().getBytes());
        outputStream.close();
      }
      catch (IOException e) {
        System.out.println("Output file's path is invalid");
      }
  }
}
