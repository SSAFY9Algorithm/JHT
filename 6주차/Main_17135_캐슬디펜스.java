package study.day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
	static int [][] map;
	static boolean [][] visited;
	static int N,M,D,ans = -2147483648, enemy = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		visited = new boolean [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					enemy++;
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				 for (int k = j + 1; k < M; k++) {
					check(N, new int [] {i, j, k}, 0);
				}
			}
		}
		System.out.println(ans);
	}
	private static void check(int ArcherR, int[] po, int catchNum) {
		if (ans == enemy)return;
		if (ArcherR == 0) {
			if (ans < catchNum) {
				ans = catchNum;
			}
			return;
		}
		int [][] temp = new int [3][3]; // 0 :길이, 1: r값, 2: c값
		for (int i = 0; i < 3; i++) {
			temp[i][0] = 2147483647;
		}
		for (int i = 1; i <= D; i++) {
			if (ArcherR - i < 0)break;
			for (int j = 0; j < M; j++) {
				if (map[ArcherR - i][j] == 0 || visited[ArcherR - i][j])continue;
				for (int archer = 0; archer < 3; archer++) {
					int distance = dis(ArcherR - i,j,ArcherR,po[archer]);
					if (distance <= D && distance < temp[archer][0]) {
						temp[archer][0] = distance;
						temp[archer][1] = ArcherR - i;
						temp[archer][2] = j;
					}
					else if (distance <= D && distance == temp[archer][0] && temp[archer][2] > j) {
						temp[archer][0] = distance;
						temp[archer][1] = ArcherR - i;
						temp[archer][2] = j;
					}
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			if (temp[i][0] != 2147483647 && !visited[temp[i][1]][temp[i][2]]) {
				visited[temp[i][1]][temp[i][2]] = true;
				catchNum++;
			}
		}
		check(ArcherR - 1, po, catchNum);
		for (int i = 0; i < 3; i++) {
			if (temp[i][0] != 2147483647)
				visited[temp[i][1]][temp[i][2]] = false;
		}
	}
	private static int dis(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
}
