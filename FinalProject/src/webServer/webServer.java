package webServer;
import java.io.IOException;
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
		} catch (IOException e) {
			System.out.println("YOU ARE IDIOT");
			return;
		}
		
		Socket sock;
		
		try {
			sock = sevSock.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
