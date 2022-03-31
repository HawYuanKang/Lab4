
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerSetUp 
{
	public void createData()
	{
		// file declare
		String MalayStorage = "Malay.dat";
		String ArabicStorage = "Arabic.dat";
		String KoreanStorage = "Korean.dat";
		
		// streamm declare
		FileOutputStream fileOutputStreamBm; 
		FileOutputStream fileOutputStreamArb; 
		FileOutputStream fileOutputStreamKrn; 
		
		DataOutputStream dataOutputStreamBm; 
		DataOutputStream dataOutputStreamArb; 
		DataOutputStream dataOutputStreamKrn; 
		
		try
		{
			// in malay form
			fileOutputStreamBm = new FileOutputStream(MalayStorage);
			dataOutputStreamBm = new DataOutputStream(fileOutputStreamBm);
			
			dataOutputStreamBm.writeUTF("Selamat pagi");
			dataOutputStreamBm.writeUTF("Selamat malam");
			dataOutputStreamBm.writeUTF("Apa khabar ?");
			dataOutputStreamBm.writeUTF("Terima kasih");
			dataOutputStreamBm.writeUTF("Selamat tinggal");
			dataOutputStreamBm.writeUTF("Ada apa");
			dataOutputStreamBm.flush();
			
			//in arabic form
			fileOutputStreamArb = new FileOutputStream(ArabicStorage);
			dataOutputStreamArb = new DataOutputStream(fileOutputStreamArb);
			
			dataOutputStreamArb.writeUTF("صباح الخير");
			dataOutputStreamArb.writeUTF("طاب مساؤك");
			dataOutputStreamArb.writeUTF("كيف حالك؟");
			dataOutputStreamArb.writeUTF("شكرا لك");
			dataOutputStreamArb.writeUTF("مع السلامة");
			dataOutputStreamArb.writeUTF("ما أخبارك؟");
			dataOutputStreamArb.flush();
			
			//in korean form
			fileOutputStreamKrn = new FileOutputStream(KoreanStorage);
			dataOutputStreamKrn = new DataOutputStream(fileOutputStreamKrn);
			
			dataOutputStreamKrn.writeUTF("좋은 아침");
			dataOutputStreamKrn.writeUTF("안녕히 주무세요");
			dataOutputStreamKrn.writeUTF("어떻게 지내세요 ?");
			dataOutputStreamKrn.writeUTF("감사합니다");
			dataOutputStreamKrn.writeUTF("안녕");
			dataOutputStreamKrn.writeUTF("뭐야 ?");
			dataOutputStreamKrn.flush();
						
			// close everthing
			fileOutputStreamBm.close(); 
			fileOutputStreamArb.close(); 
			fileOutputStreamKrn.close(); 
			
			dataOutputStreamBm.close(); 
			dataOutputStreamArb.close(); 
			dataOutputStreamKrn.close(); 
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
