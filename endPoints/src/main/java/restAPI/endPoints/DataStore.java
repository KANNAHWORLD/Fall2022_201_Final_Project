package restAPI.endPoints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// NOTE: WE NEED MIGHT NEED TO MAKE ANOTHER GENERAL GET QUERY THAT RETURNS A JSON
// THE CURRENT GENERALQUERY ONLY MAKES A QUERY, SOMETHING SIMILAR TO A POST QUERY
// FEEL FREE TO ADD IF NEEDED
// MIGHT BE BENEFICIAL TO ADD ABOVE FUNCTION AND HAVE IT RETURN A RESULT SET

public class DataStore 
{
	
	// Static to make sure that all instances are using the same connection
	// This could be made to not be 
	private static Connection DBConnection = null;
  
	
	// Main needs to ultimately phased out
	// its only purpose is to test functionality within the program
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
		
		Profiles test = new Profiles();
		test.FirstName = "Sid";
		
		ProfileWrapper PW = ds.getProfile(test);
		
		for(Profiles x : PW.profiles)
		{
			System.out.println(x.FirstName + x.LastName + x.UserName);
		}
		
		//Creates Schema "testSchema"
//		ds.noexceptQuery("CREATE SCHEMA testSchema");
//		
//		// TODO: add code here for testing
//		
//		ds.noexceptQuery("CREATE TABLE testSchema.herro(number INT)");
//		ds.noexceptQuery("INSERT INTO testSchema.herro VALUES (50)");
//		
//		//Deletes the Schema
//		ds.noexceptQuery("DROP SCHEMA testSchema");
	}
	
	
	// IGNORE FOR NOW
	// Eventually we want to somehow link this constructor to the google cloud
	// cloud sql instance
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

	// Uses initializeDatabase()
	DataStore()
	{
		
		
		// TODO: 
		// GO TO DBConstants AND CHANGE TO YOUR PERSONAL SQL USERNAMES AND PASSWORDS!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
		// TO PREVENT Multiple connections being made and overriding 
		// DBConnection. So that its value is only set once upon initializing 
		// DataStore
		if(DBConnection != null)
		{
			return;
		}
		
		// Initializes a connection to the database, and assigns it to DBConnection variable
		// to be use by other member functions

		
		try {
			Connection conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306", DBConstants.UserName, DBConstants.Password);
			DataStore.DBConnection = conn;
			conn.createStatement();
			
			// Initialzie database
			initializeDatabase();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//////////////////////
	//Deprecated ///////////
	////////////////////
	/*
	 * JsonFormats getQuery(String query) { PreparedStatement ps = null; ResultSet
	 * rs = null;
	 * 
	 * try { ps = DBConnection.prepareStatement(query); rs = ps.executeQuery(); }
	 * catch (SQLException e) { JsonFormats jf = new JsonFormats(); jf.statusCode =
	 * 404; return jf; }
	 * 
	 * return new JsonFormats(); }
	 */
	
	JsonFormats postQuery(JsonFormats jfmt)
	{
		
		
		
		return new JsonFormats();
	}
	
	
	// To default initialize a database
	// Defualt file
	//				sql/datatable_creation.sql
	// _______________________________________
	
	// Should be called by default constructor
	private void initializeDatabase()
	{
		String defaultPath = "src/main/sql/datatable_creation.sql";
		try {
			
			File file = new File(defaultPath);
			BufferedReader pass = new BufferedReader(new FileReader(file));
			scriptExecutor(pass);
			pass.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not read, not\nDatabase not created");
		} catch (IOException e) {
			System.out.println("File not closed");
			e.printStackTrace();
		}
	}
	
	// To initialize a database from a custom script
	// Provide a file directory/file name and reader will automatically created and passed
	// to scriptExecutor
	@SuppressWarnings("unused")
	private void customInitializeDatabase(String scriptLocation)
	{
		try {
			
			BufferedReader pass = new BufferedReader(new FileReader(scriptLocation));
			scriptExecutor(pass);
			pass.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not read\nDatabase not created");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File not closed");
			e.printStackTrace();
		}
	}
	
	
	// Executes a script from a .sql file
	// Parameter is a filereader that should be able to read in SQL lines
	// from SQL files
	// Uses noexceptQuery(String)
	// Script to SQL Files and execute them
	private void scriptExecutor(BufferedReader file)
	{
		String reading = "";
		String query = "";
		
		try {
			while((reading = file.readLine()) != null)
			{
				//System.out.println(reading);
				if(reading.isEmpty())
				{
					continue;
				}
				else if(reading.charAt(0) == '#')
				{
					reading = null;
					continue;
				}
				else if(reading.charAt(reading.length()-1) != ';')
				{
					query += reading + " ";
					continue;
				}
				
				query += reading;
				
				noexceptQuery(query);
				
				// Debug purposes
				// System.out.println(query);
				//
				
				reading = null;
				query = "";
			}
		} catch (IOException e) {
			System.out.println("There was a problem in somewhere");
			e.printStackTrace();
		}
		System.out.println("Database Created");
	}
	
	
	///////////////////////////////
	// Both below like POST queries
////////	
	// General query should become a private member function eventually
	// throws exception so that we know whether or not a query went through
	public void generalQuery(String query) throws SQLException
	{
		Statement st = null;
		st = DBConnection.createStatement();
		st.execute(query);
	}
	
	
	// This should be used for testing certain statements
	// Use generalQuery for all other integrations 
	// Basically you can make any query with this
	public void noexceptQuery(String query)
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
//////////////
	// Above like POST query
	
	
	
	// Below like GET query
//////////////
	public ResultSet getQuery(String query) {
		
		try {
			Statement st = DBConnection.createStatement();
			ResultSet ret = st.executeQuery(query);
			if(!ret.next())
			{
				System.out.println("Empty result set");
				return null;
			}
			else
			{
				ret.beforeFirst();
			}
			return ret;
		} catch (SQLException e) {
			System.out.println("There was an error in the query");
			e.printStackTrace();
		}
		
		// Just here to make the compiler SHUT up
		return null;
	}
	
	
/////////////
	// Above like GET query
	///////////////////////
	
	
	
	// Simply creates the profile in the Database,
	// so far does not throw exceptions
	public JsonFormats createProfile(CreateProfileJson CPJ)
	{
		// Create the SQL Statements for CPJ
		// Check JsonForats.java, CreateProfileJson class for 
		// data members to be added to the database
		
		// Temporary place holder
		// Build the query string here
		String query = "";
		
		// call generalQuery with the string built here
		// error is catched here, and either error code is returned,
		// or some other handling will happen
		try {
			generalQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return new JsonFormats(417);
		}
		return new JsonFormats(200);
	}

	
	// In this section, we will just query based on the profile we want
	// It will only return name and password
	// Needs a first name or last name
	public ProfileWrapper getProfile(Profiles JF)
	{
		// Builds the SQL statement before it is queried
		// Then, the percent signs are filled with the parameters
		// by doing .format
		
		String Components = "FirstName, LastName, username";
		String Table = "UserLogin";
		String Conditions = "FirstName=%s OR LastName=%s OR FirstName=%s OR LastName=%s";
		
		String query = "SELECT " + Components + " FROM " + Table + " WHERE " + Conditions + ";";
		query = String.format(query, JF.FirstName, JF.FirstName, JF.LastName, JF.LastName);
		
		ResultSet set = getQuery(query);
		
		if(set.equals(null))
		{
			return new ProfileWrapper(404);
		}
		
		ProfileWrapper ret = new ProfileWrapper();
		Profiles addition = new Profiles();
		
		// Basically goes through the entire result set and finds all of the data
		// which gets added to the ProfilesWrapper which would eventually be sent 
		// to the front-end.
		
		// Finds all of the profiles that match with the name/last name provided
		try {
			while(set.next())
			{
				addition = new Profiles();
				addition.FirstName = set.getString("FirstName");
				addition.LastName = set.getString("LastName");
				addition.UserName = set.getString("username");
				ret.profiles.add(addition);
			}
		} catch (SQLException e) {
			System.out.println("There was a problem b/c you are a bad programmer");
			e.printStackTrace();
		}
		return ret;
	}

	// currently returns void, might be needed to change later on
	// Might need to change the function parameter
	// Might return result set
	public void authorize(JsonFormats JF)
	{
		//place holder
		String query = "";
		
		// might need to change function
		try {
			generalQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
