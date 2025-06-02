package main.java.ui;

import main.java.application.ConversionService;
import main.java.infrastructure.ExchangeRateApi;

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Source Currency:"), gbc);

        gbc.gridx = 1;
        sourceCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "PEN"});
        add(sourceCurrencyBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Target Currency:"), gbc);

        gbc.gridx = 1;
        targetCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "PEN"});
        add(targetCurrencyBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        amountField = new JTextField();
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this::onConvert);
        add(convertButton, gbc);

        gbc.gridy = 4;
        resultLabel = new JLabel("Result: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, gbc);

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    private void onConvert(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());

            //Llama a la API
            ExchangeRateApi api = new ExchangeRateApi();
            String sourceCurrency = sourceCurrencyBox.getSelectedItem().toString();
            String targetCurrency = targetCurrencyBox.getSelectedItem().toString();
            double exchangeRate = api.getExchangeRate(sourceCurrency, targetCurrency);

            if (exchangeRate > 0) {
                double result = conversionService.convert(amount, exchangeRate);
                resultLabel.setText("Result: " + result);
            } else {
                JOptionPane.showMessageDialog(this, "Error retrieving exchange rate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}