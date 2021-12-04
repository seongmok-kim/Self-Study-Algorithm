package baekjoon.level.level_7;

import java.util.Scanner;

public class Baekjoon_No_1152 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		String str = in.nextLine();
		String[] strArr = str.split(" ");
		
		int total = strArr.length;

		for(int i=0;i<strArr.length;i++){
		    if(strArr[i].equals("")){
		        total--;
		    }
		}
		
		System.out.println(total);
	}

}
