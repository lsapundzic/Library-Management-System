package inventory.multimedia.audio;

import inventory.LibraryDatabase;
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

public class Podcast extends Audio {
    private boolean availableOffline;
    private boolean featuredOnItunes;

    public Podcast() {
        this(false, false);
        setInternalID(18);
        setType(getClass().getSimpleName());
    }

    public Podcast(boolean availableOffline, boolean featuredOnItunes) {
        this.availableOffline = availableOffline;
        this.featuredOnItunes = featuredOnItunes;
        setInternalID(18);
        setType(getClass().getSimpleName());
    }

    public Podcast(String title, String publisher, String subDefine, String format, boolean availableOffline, boolean featuredOnItunes) {
        super(title, publisher, subDefine, format);
        this.availableOffline = availableOffline;
        this.featuredOnItunes = featuredOnItunes;
        setInternalID(18);
        setType(getClass().getSimpleName());
    }

    public boolean isAvailableOffline() {
        return availableOffline;
    }

    public void setAvailableOffline(boolean availableOffline) {
        this.availableOffline = availableOffline;
    }

    public boolean isFeaturedOnItunes() {
        return featuredOnItunes;
    }

    public void setFeaturedOnItunes(boolean featuredOnItunes) {
        this.featuredOnItunes = featuredOnItunes;
    }

    @Override
    public void addToDatabase() {
        LibraryDatabase.addPodcast(this);
        LibraryDatabase.addAudio(this);
        LibraryDatabase.addInventory(this);
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "availableOffline=" + availableOffline +
                ", featuredOnItunes=" + featuredOnItunes +
                '}';
    }

    @Override
    public StringBuilder returnFinalInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.returnFinalInfo())
                .append("Available Offline: ")
                .append(isAvailableOffline())
                .append("\r\n")
                .append("Featured on Itunes: ")
                .append(isFeaturedOnItunes())
                .append("\r\n");

        return sb;
    }

    @Override
    public StringBuilder returnRawInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.returnRawInfo())
                .append(isAvailableOffline())
                .append("\r\n")
                .append(isFeaturedOnItunes())
                .append("\r\n");

        return sb;
    }

    public static void displayPodcastInventory() {

        // Set up the primary stage
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Podcast Inventory");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        // Initiate button
        Button close = new Button("Close");

        // Set button size
        close.setMinSize(90, 30);
        close.setMaxSize(30, 10);

        //Title column
        TableColumn<Podcast, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //Type column
        TableColumn<Podcast, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //Status column
        TableColumn<Podcast, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        //Publisher column
        TableColumn<Podcast, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setMinWidth(100);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        //SubDef column
        TableColumn<Podcast, String> subDefineColumn = new TableColumn<>("Genre");
        subDefineColumn.setMinWidth(100);
        subDefineColumn.setCellValueFactory(new PropertyValueFactory<>("subDefine"));

        //Format column
        TableColumn<Podcast, String> formatColumn = new TableColumn<>("Format");
        formatColumn.setMinWidth(100);
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));

        //Available offline column
        TableColumn<Podcast, Boolean> availableOffline = new TableColumn<>("Available Offline");
        availableOffline.setMinWidth(100);
        availableOffline.setCellValueFactory(new PropertyValueFactory<>("availableOffline"));

        //Featured column
        TableColumn<Podcast, Boolean> featuredColumn = new TableColumn<>("Featured on Itunes");
        featuredColumn.setMinWidth(100);
        featuredColumn.setCellValueFactory(new PropertyValueFactory<>("featuredOnItunes"));

        // Button action
        close.setOnAction(event -> primaryStage.close());

        TableView<Podcast> table = new TableView<>();
        table.setItems(getInventory());
        table.getColumns().addAll(statusColumn, typeColumn, titleColumn, publisherColumn, subDefineColumn, formatColumn,
                availableOffline, featuredColumn);

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
    private static ObservableList<Podcast> getInventory() {
        ObservableList<Podcast> inventory = FXCollections.observableArrayList();

        inventory.addAll(LibraryDatabase.getPodcastList());

        return inventory;
    }
}
