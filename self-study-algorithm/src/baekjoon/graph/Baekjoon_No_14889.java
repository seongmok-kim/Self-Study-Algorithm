package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_14889 {

	// 능력치 표 변수
	public static int[][] map;

	// 방문 체크 변수
	public static boolean[] visited;
	
	public static int N;
	public static int halfN;
	
	// 결과 변수
	public static int result = Integer.MAX_VALUE;
	
	
	// DFS함수
	public static void DFS(int start, int count) {
		// 현재 방문한 변수가 N의 절반이면 능력치 확인 후 종료
		if(count == halfN) {
			check();
			
			return;
		}
		
		// DFS 탐색을 이용하여 한 명씩 방문
		// 방문 후에는 다시 미방문처리
		for(int i=start+1; i<N; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				DFS(i, count+1);
				visited[i] = false;
			} 
		}
	}
	
	// 능력치 계산
	public static void check() {
		int[] home = new int[halfN];
		int[] away = new int[halfN];
		
		int homeCnt = 0;
		int awayCnt = 0;
		
		// 방문되어있는 사람은 home 팀에 넣고 아니라면 away팀에 넣기
		for(int i=0; i<N; i++) {
			if(visited[i] == true) {
				home[homeCnt] = i;
				homeCnt++;
			}
			else {
				away[awayCnt] = i;
				awayCnt++;
			}
		}
		
		// 각 팀 별로 능력치 추출 후
		int homePower = calc(home);
		int awayPower = calc(away);
		
		// 차이 구하고
		int powerGap = Math.abs(homePower-awayPower);
		
		// 그 차이가 result보다 작으면 갱신
		result = Math.min(result, powerGap);
	}
	
	// 능력치 추출
	public static int calc(int[] teams) {
		int sum = 0;
		
		for(int i=0; i<teams.length; i++) {
			for(int j=i+1; j<teams.length; j++) {
				sum += (map[teams[i]][teams[j]] + map[teams[j]][teams[i]]);
			}
		}
		
		return sum;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		halfN = N/2;
		
		map = new int [N][N];
		
		// 방문 체크 변수
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// DFS탐색을 이용하여 풀이
		DFS(-1,0);
		
		System.out.println(result);
	}

}