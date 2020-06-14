import java.io.IOException;

/**
 * 
 * Classe principal do projeto onde ocorre toda execução
 * nela os metodos foram extraidos ao meximo para facilitar a leitura
 * 
 * @author Luiz Henrique Silva Jesus
 *
 */
public class Mips32Decode {
	private static Format f = new Format();
	
	private static String fileIn;
	private static String instructionsFile = "instructions.out";
	private static String registersFile = "registers.out.";
	private static String memoryFile = "memory.out";
	
	/**
	 * 
	 * @param args Argumentos passados por linha de comando para a execução do programa nesse aso deve haver 
	 * duas posições sendo a primeira o arquivo de entrada e a segunda o arquivo de saida
	 * 
	 * @throws Exception criado para trataivas de erro que pudessem ocorrer durnate a execuão do codigo
	 */
	public static void main(String[] args) throws Exception {
		fileIn = args[0];
		String[] fileLines = FileUtil.readFrom(fileIn);
		Memory.setMemory();
		Register.setRegistersValues();
		getMipsConvertion(fileLines);
		
		Memory.memory.forEach((k,v)->{
			try {
				FileUtil.writeOn(memoryFile, k + " " + v);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		Register.registersValues.forEach((k,v)->{
			try {
				FileUtil.writeOn(registersFile, k + " " + v);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * 
	 * função criada na extração do metodo 'getMipsConvertion' sendo sua função de isntanciar corretamente cada tipo de registrador por meio
	 * do nome da instrução e retornar o cojunto de bites que é composto pela instrução dada
	 *
	 * @param format parametro que indica qual o formato da instução possivei valores: {"R", "I", "J", null}
	 * @param instructionName parametro que informa o nome da instrução
	 * @param params parametro que informa os parametros subsequentes da linda da instução, sem o nome somente os parametros
	 * @return essa função retorna um conjunto de bits que compõe a instrução eviada
	 * @throws Exception criado para trataivas de erro que pudessem ocorrer durnate a execuão do codigo
	 */
	private static String getBytesStringByFormat(String format, String instructionName, String[] params) throws Exception {
		String bytesString = null;
		
		if(format == null) {
			System.out.println("instrução não parametrizada -> " + instructionName);
			throw new Exception("instrução não parametrizada");
		} else if(format.equals("R")) {
			R r = new R(instructionName, params);
			bytesString = r.getMipsBytes();
		} else if(format.equals("I")) {
			I i = new I(instructionName, params);
			bytesString = i.getMipsBytes();
		} else if(format.equals("J")) {
			J j = new J(instructionName, params[0]);
			bytesString = j.getMipsBytes();
		}
		
		return bytesString;
	}
	
	/**
	 * 
	 * Função criada na extrção de metodo da função "getMipsConvertion" que tem como obejtivo
	 * coletar os parametros da linha de uma instrução do arquivo e retornar somente a instrução sem o mnemoico
	 * 
	 * @param fileLine
	 * @return retora um array de strings que seria a instrução sem o mnemoico
	 */
	private static String[] getParamsFromLines(String[] fileLine) {
		String[] params = new String[fileLine.length - 1];
		for (int i = 1; i < fileLine.length; i++) {
			params[i-1] = fileLine[i];
		}
		
		return params;
	}
	
	/**
	 * 
	 * função criada apartir da extração de metodo da fução "main"
	 * que tem por objetivo iterar as linhas indentificado qual é o formato da instrução de cada linha
	 * e coletando o binario daquila linha
	 * 
	 * @param fileLines parametro com todas as linhas da entrada
	 * @throws Exception criado para trataivas de erro que pudessem ocorrer durnate a execuão do codigo
	 */
	private static void getMipsConvertion(String[] fileLines) throws Exception {
		for (int idx = 0; idx < fileLines.length; idx++) {
			try {
				String[] lineParams = fileLines[idx].split(" ");
				String instructionName = lineParams[0].toLowerCase().trim();
				String[] params = getParamsFromLines(lineParams);
				
				String format = f.getFormatByIntructionName(instructionName);
				
				String bytesString = getBytesStringByFormat(format, instructionName, params);
				FileUtil.writeOn(instructionsFile, bytesString);
			}
			catch (Exception e) {}
		}
	}

}
