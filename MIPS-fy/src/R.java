import java.util.HashMap;

public class R extends Format{
	private final String opCode = "000000";
	private HashMap<String, String> functCodesByInstructionName = new HashMap<String, String>();
	
	private String regOri1 = "00000";
	private String regOri2 = "00000";
	private String regDest = "00000";
	private String shamt = "00000";
	
	private String mnemoic;
	private String[] params;
	
	public R(String mnemoic, String[] params) {
		super();
		this.setMnemoic(mnemoic);
		this.setParams(params);
		
		if(this.getParamsLength() == 0 && this.getMnemoic().equals("nop")) {
			
		} else if(this.getParamsLength() == 1) {
			String binryRegOri1 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(0).trim());
			this.setRegOri1(binryRegOri1);
		} else if (this.getParamsLength() == 2){
			String binryRegOri1 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegOri1(binryRegOri1);
			
			String binryRegOri2 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(1).trim());
			this.setRegOri2(binryRegOri2);
		} else if(this.getParamsLength() == 3 && !this.getMnemoic().equals("sll")) {
			String binryRegOri1 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(1).replace(',', ' ').trim());
			this.setRegOri1(binryRegOri1);
			
			String binryRegOri2 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(2).trim());
			this.setRegOri2(binryRegOri2);
			
			String binryRegDest = this.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegDest(binryRegDest);
		} else if(this.getParamsLength() == 3 && this.getMnemoic().equals("sll")) {			
			String binryRegOri2 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(1).replace(',', ' ').trim());
			this.setRegOri2(binryRegOri2);
			
			String binryRegDest = this.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegDest(binryRegDest);
			
			String binaryDeslocation = this.getBinaryOf(Integer.parseInt(this.getParamByIndex(2).trim()));
			this.setShamt(this.padLeftZeros(binaryDeslocation, 5));
		} else {
			System.out.println("parametros incorretos para -> " + this.getMnemoic());
		}
		
		setFunctCodesByInstructionName();
	}
	
	
	/**
	 * função criada para popular o hasmap, de fucts por nome da instrução sendo que esse hashmap é composto do nome da instrução
	 * como chave e como valor tem o binario da funnct da respectiva instrução
	 */
	private void setFunctCodesByInstructionName() {
		functCodesByInstructionName.put("add", "100000");
		functCodesByInstructionName.put("sub", "100010");
		functCodesByInstructionName.put("and", "100100");
		functCodesByInstructionName.put("or", "100101");
		functCodesByInstructionName.put("nor", "100111");
		functCodesByInstructionName.put("xor", "100110");
		functCodesByInstructionName.put("nor", "100111");
		functCodesByInstructionName.put("slt", "101010");
		
		functCodesByInstructionName.put("sll", "000000");
		functCodesByInstructionName.put("srl", "000010");
		
		functCodesByInstructionName.put("mult", "011000");
		functCodesByInstructionName.put("div", "011010");
		
		functCodesByInstructionName.put("jr", "001000");
	}
	
	/**
	 * Função criada para encapsular o atributo functCodesByInstructionName
	 * no intuido de não ter acesso direto ao mesmo para que possa ser coletado 
	 * @return retorna o funct em binario da instrução isntanciada
	 */
	public String getFunctCodesByInstructionName() {
		return this.functCodesByInstructionName.getOrDefault(this.getMnemoic(), "000000");
	}

	public String getRegOri1() {
		return regOri1;
	}

	public void setRegOri1(String regOri1) {
		this.regOri1 = regOri1;
	}

	public String getRegOri2() {
		return regOri2;
	}

	public void setRegOri2(String regOri2) {
		this.regOri2 = regOri2;
	}

	public String getRegDest() {
		return regDest;
	}

	public void setRegDest(String regDest) {
		this.regDest = regDest;
	}

	public String getShamt() {
		return shamt;
	}

	public void setShamt(String shamt) {
		this.shamt = shamt;
	}

	public String getOpCode() {
		return opCode;
	}

	public String getMnemoic() {
		return mnemoic;
	}

	public void setMnemoic(String mnemoic) {
		this.mnemoic = mnemoic;
	}

	public int getParamsLength() {
		return params.length;
	}
	
	public String getParamByIndex(int index) {
		return params[index];
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
		
		binaryInstruction += this.getOpCode();
		binaryInstruction += this.getRegOri1();
		binaryInstruction += this.getRegOri2();
		binaryInstruction += this.getRegDest();
		binaryInstruction += this.getShamt();
		binaryInstruction += this.getFunctCodesByInstructionName();
		
		return binaryInstruction;
	}

}
