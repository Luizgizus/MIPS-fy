import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

/**
 * 
 * Classe com intuito simples de generalizar matodos para a manipulação de arquivos
 * 
 * @author Luiz Henrique Silva Jesus
 */
public class FileUtil {
	public FileUtil(){}
	
	/**
	 * 
	 * Metodo para leitura de dados em arquivos passiveis de acesso.
	 * 
	 * @param path parametro que indica o caminho de onde deve ser lido o arquivo
	 * @return retorna um conjunto de strings sendo cada string uma linha do arquivo lido
	 * @throws IOException para casos de tratativas de erros de abertura do arquivo por inumeros motivos
	 */
	public static String[] readFrom(String path) throws IOException{
		RandomAccessFile rad = new RandomAccessFile(path, "rw");
		byte[] byteFile = new byte[(int) rad.length()];
		String strigFile;
		
		rad.readFully(byteFile);
		strigFile = new String(byteFile, "utf-8");
		
		rad.close();
		return strigFile.split("\n");
	}
	
	/**
	 * 
	 * Metodo para escrita de dados em arquivos passiveis de acesso.
	 * 
	 * @param path parametro que indica o caminho de onde deve ser escrito os dados
	 * @param data os dados que devem ser escrito o caminho indicado
	 * @throws IOExceptions para casos de tratativas de erros de abertura do arquivo por inumeros motivos
	 */
	public static void writeOn(String path, String data) throws IOException{
		RandomAccessFile rad = new RandomAccessFile(path, "rw");
		
		rad.seek(rad.length());
		rad.writeBytes(data + "\n");
		
		rad.close();
	}
}
