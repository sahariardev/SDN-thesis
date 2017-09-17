package com.SDN.fileread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenerateNetwork {
	
	public void genereate()
	{
		File f=new File("input.txt");
		
		int numberofnodes=0;
		
		try {
			Scanner sc=new Scanner(f);
			
			if(sc.hasNextLine())
			{
				numberofnodes=sc.nextInt();
				
			}
			if(sc.hasNextLine())
			{
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
