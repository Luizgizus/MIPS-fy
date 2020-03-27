import java.io.IOException;

public class Application {
	private static Format f = new Format();
	
	public static void main(String[] args) throws Exception {
		String[] fileLines = FileUtil.readFrom("C:\\Users\\luiz1\\Desktop\\GIT\\MIPS-fy\\MIPS-fy\\src\\in.txt");
		getMipsConvertion(fileLines);
	}
	
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
	
	private static String[] getParamsFromLines(String[] fileLine) {
		String[] params = new String[fileLine.length - 1];
		for (int i = 1; i < fileLine.length; i++) {
			params[i-1] = fileLine[i];
		}
		
		return params;
	}
	
	private static void getMipsConvertion(String[] fileLines) throws Exception {
		for (int idx = 0; idx < fileLines.length; idx++) {
						
			String[] lineParams = fileLines[idx].split(" ");			
			String instructionName = lineParams[0].toLowerCase().trim();
			String[] params = getParamsFromLines(lineParams);
			
			String format = f.getFormatByIntructionName(instructionName);
			
			String bytesString = getBytesStringByFormat(format, instructionName, params);
			FileUtil.writeOn("C:\\Users\\luiz1\\Desktop\\GIT\\MIPS-fy\\MIPS-fy\\src\\out.txt", bytesString);
		}
	}

}
