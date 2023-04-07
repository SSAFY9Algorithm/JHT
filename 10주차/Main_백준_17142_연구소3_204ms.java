package study.day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17142_연구소3_204ms {
	static ArrayList<int[]> virus;
	static int N, M, blankCnt = 0, ans = 2147483647;
	static int [][] map;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new  int [N][N];
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new int [] {i, j});
				if (map[i][j] == 0)
					blankCnt ++;
			}
		}
		if (blankCnt == 0) {
			System.out.println(0);
			return;
		}
		comb(0, 0, 0);
		System.out.println(ans == 2147483647 ? -1 :ans);
	}

	private static void comb(int cnt, int start, int selected) {
		if (cnt == M) {
			BFS(selected);
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			comb(cnt+1, i + 1, selected | (1 << i));
		}
	}
	private static void BFS(int selected) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean [][]visited = new boolean[N][N];
		for (int i = 0; i < virus.size(); i++) {
			if ((selected & (1<<i)) != 0) {
				int [] a = virus.get(i);
				visited[a[0]][a[1]] = true;
				q.offer(a);
			}
		}
		int time = 0;
		int cnt = blankCnt;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int len = 0; len < size; len++) {
				int [] now = q.poll();
				for (int i = 0; i < 4; i++) {
					int cr = now[0] + dr[i];
					int cc = now[1] + dc[i];
					if (0 <= cr && cr < N && 0 <= cc && cc < N && !visited[cr][cc] && map[cr][cc] != 1) {
						visited[cr][cc] = true;
						q.offer(new int [] {cr, cc});
						if (map[cr][cc] == 0)
							cnt--;
					}
				}
			}
			time++;
			if (cnt == 0)break;
		}
		if (cnt == 0 && time < ans)
			ans = time;
	}
}
