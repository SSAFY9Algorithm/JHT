package study.February.day0224;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**미안하다 설명 못 하겠다, 11try인가? 주석도 에라이*/
public class Main_백준_11657_타임머신_408ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node();
		}
		int a,b,c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1; // 출발
			b = Integer.parseInt(st.nextToken()) - 1; // 도착
			c = Integer.parseInt(st.nextToken()); // 가중치
			nodes[a].li.add(new int[] {b, c}); 
		}
		nodes[0].weight = 0;
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = N;
		boolean []visited = new boolean[N];
		while (cnt--> 0) {
			visited = new boolean[N];
			q.offer(0);
			visited[0] = true;
			while(!q.isEmpty()) {
				Node now = nodes[q.poll()];
				for (int i = 0; i < now.li.size(); i++) {
					nodes[now.li.get(i)[0]].weight = Math.min(nodes[now.li.get(i)[0]].weight, now.weight + now.li.get(i)[1]);
					if (!visited[now.li.get(i)[0]]) {
						q.offer(now.li.get(i)[0]);
						visited[now.li.get(i)[0]] = true;
					}
				}
			}
		}
		q.offer(0);
		visited[0] = false;
		while(!q.isEmpty()) {
			Node now = nodes[q.poll()];
			for (int i = 0; i < now.li.size(); i++) {
				long temp = Math.min(nodes[now.li.get(i)[0]].weight, now.weight + now.li.get(i)[1]);
				if (nodes[now.li.get(i)[0]].weight != temp) {
					System.out.println(-1);
					return;
				}
				if (visited[now.li.get(i)[0]]) {
					q.offer(now.li.get(i)[0]);
					visited[now.li.get(i)[0]] = false;
				}
			}
		}
		for (int i = 1; i < N; i++) {
			if (nodes[i].weight == Long.MAX_VALUE)
				sb.append("-1\n");
			else
				sb.append(nodes[i].weight).append("\n");
		}
		System.out.println(sb.toString());
	}
	static class Node{
		long weight = Long.MAX_VALUE;
		List<int[]> li = new ArrayList<int[]>();
	}
}
