
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClientFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	// declare the components
	private JLabel statusTittle;
	private JLabel status;
	private JTextArea textToCount;
	private JButton btn;
	private JScrollPane scroll;
	
	// declare frame resolution
	private int width = 800;
	private int height = 400;
	
	public ClientFrame()
	{
		// default frame build
		this.setLayout(new BorderLayout());
		this.setTitle("APPLICATION WORDS COUNT - CLIENT SIDE");
		this.setSize(new Dimension(width, height));
		
		// everytime debug frame start on center of the screen
        this.setLocationRelativeTo(null);
        
		// default title and status
		this.statusTittle = new JLabel ("Words Counter Server Connection: ");
		this.status = new JLabel ("None");
		
		// textbox area
		this.textToCount  = new JTextArea(3, 10);
		scroll = new JScrollPane(textToCount);
	    
		// button to count words 
		this.btn = new JButton("COUNT");
		btn.addActionListener(this);
		
		// put all components into frame
		loadComponent();
	}
	
	// update the connection status
	public void updateConnectionStatus (boolean connStatus) 
	{
		// default status
		String status = "No connection to server.";
		
		// validation of connection status
		if (connStatus)
		{
			status = "Connection has established.";
		}
			
		// update the status text
		this.status.setText(status);
	}
	
	// get the text of connection status
	private JPanel getConnectionStatusPanel(Font font)
	{
		JPanel panel = new JPanel();
		statusTittle.setFont(font);
		status.setFont(font);
		statusTittle.setOpaque(true);
		status.setOpaque(true);

		panel.add(statusTittle);
		panel.add(status);

		return panel;
	}
	
	// get the input of textbox area 
	private JPanel getClientTextArea(Font font)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		textToCount.setFont(font);
		textToCount.setOpaque(true);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}
	
	// get the reaction of the button push
	private JPanel getClientButton(Font font)
	{
		JPanel panel = new JPanel();
		btn.setFont(font);
		btn.setOpaque(true);
		panel.add(btn);
		return panel;
	}

	// put all components into frame
	private void loadComponent() 
	{
		// get font style
		Font font = this.getFontStyle();
		
		// get status panel and put into frame
		JPanel northPanel = this.getConnectionStatusPanel(font);		
		this.add(northPanel, BorderLayout.NORTH);
		
		// get textbox area panel and put into frame
		JPanel center = getClientTextArea(font);
		this.add(center, BorderLayout.CENTER);
		
		// get button and put into frame
		JPanel southPanel = getClientButton(font);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	// set up the font style
	private Font getFontStyle() 
	{
		Font font = new Font ("Serif", Font.PLAIN, 30);
		return font;
	}

	// button function when clicked
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btn)
		{
			try
			{
				Socket socket = new Socket(InetAddress.getLocalHost(), 4223);
				
				// update connection status 
				updateConnectionStatus(socket.isConnected());
				
				// read data from network
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// get the output stream from the socket
		        OutputStream outputStream = socket.getOutputStream();
		        
				// create a data output stream to send data through it
		        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				  
		        // write sentence to count word
		        String txt = textToCount.getText();
		        dataOutputStream.writeUTF(txt);
		        
				// display words count result
				String counted = bufferedReader.readLine();
				System.out.print(counted);
				JOptionPane.showMessageDialog(null, "Total Words Counted Is : " + counted);
				
				// close everything
				dataOutputStream.flush();
		        dataOutputStream.close();
				bufferedReader.close();
				socket.close();
			}
			catch(Exception error)
			{
				error.printStackTrace();
			}
		}
	}
}
