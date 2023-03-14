package study.day0306test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기 {
	static int [][] map;
	static List<int []> li;
	static int N, coreCnt,lineLen;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int [N][N];
			li = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						li.add(new int [] {i,j});
				}
			}
			coreCnt = 0;
			lineLen = 2147483647;
			check(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(lineLen).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void check(int coreNum, int coretemp, int lentemp) {
		if (coreNum == li.size()) {
			if (coreCnt < coretemp) {
				coreCnt = coretemp;
				lineLen = lentemp;
			}
			else if (coreCnt == coretemp && lineLen > lentemp) {
				lineLen = lentemp;
			}
			return;
		}
		int nowr = li.get(coreNum)[0];
		int nowc = li.get(coreNum)[1];
		if (nowr == 0 || nowr == N-1 || nowc == 0 || nowc == N-1) {
			check(coreNum + 1, coretemp + 1, lentemp);
			return;
		}
		//상
		int i = nowr - 1;
		int temp = 0;
		for (; i >= 0; i--) {
			if (map[i][nowc] != 0) break;
			map[i][nowc] = 2;
			temp++;
		}
		if (i == -1) {
			check(coreNum + 1, coretemp + 1, lentemp + temp);
			for (int j = nowr - 1; j >= 0; j--) {
				map[j][nowc] = 0;
			}
		}else {
			for (int j = i + 1; j < nowr; j++) {
				map[j][nowc] = 0;
			}
		}
		//하
		i = nowr + 1;
		temp = 0;
		for (; i < N; i++) {
			if (map[i][nowc] != 0) break;
			map[i][nowc] = 2;
			temp++;
		}
		if (i == N) {
			check(coreNum + 1, coretemp + 1, lentemp + temp);
			for (int j = nowr + 1; j < N; j++) {
				map[j][nowc] = 0;
			}
		}else {
			for (int j = i - 1; j > nowr; j--) {
				map[j][nowc] = 0;
			}
		}
		//좌
		i = nowc - 1;
		temp = 0;
		for (; i >= 0; i--) {
			if (map[nowr][i] != 0) break;
			map[nowr][i] = 2;
			temp++;
		}
		if (i == -1) {
			check(coreNum + 1, coretemp + 1, lentemp + temp);
			for (int j = nowc - 1; j >= 0; j--) {
				map[nowr][j] = 0;
			}
		}else {
			for (int j = i + 1; j < nowc; j++) {
				map[nowr][j] = 0;
			}
		}
		//우
		i = nowc + 1;
		temp = 0;
		for (; i < N; i++) {
			if (map[nowr][i] != 0) break;
			map[nowr][i] = 2;
			temp++;
		}
		if (i == N) {
			check(coreNum + 1, coretemp + 1, lentemp + temp);
			for (int j = nowc + 1; j < N; j++) {
				map[nowr][j] = 0;
			}
		}else {
			for (int j = i - 1; j > nowc; j--) {
				map[nowr][j] = 0;
			}
		}
		check(coreNum + 1, coretemp, lentemp);
	}
}
