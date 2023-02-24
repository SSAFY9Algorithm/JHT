package study.day0215;
/**
 * 너비우선탐색 공부 필요
 * 코드 보는데 나도 이해안됨
 * 코드 설명해달라하면 못 할 확률 90%
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2589_보물섬_됨 {
	public static char[][] map;
	public static int N, M;
	static int I = -1, J = -1;
	private static int [] di = {1, -1, 0, 0};
	private static int [] dj = {0, 0, 1, -1};
	private static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세
		M = Integer.parseInt(st.nextToken()); // 가
		map = new char [N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'W')continue;
				int temp = BFS(i,j);
				if (ans < temp)
					ans = temp;
			}
		}
		System.out.println(ans-1);
	}
	private static int BFS(int i, int j) {
		int [][] checker = new int [N][M];
		Queue<int[]> q = new ArrayDeque<>();
		int first[] = new int [2];
		first [0] = i;
		first [1] = j;
		checker[i][j] = 1;
		q.offer(first);
		int ans = 0;
		while (!q.isEmpty()) {
			int [] a = q.poll();
			int ai = a[0];
			int aj = a[1];
			for (int idx = 0; idx < 4; idx++) {
				int ci = ai + di[idx];
				int cj = aj + dj[idx];
				if (0 <= ci && ci < N && 0 <= cj && cj < M && map[ci][cj] == 'L'&& checker[ci][cj] == 0) {
					checker[ci][cj] = checker[ai][aj] + 1;
					ans =checker[ci][cj]; 
					int []add = new int [2];
					add[0] = ci;
					add[1] = cj;
					q.offer(add);
				}
			}
		}
		return ans;
	}
}