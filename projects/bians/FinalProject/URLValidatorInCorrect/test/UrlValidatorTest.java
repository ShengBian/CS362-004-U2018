
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

	public void testYourFirstPartition() {
		// You can use this function to implement your First Partition testing

	}

	public void testYourSecondPartition() {
		// You can use this function to implement your Second Partition testing

	}
	// You need to create more test cases for your Partitions if you need to

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
		
		System.out.printf("Total running cases: %d\n", totalCount);
		System.out.printf("Total failed cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> End <<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
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
			"?action=view"		
	};
	
	boolean[] queryIsValid = {
			true,
			true,
			true			
	};
	
	
	
	

}
