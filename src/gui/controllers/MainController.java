package gui.controllers;

import app.Customer;
import app.Goods;
import app.Invoice;
import app.InvoiceSystem;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class MainController extends SampleController {
    @FXML
    public Label createdLabel;
    @FXML
    private TableView<Invoice.GoodsAndCount> listOfGoods;
    @FXML
    private Label warning;
    @FXML
    private Label totalCount;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField town;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField number;
    @FXML
    private TableColumn<Invoice.GoodsAndCount, String> columnName;
    @FXML
    private TableColumn<Invoice.GoodsAndCount, String> columnDescription;
    @FXML
    private TableColumn<Invoice.GoodsAndCount, Double> columnValue;
    @FXML
    private TableColumn<Invoice.GoodsAndCount, Integer> columnCount;

    @FXML
    private void initialize() {
        localSys = new InvoiceSystem();
        warning.setTextFill(Color.color(1, 0, 0));
        createdLabel.setText(String.valueOf(localSys.getCurrInvoice().getCreated()));
        totalCount.setText(String.valueOf(0));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        columnCount.setResizable(false);

        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(getGoods());
    }

    @FXML
    private void saveCustomer(ActionEvent event)
    {
        if (name.getText().length() != 0 && address.getText().length() != 0 && town.getText().length() != 0 && postal_code.getText().length() != 0 && number.getText().length() != 0) {
            warning.setText("");
            Customer newCustomer = new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText());

            localSys.getListOfCustomers().add(newCustomer);
        } else {
            warning.setText("Vyplň všetky polia!");
        }
    }

    @FXML
    private void loadCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/loadSavedCustomers.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadCustomersController controller = loader.getController();

        Stage window = new Stage();
        window.setResizable(false);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Customer currCust = controller.getItem();
        if (currCust != null) {
            name.setText(currCust.getName());
            address.setText(currCust.getAddress());
            number.setText(currCust.getNumber());
            town.setText(currCust.getTown());
            postal_code.setText(currCust.getPostal_code());
        }
    }

    @FXML
    public void addGood(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/loadSavedGoods.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadGoodsController controller = loader.getController();

        Stage window = new Stage();
        window.setResizable(false);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Goods currGood = controller.getItem();
        if (currGood != null) {
            localSys.getCurrInvoice().addGood(currGood);
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(getGoods());
            totalCount.setText(String.valueOf(localSys.getCurrInvoice().getTotalValue()));
        }
    }


    @FXML
    public void deleteGood(ActionEvent actionEvent) {
        localSys.getCurrInvoice().deleteGood((Invoice.GoodsAndCount) listOfGoods.getSelectionModel().getSelectedItem());
        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(localSys.getCurrInvoice().getListOfGoods());
        totalCount.setText(String.valueOf(localSys.getCurrInvoice().getTotalValue()));
    }

    @FXML
    public void loadInvoice(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/loadSavedInvoices.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadInvoiceController controller = loader.getController();

        Stage window = new Stage();
        window.setResizable(false);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Invoice currInvoice = controller.getItem();
        if (currInvoice != null) {
            localSys.setNewInvoice();

            name.setText(currInvoice.getCustomer().getName());
            address.setText(currInvoice.getCustomer().getAddress());
            number.setText(currInvoice.getCustomer().getNumber());
            town.setText(currInvoice.getCustomer().getTown());
            postal_code.setText(currInvoice.getCustomer().getPostal_code());

            ArrayList<Invoice.GoodsAndCount> newList = currInvoice.duplicateGoods();
            localSys.getCurrInvoice().setListOfGoods(newList);
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(getGoods());
            totalCount.setText(String.valueOf(currInvoice.getTotalValue()));
            createdLabel.setText(String.valueOf(currInvoice.getCreated()));
        }
    }

    @FXML
    public void saveInvoice(ActionEvent actionEvent) throws IOException {
        if (name.getText().length() != 0 && address.getText().length() != 0 && town.getText().length() != 0 && postal_code.getText().length() != 0 && number.getText().length() != 0) {
            warning.setText("");
            Customer newCustomer = new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText());
            localSys.getCurrInvoice().setCustomer(newCustomer);

            ArrayList<Invoice.GoodsAndCount> newList = localSys.getCurrInvoice().duplicateGoods();
            localSys.getCurrInvoice().setListOfGoods(newList);

            String saveAsName = saveAs("Zadaj meno pre uloženie faktúry:");
            if (saveAsName != null) {
                localSys.getCurrInvoice().setInfo(saveAsName);
            } else {
                localSys.getCurrInvoice().setInfo(localSys.getCurrInvoice().getCustomer().getName());
            }

            localSys.getListOfInvoice().add(localSys.getCurrInvoice());

            localSys.setNewInvoice();
            name.setText("");
            address.setText("");
            number.setText("");
            town.setText("");
            postal_code.setText("");

            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(getGoods());
        } else {
            warning.setText("Vyplň všetky polia!");
        }
    }


    private ObservableList<Invoice.GoodsAndCount> getGoods() {
        ObservableList<Invoice.GoodsAndCount> items = FXCollections.observableArrayList();
        items.addAll(localSys.getCurrInvoice().getListOfGoods());
        return items;
    }

}
