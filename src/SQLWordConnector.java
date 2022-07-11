import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLWordConnector {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String mySQLURL = "jdbc:mysql://localhost:3306/wordoccurrences";
	private static String username = "";
	private static String password = "";

	public static Connection getConnection() throws Exception {
		try {
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(mySQLURL, username, password);
			System.out.println("Connected");

			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public static void createTable() throws Exception {
		try {
			System.out.println("Entering createTable()");
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS word(id int NOT NULL AUTO_INCREMENT, word varchar(255), count int, PRIMARY KEY(id))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Exiting createTable()");
		}
	}

	public static void post(String word, int count) throws Exception {
		try {
			System.out.println("Entering post()");
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement(
					"INSERT INTO wordOccurrences.word (word, count) VALUES ('" + word + "', '" + count + "')");
			posted.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Exiting post()");
		}
	}

	public static ArrayList<String> get() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM word");

			ResultSet result = statement.executeQuery();

			ArrayList<String> array = new ArrayList<String>();
			while (result.next()) {
				System.out.print("id: ");
				System.out.print(result.getString("id"));
				System.out.print("\t");
				System.out.print(result.getString("word"));
				System.out.print("\t");
				System.out.println(result.getString("count"));

				array.add(result.getString("word"));
				array.add(result.getString("count"));
			}
			System.out.println("All records have been selected!");
			return array;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}