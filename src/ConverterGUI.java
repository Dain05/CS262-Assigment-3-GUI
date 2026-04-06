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

public class ConverterGUI extends JFrame {

    private JTextField amountField;
    private JComboBox<String> currencyBox;
    private JTextField resultField;
    private JButton convertBtn;
    private JButton clearBtn;

    public ConverterGUI() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Enter Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Select Currency:"));
        currencyBox = new JComboBox<>(new String[]{"USD", "CAD", "EUR"});
        add(currencyBox);

        add(new JLabel("JMD Result:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        convertBtn = new JButton("Convert");
        add(convertBtn);

        clearBtn = new JButton("Clear");
        add(clearBtn);

        convertBtn.addActionListener(e -> {
            if (amountField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an amount.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountField.getText());
                String currency = currencyBox.getSelectedItem().toString();

                double result = ConverterLogic.convertToJMD(amount, currency);

                resultField.setText(String.format("%.2f", result));

                try (PrintWriter out = new PrintWriter(new FileWriter("conversion_history.txt", true))) {
                    out.println("Amount: $" + String.format("%.2f", amount)
                            + " | Currency: " + currency
                            + " | JMD: $" + String.format("%.2f", result));
                    out.println("----------------------------------");
                    out.println();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Could not save conversion history.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter numbers only.");
            }
        });

        clearBtn.addActionListener(e -> {
            amountField.setText("");
            resultField.setText("");
            currencyBox.setSelectedIndex(0);
            amountField.requestFocus();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ConverterGUI();
    }
}