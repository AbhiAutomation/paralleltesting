package testng.parallelExecution;


class A{
	
	public void printI()
	{
		for (int i = 1; i<101; i++)
		{
			System.out.println("i: " + i);
		}
	}
}


class Demo{
	
	public static void main(String [] args)
	{
		A objA = new A();
		objA.printI();
	}
}