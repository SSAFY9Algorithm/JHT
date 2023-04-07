package study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_20056_마법사상어와파이어볼_440ms {
	static int N, M, K;
	static square[][] map;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new square[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBall fb = new fireBall(m, s, d);
			if (map[r][c] == null)
				map[r][c] = new square();
			map[r][c].push(fb);
		}
		doMagic();
		System.out.println(getAns());
	}

	private static int getAns() {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					for (int idx = 0; idx < map[i][j].idx; idx++) {
						ans += map[i][j].arr[idx].weight;
					}
				}
			}
		}
		return ans;
	}

	private static void doMagic() {
		while (K-- > 0) {
			moveFireBalls();
			checkMap();
		}
	}

	private static void checkMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].idx > 1) {
					int weights = 0;
					int speeds = 0;
					int dirChecker = 0;
					int cnt = 0;
					for (int idx = 0; idx < map[i][j].idx; idx++) {
						fireBall fb = map[i][j].arr[idx];
						cnt++;
						weights += fb.weight;
						speeds += fb.speed;
						if (dirChecker == 0) {
							dirChecker = (fb.dir % 2 == 1) ? 1 : 2;
						} else if (dirChecker == 3) {
							continue;
						} else {
							dirChecker = (fb.dir % 2 == dirChecker % 2) ? dirChecker : 3;
						}
					}
					map[i][j].clear();
					weights = weights / 5;
					if (weights != 0) {
						for (int k = 0; k < 4; k++) {
							fireBall fb = new fireBall(weights, speeds / cnt, (dirChecker == 3) ? 2 * k + 1 : 2 * k);
							map[i][j].push(fb);
						}
					}
				}
			}
		}
	}

	private static void moveFireBalls() {
		square[][] temp = new square[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].idx != 0) {
					for (int idx = 0; idx < map[i][j].idx; idx++) {
						fireBall fb = map[i][j].arr[idx];
						int nextR = i + (dr[fb.dir] * fb.speed);
						int nextC = j + (dc[fb.dir] * fb.speed);
						while (nextR < 0)
							nextR += N;
						while (nextC < 0)
							nextC += N;
						while (N <= nextR)
							nextR -= N;
						while (N <= nextC)
							nextC -= N;
						if (temp[nextR][nextC] == null)
							temp[nextR][nextC] = new square();
						temp[nextR][nextC].push(fb);
					}
				}
			}
		}
		map = temp;
	}

	static class square {
		int idx = 0;
		fireBall[] arr;

		public void push(fireBall ball) {
			if (arr == null || arr.length - 1 < idx) {
				fireBall[] temp = new fireBall[idx + 1];
				for (int i = 0; i < idx; i++) {
					temp[i] = arr[i];
				}
				arr = temp;
			}
			arr[idx++] = ball;
		}

		public void clear() {
			idx = 0;
		}
	}

	static class fireBall {
		int weight;
		int speed;
		int dir;

		public fireBall() {
		}

		public fireBall(int weight, int speed, int dir) {
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
		}

	}
}
