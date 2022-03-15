package etc;

public class problem_1 {

	public static int width, height;
	
	public static int[][] diagList;
	public static int[][] diag;
	public static boolean[][] visited;
	
	public static int min = (int)1e9;
	
	public static long result = 0;
	
	public static int[] amove = {-1,0};
	public static int[] bmove = {0,1};
	
	public static int diagNum = 0;
	
	
	// a : 세로
	// b : 가로
	// count : 지나친 선분
	// passed
		// -1 : 대각선 탔음
		// 0 : 대각선을 지나치지 않음
		// 1 : 첫 번째 대각선 지나침
		// 2 : 두 번째 대각선 지나침
		// ...
	public static void DFS(int a, int b, int count, int passed) {
		// 맨 오른쪽 위면
		if(a==0 && b == width) {
			// 최단 경로보다 작을 경우
			if(min > count & passed == -1) {
				min = count;
				result = 1;
			}
			// 최단경로와 같을 경우 
			else if(min == count & passed == -1) {
				result ++ ;
			}
			return;
		}
		
		// 지금 좌표에서 대각선으로 통하는 길이 있는 지 여부
		boolean isDiag = false;
		
		// 대각선을 탄 적이 없는 경우에만 대각선을 탈 수 있음
		if(passed != -1) {
			for(int j=0; j<diagNum; j++) {
				// 대각선 좌표일 경우 (사각형 기준 왼쪽위)
				if(a == diag[j][0] && b == diag[j][1]) {
					visited[a+1][b+1] = true;
					DFS(a+1, b+1, count+1, -1);
					visited[a+1][b+1] = false;
					isDiag = true;
				}
				// 대각선 좌표일 경우 (사각형 기준 오른쪽아래)
				else if(a == diag[j][0]+1 && b == diag[j][1]+1) {
					visited[a-1][b-1] = true;
					DFS(a-1, b-1, count+1, -1);
					visited[a-1][b-1] = false;
					isDiag = true;
				}
				
			}
		}
		
		// 맨왼쪽아래에서 맨오른쪽 위로 올라가는 것이므로
		// 위로 올라가거나 오른쪽으로 방향 설정
		for(int i=0; i<2; i++) {
			int tempa = a + amove[i];
			int tempb = b + bmove[i];
			
			if(tempa < 0 || tempa > height || tempb < 0 || tempb > width) {
				continue;
			}
			
			if(visited[tempa][tempb] == false) {
				if(isDiag == true) {
					visited[tempa][tempb] = true;
					DFS(tempa,tempb,count+1,passed+1);
					visited[tempa][tempb] = false;
				}
				else {
					visited[tempa][tempb] = true;
					DFS(tempa,tempb,count+1,passed);
					visited[tempa][tempb] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TESTCASE 1
		/*
		width = 2;
		height = 2;
		diagList = new int[][] {{1,1}, {2,2}};
		*/
		
		// TESTCASE 2
		width = 51;
		height = 37;
		diagList = new int[][] {{17,19}};

		// 대각선의 수
		diagNum = diagList.length;
		
		// 방문 체크 변수 크기 정의
		visited = new boolean[height+1][width+1];
		
		// 대각선이 그려지는 사각형의 왼쪽위 꼭짓점 저장
		diag = new int[diagNum][2];
		
		// 예시에서는 x,y지만 헷갈리지 않게 하기위해 순서 변경 및 좌표 설정
		// diag[i][0] : 세로
		// diag[i][0] : 가로
		for(int i=0; i<diagNum; i++) {
			diag[i][0] = height - diagList[i][1];
			diag[i][1] = diagList[i][0] - 1;
		}
		
		DFS(height, 0, 0, 0);
		
		System.out.println(result % 10000019);
	}

}