package baekjoon.level.level_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_2869 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// 시간 제한으로 인해 시간이 상당히 촉박하니 Scanner가 아닌 BufferedReader 및 StringTokenize 이
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 달팽이가 낮에 올라가는 거리
		int A = Integer.parseInt(st.nextToken());
		
		// 달팽이가 밤에 미끄러지는 거리
		int B = Integer.parseInt(st.nextToken());
		
		// 나무막대 길이
		int V = Integer.parseInt(st.nextToken());
		
		// ★ 달팽이가 정상에 오르면 미끄러지지 않는다.
		// V <= Ax + B(x-1)
		// 도출 (V-B) / (A-B) <= x
		int days = ((V-B) / (A-B));
		double check = 0.0;
		
		// (V-B) 에서 (A-B) 나눴을 때, 나머지가 있을 경우 x에 +1을 해준다.  
		check = ((V-B) % (A-B));
		if(check != 0.0) {
			days ++;
		}
		
		System.out.println(days);
	}

}
