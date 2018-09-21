package Register;

import java.util.BitSet;

import Load_Store.Bits;
import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class RegisterDecorator {

    private final Register register;

    public RegisterDecorator(Register register){
        this.register = register;
    }

    /***
     * Convert the Bitset to long, given that size cannot be over
     * 64 bits, we can safely return the first index
     *
     * @return value of the register expressed as a long
     */
    public long toLong(){
        BitSet data = register.getData();
        if(data.isEmpty()){
            return 0L;
        }
        //long[] longArray = data.toLongArray();
        return Bits.convert(data);
    }

    public String toBinaryString(){
        BitSet data = register.getData();
        int numberOfBits = register.getSize();
        if(data.isEmpty()){
            char[] empties = new char[numberOfBits];
            return new String(empties).replace("\0", "0");
        }
        return Bits.convertToString(data, numberOfBits);
        //StringBuilder builder = new StringBuilder(numberOfBits);
        //data.stream().forEach(builder::append);
        
    }

    public SimpleStringProperty toBinaryObservableString(){
        return new SimpleStringProperty(toBinaryString());
    }

    public SimpleStringProperty toLongObservableString(){
        String string = Long.toString(toLong());
        return new SimpleStringProperty(string);
    }

    public SimpleStringProperty getRegisterName(){
        return new SimpleStringProperty(register.getRegisterType().toString());
    }

}

