package study.week9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//메모리10만KB 사용함
// 시간 + 메모리 내가 제일 많이 사용한 듯? 돈이 많은가?

public class Main_백준_20057_마법사상어와토네이도_540ms {
	static int N, ans = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 }; // 왼,아,우,위
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[N / 2][N / 2] = true;
		move(N / 2, N / 2 - 1, 0);
		System.out.println(ans);
	}

	private static void move(int r, int c, int dir) {
		if (c == -1)return;
		visited[r][c] = true;
		spread(r, c, dir);
		int leftDirr = r + dr[(dir + 1) % 4];
		int leftDirc = c + dc[(dir + 1) % 4];
		if (!visited[leftDirr][leftDirc])
			move(leftDirr, leftDirc, (dir + 1) % 4);
		else
			move(r + dr[dir], c + dc[dir], dir);
	}

	private static void spread(int r, int c, int dir) {
		switch (dir) {
		case 0:
			SpreadL(r, c);
			break;
		case 1:
			SpreadD(r, c);
			break;
		case 2:
			SpreadR(r, c);
			break;
		case 3:
			SpreadU(r, c);
			break;
		}
	}

	private static void SpreadU(int r, int c) {
		int sands = map[r][c];
		map[r][c] = 0;
		int[] percent = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };
		int[] mover = { -2, -1, -1, 0, 0, 0, 0, 1, 1 };
		int[] movec = { 0, -1, 1, -1, 1, -2, 2, -1, 1 };
		int leftSands = sands;
		for (int i = 0; i < 9; i++) {
			int cr = r + mover[i];
			int cc = c + movec[i];
			int addsand = sands * percent[i] / 100;
			leftSands -= addsand;
			if (0 <= cr && cr < N && 0 <= cc && cc < N) {
				map[cr][cc] += addsand;
			}
			else
				ans += addsand;
		}
		if (r-1 >= 0)
			map[r-1][c] += leftSands;
		else
			ans += leftSands;
	}

	private static void SpreadR(int r, int c) {
		int sands = map[r][c];
		map[r][c] = 0;
		int[] percent = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };
		int[] mover = { 0, 1, -1, 1, -1, -2, 2, 1, -1 };
		int[] movec = { 2, 1, 1, 0, 0, 0, 0, -1, -1 };
		int leftSands = sands;
		for (int i = 0; i < 9; i++) {
			int cr = r + mover[i];
			int cc = c + movec[i];
			int addsand = sands * percent[i] / 100;
			leftSands -= addsand;
			if (0 <= cr && cr < N && 0 <= cc && cc < N) {
				map[cr][cc] += addsand;
			}
			else
				ans += addsand;
		}
		if (c+1 < N)
			map[r][c+1] += leftSands;
		else
			ans += leftSands;
	}

	private static void SpreadD(int r, int c) {
		int sands = map[r][c];
		map[r][c] = 0;
		int[] percent = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };
		int[] mover = { 2, 1, 1, 0, 0, 0, 0, -1, -1 };
		int[] movec = { 0, -1, 1, -1, 1, -2, 2, -1, 1 };
		int leftSands = sands;
		for (int i = 0; i < 9; i++) {
			int cr = r + mover[i];
			int cc = c + movec[i];
			int addsand = sands * percent[i] / 100;
			leftSands -= addsand;
			if (0 <= cr && cr < N && 0 <= cc && cc < N) {
				map[cr][cc] += addsand;
			}
			else
				ans += addsand;
		}
		if (r+1 < N)
			map[r+1][c] += leftSands;
		else
			ans += leftSands;
	}

	private static void SpreadL(int r, int c) {
		int sands = map[r][c];
		map[r][c] = 0;
		int[] percent = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };
		int[] mover = { 0, 1, -1, 1, -1, -2, 2, 1, -1 };
		int[] movec = { -2, -1, -1, 0, 0, 0, 0, 1, 1 };
		int leftSands = sands;
		for (int i = 0; i < 9; i++) {
			int cr = r + mover[i];
			int cc = c + movec[i];
			int addsand = sands * percent[i] / 100;
			leftSands -= addsand;
			if (0 <= cr && cr < N && 0 <= cc && cc < N) {
				map[cr][cc] += addsand;
			}
			else
				ans += addsand;
		}
		if (c-1 >= 0)
			map[r][c-1] += leftSands;
		else
			ans += leftSands;
	}
}