import java.awt.*;

class GridSolverUtil
{
	static boolean canWordFitInDirection(Point firstLetterPoint, int gridLength, int wordLength, Direction direction)
	{
		int XIndexOfLastLetter = (int)firstLetterPoint.getX() + (wordLength - 1) * direction.xDir();
		int YIndexOfLastLetter = (int)firstLetterPoint.getY() + (wordLength - 1) * direction.yDir();
		return XIndexOfLastLetter >= 0 && XIndexOfLastLetter <= gridLength - 1 && YIndexOfLastLetter >= 0 && YIndexOfLastLetter <= gridLength - 1;
	}
}