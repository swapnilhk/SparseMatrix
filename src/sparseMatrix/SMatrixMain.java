package sparseMatrix;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SMatrixMain {	
	public static void main(String[] args) {
		SMatrix mat1 = null, mat2 = null;
		String ch = "yes";
		String str;
		int rows;
		int cols;
		DataInputStream in;
		BufferedReader br;
		try{
			System.out.println("Mat1");			
			in = new DataInputStream(new FileInputStream("mat1.txt"));
			br = new BufferedReader(new InputStreamReader(in));			
			
			System.out.println("No of rows: ");
			System.out.println(str = br.readLine());
			
			rows = Integer.parseInt(str);
			System.out.println("No of cols: ");
			
			System.out.println(str = br.readLine());
			cols = Integer.parseInt(str);
			
			mat1 = new SMatrix(rows, cols);
			
			do{				
				int row, col, value;
				
				System.out.println("Insert row no: ");
				do{					
					System.out.println(str = br.readLine());
					row = Integer.parseInt(str);
				}while(row >= rows);
				
				System.out.println("Insert col no: ");
				do{					
					System.out.println(str = br.readLine());
					col = Integer.parseInt(str);
				}while(col >= cols);
				
				System.out.println("Insert  value: ");
				System.out.println(str = br.readLine());
				value = Integer.parseInt(str);
				
				mat1.setValue(row, col, value);				
				
				System.out.println("Insert more elements? ");

				System.out.println("Mat1 = "+mat1);
				do{					
					System.out.println(str = br.readLine());
					ch = str;
				}while(!ch.equalsIgnoreCase("yes") && !ch.equalsIgnoreCase("no"));
			}while(!ch.equalsIgnoreCase("no"));
			
			System.out.println("Mat2");			
			in = new DataInputStream(new FileInputStream("mat2.txt"));
			br = new BufferedReader(new InputStreamReader(in));
			
			System.out.println("No of rows: ");
			System.out.println(str = br.readLine());
			rows = Integer.parseInt(str);
			
			System.out.println("No of cols: ");
			System.out.println(str = br.readLine());
			cols = Integer.parseInt(str);
			
			mat2 = new SMatrix(rows, cols);
			
			do{				
				int row, col, value;
				
				System.out.println("Insert row no: ");
				do{					
					System.out.println(str = br.readLine());
					row = Integer.parseInt(str);
				}while(row >= rows);
				
				System.out.println("Insert col no: ");
				do{					
					System.out.println(str = br.readLine());
					col = Integer.parseInt(str);
				}while(col >= cols);
				
				System.out.println("Insert  value: ");
				System.out.println(str = br.readLine());
				value = Integer.parseInt(str);
				
				mat2.setValue(row, col, value);				
				
				System.out.println("Insert more elements? ");
				do{					
					System.out.println(str = br.readLine());
					ch = str;
				}while(!ch.equalsIgnoreCase("yes") && !ch.equalsIgnoreCase("no"));
			}while(!ch.equalsIgnoreCase("no"));
		}catch(Exception e){
			e.printStackTrace();
		}		
		System.out.println("Mat1 = "+mat1);
		System.out.println("Mat2 = "+mat2);
		
		try {
			SMatrix res = mat1.add(mat2);
			System.out.println("Mat1 + Mat2 = "+res);
		} catch (MatrixAddException e) {
			System.out.println(e.toString());
		}
		
		mat2.transpose();
		System.out.println("Trabspose(mat2) = "+mat2);
		
		try {
			SMatrix res = mat1.mul(mat2);
			System.out.println("Mat1 * Mat2 = "+res);
		} catch (MatrixMultiplyException e) {
			System.out.println(e.toString());
		}
		
		try {
			SMatrix res = mat1.mul(mat2);
			res.transpose();
			System.out.println("transpose(Mat1 * Mat2) = "+res);
		} catch (MatrixMultiplyException e) {
			System.out.println(e.toString());
		}
	}
}
