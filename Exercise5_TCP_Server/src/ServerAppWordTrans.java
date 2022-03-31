
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAppWordTrans 
{
	public static void main(String[] args) throws IOException
	{
		// declare all others class so later can call their function
		ServerSetUp setup = new ServerSetUp();
		setup.createData();
		
		ServerSocket serverSocket = null;
		ServerTranslate translate = new ServerTranslate();
		
		ServerFrame frame = new ServerFrame();
		frame.setVisible(true);
		
		try
		{
			// initial value of request
			int totalRequest = 0;
			
			// bind port
			int portNo = 4220;
			serverSocket = new ServerSocket(portNo);
			
			frame.updateServerStatus(false);
			
			// keep server always active
			while(true)
			{
				//Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				frame.updateServerStatus(clientSocket.isConnected());
				
				DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
				String text = inputStream.readUTF();
				String language = inputStream.readUTF();
				String translated = "";
				String bm = "malay";
				String arb = "arab";
				String krn = "korean";
				
				// translate into correct language depends on selected button
				if(language.equals(bm))
				{
					translated = translate.translate2BM(text);
				}
				if(language.equals(arb))
				{
					translated = translate.translate2Arb(text);
				}
				if(language.equals(krn))
				{
					translated = translate.translate2Krn(text);
				}
				

				//Create stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
				
				//send current text back to the client
				outputStream.writeUTF(translated);
				
				
				//close the socket
				outputStream.flush();
				outputStream.close();
				clientSocket.close();
				
				// update request status
				frame.updateRequestStatus("Data sent to the client: " + text + " is translated to " + language);
				frame.updateRequestStatus("Accepted connection to from the " + "client. Total request = " + ++totalRequest);
				
				text = "";
				language = "";
			}
		}
		catch(IOException ioe)
		{
			if(serverSocket != null)
			{
				serverSocket.close();
			}
		}
	}
}