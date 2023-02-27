import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends JFrame {
  private JButton check_file;
  private Container panel;

  GUI () {
    super("File transform");
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screen.width/2 - 250, screen.height/2 - 150, 500, 300); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    check_file = new JButton("Check file");
    check_file.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        String inputFile = JOptionPane.showInputDialog("Enter input file's name");
        FileWorker worker = new FileWorker(inputFile, 
            JOptionPane.showInputDialog("Enter output file's name"));
        try {
          worker.checkFile();
        }
        catch (IOException e) {
          JOptionPane.showMessageDialog(panel, e.getMessage());
          return;
        }
        catch (NullPointerException e) {
          JOptionPane.showMessageDialog(panel, "Please, input correctly");
        }
      }
    });
    check_file.setPreferredSize(new Dimension(450, 160));

    panel = getContentPane();
    panel.add(check_file, BorderLayout.NORTH);
    setContentPane(panel);

    pack();

    setVisible(true);
  }

  private JProgressBar progressBar;
  private Container panelForBar;
  GUI (String fileName) {
    super(fileName);

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screen.width/2 + 50, screen.height/2 + 50, 500, 300); 

    progressBar = new JProgressBar();
    progressBar.setStringPainted(true);
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setValue(0);

    panelForBar = getContentPane();
    panelForBar.add(progressBar, BorderLayout.NORTH);
    setContentPane(panelForBar);

    setVisible(true);
  }

  public void updateBar (int value) {
    progressBar.setValue(value);
  }

}
