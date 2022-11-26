package restAPI.endPoints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.checkerframework.checker.units.qual.A;
import org.springframework.scheduling.annotation.Scheduled;



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
		// using cloudsql database
		DataStore ds = new DataStore();
		
		////////////////////////////
		// TESTS getProfile function
		// Working!
		Profiles test = new Profiles();
		test.FirstName = "Sid";
		
		CreateProfileJson PW = ds.getProfileString(test.UserName);
		System.out.println("HERE");
//		for(Profiles x : PW.profiles)
//		{
//			System.out.println(x.FirstName + x.LastName + x.UserName);
//		}
		
		/////////////////////////////
		
		
		
		/////////////////////
		// CreateProfile Test
		// Status? In progress
		// So far it works
		System.out.println("\n\nCreating new profile test:\n");
		CreateProfileJson CPJ = new CreateProfileJson(34);
		ds.createProfile(CPJ);
		System.out.println(CPJ.ServerMessage);
		System.out.println(CPJ.statusCode);
		
		String query = "SELECT FirstName FROM UserLogin WHERE username=\"" + CPJ.UserName + "\";";
		ResultSet test2 = ds.getQuery(query);
		
		try {
			test2.next();
			System.out.println(test2.getString("FirstName"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		////////////////////
		
		////////////////////
		// CREATING A PROFILE WHICH ALREADY EXISTS TEST
		// IT FUCKING WORKS!!!!!!!!!!!!!!!!!!!!!!!!
		//
		System.out.println("\n\nCREATING SAME PROFILE TEST:\n");
		CPJ = new CreateProfileJson(34);
		ds.createProfile(CPJ);
		System.out.println(CPJ.ServerMessage);
		System.out.println(CPJ.statusCode);
		
		
		////////////////////
		// TESTING if ALLPROFILES WORKS!!
		// Tested and it works
		//
		ArrayList<Profiles> testP = ds.allProfiles();
		for(Profiles profs : testP)
		{
			System.out.println(profs.FirstName);
		}
		
		
		//////////////////////
		// TESTING IF AUTHORIZATION WORKS
		// -- not yet tested
		JsonFormats returned = ds.authorize(CPJ);
		System.out.println("\n\nTESTING AUTHORIZATION");
		System.out.println(returned.ServerMessage);
		System.out.println(returned.statusCode);
		
		
		
		/////////////////////
		// get profile test
		//
		System.out.println("\n\nn\n\n");
		Profiles prof5 = new Profiles();
		prof5.UserName = "sid_bansal";
		CreateProfileJson CPJ5 = ds.getProfileString(prof5.UserName);
		System.out.println(CPJ5.age);
		System.out.println("\n\nn\n\n");

		
		////////////////////
		// TESTING IF MATCH PAIRS WORKS
		// Supposedly works
		ds.matchPairs();
		String query1 = "SELECT username, matchname FROM Matches";
		ResultSet test3 = ds.getQuery(query1);
		try {
			while (test3.next()){
				System.out.println(test3.getString("username") + test3.getString("matchname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ds.getMatch("sanjana123"));
		
	}
	
	
	
	//private static final String DB_USER = "root";
	//private static final String DB_PASS = "dbpassword";
	//private static final String DB_NAME = "db201";
	
	//private static final String INSTANCE_HOST = "34.68.114.207";
	//private static final String DB_PORT = "3306";

	// WERKING!!!
	// cloud sql instance
	DataStore(String URL)
	{
		try {
			
			// username = root
			// password dbpassword
			
			// TO PREVENT Multiple connections being made and overriding 
			// DBConnection. So that its value is only set once upon initializing 
			// DataStore
			if(DBConnection != null)
			{
				return;
			}
			
			
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.68.114.207:3306/db201", "root", "dbpassword");
			DataStore.DBConnection = conn;
			initializeDatabase();

        }
        catch(Exception e)
        {
        	System.out.println("Here");
            System.out.println(e);
        }
	}

	// Uses initializeDatabase()
	DataStore()
	{
		try {
			
			// username = root
			// password dbpassword
			
			// TO PREVENT Multiple connections being made and overriding 
			// DBConnection. So that its value is only set once upon initializing 
			// DataStore
			if(DBConnection != null)
			{
				return;
			}
			
			
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.68.114.207:3306/db201", "root", "dbpassword");
			DataStore.DBConnection = conn;
			initializeDatabase();

        }
        catch(Exception e)
        {
        	System.out.println("Here");
            System.out.println(e);
        }
		
		// TODO: 
		// GO TO DBConstants AND CHANGE TO YOUR PERSONAL SQL USERNAMES AND PASSWORDS!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
		// TO PREVENT Multiple connections being made and overriding 
		// DBConnection. So that its value is only set once upon initializing 
		// DataStore
		
		
//		if(DBConnection != null)
//		{
//			return;
//		}
		
		// Initializes a connection to the database, and assigns it to DBConnection variable
		// to be use by other member functions

		
//		try {
//			Connection conn = DriverManager.getConnection
//					("jdbc:mysql://localhost:3306", DBConstants.UserName, DBConstants.Password);
//			DataStore.DBConnection = conn;
//			conn.createStatement();
//			
//			// Initialzie database
//			initializeDatabase();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
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
	
	
	// Below currently not in use/ not implemented
	// function will eventually be sent to hell
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
	
	//////////////////
	// Executes a script from a .sql file
	// Parameter is a filereader that should be able to read in SQL lines
	// from SQL files
	// Uses noexceptQuery(String)
	// Script to SQL Files and execute them
	//////////////
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
	// Used by CreateProfile
	///////////////////////////
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
	////////////////////////////////////
	
	///////////////////////////////
	// Below like GET query
	//////////////
	//
	//
	// RETURNS NULL IF THE RESULT SET IS EMPTY!!
	public ResultSet getQuery(String query) {
		try {
			Statement st = DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	
	
	/////// createProfile() SHOULD BE COMPLETE ////////////////
	
	// Simply creates the profile in the Database,
	// does not throw exceptions
	// returns the input Json Format but edits the ServerMessage and StatusCode if successful
	// CPJ with error code and message if failed
	public JsonFormats createProfile(CreateProfileJson CPJ)
	{
		// Create the SQL Statements for CPJ
		// Check JsonForats.java, CreateProfileJson class for 
		// data members to be added to the database
		
		///////////////////////////////////////////////////////////////////////
		// query to check if the profile already exists
		
		String exists;
		
		String tables = "UserLogin";
		String fields = "username, password";
		String conditions = "username = \"%s\" AND password = \"%s\"";
		
		conditions = String.format(conditions, CPJ.UserName, CPJ.Password);
		
		exists = "SELECT " + fields + " FROM " + tables + " WHERE " + conditions + ";";
		ResultSet rs = getQuery(exists);
		
		if(rs != null)
		{
			CPJ.ServerMessage = "Profile either exists or username is taken";
			CPJ.statusCode = 417;
			return CPJ;
		}
		
		// LastQuery is there for the purposes of checking which query
		// was the last query before the SQL failed
		String LastQuery = null;
		
		
		// TODO:
		// IMPORTANT //////////////////////
		// Goes ahead and queries each data table
		// and inserts the appropriate information
		// 3/4 need to be readjusted once the tables format has been changed
		// IMPORTANT //////////////////////
		
		try
		{
			/////////////////////////////////////////////////////////////////////////////
	
			/////////////////////////////////////////////////////////////////////////////
			// Inputs the information to the UserLogin table
			// Build the query string here
			String query = "";
			tables = "UserLogin";
			String values = "VALUES(\"%s\",\"%s\",\"%s\",\"%s\")";
			values = String.format(values, CPJ.first, CPJ.last, CPJ.UserName, CPJ.Password);
			query = "INSERT INTO " + tables + " " + values + ";";
			LastQuery = query;
			generalQuery(query);
			
			// Should be properly inserted above
			////////////////////////////////////////////////////////////////////////////////
			
			
			
			// Below changed to add support for username mapping
			
			///////////////////////////////////////////////////////////////////////////////
			// Inputs the information into UserInfo table
			
			tables = "UserInfo";
			values = "VALUES(\"%s\", \"%s\",\"%s\",%d, %d, %d, \"%s\",\"%s\")";
			values = String.format(values, CPJ.UserName, CPJ.first, CPJ.last, CPJ.age, CPJ.SexOrient, CPJ.gender, CPJ.insta, CPJ.description);
			query = "INSERT INTO " + tables + " " + values + ";";
			LastQuery = query;
			generalQuery(query);
			
			//Should be inserted into UserInfo
			/////////////////////////////////////////////////////////////////////////////////
			
			
			
			/////////// IMPORTANT //////////////////
			// BELOW NEEDS TO BE FIXED BECAUSE WE DO NOT KNOW HOW TO STORE THE THINGS BELOW
			//////////// IMPORTANT //////////////////
			//
			// Adjusted to support username instead of first/last name
			
			
			///////////////////////////////////////////////////////////////////////////////
			// Inputs the information into UserPrference table
			// Currently using a CSV approach
			// Should ideally store usernames
			
			tables = "UserPreferenceList";
			values = "VALUES(\"%s\", \"%s\")";
			
			
			String CSVPreferences = "";
			for(String x : CPJ.prefered.people)
			{
				CSVPreferences += x + ",";
			}
			
			// adds no one as a placeholder if there is no one in their preference list
			if(CSVPreferences.equals(""))
			{
				CSVPreferences += "no one";
			}
			
			values = String.format(values, CPJ.UserName, CSVPreferences);
			query = "INSERT INTO " + tables + " " + values + ";";
			LastQuery = query;
			generalQuery(query);
			
			// Should be inserted into UserPreferenceList
			/////////////////////////////////////////////////////////////////////////////////
			
			
			
			///////////////////////////////////////////////////////////////////////////////
			// Inputs the information into UserDesiresRanking table
						
			tables = "UserDesiresRanking";
			values = "VALUES(\"%s\", %d, %d, %d, %d, %d, %d, %d, %d, %d, %d )";
			values = String.format(values, CPJ.UserName, CPJ.preferRank.extroverted, CPJ.preferRank.humor, CPJ.preferRank.adventure,
					CPJ.preferRank.ambition, CPJ.preferRank.artistic, CPJ.preferRank.wOfAff, CPJ.preferRank.physTouch, CPJ.preferRank.gifts,
					CPJ.preferRank.qualTime, CPJ.preferRank.service);
			query = "INSERT INTO " + tables + " " + values + ";";
			LastQuery = query;
			generalQuery(query);

			// Should be inserted into UserDesiresRanking table
			/////////////////////////////////////////////////////////////////////////////////
			
			
	
			///////////////////////////////////////////////////////////////////////////////
			// Inputs the information into UserSelfRanking table
			
			tables = "UserSelfRanking";
			values = "VALUES(\"%s\", %d, %d, %d, %d, %d, %d, %d, %d, %d, %d )";
			values = String.format(values, CPJ.UserName, CPJ.selfRank.extroverted, CPJ.selfRank.humor, CPJ.selfRank.adventure,
					CPJ.selfRank.ambition, CPJ.selfRank.artistic, CPJ.selfRank.wOfAff, CPJ.selfRank.physTouch, CPJ.selfRank.gifts,
					CPJ.selfRank.qualTime, CPJ.selfRank.service);
			query = "INSERT INTO " + tables + " " + values + ";";
			LastQuery = query;
			generalQuery(query);
			
			// Should be inserted into UserSelfRanking table
			/////////////////////////////////////////////////////////////////////////////////
		}
		catch (SQLException SQLE)
		{
			SQLE.printStackTrace();
			System.out.println("There was an error in the insertion somewhere somehow. This was the "
					+ "Last Query Executed:\n" + LastQuery + "\n");
			CPJ.ServerMessage = "SQL QUERY FAILED DURING PROFILE CREATION";
			CPJ.statusCode = 417;
			return CPJ;
			
		} 
		catch (Exception GeneralException)
		{
			System.out.println("Some other exception was hit\n");
			CPJ.statusCode = 417;
			CPJ.ServerMessage = "SOME ERROR OCCURED";
			GeneralException.printStackTrace();
			return CPJ;
			
		}
		
		CPJ.ServerMessage = "The Profile Has been Created";
		CPJ.statusCode = 200;
		return CPJ;
	}
	


	// POSSIBLY COMPLETE
	// gets singular profile
	//
//	public CreateProfileJson getProfile(Profiles JF)
//	{
//		// Builds the SQL statement before it is queried
//		// Then, the percent signs are filled with the parameters
//		// by doing .format
//		
//		
//		String query = "SELECT ui.username, ui.Instagram, ui.UserDescription, ui.SexualOrientation, ui.Gender, upl.PreferenceList, udr.Extroverted,"
//				+ " udr.Humor, udr.Adventurous, udr.Ambitious, udr.Artistic, udr.WordsOfAffirmation, udr.PhysicalTouch,"
//				+ " udr.ReceivingGifts, udr.QualityTime, udr.ActsOfService, m.matchname, usr.Extroverted as SE, usr.Humor as SH, "
//				+ "usr.Adventurous as SA, usr.Ambitious as SAM, usr.Artistic as SAR, usr.WordsOfAffirmation as SW,"
//				+ " usr.PhysicalTouch as SP, usr.ReceivingGifts as SR, usr.QualityTime as SQ, usr.ActsOfService as SAC "
//				+ "FROM UserInfo ui, UserPreferenceList upl, UserDesiresRanking udr, UserSelfRanking usr, Matches m "
//				+ "WHERE ui.username = upl.username AND ui.username = udr.username AND ui.username = usr.username AND ui.username = m.username";
//		ResultSet rs = getQuery(query);
//		
//		CreateProfileJson prof = new CreateProfileJson();
//		try
//		{
//<<<<<<< HEAD
//			rs.next();
//=======
//			while(rs.next()){
//				String g = rs.getString("Gender");
//				System.out.println(g);
//				if (g.equals("M")){
//					prof.gender = 1;
//				}
//				else if (g.equals("F")){
//					prof.gender = 2;
//				}
//				else{
//					prof.gender = 3;
//				}
//				g = rs.getString("SexualOrientation");
//				if (g.equals("M")){
//					prof.SexOrient = 1;
//				}
//				else if (g.equals("F")){
//					prof.SexOrient = 2;
//				}
//				else{
//					prof.SexOrient = 3;
//				}
//				prof.description = rs.getString("UserDescription");
//				prof.insta = rs.getString("Instagram");
//				prof.first = rs.getString("matchname");
//				prof.UserName = rs.getString("username");
//				prof.selfRank.extroverted = rs.getInt("SE");
//				prof.selfRank.humor = rs.getInt("SH");
//				prof.selfRank.adventure = rs.getInt("SA");
//				prof.selfRank.ambition = rs.getInt("SAM");
//				prof.selfRank.artistic = rs.getInt("SAR");
//				prof.selfRank.wOfAff = rs.getInt("SW");
//				prof.selfRank.physTouch = rs.getInt("SP");
//				prof.selfRank.gifts = rs.getInt("SR");
//				prof.selfRank.qualTime = rs.getInt("SQ");
//				prof.selfRank.service = rs.getInt("SAC");
//				
//				prof.preferRank.extroverted = rs.getInt("Extroverted");
//				prof.preferRank.humor = rs.getInt("Humor");
//				prof.preferRank.adventure = rs.getInt("Adventurous");
//				prof.preferRank.ambition = rs.getInt("Ambitious");
//				prof.preferRank.artistic = rs.getInt("Artistic");
//				prof.preferRank.wOfAff = rs.getInt("WordsOfAffirmation");
//				prof.preferRank.physTouch = rs.getInt("PhysicalTouch");
//				prof.preferRank.gifts = rs.getInt("ReceivingGifts");
//				prof.preferRank.qualTime = rs.getInt("QualityTime");
//				prof.preferRank.service = rs.getInt("ActsOfService");
//			}
//		} catch (Exception e)
//		{
//			System.out.println("SOMETHING WAS CAUGHT");
//			prof.ServerMessage = "There was an error";
//			prof.statusCode = 404;
//			return prof;
//		}
//		
//		query = "SELECT FirstName, LastName FROM UserLogin WHERE username=" + prof.UserName + ";";
//		ResultSet rs2 = getQuery(query);
//		try
//		{
//			prof.first = rs2.getString("FirstName");
//			prof.last = rs2.getString("LastName");
//		} catch (Exception e)
//		{
//			System.out.println("There was an exception which is not likely");
//		}
//		
//		
//		prof.ServerMessage = "Got proifle";
//		prof.statusCode = 200;
//		return prof;
//		
//	}

	public CreateProfileJson getProfileString(String usern)
	{
		// Builds the SQL statement before it is queried
		// Then, the percent signs are filled with the parameters
		// by doing .format
		
		String col = "m.username = \"%s\"";
		col = String.format(col, usern);
		String query = "SELECT ui.Age, ui.username, ui.Instagram, ui.UserDescription, ui.SexualOrientation, ui.Gender, upl.PreferenceList, udr.Extroverted,"
				+ " udr.Humor, udr.Adventurous, udr.Ambitious, udr.Artistic, udr.WordsOfAffirmation, udr.PhysicalTouch,"
				+ " udr.ReceivingGifts, udr.QualityTime, udr.ActsOfService, m.matchname, usr.Extroverted as SE, usr.Humor as SH, "
				+ "usr.Adventurous as SA, usr.Ambitious as SAM, usr.Artistic as SAR, usr.WordsOfAffirmation as SW,"
				+ " usr.PhysicalTouch as SP, usr.ReceivingGifts as SR, usr.QualityTime as SQ, usr.ActsOfService as SAC "
				+ "FROM UserInfo ui, UserPreferenceList upl, UserDesiresRanking udr, UserSelfRanking usr, Matches m "
				+ "WHERE ui.username = upl.username AND ui.username = udr.username AND ui.username = usr.username AND ui.username = m.username AND " + col;
		ResultSet rs = getQuery(query);
		if(rs == null) {
			return null;
		}
		
		CreateProfileJson prof = new CreateProfileJson();
		try
		{
			while (rs.next())
			{
			String g = rs.getString("Gender");
			if (g.equals("M")){
				prof.gender = 1;
			}
			else if (g.equals("F")){
				prof.gender = 2;
			}
			else{
				prof.gender = 3;
			}
			g = rs.getString("SexualOrientation");
			if (g.equals("M")){
				prof.SexOrient = 1;
			}
			else if (g.equals("F")){
				prof.SexOrient = 2;
			}
			else{
				prof.SexOrient = 3;
			}
			prof.age = rs.getInt("Age");
			prof.description = rs.getString("UserDescription");
			prof.insta = rs.getString("Instagram");
			prof.first = rs.getString("matchname");
			prof.UserName = rs.getString("username");
			prof.selfRank.extroverted = rs.getInt("SE");
			prof.selfRank.humor = rs.getInt("SH");
			prof.selfRank.adventure = rs.getInt("SA");
			prof.selfRank.ambition = rs.getInt("SAM");
			prof.selfRank.artistic = rs.getInt("SAR");
			prof.selfRank.wOfAff = rs.getInt("SW");
			prof.selfRank.physTouch = rs.getInt("SP");
			prof.selfRank.gifts = rs.getInt("SR");
			prof.selfRank.qualTime = rs.getInt("SQ");
			prof.selfRank.service = rs.getInt("SAC");
			
			prof.preferRank.extroverted = rs.getInt("Extroverted");
			prof.preferRank.humor = rs.getInt("Humor");
			prof.preferRank.adventure = rs.getInt("Adventurous");
			prof.preferRank.ambition = rs.getInt("Ambitious");
			prof.preferRank.artistic = rs.getInt("Artistic");
			prof.preferRank.wOfAff = rs.getInt("WordsOfAffirmation");
			prof.preferRank.physTouch = rs.getInt("PhysicalTouch");
			prof.preferRank.gifts = rs.getInt("ReceivingGifts");
			prof.preferRank.qualTime = rs.getInt("QualityTime");
			prof.preferRank.service = rs.getInt("ActsOfService");
		}
		} catch (Exception e)
		{
			System.out.println("SOMETHING WAS CAUGHT");
			e.printStackTrace();
			prof.ServerMessage = "There was an error";
			prof.statusCode = 404;
			return prof;
		}
		col = "username = \"%s\"";
		col = String.format(col, prof.UserName);
		System.out.println(prof.UserName);
		query = "SELECT FirstName, LastName FROM UserLogin WHERE " + col;
		ResultSet rs2 = getQuery(query);
		try
		{
			while(rs2.next()){

			
			prof.first = rs2.getString("FirstName");
			prof.last = rs2.getString("LastName");
			}
		} catch (Exception e)
		{
			System.out.println("There was an exception which is not likely");
		}
		
		
		prof.ServerMessage = "Got proifle";
		prof.statusCode = 200;
		return prof;
		
	}

	
	// Simple, basically just gets all of the people on the platform
	// and returns a set with FirstName, LastName, and usernames of each 
	// user on the platform
	public ArrayList<Profiles> allProfiles()
	{
		String components = "FirstName, LastName, username";
		String table = "UserLogin";
		String query = "SELECT " + components + " FROM " + table + ";";
		ResultSet rs = getQuery(query);
		
		ArrayList<Profiles> retSet = new ArrayList<Profiles>();
		Profiles prof = null;
		try {
			while (rs.next())
			{
				prof = new Profiles();
				prof.FirstName = rs.getString("FirstName");
				prof.LastName = rs.getString("LastName");
				prof.UserName = rs.getString("username");
				retSet.add(prof);
			}
		} catch (SQLException e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		
		return retSet;
		
	}

	// Thanks to Aayushi!!!!
	public String getMatch(String usern){
		String conditions = "username = \"%s\"";
		
		conditions = String.format(conditions, usern);
		String query = "SELECT matchname FROM Matches WHERE " + conditions;
		ResultSet rs = getQuery(query);
		String toreturn = null;
		try {
			while (rs.next())
			{
				toreturn = rs.getString("matchname");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toreturn;
	}

	
	// Thanks to Aayushi!!!!!
	@Scheduled (cron = "0 0 0 * * ?")
	public void matchPairs(){
		String query = "SELECT ui.username, ui.SexualOrientation, ui.Gender, upl.PreferenceList, udr.Extroverted,"
				+ " udr.Humor, udr.Adventurous, udr.Ambitious, udr.Artistic, udr.WordsOfAffirmation, udr.PhysicalTouch,"
				+ " udr.ReceivingGifts, udr.QualityTime, udr.ActsOfService, m.matchname, usr.Extroverted as SE, usr.Humor as SH, "
				+ "usr.Adventurous as SA, usr.Ambitious as SAM, usr.Artistic as SAR, usr.WordsOfAffirmation as SW,"
				+ " usr.PhysicalTouch as SP, usr.ReceivingGifts as SR, usr.QualityTime as SQ, usr.ActsOfService as SAC "
				+ "FROM UserInfo ui, UserPreferenceList upl, UserDesiresRanking udr, UserSelfRanking usr, Matches m "
				+ "WHERE ui.username = upl.username AND ui.username = udr.username AND ui.username = usr.username AND ui.username = m.username";
		ResultSet rs = getQuery(query);
		Vector<CreateProfileJson> peop = new Vector<CreateProfileJson>();
		HashSet<String> matched = new HashSet<String>();
		HashMap<String, HashMap<String, Integer>> rated = new HashMap<String, HashMap<String, Integer>>();
		try {
			while (rs.next())
			{
				CreateProfileJson prof = new CreateProfileJson();	
				String g = rs.getString("Gender");
				if (g == "M"){
					prof.gender = 1;
				}
				else if (g == "F"){
					prof.gender = 2;
				}
				else{
					prof.gender = 3;
				}
				g = rs.getString("SexualOrientation");
				if (g == "M"){
					prof.SexOrient = 1;
				}
				else if (g == "F"){
					prof.SexOrient = 2;
				}
				else{
					prof.SexOrient = 3;
				}
				prof.first = rs.getString("matchname");
				prof.UserName = rs.getString("username");
				prof.selfRank.extroverted = rs.getInt("SE");
				prof.selfRank.humor = rs.getInt("SH");
				prof.selfRank.adventure = rs.getInt("SA");
				prof.selfRank.ambition = rs.getInt("SAM");
				prof.selfRank.artistic = rs.getInt("SAR");
				prof.selfRank.wOfAff = rs.getInt("SW");
				prof.selfRank.physTouch = rs.getInt("SP");
				prof.selfRank.gifts = rs.getInt("SR");
				prof.selfRank.qualTime = rs.getInt("SQ");
				prof.selfRank.service = rs.getInt("SAC");
				
				prof.preferRank.extroverted = rs.getInt("Extroverted");
				prof.preferRank.humor = rs.getInt("Humor");
				prof.preferRank.adventure = rs.getInt("Adventurous");
				prof.preferRank.ambition = rs.getInt("Ambitious");
				prof.preferRank.artistic = rs.getInt("Artistic");
				prof.preferRank.wOfAff = rs.getInt("WordsOfAffirmation");
				prof.preferRank.physTouch = rs.getInt("PhysicalTouch");
				prof.preferRank.gifts = rs.getInt("ReceivingGifts");
				prof.preferRank.qualTime = rs.getInt("QualityTime");
				prof.preferRank.service = rs.getInt("ActsOfService");
				String[] prefl = rs.getString("PreferenceList").split(",");
				HashMap<String, Integer> toa = new HashMap<String, Integer>();
				for (int i = 0; i < prefl.length; i++){
					String na = prefl[i];
					i++;
					int rat = Integer.valueOf(prefl[i]);
					toa.put(na, rat);
				}
				rated.put(prof.UserName, toa);
				peop.add(prof);
			}
			MatchThread.peop = peop;
			MatchThread.rated = rated;
			MatchThread.set = new TreeMap<Integer, Vector<Vector<String>>>();
			ExecutorService matchthreads = Executors.newCachedThreadPool();
			for (int i = 0; i < peop.size(); i++){
				MatchThread threadtoadd = new MatchThread(i);
				System.out.println(i);
				matchthreads.execute(threadtoadd);
			}
			matchthreads.shutdown();
			while (!matchthreads.isTerminated()){
				Thread.yield();
			}
			for (Map.Entry<Integer, Vector<Vector<String>>> entry : MatchThread.set.entrySet()){
				Vector<Vector<String>> pval = entry.getValue();
				for (int a = 0; a < pval.size(); a++){
					String p1 = pval.get(a).get(0);
					String p2 = pval.get(a).get(1);
					if (!matched.contains(p1) && !matched.contains(p1))
					{
						matched.add(p1);
						matched.add(p2);
						System.out.println(p1);
						String conditions = "username = \"%s\"";
						String col = "matchname = \"%s\"";
						col = String.format(col, p1);
		
						conditions = String.format(conditions, p2);
						String query1 = "UPDATE Matches SET " + col + " WHERE " + conditions;
						generalQuery(query1);
						conditions = "username = \"%s\"";
		
						conditions = String.format(conditions, p1);
						col = "matchname = \"%s\"";
						col = String.format(col, p2);
						String query2 = "UPDATE Matches SET " + col + " WHERE " + conditions;
						generalQuery(query2);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}

	}
	
	
	// Code below needs some adjustment
	//
	// currently returns void, might be needed to change later on
	// Might need to change the function parameter
	// Might return result set
	public JsonFormats authorize(CreateProfileJson JF)
	{
		String Components = "username";
		String Table = "UserLogin";
		String Conditions = "username=\"%s\" AND password=\"%s\"";
		
		
		String query = "SELECT " + Components + " FROM " + Table + " WHERE " + Conditions + ";";
		query = String.format(query, JF.UserName, JF.Password);
		
		ResultSet set = getQuery(query);
		
		JsonFormats retJson = new JsonFormats();
		
		try {
			if(set.next())
			{
				retJson.ServerMessage = "User Profile Found";
				retJson.statusCode = 200;
			}
			else
			{
				retJson.ServerMessage = "User profile does not exist";
				retJson.statusCode = 404;
			}
		} catch (SQLException e1) {
			System.out.println("Error in DataStore.authorize function");
			e1.printStackTrace();
		}

		return retJson;

		
	}

	public void changeMatch(String p1, String p2){
		System.out.println(p1 + p2);
		String conditions = "username = \"%s\"";
		conditions = String.format(conditions, p2);
		String query = "SELECT matchname FROM Matches WHERE " + conditions;
		ResultSet rs = getQuery(query);
		boolean matched = true;
		try {
			while (rs.next()){
				if (rs.getString("matchname").equals("-5")){
					matched = false;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(matched);
		if (matched){
			conditions = "username = \"%s\"";
			String col = "matchname = \"%s\"";
			col = String.format(col, "-5");

			conditions = String.format(conditions, p2);
			String query1 = "UPDATE Matches SET " + col + " WHERE " + conditions;
			try {
				generalQuery(query1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conditions = "username = \"%s\"";

			conditions = String.format(conditions, p1);
			col = "matchname = \"%s\"";
			col = String.format(col, "-5");
			String query2 = "UPDATE Matches SET " + col + " WHERE " + conditions;
			try {
				generalQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			conditions = "username = \"%s\"";
			String col = "matchname = \"%s\"";
			col = String.format(col, p1);

			conditions = String.format(conditions, p2);
			String query1 = "UPDATE Matches SET " + col + " WHERE " + conditions;
			try {
				generalQuery(query1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conditions = "username = \"%s\"";

			conditions = String.format(conditions, p1);
			col = "matchname = \"%s\"";
			col = String.format(col, p2);
			String query2 = "UPDATE Matches SET " + col + " WHERE " + conditions;
			try {
				generalQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		query = "SELECT matchname FROM Matches WHERE " + conditions;
		ResultSet rs5 = getQuery(query);
		matched = true;
		try {
			while (rs5.next()){
				if (rs5.getString("matchname").equals("-5")){
					matched = false;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(matched);
		
	}
	
}


