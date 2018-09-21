package Load_Store;

public enum OpCodeType {
	/**
	 * the operation list
	 */
	LDR("000001"),
	STR("000010"),
	LDA("000011"),
	LDX("101001"),
	STX("101010");
	private final String OpCode;
    
	OpCodeType(String OpCode){
        this.OpCode = OpCode;
    }
	public String getOpCode() {
        return this.OpCode;
    }

}
