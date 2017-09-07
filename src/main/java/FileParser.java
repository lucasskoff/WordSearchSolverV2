import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

class FileParser
{
	private static final String delimConst = ",";
	private File parsableFile;
	private List<String> wordsList;
	private char[][] letterGrid;
	private Map<Character, List<Point>> letterMap;
	FileParser(File file)
	{
		parsableFile = file;
		wordsList = new ArrayList<>();
	}

	void parseFile(){
		try {
			Scanner fileScanner = new Scanner(parsableFile);
			String currentLine = fileScanner.nextLine();
			wordsList.addAll(Arrays.asList(currentLine.split(delimConst)));

			initializeMap();

			currentLine = fileScanner.nextLine();
			int lengthOfLine = Integer.divideUnsigned(currentLine.length(), 2) + 1;
			letterGrid = new char[lengthOfLine][lengthOfLine];
			letterGrid[0] = parseGridLine(currentLine, lengthOfLine, 0);
			for(int i = 1; i < letterGrid.length; i++){
				if(fileScanner.hasNext()) {
					letterGrid[i] = parseGridLine(fileScanner.nextLine(), lengthOfLine, i);
				}else{
					Arrays.fill(letterGrid[i], 'A');
				}
			}


		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	private char[] parseGridLine(String letterGridLine, int length, int curRow){
		char[] line = new char[length];
		String[] letters = letterGridLine.split(delimConst);
		for(int j = 0; j < letters.length; j++){
			char firstLetter = letters[j].charAt(0);
			line[j] = firstLetter;
			if(letterMap.containsKey(firstLetter)){
				letterMap.get(firstLetter).add(new Point(curRow, j));
			}
		}
		return line;
	}

	private void initializeMap(){
		letterMap = new HashMap<>();
		for(String word : wordsList){
			letterMap.put(word.charAt(0), new ArrayList<>());
		}
	}

	List<String> getWordsList() {
		return wordsList;
	}

	char[][] getLetterGrid() {
		return letterGrid;
	}

	Map<Character, List<Point>> getLetterMap() {
		return letterMap;
	}
}
