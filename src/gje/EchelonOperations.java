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
	        
			pivot = mo.findPivot(result, row, pivot.c, rL, cL);
			if (pivot == null) return result;
			
			mo.swapRows(result, row, pivot.r);
			
			for (int i = pivot.r+1; i < rL; i++)
			{
				double f = result[i][pivot.c] / result[row][pivot.c];
				
				result[i][pivot.r] = 0;
				
				for (int j = pivot.c+1; j < tcL; j++)
				{
					result[i][j] = (result[i][j] - result[pivot.r][j] * f);
				}
			}
			pivot.r++;
			pivot.c++;
		}
		return result;
	}
	
	double[][] rref(double[][] m, int rL, int cL, int tcL)
	{
		double[][] result = mo.copyArray(m);
		
		Coord pivot = new Coord(0, 0);
		result = ref(result, rL, cL, tcL);
		
		for (int row = 0; row < rL; row++)
		{
			pivot = mo.findPivot(result, pivot.r, pivot.c, rL, cL);
			if (pivot == null) return result;
			
			if (result[pivot.r][pivot.c] != 1)
			{
				double mul = result[pivot.r][pivot.c];
				for (int col = pivot.c; col < tcL; col++)
				{
					result[pivot.r][col] = result[pivot.r][col] / mul;
				}
			}
			pivot.r++;
			pivot.c++;
		}
		
		for (int row = 0; row < rL; row++)
		{
			for (int col = row+1; col < cL-1; col++)
			{
				if (result[row][col] != 0)
				{
					double mul = result[row][col];
					for (int i = 0; i < tcL; i++)
					{
						result[row][i] = result[row][i] - (result[col][i] * mul);
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
