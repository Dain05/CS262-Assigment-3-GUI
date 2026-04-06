// Assignment #3 - GUI Applications
// Course: CS262 - Introduction to Object-Oriented Programming II
// Lecturer: Doron Williams

// Group Members:
// Dain Thorpe - ID: 2301011605
// Joan Johnson-Brown - ID: 2401010520
// Shanique Wisdom - ID: 2401010649
// Pasha Pinnock - ID: 2401011323
// Dante Graham - ID: 2401010192

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class TextEditorGUI extends JFrame {

    private JTextArea textArea;

    public TextEditorGUI() {
        setTitle("Simple Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        saveItem.addActionListener(e -> {
            PrintWriter out = null;

            try {
                out = new PrintWriter(new FileWriter("editor_output.txt"));
                out.print(textArea.getText());
                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        });

        openItem.addActionListener(e -> {
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader("editor_output.txt"));
                textArea.setText("");

                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File loaded successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file.");
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error closing file.");
                }
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new TextEditorGUI();
    }
}