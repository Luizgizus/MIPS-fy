import java.awt.image.SampleModel;
import java.util.HashMap;

public class R extends Format {
	private final String opCode = "000000";
	private HashMap<String, String> functCodesByInstructionName = new HashMap<String, String>();

	private String regOri1 = "00000";
	private String regOri2 = "00000";
	private String regDest = "00000";
	private String shamt = "00000";

	private String oriRegOri1;
	private String oriRegOri2;
	private String oriRegDest;
	private String oriShamt;

	private String mnemoic;
	private String[] params;

	public R(String mnemoic, String[] params) {
		super();
		this.setMnemoic(mnemoic);
		this.setParams(params);

		if (this.getParamsLength() == 0 && this.getMnemoic().equals("nop")) {

		} else if (this.getParamsLength() == 1) {
			String binryRegOri1 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(0).trim());
			this.setRegOri1(binryRegOri1);
			this.oriRegOri1 = this.getParamByIndex(0).trim();
		} else if (this.getParamsLength() == 2) {
			String binryRegOri1 = this
					.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegOri1(binryRegOri1);
			this.oriRegOri1 = this.getParamByIndex(0).replace(',', ' ').trim();

			String binryRegOri2 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(1).trim());
			this.setRegOri2(binryRegOri2);
			this.oriRegOri2 = this.getParamByIndex(1).trim();
		} else if (this.getParamsLength() == 3 && !this.getMnemoic().equals("sll")
				&& !this.getMnemoic().equals("srl")) {
			String binryRegOri1 = this
					.getRegisterBinaryByRegisterName(this.getParamByIndex(1).replace(',', ' ').trim());
			this.setRegOri1(binryRegOri1);
			this.oriRegOri1 = this.getParamByIndex(1).replace(',', ' ').trim();

			String binryRegOri2 = this.getRegisterBinaryByRegisterName(this.getParamByIndex(2).trim());
			this.setRegOri2(binryRegOri2);
			this.oriRegOri2 = this.getParamByIndex(2).trim();

			String binryRegDest = this
					.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegDest(binryRegDest);
			this.oriRegDest = this.getParamByIndex(0).replace(',', ' ').trim();
		} else if (this.getParamsLength() == 3
				&& (this.getMnemoic().equals("sll") || this.getMnemoic().equals("srl"))) {
			String binryRegOri2 = this
					.getRegisterBinaryByRegisterName(this.getParamByIndex(1).replace(',', ' ').trim());
			this.setRegOri2(binryRegOri2);
			this.oriRegOri2 = this.getParamByIndex(1).replace(',', ' ').trim();

			String binryRegDest = this
					.getRegisterBinaryByRegisterName(this.getParamByIndex(0).replace(',', ' ').trim());
			this.setRegDest(binryRegDest);
			this.oriRegDest = this.getParamByIndex(0).replace(',', ' ').trim();

			String binaryDeslocation = this.getBinaryOf(Integer.parseInt(this.getParamByIndex(2).trim()));
			this.setShamt(this.padLeftZeros(binaryDeslocation, 5));
			this.oriShamt = this.getParamByIndex(2).trim();
		} else {
			System.out.println("parametros incorretos para -> " + this.getMnemoic());
		}

		setFunctCodesByInstructionName();
	}

	/**
	 * função criada para popular o hasmap, de fucts por nome da instrução sendo que
	 * esse hashmap é composto do nome da instrução como chave e como valor tem o
	 * binario da funnct da respectiva instrução
	 */
	private void setFunctCodesByInstructionName() {
		functCodesByInstructionName.put("add", "100000");
		functCodesByInstructionName.put("sub", "100010");
		functCodesByInstructionName.put("and", "100100");
		functCodesByInstructionName.put("or", "100101");
		functCodesByInstructionName.put("nor", "100111");
		functCodesByInstructionName.put("xor", "100110");
		functCodesByInstructionName.put("slt", "101010");

		functCodesByInstructionName.put("sll", "000000");
		functCodesByInstructionName.put("srl", "000010");

		functCodesByInstructionName.put("mult", "011000");
		functCodesByInstructionName.put("div", "011010");

		functCodesByInstructionName.put("jr", "001000");
	}

	/**
	 * Função criada para encapsular o atributo functCodesByInstructionName no
	 * intuido de não ter acesso direto ao mesmo para que possa ser coletado
	 * 
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
	 * função com intuido de identificar se a intrução enviada acessa ou grava na
	 * memória a memória e se ela muda algum registrador e efetivamete altera os
	 * valores dos registradores que ela usa assim como as operações feitas e os
	 * valores na memóra em caso de escrita ou leitura
	 * 
	 */
	private void checkChages() {
		if (this.getMnemoic().equals("add")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			String value = "" + (Integer.parseInt(register1) + Integer.parseInt(register2));

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("sub")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			String value = "" + (Integer.parseInt(register1) - Integer.parseInt(register2));

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("and")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			boolean valueBoolean = (Boolean.parseBoolean(register1) && Boolean.parseBoolean(register2));
			String value = null;

			if (valueBoolean) {
				value = "1";
			} else {
				value = "0";
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("or")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			boolean valueBoolean = (Boolean.parseBoolean(register1) || Boolean.parseBoolean(register2));
			String value = null;

			if (valueBoolean) {
				value = "1";
			} else {
				value = "0";
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("nor")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			boolean valueBoolean = (Boolean.parseBoolean(register1) || Boolean.parseBoolean(register2));
			String value = null;

			if (valueBoolean) {
				value = "1";
			} else {
				value = "0";
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("xor")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			boolean valueBoolean = (Boolean.parseBoolean(register1) ^ Boolean.parseBoolean(register2));
			String value = null;

			if (valueBoolean) {
				value = "1";
			} else {
				value = "0";
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("slt")) {
			int register1 = Integer.parseInt(Register.getRegistersValueByRegister(this.oriRegOri1));
			int register2 = Integer.parseInt(Register.getRegistersValueByRegister(this.oriRegOri2));

			String value = null;

			if (register1 <= register2) {
				value = "1";
			} else {
				value = "0";
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("mult")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			String value = "" + (Integer.parseInt(register1) * Integer.parseInt(register2));

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		} else if (this.getMnemoic().equals("div")) {
			String register1 = Register.getRegistersValueByRegister(this.oriRegOri1);
			String register2 = Register.getRegistersValueByRegister(this.oriRegOri2);

			String value = null;
			if (register2.equals("0")) {
				value = "0";
			} else {
				value = "" + (Integer.parseInt(register1) / Integer.parseInt(register2));
			}

			String registerD = this.oriRegDest;
			Register.setValueOnRegister(registerD, value);

		}
	}

	/**
	 * função com intuido de coletar e juntar as partes do binario da instrução dada
	 * 
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

		checkChages();

		return binaryInstruction;
	}

}
