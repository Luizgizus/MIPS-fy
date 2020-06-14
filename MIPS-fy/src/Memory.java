import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 
 * Classe feita para simular a inicialização, o input e output de dados detro de uma memoria
 * 
 * @author LHSJ
 *
 */
public class Memory {
	public static HashMap<String, String> memory = new HashMap<String, String>();

	/**
	 * Metodo de inicializaçao da memória zerando ela toda ao inicio da execução
	 */
	public static void setMemory() {
		memory.put("0x000", "0x00000000");
		memory.put("0x010", "0x00000000");
		memory.put("0x020", "0x00000000");
		memory.put("0x030", "0x00000000");
		memory.put("0x040", "0x00000000");
		memory.put("0x050", "0x00000000");
		memory.put("0x060", "0x00000000");
		memory.put("0x070", "0x00000000");
		memory.put("0x080", "0x00000000");
		memory.put("0x090", "0x00000000");
		memory.put("0x0a0", "0x00000000");
		memory.put("0x0b0", "0x00000000");
		memory.put("0x0c0", "0x00000000");
		memory.put("0x0d0", "0x00000000");
		memory.put("0x0e0", "0x00000000");
		memory.put("0x0f0", "0x00000000");
		memory.put("0x100", "0x00000000");
		memory.put("0x110", "0x00000000");
		memory.put("0x120", "0x00000000");
		memory.put("0x130", "0x00000000");
		memory.put("0x140", "0x00000000");
		memory.put("0x150", "0x00000000");
		memory.put("0x160", "0x00000000");
		memory.put("0x170", "0x00000000");
		memory.put("0x180", "0x00000000");
		memory.put("0x190", "0x00000000");
		memory.put("0x1a0", "0x00000000");
		memory.put("0x1b0", "0x00000000");
		memory.put("0x1c0", "0x00000000");
		memory.put("0x1d0", "0x00000000");
		memory.put("0x1e0", "0x00000000");
		memory.put("0x1f0", "0x00000000");
	}
	
	/**
	 * 
	 * Metodo para inserir zeros apartir do tamaho de uma sting sendo que se ela tiver tamanho 4 e o taamho real for 10
	 * ele preenche o resto das casas com 0
	 * 
	 * @param inputString - a string que sejese ser preenchida
	 * @param length - a quatidade de casas
	 * @return a string preenchida
	 */
	public static String padLeftZeros(String inputString, int length) {
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inputString.length()) {
			sb.append('0');
		}
		sb.append(inputString);

		return sb.toString();
	}

	/**
	 * 
	 * Metodo que simula a inserção de dados dentro da memoria, tranformando os valores de decimal em binário e inserindo na
	 * posição desejada
	 * 
	 * @param position - posição desejada
	 * @param value - valor
	 */
	public static void setOnMemory(String position, String value) {
		String hexadecimal = Integer.toHexString(Integer.parseInt(position));
		String binary = Integer.toBinaryString(Integer.parseInt(value));
		Memory.memory.put(
			"0x" + Memory.padLeftZeros(hexadecimal, 2) + "0",
			"0x" + Memory.padLeftZeros(binary, 8)
		);
		
		return;
	}
	
	/**
	 * 
	 * Metodo que simula a leitura de dados de detro da memoria, a posição desejada tranformando os valors de binario para decimal
	 * 
	 * @param position - posição desejada
	 */
	public static String getOfMemory(String position) {
		String valueOfMemory = null;
		String hexadecimal = Integer.toHexString(Integer.parseInt(position));
		String value = Memory.memory.get("0x" + Memory.padLeftZeros(hexadecimal, 2) + "0");
		
		if(value != null) {
			String explodedValueMemory = value.split("x")[1];
			valueOfMemory = "" + Integer.parseInt(explodedValueMemory,2);
		}
		
		return valueOfMemory;
	}
}
