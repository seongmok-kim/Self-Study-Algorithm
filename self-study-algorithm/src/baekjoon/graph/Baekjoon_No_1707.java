package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_1707 {

    // 이분 그래프 : 그래프 형태의 자료구조이며, 정점을 2그룹으로 나눴는 데 같은 그룹의 정점끼리는 간선으로 연결되지 않은 형태.
    // 간선이 없고 정점만 존재할 때도 이분 그래프로 본다.
    static class Node{
        int idx;
        int groupNo;

        public Node(int pos, int groupNo) {
            this.idx = pos;
            this.groupNo = groupNo;
        }
    }
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        boolean[] resultArr = new boolean[K];

        // 테스트 케이스만큼 반복
        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());       // ~ 20,000
            int E = Integer.parseInt(st.nextToken());       // ~ 200,000

            boolean isNotBinaryGraph = false;
            int[] group = new int[V+1];
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for(int j=0; j<=V; j++){
                graph.add(new ArrayList<>());
            }

            for(int j=0; j<E; j++){
                st = new StringTokenizer(br.readLine());

                int pos1 = Integer.parseInt(st.nextToken());
                int pos2 = Integer.parseInt(st.nextToken());

                graph.get(pos1).add(pos2);
                graph.get(pos2).add(pos1);
            }

            for(int j=1; j<=V; j++){
                // 어느 노드에도 속하지 않은 노드. (탐색하지 않았던 노드)만 확인
                if(group[j] != 0)   continue;
                Queue<Node> q = new LinkedList<>();
                q.offer(new Node(j, 1));


                while(!q.isEmpty()){
                    Node now = q.poll();
                    group[now.idx] = now.groupNo;

                    // 현재 노드와 연결되는 곳은 상대 그룹 번호로 기록할 예정
                    int nextGroupNo = now.groupNo == 1 ? 2:1;

                    for(int k=0; k<graph.get(now.idx).size(); k++){
                        int next = graph.get(now.idx).get(k);

                        // 노드가 같은 그룹의 노드를 가리킨다면 이분 그래프가 아니다.
                        if(group[next] == now.groupNo){
                            isNotBinaryGraph = true;
                            break;
                        }

                        // 어느 노드에도 속하지 않은 노드. (탐색하지 않았던 노드)만 확인
                        if(group[next] == 0){
                            q.offer(new Node(next, nextGroupNo));
                        }
                    }

                    if(isNotBinaryGraph)
                        break;
                }
                if(isNotBinaryGraph)
                    break;
            }

            if(isNotBinaryGraph == true) resultArr[i] = false;
            else resultArr[i] = true;
        }

        for(int i=0; i<K; i++){
            boolean result = resultArr[i];
            if(result == true) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}