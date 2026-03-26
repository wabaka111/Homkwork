import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class app extends JFrame implements ActionListener {

    private JTextField display;
    private StringBuilder input = new StringBuilder();
    private double num1 = 0, num2 = 0;
    private char operator = ' ';
    private boolean startNewInput = true;
    public app() {
        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setEditable(false);
        display.setText("0");
        add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] buttons = {
                "%", "CE", "C", "⌫",
                "1/x", "x²", "2√x", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.addActionListener(this);

            if (text.equals("=")) {
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            }
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
            case ".":
                if (startNewInput) {
                    input.setLength(0);
                    startNewInput = false;
                }
                if (command.equals(".") && input.indexOf(".") != -1) break; // 避免多个小数点
                input.append(command);
                display.setText(input.toString());
                break;
            case "+": case "-": case "×": case "÷":
                if (!startNewInput) {
                    num1 = Double.parseDouble(input.toString());
                    operator = command.charAt(0);
                    startNewInput = true;
                }
                break;
            case "=":
                if (operator != ' ' && !startNewInput) {
                    num2 = Double.parseDouble(input.toString());
                    double result = 0;
                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '×': result = num1 * num2; break;
                        case '÷':
                            if (num2 != 0) result = num1 / num2;
                            else display.setText("Error");
                            break;
                    }
                    if (num2 != 0 || operator != '÷') {
                        display.setText(String.valueOf(result));
                        input.setLength(0);
                        input.append(result);
                        startNewInput = true;
                    }
                }
                break;
            case "C": // 清空所有
                input.setLength(0);
                num1 = num2 = 0;
                operator = ' ';
                startNewInput = true;
                display.setText("0");
                break;
            case "CE": // 清空当前输入
                input.setLength(0);
                startNewInput = true;
                display.setText("0");
                break;
            case "⌫": // 退格
                if (input.length() > 0) {
                    input.deleteCharAt(input.length() - 1);
                    display.setText(input.length() == 0 ? "0" : input.toString());
                }
                break;
            case "+/-": // 正负号
                if (input.length() > 0) {
                    double val = Double.parseDouble(input.toString());
                    val *= -1;
                    input.setLength(0);
                    input.append(val);
                    display.setText(input.toString());
                }
                break;

            default:
                break;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new app().setVisible(true);
        });
    }
}