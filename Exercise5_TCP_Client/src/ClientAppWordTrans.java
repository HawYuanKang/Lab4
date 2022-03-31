
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientAppWordTrans 
{
	// declare client frame class so later can call it's function
	static ClientFrame client = new ClientFrame();
	
	public static void main(String[] args) 
	{	
		// display the GUI
		client.setVisible(true);
	}
	
	// when button is clicked perform function
	static public void btnPressed()
	{
		try
		{		
			// connect port
			Socket socket = new Socket(InetAddress.getLocalHost(), 4220);
			client.updateConnectionStatus(socket.isConnected());
			
			// generate the data to server side
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			String textinput = client.getText();
			String language = client.getLanguage();
			
			// get the data from server side
			DataOutputStream dataoutputStream = new DataOutputStream(socket.getOutputStream());
			
			// translate the messages when the button is clicked
			if(client.ispressed() == true)
			{
				dataoutputStream.writeUTF(textinput);
				dataoutputStream.writeUTF(language);
				
				String translated = datainputstream.readUTF();
				
				client.setAnsLbl(translated);
				client.updatebtn(false);
			}
			
			// close everthing
			datainputstream.close();
			dataoutputStream.close();
			socket.close();
			
			// clear variable value
			textinput = "";
			language = "";
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		} 
	}
}
