import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GridSolverUtilTest
{
	private List<Point> singleWordFirstLetterPointList;
	private List<String> singleWordList;

	private List<Point> correctListDog;
	private List<Point> correctListDon;

	private FileParser doubleWordFileParser;
	private GridSolverUtil gridSolverSingleWordUtil;
	private GridSolverUtil gridSolverDoubleWordUtil;
	private Map<String, List<Point>> correctMap;
	@Before
	public void init()
	{
		File singleWordFile = new File("resources/horizontalSingleWordThreeByThreeGrid.txt");
		FileParser singleWordFileParser = new FileParser(singleWordFile);
		gridSolverSingleWordUtil = new GridSolverUtil(singleWordFileParser);
		singleWordFirstLetterPointList = singleWordFileParser.getLetterMap().get('D');
		singleWordList =  singleWordFileParser.getWordsList();

		File doubleWordFile = new File("resources/horizontalDoubleWordThreeByThreeGrid.txt");
		doubleWordFileParser = new FileParser(doubleWordFile);
		gridSolverDoubleWordUtil = new GridSolverUtil(doubleWordFileParser);

		Point[] correctPoints = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};
		correctListDog = Arrays.asList(correctPoints);

		Point[] correctPointsDon = {new Point(0, 0), new Point(0, 1), new Point(0, 2)};
		correctListDon = Arrays.asList(correctPointsDon);

		correctMap = new HashMap<>();
		correctMap.put("DOG", correctListDog);
	}

	@Test
	public void wordFinderNullWhenWordIsNotFound()
	{
		assertEquals(null, gridSolverSingleWordUtil.findWord(singleWordFirstLetterPointList, "BAT"));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPoints()
	{
		assertEquals(correctListDog, gridSolverSingleWordUtil.findWord(singleWordFirstLetterPointList, singleWordList.get(0)));
	}

	@Test
	public void wordFinderReturnsCorrectListOfPointsNonHorizontalDirection()
	{
		assertEquals(correctListDon, gridSolverDoubleWordUtil.findWord(doubleWordFileParser.getLetterMap().get('D'), doubleWordFileParser.getWordsList().get(1)));
	}

	@Test
	public void wordFinderCreatesMapForWord()
	{
		assertEquals(correctMap, gridSolverSingleWordUtil.findAllWords());
	}

	@Test
	public void wordFinderCreatesMapForMultipleWords()
	{
		correctMap.put("DON", correctListDon);
		assertEquals(correctMap, gridSolverDoubleWordUtil.findAllWords());
	}
}
