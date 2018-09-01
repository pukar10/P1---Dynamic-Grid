// TO DO: add your implementation and JavaDoc

public class Table<RowType, ColType, CellType, OpType extends Combiner<RowType,ColType,CellType>>{
	
	private DynamicArray<RowType> rowHead;	// rowHead as a list of RowType values
	private DynamicArray<ColType> colHead;	// colHead as a list of ColType values
	private DynamicGrid<CellType> board;	// a 2-D grid of CellType values determined by
											//		rowHead, colHead, and op
	private OpType op;						// op that defines a function f:
											//		f(RowType,ColType)-> CellType
	
	//ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	public Table(OpType op){
		// constructor
		// create an table of empty rowHead and colHead, board of 0 rows and 0 cols
		// set the operator
	}
	
	public int getSizeRow(){
		// report the number of rows in board
		// O(1)
	}
	
	public int getSizeCol(){
		// report the number of columns in board 
		// O(1)

	}
		
	public RowType getRowHead(int r) {
		// return the item at index r from rowHead
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
	}
	
	public ColType getColHead(int c) {
		// return the item at index c from colHead
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
	}
	
	public CellType getCell(int r, int c) {
		// return the cell at row r, column c from board
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
	}
	
	public void setOp(OpType op) {
		// change the operation
		// re-calculate and reset the cells of the board
		//
		// O(CR) where C is the number of columns and R is the number of rows of the grid
	}


	public boolean addRow(int i, RowType v){
		// insert v to rowHead at index i 
		// also insert a new row to the grid at row index i
		// calculate the new row based on v, existing colHead and op
		//
		// i may be equal to the size (indicating that you are appending a row)
		//
		// O(C+R) where R is the number of rows of the grid and 
		//		C is the number of columns of the grid

	}
	
	public boolean addCol(int i, ColType v){
		// insert v to colHead at index i 
		// also insert a new column to the grid at column index i
		// calculate the new column based on v, existing rowHead and op
		// i may be equal to the size (indicating that you are appending a column)
		//
		// O(CR) where R is the number of rows of the grid and 
		//		C is the number of columns of the grid

	}
	
	public RowType removeRow(int i){
		// remove and return value from rowHead at index i
		// also remove row i from grid
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(R) where R is the number of rows of the grid

	}

	public ColType removeCol(int i){
		// remove and return value from colHead at index i
		// also remove column i from grid
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(CR) where R is the number of rows and 
		//		 C is the number of columns of the grid 

	}
	
	public RowType setRow(int i, RowType v){
		// change value of rowHead at index i to be v
		// also change the ith row of grid using v, the ColTypes, and op
		// return old value of rowHead from index i
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(C) where C is the number of columns of the grid
		 
	}
	
	public ColType setCol(int i, ColType v){
		// change value of colHead at index i to be v
		// also change the ith column of grid using v, the RowTypes, and op
		// return old value of colHead from index i
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(R) where R is the number of rows of the grid

	}
	
	// --------------------------------------------------------
	// PROVIDED for you to help with testing
	// DO NOT CHANGE CODE!
	// More testing code you can change further down...
	// --------------------------------------------------------


	/**
	 *  Find the width we should use to print the specified column
	 *  @param colIndex column index to specify which column of the grid to check width
	 *  @return an integer to be used to for formatted printing of the column
	 */

	private int getColMaxWidth(int colIndex){
		int ans = -1;
		for (int i=0;i<this.sizeRow;i++){
			int width = (this.getCell(i,colIndex)).toString().length();
			if (ans<width)
				ans = width;
		}
		return ans+1;	
	}

	/**
	 *  Find the width we should use to print the rowHead 
	 *  @return an integer to be used to for formatted printing of the rowHead
	 */

	private int getRowHeadMaxWidth(){
		int ans = -1;
		for (int i=0;i<this.sizeRow;i++){
			int width = (rowHead.get(i)).toString().length();
			if (ans<width)
				ans = width;
		}
		return ans+1;	
	}

	
	/**
	 *  Construct a string representation of the table
	 *  @return a string representation of the table
	 */

	@Override
	public String toString(){
		
		if(sizeRow == 0 && sizeCol==0 ){ return "Empty Table"; }

		// basic info of op and size
    	StringBuilder sb = new StringBuilder("============================\nTable\n");
    	sb.append("Operation: "+op.getClass()+"\n");
    	sb.append("Size: "+ sizeRow + " rows, "+sizeCol+ " cols\n");

		// decide how many chars to use for rowHead 
    	int rowHeadWidth = getRowHeadMaxWidth(); 
    	int totalWidth = rowHeadWidth;
    	DynamicArray<Integer> colWidths = new DynamicArray<>();
    	sb.append(String.format(String.format("%%%ds",rowHeadWidth)," "));

		// colHead 
    	for (int i=0; i<sizeCol; i++){		
    		int colWidth = getColMaxWidth(i);
    		colWidths.add(colWidth);
    		totalWidth += colWidth+1;
    		sb.append(String.format(String.format("|%%%ds",colWidth),colHead.get(i)));
    	}

    	sb.append("\n"+String.format(String.format("%%%ds", totalWidth), " ").replace(" ","-")+"\n");

		// row by row
    	for(int i=0; i<sizeRow; i++){		
    		sb.append(String.format(String.format("%%%ds",rowHeadWidth),rowHead.get(i)));
    		for (int j=0;j<sizeCol; j++){
	    		int colWidth = colWidths.get(j);
      			sb.append(String.format(String.format("|%%%ds",colWidth),board.get(i,j)));
      		}
      		sb.append("\n");
    	}
    	sb.append("============================\n");
    	return sb.toString();

	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	public static void main(String[] args){
		StringAdder sa = new StringAdder();
		Table<String, String, String, StringAdder> stable = new Table<>(sa);
		stable.addRow(0,"red");
		stable.addRow(1,"yellow");
		stable.addCol(0,"apple");
		
		if (stable.getSizeRow() == 2 && stable.getSizeCol() == 1 &&
			stable.getCell(0,0).equals("red apple") && stable.getCell(1,0).equals("yellow apple")) {
			System.out.println("Yay 1");
		}
		//System.out.println(stable.toString());		
		
		stable.addCol(1,"banana");
		stable.addCol(1,"kiwi");
		stable.addRow(2,"green");
		if (stable.getSizeRow()==3 && stable.getSizeCol()==3 && stable.getRowHead(2).equals("green")
			&& stable.getColHead(2).equals("banana") && stable.getCell(2, 1).equals("green kiwi")){
			System.out.println("Yay 2");			
		}
		//System.out.println(stable.toString());
		
		stable.removeRow(0);
		stable.setCol(2,"orange");
		if (stable.getSizeRow()==2 && stable.getSizeCol()==3 && stable.getRowHead(0).equals("yellow")
			&& stable.getColHead(2).equals("orange") && stable.getCell(0, 2).equals("yellow orange")){
			System.out.println("Yay 3");			
		}		
		//System.out.println(stable.toString());

		Table<Integer,Integer, Integer, IntegerComb> itable = new Table<>(new IntegerAdder());
		for (int i=0;i<5; i++){
			itable.addRow(itable.getSizeRow(),i+1);
			itable.addCol(0,(i+1)*10);
		}
		if (itable.getSizeRow()==5 && itable.getSizeCol()==5 && itable.getCell(0, 0)==51
			&& itable.getCell(4, 0)==55 && itable.getCell(3, 4)==14 ){
			System.out.println("Yay 4");			
		}
		//System.out.println(itable.toString());
		
		itable.setOp(new IntegerTimer());
		if (itable.getSizeRow()==5 && itable.getSizeCol()==5 && itable.getCell(0, 0)==50
			&& itable.getCell(4, 0)==250 && itable.getCell(3, 4)==40 ){
			System.out.println("Yay 5");			
		}
		//System.out.println(itable.toString());
					
	}
	
}