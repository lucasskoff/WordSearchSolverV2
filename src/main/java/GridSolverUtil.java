import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class GridSolverUtil
{
	private Direction[] directions;

	GridSolverUtil()
	{
		directions = Direction.values();
	}

	List<Point> findWord(char[][] letterGrid, List<Point> firstLetterPointList, String word)
	{
		for(Point point : firstLetterPointList)
		{
			String wordFound = buildWord(directions[0], letterGrid, point, word.length());
			if(wordFound.equalsIgnoreCase(word)){
				return collectPoints(directions[0], point, word.length());
			}
		}

		return null;
	}

	boolean canWordFitInDirection(Direction direction, int gridLength, Point firstLetterPoint, int wordLength)
	{
		int XIndexOfLastLetter = (int)firstLetterPoint.getX() + (wordLength - 1) * direction.xDir();
		int YIndexOfLastLetter = (int)firstLetterPoint.getY() + (wordLength - 1) * direction.yDir();
		return XIndexOfLastLetter >= 0 && XIndexOfLastLetter <= gridLength - 1 && YIndexOfLastLetter >= 0 && YIndexOfLastLetter <= gridLength - 1;
	}



	private String buildWord(Direction direction, char[][] letterGrid, Point firstLetterPoint,  int wordLength)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < wordLength; i++)
		{
			stringBuilder.append(letterGrid[getIndexOfNextChar((int) firstLetterPoint.getY(), direction.yDir(), i)][getIndexOfNextChar((int) firstLetterPoint.getX(), direction.xDir(), i)]);
		}
		return stringBuilder.toString();
	}

	private List<Point> collectPoints(Direction direction, Point firstLetterPoint, int wordLength)
	{
		List<Point> pointsList = new ArrayList<>();
		for(int i = 0; i < wordLength; i++){
			pointsList.add(new Point(getIndexOfNextChar((int)firstLetterPoint.getX(), direction.xDir(), i), getIndexOfNextChar((int)firstLetterPoint.getY(), direction.yDir(), i)));
		}
		return pointsList;
	}

	private int getIndexOfNextChar(int firstLetterIndex, int directionModifier, int currentIndex)
	{
		return firstLetterIndex + currentIndex * directionModifier;
	}
}
