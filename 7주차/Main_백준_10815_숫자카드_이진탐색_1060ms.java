package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 솔직히 HashSet쓰면 빠르긴함
 */
public class Main_백준_10815_숫자카드_이진탐색_1060ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N= Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int arr [] = new int [N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		st = new StringTokenizer(br.readLine());
		int M= Integer.parseInt(st.nextToken());
		int [] ans = new int [M]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int a = Integer.parseInt(st.nextToken());
			ans[i] = check(arr, 0, N - 1, a);
		}
		StringBuilder sb = new StringBuilder();
		for (int i : ans) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int check(int[] arr, int front, int back, int a) {
		int mid = (front + back)/2;
		if (back < front) {
			return 0;
		}
		if (arr[mid] == a)
			return 1;
		else if (arr[mid] < a) {
			return check(arr, mid + 1, back, a);
		}
		else if (arr[mid] > a) {
			return check(arr, front, mid - 1, a);
		}
		return 0;
	}
}
