package Register;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AllRegisters {
	/**
	 * use a hashmap to store all registers
	 */
    private HashMap<RegisterType, Register> registerMap;

    public AllRegisters() {
        registerMap = new LinkedHashMap<>();
        for(RegisterType registerType : RegisterType.values()){
            registerMap.put(registerType, new Register(registerType));
        }
    }

    public void initializeRegisters(){
        for(Register register: registerMap.values()){
            register.initialize();
        }
    }
    
    public Set<Map.Entry<RegisterType, Register>> getRegisters(){
        return registerMap.entrySet();
    }
    
    public void DisplaysRegisters(){
    	for(Register register : registerMap.values()) {
    		System.out.println(register+"\t"+"Name:"+register.getName()+"\t"+"Data:"+register.getData()+"\t"+"IntData:"+register.getIntData());
    	}
    }
    
    public Register getARegister(RegisterType registerType){
    	/**
    	 * get the Register by RegisterType
    	 */
    	RegisterType registerType_temp=registerType;
		return registerMap.get(registerType_temp);
    }
}
