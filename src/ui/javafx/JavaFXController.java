package ui.javafx;

import be.Exit;
import be.Item;
import bll.GameController;
import enums.Command;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXController extends GameController implements Initializable {  //needs refactoring :)
    //IDEA: txf view right click menu (observe/drop/consume etc. - requires more access to data?)
    @FXML
    private TextArea txaConsole;
    @FXML
    private TextField txfInput;
    @FXML
    private ListView<String> lsvInventory;
    @FXML
    private Label lblAreaName, lblInventory, lblDescription;
    @FXML
    private ListView<String> lsvAreaItems;
    @FXML
    ListView<String> lsvExits;

    @FXML
    private void enterInput(KeyEvent key)
    {
        if (key.getCode() == KeyCode.ENTER)
        {
            sendInput();
        }
    }

    private void sendInput()
    {
        execute();
        txfInput.setText("");
        updateGUI();
    }

    private void updateGUI()
    {
        lblAreaName.setText("Area: " + dataAccess.areaName());
        lblDescription.setText("Description: " + dataAccess.areaDescription());

        lblInventory.setText("Inventory: (" + dataAccess.inventoryWeight() + "kg/" + dataAccess.inventoryCapacity() + "kg)");

        lsvExits.setItems(FXCollections.observableList(dataAccess.exits().stream().map(Exit::direction).toList()));
        lsvAreaItems.setItems(FXCollections.observableList(dataAccess.areaItems().stream().map(Item::getName).toList()));
        lsvInventory.setItems(FXCollections.observableList(dataAccess.inventory().stream().map(Item::getName).toList()));
    }

    @Override
    public void displayException(String error) {
        txaConsole.setText(error.toString());
    }

    @Override
    public String pullInput() {
        return txfInput.getText();
    }

    @Override
    public void pushOutput(String output) {
        txaConsole.setText(output);
    }

    @Override
    public void exitEnvironment() {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lsvExits.setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem go = new MenuItem();
            go.textProperty().bind(Bindings.format(Command.GO + " \"%s\"", cell.itemProperty()));
            go.setOnAction(event -> {
                String item = cell.getItem();
                txfInput.setText(Command.GO + " " + item);
                sendInput();
            });
            contextMenu.getItems().addAll(go);

            cell.textProperty().bind(cell.itemProperty());

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });

        lsvAreaItems.setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem observeItem = new MenuItem();
            observeItem.textProperty().bind(Bindings.format(Command.OBSERVE + " \"%s\"", cell.itemProperty()));
            observeItem.setOnAction(event -> {
                String item = cell.getItem();
                txfInput.setText(Command.OBSERVE + " " + item);
                sendInput();
            });
            MenuItem takeItem = new MenuItem();
            takeItem.textProperty().bind(Bindings.format(Command.TAKE + " \"%s\"", cell.itemProperty()));
            takeItem.setOnAction(event -> {
                String item = cell.getItem();
                txfInput.setText(Command.TAKE + " " + item);
                sendInput();
            });
            contextMenu.getItems().addAll(observeItem, takeItem);

            cell.textProperty().bind(cell.itemProperty());

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });
        lsvInventory.setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem observeItem = new MenuItem();
            observeItem.textProperty().bind(Bindings.format(Command.OBSERVE + " \"%s\"", cell.itemProperty()));
            observeItem.setOnAction(event -> {
                String item = cell.getItem();
                txfInput.setText(Command.OBSERVE + " " + item);
                sendInput();
            });
            MenuItem dropItem = new MenuItem();
            dropItem.textProperty().bind(Bindings.format(Command.DROP + " \"%s\"", cell.itemProperty()));
            dropItem.setOnAction(event -> {
                String item = cell.getItem();
                txfInput.setText(Command.DROP + " " + item);
                sendInput();
            });
            contextMenu.getItems().addAll(observeItem, dropItem);

            cell.textProperty().bind(cell.itemProperty());

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });

        newGame();
        updateGUI();
    }
}
