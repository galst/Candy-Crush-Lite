package hw4;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;


public class FileManager {
	
	
	// This method writes a new high score to a new line in a text file.
	// Catches / throws exception if needed.
	public void writeToFile(String newScore) throws Exception {
		while (true) {
				File tFile = new File("Leaderboard.txt");
				try {
					FileWriter tOut = new FileWriter(tFile,true);
					tOut.write(newScore);
					tOut.write("\n");
					tOut.close();
					return; 
				} catch (IOException e) {
					reportError(e);
				}
		}
		
	}

	// This method read a file line by line, and parses each string (line of text).
	// Uses a comparator to sort the scores by their values (high to low).
	public void readFile(String[][] obs){
		
				
		try{
			  FileInputStream fstream = new FileInputStream("Leaderboard.txt");
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  //Read File Line By Line
			  String str;
			  int i = 0;
			  while ((str = br.readLine()) != null && i < 11)   {
			  // Print the content on the console
				  String[] tokens = str.split(",");
				  obs[i][0] = i+"";
				  obs[i][1] = tokens[0];
				  obs[i][2] = tokens[1];
				  i++;
			  }
			//Close the input stream
			  in.close();

			  while (i < 11){
				  obs[i][0] = i+"";
				  obs[i][1] = 0 + "";
				  obs[i][2] = 0 + "";
				  i++;

			  }
			  Arrays.sort(obs, new Comparator<String[]>() {
		        @Override
		      public int compare(String[] entry1, String[] entry2) {
		            int time1 = Integer.parseInt(entry1[2]);
		            int time2 = Integer.parseInt(entry2[2]);
		            return time2-time1;
		        }
			  });
			  
			for (int j = 0; j < 11; j++){
				  obs[j][0] = j+"";
			  }
			    }catch (Exception e){//Catch exception if any
			   }

		
	// Method that print out an error if there is a problem with writing to the file.	
	}
	private void reportError(Exception e) {
		System.err.println("Could not write to file");
	}
	
}
