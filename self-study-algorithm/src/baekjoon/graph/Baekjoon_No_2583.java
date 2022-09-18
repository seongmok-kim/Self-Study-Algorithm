package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_2583 {
	public static int M, N, K;		// M : 맵 세로, N : 맵 가로, K : 직사각형 갯수 
	
	public static boolean[][] map;	// 맵
	
	public static ArrayList<Integer> areaList = new ArrayList<>(); 		// 남은 영역 리스트
	
	// 상하좌우 이동에 사용될 변수들
	public static final int[] ymove = {-1, 1, 0, 0};
	public static final int[] xmove = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[M][N];
		
		// 1. 직사각형 좌표를 받아서 직사각형 그리기. 그린 부분은 true처리
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			makeRectangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		// 2. 맵의 남은 영역 구하기 (BFS 이용)
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == false) {
					map[i][j] = true;
					BFS(i,j);
				}
			}
		}
		
		// 3. 정답 출력
		System.out.println(areaList.size());		// 남은 영역의 갯수 출력 
		areaList.sort(null);						// 오름차순 정렬	
		for(int i=0; i<areaList.size(); i++) {	
			System.out.print(areaList.get(i) + " ");	// 각 사이즈 출력
		}
	}
	
	// 맵의 남은 영역 구하기
	public static void BFS(int y, int x) {
		int size=1;
		Queue<Pos> q = new LinkedList<>();
		
		q.offer(new Pos(y,x));

		while(!q.isEmpty()) {
			Pos pos = q.poll();
			
			for(int i=0; i<4; i++) {
				int tempY = pos.y + ymove[i];
				int tempX = pos.x + xmove[i];
				
				if(tempY < 0 || tempY >= M || tempX < 0 || tempX >= N) {
					continue;
				}
				
				if(map[tempY][tempX] == false) {
					map[tempY][tempX] = true;
					size++;
					q.offer(new Pos(tempY,tempX));
				}
			}
		}
		
		areaList.add(size);
	}
	
	// 직사각형 그리기
	public static void makeRectangle(int leftX, int leftY, int rightX, int rightY) {
		for(int i=leftY; i<rightY; i++) {
			for(int j=leftX; j<rightX; j++) {
				map[i][j] = true;
			}
		}
	}
	
	public static class Pos{
		int y, x;
		
		public Pos(int y, int x) {
			this.y= y;
			this.x= x;
		}
	}
}