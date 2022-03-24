package programmers;

public class archery_competition {

	public static int[] apeach;
	
	// 쏠 수 있는 화살의 수
	public static int N;
	
	// 가장 큰 점수 차
	public static int maxScore = 0;
	
	// 라이언이 쏜 화살의 경우를 모아두는 배열
	public static int[] result = new int[11];
	
	// 화살쏘기
	// score : 쏴야할 영역의 점수
	// count : 현재 남은 화살의 수
	// arr : 라이언이 쏜 화살의 경우의 수를 기록해둔 배열
	public static void findCase(int score, int count, int[] arr) {
		
		// 점수가 0점 밑으로 떨어지면 모두 순회한 것이므로 결과 확인
		if(score<0) {
			// 화살을 다 썻으면 계산 시작
			if(count <= 0) {
				
				// 어피치 점수 합
				int apeachScore = 0;
				
				// 라이언 점수 합
				int lyanScore = 0;

				// 각 점수 구하기
				
				for(int i=0; i<11; i++) {
					// 둘 다 한 발도 못 맞췄으면 점수 아무도 안 줌
					if(arr[i] == 0 && apeach[i] == 0) {
						continue;
					}
					// 같은 점수의 영역에 라이언이 더 많이 맞춘 경우 라이언한테 해당 점수를 줌
					else if(arr[i] > apeach[i]) {
						lyanScore += (10-i);
					}
					// 같은 점수의 영역에 동일한 화살을 맞춘 경우 어피치한테 해당 점수를 줌
					else {
						apeachScore += (10-i);
					}
				}
				
				// 점수 차 구하기
				int scoreGap = lyanScore - apeachScore;

				// 기존에 등록됐던 최대 점수차와 같다면
				if(scoreGap == maxScore) {
					// 낮은 점수를 많이 맞힌 경우의 수를 정답으로 배출해야함.
					
					// 낮은 점수부터 확인
					for(int i=10; i>=0; i--) {
						// 낮은 점수 맞힌 숫자가 같을 경우 그 다음 높은 수 확인
						if(arr[i] == result[i]) {
							continue;
						}
						// 낮은 점수를 맞춘 수가 arr이 더 많으면 result 갱신
						else if(arr[i] > result[i]) {
							for(int j=0; j<arr.length; j++) {
								result[j] = arr[j];
							}
							break;
						}
						// 아니라면 탈출 
						else {
							break;
						}
					}
				}
				// 이번 경우의 수에서 구한 점수차가 최대점수차보다 크면 result 갱신
				else if(scoreGap > maxScore) {
					for(int i=0; i<arr.length; i++) {
						result[i] = arr[i];
					}
					maxScore = scoreGap;
				}
				
				return;
			}
			
			// 화살을 다 안썼으면 무시
			return;
		}
		
		// 경우는 현재 영역를 이기거나 지거나 둘 중 하나.
		
		// 1. 현재 영역을 이긴다
		int apeachNum = apeach[Math.abs(score-10)];
		if(count-(apeachNum+1) >= 0) {
			arr[Math.abs(score-10)] = (apeachNum+1);
       			findCase(score-1, count-(apeachNum+1), arr);
		}
		
		// 2. 현재 영역을 진다.
		// 0점일때는 다 쏴야만함..
		if(score == 0) {
			arr[10] = count;
			findCase(score-1, 0, arr);
		}
		// 0점이 아닐 때는 그냥 넘어가기
		else {
			arr[Math.abs(score-10)] = 0;
			findCase(score-1, count, arr);
		}
	}
	
	public static int[] solution(int n, int[] info) {
		// n : 양궁 쏠 수 있는 기회
		apeach = new int[info.length];
		for(int i=0; i<info.length; i++) {
			apeach[i] = info[i];
		}
		
		N = n;
		
		int[] ryan = new int[11];
		
		findCase(10, n, ryan);
		
		if(maxScore == 0) {
			int[] lose = {-1};
			return lose;
		}
		else {
			return result;
		}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		int[] info = {0,0,0,0,0,0,0,0,3,4,3};
		
		int[] test = solution(n,info);
		
		for(int i=0; i<test.length; i++) {
			System.out.print(test[i] + " ");
		}
	}

}
