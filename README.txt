

CS6461: Computer Architectures Project
Part1: Basic Machine
Group 9
-------------------------------------------------------------------------------------------------------------------------------------------------------------

IMPLEMENT A MEMORY:
1.Some data has been read into memory for test purpose:
	memory.store(9, 7);
	memory.store(10, 8);
	memory.store(8, 31);
	memory.store(7, 33);
	memory.store(4, 23);
	memory.store(65535, 2047);
2.You can use store memory to set the memory data
Input ADDR and VALUE (Decimal) and click Button [Store]
(ADDR should be a integer greater than greater or equal to 6 and less than or equal to 2047, and VALUE should be less than 2^16, otherwise it will not work.)

-------------------------------------------------------------------------------------------------------------------------------------------------------------

INSTRUCTIONS:
1.Input a 16 bit binary number (instruction) and click the Button [Execute] to execute the instruction. Then the panel will change.
For example:
1010010011111111 (LDX,3,31,1)
1010010001010111 (LDX,1,23)
0000011100011111 (LDR,3,0,31)
Note: Execution will not be successful if the input is not 16 bit number or not binary.
2.After execution, PC+1, MAR, MBR and the R0-R3/X1-X3 used in the instruction will change.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

INITIALIZE:
1.By Click Button [Initialize], the Register and Memory will return to the initial state.