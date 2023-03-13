package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 이게 왜맞는 거냐?
 * 이해가 안되네
 */
public class Main_백준_1106_호텔 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int arr[][] = new int [N][2];
		int [] D = new int [C + 1]; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, (o1,o2) 
				-> (((double)o1[0] /(double)o1[1]) < ((double)o2[0])/(double)o2[1])? -1 
				: ((double)o1[0] /(double)o1[1]==(double)o2[0]/(double)o2[1])?o2[0] - o1[0]:1);
		for (int i = 1; i < D.length; i++) {
			D[i] = 2147483647;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < C + 1; j++) {
				D[j] = Math.min(D[j], arr[i][0] * ((j-1)/arr[i][1] + 1));
				for (int k = 1; k < j/arr[i][1] + 1; k++) {
					if (j - k * arr[i][1] > 0) {
						D[j] = Math.min(D[j], D[j - k * arr[i][1]] + k * arr[i][0]);
					}
				}
			}
		}
		System.out.println(D[C]);
	}
}
