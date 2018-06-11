package gui.inventoryView;

import inventory.LibraryDatabase;
import inventory.print.periodical.Comic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ComicView {

    public static void viewInventory() {

        // Set up the primary stage
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Full Library Inventory");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        // Initiate button
        Button close = new Button("Close");

        // Set button size
        close.setMinSize(90, 30);
        close.setMaxSize(30, 10);

        //Title column
        TableColumn<Comic, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //Type column
        TableColumn<Comic, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //Status column
        TableColumn<Comic, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        //Author column
        TableColumn<Comic, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        //SubType column
        TableColumn<Comic, String> subTypeColumn = new TableColumn<>("Genre");
        subTypeColumn.setMinWidth(100);
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));

        //Publication column
        TableColumn<Comic, String> publicationColumn = new TableColumn<>("Publication Frequency");
        publicationColumn.setMinWidth(100);
        publicationColumn.setCellValueFactory(new PropertyValueFactory<>("publicationFrequency"));

        //Manga column
        TableColumn<Comic, Boolean> mangaColumn = new TableColumn<>("Manga");
        mangaColumn.setMinWidth(100);
        mangaColumn.setCellValueFactory(new PropertyValueFactory<>("manga"));

        //Graphic novel column
        TableColumn<Comic, String> graphicNovelColumn = new TableColumn<>("Graphic Novel");
        graphicNovelColumn.setMinWidth(100);
        graphicNovelColumn.setCellValueFactory(new PropertyValueFactory<>("graphicNovel"));

        // Button action
        close.setOnAction(event -> primaryStage.close());

        TableView<Comic> table = new TableView<>();
        table.setItems(getInventory());
        table.getColumns().addAll(statusColumn, typeColumn, titleColumn, authorColumn, subTypeColumn, publicationColumn,
                mangaColumn, graphicNovelColumn);

        // VBox for holding button controls
        VBox controls = new VBox(15);
        controls.setPadding(new Insets(10));
        controls.getChildren().addAll(table, close);

        // Setting up the scene
        Scene scene = new Scene(controls, 530, 480);

        // Setting up the stage
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    //Get all of the inventory
    public static ObservableList<Comic> getInventory() {
        ObservableList<Comic> inventory = FXCollections.observableArrayList();

        inventory.addAll(LibraryDatabase.getComicList());

        return inventory;
    }
}
