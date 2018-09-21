package Memory;
import java.util.BitSet;
import Load_Store.*;
public class Memory {
    private static final int DEFAULT_MEMORY_SIZE = 2048,
            DEFAULT_WORD_SIZE = 16;

    private final int size, wordSize;
    private BitSet[] memory;
    public BitSet MAR;
    public BitSet MBR;

    public Memory(int size, int wordSize){
        this.size = size;
        this.wordSize = wordSize;
    }

    public Memory(){
        this.size = DEFAULT_MEMORY_SIZE;
        this.wordSize = DEFAULT_WORD_SIZE;
    }

    public void initialize(){ 
    	/**
    	 * Initialize a memory with 2048 words(16bit word) 
    	 */
    	this.MAR = new BitSet(16);
    	this.MBR = new BitSet(16);
        this.memory = new BitSet[this.size];
        for(int i =0; i < this.size; i++){
            this.memory[i] = new BitSet(this.wordSize);
        }
    }
    public void display_memory() { 
    	/**
    	 * display the memory like 
    	 * Memory Addr 1: {}
    	 * Memory Addr 2: {}
    	 */
    	for(int i =0; i < this.size; i++){
    		System.out.print("Memory Addr "+i+":");
    		System.out.println(this.memory[i]);
    	}
    }
    public void store(int Value,int Addr){ 
    	/**
    	 * set memory[Addr] = Value
    	 * for example 
    	 * Value = 8, BinaryValue = 1000, memory[Addr] = {3}
    	 * Value = 5, BinaryValue = 101,  memory[Addr] = {0, 2}
    	 * it shows which bit is '1'
    	 */
    	char word_truth = '1';
    	if(Addr >= 6 && Addr <= 2047){ 
    		/**
    		 * 0-5 memory is Reserved for other use;
    		 */
    	String BinaryValue = Integer.toBinaryString(Value);
    	if (BinaryValue.length()>16) {return;} //word over 16 bit
    	//System.out.print(BinaryValue);
    	for(int i = 0; i < BinaryValue.length() ; i++) {
    		//System.out.println(BinaryValue.charAt(i));
    		if(BinaryValue.charAt(i) == word_truth) {
    			this.memory[Addr].set(BinaryValue.length()-i-1);
    		}
    		}
    	this.MBR = this.memory[Addr];
    	}
    	else{
    		return;
    	}
    	
    }
    
    public BitSet fetch(int Addr) { 
    	/**
    	 * get memory with address
    	 * set MAR and MBR in every fetch
    	 */
    	if(Addr >= 0 && Addr <= 2047) {
    		this.MAR = Bits.convert(Addr);
    		this.MBR = this.memory[Addr];
    		BitSet mb = this.memory[Addr];
    		return mb;
    	}
    	else {
    		//fault
    		return this.memory[0];
    	}
    }
    
}//end of memory class