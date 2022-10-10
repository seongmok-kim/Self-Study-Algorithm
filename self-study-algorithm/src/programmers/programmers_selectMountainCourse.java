package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// Link : https://school.programmers.co.kr/learn/courses/30/lessons/118669
public class programmers_selectMountainCourse {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 7;
        int[][] paths = { { 1, 2, 5 }, { 1, 4, 1 }, { 2, 3, 1 }, { 2, 6, 7 }, { 4, 5, 1 }, { 5, 6, 1 }, { 6, 7, 1 } };
        int[] gates = { 3, 7 };
        int[] summits = { 1, 5 };

        int[] result = solution(n, paths, gates, summits);
        System.out.println(result[0] + " " + result[1]);
    }

    // Node 클래스 정의
    static class Node {
        int pos;
        int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }
    }

    static ArrayList<ArrayList<Node>> map = new ArrayList<>();			// Map 정의
    public static HashMap<Integer, Boolean> summitMap = new HashMap<Integer, Boolean>();		// 산봉우리 HashMap
    public static HashMap<Integer, Boolean> gateMap = new HashMap<Integer, Boolean>();			// 출입문	HashMap
    public final static int INF = Integer.MAX_VALUE;	// 무한의 수
    public static int N;		// n(지점 갯수)
    public static int[] d;		// 출입구에서의 각 지점까지의 최소 거리

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;

        for(int i=0; i<n+1; i++){
            map.add(new ArrayList<Node>());
            gateMap.put(i, false);
            summitMap.put(i, false);
        }

        // 출입문인건 true로 변경
        for(int num : gates){
            gateMap.put(num, true);
        }

        // 산봉우리인건 true로 변경
        for(int num : summits){
            summitMap.put(num, true);
        }

        // 중요) 출입구에서 산봉우리로 가는 루트만 알면 됨.
        // Why? 그 루트 그대로 돌아오면 되기 때문.
        for(int i=0; i< paths.length; i++){
            int posA = paths[i][0];
            int posB = paths[i][1];
            int length = paths[i][2];

            // 등산로, 산봉우리의 경우 단방향으로만.
            if(gateMap.get(posA) || summitMap.get(posB)) {
                map.get(posA).add(new Node(posB, length));
            }
            else if(gateMap.get(posB) || summitMap.get(posA)) {
                map.get(posB).add(new Node(posA, length));
            }
            // 일반 등산로의 경우 양방향
            else {
                map.get(posA).add(new Node(posB, length));
                map.get(posB).add(new Node(posA, length));
            }
        }

        dijkstra(gates);

        int finalDis = INF;
        int finalPos = 0;

        for(int summit : summits) {
            if(finalDis > d[summit]) {
                finalDis = d[summit];
                finalPos = summit;
            }
            // 같은 거리의 산봉우리가 있을 때, 번호가 작은 산봉우리가 나오도록 문제에서 요청함
            else if(finalDis == d[summit] && finalPos > summit) {
                finalPos = summit;
            }
        }
        int[] answer = {finalPos, finalDis};

        return answer;
    }


    public static void dijkstra(int[] gates) {
        Queue<Node> q = new LinkedList<>();
        d = new int[N+1];
        Arrays.fill(d, INF);

        // 모든 출입구를 큐에 넣음
        for(int i=0; i<gates.length; i++){
            int gate = gates[i];

            q.offer(new Node(gate, 0));
            d[gate] = 0;
        }

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(d[node.pos] < node.dis) {
                continue;
            }

            for(int i=0; i<map.get(node.pos).size(); i++) {
                Node next = map.get(node.pos).get(i);

                // 여기가 일반 다익스트라 알고리즘과는 다름.
                // Math.max를 이용해 가장 긴 길이의 등산로를 확인함.
                int cost = Math.max(d[node.pos],next.dis);

                if(cost < d[next.pos]) {
                    d[next.pos] = cost;
                    q.offer(new Node(next.pos, cost));
                }
            }
        }
    }
}