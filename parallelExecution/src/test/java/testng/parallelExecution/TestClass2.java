package testng.parallelExecution;

import org.testng.annotations.Test;

/**
 * TestClass2 contains 2 test cases
 */
public class TestClass2 {
	int waitTimeInMiliSecond = 5000;
	@Test
	public void TestClass2_test1()
	{
		System.out.println("In TestClass2_test1. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestClass2_test2()
	{
		System.out.println("In TestClass2_test2. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
