
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
		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Manul Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		
		String[] validURL = {
				"http://foo.com/blah_blah",
				"http://foo.com/blah_blah/",
				"http://foo.com/blah_blah_(wikipedia)",
				"http://foo.com/blah_blah_(wikipedia)_(again)",
				"http://www.example.com/wpstyle/?p=364",
				"http://userid:password@example.com:8080",
				"http://userid:password@example.com:8080/",
				"http://userid@example.com",
				"http://userid@example.com/",
				"http://userid@example.com:8080",
				"http://userid@example.com:8080/",
				"http://userid:password@example.com",
				"http://userid:password@example.com/",
				"http://142.42.1.1/",
				"http://142.42.1.1:8080/",
				"http://foo.com/blah_(wikipedia)#cite-1",
				"http://foo.com/blah_(wikipedia)_blah#cite-1",
				"http://foo.com/(something)?after=parens",
				"http://code.google.com/events/#&product=browser",
				"http://j.my",
				"http://1337.net",
				"http://a.b-c.de",
				"http://223.255.255.254"
		};
		
		
		String[] invalidURL = {
				"http://",
				"http://.",
				"http://..",
				"http://../",
				"http://?",
				"http://??",
				"http://??/",
				"http://#",
				"http://##",
				"http://##/",
				"http://foo.bar?q=Spaces should be encoded",
				"//",
				"//a",
				"///a",
				"///",
				"http:///a",
				"foo.com",
				"http:// shouldfail.com",
				":// should fail",
				"http://foo.bar/foo(bar)baz quux",
				"http://-error-.invalid/",
				"http://a.b--c.de/",
				"http://-a.b.co",
				"http://a.b-.co",
				"http://0.0.0.0",
				"http://10.1.1.0",
				"http://10.1.1.255",
				"http://224.1.1.1",
				"http://1.1.1.1.1",
				"http://3628126748",
				"http://.www.foo.bar/",
				"http://.www.foo.bar./",
				"http://10.1.1.1",
				"http://10.1.1.254"
		};
		
		for (int a = 0; a < validURL.length; a++) {
			String fullURL = validURL[a];
			boolean expectedResult = true;
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
			
		}
		
		for (int a = 0; a < invalidURL.length; a++) {
			String fullURL = invalidURL[a];
			boolean expectedResult = true;
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
			System.out.printf(fullURL + "\n");
		}
		
		
		System.out.printf("Manul Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Manul Testing Total Failed Cases: %d\n", failCount);		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Manul Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");

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
