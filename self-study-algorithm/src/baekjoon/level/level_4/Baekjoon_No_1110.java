package baekjoon.level.level_4;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_1110 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
        int num = sc.nextInt();
        int initNum = num;
        int count = 0;
        while(true){
            count++;
            int num10 = num / 10;
            int num1 = num % 10;
            int newNum = num10 + num1;
            
            num = (num1*10)+(newNum%10);
            if(initNum == num)
                break;
        }
        System.out.println(count);
	}

}
