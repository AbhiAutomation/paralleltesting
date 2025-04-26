package testng.parallelExecution;

import org.testng.annotations.Test;
/**
 * TestClass3 contains 3 test cases
 */
public class TestClass3 {
	int waitTimeInMiliSecond = 5000;
	@Test
	public void TestClass3_test1()
	{
		System.out.println("In TestClass3_test1. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestClass3_test2()
	{
		System.out.println("In TestClass3_test2. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestClass3_test3()
	{
		System.out.println("In TestClass3_test3. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
