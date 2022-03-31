
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	// declare component
	private JLabel lblServerStatus;
	private JTextArea txtRequestStatus;
	
	// frame size
	private int width = 1000;
	private int height = 500;
	
	public ServerFrame()
	{
		// set up frame
		this.setLayout(new BorderLayout());
		this.setTitle("TCP Application: Server Side");
		this.setSize(new Dimension(width, height));  
				
		// put the frame in the center of screen
		this.setLocationRelativeTo(null);
		 
		// default status of server
		this.lblServerStatus = new JLabel ("None");
		
		// textbox area
		this.txtRequestStatus  = new JTextArea(20, 70);
				
		// call funtion to load all component
		loadComponent();
	}
	
	private JPanel getServerStatusPanel(Font font) 
	{
		// label of the server status
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblServer = new JLabel ("Server status: ");
		
		// set style for label server
		lblServer.setFont(font);
		lblServerStatus.setFont(font);
		lblServer.setOpaque(true);
		lblServerStatus.setOpaque(true);
		
		// put label into a panel
		panel.add(lblServer);
		panel.add(lblServerStatus);
		
		return panel;
	}
	
	private JPanel getRequestStatusPanel () 
	{
		// panel of request status
		JPanel panel = new JPanel();

		// default request status messages
		txtRequestStatus.setText("\n > Server is running");
		txtRequestStatus.setEditable(false);
		
		// set font style for reqeust status
		txtRequestStatus.setFont(new Font("Courier", Font.PLAIN, 15));

		// add into panel
		panel.add(txtRequestStatus);
		
		return panel;
	}
	
	// organize all component
	public void loadComponent() 
	{
		Font font = this.getFontStyle();
		JPanel topPanel = this.getServerStatusPanel(font);
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel centrePanel = this.getRequestStatusPanel();		
		this.add(centrePanel, BorderLayout.CENTER);
	}
	
	public void updateServerStatus(boolean flag) 
	{
		String status = "Waiting for connection.";
		
		if (flag)
		{
			status = "Connected.";
		}
		
		this.lblServerStatus.setText(status);
	}
	
	public void updateRequestStatus (String status)
	{
		// get currect request status
		String currentText = this.txtRequestStatus.getText();
		txtRequestStatus.setEditable(true);
		
		// show latest request status
		status += "\n > " + currentText;
		this.txtRequestStatus.setText(status);
		txtRequestStatus.setEditable(false);
	}
	
	private Font getFontStyle() 
	{
		Font font = new Font (Font.SANS_SERIF, Font.PLAIN, 30);
		return font;
	}
}