package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon_No_1461 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> bookArrUp = new ArrayList<>();
        ArrayList<Integer> bookArrDown = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(num > 0)
                bookArrUp.add(num);
            else
                bookArrDown.add(Math.abs(num));
        }

        // 내림차순 정렬
        Collections.sort(bookArrUp, Collections.reverseOrder());
        Collections.sort(bookArrDown, Collections.reverseOrder());

        // 마지막 책을 제자리에 둔 후에는 0으로 되돌아올 필요가 없으니 가장 긴 거리를 minus 변수에 기록
        int minus = 0;
        if(bookArrUp.size() == 0) {
            minus = bookArrDown.get(0);
        }
        else if(bookArrDown.size() == 0) {
            minus = bookArrUp.get(0);
        }
        else {
            if(bookArrUp.get(0) > bookArrDown.get(0)) {
                minus = bookArrUp.get(0);
            }
            else {
                minus = bookArrDown.get(0);
            }
        }

        int have = 0;		// 가지고 있는 책의 갯수
        int distance = 0;	// 움직인 거리
        int num = 0;

        while(!bookArrUp.isEmpty()) {
            num = bookArrUp.get(0);
            if(have == 0 || bookArrUp.isEmpty()) {
                distance += (num*2);
                have = 0;
            }
            bookArrUp.remove(0);
            have++;

            if(have == M) {
                have = 0;
            }
        }

        have = 0;
        while(!bookArrDown.isEmpty()) {
            num = bookArrDown.get(0);
            if(have == 0 || bookArrDown.isEmpty()) {
                distance += (num*2);
                have = 0;
            }
            bookArrDown.remove(0);
            have++;

            if(have == M) {
                have = 0;
            }
        }

        distance -= minus;
        System.out.println(distance);
    }
}