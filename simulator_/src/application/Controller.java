package application;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;  
import javafx.scene.control.*;

import java.util.BitSet;
import java.util.Map;
import java.util.regex.Pattern;

import Load_Store.*;
import Memory.*;
import Register.*;


public class Controller {

    private AllRegisters allRegisters = new AllRegisters();
    private Memory memory = new Memory();
	
	@FXML private TableView<Register> registerTable;
    @FXML private TableColumn<Register, String> registerNameColumn;
    @FXML private TableColumn<Register, String> binaryColumn;
    @FXML private TableColumn<Register, String> decimalColumn;
    @FXML private Button Initialize;
    @FXML private Button Store;
    @FXML private Button Excute;
    @FXML private TextField IRinput;
    @FXML private TextField memoryADDR;
    @FXML private TextField memoryVALUE;

    

    private void initializeRegisters_Table(){
        //If we modify the getValue on the decorator, we can set the value to null, then initialize registers on IPL
    	allRegisters.initializeRegisters();
    	registerNameColumn.setCellValueFactory(cellData -> new RegisterDecorator(cellData.getValue()).getRegisterName());
        binaryColumn.setCellValueFactory(cellData -> new RegisterDecorator(cellData.getValue()).toBinaryObservableString());
        decimalColumn.setCellValueFactory(cellData -> new RegisterDecorator(cellData.getValue()).toLongObservableString());
    }
    private void initializePanel() {
    	ObservableList<Register> registerList = FXCollections.observableArrayList();
    	registerList.clear();
        for(Map.Entry<RegisterType, Register> registerEntry: allRegisters.getRegisters()){
            registerList.add(registerEntry.getValue());
        }
        registerTable.setItems(registerList);
        
    }
 
    private void initializeMemory() {
    	memory.initialize();
    	
    	memory.store(9, 7);
    	memory.store(10, 8);
    	memory.store(8, 31);
    	memory.store(7, 33);
    	memory.store(4, 23);
    	memory.store(65535, 2047);
    	
    }
    
    @FXML
    public void initialize() {
    	initializeRegisters_Table();
    	initializePanel();
    	initializeMemory();
    }
    @FXML
    void excute(ActionEvent event) {
    	if(IRinput.getText().length()==16) {
    		String IRinputS = IRinput.getText().toString();
    		Register IR = allRegisters.getARegister(RegisterType.IR);
    		//Register R3 = allRegisters.getARegister(RegisterType.R3);
    		//Register X3 = allRegisters.getARegister(RegisterType.X3);
    		if(isBinary(IRinputS))
    		{
    		int IRinputI = Integer.parseInt(IRinputS,2);
    		BitSet bs = Bits.convert(IRinputI);
    		IR.setData(bs);
    		Load_Store.Excute.excute_IR(allRegisters, memory);
    		registerTable.refresh();
    		}
    	}
    }
    @FXML
    void MemoryStore(ActionEvent event) {
    	int memoryADDRS = Integer.parseInt(memoryADDR.getText());
    	int memoryVALUES =Integer.parseInt(memoryVALUE.getText());
    	memory.store(memoryVALUES, memoryADDRS);
    }
    public static boolean isBinary(String str){  
        Pattern pattern = Pattern.compile("[0-1]*");  
        return pattern.matcher(str).matches();     
    }  

}

