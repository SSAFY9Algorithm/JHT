package study.FiveWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1738_골목길_332ms {
	static int N;
	static int [] moneys; // 각 위치에서의 최대 금품양
	static List<int[]>[]cross; // 각위치에서 갈 수 있는 고목길들 (0 : 다음 위치, 1: 가중치)
	static boolean[] checker, notVisited; // checker : 정답 찾을 때 방문 체크, notVisited : 무한으로 증가하는 위치들(방문 금지)
	static boolean find = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		cross = new ArrayList[N];
		moneys = new int [N];
		for (int i = 0; i < N; i++) {
			moneys[i] = -2147483648;
		}
		for (int i = 0; i < cross.length; i++) {
			cross[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1; // 출발 교차점
			int v = Integer.parseInt(st.nextToken()) - 1; // 도착 교차점
			int w = Integer.parseInt(st.nextToken()); // 가중치
			cross[u].add(new int[] {v,w});
		}
		int cnt = N;
		Queue<Integer> q = new ArrayDeque<Integer>();
		moneys[0] = 0;
		boolean[] visited;
		while (cnt-- > 0) {
			q.offer(0);
			visited = new boolean[N];
			visited[0] = true;
			while(!q.isEmpty()) {
				int now = q.poll();
				for (int i = 0; i < cross[now].size(); i++) {
					int next = cross[now].get(i)[0];
					int nextWeight = cross[now].get(i)[1];
					moneys[next] = Math.max(moneys[next], moneys[now] + nextWeight);
					if (visited[next])continue;
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		visited = new boolean[N];
		q.offer(0);
		notVisited = new boolean[N];
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < cross[now].size(); i++) {
				int next = cross[now].get(i)[0];
				int nextWeight = cross[now].get(i)[1];
				int temp = Math.max(moneys[next], moneys[now] + nextWeight);
				if (temp != moneys[next]) {// 방문하면 안되는 노드들
					notVisited[next] = true;
				}
				if (visited[next])continue;
				visited[next] = true;
				q.offer(next);
			}
		}
		// 무한으로 증가후 목적지 갈 수 있것 확인 (안하면 90% 에서 틀렸다고 나옴)
		for (int i = 0; i < notVisited.length; i++) { 
			if (!notVisited[i])continue;
			for (int j = 0; j < cross[i].size(); j++) {
				if (cross[i].get(j)[0] == N-1) {
					System.out.println(-1);
					return;
				}
			}
		}
		checker = new boolean[N];
		check(0,0,new ArrayList<Integer>());
		if (!find)
			System.out.println(-1);
	}
	//경로찾기 idx :현재 교차점, money : 현재까지 금품양, ans : 현재까지 경로
	private static void check(int idx, int money, ArrayList<Integer> ans) {
		if (find)return; // 정답 찾았으면 return
		if (moneys[idx] != money)return; // 해당위치의 최대값 경로 아닐경우 return(백트래킹)
		if (idx == N - 1) {
			if (money == moneys[N-1]) {
				for (Integer in : ans) {
					System.out.print((in + 1) + " ");
				}
				System.out.println(N);
				find = true;
			}
			return;
		}
		ans.add(idx);
		for (int i = 0; i < cross[idx].size(); i++) {
			if (checker[cross[idx].get(i)[0]] || notVisited[cross[idx].get(i)[0]])continue;
			checker[cross[idx].get(i)[0]] = true;
			check(cross[idx].get(i)[0],money + cross[idx].get(i)[1],ans);
			checker[cross[idx].get(i)[0]] = false;
		}
		ans.remove(ans.size()-1);
	}
}
