import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Text area for displaying contents
		TextArea ta = new TextArea();
		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server");

		// Set the stage title
		primaryStage.setScene(scene);

		// Place the scene in the stage
		primaryStage.show();
		// Display the stage
		new Thread(() -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

				// Listen for a connection request
				Socket socket = serverSocket.accept();

				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

				while (true) {
					// Receive URL from the client
					String URL = inputFromClient.readUTF();
					
					boolean validation = URL.equals("http://shakespeare.mit.edu/macbeth/full.html");
					outputToClient.writeBoolean(validation);
					
					Platform.runLater(() -> {
						ta.appendText("URL received from CLIENT: " + URL + '\n');
						ta.appendText("Validation is: " + validation + '\n' + '\n');
					});
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
	public static void main(String args[]) 
    { 
        launch(args);
    }
}