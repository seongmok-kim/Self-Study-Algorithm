package etc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class bfs_basic {
    static boolean[] visited_node = new boolean[7];
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) {
        for (int i=0; i<7; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);

        graph.get(1).add(0);
        graph.get(1).add(5);

        graph.get(2).add(0);
        graph.get(2).add(3);

        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(3).add(6);

        graph.get(4).add(3);
        graph.get(5).add(1);
        graph.get(6).add(3);

        bfs();
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // 시작 노드: 0

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (!visited_node[idx]) {
                visited_node[idx] = true;
                List<Integer> nodeList = graph.get(idx);
                System.out.println(idx);

                for (Integer node : nodeList) {
                    queue.add(node);
                }
            }
        }
    }
}
