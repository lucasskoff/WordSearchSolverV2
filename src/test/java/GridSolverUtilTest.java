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
	private List<Point> singleWordFirstLetterPointList;
	private char[][] singleWordLetterGrid;
	private List<String> singleWordList;
	private GridSolverUtil gridSolverUtil;

	private FileParser doubleWordFileParser;
	@Before
	public void init()
	{
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		singleWordFileParser = new FileParser(singleWordFile);
		singleWordFirstLetterPointList = singleWordFileParser.getLetterMap().get('D');
		singleWordLetterGrid = singleWordFileParser.getLetterGrid();
		singleWordList =  singleWordFileParser.getWordsList();

		File doubleWordFile = new File("resources/horizontalDoubleWordThreeByThreeGrid.txt");
		doubleWordFileParser = new FileParser(doubleWordFile);

		gridSolverUtil = new GridSolverUtil();
	}

	@Test
	public void wordCanFitInHorizontalDirection()
	{
		assertEquals(true, gridSolverUtil.canWordFitInDirection(Direction.Horizontal_Forward, singleWordLetterGrid.length, singleWordFirstLetterPointList.get(0), singleWordList.get(0).length()));
	}

	@Test
	public void wordCannotFitInHorizontalDirection()
	{
		assertEquals(false, gridSolverUtil.canWordFitInDirection(Direction.Horizontal_Forward, singleWordLetterGrid.length, singleWordFirstLetterPointList.get(0), singleWordList.get(0).length() + 1));
	}

	@Test
	public void wordFinderNullWhenWordIsNotFound()
	{
		assertEquals(null, gridSolverUtil.findWord(singleWordLetterGrid, singleWordFirstLetterPointList, "BAT"));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPoints()
	{
		List<Point> correctList = new ArrayList<>();
		correctList.add(new Point(0,0));
		correctList.add(new Point(1, 0));
		correctList.add(new Point(2, 0));
		assertEquals(correctList, gridSolverUtil.findWord(singleWordLetterGrid, singleWordFirstLetterPointList, singleWordList.get(0)));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPointsNonHorizontalDirection()
	{
		List<Point> correctList = new ArrayList<>();
		correctList.add(new Point(0, 0));
		correctList.add(new Point(0, 1));
		correctList.add(new Point(0, 2));
		assertEquals(correctList, gridSolverUtil.findWord(doubleWordFileParser.getLetterGrid(), doubleWordFileParser.getLetterMap().get('D'), doubleWordFileParser.getWordsList().get(1)));
	}
}
