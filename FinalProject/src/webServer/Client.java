package webServer;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	public void running()
	{
		try
		{
			Socket sock = new Socket("100.65.23.45", 6789);
			
			DataInputStream DIS = new DataInputStream(sock.getInputStream());
			
			System.out.println(DIS.read());
			
			
		}catch(Exception e)
		{
			System.out.println("HEHEHHE");
		}
	}
}
