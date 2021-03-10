package zadanie1.gui.controllers;

import zadanie1.app.Goods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import zadanie1.app.InvoiceSystem;


public class LoadGoodsController extends LoadController{
    @FXML
    private TableView<Goods> tableOfGoods;
    @FXML
    private TextField name;
    @FXML
    private TextField text;
    @FXML
    private TextField value;
    @FXML
    private Label warning;
    @FXML
    private TableColumn<Goods, String> columnName;
    @FXML
    private TableColumn<Goods, String> columnDescription;
    @FXML
    private TableColumn<Goods, Double> columnValue;

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        columnValue.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                try {
                    return String.valueOf(object);
                } catch (NumberFormatException a) {
                    return "";
                }
            }

            @Override
            public Double fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException a) {
                    Goods item = tableOfGoods.getSelectionModel().getSelectedItem();
                    return item.getValue();
                }
            }
        }));
        tableOfGoods.getItems().clear();
        tableOfGoods.getItems().addAll(getGoods());
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) tableOfGoods.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void useItem(ActionEvent actionEvent) {
        chosedItem = tableOfGoods.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) tableOfGoods.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addItem(ActionEvent actionEvent) {
        try {
            double val = Double.parseDouble(value.getText());
            if (name.getText().length() != 0 && text.getText().length() != 0 && value.getText().length()  != 0) {
                warning.setText("");
                Goods temp = new Goods(name.getText(), text.getText(), val);
                InvoiceSystem.getInstance().getListOfGoods().add(temp);
                tableOfGoods.getItems().clear();
                tableOfGoods.getItems().addAll(getGoods());
            } else {
                warning.setText("Vyplň všetky polia!");
            }
        } catch (NumberFormatException a) {
            warning.setText("Zadal si neplatnú hodnotu v Cena");
        }

    }

    @FXML
    public void columnEditName(TableColumn.CellEditEvent<Goods, String> cellEditEvent) {
        Goods editGood = tableOfGoods.getSelectionModel().getSelectedItem();
        editGood.setName(cellEditEvent.getNewValue());
    }

    @FXML
    public void columnEditDescription(TableColumn.CellEditEvent<Goods, String> cellEditEvent) {
        Goods editGood = tableOfGoods.getSelectionModel().getSelectedItem();
        editGood.setDescription(cellEditEvent.getNewValue());
    }

    @FXML
    public void columnEditValue(TableColumn.CellEditEvent<Goods, Double> cellEditEvent) {
        Goods editGood = tableOfGoods.getSelectionModel().getSelectedItem();
        editGood.setValue(cellEditEvent.getNewValue());
    }

    private ObservableList<Goods> getGoods() {
        ObservableList<Goods> items = FXCollections.observableArrayList();
        items.addAll(InvoiceSystem.getInstance().getListOfGoods());
        return items;
    }
}
