import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTranslationApplication 
{
	public static void main(String[] args) 
	{
		try 
		{
			// connect port
			Socket socket=new Socket(InetAddress.getLocalHost(),4231);
			
			// get outputstream data and read it
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String text= bufferedReader.readLine();
			
			// display the message
			System.out.println(text);
			
			// close everthing
			bufferedReader.close();
			socket.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
