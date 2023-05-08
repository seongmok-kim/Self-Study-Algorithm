package baekjoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_2467 {
    static int num1 = 0, num2 = 0, figure = Integer.MAX_VALUE;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++)      arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);           // 오름차순 정렬

        BinarySearch(0, arr.length-1);

        System.out.println(num1 + " " + num2);
    }

    static void BinarySearch(int start, int end) {
        if(start >= end) return;

        int sum = arr[start] + arr[end];
        if(Math.abs(sum) < figure) {
            num1=arr[start];
            num2=arr[end];
            figure=Math.abs(sum);
            if(sum == 0) {
                return;
            }
        }

        if(sum < 0) {
            BinarySearch(start+1, end);
        }
        else {
            BinarySearch(start, end-1);
        }
    }
}
