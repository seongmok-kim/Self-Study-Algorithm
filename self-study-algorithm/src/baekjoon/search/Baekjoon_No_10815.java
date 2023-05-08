package baekjoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_10815 {
    static int[] myCard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        myCard = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)  myCard[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(myCard);        // 오름차순 정렬

        int M = Integer.parseInt(br.readLine());
        int[] targetCard = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++)  targetCard[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int target : targetCard) {
            boolean result = BinarySearch(0, myCard.length-1, target);
            if(result)  sb.append("1 ");
            else        sb.append("0 ");
        }

        System.out.println(sb);
    }

    // 이진 탐색(이분 탐색)
    static boolean BinarySearch(int start, int end, int target) {
        if(start > end)     return false;
        int mid = (start+end) / 2;

        if(myCard[mid] == target)   return true;
        else if(myCard[mid] > target)   return BinarySearch(start, mid-1, target);
        else    return BinarySearch(mid+1, end, target);

    }

}
