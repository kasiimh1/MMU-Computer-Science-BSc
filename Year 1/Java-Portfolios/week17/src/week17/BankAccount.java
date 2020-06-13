package week17;

public class BankAccount {
	
	int Balance (int deposite, int years)
	{
		
		if (deposite > 0 && deposite < 1001 && years > 0 && years < 11)
		{	
		double balance = deposite * Math.pow(1.0 + 0.5 * 0.01, years);
		return (int)(balance);		
		}
		
		if (deposite > 1000 && deposite < 10001 && years > 0 && years < 11)
		{	
		double balance = deposite * Math.pow(1.0 + 1.5 * 0.01, years);
		return (int)(balance);
		}
		
		if (deposite > 10000 && deposite < 50001 && years > 0 && years < 11)
		{	
		double balance = deposite * Math.pow(1.0 + 2.5 * 0.01, years);
		return (int)(balance);
		}
										
		else 
		{
			return -2;
		}
	}
}
