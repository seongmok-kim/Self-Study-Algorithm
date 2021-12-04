package baekjoon.level.level_5;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_8958 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    
	    int num = Integer.parseInt(bf.readLine());
	    String[] strArray = new String[num];
	    
	    for(int i=0;i<num;i++){
	        strArray[i] = bf.readLine();
	        String[] tempArray = strArray[i].split("");
	        
	        int count = 0;
	        int sum = 0;
	        for(int j=0;j<tempArray.length;j++){
	            if("O".equals(tempArray[j])){
	                int score = 1 + count;
	                
	                count++;
	                sum += score;
	            }
	            else{
	                count = 0;
	            }
	        }
	        System.out.println(sum);
	    }
	}

}
