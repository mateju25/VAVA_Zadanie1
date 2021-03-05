package gui.controllers;

import app.Customer;
import app.Goods;
import app.InvoiceSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoadGoodsController extends SampleController {
    @FXML
    public ListView listOfGoods;
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

    private Goods chosedGood = null;

    @FXML
    public void initialize() {
        super.initialize(listOfGoods);
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
