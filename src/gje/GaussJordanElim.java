package gje;

class GaussJordanElim
{
	static int r;
	static int c;
	static double[][] originalM;
	static double[][] resultM;
	static MatrixOperations mo;
	static EchelonOperations eo;
	
	GaussJordanElim()
	{
		mo = new MatrixOperations();
		eo = new EchelonOperations(mo);
		r = mo.inputMatrixSize();
		c = r+1;
		originalM = new double[r][c];
		resultM = new double[r][c];
	}
	
	public static void main(String[] args)
	{
		new GaussJordanElim();
		
		mo.inputM(originalM);
		System.out.println("\nOriginal Matrix:\n");
		mo.printM(originalM);
		
		resultM = eo.ref(originalM, originalM.length, originalM[0].length, originalM[0].length);
		System.out.println("\nRef Matrix:\n");
		mo.printM(resultM);
		
		resultM = eo.rref(originalM, originalM.length, originalM[0].length, originalM[0].length);
		System.out.println("\nRref Matrix:\n");
		mo.printM(resultM);
		
		resultM = mo.addIdentity(originalM);
		System.out.println("\nOriginal Matrix with Identity:\n");
		mo.printM(resultM);
		
		resultM = eo.inverse(originalM);
		System.out.println("\nInverse Matrix with Identity:\n");
		mo.printM(resultM);
		
		resultM = mo.removeInverseIdentity(resultM);
		System.out.println("\nInverse Matrix:\n");
		mo.printM(resultM);
	}
}