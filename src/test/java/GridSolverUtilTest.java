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
	private List<Point> firstLetterPointList;
	private char[][] letterGrid;
	private List<String> wordsList;
	private GridSolverUtil gridSolverUtil;
	@Before
	public void init()
	{
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		singleWordFileParser = new FileParser(singleWordFile);
		firstLetterPointList = singleWordFileParser.getLetterMap().get('D');
		letterGrid = singleWordFileParser.getLetterGrid();
		wordsList =  singleWordFileParser.getWordsList();
		gridSolverUtil = new GridSolverUtil();
	}

	@Test
	public void wordCanFitInHorizontalDirection()
	{
		assertEquals(true, gridSolverUtil.canWordFitInDirection(Direction.Horizontal_Forward, letterGrid.length, firstLetterPointList.get(0), wordsList.get(0).length()));
	}

	@Test
	public void wordCannotFitInHorizontalDirection()
	{
		assertEquals(false, gridSolverUtil.canWordFitInDirection(Direction.Horizontal_Forward, letterGrid.length, firstLetterPointList.get(0), wordsList.get(0).length() + 1));
	}

	@Test
	public void wordFinderNullWhenWordIsNotFound()
	{
		assertEquals(null, gridSolverUtil.findWord(singleWordFileParser.getLetterGrid(), singleWordFileParser.getLetterMap().get('D'), "BAT"));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPoints()
	{
		List<Point> correctList = new ArrayList<>();
		correctList.add(new Point(0,0));
		correctList.add(new Point(1, 0));
		correctList.add(new Point(2, 0));
		assertEquals(correctList, gridSolverUtil.findWord(singleWordFileParser.getLetterGrid(), singleWordFileParser.getLetterMap().get('D'), singleWordFileParser.getWordsList().get(0)));
	}
}
