package study.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_9663_NQueen_4772ms {
	static int [] arr ;// 퀸을 놓을 판
	static int ans = 0 , N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int [N];
		for (int i = 0; i < N; i++) {
			arr[0] = i;
			make(1);// 직접 퀸을 놓는 작업
		}
		System.out.println(ans);
	}
	private static void make(int cnt) {
		if (cnt == N) {// arr이 다 찼을 때 ans++
			ans ++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (canNum(cnt, i)) {
				arr[cnt] = i;
				make(cnt + 1);
			}
		}
	}
	private static boolean canNum(int cnt, int i) {// 해당 cnt위치에 i 값이 가능한지 앞에 값들과 비교
		boolean ans = true;
		for (int j = 0; j < cnt; j++) {
			if (arr[j] == i) { // 같은 가로 선상
				ans = false;
				break;
			}
			if (arr[j] - i == j - cnt || arr[j] - i == -j + cnt) { // 대각선 선상
				ans = false;
				break;
			}
		}
		return ans;
	}
}
