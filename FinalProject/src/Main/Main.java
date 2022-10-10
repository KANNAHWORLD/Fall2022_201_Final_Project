package Main;
import webServer.*;

public class Main {
	public static void main(String [] args) 
	{
		System.out.println("Hello World!");
		TestClass.printRandomTestMessage();
		TestClass.sayHi("Sanjana");
		
		webServer webS = new webServer();
		
	}
}