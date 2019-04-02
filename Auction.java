import java.util.*;
import java.io.*;

public class Auction{
	
	public Auction(){

	}

	public void readData(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("data.txt"));
			String strLine;
			while ((strLine = br.readLine()) != null){
				try{
   					String strArray[] = strLine.split(" ");
    			}
    			catch(NumberFormatException npe){
    				System.out.println("Error 2");
    			}
			}
			System.out.print(strArray);
		}
		catch(Exception e){//Catch exception if any
      		System.err.println("Error: " + e.getMessage());
    	}
    	finally{
     		in.close();
    	}
	}

	public static void main(String args[]){
		readData();
	}
}