package inventory.multimedia.video;

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

public class ArchiveFootage extends Video {
    private boolean regionalHistory;
    private boolean propaganda;

    public ArchiveFootage() {
        this(false, false);
        setInternalID(16);
        setType(getClass().getSimpleName());
    }

    public ArchiveFootage(boolean regionalHistory, boolean propaganda) {
        this.regionalHistory = regionalHistory;
        this.propaganda = propaganda;
        setInternalID(16);
        setType(getClass().getSimpleName());
    }

    public ArchiveFootage(String title, String publisher, String subDefine, String director, String screenwriter, boolean regionalHistory, boolean propaganda) {
        super(title, publisher, subDefine, director, screenwriter);
        this.regionalHistory = regionalHistory;
        this.propaganda = propaganda;
        setInternalID(16);
        setType(getClass().getSimpleName());
    }

    public boolean isRegionalHistory() {
        return regionalHistory;
    }

    public void setRegionalHistory(boolean regionalHistory) {
        this.regionalHistory = regionalHistory;
    }

    public boolean isPropaganda() {
        return propaganda;
    }

    public void setPropaganda(boolean propaganda) {
        this.propaganda = propaganda;
    }

    @Override
    public void addToDatabase() {
        LibraryDatabase.addArchiveFootage(this);
        LibraryDatabase.addVideo(this);
        LibraryDatabase.addInventory(this);
    }

    @Override
    public String toString() {
        return "ArchiveFootage{" +
                "regionalHistory=" + regionalHistory +
                ", propaganda=" + propaganda +
                '}';
    }

    public StringBuilder returnFinalInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.returnFinalInfo())
                .append("Regional History: ")
                .append(isRegionalHistory())
                .append("\r\n")
                .append("Propaganda material: ")
                .append(isPropaganda())
                .append("\r\n");
        return sb;
    }

    @Override
    public StringBuilder returnRawInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.returnRawInfo())
                .append(isRegionalHistory())
                .append("\r\n")
                .append(isPropaganda())
                .append("\r\n");
        return sb;
    }

    public static void displayInventory() {

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
        TableColumn<ArchiveFootage, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //Type column
        TableColumn<ArchiveFootage, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //Status column
        TableColumn<ArchiveFootage, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        //Publisher column
        TableColumn<ArchiveFootage, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setMinWidth(100);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        //SubDef column
        TableColumn<ArchiveFootage, String> subDefineColumn = new TableColumn<>("Genre");
        subDefineColumn.setMinWidth(100);
        subDefineColumn.setCellValueFactory(new PropertyValueFactory<>("subDefine"));

        //Director column
        TableColumn<ArchiveFootage, String> directorColumn = new TableColumn<>("Director");
        directorColumn.setMinWidth(100);
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));

        //Screenwriter column
        TableColumn<ArchiveFootage, String> screenWriterColumn = new TableColumn<>("Screenwriter");
        screenWriterColumn.setMinWidth(100);
        screenWriterColumn.setCellValueFactory(new PropertyValueFactory<>("screenwriter"));

        //Regional history column
        TableColumn<ArchiveFootage, Boolean> regionalHistoryColumn = new TableColumn<>("Regional History");
        regionalHistoryColumn.setMinWidth(100);
        regionalHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("regionalHistory"));

        //Propaganda column
        TableColumn<ArchiveFootage, Boolean> propagandaColumn = new TableColumn<>("Propaganda Material");
        propagandaColumn.setMinWidth(100);
        propagandaColumn.setCellValueFactory(new PropertyValueFactory<>("propaganda"));

        // Button action
        close.setOnAction(event -> primaryStage.close());

        TableView<ArchiveFootage> table = new TableView<>();
        table.setItems(getInventory());
        table.getColumns().addAll(statusColumn, typeColumn, titleColumn, publisherColumn, directorColumn, screenWriterColumn,
                regionalHistoryColumn, propagandaColumn);

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
    private static ObservableList<ArchiveFootage> getInventory() {
        ObservableList<ArchiveFootage> inventory = FXCollections.observableArrayList();

        inventory.addAll(LibraryDatabase.getArchiveFootageList());

        return inventory;
    }
}


