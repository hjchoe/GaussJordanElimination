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
			for (int j = 0; j < m.length; j++)
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
	
	double[][] swapRows(double[][] m, int r1, int r2)
	{
		double[][] result = copyArray(m);
		for (int i = 0; i < m[0].length; i++)
		{
			result[r2][i] = m[r1][i];
			result[r1][i] = m[r2][i];
		}
		return result;
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
	
	Coord inputMatrixSize()
	{
		Coord size;
		StringBuffer r = new StringBuffer();
		StringBuffer c = new StringBuffer();
		int rowSize = 0;
		int columnSize = 0;
		int index = 0;
		String input;
		
		System.out.println("Enter matrix size (ex: enter 3x4 for 3x4 matrix): ");
		System.out.flush();
		
		try
		{
			input = stdin.readLine();
			
			for (int i = index; i < input.length(); i++)
			{
				String substr = input.substring(i, i+1);
				if (!substr.equalsIgnoreCase("x"))
				{
					r.append(substr);
				}
				else
				{
					index = i;
					break;
				}
			}
			for (int i = index+1; i < input.length(); i++)
			{
				String substr = input.substring(i, i+1);
				c.append(substr);
			}
			
			rowSize = Integer.parseInt(r.toString());
			columnSize = Integer.parseInt(c.toString());
		} catch (NumberFormatException | IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	    size = new Coord(rowSize, columnSize);
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