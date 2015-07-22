package SFTP;
import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.OutputStream; 
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 
public class SFTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String SFTPHOST = "ppsftp.eu.gmacfs.com"; 
		int SFTPPORT = 22; 
		String SFTPUSER = "jcapsusr_china222"; 
		String SFTPPASS = "test@123";
		String SFTPWORKINGDIR = "/Dowjones/Dowjones_Feed/Download/TEMP/"; 
		Session session = null; 
		Channel channel = null;
		ChannelSftp channelSftp = null;
		
		try{ 
			System.out.println("inside function");
			JSch jsch = new JSch(); 
			session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT); 
			session.setPassword(SFTPPASS); 
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no"); 
			session.setConfig(config); session.connect(); 
			channel = session.openChannel("sftp"); 
			channel.connect(); 
			System.out.println("Connection Success "+channel.isConnected() );
			
			channelSftp = (ChannelSftp)channel; 
			channelSftp.cd(SFTPWORKINGDIR);
			System.out.println("Connecting directory");
			byte[] buffer = new byte[1024]; 
			BufferedInputStream bis = new BufferedInputStream(channelSftp.get("test.txt"));
			File newFile = new File("D:/Test.txt");
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int readCount; 
			//System.out.println("Getting: " + theLine); 
			while( (readCount = bis.read(buffer)) > 0) { 
				System.out.println("Writing: " );
				bos.write(buffer, 0, readCount);
			} 
			bis.close();
			bos.close();
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		}
	}

}
