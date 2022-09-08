package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1991
public class Baekjoon_No_1991 {

	public static int N;		// 노드의 갯수
	public static StringBuilder sb = new StringBuilder();	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Node root = new Node('A', null, null);		// A는 항상 root Node
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char main = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			insertNode(root, main, left, right);
		}
		
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);
		
		System.out.println(sb.toString());
	}

	// 노드에 넣기
	public static void insertNode(Node node, char main, char left, char right) {
		
		// 현재 노드에 해당되는 경우
		if(node.value == main) {
			if(left != '.') {
				node.left = new Node(left, null, null);
			}
			if(right != '.') {
				node.right = new Node(right, null, null);
			}
		}
		
		// 아닌 경우 왼쪽, 오른쪽 노드로 재귀호출
		else {
			if(node.left != null) {
				insertNode(node.left, main, left, right);
			}
			if(node.right != null) {
				insertNode(node.right, main, left, right);
			}
		}
	}
	
	// 전위탐색(루트>왼쪽>오른쪽)
	public static void preOrder(Node node) {
		sb.append(node.value);
		if(node.left != null) {
			preOrder(node.left);
		}
		if(node.right != null) {
			preOrder(node.right);
		}
	}
	
	// 중위탐색(왼쪽>루트>오른쪽)
	public static void inOrder(Node node) {
		if(node.left != null) {
			inOrder(node.left);
		}
		sb.append(node.value);
		if(node.right != null) {
			inOrder(node.right);
		}
	}
	
	// 후위탐색(왼쪽>오른쪽>루트)
	public static void postOrder(Node node) {
		if(node.left != null) {
			postOrder(node.left);
		}
		if(node.right != null) {
			postOrder(node.right);
		}
		sb.append(node.value);
	}

	public static class Node{
		char value;
		Node left;
		Node right;
		
		public Node(char value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}