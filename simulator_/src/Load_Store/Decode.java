package Load_Store;

import Memory.Memory;
import Register.AllRegisters;
import Register.Register;
import Register.RegisterType;

public class Decode {
	/**
	 * decode the IR to choose operation and Register
	 */
	public static String IR_decode(Register R) {
		/**
		 * translate the BitSet to String
		 */
		String s = "";
		if(R.getRegisterType()==RegisterType.IR) {
			for(int i = 0; i < R.getData().length(); i++) {
				if(R.getData().get(i)) {
					s+="1";
					}
				else {
					s+="0";
				}
			}
			for (int i = R.getData().length(); i <16 ; i++) {
				s+="0";
			}
			s = new StringBuffer(s).reverse().toString();
		}
		return s;
	}
	
	public static String opcode_decode(String Opcode){ 
		for (OpCodeType OCT : OpCodeType.values()) {   
			/**
			 * Traversal enum OpCodeType choose which operation to do
			 */
			if(Opcode.equals(OCT.getOpCode())){
				return OCT.toString();
				}
		}
		return "";
	}
	
	public static RegisterType R_code_decode(String R_code){ 
		for (RegisterType RCD : RegisterType.values()) {
			/**
			 * Traversal enum RegisterType choose which General Purpose Register to use
			 */
			if(R_code.equals(RCD.getBinaryCode()) && RCD.getDescription().equals("General Purpose Register")){
				return RCD;
				}
		}
		return RegisterType.MFR;
	}
	
	public static RegisterType IX_code_decode(String IX_code){
		for (RegisterType IXC : RegisterType.values()) {
			/**
			 * Traversal enum RegisterType choose which Index Register to use
			 */
			if(IX_code.equals(IXC.getBinaryCode()) && IXC.getDescription().equals("Index Register")){
				return IXC;
				}
		}
		return RegisterType.MFR;
	}
	
	public static int EA (String Opcode, String IX_code, String I_code, String Address_code, Memory m, AllRegisters a){
		/**
		 * calculate the effective address
		 * LDX and STX don't use Index
		 */
		if(Opcode.equals(OpCodeType.LDX.getOpCode())||Opcode.equals(OpCodeType.STX.getOpCode()))
		{
			if(I_code.equals("0")) {
				//System.out.println(Integer.parseInt(Address_code,2));
				return Integer.parseInt(Address_code,2);
			}
			else {
				System.out.println(Bits.convert(m.fetch(Integer.parseInt(Address_code,2))));
				return Bits.convert(m.fetch(Integer.parseInt(Address_code,2)));
			}
		}
		if(I_code.equals("0")){
			//System.out.println("I=0");
				if (IX_code.equals("00")) {
					//System.out.println("IX=00");
					return Integer.parseInt(Address_code,2);
				}
				else {
					//System.out.println("IX=?");
					return a.getARegister(Decode.IX_code_decode(IX_code)).getIntData()+Integer.parseInt(Address_code,2);
				}
			}
			else{
				//System.out.println("I=1");
				if (IX_code.equals("00")) {
					//System.out.println("IX=00");
					return Bits.convert(m.fetch(Integer.parseInt(Address_code,2)));
				}
				else {
					//System.out.println("IX=?");
					return Bits.convert(m.fetch(a.getARegister(Decode.IX_code_decode(IX_code)).getIntData()+Integer.parseInt(Address_code,2)));
				}
			}
		}
}
