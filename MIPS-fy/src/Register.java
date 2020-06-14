import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 
 * Classe feita para simular a inicialização, o input e output de dados detro dos registradores
 * 
 * @author LHSJ
 *
 */
public class Register {
	public static HashMap<String, String> registersValues = new HashMap<String, String>();

	public static void setRegistersValues() {
		// zero
		registersValues.put("zero", "0x00000000");

		// at
		registersValues.put("at", "0x00000000");

		// v0-v1
		registersValues.put("v0", "0x00000000");
		registersValues.put("v1", "0x00000000");

		// a0-a3
		registersValues.put("a0", "0x00000000");
		registersValues.put("a1", "0x00000000");
		registersValues.put("a2", "0x00000000");
		registersValues.put("a3", "0x00000000");

		// t0-t7
		registersValues.put("t0", "0x00000000");
		registersValues.put("t1", "0x00000000");
		registersValues.put("t2", "0x00000000");
		registersValues.put("t3", "0x00000000");
		registersValues.put("t4", "0x00000000");
		registersValues.put("t5", "0x00000000");
		registersValues.put("t6", "0x00000000");
		registersValues.put("t7", "0x00000000");

		// s0-s7
		registersValues.put("s0", "0x00000000");
		registersValues.put("s1", "0x00000000");
		registersValues.put("s2", "0x00000000");
		registersValues.put("s3", "0x00000000");
		registersValues.put("s4", "0x00000000");
		registersValues.put("s5", "0x00000000");
		registersValues.put("s6", "0x00000000");
		registersValues.put("s7", "0x00000000");

		// t8-t9
		registersValues.put("t8", "0x00000000");
		registersValues.put("t9", "0x00000000");

		// k1-k2
		registersValues.put("k1", "0x00000000");
		registersValues.put("k2", "0x00000000");

		// gp
		registersValues.put("gp", "0x00000000");

		// sp
		registersValues.put("sp", "0x00000000");

		// fp
		registersValues.put("fp", "0x00000000");

		// ra
		registersValues.put("ra", "0x00000000");
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
	 * Metodo que somula a leitura de dados de um registrador desejado tranformando os valors de binario para decimal
	 * 
	 * @param register - registrador desejado
	 */
	public static String getRegistersValueByRegister(String register) {
		String binaryValue = Register.registersValues.get(register).split("x")[1]; 
		return "" + Integer.parseInt(binaryValue, 2);
	}
	
	/**
	 * 
	 * Metodo que simula a inserção de dados dentro do um registrador desejado, tranformando os valores de decimal em binário e inserindo na
	 * registrador desejado
	 * 
	 * @param register - registrador desejada
	 * @param value - valor
	 */
	public static void setValueOnRegister(String register, String value) {
		String valueBinary = Integer.toBinaryString(Integer.parseInt(value));
		if(register != null) {
			Register.registersValues.put(
					register,
					"0x" + Register.padLeftZeros(valueBinary, 8)
				);
		}
	}
}
