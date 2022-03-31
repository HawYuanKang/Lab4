import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTranslationApplication 
{
	public static void main(String[] args)throws IOException
	{
		ServerSocket serverSocket=null;
		
		try
		{
			// bind port
			int portNo=4231;
			serverSocket=new ServerSocket(portNo);
			
			// default message
			String text1="Good Afternoon";
			System.out.println("Waiting for request");
			
			// always check accept
			while(true) 
			{
				Socket clientSocket=serverSocket.accept();
				DataOutputStream outputStream=new DataOutputStream(clientSocket.getOutputStream());
				outputStream.writeUTF(text1);
				clientSocket.close();
			}
		}
		catch(IOException ioe)
		{
			if(serverSocket !=null)
			{
				serverSocket.close();
			}
			ioe.printStackTrace();
		}
	}
}
