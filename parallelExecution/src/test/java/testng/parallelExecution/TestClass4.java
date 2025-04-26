package testng.parallelExecution;

import org.testng.annotations.Test;

public class TestClass4 {
	int waitTimeInMiliSecond = 5000;
	@Test
	public void TestClass4_test1()
	{
		System.out.println("In TestClass4_test1. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestClass4_test2()
	{
		System.out.println("In TestClass4_test2. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TestClass4_test3()
	{
		System.out.println("In TestClass4_test3. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestClass4_test4()
	{
		System.out.println("In TestClass4_test4. , Thread id is " + Thread.currentThread().getId());
		try {
			Thread.sleep(waitTimeInMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
