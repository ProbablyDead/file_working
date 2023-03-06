import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.io.FileOutputStream;

/**
 * Класс работы с файлами
 * @author Yakiza
 * */

public class FileWorker {
  private HashMap<Integer, Integer> table = new HashMap<>();
  private String inputPath;
  private String outputPath;
  private double inputFileSize;
  private long addedSymbols = 0;
  private GUI bar;
  private boolean isDone = false;

  /**
   * Конструктор без параметров запрашивает пути к файлам у пользователя в консоли
   **/
  FileWorker () {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter input file's path\n-> ");
    this.inputPath = in.nextLine();
    System.out.print("Enter output file's path\n-> ");
    this.outputPath = in.nextLine();

    in.close();
  }

  /**
   * Конструктор с двумя строками записывает два пути
   * @param inputPath путь к исходному файлу
   * @param outputPath путь к файлу для записи
  */
  FileWorker (String inputPath, String outputPath) {
    this.inputPath = inputPath;
    this.outputPath = outputPath;
  }

  /**
   * Метод проверки символа на принадлежность к латинскому алфавиту
   * @param c
   * @return true/false
  */
  private boolean isLatinLetter (int c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

  /**
   * Метод добавления символа в хэш-таблицу
   * @param c символ
  */
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
    bar.setBarValue((int)(++addedSymbols/inputFileSize*100));
  }

  /**
   * Метода добавления строки в хэш-таблицу
   * @param str строка
  */
  private void updateMapWtString (String str) {
    str.chars().forEach(c -> addValue(c));
    isDone = true;
  }

  /**
   * Метод получения строки для записи ее в выходной файл
   * @return искомая строка
  */
  private String getResultString () {
    String result = "";

    for (int a : table.keySet()) {
        result += (char)a + " - " + table.get(a) + "\n"; 
    }
    
    return result;
  }

  /**
   * Метод работы с файлом 
   * @throws IOException
  */
  public void checkFile () throws IOException {

         new SwingWorker<Void,Void>() {

          protected Void doInBackground() throws IOException {
            BufferedReader inputBuf;
            FileOutputStream outputStream;
            
            bar = new GUI(inputPath);
            try {
              File input = new File(inputPath);
              inputFileSize = input.length();
              inputBuf = new BufferedReader(new FileReader(input));
            }
            catch (IOException e) {
              JOptionPane.showMessageDialog(new JFrame(), "Input file's path is invalid");
              throw new IOException("Input file's path is invalid");
            }

            try {
              outputStream = new FileOutputStream(outputPath);
            }
            catch (IOException e) {
              JOptionPane.showMessageDialog(new JFrame(), "Output file's path is invalid");
              inputBuf.close();
              throw new IOException("Output file's path is invalid");
            }

            bar.setVisible(true);

            String line = inputBuf.readLine();
            while (line != null) {
              updateMapWtString(line);
              line = inputBuf.readLine();
            }
            
            inputBuf.close();
            outputStream.write(getResultString().getBytes());
            outputStream.close();

            return null;
          };

          protected void done () {
            bar.setVisible(false);
            bar.dispose();

            if (isDone) {
              JOptionPane.showMessageDialog(new JFrame(), "File successfully processed");
            }
          };
        }.execute();
        
  }
}
