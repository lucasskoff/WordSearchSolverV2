import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
		assertEquals(null, GridSolverUtil.findWord(Direction.values(), singleWordFileParser.getLetterGrid(), singleWordFileParser.getLetterMap().get('D'), "BAT"));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPoints()
	{
		List<Point> correctList = new ArrayList<Point>();
		correctList.add(new Point(0,0));
		correctList.add(new Point(1, 0));
		correctList.add(new Point(2, 0));
		assertEquals(correctList, GridSolverUtil.findWord(Direction.values(), singleWordFileParser.getLetterGrid(), singleWordFileParser.getLetterMap().get('D'), singleWordFileParser.getWordsList().get(0)));
	}
}
