package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_18405 {

	// N : 시험관의 크기, K : 가장 높은 바이러스의 번호 
	public static int N, K;
	
	// 맵
	public static int[][] map;
	
	// 상하좌우 퍼지는 데 사용될 배열들
	public static int[] xmove = {-1,1,0,0};
	public static int[] ymove = {0,0,-1,1};

	// 바이러스의 위치를 담을 큐
	public static Queue<Virus> q = new LinkedList<>();
	
	// 바이러스 클래스 
	public static class Virus implements Comparable<Virus>{
		int y;
		int x;
		int rank;
		
		public Virus(int y, int x, int rank) {
			this.y = y;
			this.x = x;
			this.rank = rank;
		}

		@Override
		public int compareTo(Virus o) {
			// TODO Auto-generated method stub
			if(this.rank < o.rank) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		// 우선순위큐에 넣어서 낮은 바이러스부터 정렬되도록 함
		PriorityQueue<Virus> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				
				// 바이러스일 때만 넣어줌
				if(num != 0) {
					pq.offer(new Virus(i, j, num));
				}
			}
		}
		
		// 우선순위 큐에 있던 내용들(정렬되어있음)을 일반 큐로 복사
		while(!pq.isEmpty()) {
			Virus v = pq.poll();
			q.offer(v);
		}
		
		st = new StringTokenizer(br.readLine());
		int endTime = Integer.parseInt(st.nextToken());
		int endY = Integer.parseInt(st.nextToken()) - 1; 	
		int endX = Integer.parseInt(st.nextToken()) - 1;
		
		// 주어진 초만큼 반복
		for(int i=0; i<endTime; i++) {
			Queue<Virus> q2 = new LinkedList<>();
			
			// q에 있던 걸 q2로 옮기고
			while(!q.isEmpty()) {
				Virus v = q.poll();
				q2.offer(v);
			}
			
			// q2에 있는 것을 모두 탐색
			while(!q2.isEmpty()) {
				// q2에서 꺼낸 바이러스를
				Virus v = q2.poll();
				
				// 상하좌우 확산시키기
				Spread(v);
			}
		}
		
		// 결과 출력
		System.out.println(map[endY][endX]);
	}
	
	// 바이러스 확산
	public static void Spread(Virus v) {
		// 상하좌우 탐색
		for(int i=0; i<4; i++) {
			int tempY = v.y + ymove[i];
			int tempX = v.x + xmove[i];
			
			// 맵의 범위를 벗어나면 무시
			if(tempY < 0 || tempY >= N || tempX < 0 || tempX >= N) {
				continue;
			}else {
				int vRank = map[tempY][tempX];
				
				// 이동할 곳에 바이러스가 아직 없다면
				if(vRank == 0) {
					// 바이러스 퍼트리고
					map[tempY][tempX] = v.rank;
					
					// q에다가 넣기
					q.offer(new Virus(tempY, tempX, v.rank));
				}
			}
		}
	}

}