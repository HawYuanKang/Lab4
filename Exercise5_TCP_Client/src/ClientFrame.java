
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientFrame  extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	// declare all components
	private JLabel tittle, anslbl;
	private JButton bm, arb, krn;
	
	String[] choices = { "Good Morning","Good Night", "How Are You?","Thank You","Goodbye","What's Up?"};
	private JComboBox<String> greet=new JComboBox<String>(choices);

	
	// frame size
	private int width = 750;
	private int height = 200;
	
	// initial value of text
	private String textinput = "";
	private String language = "";
	
	// initial status of button
	private boolean pressed = false;
	
	public ClientFrame()
	{
		// set up frame
		this.setLayout(new BorderLayout());
		this.setTitle("WORDS TRANSLATOR");
		this.setSize(new Dimension(width, height));
		
		//put frame to center of screen
		this.setLocationRelativeTo(null);
		
		// default title
		this.tittle = new JLabel("Type to translate: ");
		this.anslbl = new JLabel("");
		
		// combobox area
		greet.setVisible(true);
		
		// text of the buttons
		this.bm = new JButton("Malay");
		this.arb = new JButton("Arabic");
		this.krn = new JButton("Korean");
		
		// perform one of these function when condition is true
		bm.addActionListener(this);
		arb.addActionListener(this);
		krn.addActionListener(this);
		
		// call function to load all component
		loadForm();
	}
	
	private JPanel topPanel(Font font)
	{
		JPanel panel = new JPanel();
		tittle.setFont(font);
		tittle.setOpaque(true);
		greet.setFont(font);
		greet.setOpaque(true);
		panel.add(tittle);
		panel.add(greet);
		return panel;
	}
	
	private JPanel centerPanel(Font font)
	{
		JPanel panel = new JPanel();
		bm.setFont(font);
		arb.setFont(font);
		krn.setFont(font);
		bm.setOpaque(true);
		arb.setOpaque(true);
		krn.setOpaque(true);
		panel.add(bm);
		panel.add(arb);
		panel.add(krn);
		return panel;
	}
	
	private JPanel bottomPanel(Font font)
	{
		JPanel panel = new JPanel();
		anslbl.setOpaque(true);
		anslbl.setFont(font);
		panel.add(anslbl);
		return panel;
	}
	
	private Font getFontStyle() 
	{
		Font font = new Font ("Serif", Font.PLAIN, 30);
		return font;
	}

	// loadcomponent
	private void loadForm()
	{
		Font font = this.getFontStyle();
		
		JPanel top = this.topPanel(font);
		this.add(top, BorderLayout.NORTH);
		
		JPanel center = this.centerPanel(font);
		this.add(center, BorderLayout.CENTER);
		
		JPanel btm = this.bottomPanel(font);
		this.add(btm, BorderLayout.SOUTH);
	}
	
	
	public void setAnsLbl(String translated)
	{
		anslbl.setText(translated);
	}
	
	public void updateConnectionStatus (boolean connStatus) 
	{
		// default status
		this.setTitle("TCP Translator: No connection to server.");
		
		// validation for connection status
		if (connStatus)
		{
			this.setTitle("TCP Translator: Connection has established.");
		}
	}
	
	public String getLanguage() 
	{
		return this.language;
	}
	
	public String getText() 
	{
		return this.textinput;
	}
	
	private void setFonttoArabic()
	{
		anslbl.setFont(new Font("Arabic",Font.PLAIN,30));
	}
	
	private void setFonttoKorean()
	{
		anslbl.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
	}
	
	public boolean ispressed()
	{
		return pressed;
	}
	
	public void updatebtn(boolean b)
	{
		pressed = b;
	}
	
	// when button is clicked perform the function
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		// check if the clicked button is Bahasa Melayu or not
		if(e.getSource()== bm)
		{
			updatebtn(true);
			// get the selected item of the combobox
			textinput=greet.getSelectedItem().toString();
			language = "malay";	
			anslbl.setFont(getFontStyle());	
			ClientAppWordTrans.btnPressed();
		}
		// check if the clicked button is Arabic or not
		else if(e.getSource()== arb)
		{
			updatebtn(true);
			textinput=greet.getSelectedItem().toString();
			language = "arab";
			setFonttoArabic();
			ClientAppWordTrans.btnPressed();
		}
		// check if the clicked button is Korean or not
		else
		{
			updatebtn(true);
			textinput=greet.getSelectedItem().toString();
			language = "korean";
			setFonttoKorean();
			ClientAppWordTrans.btnPressed();
		}	
	}
}