package programmers;

import java.util.*;

public class serverAddCount {
    static int answer = 0;
    public static void main(String[] args) {
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;

        System.out.println(solution(players, m, k));
    }
    public static int solution(int[] players, int m, int k) {
        List<Integer> serverList = new ArrayList<>();

        for (int time=0; time<24; time++) {
            int humanCount = players[time];

            // 서버 증설 만료 대상 확인
            for (int j=0; j<serverList.size(); ) {
                Integer targetServer = serverList.get(j);
                if (targetServer == time) {
                    serverList.remove(targetServer);
                } else {
                    j++;
                }
            }

            // 현재 인원 확인 후 증설
            int need = (humanCount/m) - serverList.size();
            for (int j=0; j<need; j++) {
                serverList.add(time + k);
                answer++;
            }

        }

        return answer;
    }
}
