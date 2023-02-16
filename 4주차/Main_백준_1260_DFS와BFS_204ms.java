package study.day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1260_DFS와BFS_204ms {
	static int N,M,V;
	static Node [] nodes;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		nodes = new Node[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (nodes[a] == null)
				nodes[a] = new Node();
			if (nodes[b] == null)
				nodes[b] = new Node();
			nodes[a].li.add(b);
			nodes[b].li.add(a);
		}
		if (nodes[V] == null) {
			System.out.println(V);
			System.out.println(V);
			return;
		}
		DFS(V);
		sb.append("\n");
		BFS(V);
		System.out.println(sb.toString());
	}
	private static void DFS(int v) {
		nodes[v].visited = 1;
		sb.append(v + " ");
		Collections.sort(nodes[v].li);
		for (int i = 0; i < nodes[v].li.size(); i++) {
			if (nodes[nodes[v].li.get(i)].visited != 1) {
				DFS(nodes[v].li.get(i));
			}
		}
	}
	private static void BFS(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(v);
		nodes[v].visited = 2;
		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + " ");
			Collections.sort(nodes[now].li);
			for (int i = 0; i < nodes[now].li.size(); i++) {
				if (nodes[nodes[now].li.get(i)].visited != 2) {
					nodes[nodes[now].li.get(i)].visited = 2;
					q.add(nodes[now].li.get(i));
				}
			}
		}
	}
}
class Node{
	int visited;
	List<Integer> li = new ArrayList<>();
}
