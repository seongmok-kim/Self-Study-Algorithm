package baekjoon.level.level_7;

import java.util.Scanner;

public class Baekjoon_No_2292 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 2~7은 6, 8~19는 12, 20~37는 18, 38~61은 24.
		// 즉, 6n만큼 증가되는 규칙을 가지고 있다. 
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		// 입력받은 수가 1인 경우 1임.
		int result = 1;
		
		// 입력받은 수가 1이 아닌 경우에 
		if(num != 1) {
			int startNum = 2;
			int endNum;
			
			for(int i = 1;true;i++) {
				// 시작점도 포함이므로 1을 빼준다.
				endNum = startNum + (6*i) -1;
				
				// 입력받은 수가 startNum, endNum 사이에 있을 
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
