package baekjoon.level.level_6;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_1065 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt()+1;
	    
	    int count = 0;
	    for(int i=1;i<n;i++){
	        if(hansu(i)){
	            count++;
	        }
	    }
	    
	    System.out.println(count);
	}

	public static boolean hansu(int num){
	    String strNum = Integer.toString(num);
	    String[] strArrayNum = strNum.split("");
	    boolean res = true;
	    int gap=0;
	    int temp=0;
	    for(int i=0;i<strArrayNum.length-1;i++){
	        if(i==0){
	            gap = Integer.parseInt(strArrayNum[i]) - Integer.parseInt(strArrayNum[i+1]);

	        }
	        else{
	            temp = Integer.parseInt(strArrayNum[i]) - Integer.parseInt(strArrayNum[i+1]);
	            if(temp != gap)
	                res = false;
	        }
	    }
	    return res;
	}
}
