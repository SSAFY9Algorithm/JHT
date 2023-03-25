package study.week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_21609_상어중학교_112ms {
	static int N,M, totalblock = 0, ans =0;
	static int [][] map;
	static boolean [][] visited;
	static int [] dr = {1, -1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != -1)
					totalblock++;
			}
		}
		PlayGame();
		System.out.println(ans);
	}
	private static void PlayGame() {
		while (totalblock != 0) {
			visited = new boolean [N][N];
			int [] standard = FindBlock();
			if (standard[0] == -1)
				break;
			RemoveBlock(standard);
			Gravity();
			Spin();
			Gravity();
		}
	}
	private static void Spin() {
		int [][] temp = new int [N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[N - 1 - j][i] = map[i][j];
			}
		}
		map = temp;
	}
	private static void Gravity() {
		for (int j = 0; j < N; j++) {
			int floor = N-1;
			for (int i = N-1; i >= 0; i--) {
				if (map[i][j] >= 0) {
					map[floor--][j] = map[i][j];
					if (floor != i - 1)
						map[i][j] = -10;
				}
				else if (map[i][j] == -1) {
					floor = i - 1;
				}
			}
			for (int i = floor; i >= 0; i--) {
				map[floor][j] = -10;
			}
		}
	}
	private static void RemoveBlock(int[] standard) {
		int r = standard[0];
		int c = standard[1];
		int stdNum = map[r][c];
		Queue<int []> q = new ArrayDeque<int[]>();
		q.offer(new int [] {r,c});
		map[r][c] = -10;
		totalblock--;
		int temp = 1;
		while (!q.isEmpty()) {
			int [] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int cr = now[0] + dr[i];
				int cc = now[1] + dc[i];
				if (0 <= cr && cr < N && 0 <= cc && cc < N && 
						(map[cr][cc] == stdNum || map[cr][cc] == 0)) {
					q.offer(new int [] {cr,cc});
					map[cr][cc] = -10;
					temp++;
					totalblock--;
				}
			}
		}
		ans+=temp*temp;
	}
	private static int[] FindBlock() {
		int [] ans = new int [2];
		ans[0] = -1;
		int zeroCnt = 0;
		int blockCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] || map[i][j] <= 0)continue;
				int [] zCbC = BFS(i, j);
				if ((blockCnt < zCbC[1])||(blockCnt == zCbC[1] && zeroCnt < zCbC[0])) {
					ans[0] = i;
					ans[1] = j;
					zeroCnt =  zCbC[0];
					blockCnt =  zCbC[1];
				}
				else if (blockCnt == zCbC[1] && zeroCnt == zCbC[0]) {
					ans[0] = i;
					ans[1] = j;
				}
			}
		}
		if (blockCnt == 1)
			ans[0] = -1;
		return ans;
	}
	private static int[] BFS(int r, int c) {
		int [] res = new int [2];
		int standardNum = map[r][c];
		Queue<int []> q = new ArrayDeque<int[]>();
		Queue<int []> zeroQ = new ArrayDeque<int[]>();
		q.offer(new int [] {r,c});
		res[1]++;
		res[0]++;
		visited[r][c] = true;
		while (!q.isEmpty()) {
			int [] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int cr = now[0] + dr[i];
				int cc = now[1] + dc[i];
				if (0 <= cr && cr < N && 0 <= cc && cc < N && 
						(map[cr][cc] == standardNum || map[cr][cc] == 0)&& !visited[cr][cc]) {
					q.offer(new int [] {cr,cc});
					visited[cr][cc] = true;
					res[1]++;
					if (map[cr][cc] == 0) {
						res[0]++;
						zeroQ.add(new int [] {cr, cc});
					}
				}
			}
		}
		RemoveZeroVisited(zeroQ);
		return res;
	}
	private static void RemoveZeroVisited(Queue<int[]> zeroQ) {
		while (!zeroQ.isEmpty()) {
			int []now = zeroQ.poll();
			visited[now[0]][now[1]] = false;
		}
	}
}