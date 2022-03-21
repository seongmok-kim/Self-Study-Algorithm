package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_15649 {

	public static int N, M;
	
	public static boolean[] visited;
	
	public static void calcPerm(int count, int[] arr) {
		// 뽑은 갯수가 M이랑 같으면
		// 출력후 함수 종료
		if(count >= M) {
			for(int i=0; i<arr.length; i++) {
					System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		// 하나씩 고름
		for(int i=0; i<N; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				arr[count] = i+1;
				calcPerm(count+1, arr);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		int[] arr = new int[M];
		
		calcPerm(0, arr);
	}

}