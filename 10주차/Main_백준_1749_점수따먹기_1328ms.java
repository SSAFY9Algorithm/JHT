package study.day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1749_점수따먹기_1328ms {
	static int ans = -2147483647, N, M;
	static int [][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i > 0 )
					map[i][j] += map[i-1][j];
				if (j > 0)
					map[i][j] += map[i][j - 1];
				if (j > 0 && i > 0)
					map[i][j] -= map[i - 1][j - 1];
			}
		}
		SearchAll();
		System.out.println(ans);
	}
	private static void SearchAll() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ans < map[i][j])
					ans = map[i][j];
				for (int subi = 0; subi <= i; subi++) {
					for (int subj = 0; subj <= j; subj++) {
						int temp = map[i][j] - ((subj != j)?map[i][subj]: 0) - ((subi != i)?map[subi][j]: 0) + ((subi != i && subj != j)? map[subi][subj] : 0);
						if (ans < temp)
							ans = temp;
					}
				}
			}
		}
	}
}
