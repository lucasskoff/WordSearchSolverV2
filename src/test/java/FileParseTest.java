import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FileParseTest
{
	private FileParser singleWordFileParser;
	@Before
	public void init(){
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		singleWordFileParser = new FileParser(singleWordFile);
	}

	@Test
	public void parseFirstLineAsList(){
		List<String> correctWordList = new ArrayList<>();
		correctWordList.add("DOG");
		singleWordFileParser.parseFile();
		assertEquals(correctWordList, singleWordFileParser.getWordsList());
	}

	@Test
	public void parseWordSearchAsTwoDCharArray(){
		char[][] correctArray = {{'D','O','G'},{'C','A','T'},{'A','B','C'}};
		singleWordFileParser.parseFile();
		assertArrayEquals(correctArray, singleWordFileParser.getLetterGrid());
	}

	@Test
	public void createHashMapFromWordList(){
		Map<Character, ArrayList<Point>> correctMap = new HashMap<>();
		correctMap.put('D', new ArrayList<>());
		singleWordFileParser.parseFile();
		assertEquals(correctMap, singleWordFileParser.getLetterMap());
	}
}
