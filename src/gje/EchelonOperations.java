package gje;

class EchelonOperations
{
	MatrixOperations mo;
	
	EchelonOperations(MatrixOperations mo)
	{
		this.mo = mo;
	}
	
	double[][] ref(double[][] m, int rL, int cL, int tcL)
	{
		double[][] result = mo.copyArray(m);
		
		Coord pivot = new Coord(0, 0);
		
		for (int row = 0; row < rL; row++)
		{
	        if (pivot.c >= cL) return result;
	        
	        // find pivot point
			pivot = mo.findPivot(result, row, 0, rL, cL);
			
			// if there is no pivot, return matrix
			if (pivot == null) return result;
			
			// swap row if pivot is not on same row
			result = mo.swapRows(result, row, pivot.r);
			
			// rest of the rows below
			for (int i = pivot.r+1; i < rL; i++)
			{
				double f = result[i][pivot.c] / result[row][pivot.c];
				
				result[i][pivot.c] = 0;
				
				for (int j = pivot.c+1; j < tcL; j++)
				{
					result[i][j] = (result[i][j] - result[pivot.r][j] * f);
				}
			}
		}
		return result;
	}
	
	double[][] rref(double[][] m, int rL, int cL, int tcL)
	{
		double[][] result = mo.copyArray(m);
		
		Coord pivot = new Coord(0, 0);
		Coord belowPivot = new Coord(0, 0);
		result = ref(result, rL, cL, tcL);
		
		for (int row = 0; row < rL; row++)
		{
			pivot = mo.findPivot(result, row, 0, rL, cL);
			if (pivot == null) return result;
			
			if (result[pivot.r][pivot.c] != 1)
			{
				double mul = result[pivot.r][pivot.c];
				for (int col = pivot.c; col < tcL; col++)
				{
					result[pivot.r][col] = result[pivot.r][col] / mul;
				}
			}
		}
		

		for (int row = 0; row < rL; row++)
		{
			pivot = mo.findPivot(result, row, 0, rL, cL);
			if (pivot == null) return result;
			
			for (int col = pivot.c+1; col < cL; col++)
			{
				if (result[row][col] != 0)
				{
					double mul = result[row][col];
					
					for (int r = row+1; r < rL; r++)
					{
						if (result[r][col] != 0d)
						{
							belowPivot = new Coord(r, col);
						}
					}
					
					for (int i = col; i < tcL; i++)
					{
						if (belowPivot != null)
						{
							result[row][i] = result[row][i] - (result[belowPivot.r][i] * mul);
						}
					}
				}
			}
		}
		
		return result;
	}
	
	double[][] inverse(double[][] m)
	{
		double[][] result = mo.addIdentity(m);
		
		result = rref(result, m.length, m[0].length, result[0].length);
		
		return result;
	}
}
