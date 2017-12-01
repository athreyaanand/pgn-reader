import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ChessGui extends Application {
    private TextField filterField;

    @Override
    public void start(Stage stage) {
        ChessDb chessDb = new ChessDb();
        filterField = new TextField();
        filterField.setInputPrompt("Search");
        ObservableList<ChessGame> games =
            FXCollections.observableArrayList(chessDb.getGames());
        TableView<ChessGame> table = createTable(games);

        Button viewButton = new Button("View Game");
        viewButton.setOnAction(e -> {
                ChessGame game = table.getSelectionModel().getSelectedItem();
                viewDialog(game);
            });
        viewButton.disableProperty()
            .bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(viewButton, dismissButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(filterField, table, buttonBox);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Chess DB GUI");
        stage.show();
    }

    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {
        TableView<ChessGame> table = new TableView<ChessGame>();
        table.setItems(games);

        TableColumn<ChessGame, String> eventCol =
            new TableColumn<ChessGame, String>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));

        TableColumn<ChessGame, String> siteCol =
            new TableColumn<ChessGame, String>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));

        TableColumn<ChessGame, String> dateCol =
            new TableColumn<ChessGame, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<ChessGame, String> whiteCol =
            new TableColumn<ChessGame, String>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));

        TableColumn<ChessGame, String> blackCol =
            new TableColumn<ChessGame, String>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));

        TableColumn<ChessGame, String> resultCol =
            new TableColumn<ChessGame, String>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));

        TableColumn<ChessGame, String> openingCol =
            new TableColumn<ChessGame, String>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("opening"));

        table.getColumns().setAll(eventCol, siteCol, dateCol,
                                  whiteCol, blackCol, resultCol, openingCol);

        FilteredList<ChessGame> filteredData = new FilteredList<>(games, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ChessGame -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String value = newValue.toLowerCase();

                if (ChessGame.getEvent().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getSite().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getDate().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getWhite().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getBlack().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getResult().toLowerCase().contains(value)) {
                    return true;
                } else if (ChessGame.getOpening().toLowerCase().contains(value)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<ChessGame> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

        return table;
    }

    private void viewDialog(ChessGame cg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(cg.getEvent());
        alert.setHeaderText(String.format("Site: %s%nDate: %s%nWhite: %s%nBlack: %s%nResult: %s%nOpening: %s",
                                          cg.getSite(), cg.getDate(),
                                          cg.getWhite(), cg.getBlack(),
                                          cg.getResult(), cg.getOpening()));
        alert.setContentText(cg.getMoves());
        alert.showAndWait();
    }
}
