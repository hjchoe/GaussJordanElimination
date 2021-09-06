package gje;

class GaussJordanElim
{
	static int r;
	static int c;
	static double[][] originalM;
	static double[][] resultM;
	static double[][] example = {{2, 1, -1, 8}, {-3, -1, 2, -11}, {-2, 1, 2, -3}};
	static double[][] example2 = {{0, 1, 1}, {1, 2, 1}, {2, 7, 9}};
	static MatrixOperations mo;
	static EchelonOperations eo;
	
	GaussJordanElim()
	{
		mo = new MatrixOperations();
		eo = new EchelonOperations(mo);
		Coord pos = mo.inputMatrixSize();
		r = pos.r;
		c = pos.c;
		originalM = new double[r][c];
		resultM = new double[r][c];
	}
	
	public static void main(String[] args)
	{
		new GaussJordanElim();
		
		originalM = example;
		
		//mo.inputM(originalM);
		System.out.println("\nOriginal Matrix:\n");
		mo.printM(originalM);
		
		resultM = eo.ref(originalM, originalM.length, originalM.length, originalM[0].length);
		System.out.println("\nRef Matrix:\n");
		mo.printM(resultM);
		
		resultM = eo.rref(originalM, originalM.length, originalM.length, originalM[0].length);
		System.out.println("\nRref Matrix:\n");
		mo.printM(resultM);
		
		resultM = mo.addIdentity(originalM);
		System.out.println("\nOriginal Matrix with Identity:\n");
		mo.printM(resultM);
		
		/*
		// ERRORS WITHIN INVERSE + ADD IDENTITY FUNCTIONS
		resultM = eo.inverse(originalM);
		System.out.println("\nInverse Matrix with Identity:\n");
		mo.printM(resultM);
		
		resultM = mo.removeInverseIdentity(resultM);
		System.out.println("\nInverse Matrix:\n");
		mo.printM(resultM);
		*/
	}
}