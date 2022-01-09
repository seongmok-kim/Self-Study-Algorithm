package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon_No_2667 {

	// 그래프 변수
	public static int[][] graph;
	
	// 방문 체크용 변수
	public static boolean[][] visited;
	
	// 그래프 크기 변수
	public static int N;
	
	// 이동할 네 가지 방향 정의 (상, 하, 좌, 우) 
    public static int[] dnum1 = {-1, 1, 0, 0};
    public static int[] dnum2 = {0, 0, -1, 1};
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 그래프와 방문체크용 변수에 크기 할당
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		// 단지 내 집의 갯수 기록용 변수
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// 그래프 내부 세팅
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			String[] strArr = str.split("");
			
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(strArr[j]);
			}
		}
		
		// DFS 이용
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int num = DFS(i,j);
				if(num != 0) {
					result.add(num);
				}
			}
		}
		
		// 오름차순으로 정렬
		result.sort(null);
		
		// 단지 수 출력
		System.out.println(result.size());
		
		// 각 단지의 집의 수 출력
		for(int i=0;i<result.size();i++) {
			System.out.println(result.get(i));
		}
		
	}
	
	public static int DFS(int num1, int num2) {
		int num = 0;
		// num1과 num2가 범위를 벗어난 경우 무시
		if(num1 >= N || num1 < 0 || num2 >= N || num2 < 0) {
			return num;
		}
		else {
			// 현재 위치가 방문한 적 없고, 집이 있는 곳이라면
			if(visited[num1][num2] == false && graph[num1][num2] == 1) {
				
				// 방문 체크 
				visited[num1][num2] = true;
				
				// 집 갯수 1 증가
				num++;
				
				// 상하좌우에 집이 있는 지 체크
				for(int i=0;i<4;i++) {
					num += DFS(num1+dnum1[i],num2+dnum2[i]);
				}
			}
			
			return num;
		}
	}
}
