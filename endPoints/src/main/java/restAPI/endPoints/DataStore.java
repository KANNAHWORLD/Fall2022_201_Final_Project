package restAPI.endPoints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataStore 
{

	Connection DBConnection = null;
	DataStore(String URL)
	{
		try {
			DBConnection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			System.out.println("Error connecting to Database!");
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
}
