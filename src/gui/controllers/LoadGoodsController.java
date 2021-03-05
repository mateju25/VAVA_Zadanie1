package gui.controllers;

import app.Customer;
import app.Goods;
import app.InvoiceSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoadGoodsController extends SampleController {
    @FXML
    public TableView listOfGoods;

    @FXML
    public Button cancelButton;
    @FXML
    public Button save;
    @FXML
    public TextField name;
    @FXML
    public TextField text;
    @FXML
    public TextField value;
    @FXML
    public Button save1;
    @FXML
    public Label warning;
    @FXML
    public TableColumn tableName;
    @FXML
    public TableColumn tableDescription;
    @FXML
    public TableColumn tableValue;

    private Goods chosedGood = null;

    @FXML
    public void initialize() {
        //super.initialize(listOfGoods);
        TableColumn<Goods, String> column1 = new TableColumn<>("Názov");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Goods, String> column2 = new TableColumn<>("Popis");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Goods, Integer> column3 = new TableColumn<>("Cena");
        column3.setCellValueFactory(new PropertyValueFactory<>("value"));


        listOfGoods.getColumns().add(column1);
        listOfGoods.getColumns().add(column2);
        listOfGoods.getColumns().add(column3);

        column1.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.3));
        column2.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.5));
        column3.prefWidthProperty().bind(listOfGoods.widthProperty().multiply(0.2));

        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);


        warning.setTextFill(Color.color(1, 0, 0));
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void load_Good(ActionEvent actionEvent) {
        chosedGood = (Goods) listOfGoods.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfGoods.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        try {
            int val = Integer.parseInt(value.getText());
            if (name.getText().length() != 0 && text.getText().length() != 0 && value.getText().length()  != 0) {
                Goods temp = new Goods(name.getText(), text.getText(), val);
                localSys.getListOfGoods().add(temp);
                listOfGoods.getItems().clear();
                listOfGoods.getItems().addAll(localSys.getListOfGoods());
            } else {
                warning.setText("Vyplň všetky polia!");
            }
        } catch (NumberFormatException a) {
            warning.setText("Zadal si neplatnú hodnotu v Cena");
        }

    }

    public Goods getChosedGood() {
        return chosedGood;
    }

    public void loadCustomerController(InvoiceSystem sys) {
        localSys = sys;
        listOfGoods.getItems().clear();
        listOfGoods.getItems().addAll(localSys.getListOfGoods());
    }
}
