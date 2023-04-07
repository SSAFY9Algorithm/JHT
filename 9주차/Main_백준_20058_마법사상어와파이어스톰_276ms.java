package study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_20058_마법사상어와파이어스톰_276ms {
	static int N, Q, size, ans1 = 0, ans2 = 0, maxsize = 0;;
	static int[][] map;
	static int[] Ls;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		size = 1 << N;
		map = new int[size][size];
		Ls = new int[Q];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			Ls[i] = Integer.parseInt(st.nextToken());
		}
		doMagic();
		System.out.println(ans1);
		System.out.println(ans2);
	}

	private static void doMagic() {
		int cnt = 0;
		while (cnt != Q) {
			spin(Ls[cnt]);
			melt();
			cnt++;
		}
		countAns();
	}
	private static void countAns() {
		visited  = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] != 0 && !visited[i][j])
					BFS(i, j);
			}
		}
	}

	private static void BFS(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int [] {r , c});
		visited[r][c] = true;
		ans1 += map[r][c];
		int lands = 1;
		while (!q.isEmpty()) {
			int [] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int cr = now[0] + dr[i];
				int cc = now[1] + dc[i];
				if (0 <= cr && cr < size && 0 <= cc && cc < size && map[cr][cc] > 0 && !visited[cr][cc]) {
					visited[cr][cc] = true;
					ans1 += map[cr][cc];
					lands++;
					q.offer(new int [] {cr , cc});
				}
			}
		}
		if (ans2 < lands && 1 < lands) {
			ans2 = lands;
		}
	}

	private static void melt() {
		boolean check[][] = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (checkMelt(i, j))
					check[i][j] = true;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (check[i][j] && map[i][j] > 0)
					map[i][j]--;
			}
		}
	}

	private static boolean checkMelt(int r, int c) { // 녹여야하면 true
		int temp = 0;
		for (int i = 0; i < 4; i++) {
			int cr = r + dr[i];
			int cc = c + dc[i];
			if (0 <= cr && cr < size && 0 <= cc && cc < size && map[cr][cc] > 0)
				temp++;
		}
		if (temp > 2)
			return false;
		return true;
	}

	private static void spin(int L) {
		int[][] temp = new int[size][size];
		for (int I = 0; I < size / (1 << L); I++) {
			for (int J = 0; J < size / (1 << L); J++) {
				for (int i = 0; i < (1 << L); i++) {
					for (int j = 0; j < (1 << L); j++) {
						temp[I * (1 << L) + j][J * (1 << L) + (1 << L) - 1 - i] = map[I * (1 << L) + i][J * (1 << L) + j];
					}
				}
			}
		}
		map = temp;
	}
}
