package baekjoon_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_16234 {

	public static int N, L, R;
	
	public static int[][] map;
	public static boolean[][] visited;
	
	public static ArrayList<ArrayList<Pos>> list = new ArrayList<>();
	public static int[][] sector;
	
	public static int[] xmove = {1,-1,0,0};
	public static int[] ymove = {0,0,1,-1};
	public static boolean flag = false;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		sector = new int[N][N];
				
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		
		while(true) {
			list = new ArrayList<>();
			int sectorCount = 0;
			
			// 1. 구역 찾기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					boolean flag = false; 
					for(int k=0; k<4; k++) {
						int tempY = i+ymove[k];
						int tempX = j+xmove[k];
						
						
						// 맵의 범위를 벗어났다면
						if(tempY < 0 || tempY >= N || tempX < 0 || tempX >= N) {
							continue;
						}
						else {
							int difference = Math.abs(map[i][j] - map[tempY][tempX]);
							
							if(difference >= L && difference <= R && difference != 0) {
								int sectorNum = 0;
								
								if(sector[tempY][tempX] != 0) {
									sectorNum = sector[tempY][tempX];
								}
								else if(sector[i][j] != 0) {
									sectorNum = sector[i][j];
								}
								else {
									flag = true;
									sectorNum = sectorCount+1;
									
								}
								
								sector[i][j] = sectorNum;
							}
						}
					}
					if(flag) 
						sectorCount++;
				}
			}
			
			if(sectorCount == 0) {
				break;
			}
			cnt++;
			
			
			// 2. 구역 인구수 배분하기
			ArrayList<ArrayList<Pos>> list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(sector[i][j] != 0) {
						//list.
					}
				}
			}
			
			
			
			System.out.println("=======================");
			System.out.println("횟수 : " + cnt + ", 구역 : "+sectorCount);
			for(int i=0; i<N; i++) {
				System.out.println();
				for(int j=0; j<N; j++) {
					System.out.print(sector[i][j] + " ");
				}
			}
			
			System.out.println();
			System.out.println("======================");
		}
		
		System.out.println(cnt);
	}
	

	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}