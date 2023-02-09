package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Baekjoon_No_1339 {
    public static class CustomChar implements Comparable<CustomChar>{
        char c;
        int length;

        public CustomChar(char c, int l) {
            this.c = c;
            this.length = l;
        }

        @Override
        public int compareTo(CustomChar o) {
            return o.length - this.length;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            strArr[i] = br.readLine();
            char[] charArr =strArr[i].toCharArray();

            // 어느 알파벳이 가장 큰 영향을 가지는 지 확인
            // 자릿수에 10을 거듭제곱하여 확인. Math.pow()
            for(int j=0; j<charArr.length; j++) {
                if(map.get(charArr[j]) == null) {
                    map.put(charArr[j], (int) Math.pow(10, charArr.length - j));
                }else {
                    map.put(charArr[j], (int) (map.get(charArr[j]) + Math.pow(10, charArr.length - j)));
                }
            }
        }

        PriorityQueue<CustomChar> pq = new PriorityQueue<>();
        Iterator<Character> keyIter = map.keySet().iterator();

        while(keyIter.hasNext()) {
            char c = keyIter.next();
            pq.add(new CustomChar(c, map.get(c)));		// 우선순위 큐에 넣어서 가장 영향도가 높은 알파벳부터 나오도록 설정
        }

        int num = 9;			// 가장 영향도가 높은 알파벳에게 가장 높은 숫자인 9를 주고, 다음 알파벳은 8 이런식으로..
        while(!pq.isEmpty()) {
            CustomChar customChar = pq.poll();
            char c = customChar.c;
            for(int i=0; i<N; i++) {
                strArr[i] = strArr[i].replaceAll(c+"", num+"");		// 알파벳을 해당 숫자로 치환
            }
            num--;
        }

        int sum = 0;
        for(int i=0; i<N; i++) {
            int strNum = Integer.parseInt(strArr[i]);		// 숫자로 된 문자열을 int형으로 변환한 다음 덧셈 진행
            sum += strNum;
        }

        System.out.println(sum);		// 결과 출력
    }
}