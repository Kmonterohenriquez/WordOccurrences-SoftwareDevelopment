import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Kevin Montero
 * 
 *         Sets the stage in pop-up window
 * @param View
 * @see Main
 */
public class View extends Application {

	private Stage window;
	private TableView<Words> table;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * 
	 * Fills the View with a table populated with words and their counts
	 * 
	 * @param primaryState
	 * 
	 * @see Words
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Frequent Words from HTML Text");

		TableColumn<Words, String> wordColumn = new TableColumn<>("Word");
		wordColumn.setMinWidth(200);
		wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

		TableColumn<Words, Integer> countColumn = new TableColumn<>("Counts");
		countColumn.setMinWidth(100);
		countColumn.setCellValueFactory(new PropertyValueFactory<>("counts"));

		table = new TableView<>();
		table.setItems(Words.getWords("http://shakespeare.mit.edu/macbeth/full.html"));
		table.getColumns().addAll(wordColumn, countColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
}
