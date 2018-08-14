
import junit.framework.TestCase;
import java.util.Random;


public class RandomTestURLValidator extends TestCase {

	public RandomTestURLValidator(String testName) {
		super(testName);
	}

	// This is random tester for URL Validator isValid() method
	public void testRandomIsValid() {

		UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(">>>>>>>>>>>>>>>> Random Testing <<<<<<<<<<<<<<<<<<");
		int failCount = 0;
		int totalCount = 0;
		Random rand = new Random();
		
		// generate 2000 random cases
		for (int i = 0; i < 2000; i++) {
			int  randScheme = rand.nextInt(scheme.length);
			int  randAuthority = rand.nextInt(authority.length);
			int  randPort = rand.nextInt(port.length);
			int  randPath = rand.nextInt(path.length);
			int  randQuery = rand.nextInt(query.length);
			String fullURL = scheme[randScheme] + authority[randAuthority] + port[randPort] + path[randPath] + query[randQuery];
			boolean expectedResult = schemeIsValid[randScheme] && authorityIsValid[randAuthority] && portIsValid[randPort] && pathIsValid[randPath] && queryIsValid[randQuery];
			boolean actualResult = urlVal.isValid(fullURL);
			if(expectedResult != actualResult) {
				System.out.printf("Failed! URL: %s, Expected: %b, Actual: %b\n", fullURL, expectedResult, actualResult);
				failCount++;	
			}
			totalCount++;
			
		}
		
		
		System.out.printf("Random Testing Total Running Cases: %d\n", totalCount);
		System.out.printf("Random Testing Total Failed Cases: %d\n", failCount);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> Random Testing End <<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
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
