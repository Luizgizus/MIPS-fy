import java.util.HashMap;

/**
 * 
 * Classe que representa um formato do tipo I
 * 
 * @author Luiz Henrique Silva Jesus
 *
 */
public class I extends Format{
	private HashMap<String, String> opCodesByInstructionName = new HashMap<String, String>();
	
	private String regOri;
	private String regDest;
	private String imd;
	
	private String mnemoic;
	private String[] params;
	
	public I(String mnemoic, String[] params) {
		super();
		this.setMnemoic(mnemoic);
		this.setParams(params);
		
		if(this.getParamsLength() == 3) {
			String formattedDestinyRegistry = this.getParamsByIndex(0).replace(',', ' ').trim();
			String regDest = this.getRegisterBinaryByRegisterName(formattedDestinyRegistry);
			this.setRegDest(regDest);
			
			String formattedOriginRegister = this.getParamsByIndex(1).replace(',', ' ').trim();
			String regOri = this.getRegisterBinaryByRegisterName(formattedOriginRegister);
			this.setRegOri(regOri);
			
			String formattedImedat = this.getParamsByIndex(2).trim();
			String binaryImd = this.getBinaryOf(Integer.parseInt(formattedImedat));
			this.setImd(binaryImd);		
			
		} else if(this.getParamsLength() == 2) {
			String formattedDestinyRegistry = this.getParamsByIndex(0).replace(',', ' ').trim();
			String regDest = this.getRegisterBinaryByRegisterName(formattedDestinyRegistry);
			this.setRegDest(regDest);
			
			String[] explpodedMemoryPosition = this.getParamsByIndex(1).split("[(]");
			
			String binaryImd = this.getBinaryOf(Integer.parseInt(explpodedMemoryPosition[0]));
			this.setImd(binaryImd);
			
			String formattedRelationalRegister = explpodedMemoryPosition[1].replace(')', ' ').trim();
			String regOri = this.getRegisterBinaryByRegisterName(formattedRelationalRegister);
			this.setRegOri(regOri);
			
		} else {
			System.out.println("parametros incorretos para -> " + this.getMnemoic());
		}
		
		setOpCodesByInstructionName();
	}
	
	/**
	 * função criada para popular o hasmap, de opcodes por nome da instrução sendo que esse hashmap é composto do nome da instrução
	 * como chave e como valor tem o binario respectivo
	 */
	private void setOpCodesByInstructionName() {
		opCodesByInstructionName.put("lw", "100011");
		opCodesByInstructionName.put("sw", "101011");
		
		opCodesByInstructionName.put("addi", "001000");
		opCodesByInstructionName.put("andi", "001100");
		opCodesByInstructionName.put("ori", "001101");
		opCodesByInstructionName.put("slti", "001010");
		opCodesByInstructionName.put("beq", "000100");
		opCodesByInstructionName.put("bne", "000101");
	}
	
	/**
	 * Função criada para encapsular o atributo opCodesByInstructionName
	 * no intuido de não ter acesso direto ao mesmo para que possa ser coletado 
	 * @return retorna o opcode em inario da isntrução isntanciada
	 */
	public String getOpCodesByInstructionName() {
		return this.opCodesByInstructionName.get(this.getMnemoic());
	}

	public String getRegOri() {
		return regOri;
	}

	public void setRegOri(String regOri) {
		this.regOri = regOri;
	}

	public String getRegDest() {
		return regDest;
	}

	public void setRegDest(String regDest) {
		this.regDest = regDest;
	}

	public String getImd() {
		return this.padLeftZeros(imd, 16);
	}

	public void setImd(String imd) {
		this.imd = imd;
	}

	public String getMnemoic() {
		return mnemoic;
	}

	public void setMnemoic(String mnemoic) {
		this.mnemoic = mnemoic;
	}

	public String getParamsByIndex(int index) {
		return params[index];
	}
	
	public int getParamsLength() {
		return params.length;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	/**
	 * função com intuido de coletar e juntar as partes do binario da instrução dada
	 * @return retorno a binario da instrução instanciada
	 */
	public String getMipsBytes() {
		String binaryInstruction = "";
		
		binaryInstruction += this.getOpCodesByInstructionName();
		binaryInstruction += this.getRegOri();
		binaryInstruction += this.getRegDest();
		binaryInstruction += this.getImd();
		
		return binaryInstruction;
	}
	
	
}
