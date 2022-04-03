package programmers;

import java.util.ArrayList;

public class undestroyed_structure {
	public int solution(int[][] board, int[][] skill) {
		int destoryedCount = 0;
		
		// +면 안봐도되고
		// -면 기존 건물 내구도랑 비교해서 파괴건물 카운트
		
		for(int i=0; i<skill.length; i++) {
			int type = skill[i][0];
			int r1 = skill[i][1];
			int c1 = skill[i][2];
			int r2 = skill[i][3];
			int c2 = skill[i][4];
			int degree = skill[i][5];
			
			// 공격
			if(type == 1) {
				for(int a=r1; a<=r2; a++) {
					for(int b=c1; b<=c2; b++) {
						board[a][b] -= degree;
					}
				}
			}
			// 치료
			else if(type==2) {
				for(int a=r1; a<=r2; a++) {
					for(int b=c1; b<=c2; b++) {
						board[a][b] += degree;
					}
				}
			}
		}
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j] <= 0) {
					destoryedCount++;
				}
			}
		}
        int answer = 0;
        return destoryedCount;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
