package restAPI.endPoints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DataStore 
{
	private Connection DBConnection;
  
	public static void main(String [] args)
	{
		//String URL2 = "jdbc:mysql:/35.185.234.102:3306/test201";
		//DataStore ss = new DataStore(URL2);
		
//		String URL = "jdbc:mysql:///test201?cloudSqlInstance="
//				+ "utopian-sky-368201:us-west1:test201" // Instance name
//				+ "&socketFactory=com.google."
//				+ "cloud.sql.mysql.SocketFactory&"
//				+ "user=test201" // username
//				+ "&password=horsehorsepig"; // password
//		
//		DataStore s = new DataStore(URL);
		//ss.createSchema("CREATE SCHEMA testSchema");
		
		
		// ^^ IGNORE ABOVE
		
		// Creates a new database by using default constructor.
		// Deletes the schema, then creates a new one at each instance
		// might be usefule for testing functionality and writing specific code
		DataStore ds = new DataStore();
		
		//Creates Schema "testSchema"
		ds.generalQuery("CREATE SCHEMA testSchema");
		
		// TODO: add code here for testing
		
		ds.generalQuery("CREATE TABLE herro(number INT)");
		ds.generalQuery("INSERT INTO herro VALUES (50)");
		
		//Deletes the Schema
		ds.generalQuery("DROP SCHEMA testSchema");
	}
	
	// IGNORE FOR NOW
	DataStore(String URL)
	{
		try {
			
			
			// jdbc:mysql://google/test201?cloudSqlInstance=utopian-sky-368201:us-west1:test201&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=root
			
            Class.forName("com.mysql.cj.jdbc.Driver");

//            String instanceConnectionName = "TheActualInstanceName"; 
//            String databaseName = "BudgetApp";
//
//            String IP_of_instance = "35.185.234.102";
//            String username = "201test";
//            String password = "horsepig";
//            String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + 
//                    "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
//                    IP_of_instance, databaseName, instanceConnectionName);
            
            String jdbcurl2 = "jdbc:mysql://google/test201?cloudSqlInstance=utopian-sky-368201:us-west1:test201&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=root";

           // Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Connection conn = DriverManager.getConnection(jdbcurl2);
            
            Statement stat = conn.createStatement();
            
            DBConnection = conn;
           
//            ResultSet res = stat.executeQuery("Select * from users;");
//            String result = "";
//            while (res.next()) 
//            {
//                result += (res.getString(1) + " " + res.getString(2) + " " + res.getString(3));
//            }
        }
        catch(Exception e)
        {
        	System.out.println("Here");
            System.out.println(e);
//            return null;
        }


	}

	DataStore()
	{
		
		
		// TODO: 
		// GO TO DBConstants AND CHANGE TO YOUR PERSONAL SQL USERNAMES AND PASSWORDS!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
		
		// Initializes a connection to the database, and assigns it to DBConnection variable
		// to be use by other member functions
		try {
			Connection conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306", DBConstants.UserName, DBConstants.Password);
			DBConnection = conn;
			conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	JsonFormats getQuery(String query)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = DBConnection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			JsonFormats jf = new JsonFormats();
			jf.statusCode = 404;
			return jf;
		}
		
		return new JsonFormats();
	}
	
	JsonFormats postQuery(JsonFormats jfmt)
	{
		
		
		
		return new JsonFormats();
	}
	
	public void generalQuery(String query)
	{
		Statement st = null;
		try {
			st = DBConnection.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("SQL STATEMENT FAILED");
			System.out.println(e.getMessage());
		}
		
	}
}
