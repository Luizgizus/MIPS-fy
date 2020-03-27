import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

public class FileUtil {
	public FileUtil(){}
	
	public static String[] readFrom(String path) throws IOException{
		RandomAccessFile rad = new RandomAccessFile(path, "rw");
		byte[] byteFile = new byte[(int) rad.length()];
		String strigFile;
		
		rad.readFully(byteFile);
		strigFile = new String(byteFile, "utf-8");
		
		rad.close();
		return strigFile.split("\n");
	}
	
	public static void writeOn(String path, String data) throws IOException{
		RandomAccessFile rad = new RandomAccessFile(path, "rw");
		
		rad.seek(rad.length());
		rad.writeBytes(data + "\n");
		
		rad.close();
	}
}
