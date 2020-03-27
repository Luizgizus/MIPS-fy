import java.util.HashMap;

public class Format {
	private HashMap<String, String> formatByInstr = new HashMap<String, String>();
	private HashMap<String, String> registers = new HashMap<String, String>();
	
	public Format() {
		setInstructionByFromat();
		setRegisters();
	}
	
	private void setRegisters() {
		//zero
		registers.put("zero", "00000");
		
		//at
		registers.put("at", "00001");
		
		//v0-v1
		registers.put("v0", "00010");
		registers.put("v1", "00011");
		
		//a0-a3
		registers.put("a0", "00100");
		registers.put("a1", "00101");
		registers.put("a2", "00110");
		registers.put("a3", "00111");
		
		//t0-t7
		registers.put("t0", "01000");
		registers.put("t1", "01001");
		registers.put("t2", "01010");
		registers.put("t3", "01011");
		registers.put("t4", "01100");
		registers.put("t5", "01101");
		registers.put("t6", "01110");
		registers.put("t7", "01111");
		
		//s0-s7
		registers.put("s0", "10000");
		registers.put("s1", "10001");
		registers.put("s2", "10010");
		registers.put("s3", "10011");
		registers.put("s4", "10100");
		registers.put("s5", "10101");
		registers.put("s6", "10110");
		registers.put("s7", "10111");
		
		//t8-t9
		registers.put("t8", "11000");
		registers.put("t9", "11001");
		
		//k0-k1
		registers.put("k0", "11010");
		registers.put("k1", "11011");
		
		//gp
		registers.put("gp", "11100");
		
		//sp
		registers.put("sp", "11101");
		
		//fp
		registers.put("fp", "11110");
		
		//ra
		registers.put("ra", "11111");
	}
	
	public String getRegisterBinaryByRegisterName(String registerName) {
		return registers.get(registerName);
	}

	public String getBinaryOf(int decimal) {		
		StringBuffer binary = new StringBuffer();
		while (decimal > 0) {
			int b = decimal % 2;
			binary.append(b);
			decimal = decimal >> 1;
		}
		
		return binary.reverse().toString();
	}
	
	public String padLeftZeros(String inputString, int length) {
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

	private void setInstructionByFromat() {
		//register format
		formatByInstr.put("add", "R");
		formatByInstr.put("sub", "R");
		formatByInstr.put("mul", "R");
		formatByInstr.put("mult", "R");
		formatByInstr.put("div", "R");
		formatByInstr.put("neg", "R");
		formatByInstr.put("and", "R");
		formatByInstr.put("or", "R");
		formatByInstr.put("xor", "R");
		formatByInstr.put("nor", "R");
		formatByInstr.put("slt", "R");
		formatByInstr.put("sll", "R");
		formatByInstr.put("srl", "R");
		formatByInstr.put("jr", "R");
		
		//instant format
		formatByInstr.put("addi", "I");
		formatByInstr.put("andi", "I");
		formatByInstr.put("ori", "I");
		formatByInstr.put("slti", "I");
		formatByInstr.put("lw", "I");
		formatByInstr.put("sw", "I");
		formatByInstr.put("beq", "I");
		formatByInstr.put("bne", "I");
		
		//jump format
		formatByInstr.put("jal", "J");
		formatByInstr.put("j", "J");
		
		//others
		formatByInstr.put("nop", "N");
	}
	
	public String getFormatByIntructionName(String intructionName) {
		return formatByInstr.get(intructionName);
	}
}
