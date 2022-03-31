
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAppCountWord 
{
	public static void main(String[] args) throws IOException 
	{
		ServerFrame server = new ServerFrame();
		server.setVisible(true);
		
		// port bind
		int portNo = 4223;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		// declare class from servercount so later can use it
		ServerCount words = new ServerCount();
		
		// counter for the number of request connection
		int totalRequest = 0;
		
		// loop the server so it is always activated
		while (true) 
		{
			// accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// message to indicate server is active
			server.updateServerStatus(clientSocket.isConnected());
			
			// get the input stream from the connected socket
	        InputStream inputStream = clientSocket.getInputStream();
	        
	        // create a DataInputStream and read data from it.
	        DataInputStream dataInputStream = new DataInputStream(inputStream);
	        String txt = dataInputStream.readUTF();
	        String wordsCounted = words.Counter(txt);
	        
			// create stream to write data on the network
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			// return current data to client
			outputStream.writeBytes(wordsCounted);
			
			// close socket
			clientSocket.close();
		
			// update the request status
			server.updateRequestStatus("Data sent to the client: " + wordsCounted + " TOTAL WORDS COUNTED");
			server.updateRequestStatus("Accepted connection to from the " + "client. Total request = " + ++totalRequest);
		}
	}
}