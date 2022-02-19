package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_7569 {

	// N : 상자의 가로 길이
	// M : 상자의 세로 길이
	// H : 상자의 수
	public static int N,M,H;
	
	public static int[][][] graph;
	
	public static boolean[][][] visited;
	
	public static int result = 0;
	
	public static ArrayList<Node> doneTomato = new ArrayList<Node>();
	
	public static int[] nmove = {1,-1,0,0,0,0};
	public static int[] mmove = {0,0,1,-1,0,0};
	public static int[] hmove = {0,0,0,0,1,-1};
	
	public static int before0 = 0;
	public static int before1 = 0;
	public static int after = 0;
	
	public static void BFS() {
		Queue<Node> q = new LinkedList<Node>(); 
		
		// 익은 토마토를 큐에 넣기
		for(int i=0;i<doneTomato.size(); i++) {
			Node node = doneTomato.get(i);
			
			int n=node.n;
			int m=node.m;
			int h=node.h;
			
			// 큐에 넣기
			q.offer(new Node(n,m,h,0));
		}
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			Node node = q.poll();
			int n=node.n;
			int m=node.m;
			int h=node.h;
			int day=node.day;
			
			// 큐에 한 토마토가 여러개 있을 수 있으니, 여기서도 현재 토마토에 방문했었는지 안했는지 확인
			if(visited[h][m][n]==true) {
				continue;
			}
			
			// 방문 체크
			visited[h][m][n]=true;
			
			// 토마토가 없을 경우
			if(graph[h][m][n] == -1) {
				continue;
			}
			
			// 토마토가 있으면 
			else {
				
				// day 갱신
				result = Math.max(day, result);
				
				// 토마토 갯수 증가
				after+=1;
				
				// 상하좌우위아래 확인
				for(int i=0;i<6;i++) {
					int tempN = n+nmove[i];
					int tempM = m+mmove[i];
					int tempH = h+hmove[i];
					int tempDay = day +1;
					
					// 범위를 벗어난 경우
					if(tempN>=N || tempN<0 || tempM>=M || tempM<0 || tempH>=H || tempH<0) {
						continue;
					}
					
					// 방문안했던 토마토만 큐에 등록
					if(visited[tempH][tempM][tempN] == false) {
						q.offer(new Node(tempN,tempM,tempH,tempDay));
					}
				}
			}
		}
	}
	
	// 노드클래스선언
	public static class Node{
		int n;
		int m;
		int h;
		int day;
		
		public Node(int a, int b, int c, int d) {
			n=a;
			m=b;
			h=c;
			day=d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new int[H][M][N];
		visited = new boolean[H][M][N];
		
		// graph 입력
		for(int k=0; k<H; k++) {
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j=0;j<N;j++) {
					int tomato = Integer.parseInt(st.nextToken());
					graph[k][i][j] = tomato;
					
					// -1:토마토 없음, 0:일반 토마토, 1:익은 토마토
					// before0 : 일반 토마토의 갯수
					if(tomato == 0) {
						before0+=1;
					}
					// 익은 토마토 위치 기억
					// before1 : 익은 토마토의 갯수
					if(tomato == 1) {
						doneTomato.add(new Node(j,i,k,0));
						before1+=1;
					}
				}
			}
		}
		
		// 애초에 일반 토마토가 없었으면 0 출력
		if(before0 == 0) {
			System.out.println(0);
			return;
		}
		
		BFS();
		
		// BFS 돌리기 전 (일반토마토 + 익은토마토)과 BFS하고 체크한 토마토의 갯수랑 다르면 모든 토마토가 익은 것이 아니므로 -1 출력
		if(after != before0+before1) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);
		}
	}

}