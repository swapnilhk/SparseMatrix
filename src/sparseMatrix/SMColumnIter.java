package sparseMatrix;

import LinkedList.LinkedList;

public class SMColumnIter {
	private LinkedList<SMatrixData> colLL;
	
	SMColumnIter(int colNo, SMatrix s) {
		LinkedList<SMatrixData>elements = s.getElements();
		elements.resetCurr();
		while(elements.hasCurr()){
			if(elements.curr().getCol() == colNo){
				if(colLL == null)
					colLL = new LinkedList<SMatrixData>(elements.curr());
				else this.colLL.addAtEnd(elements.curr());				
			}
			elements.next();
		}
	}
	
	void next(){
		if(colLL != null)
			colLL.next();
	}
	
	SMatrixData getData(){
		if(colLL == null)
			return null;
		else
			return colLL.curr();
	}
	
	boolean hasData(){
		if(colLL == null)
			return false;
		else
			return colLL.hasCurr();
	}	
}
