package study.sixWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17472_다리만들기2_84ms {
	static int [][] map;
	static int N, M, complexNum = 2, ans = 0; // 단지 번호 2부터 시작
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static int [] p;
	static PriorityQueue<Bridge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					drawAp(i,j);
			}
		}
		countBridge();
		p = new int [complexNum - 2];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		MST();
		System.out.println(ans);
	}
	private static void MST() {
		int cnt = complexNum -3;
		while (cnt > 0) {
			Bridge min = pq.poll();
			if (min == null)break;
			int ps = find(min.start);
			int pe = find(min.end);
			if (ps == pe)continue;
			ans += min.len;
			union(ps,pe);
			cnt--;
		}
		if (cnt != 0)
			ans = -1;
	}
	private static void union(int ps, int pe) {
		p[pe] = ps;
	}
	private static int find(int x) {
		if (p[x] != x)
			p[x] = find(p[x]);
		return p[x];
	}
	private static void countBridge() {
		for (int i = 0; i < N; i++) {
			int temp = -1;
			int tempLen = 0;
			for (int j = 0; j < M; j++) {
				if (tempLen == 0 && temp == map[i][j])continue;
				if (map[i][j] == 0) {
					tempLen++;
					continue;
				}
				if (temp != -1 && tempLen > 1) {
					pq.offer(new Bridge(temp - 2, map[i][j] - 2, tempLen));
				}
				temp = map[i][j];
				tempLen = 0;
			}
		}
		for (int j = 0; j < M; j++) {
			int temp = -1;
			int tempLen = 0;
			for (int i = 0; i < N; i++) {
				if (tempLen == 0 && temp == map[i][j])continue;
				if (map[i][j] == 0) {
					tempLen++;
					continue;
				}
				if (temp != -1 && tempLen > 1) {
					pq.offer(new Bridge(temp - 2, map[i][j] - 2, tempLen));
				}
				temp = map[i][j];
				tempLen = 0;
			}
		}
	}
	private static void drawAp(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int [] {r, c});
		map[r][c] = complexNum;
		while (!q.isEmpty()) {
			int [] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int cr = now[0] + dr[i];
				int cc = now[1] + dc[i];
				if (0 <= cr && cr < N && 0 <= cc && cc < M && map[cr][cc] == 1) {
					q.offer(new int [] {cr, cc});
					map[cr][cc] = complexNum;
				}
			}
		}
		complexNum++;
	}
	static class Bridge implements Comparable<Bridge>{
		int start;
		int end;
		int len;
		
		public Bridge(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.len - o.len;
		}
	} 
}
