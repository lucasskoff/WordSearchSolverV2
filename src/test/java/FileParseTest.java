import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileParseTest
{
	@Test
	public void parseFirstLineAsList(){
		File file = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		FileParser fileParser = new FileParser(file);
		fileParser.parseFile();
		List<String> correctWordList = new ArrayList<>();
		correctWordList.add("DOG");
		assertEquals(correctWordList, fileParser.wordsList);
	}
}
