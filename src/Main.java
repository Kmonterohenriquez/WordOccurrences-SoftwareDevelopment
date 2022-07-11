/**
 * @author Kevin Montero
 * 
 */
public class Main extends View {

	/**
	 * Launches a pop-up window from the View class
	 * 
	 * @param args
	 * @throws Exception
	 * @see View
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * Connects to MySQL database with localhost
		 * 
		 * @param getConnection()
		 * @see SQLWordConnector
		 */
		SQLWordConnector.getConnection();
		SQLWordConnector.createTable();

		View.launch(args);
	}

}