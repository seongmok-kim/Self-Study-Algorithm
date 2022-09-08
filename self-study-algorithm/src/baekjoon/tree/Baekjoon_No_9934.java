package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// Link : https://www.acmicpc.net/problem/9934
// 참고로 처음에 Node의 value 타입을 char로 줬었는데, 노드의 depth가 4 이상이 넘어가는 경우 10의 자리가 될 수 있기 때문에 char로 하면 안됨. int로 수정함 
public class Baekjoon_No_9934 {

	public static int K;
	
	public static StringBuilder sb = new StringBuilder();
	
	public static int[] intArr;
	
	public static String s;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		s = br.readLine();	// ex) 2 1 3, 1 6 4 3 5 2 7
		String[] sArr = s.split(" ");
		
		intArr = new int[sArr.length];
		for(int i=0; i<sArr.length; i++) {
			intArr[i] = Integer.parseInt(sArr[i]);
		}
		
		Node root = new Node(0, null, null, 0);
		
		InsertNode(root, 0, intArr.length-1, 0);
		
		Queue<Node> q= new LinkedList<>();
		q.offer(root);
		int nowDepth = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.value == 0) 
				continue;
			
			
			if(node.depth != nowDepth) {
				sb.append("\n");
				nowDepth = node.depth;
			}
			
			sb.append(node.value+" ");
			
			q.offer(node.left);
			q.offer(node.right);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void InsertNode(Node node, int start, int end, int depth) {
		if(start>end) {
			return;
		}
		
		int center = (start+end) / 2;
		
		int croot = intArr[center];
		node.value = croot;
		
		if(node.left == null) {
			node.left = new Node(0, null, null, depth+1);
		}
		if(node.right == null) {
			node.right = new Node(0, null, null, depth+1);
		}
		
		InsertNode(node.left, start, center-1,depth+1);
		InsertNode(node.right, center+1, end, depth+1);
	}
	
	public static class Node{
		int value;
		Node left;
		Node right;
		int depth;
		
		public Node(int value, Node left, Node right, int depth) {
			this.value = value;
			this.left = left;
			this.right = right;
			this.depth = depth;
		}
	}

}