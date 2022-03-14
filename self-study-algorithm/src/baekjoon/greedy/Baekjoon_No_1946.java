package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1946 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// T : 테스트케이스의 갯수
		int T = Integer.parseInt(br.readLine());
		
		// result : 테스트케이스의 각 결과를 가지는 배열
		int[] result = new int[T];
		
		// 테스트케이스의 수만큼 반복
		for(int i=0; i<T; i++) {
			
			// N : 사람의 수
			int N = Integer.parseInt(br.readLine());

			// 사람이 한 명일 때
			if(N==1) {
				result[i]=1;
				continue;
			}
			
			// 합격할 수 있는 사람의 수
			int count = 0;
			
			int[] scoreMap = new int[N+1];
			for(int j=0; j<N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// s1 : 서류 등수
				// s2 : 면접 등수
				int s1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				
				scoreMap[s1] = s2;
			}
			
			// 서류 등수가 1등이라면 무조건 합격 
			// 1등의 면접 등수를 저장한다.
			int nowS2 = scoreMap[1]; 
			// count 증가
			count++;
			
			// 위에서 1등은 합격시켰으니, 2등부터 확인한다.
			for (int k = 2; k <= N; k++) {
				
				// 합격하기 위해서는 앞 사람의 면접 등수보다 높아야 한다.
                if (nowS2 >= scoreMap[k]) {
                	
                	// 앞 사람의 면접 등수보다 높다면
                	// count 증가
                	count++;
                	
                	// 앞 사람의 면접 등수를 최신화
                	nowS2 = scoreMap[k];
                    
                }
            }
			
			// 결과를 배열에 넣는다.
			result[i] = count;
		}
		
		// 결과 출력
		for(int i=0; i<T; i++) {
			System.out.println(result[i]);
		}
		
	}

}
