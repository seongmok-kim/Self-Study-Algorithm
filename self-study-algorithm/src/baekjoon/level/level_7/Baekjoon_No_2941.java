package baekjoon.level.level_7;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_2941 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strArr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        
        for(int i=0;i<strArr.length;i++){
            while(true){
                if(str.contains(strArr[i])){
                    str = str.replaceFirst(strArr[i], "0");
                }
                else{
                    break;
                }
            }
        }
        String[] strNum = str.split("");
        System.out.print(strNum.length);
	}

}
