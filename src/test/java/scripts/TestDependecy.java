package scripts;

import org.testng.annotations.Test;


public class TestDependecy {

//	@Test(dependsOnMethods = { "testTwo" })
	@Test(enabled =false)
	public void testOne() {
		System.out.println("Test method one");
	}

	@Test
	public void testTwo() {
			System.out.println("Test method two");
		
			testOne();
	}

}
