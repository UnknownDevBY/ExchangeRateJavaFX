package controllers;

import entity.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import parsers.ExchangeRateParser;

public class Controller {

    @FXML private TableView table;
    @FXML private TableColumn<Currency, String> code;
    @FXML private TableColumn<Currency, String> title;
    @FXML private TableColumn<Currency, String> value;
    @FXML private TableColumn<Currency, String> symbolCode;
    @FXML private TableColumn<Currency, String> date;
    @FXML private TextField textField;

    private ObservableList<Currency> currencies = FXCollections
            .observableArrayList(ExchangeRateParser
            .parse("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json"));

    private EventHandler action = e -> {
            ObservableList<Currency> tempList = FXCollections.observableArrayList();
            String text = textField.getText().toLowerCase();
            currencies.forEach(currency -> {
                if (Integer.toString(currency.getR030()).startsWith(text) ||
                    currency.getCc().toLowerCase().startsWith(text) ||
                    currency.getTxt().toLowerCase().startsWith(text)) {
                    tempList.add(currency);
                }
            });
            table.setItems(tempList);
    };

    public void initialize() {
        setCellValueFactory();
        textField.setOnKeyReleased(action);
        table.setItems(currencies);
    }

    private void setCellValueFactory() {
        code.setCellValueFactory(new PropertyValueFactory<>("r030"));
        title.setCellValueFactory(new PropertyValueFactory<>("txt"));
        value.setCellValueFactory(new PropertyValueFactory<>("rate"));
        symbolCode.setCellValueFactory(new PropertyValueFactory<>("cc"));
        date.setCellValueFactory(new PropertyValueFactory<>("exchangedate"));
    }
}
