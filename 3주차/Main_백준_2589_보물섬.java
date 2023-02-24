package study.day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2589_보물섬 {
	public static char[][] map;
	public static int [][] checker;
	public static int N, M;
	static int I = -1, J = -1;
	private static int [] di = {1, -1, 0, 0};
	private static int [] dj = {0, 0, 1, -1};
	private static int ans = 0, temp ;
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
				System.out.println(i + ", " + j);
				temp = 0;
				check(0, i, j); // BFS실행
				check(0, I, J); // 가장 먼곳에서 다시 BFS
				if (temp > ans)
					ans = temp;
			}
		}
		System.out.println(ans);
	}
	private static void check(int cnt, int i, int j) {
		if (map[i][j] == 'W')
			return ;
		map[i][j] = 'W';
		if (cnt == 0)
			checker = new int [N][M];
		if (checker[i][j] == 0)
			checker[i][j] = cnt;
		else
			checker[i][j] = Math.min(checker[i][j], cnt);
		for (int idx = 0; idx < 4; idx++) {
			int ci = i + di[idx];
			int cj = j + dj[idx];
			if (0 <= ci && ci < N && 0 <= cj && cj < M)
				check(cnt + 1, ci, cj);
		}
		map[i][j] = 'L';
		if (cnt == 0) {
			if (i == I && j == J)
				test(0,i,j);
			else
				test(1,i,j);
		}
		return ;
	}
	private static void test(int what,int i, int j) {
		if (map[i][j] == 'W')
			return ;
		if (temp < checker[i][j]) {
			I = i;
			J = j;
			temp = checker[i][j];
		}
		map[i][j] = 'W';
		for (int idx = 0; idx < 4; idx++) {
			int ci = i + di[idx];
			int cj = j + dj[idx];
			if (0 <= ci && ci < N && 0 <= cj && cj < M)
				test(what,ci, cj);
		}
		if (what == 1)
			map[i][j] = 'L';
	}
}