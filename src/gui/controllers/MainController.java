package gui.controllers;

import app.Customer;
import app.Goods;
import app.Invoice;
import app.InvoiceSystem;
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

public class MainController extends SampleController {
    @FXML
    public TableView listOfGoods;
    @FXML
    public Label warning;
    @FXML
    public Label totalCount;
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
    private void initialize() {
        localSys = new InvoiceSystem();
        warning.setTextFill(Color.color(1, 0, 0));
        totalCount.setText(String.valueOf(0));

        TableColumn<Invoice.GoodsAndCount, String> column1 = new TableColumn<>("Názov");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Invoice.GoodsAndCount, String> column2 = new TableColumn<>("Popis");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Invoice.GoodsAndCount, Integer> column3 = new TableColumn<>("Cena");
        column3.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Invoice.GoodsAndCount, Integer> column4 = new TableColumn<>("Počet");
        column4.setCellValueFactory(new PropertyValueFactory<>("count"));


        listOfGoods.getColumns().add(column1);
        listOfGoods.getColumns().add(column2);
        listOfGoods.getColumns().add(column3);
        listOfGoods.getColumns().add(column4);

        column1.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.3));
        column2.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.5));
        column3.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.1));
        column4.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.1));

        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);
        column4.setResizable(false);

        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(localSys.getListOfGoods());
    }

    @FXML
    private void save_customer(ActionEvent event)
    {
        int temp = 0;
        try {
            temp = Integer.parseInt(number.getText());
            temp = 1;
            temp = Integer.parseInt(postal_code.getText());

            if (name.getText().length() != 0 && address.getText().length() != 0 && town.getText().length() != 0 && postal_code.getText().length() != 0 && number.getText().length() != 0) {
                warning.setText("");
                Customer newCustomer = new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText());

                String saveAsName = saveAs("Zadaj meno pre uloženie zákazníka:");
                if (saveAsName != null) {
                    newCustomer.setInfo(saveAsName);
                } else {
                    newCustomer.setInfo(newCustomer.getName());
                }

                localSys.getListOfCustomers().add(newCustomer);
                localSys.getCurrInvoice().setCustomer(newCustomer);
            } else {
                warning.setText("Vyplň všetky polia!");
            }
        } catch (NumberFormatException a) {
            if (temp == 0)
                warning.setText("Zadal si neplatnú hodnotu v Číslo");
            else
                warning.setText("Zadal si neplatnú hodnotu v PSČ");
        }
    }

    @FXML
    private void load_customer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/loadSaveAsItems.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadSaveAsItemsController controller = loader.getController();
        controller.loadCustomerController(localSys, Customer.class);

        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Customer currCust = (Customer) controller.getChosedItem();
        if (currCust != null) {
            name.setText(currCust.getName());
            address.setText(currCust.getAddress());
            number.setText(currCust.getNumber());
            town.setText(currCust.getTown());
            postal_code.setText(currCust.getPostal_code());
            localSys.getCurrInvoice().setCustomer(currCust);
        }
    }

    @FXML
    public void add_good(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/loadSavedGoods.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadGoodsController controller = loader.getController();
        controller.loadCustomerController(localSys);

        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Goods currGood = controller.getChosedGood();
        if (currGood != null) {
            localSys.getCurrInvoice().addGood(currGood);
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(localSys.getCurrInvoice().getListOfGoods());
            totalCount.setText(String.valueOf(localSys.getCurrInvoice().getTotalValue()));
        }
    }

    @FXML
    public void saveInvoice(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        if (getClass().getResource("../resources/loadSaveAsItems.fxml") == null)
            loader.setLocation(getClass().getResource("loadSaveAsItems.fxml"));
        else
            loader.setLocation(getClass().getResource("../resources/loadSaveAsItems.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        LoadSaveAsItemsController controller = loader.getController();
        controller.loadCustomerController(localSys, Invoice.class);

        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/Ikona.png")));
        window.setScene(scene);
        window.showAndWait();

        Invoice currInvoice = (Invoice) controller.getChosedItem();
        if (currInvoice != null) {
            try {
                currInvoice = (Invoice) currInvoice.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            localSys.setCurrInvoice(currInvoice);
            name.setText(currInvoice.getCustomer().getName());
            address.setText(currInvoice.getCustomer().getAddress());
            number.setText(currInvoice.getCustomer().getNumber());
            town.setText(currInvoice.getCustomer().getTown());
            postal_code.setText(currInvoice.getCustomer().getPostal_code());
            listOfGoods.getItems().clear();
            listOfGoods.getItems().addAll(localSys.getCurrInvoice().getListOfGoods());
            totalCount.setText(String.valueOf(localSys.getCurrInvoice().getTotalValue()));
        }
        localSys.setNewInvoice();
    }

    @FXML
    public void delete_good(ActionEvent actionEvent) {
        localSys.getCurrInvoice().deleteGood((Invoice.GoodsAndCount) listOfGoods.getSelectionModel().getSelectedItem());
        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(localSys.getCurrInvoice().getListOfGoods());
        totalCount.setText(String.valueOf(localSys.getCurrInvoice().getTotalValue()));
    }

    @FXML
    public void add_invoice(ActionEvent actionEvent) throws IOException {
        int temp = 0;
        try {
            temp = Integer.parseInt(number.getText());
            temp = 1;
            temp = Integer.parseInt(postal_code.getText());

            if (name.getText().length() != 0 && address.getText().length() != 0 && town.getText().length() != 0 && postal_code.getText().length() != 0 && number.getText().length() != 0) {
                warning.setText("");
                Customer newCustomer = new Customer(name.getText(), address.getText(), number.getText(), town.getText(), postal_code.getText());
                localSys.getCurrInvoice().setCustomer(newCustomer);

                String saveAsName = saveAs("Zadaj meno pre uloženie faktúry:");
                if (saveAsName != null) {
                    localSys.getCurrInvoice().setInfo(saveAsName);
                } else {
                    localSys.getCurrInvoice().setInfo(localSys.getCurrInvoice().getCustomer().getName());
                }

                localSys.getListOfInvoice().add(localSys.getCurrInvoice());
            } else {
                warning.setText("Vyplň všetky polia!");
            }
        } catch (NumberFormatException a) {
            if (temp == 0)
                warning.setText("Zadal si neplatnú hodnotu v Číslo");
            else
                warning.setText("Zadal si neplatnú hodnotu v PSČ");
        }
    }
}
