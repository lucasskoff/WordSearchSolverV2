import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class FileParser
{
	private File parsableFile;
	List<String> wordsList;
	FileParser(File file)
	{
		parsableFile = file;
		wordsList = new ArrayList<>();
	}

	void parseFile(){
		try {
			Scanner fileScanner = new Scanner(parsableFile);
			String topLine = fileScanner.nextLine();
			wordsList.addAll(Arrays.asList(topLine.split(",")));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public List<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<String> wordsList) {
		this.wordsList = wordsList;
	}
}
