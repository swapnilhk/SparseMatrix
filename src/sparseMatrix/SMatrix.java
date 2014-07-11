package sparseMatrix;

import java.util.ArrayList;
import java.util.Iterator;

import LinkedList.LinkedList;

public class SMatrix {
	private LinkedList<SMatrixData> elementsLL;
	private int rows, columns;
	
	SMatrix(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}
	
	public LinkedList<SMatrixData> getElements() {
		return elementsLL;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	void setValue(int row, int col, int value){
		if(value == 0 || row >= rows || col >= columns) return;
		
		SMatrixData data = new SMatrixData(row, col, value);
		
		// Inserting first element in the linked list
		if(elementsLL == null){
			elementsLL = new LinkedList<SMatrixData>(data);
			return;
		}
		
		SMRowIter ri = this.RowBegin(row);	
		while(ri.hasData()){			
			if(ri.getData() != null && ri.getData().getCol() == col){//Duplicate element
				this.elementsLL.addInPlaceOf(ri.getData(), data);
				return;
			}
			else if(ri.getData() != null && ri.getData().getCol() > col){
				this.elementsLL.addBefore(ri.getData(), data);
				return;
			}
			ri.next();
		}
		
		/* This point can be reached in two conditions
		 * 1. There are no elements in this row
		 * 2. Elements are present in the row but end of row has been reached without inserting the element
		 * In both cases, traverse the list to find next row or the end of the list and insert the node before this node  
		 */
		
		elementsLL.resetCurr();
		while(elementsLL.hasCurr() && elementsLL.curr().getRow() <= row)
			elementsLL.next();
		if(elementsLL.curr() != null)
			this.elementsLL.addBefore(elementsLL.curr(), data);
		else
			this.elementsLL.addAtEnd(data);
	}
	
	void setValue(SMatrixData d){
		setValue(d.getRow(), d.getCol(), d.getValue());
	}
	
	SMRowIter RowBegin(int row){
		return new SMRowIter(row, this);
	}
	
	SMRowIter RowEnd(int row){
		return new SMRowIter(row+1, this);
	}
	
	SMColumnIter ColumnBegin (int column){
		return new SMColumnIter(column, this);
	}
	
	SMColumnIter ColumnEnd (int column){
		return new SMColumnIter(column+1, this);
	}
	
	SMatrix add(SMatrix a) throws MatrixAddException{
		SMatrix res;
		if(a.getRows() != rows || a.getColumns() != columns)
			throw new MatrixAddException();
		else{
			res = new SMatrix(rows, columns);
			SMatrixData t1 = null, t2 = null;
			elementsLL.resetCurr();
			a.elementsLL.resetCurr();
			if(elementsLL.hasCurr())
				t1 = elementsLL.curr();
			if(a.elementsLL.hasCurr())
				t2 = a.elementsLL.curr();			
			while(t1 != null && t2 != null){
				if(t1.getRow() == t2.getRow()){
					if(t1.getCol() == t2.getCol()){
						res.setValue(t1.getRow(), t1.getCol(), t1.getValue()+t2.getValue());
						if(elementsLL.hasNext()){
							elementsLL.next();
							t1 = elementsLL.curr();
						}
						else
							t1 = null;
						
						if(a.elementsLL.hasNext()){
							a.elementsLL.next();
							t2 = a.elementsLL.curr();
						}
						else
							t2 = null;						
					}
					else if(t1.getCol() < t2.getCol()){
						res.setValue(t1);
						if(elementsLL.hasNext()){
							elementsLL.next();
							t1 = elementsLL.curr();
						}
						else
							t1 = null;						
					}
					else{
						res.setValue(t2);
						if(a.elementsLL.hasNext()){
							a.elementsLL.next();
							t2 = a.elementsLL.curr();
						}
						else
							t2 = null;
					}
				}
				else if(t1.getRow() < t2.getRow()){
					res.setValue(t1);
					if(elementsLL.hasNext()){
						elementsLL.next();
						t1 = elementsLL.curr();
					}
					else
						t1 = null;
				}
				else{
					res.setValue(t2);
					if(a.elementsLL.hasCurr()){
						a.elementsLL.next();
						t2 = a.elementsLL.curr();
					}
					else
						t2 = null;
				}
			}
			if(t1 != null){
				do{
					t1 = elementsLL.curr();
					res.setValue(t1);
					elementsLL.next();
				}while(elementsLL.hasCurr());
			}
			if(t2 != null){
				do{
					t2 = a.elementsLL.curr();
					res.setValue(t2);
					a.elementsLL.next();
				}while(a.elementsLL.hasCurr());
			}	
		}
		return res;
	}
	
	SMatrix mul(SMatrix a) throws MatrixMultiplyException{
		SMatrix res;
		if(this.columns != a.rows)
			throw new MatrixMultiplyException();
		else{
			res = new SMatrix(this.rows, a.columns);			
			for(int i = 0; i < this.rows; i++){
				for(int j = 0; j < a.getColumns(); j++){
					SMRowIter ri = this.RowBegin(i);
					SMColumnIter rc = a.ColumnBegin(j);
					if(ri == null)continue;
					if(rc == null)continue;
					int tempValue = 0;
					while(ri.hasData() && rc.hasData()){
						if(ri.getData().getCol() == rc.getData().getRow()){
							tempValue += ri.getData().getValue() * rc.getData().getValue();
							ri.next();
							rc.next();
						}
						else if(ri.getData().getCol() < rc.getData().getRow()){
							ri.next();
						}
						else rc.next();
					}
					SMatrixData d = new SMatrixData(i, j, tempValue);
					res.setValue(d);
				}					
			}
		}
		return res;
	}	
	
	void transpose(){
		SMatrixData tempData;
		ArrayList<SMatrixData> tempList = new ArrayList<SMatrixData>();
		elementsLL.resetCurr();
		while(elementsLL.hasCurr()){
			tempData = elementsLL.curr();
			elementsLL.remove(elementsLL.curr());
			elementsLL.resetCurr();
			int j = tempData.getCol();
			tempData.setCol(tempData.getRow());
			tempData.setRow(j);
			tempList.add(tempData);
		}
		int j = rows;
		rows = columns;
		columns = j;
		Iterator<SMatrixData>it = tempList.iterator();
		if(it.hasNext())
			elementsLL = new LinkedList<SMatrixData>(it.next());
		while(it.hasNext()){
			setValue(it.next());
		}
	}
	
	public String toString()
	{	
		elementsLL.resetCurr();
		String ret = "";
		while(elementsLL.hasCurr()){
			ret += elementsLL.curr().toString()+" ";
			elementsLL.next();
		}
		return ret;
	}
}
