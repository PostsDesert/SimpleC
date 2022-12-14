package lang;

import java.io.File;

import org.junit.Test;

public class ParseTests {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/parser");

//	@Test public void example() {
//		Util.testValidSyntax(TEST_DIRECTORY, "example.in");
//	}

	@Test
	public void error() {
		Util.testSyntaxError(TEST_DIRECTORY, "error.in");
	}

	@Test
	public void error2() {
		Util.testSyntaxError(TEST_DIRECTORY, "error2.in");
	}

	@Test
	public void missingSimicolon() {
		Util.testSyntaxError(TEST_DIRECTORY, "missingSimicolon.in");
	}
}
