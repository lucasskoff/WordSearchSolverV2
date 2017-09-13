import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class GridSolverUtilTest
{
	private FileParser singleWordFileParser;
	private Point firstLetterPoint;
	private int gridLength;
	private int wordLength;
	@Before
	public void init()
	{
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		singleWordFileParser = new FileParser(singleWordFile);
		firstLetterPoint = singleWordFileParser.getLetterMap().get('D').get(0);
		gridLength = singleWordFileParser.getLetterGrid().length;
		wordLength =  singleWordFileParser.getWordsList().get(0).length();
	}

	@Test
	public void wordCanFitInHorizontalDirection()
	{
		Point firstLetterPoint = singleWordFileParser.getLetterMap().get('D').get(0);
		int gridLength = singleWordFileParser.getLetterGrid().length;
		int wordLength =  singleWordFileParser.getWordsList().get(0).length();

		assertEquals(true, GridSolverUtil.canWordFitInDirection(firstLetterPoint, gridLength, wordLength, Direction.Horizontal_Forward));
	}

	@Test
	public void wordCannotFitInHorizontalDirection()
	{
		assertEquals(false, GridSolverUtil.canWordFitInDirection(firstLetterPoint, gridLength, wordLength + 1, Direction.Horizontal_Forward));
	}

	@Test
	public void wordFinderNullWhenWordIsNotFound()
	{
		assertEquals(null, GridSolverUtil.findWord(Direction.values(), singleWordFileParser.getLetterGrid(), "BAT"));
	}
}
