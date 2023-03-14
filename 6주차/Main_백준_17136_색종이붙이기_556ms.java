package study.sixWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 백트래킹으로 1096ms에서 800ms로 줄임 
 * sizeTest안에 for문 2개 순서 바꿈 556ms
 * 메모리 낭비가 많은 것 같음 - 개선 방안 필요 (스터디에서 의견 나눠도 좋을듯(다른 문제들 포함))
 */
public class Main_백준_17136_색종이붙이기_556ms {
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [][] map = new int [10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 2147483647;
		int [] sizeCnt = {5,5,5,5,5};
		count(sizeCnt, map, 0);
		if (ans == 2147483647)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
	private static void count(int [] sizeCnt, int[][] map, int cnt) {
		if (ans < cnt) return;
		for (int i = 0; i < 5; i++) {
			if (sizeCnt[i] < 0)return;
		}
		int i = 0;
		int j = 0;
check:	for (; i < 10; i++) { // map 상태 확인
			j = 0;
			for (; j < 10; j++) {
				if (map[i][j] != 0) {
					break check;
				}
			}
		}
		if (i == 10 && j == 10) { // 전부 0일 경우  
			int temp = 5*5; // cnt를 나중에 추가해서 이렇게 계산했음
			for (int k = 0; k < 5; k++) {
				temp -= sizeCnt[k];
			}
			if (temp < ans)
				ans = temp;
			return;
		}
		for (int size = 5; size > 0; size--) { // 해당 위치에서 가능한 사이들 선택후 재귀
			int [][] temp = sizeTest(size, i, j, map);
			if (temp == null)continue;
			sizeCnt[size-1]--;
			count(sizeCnt, temp, cnt+1);
			sizeCnt[size-1]++;
		}
	}
	private static int[][] sizeTest(int size, int r, int c, int[][] map) {// size가 맞지 않을 경우 null반환 아니면 덮은 결과 반환
		for (int i = 0; i < size; i++) { 
			for (int j = 0; j < size; j++) {
				if ((r + i) > 9 || (c + j) > 9 || map[r + i][c + j] == 0)
					return null;
			}
		}
		int [][] res = new int [10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (r <= i && i < r + size && c <= j && j < c + size)
					res[i][j] = 0;
				else
					res[i][j] = map[i][j];
			}
		}
		return res;
	}
}
