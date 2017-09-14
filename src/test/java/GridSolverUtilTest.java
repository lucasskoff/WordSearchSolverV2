import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

		correctMap = new TreeMap<>();
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

	@Test
	public void wordFinderCreatesCorrectMapForLargeWordSearch() {
		String filename = "resources/bigGridFromKata.txt";
		FileParser fileParser = new FileParser(new File(filename));
		GridSolverUtil gridSolverUtil = new GridSolverUtil(fileParser);
		assertEquals(correctMap(), gridSolverUtil.findAllWords());
	}

	private Map<String, List<Point>> correctMap() {
		Point[] correctPointsBones = {new Point(0, 6), new Point(0, 7), new Point(0, 8), new Point(0, 9), new Point(0, 10)};
		Point[] correctPointsKhan = {new Point(5, 9), new Point(5, 8), new Point(5, 7), new Point(5, 6)};
		Point[] correctPointsKirk = {new Point(4, 7), new Point(3, 7), new Point(2, 7), new Point(1, 7)};
		Point[] correctPointsScotty = {new Point(0, 5), new Point(1, 5), new Point(2, 5), new Point(3, 5), new Point(4, 5), new Point(5, 5)};
		Point[] correctPointsSpock = {new Point(2, 1), new Point(3, 2), new Point(4, 3), new Point(5, 4), new Point(6, 5)};
		Point[] correctPointsSulu = {new Point(3, 3), new Point(2, 2), new Point(1, 1), new Point(0, 0)};
		Point[] correctPointsUhura = {new Point(4, 0), new Point(3, 1), new Point(2, 2), new Point(1, 3), new Point(0, 4)};
		Map<String, List<Point>> kataTreeMap = new TreeMap<>();
		kataTreeMap.put("BONES", Arrays.asList(correctPointsBones));
		kataTreeMap.put("KHAN", Arrays.asList(correctPointsKhan));
		kataTreeMap.put("KIRK", Arrays.asList(correctPointsKirk));
		kataTreeMap.put("SCOTTY", Arrays.asList(correctPointsScotty));
		kataTreeMap.put("SPOCK", Arrays.asList(correctPointsSpock));
		kataTreeMap.put("SULU", Arrays.asList(correctPointsSulu));
		kataTreeMap.put("UHURA", Arrays.asList(correctPointsUhura));
		return kataTreeMap;
	}
}
