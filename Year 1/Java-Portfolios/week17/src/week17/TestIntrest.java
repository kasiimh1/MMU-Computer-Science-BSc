package week17;

public class TestIntrest {
	
	static BankAccount functions;
	
	static void TestCompoundInterest()
	{
		int [] deposite = {1,1000,1001,10000,10001,50000,0,50001,1000,1000};
		
		int [] years = {1,10,1,10,5,10,1,10,0,11};		
			
		int [] output = {1, 1051, 1016, 11605, 11315, 64004,0,50001,1000,1000};

		int numTestCases = deposite.length;
		System.out.println("***********\nTesting BankAccount ");
		
		int passed = 0;
		int error = 0;
		
		for ( int i = 0; i < numTestCases; i++ )
		{
			int result = functions.Balance(deposite[i], years[i]);
										
			if (result == output[i]) {
				passed++;
				System.out.println("Test "+i+" passed!");
			}
				
			if (result == -2)
			{
				error++;
				System.out.println("Test "+i+" Ran into an Error, years or deposite is invalid!");	
			}		
			
		}
		System.out.println("Finished testing BankAccount - passed "+passed+" out of 6");
		System.out.println("Finished testing BankAccount - errors "+error+" out of 4");

	}
	
	public static void main( String args[] )
	{
		functions = new BankAccount();
		TestCompoundInterest();
	}

}
