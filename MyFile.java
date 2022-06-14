package z15game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyFile {
	String filename = "C:\\Users\\HOME\\eclipse-workspace\\z15game\\bestScore.txt";
	
	public void writeFile(){
		writeFile("10000");
	}
	
	//Overloading
	public void writeFile(String data){	 
	
	   	try {
	   		FileWriter myWriter = new FileWriter(filename);   	  	 
	   	   	myWriter.write(data);
	   	   	myWriter.close();
	   	   	System.out.println("Successfully wrote to the file.");
	   	   	
	   	} catch (IOException e) {
	   	   	System.out.println("An error occurred.");
	   	   	e.printStackTrace();
	   	}
	}
	
	public String readFile(){
		String data="";
	   	try {
	   		File myObj = new File(filename);
	   	   	Scanner myReader = new Scanner(myObj);
	   	   	while (myReader.hasNextLine()) {
	   			   data += myReader.nextLine();   	   	 
	   	   	}
	   	   	myReader.close();
	   	} catch (FileNotFoundException e) {
	   		System.out.println("An error occurred.");
	   	   	e.printStackTrace();
	   	}
	   	return data;    
	}
}
