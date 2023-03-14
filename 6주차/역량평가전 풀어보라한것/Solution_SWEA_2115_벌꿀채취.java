package study.day0306test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2115_벌꿀채취 {
	static int N,M,C;
	static int [][] map, maxHoney;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통크기
			M = Integer.parseInt(st.nextToken()); // 벌통개수
			C = Integer.parseInt(st.nextToken()); // 꿀 채취 최대양
			map = new int [N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxHoney = new int [N][N - M + 1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					conHoney(0, i, j, 0, 0);
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					int temp = maxHoney[i][j];
					for (int k = j + M; k < N - M + 1; k++) {
						if (ans < temp + maxHoney[i][k])
							ans = temp + maxHoney[i][k];
					}
					for (int i2 = i + 1; i2 < N; i2++) {
						for (int j2 = 0; j2 < N - M + 1; j2++) {
							if (ans < temp + maxHoney[i2][j2])
								ans = temp + maxHoney[i2][j2];
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void conHoney(int cnt, int r, int c, int flag, int temp) {
		if (temp > C)return;
		if (cnt == M) {
			int result = 0;
			for (int i = 0; i < M; i++) {
				if ((flag & (1 << i)) == 0)continue;
				result  += (map[r][c + i] * map[r][c + i]);
			}
			if (maxHoney[r][c] < result)
				maxHoney[r][c] = result;
			return;
		}
		conHoney(cnt + 1, r, c, flag | (1 << cnt), temp + map[r][c + cnt]);
		conHoney(cnt + 1, r, c, flag, temp);
	}
}
