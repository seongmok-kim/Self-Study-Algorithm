package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_2251 {
    static class History{
        int A;
        int B;
        int C;

        public History(int A, int B, int C){
            this.A=A;
            this.B=B;
            this.C=C;
        }
    }

    static ArrayList<History> historyList = new ArrayList<>();
    static ArrayList<Integer> resultList = new ArrayList<>();

    static int A_SIZE;
    static int B_SIZE;
    static int C_SIZE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A_SIZE = Integer.parseInt(st.nextToken());
        B_SIZE = Integer.parseInt(st.nextToken());
        C_SIZE = Integer.parseInt(st.nextToken());

        resultList.add(C_SIZE);

        DFS(0, 0, C_SIZE);

        int[] result = new int[resultList.size()];
        for(int i=0; i<resultList.size(); i++){
            result[i] = resultList.get(i);
        }
        Arrays.sort(result);
        for(int num : result){
            System.out.print(num + " ");
        }
    }

    static void DFS(int A, int B, int C) {
        boolean flag = false;
        for(History history : historyList){
            if(history.A == A && history.B == B){
                flag = true;
            }
        }
        historyList.add(new History(A, B, C));

        //다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
        if(!flag){
            if(A == 0){
                if(!resultList.contains(C)) resultList.add(C);
                historyList.add(new History(A,B,C));
            }

            // 1. A물통의 물을 옮김
            if(A!=0){
                // 1.1 A물통을 B물통으로
                if(B != B_SIZE){
                    int tempB = A+B;
                    int tempA = 0;
                    if(tempB > B_SIZE){
                        tempA = tempB - B_SIZE;
                        tempB = B_SIZE;
                    }

                    DFS(tempA, tempB, C);
                }

                // 1.2 A물통을 C물통으로
                if(C != C_SIZE){
                    int tempC = A+C;
                    int tempA = 0;
                    if(tempC > C_SIZE){
                        tempA = tempC - C_SIZE;
                        tempC = C_SIZE;
                    }

                    DFS(tempA, B, tempC);
                }
            }

            // 2. B물통의 물을 옮김
            if(B!=0){
                // 2.1 B물통을 A물통으로
                if(A != A_SIZE){
                    int tempA = A+B;
                    int tempB = 0;
                    if(tempA > A_SIZE){
                        tempB = tempA - A_SIZE;
                        tempA = A_SIZE;
                    }

                    DFS(tempA, tempB, C);
                }

                // 2.2 B물통을 C물통으로
                if(C != C_SIZE){
                    int tempC = B+C;
                    int tempB = 0;
                    if(tempC > C_SIZE){
                        tempB = tempC - C_SIZE;
                        tempC = C_SIZE;
                    }

                    DFS(A, tempB, tempC);
                }
            }

            // 3. C물통의 물을 옮김
            if(C!=0){
                // 3.1 C물통을 A물통으로
                if(A != A_SIZE){
                    int tempA = A+C;
                    int tempC = 0;
                    if(tempA > A_SIZE){
                        tempC = tempA - A_SIZE;
                        tempA = A_SIZE;
                    }

                    DFS(tempA, B, tempC);
                }

                // 3.2 C물통을 B물통으로
                if(B != B_SIZE){
                    int tempB = B+C;
                    int tempC = 0;
                    if(tempB > B_SIZE){
                        tempC = tempB - B_SIZE;
                        tempB = B_SIZE;
                    }

                    DFS(A, tempB, tempC);
                }
            }
        }
    }
}
