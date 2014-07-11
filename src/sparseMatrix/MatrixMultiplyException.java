package sparseMatrix;

public class MatrixMultiplyException extends Exception {	
	public String toString(){
		return "No. of columns of first matrix should be same as no. of rows in second matrix";
	}
}
