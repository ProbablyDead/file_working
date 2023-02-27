import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.SwingWorker;
import java.io.FileOutputStream;

public class FileWorker {
  private HashMap<Integer, Integer> table = new HashMap<>();
  private String inputPath;
  private String outputPath;
  private double inputFileSize;
  private long addedSymbols = 0;
  private GUI bar;

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
    bar.updateBar((int)(++addedSymbols/inputFileSize*100));
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

  public void checkFile () throws IOException {
        
      bar = new GUI(inputPath);
  
      new SwingWorker<Void,Void>() {

        protected Void doInBackground() throws Exception {
          BufferedReader inputBuf;
          FileOutputStream outputStream;
          
          try {
            File input = new File(inputPath);
            inputFileSize = input.length();
            inputBuf = new BufferedReader(new FileReader(input));
          }
          catch (IOException e) {
            throw new IOException("Input file's path is invalid");
          }

          try {
            outputStream = new FileOutputStream(outputPath);
          }
          catch (IOException e) {
            throw new IOException("Output file's path is invalid");
          }

          String line = inputBuf.readLine();
          while (line != null) {
            updateMapWtString(line);
            line = inputBuf.readLine();
          }
          
          inputBuf.close();
          outputStream.write(getResultString().getBytes());
          outputStream.close();
          bar.setVisible(false);

          return null;
        };

        protected void done () {
          System.out.println("fin");
        };
    }.execute();
  }
}
