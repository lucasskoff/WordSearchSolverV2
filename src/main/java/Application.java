import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public class Application {
	public static void main(String[] args) {
		try {
			if (args.length < 1) {
				System.out.println("Please enter filename as command line argument.");
				System.exit(1);
			} else if (!args[0].endsWith(".txt")) {
				System.out.println("Only .txt files are allowed as command line arguments.");
				System.exit(1);
			}
			String filename = args[0];

			File file = new File(filename);
			if (!file.canRead()) {
				System.out.println("Cannot read file. Please check file path and try again.");
				System.exit(1);
			}

			GridSolverUtil gridSolverUtil = new GridSolverUtil(new FileParser(file));
			Map<String, List<Point>> wordSearchMap = gridSolverUtil.findAllWords();
			System.out.println("Solution: ");
			printHashMap(wordSearchMap);
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Exception thrown while trying to find solution.");
			System.exit(1);
		}
	}

	private static void printHashMap(Map<String, List<Point>> mapToPrint) {
		for (Map.Entry<String, List<Point>> entry : mapToPrint.entrySet()) {
			System.out.println(entry.getKey() + ": " + getStringFromPoints(entry.getValue()));
		}
	}

	private static String getStringFromPoints(List<Point> points) {
		String returnable = "";
		for (Point point : points) {
			returnable = returnable.concat("(" + Integer.toString((int) point.getX()) + "," + Integer.toString((int) point.getY()) + "), ");
		}
		return returnable.substring(0, returnable.length() - 1);
	}
}
