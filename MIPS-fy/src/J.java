import java.util.HashMap;

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
	
	private void setOpCodesByInstructionName() {
		opCodesByInstructionName.put("j", "000010");
		opCodesByInstructionName.put("jal", "000011");
	}

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

	public String getMipsBytes() {
		String binaryInstruction = "";
		
		binaryInstruction += this.getOpCodesByInstructionName();
		binaryInstruction += this.getAddress();
		
		return binaryInstruction;
	}
	
	
}
