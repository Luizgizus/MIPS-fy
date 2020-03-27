import java.util.HashMap;

/**
 * 
 * Classe que representa um formato do tipo J
 * 
 * @author Luiz Henrique Silva Jesus
 *
 */
public class J extends Format{
	private HashMap<String, String> opCodesByInstructionName = new HashMap<String, String>();
	
	private int addr;
	private String mnemoic;
	
	public J(String mnemoic, String addr) {
		super();
		this.setMnemoic(mnemoic);
		
		System.out.println(addr);
		
		this.setAddr(addr);
		
		setOpCodesByInstructionName();
	}
	
	/**
	 * fun��o criada para popular o hasmap, de opcodes por nome da instru��o sendo que esse hashmap � composto do nome da instru��o
	 * como chave e como valor tem o binario respectivo
	 */
	private void setOpCodesByInstructionName() {
		opCodesByInstructionName.put("j", "000010");
		opCodesByInstructionName.put("jal", "000011");
	}

	/**
	 * Fun��o criada para encapsular o atributo opCodesByInstructionName
	 * no intuido de n�o ter acesso direto ao mesmo para que possa ser coletado 
	 * @return retorna o opcode em inario da isntru��o isntanciada
	 */
	public String getOpCodesByInstructionName() {
		return this.opCodesByInstructionName.get(this.getMnemoic());
	}

	private String getAddress() {
		String addressBinary = this.getBinaryOf(this.getAddr());
		
		return this.padLeftZeros(addressBinary, 26);
	}

	private int getAddr() {
		return this.addr;
	}
	
	private void setAddr(String addr) {
		String formattedAddr = addr.trim();
		int intAddr = Integer.parseInt(formattedAddr);
		this.addr = intAddr;
	}

	private String getMnemoic() {
		return mnemoic;
	}

	private void setMnemoic(String mnemoic) {
		this.mnemoic = mnemoic;
	}

	/**
	 * fun��o com intuido de coletar e juntar as partes do binario da instru��o dada
	 * @return retorno a binario da instru��o instanciada
	 */
	public String getMipsBytes() {
		String binaryInstruction = "";
		
		binaryInstruction += this.getOpCodesByInstructionName();
		binaryInstruction += this.getAddress();
		
		return binaryInstruction;
	}
	
	
}
