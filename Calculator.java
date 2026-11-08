
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField display;

    double num1, num2, result;
    char operator;

    JButton[] buttons = new JButton[16];

    String[] values = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "C", "0", "=", "+"
    };

    Calculator() {

        // Window
        setTitle("Swing Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        // Button Panel
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Creating Buttons
        for (int i = 0; i < 16; i++) {

            buttons[i] = new JButton(values[i]);

            buttons[i].setFont(
                new Font("Arial", Font.BOLD, 22)
            );

            buttons[i].addActionListener(this);

            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Button Click Events
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        // Clear
        if (command.equals("C")) {

            display.setText("");

            num1 = 0;
            num2 = 0;
            result = 0;

        }

        // Equals
        else if (command.equals("=")) {

            if (display.getText().isEmpty()) {
                return;
            }

            num2 = Double.parseDouble(display.getText());

            switch (operator) {

                case '+':
                    result = num1 + num2;
                    break;

                case '-':
                    result = num1 - num2;
                    break;

                case '*':
                    result = num1 * num2;
                    break;

                case '/':
                    if (num2 == 0) {
                        display.setText("Cannot divide by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            display.setText(String.valueOf(result));

        }

        // Operators
        else if (command.equals("+") ||
                 command.equals("-") ||
                 command.equals("*") ||
                 command.equals("/")) {

            if (display.getText().isEmpty()) {
                return;
            }

            num1 = Double.parseDouble(display.getText());

            operator = command.charAt(0);

            display.setText("");

        }

        // Numbers
        else {

            display.setText(
                display.getText() + command
            );

        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Calculator();
        });

    }
}
