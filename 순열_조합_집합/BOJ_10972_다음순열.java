package study.Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10972_다음순열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int [N];
		int[] brr = new int [N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			brr[i] = arr[i];
		}
		int i = N-2;
		for (; i > -1; i--) {
			int back = -1;
			int check = N +1;
			for (int j = i + 1; j < N; j++) {
				if  (arr[i] < arr[j] && check > arr[j]) {
					back = j;
					check = arr[j];
				}
			}
			if (check > N)
				continue;
			arr[back] = arr[i];
			arr[i] = check;
			brr[back] = brr[i];
			brr[i] = check;
			for (int j = i+1; j < N; j++) {
				arr[j] = brr[N-1 -(j - (i + 1))];
			}
			break;
		}
		if (i == -1)
			System.out.println(-1);
		else {
			for (int a : arr)
				System.out.print(a + " ");
			System.out.println();
			}
	}
}
