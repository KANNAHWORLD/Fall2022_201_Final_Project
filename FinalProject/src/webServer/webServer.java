package webServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class webServer {
	
	int PORT = 6789;
	
	public void startWebServer()
	{
		System.out.println("Hellt");
		ServerSocket sevSock;
		try {
			sevSock = new ServerSocket(6789);
		
		Socket sock = null;
		
		try {
			sock = sevSock.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader BR = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		
		while (BR.ready())
		{
			System.out.println(BR.read());
		}
		
		
		}catch(Exception e)
		{
			System.out.println("IDIOT");
		}
	}
}
