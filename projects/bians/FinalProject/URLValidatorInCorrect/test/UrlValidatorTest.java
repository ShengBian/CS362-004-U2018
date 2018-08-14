
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

	public UrlValidatorTest(String testName) {
		super(testName);
	}

	public void testManualTest() {
//You can use this function to implement your manual testing	   

	}

	// This function is to test Scheme Partition
	public void testYourFirstPartition() {
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Scheme Partition Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		for (int a = 0; a < scheme.length; a++) {
			String fullURL = scheme[a] + "www.google.com";
			boolean expectedResult = schemeIsValid[a];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
		}

		System.out.printf("Scheme Partition Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Scheme Partition Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Scheme Partition Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// This function is to test Authority Partition
	public void testYourSecondPartition() {
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Authority Partition Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		for (int a = 0; a < authority.length; a++) {
			String fullURL = "http://" + authority[a];
			boolean expectedResult = authorityIsValid[a];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
		}

		System.out.printf("Authority Partition Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Authority Partition Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Authority Partition Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}
	
	// This function is to test Port Partition
	public void testYourThirdPartition() {
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Port Partition Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		for (int a = 0; a < port.length; a++) {
			String fullURL = "www.google.com" + port[a];
			boolean expectedResult = portIsValid[a];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
		}

		System.out.printf("Port Partition Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Port Partition Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Port Partition Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}
	
	// This function is to test Path Partition
	public void testYourFourthPartition() {
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Path Partition Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		for (int a = 0; a < path.length; a++) {
			String fullURL = "http://www.google.com" + path[a];
			boolean expectedResult = pathIsValid[a];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
		}

		System.out.printf("Path Partition Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Path Partition Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Path Partition Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}
	
	// This function is to test Query Partition
	public void testYourFifthPartition() {
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Query Partition Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		for (int a = 0; a < query.length; a++) {
			String fullURL = "http://www.google.com/" + query[a];
			boolean expectedResult = queryIsValid[a];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
		}

		System.out.printf("Query Partition Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Query Partition Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Query Partition Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}
	
	
	public void testIsValid() {
		// You can use this function for programming based testing

		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Programming Based Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;

		for (int a = 0; a < scheme.length; a++) {
			for (int b = 0; b < authority.length; b++) {
				for (int c = 0; c < port.length; c++) {
					for (int d = 0; d < path.length; d++) {
						for (int e = 0; e < query.length; e++) {
							String fullURL = scheme[a] + authority[b] + port[c] + path[d] + query[e];
							boolean expectedResult = schemeIsValid[a] && authorityIsValid[b] && portIsValid[c] && pathIsValid[d] && queryIsValid[e];
							boolean actualResult = urlVal.isValid(fullURL);
							if(expectedResult != actualResult) {
								System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
								failCount++;	
							}
							totalCount++;
						}
					}
				}
			}
		}
		
		System.out.printf("Programming Based Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Programming Based Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Programming Based Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
	}

	String[] scheme = { 
			"http://", 
			"", 
			"http:/", 
			"http:", 
			"://" 
	};

	boolean[] schemeIsValid = { 
			true, 
			true, 
			false, 
			false, 
			false 
	};
	
	String[] authority = {
			"www.google.com",
			"go.au",
			"0.0.0.0",
			"255.255.255.255",
			"",
			".aaa",
			"go.1aa",
			"1.2.3",
			"256.256.256.256",
			"1.2.3.4.5"		
	};
	
	boolean[] authorityIsValid = {
			true,
			true,
			true,
			true,
			false,
			false,
			false,
			false,
			false,
			false
	};
	
	String[] port = {
			"",
			":0",
			":65535",
			":80",
			":65636",
			":65a",
			":-1"
	};
	
	boolean[] portIsValid = {
			true,
			true,
			true,
			true,
			false,
			false,
			false
	};
	
	String[] path = {
			"",
			"/test1/",
			"/test1/file",
			"/$23",
			"/t123",
			"/test1",
			"/..",
			"/../",
			"/test1//file",
			"/..//file"		
	};
	
	boolean[] pathIsValid = {
			true,
			true,
			true,
			true,
			true,
			true,
			false,
			false,
			false,
			false	
	};
	
	String[] query = {
			"",
			"?action=edit&mode=up",
			"?action=view",
			"****____"
	};
	
	boolean[] queryIsValid = {
			true,
			true,
			true,
			false
	};
	
	
	
	

}
