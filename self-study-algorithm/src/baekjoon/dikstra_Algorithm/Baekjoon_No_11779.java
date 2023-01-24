package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/11779
public class Baekjoon_No_11779 {

	public static class Bus implements Comparable<Bus>{
		int pos;
		long cost;
		ArrayList<Integer> path = new ArrayList<>();
		
		public Bus(int pos, long cost) {
			this.pos = pos;
			this.cost = cost;
		}
		
		public void copy(ArrayList<Integer> list) {
			this.path = list;
			path = (ArrayList<Integer>) list.clone();
		}

		@Override
		public int compareTo(Bus o) {
			if(this.cost > o.cost) 
				return 1;
			else 
				return -1;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());		// 도시의 갯수
		int M = Integer.parseInt(br.readLine());		// 버스의 갯수
		long[] d = new long[N+1];
		boolean[] visited = new boolean[N+1];
		
		final long INF = Long.MAX_VALUE;
		ArrayList<Integer> path = new ArrayList<>();
		
		ArrayList<ArrayList<Bus>> lines = new ArrayList<>();	 
		for(int i=0; i<=N; i++) {
			lines.add(new ArrayList<Bus>());
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			
			lines.get(start).add(new Bus(end, cost));		// 단방향
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int START = Integer.parseInt(st.nextToken());			// 시작점
		int END = Integer.parseInt(st.nextToken());				// 도착점
		
		Arrays.fill(d, INF);
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		
		d[START] = 0;
		Bus start = new Bus(START, 0);
		start.path.add(START);
		pq.offer(start);
		
		while(!pq.isEmpty()) {
			Bus bus = pq.poll();
			int pos = bus.pos;
			long cost = bus.cost;
			
			if(visited[pos])
				continue;
			visited[pos] = true;
			
			for(int i=0; i<lines.get(pos).size(); i++) {
				Bus next = lines.get(pos).get(i);
				int nextPos = next.pos;
				long nextCost = next.cost;
				
				long sum = cost + nextCost;
				if(d[nextPos] > sum) {
					d[nextPos] = sum;
					Bus target = new Bus(nextPos, sum);
					
					// 경로 기록
					target.copy(bus.path);
					target.path.add(nextPos);
					
					// END에 대한 최단 경로 갱신 시, path 기록
					if(nextPos == END) {
						path = (ArrayList<Integer>) target.path.clone();		
					}
					pq.offer(target);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(d[END]+"\n");					// 최단거리 출력
		sb.append(path.size()+"\n");			// 최단거리 시, 방문하는 도시의 갯수 출력
		for(int i=0; i<path.size(); i++) {		// 최단거리 경로 출력
			sb.append(path.get(i) + " ");
		}
		
		System.out.println(sb.toString());
	}
}
