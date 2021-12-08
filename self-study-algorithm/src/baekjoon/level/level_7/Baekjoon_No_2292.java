package baekjoon.level.level_7;

import java.util.Scanner;

public class Baekjoon_No_2292 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1~6은 5, 6~17은 11, 17~34는 17처럼 기존 차이나는 숫자의 +6임. 
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		// 입력받은 수가 1인 경우 1임.
		int result = 1;
		
		// 입력받은 수가 1이 아닌 경우에 
		if(num != 1) {
			int startNum = 2;
			int endNum;
			
			for(int i = 1;true;i++) {
				endNum = startNum + (6*i) -1;
				if(startNum <= num && num <= endNum ) {
					result += i;
					
					break;
				}
				startNum = endNum + 1;
			}
		}
		
		System.out.println(result);
	}

}
