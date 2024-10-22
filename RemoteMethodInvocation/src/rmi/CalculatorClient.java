package rmi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField display;
    private double num1, num2, result;
    private String operator;

    public CalculatorClient() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                Calculator calculator = (Calculator) registry.lookup("Calculator");

                switch (command) {
                    case "C":
                        display.setText("");
                        break;
                    case "=":
                        num2 = Double.parseDouble(display.getText());
                        switch (operator) {
                            case "+":
                                result = calculator.add(num1, num2);
                                break;
                            case "-":
                                result = calculator.subtract(num1, num2);
                                break;
                            case "*":
                                result = calculator.multiply(num1, num2);
                                break;
                            case "/":
                                result = calculator.divide(num1, num2);
                                break;
                        }
                        display.setText(String.valueOf(result));
                        break;
                    default:
                        if ("+-*/".contains(command)) {
                            operator = command;
                            num1 = Double.parseDouble(display.getText());
                            display.setText("");
                        } else {
                            display.setText(display.getText() + command);
                        }
                        break;
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalculatorClient();

	}

}
