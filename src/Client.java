import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	// IO streams
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;

	@Override // Override the start method in the Application class

	public void start(Stage primaryStage) {
		// Panel p to hold the label and text field
		BorderPane paneForTextField = new BorderPane();
		paneForTextField.setPadding(new Insets(5, 5, 5, 5));
		paneForTextField.setStyle("-fx-border-color: green");
		paneForTextField.setLeft(new Label("Enter a valid URL: "));

		TextField tf = new TextField();
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		paneForTextField.setCenter(tf);

		BorderPane mainPane = new BorderPane();
		// Text area to display contents
		TextArea ta = new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(paneForTextField);

		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane, 450, 200);
		primaryStage.setTitle("Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		tf.setOnAction(e -> {
			try {
				// Get the URL from the text field
				String URL = tf.getText().trim();

				// Send the URL to the server
				toServer.writeUTF(URL);
				toServer.flush();

				// Get URL from the server
				boolean validation = fromServer.readBoolean();

				// Display to the text area
				if (validation == true) {
					ta.appendText("URL is \"" + URL + "\"\n");
					ta.appendText("Validation received from the SERVER is " + validation + '\n' + '\n');
					ta.appendText("Performing View.launch(args)" + '\n' + '\n');
					
				}
				else {
					ta.appendText("URL is \"" + URL + "\"\n");
					ta.appendText("Validation received from the SERVER is " + validation + '\n' + '\n');
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		});

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);
			// Socket socket = new Socket("130.254.204.36", 8000);
			// Socket socket = new Socket("drake.Armstrong.edu", 8000);

			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());

			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			ta.appendText(ex.toString() + '\n');
		}
	}

	public static void main(String args[]) {
		launch(args);
	}
}