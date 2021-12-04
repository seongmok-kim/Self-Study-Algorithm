package baekjoon.level.level_6;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_4673 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    boolean[] checkArr = new boolean[10001];

	    
	    for(int i=0;i<checkArr.length;i++){
	        int temp = selfNum(i);
	        if(temp < checkArr.length){
	            checkArr[temp] = true;
	        }
	    }
	    
	    for(int j=0;j<checkArr.length;j++){
	        if(checkArr[j] == false){
	            bw.write(j+"\n");
	        }
	    }
	    
        bw.flush();
        bw.close();
	}

	public static int selfNum(int num){
	    String strNum = Integer.toString(num);
	    String[] strArrayNum = strNum.split("");
	    int result = num;
	    
	    for(int i=0;i<strArrayNum.length;i++){
	        result += Integer.parseInt(strArrayNum[i]);
	    }
	    
	    return result;
	}
}
