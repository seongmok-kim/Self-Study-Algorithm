package baekjoon.level.level_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_1193 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		// 이전 대각선 까지의 칸 갯수
		int beforeSum = 0;
		
		// 현재 대각선의 칸 갯수
		int currentSum = 1;
		
		while(true) {
			
			// N이 현재 대각선을 포함하여 안 쪽에 있는 경우
			if(N <= (beforeSum + currentSum)) {
				// 현재 대각선이 홀수일 땐 분자가 (N-이전대각선 칸의 합)
				if(currentSum %2 == 0) {
					// 분자 + 분모 = 현재대각선의 칸 갯수 + 1
					// 분자
					int nume = (N-beforeSum);
					
					//분모
					int deno = (currentSum+1)-nume;
					
					// 결과값 출력 
					System.out.println(nume + "/" + deno);
					
					break;
				}
				// 현재 대각선이 짝수일 땐 분모가 (N-이전대각선 칸의 합)
				else {
					// 분자 + 분모 = 현재대각선의 칸 갯수 + 1
					
					//분모
					int deno = (N-beforeSum);
					
					// 분자
					int nume = (currentSum+1)-deno;
					
					// 결과값 출력 (분자 + 분모 = 현재대각선의 칸 갯수 + 1)
					System.out.println(nume + "/" + deno);
					
					break;
				}
			}
			// N이 현재 대각선범위보다 더 멀리 있는 경우
			else {
				beforeSum += currentSum;
				currentSum ++;
				
			}
		}
		
	}

}
