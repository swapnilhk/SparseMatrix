package sparseMatrix;

import LinkedList.LinkedList;

public class SMRowIter {
	private LinkedList<SMatrixData> rowLL;	
	
	SMRowIter(int rowNo, SMatrix s) {
		LinkedList<SMatrixData>elements = s.getElements();
		elements.resetCurr();
		while(elements.hasCurr()){
			if(elements.curr().getRow() == rowNo){
				if(rowLL == null)
					rowLL = new LinkedList<SMatrixData>(elements.curr());
				else this.rowLL.addAtEnd(elements.curr());				
			}
			elements.next();
		}
	}
	
	void next(){
		if(rowLL != null)
			rowLL.next();
	}
	
	SMatrixData getData(){
		if(rowLL == null)
			return null;
		else
			return rowLL.curr();
	}
	
	boolean hasData(){
		if(rowLL == null)
			return false;
		else
			return rowLL.hasCurr();
	}	
}
