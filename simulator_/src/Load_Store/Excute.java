package Load_Store;

import Memory.Memory;
import Register.AllRegisters;
import Register.Register;
import Register.RegisterType;

public class Excute {
	/**
	 * to excute the instructions in IR
	 */
	public static void excute_IR(AllRegisters allregisters, Memory memory) {
		String s = Decode.IR_decode(allregisters.getARegister(RegisterType.IR));
		String Opcode = s.substring(0,6);		
		String R_code = s.substring(6,8);	
		String IX_code = s.substring(8,10);
		String I_code = s.substring(10,11);
		String Address_code = s.substring(11,16);
		int EA = 0;
		boolean status = true;
		EA = Decode.EA (Opcode, IX_code,I_code,Address_code,memory,allregisters);
		Register PC = allregisters.getARegister(RegisterType.PC);
		Register MAR = allregisters.getARegister(RegisterType.MAR);
		Register MBR = allregisters.getARegister(RegisterType.MBR);
		Register R = allregisters.getARegister(Decode.R_code_decode(R_code));
		Register X = allregisters.getARegister(Decode.IX_code_decode(IX_code));
		
		switch(Decode.opcode_decode(Opcode)){
		case "LDR" : R.setData(memory.fetch(EA)); break;
		case "STR" : memory.store(R.getIntData(), EA); break;
		case "LDA" : R.setIntData(EA); break;
		case "LDX" : X.setData(memory.fetch(EA)); break;
		case "STX" : memory.store(X.getIntData(), EA); break;
		default : status = false; break;
		}
		if(status)
		{
			MAR.setData(memory.MAR);
			MBR.setData(memory.MBR);
			PC.setData(Bits.convert(PC.getIntData()+1)); //PC+1
		}
		allregisters.DisplaysRegisters();
	}
}
