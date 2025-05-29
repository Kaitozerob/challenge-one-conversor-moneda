package main.java.ui;

import main.java.application.ConversionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private JComboBox<String> sourceCurrencyBox;
    private JComboBox<String> targetCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    private ConversionService conversionService;

    public MainWindow() {
        super("Currency Converter");
        this.conversionService = new ConversionService();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Source Currency:"));
        sourceCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY"});
        add(sourceCurrencyBox);

        add(new JLabel("Target Currency:"));
        targetCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY"});
        add(targetCurrencyBox);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this::onConvert);
        add(convertButton);

        resultLabel = new JLabel("Result: ");
        add(resultLabel);

        setVisible(true);
    }

    private void onConvert(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double exchangeRate = 0.85;
            double result = conversionService.convert(amount, exchangeRate);
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}