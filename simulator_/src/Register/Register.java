package Register;
import java.util.BitSet;
import Load_Store.Bits;

public class Register {
/**
 * the Register Class
 */
    private final RegisterType registerType;
    private BitSet data;

    public Register(RegisterType registerType){
        if(registerType.getSize() > 64){
            throw new IllegalArgumentException("Can't instantiate register size larger than 64 bits");
        }
        this.registerType = registerType;
    }

    public void initialize(){
        data = new BitSet(registerType.getSize());
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public BitSet getData() {
        return data;
    }

    public int getSize(){
        return registerType.getSize();
    }

    public String getName(){
        return registerType.toString();
    }

    public String getDescription(){
        return registerType.getDescription();
    }
    
    public void setData(BitSet data) {
    	this.data = data;
    }
    public void setIntData(int data) {
    	this.data = Bits.convert(data);
    }
    public int getIntData() {
    	return Bits.convert(data);
    }

}