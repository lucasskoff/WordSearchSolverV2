import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class GridSolverUtil
{
	private Direction[] directions;
	private char[][] letterGrid;
	private Map<Character, List<Point>> firstLetterMap;
	private List<String> wordsList;

	GridSolverUtil(FileParser fileParser)
	{
		directions = Direction.values();
		letterGrid = fileParser.getLetterGrid();
		firstLetterMap = fileParser.getLetterMap();
		wordsList = fileParser.getWordsList();
	}

	Map<String, List<Point>> findAllWords()
	{
		Map<String, List<Point>> wordPointMap = new TreeMap<>();
		for(String word : wordsList) {
			List<Point> wordPoints = findWord(firstLetterMap.get(word.charAt(0)), word);
			if (wordPoints != null) {
				wordPointMap.put(word, wordPoints);
			}
		}
		return wordPointMap;
	}

	List<Point> findWord(List<Point> firstLetterPointList, String word)
	{
		for(Point point : firstLetterPointList) {
			for(Direction direction : directions) {
				if(canWordFitInDirection(direction, letterGrid.length, point, word.length())) {
					String wordFound = buildWord(direction, point, word.length());
					if (wordFound.equalsIgnoreCase(word)) {
						return collectPoints(direction, point, word.length());
					}
				}
			}
		}

		return null;
	}

	private boolean canWordFitInDirection(Direction direction, int gridLength, Point firstLetterPoint, int wordLength)
	{
		int XIndexOfLastLetter = (int)firstLetterPoint.getX() + (wordLength - 1) * direction.xDir();
		int YIndexOfLastLetter = (int)firstLetterPoint.getY() + (wordLength - 1) * direction.yDir();
		return XIndexOfLastLetter >= 0 && XIndexOfLastLetter <= gridLength - 1 && YIndexOfLastLetter >= 0 && YIndexOfLastLetter <= gridLength - 1;
	}

	private String buildWord(Direction direction, Point firstLetterPoint, int wordLength)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < wordLength; i++) {
			stringBuilder.append(letterGrid[getIndexOfNextChar((int) firstLetterPoint.getY(), direction.yDir(), i)][getIndexOfNextChar((int) firstLetterPoint.getX(), direction.xDir(), i)]);
		}
		return stringBuilder.toString();
	}

	private List<Point> collectPoints(Direction direction, Point firstLetterPoint, int wordLength)
	{
		List<Point> pointsList = new ArrayList<>();
		for(int i = 0; i < wordLength; i++) {
			pointsList.add(new Point(getIndexOfNextChar((int)firstLetterPoint.getX(), direction.xDir(), i), getIndexOfNextChar((int)firstLetterPoint.getY(), direction.yDir(), i)));
		}
		return pointsList;
	}

	private int getIndexOfNextChar(int firstLetterIndex, int directionModifier, int currentIndex)
	{
		return firstLetterIndex + currentIndex * directionModifier;
	}
}
