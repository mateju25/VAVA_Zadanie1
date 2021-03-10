package zadanie1.gui.controllers;

import javafx.stage.Modality;
import zadanie1.app.Customer;
import zadanie1.app.Goods;
import zadanie1.app.Invoice;
import zadanie1.app.InvoiceSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainController {
    @FXML private  DatePicker calendar;
    @FXML private TableView<Invoice.GoodsAndCount> listOfGoods;
    @FXML private Label warningCustomer;
    @FXML private Label warningGoods;
    @FXML private Label warningDate;
    @FXML private Label totalCount;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField town;
    @FXML private TextField postal_code;
    @FXML private TextField number;
    @FXML private TableColumn<Invoice.GoodsAndCount, String> columnName;
    @FXML private TableColumn<Invoice.GoodsAndCount, String> columnDescription;
    @FXML private TableColumn<Invoice.GoodsAndCount, Double> columnValue;
    @FXML private TableColumn<Invoice.GoodsAndCount, Integer> columnCount;

    @FXML
    private void initialize() {
        cleanAndRefreshPage();
        totalCount.setText(String.valueOf(0));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    @FXML
    private void saveCustomer(ActionEvent event)
    {
        if (name.getText().length() != 0 && address.getText().length() != 0 && town.getText().length() != 0 && postal_code.getText().length() != 0 && number.getText().length() != 0) {
            warningCustomer.setText("");
            InvoiceSystem.getInstance().getListOfCustomers().add(new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText()));
        } else {
            warningCustomer.setText("Vyplň všetky polia!");
        }
    }

    @FXML
    private void loadCustomer(ActionEvent event) throws IOException {
        Customer currCust = (Customer) newWindow("/zadanie1/gui/resources/loadSavedCustomers.fxml").getItem();
        if (currCust != null) {
            setInfoAboutCustomer(currCust);
        }
    }

    @FXML
    public void addGood() throws IOException {
        Goods currGood = (Goods) newWindow("/zadanie1/gui/resources/loadSavedGoods.fxml").getItem();
        if (currGood != null) {
            InvoiceSystem.getInstance().getCurrInvoice().addGood(currGood);
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(getGoods());
            totalCount.setText(String.valueOf(InvoiceSystem.getInstance().getCurrInvoice().getTotalValue()));
        }
    }


    @FXML
    public void deleteGood() {
        InvoiceSystem.getInstance().getCurrInvoice().deleteGood(listOfGoods.getSelectionModel().getSelectedItem());
        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(InvoiceSystem.getInstance().getCurrInvoice().getListOfGoods());
        totalCount.setText(String.valueOf(InvoiceSystem.getInstance().getCurrInvoice().getTotalValue()));
    }

    @FXML
    public void loadInvoice() throws IOException {
        Invoice currInvoice = (Invoice) newWindow("/zadanie1/gui/resources/loadSavedInvoices.fxml").getItem();
        if (currInvoice != null) {
            InvoiceSystem.getInstance().setNewInvoice();

            setInfoAboutCustomer(currInvoice.getCustomer());

            ArrayList<Invoice.GoodsAndCount> newList = currInvoice.duplicateGoods();
            InvoiceSystem.getInstance().getCurrInvoice().setListOfGoods(newList);
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(getGoods());
            totalCount.setText(String.valueOf(currInvoice.getTotalValue()));
            calendar.setAccessibleText(currInvoice.getCreated());
        }
    }

    @FXML
    public void saveInvoice() {
        if (name.getText().length() == 0 || address.getText().length() == 0 || town.getText().length() == 0 || postal_code.getText().length() == 0 || number.getText().length() == 0) {
            warningCustomer.setText("Vyplň všetky polia!");
            return;
        }

        if (listOfGoods.getItems().size() == 0) {
            warningCustomer.setText("");
            warningGoods.setText("Pridaj nejaký tovar!");
            return;
        }

        if (calendar.getValue() == null) {
            warningCustomer.setText("");
            warningGoods.setText("");
            warningDate.setText("Zadaj dátum vytvorenia faktúry!");
            return;
        }

        warningCustomer.setText("");
        warningGoods.setText("");
        warningDate.setText("");
        Customer newCustomer = new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText());
        InvoiceSystem.getInstance().getCurrInvoice().setCustomer(newCustomer);

        ArrayList<Invoice.GoodsAndCount> newList = InvoiceSystem.getInstance().getCurrInvoice().duplicateGoods();
        InvoiceSystem.getInstance().getCurrInvoice().setListOfGoods(newList);

        String saveAsName = saveAs("Zadaj meno pre uloženie faktúry:");
        if (saveAsName != null) {
            InvoiceSystem.getInstance().getCurrInvoice().setInfo(saveAsName);
        } else {
            InvoiceSystem.getInstance().getCurrInvoice().setInfo(InvoiceSystem.getInstance().getCurrInvoice().getCustomer().getName());
        }
        InvoiceSystem.getInstance().getCurrInvoice().setCreated(calendar.getAccessibleText());
        InvoiceSystem.getInstance().getListOfInvoice().add(InvoiceSystem.getInstance().getCurrInvoice());

        cleanAndRefreshPage();
    }


    private ObservableList<Invoice.GoodsAndCount> getGoods() {
        ObservableList<Invoice.GoodsAndCount> items = FXCollections.observableArrayList();
        items.addAll(InvoiceSystem.getInstance().getCurrInvoice().getListOfGoods());
        return items;
    }

    private static String saveAs(String message) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ulož ako...");
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get().length() != 0)
                return result.get();
        }
        return null;
    }

    private LoadController newWindow(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadController controller = loader.getController();

        Stage window = new Stage();
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);
        window.getIcons().add(new Image(getClass().getResourceAsStream("/zadanie1/gui/resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();
        return controller;
    }


    private void cleanAndRefreshPage() {
        InvoiceSystem.getInstance().setNewInvoice();
        name.setText("");
        address.setText("");
        number.setText("");
        town.setText("");
        postal_code.setText("");

        totalCount.setText("");
        calendar.setValue(null);

        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(getGoods());
    }

    private void setInfoAboutCustomer(Customer customer) {
        name.setText(customer.getName());
        address.setText(customer.getAddress());
        number.setText(customer.getNumber());
        town.setText(customer.getTown());
        postal_code.setText(customer.getPostal_code());
    }

}
