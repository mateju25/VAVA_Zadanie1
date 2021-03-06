package zadanie1.gui.controllers;

import zadanie1.app.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import zadanie1.app.InvoiceSystem;

public class LoadCustomersController extends LoadController{
    @FXML
    private TableView<Customer> tableOfGoods;
    @FXML
    private TableColumn<Customer, String> columnName;
    @FXML
    private TableColumn<Customer, String> columnAddress;
    @FXML
    private TableColumn<Customer, String> columnNumber;
    @FXML
    private TableColumn<Customer, String> columnTown;
    @FXML
    private TableColumn<Customer, String> columnPostalCode;

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        columnTown.setCellValueFactory(new PropertyValueFactory<>("town"));
        columnPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_code"));

        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTown.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPostalCode.setCellFactory(TextFieldTableCell.forTableColumn());

        tableOfGoods.getItems().clear();
        tableOfGoods.getItems().addAll(getGoods());
    }

    @FXML
    public void useItem() {
        chosedItem = tableOfGoods.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) tableOfGoods.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) tableOfGoods.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void columnEditName(TableColumn.CellEditEvent<Customer, String> cellEditEvent) {
        Customer edit = tableOfGoods.getSelectionModel().getSelectedItem();
        edit.setName(cellEditEvent.getNewValue());
    }
    @FXML
    public void columnEditAddress(TableColumn.CellEditEvent<Customer, String> cellEditEvent) {
        Customer edit = tableOfGoods.getSelectionModel().getSelectedItem();
        edit.setAddress(cellEditEvent.getNewValue());
    }

    @FXML
    public void columnEditNumber(TableColumn.CellEditEvent<Customer, String> cellEditEvent) {
        Customer edit = tableOfGoods.getSelectionModel().getSelectedItem();
        edit.setNumber(cellEditEvent.getNewValue());
    }

    @FXML
    public void columnEditTown(TableColumn.CellEditEvent<Customer, String> cellEditEvent) {
        Customer edit = tableOfGoods.getSelectionModel().getSelectedItem();
        edit.setTown(cellEditEvent.getNewValue());
    }

    @FXML
    public void columnEditPostalCode(TableColumn.CellEditEvent<Customer, String> cellEditEvent) {
        Customer edit = tableOfGoods.getSelectionModel().getSelectedItem();
        edit.setPostal_code(cellEditEvent.getNewValue());
    }

    private ObservableList<Customer> getGoods() {
        ObservableList<Customer> items = FXCollections.observableArrayList();
        items.addAll(InvoiceSystem.getInstance().getListOfCustomers());
        return items;
    }

}
