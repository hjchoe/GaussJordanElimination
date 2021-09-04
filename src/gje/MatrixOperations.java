package gje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class MatrixOperations 
{
	static BufferedReader stdin;
	
	MatrixOperations()
	{
		stdin = new BufferedReader(new InputStreamReader(System.in));
	}
	
	double[][] createIdentity(int s)
	{
		double[][] identityM = new double[s][s];
		for (int i = 0; i < s; i++)
		{
			identityM[i][i] = 1;
		}
		return identityM;
	}
	
	double[][] addIdentity(double[][] m)
	{
		double[][] result = new double[m.length][m.length*2];
		double[][] identity = createIdentity(m.length);
		
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[0].length-1; j++)
			{
				result[i][j] = m[i][j];
				result[i][j+m.length] = identity[i][j];
			}
		}
		return result;
	}
	
	double[][] removeInverseIdentity(double[][] m)
	{
		double[][] result = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m.length; j++)
			{
				result[i][j] = m[i][j+m.length];
			}
		}
		return result;
	}
	
	Coord findPivot(double[][] m, int rstart, int cstart, int rL, int cL)
	{
		for (int c = cstart; c < cL; c++)
		{
			for (int r = rstart; r < rL; r++)
			{
				if (m[r][c] != 0d)
				{
					return new Coord(r, c);
				}
			}
		}
		return null;
	}
	
	void swapRows(double[][] m, int r1, int r2)
	{
		double[][] temp = copyArray(m);
		for (int i = 0; i < m[0].length; i++)
		{
			m[r2][i] = temp[r1][i];
			m[r1][i] = temp[r2][i];
		}
	}
	
	double[][] copyArray(double[][] m)
	{
		double[][] newArray = new double[m.length][m[0].length];
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[0].length; j++)
			{
				newArray[i][j] = m[i][j];
			}
		}
		return newArray;
	}
	
	int inputMatrixSize()
	{
		int size = 0;
		System.out.println("Enter matrix size (ex: enter 3 for 3x3 matrix): ");
		System.out.flush(); 
	    try
	    {
			size = Integer.parseInt(stdin.readLine());
		} catch (NumberFormatException | IOException e)
	    {
			e.printStackTrace();
			System.exit(0);
		}
	    return size;
	}
	
	void inputM(double[][] m)
	{	
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[0].length; j++)
			{
				printM(m);
				System.out.println("Enter: [ Row " + Integer.toString(i+1) + " | Column " + Integer.toString(j+1) + " ]: ");
			    System.out.flush(); 
			    try
			    {
					m[i][j] = Integer.parseInt(stdin.readLine());
				} catch (NumberFormatException | IOException e)
			    {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
	
	void printM(double[][] m)
	{
		for (int i = 0; i < m.length; i++)
		{
			System.out.println(Arrays.toString(m[i]));
		}
	}
}