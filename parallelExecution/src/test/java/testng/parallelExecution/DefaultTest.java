package testng.parallelExecution;

import org.testng.annotations.Test;

public class DefaultTest {
	@Test
	public void DefaulTest_Test1()
	{
		System.out.println("As you did not provide TestNg file path from command line"
				+ " so running default test.");
		System.out.println("Default TestNG name is \"DefaultTest.xml\"");
		System.out.println("To run desired testng file, use command : "
				+ "mvn test -DtestngFilePath=<name of TestNg XML file>");
	}

}