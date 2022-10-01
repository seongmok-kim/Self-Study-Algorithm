package programmers;

import java.util.HashMap;

// Link : https://school.programmers.co.kr/learn/courses/30/lessons/118666?language=java
public class test_character {
    public String solution(String[] survey, int[] choices) {

        /*
        1번 지표	라이언형(R), 튜브형(T)
        2번 지표	콘형(C), 프로도형(F)
        3번 지표	제이지형(J), 무지형(M)
        4번 지표	어피치형(A), 네오형(N)
        */
        HashMap<String, Integer> data = new HashMap<>();
        data.put("R", 0);
        data.put("T", 0);
        data.put("C", 0);
        data.put("F", 0);
        data.put("J", 0);
        data.put("M", 0);
        data.put("A", 0);
        data.put("N", 0);

        int length = survey.length;
        for(int i=0; i<length; i++) {
            String[] type = survey[i].split("");
            int choice = choices[i];
            int score = 0;

            if(choice >= 1 && choice < 4) {
                if(choice == 1)
                    score = 3;
                else if(choice == 2)
                    score = 2;
                else if(choice == 3)
                    score = 1;

                data.put(type[0], data.get(type[0])+score);
            } else if( choice >= 5 && choice < 8) {
                if(choice == 7)
                    score = 3;
                else if(choice == 6)
                    score = 2;
                else if(choice == 5)
                    score = 1;

                data.put(type[1], data.get(type[1])+score);
            } else if( choice == 4) {
                continue;
            }
        }

        StringBuilder answer = new StringBuilder();

        // 1번지표 확인
        if(data.get("R") < data.get("T")) {
            answer.append("T");
        }
        else {
            answer.append("R");
        }

        // 2번지표 확인
        if(data.get("C") < data.get("F")) {
            answer.append("F");
        }
        else {
            answer.append("C");
        }

        // 3번지표 확인
        if(data.get("J") < data.get("M")) {
            answer.append("M");
        }
        else {
            answer.append("J");
        }

        // 4번지표 확인
        if(data.get("A") < data.get("N")) {
            answer.append("N");
        }
        else {
            answer.append("A");
        }

        return answer.toString();
    }
}
