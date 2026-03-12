import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorJava extends JFrame implements ActionListener {

    JTextField display;
    String operator = "";
    double firstNumber = 0;
    boolean startNew = true;

    public CalculatorJava() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "C","√","",""
        };

        for (String text : buttons) {
            if (text.equals("")) {
                panel.add(new JLabel());
                continue;
            }
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9.]")) {
            if (startNew) {
                display.setText(cmd.equals(".") ? "0." : cmd);
                startNew = false;
            } else {
                if (cmd.equals(".") && display.getText().contains(".")) return;
                display.setText(display.getText() + cmd);
            }
        }
        else if (cmd.matches("[+\\-*/]")) {
            firstNumber = Double.parseDouble(display.getText());
            operator = cmd;
            startNew = true;
        }
        else if (cmd.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Eroare");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }
            display.setText(String.valueOf(result));
            startNew = true;
        }
        else if (cmd.equals("C")) {
            display.setText("0");
            operator = "";
            firstNumber = 0;
            startNew = true;
        }
        else if (cmd.equals("√")) {
            double val = Double.parseDouble(display.getText());
            if (val < 0) {
                display.setText("Eroare");
                return;
            }
            display.setText(String.valueOf(Math.sqrt(val)));
            startNew = true;
        }
    }

    public static void main(String[] args) {
        new CalculatorJava();
    }
}
