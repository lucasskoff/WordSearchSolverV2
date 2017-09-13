import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class GridSolverUtil
{
	static boolean canWordFitInDirection(Point firstLetterPoint, int gridLength, int wordLength, Direction direction)
	{
		int XIndexOfLastLetter = (int)firstLetterPoint.getX() + (wordLength - 1) * direction.xDir();
		int YIndexOfLastLetter = (int)firstLetterPoint.getY() + (wordLength - 1) * direction.yDir();
		return XIndexOfLastLetter >= 0 && XIndexOfLastLetter <= gridLength - 1 && YIndexOfLastLetter >= 0 && YIndexOfLastLetter <= gridLength - 1;
	}

	static List<Point> findWord(Direction[] directions, char[][] letterGrid, List<Point> firstLetterPoints, String word)
	{
		List<Point> pointsList = new ArrayList<>();
		for(Point point : firstLetterPoints)
		{
			String wordFound = buildWord(directions[0], letterGrid, point, word.length());
			if(wordFound.equalsIgnoreCase(word)){
				return collectPoints(directions[0], point, word.length());
			}
		}

		return null;
	}

	private static String buildWord(Direction direction, char[][] letterGrid, Point firstLetterPoint,  int wordLength)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < wordLength; i++)
		{
			stringBuilder.append(letterGrid[getIndexOfNextChar((int) firstLetterPoint.getY(), direction.yDir(), i)][getIndexOfNextChar((int) firstLetterPoint.getX(), direction.xDir(), i)]);
		}
		return stringBuilder.toString();
	}

	private static List<Point> collectPoints(Direction direction, Point firstLetterPoint, int wordLength)
	{
		List<Point> pointsList = new ArrayList<>();
		for(int i = 0; i < wordLength; i++){
			pointsList.add(new Point(getIndexOfNextChar((int)firstLetterPoint.getX(), direction.xDir(), i), getIndexOfNextChar((int)firstLetterPoint.getY(), direction.yDir(), i)));
		}
		return pointsList;
	}

	private static int getIndexOfNextChar(int firstLetterIndex, int directionModifier, int currentIndex)
	{
		return firstLetterIndex + currentIndex * directionModifier;
	}
}
