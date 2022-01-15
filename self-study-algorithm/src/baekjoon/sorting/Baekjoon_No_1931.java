package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// Meeting Class 선언 
class Meeting implements Comparable<Meeting>{
	int start;
	int end;
	
	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	// Sort에 사용할 기준 설정 
	@Override
	public int compareTo(Meeting m) {
		// 종료시간이 같다면 시작시간이 빠른 순서대로 정렬 
		if(this.end == m.end) {
			return this.start - m.start;
		}
		// 종료시간을 기준으로 정렬 
		return this.end - m.end;
	}
}

public class Baekjoon_No_1931 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Meeting> list = new ArrayList<>();
		int result = 0;
		int end_time = 0; 
		
		StringTokenizer st;
		
		for(int i=0;i<N;i++) {
			String temp = br.readLine();
			st = new StringTokenizer(temp);
			
			Meeting m = new Meeting( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) );
			list.add(m);
		}
		
		// 종료시간이 빠른 순서대로 정렬 
		Collections.sort(list);
		
		
		for(int i=0;i<list.size();i++ ) {
			if(end_time <= list.get(i).start ) {
				result++;
				end_time = list.get(i).end; 
			}
		}
		
		System.out.println(result);

	}

}
