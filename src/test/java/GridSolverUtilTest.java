import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class GridSolverUtilTest
{
	private FileParser singleWordFileParser;
	@Before
	public void init()
	{
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		singleWordFileParser = new FileParser(singleWordFile);
	}

	@Test
	public void wordCanFitInHorizontalDirection()
	{
		Point firstLetterPoint = singleWordFileParser.getLetterMap().get('D').get(0);
		int gridLength = singleWordFileParser.getLetterGrid().length;
		int wordLength =  singleWordFileParser.getWordsList().get(0).length();

		assertEquals(true, GridSolverUtil.canWordFitInDirection(firstLetterPoint, gridLength, wordLength, Direction.Horizontal_Forward));
	}
}
