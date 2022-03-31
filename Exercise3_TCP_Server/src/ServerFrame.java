
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
	
	// declare components
	private JLabel lblServerStatus;
	private JTextArea txtRequestStatus;
	
	// set frame size
	private int width = 1000;
	private int height = 500;
	
	// frame set up
	public ServerFrame()
	{
		// frame setting
		this.setLayout(new BorderLayout());
		this.setTitle("APPLICATION WORDS COUNT - SERVER SIDE");
		this.setSize(new Dimension(width, height));  
				
		// put the frame in the center of screen
		this.setLocationRelativeTo(null);
		 
		// default status
		this.lblServerStatus = new JLabel ("None");
		
		// set textbox area
		this.txtRequestStatus  = new JTextArea(20, 70);
				
		// call function to load the component
		loadComponent();
	}

	// panel and label set up
	private JPanel getServerStatusPanel(Font font) 
	{
		// server status label 
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblServer = new JLabel ("Server status: ");
		
		// set font style for label
		lblServer.setFont(font);
		lblServerStatus.setFont(font);
		lblServer.setOpaque(true);
		lblServerStatus.setOpaque(true);

		// put the label into a panel
		panel.add(lblServer);
		panel.add(lblServerStatus);
		
		return panel;
	}
	
	// status panel set up
	private JPanel getRequestStatusPanel () 
	{
		// request status panel
		JPanel panel = new JPanel();

		// default message
		txtRequestStatus.setText("\n > Server is running");
		txtRequestStatus.setEditable(false);
		
		// messahe font style
		txtRequestStatus.setFont(new Font("Courier", Font.PLAIN, 15));

		// add into panel
		panel.add(txtRequestStatus);
		
		return panel;
	}
	
	// arrange the component so GUI looks good
	public void loadComponent() 
	{
		// get server status panel
		Font font = this.getFontStyle();
		JPanel topPanel = this.getServerStatusPanel(font);
		this.add(topPanel, BorderLayout.NORTH);
		
		// get request status panel
		JPanel centrePanel = this.getRequestStatusPanel();		
		this.add(centrePanel, BorderLayout.CENTER);
	}
	
	// update server status
	public void updateServerStatus(boolean flag) 
	{
		String status = "Waiting for connection.";
		if (flag)
		{
			status = "Connected.";
		}
		this.lblServerStatus.setText(status);
		
	}
	
	// update request status
	public void updateRequestStatus (String status)
	{
		// current request status on the panel
		String currentText = this.txtRequestStatus.getText();
		txtRequestStatus.setEditable(true);
		
		// display latest request status
		status += "\n > " + currentText;
		this.txtRequestStatus.setText(status);
		txtRequestStatus.setEditable(false);
	}
	
	// set up font style
	private Font getFontStyle() 
	{
		Font font = new Font (Font.SANS_SERIF, Font.PLAIN, 30);
		return font;
	}
}
		
	