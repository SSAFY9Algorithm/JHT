package study.day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17070_파이프옮기기1_268ms {
	static int [][] map;
	static int N,ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int startr = 0;
		int startc = 1;
		int signt = 0; //0: 가로, 1: 세로, 2 : 대각선
		check(startr, startc, signt);
		System.out.println(ans);
	}
	private static void check(int nowr, int nowc, int signt) {
		if (nowr == N-1 && nowc == N-1) {
			ans++;
			return;
		}
		if (signt == 0)
			move1(nowr, nowc);
		else if (signt == 1)
			move2(nowr, nowc);
		else if (signt == 2)
			move3(nowr, nowc);
	}
	private static void move3(int nowr, int nowc) { // 대각선 일때
		if (nowc+1 < N && map[nowr][nowc + 1]!=1)
			check(nowr, nowc + 1, 0);
		if (nowr+1 < N && map[nowr + 1][nowc]!=1)
			check(nowr + 1, nowc, 1);
		if (nowr + 1 < N && nowc + 1 < N && map[nowr][nowc + 1]!=1 && map[nowr + 1][nowc]!=1 && map[nowr + 1][nowc + 1]!=1)
			check(nowr + 1, nowc + 1, 2);
	}
	private static void move2(int nowr, int nowc) { // 세로 일때
		if (nowr+1 < N && map[nowr + 1][nowc]!=1)
			check(nowr + 1, nowc, 1);
		if (nowr + 1 < N && nowc + 1 < N && map[nowr][nowc + 1]!=1 && map[nowr + 1][nowc]!=1 && map[nowr + 1][nowc + 1]!=1)
			check(nowr + 1, nowc + 1, 2);
	}
	private static void move1(int nowr, int nowc) { // 가로 일때
		if (nowc+1 < N && map[nowr][nowc + 1]!=1)
			check(nowr, nowc + 1, 0);
		if (nowr + 1 < N && nowc + 1 < N && map[nowr][nowc + 1]!=1 && map[nowr + 1][nowc]!=1 && map[nowr + 1][nowc + 1]!=1)
			check(nowr + 1, nowc + 1, 2);
	}
}
