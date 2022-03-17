package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_15686 {

	// N : 도시의 크기 (NxN)
	// M : 유지시킬 치킨집의 갯수
	public static int N, M;
	
	public static final int INF = (int)1e9;
	public static int result = INF;
	
	public static boolean[] visited;
	
	// 집의 좌표를 가지는 리스트 
	public static ArrayList<Pos> homeList = new ArrayList<Pos>();
	
	// 치킨집의 좌표를 가지는 리스트
	public static ArrayList<Pos> chickenList = new ArrayList<Pos>();
	
	public static class Pos{
		int a;
		int b;
		
		public Pos(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<=N; j++) {
				// 0 : 빈 방
				// 1 : 집
				// 2 : 치킨집
				int type = Integer.parseInt(st.nextToken());
				
				// 집은 homeList에 넣고
				if(type == 1) {
					homeList.add(new Pos(i,j));
				}
				// 치킨집은 chickenList에 넣음
				else if(type == 2) {
					chickenList.add(new Pos(i,j));
				}
			}
		}
		
		br.close();

		// 치킨 리스트의 인덱스 모음
		int[] arr = new int[chickenList.size()];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i;
		}
		
		// 치킨 리스트의 인덱스에 대한 방문 체크용 변수
		visited = new boolean[chickenList.size()];
		
		// M개 만큼 치킨 집 뽑고, 각 집에서의 최소 거리 구하기
		comb(-1, 0, arr.length, arr);
		
		// 결과 출력
		System.out.println(result);
	}	
	
	// 치킨집 조합 뽑기
	public static void comb(int idx, int cnt, int arrSize, int[] arr) {
		// 선택한 숫자의 count가 M이면 계산 
		if(cnt == M) {
			// 선택한 숫자 모음 배열
			int[] checked = new int[M];
			
			int index = 0;
			
			for(int i=0; i<arrSize; i++) {
				// 방문체크된 변수라면
				if(visited[i] == true) {
					// check에 넣고
					checked[index] = arr[i];
					index++;
				}
			}
			
			// 현재 경우의 최단 거리. 0으로 초기화 
			int tempResult = 0;
			
			// 집의 수 만큼 반복
			for(int i=0; i<homeList.size(); i++) {
				Pos home = homeList.get(i);
				
				// 현재 집 기준으로 치킨집까지의 최단거리 변수. INF로 초기화
				int distance = INF;
				
				// 위에서 선택한 치킨 집가져오기
				for(int j=0; j<M; j++) {
					Pos chicken = chickenList.get(checked[j]);
					
					// 절댓값으로 거리를 구하고
					int temp = Math.abs(home.a - chicken.a) + Math.abs(home.b - chicken.b);
					
					// 최단거리를 구해야하므로 작은 것으로 갱신
					distance = Math.min(distance, temp);
				}
				
				// 모든 치킨집을 확인한 후
				// 현재 집의 치킨집에 대한 최단거리를 추가한다.
				tempResult += distance;
			}
			
			// 방금 구한 것이 더 짧다면 이것으로 갱신
			result = Math.min(result, tempResult);
		}
		
		// 선택한 숫자의 count가 M이 아니고, 범위를 벗어나면 무시 
		else if(idx >= arrSize) {
			return;
		}
		
		// 위의 경우가 아니라면 숫자 고르기
		else {
			for(int i=idx+1; i<arrSize; i++) {
				
				// 방문하지 않은 숫자만 방문
				if(visited[i] == false) {
					// 방문체크 후
					visited[i] = true;
					comb(i, cnt+1, arrSize, arr);
					
					// 방문체크 해제 (백트래킹 기법)
					visited[i] = false;
				}
			}
		}
    }

}