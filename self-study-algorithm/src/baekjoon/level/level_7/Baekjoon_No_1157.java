package baekjoon.level.level_7;

import java.util.Scanner;

public class Baekjoon_No_1157 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		String str = in.nextLine();
		String[] strArr = str.split("");
		int manyCount = 0;
		int manyAlphabet=0;
		
		for(int i=65; i<91; i++){
		    int count=0;
		    for(int j=0;j<strArr.length;j++){
		        int temp = (int)str.charAt(j);
		        if(temp == i || temp == (i+32) ){
		            count++;
		        }
		    }
		    if(count>manyCount){
		        manyCount = count;
		        manyAlphabet = i;
		    }
		    else if(count == manyCount){
		        manyAlphabet = 63;
		    }
		}
		System.out.printf("%c", (char)manyAlphabet);
	}

}
