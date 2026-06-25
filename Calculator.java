import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "0";
    private String previousInput = "";
    private String operation = "";
    private boolean startNewNumber = true;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, eqButton, clButton, clrButton, backButton, dotButton;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        mainPanel.add(display, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Create Number Buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        }

        // Operation Buttons
        clButton = createButton("C", new Color(244, 67, 54)); // Red
        clrButton = createButton("CE", new Color(244, 67, 54));
        backButton = createButton("←", new Color(244, 67, 54));
        addButton = createButton("+", new Color(255, 152, 0)); // Orange
        subButton = createButton("-", new Color(255, 152, 0));
        mulButton = createButton("×", new Color(255, 152, 0));
        divButton = createButton("÷", new Color(255, 152, 0));
        eqButton = createButton("=", new Color(76, 175, 80)); // Green
        dotButton = createButton(".", Color.WHITE);

        // Add to panel
        buttonPanel.add(clButton);
        buttonPanel.add(clrButton);
        buttonPanel.add(backButton);
        buttonPanel.add(divButton);

        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(mulButton);

        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(subButton);

        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);

        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(dotButton);
        buttonPanel.add(eqButton);
        buttonPanel.add(new JLabel()); // Empty space

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(bgColor);
        btn.setForeground(bgColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                appendNumber(String.valueOf(i));
                updateDisplay();
                return;
            }
        }

        // Operation buttons
        if (source == addButton) {
            setOperation("+");
        } else if (source == subButton) {
            setOperation("-");
        } else if (source == mulButton) {
            setOperation("×");
        } else if (source == divButton) {
            setOperation("÷");
        } else if (source == eqButton) {
            calculate();
        } else if (source == clButton) {
            clearAll();
        } else if (source == clrButton) {
            clearEntry();
        } else if (source == backButton) {
            backspace();
        } else if (source == dotButton) {
            addDecimal();
        }

        updateDisplay();
    }

    private void appendNumber(String num) {
        if (startNewNumber) {
            currentInput = num;
            startNewNumber = false;
        } else {
            if (currentInput.equals("0") && !num.equals(".")) {
                currentInput = num;
            } else {
                currentInput += num;
            }
        }
    }

    private void setOperation(String op) {
        if (!operation.isEmpty() && !startNewNumber) {
            calculate();
        }
        previousInput = currentInput;
        operation = op;
        startNewNumber = true;
    }

    private void calculate() {
        if (previousInput.isEmpty() || operation.isEmpty()) return;

        try {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operation) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "×" -> result = num1 * num2;
                case "÷" -> {
                    if (num2 == 0) {
                        display.setText("Error: Div by 0");
                        currentInput = "0";
                        previousInput = "";
                        operation = "";
                        startNewNumber = true;
                        return;
                    }
                    result = num1 / num2;
                }
            }

            currentInput = formatResult(result);
            operation = "";
            previousInput = "";
            startNewNumber = true;
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.10f", result).replaceAll("0+$", "").replaceAll("\\.$", "");
        }
    }

    private void addDecimal() {
        if (startNewNumber) {
            currentInput = "0.";
            startNewNumber = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
    }

    private void backspace() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        } else {
            currentInput = "0";
            startNewNumber = true;
        }
    }

    private void clearEntry() {
        currentInput = "0";
        startNewNumber = true;
    }

    private void clearAll() {
        currentInput = "0";
        previousInput = "";
        operation = "";
        startNewNumber = true;
    }

    private void updateDisplay() {
        display.setText(currentInput);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}