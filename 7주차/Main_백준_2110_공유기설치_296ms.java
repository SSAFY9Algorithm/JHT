package study.day0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2110_공유기설치_296ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int [] houses = new int [N];
		int min = 1;
		int max = 0;
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
			if (max < houses[i])
				max = houses[i];
			if (min > houses[i])
				min = houses[i];
		}
		Arrays.sort(houses);
		max = max - min;
		min = 1;
		int ans = 0;
		while (min <= max) {
			int mid = (min + max) / 2;
			int temp = 1;
			int anstemp = 2147483647;
			for (int i = 0; i < N; i++) {
				int j = i+1;
				while (j < N && houses[j] - houses[i] < mid) {
					j++;
				}
				if (j < N && houses[j] - houses[i] >= mid) {
					temp++;
					if (houses[j] - houses[i] < anstemp) {
						anstemp = houses[j] - houses[i];
					}
				}
				i = j-1;
			}
			if (temp >= C) {
				min = mid + 1;
				ans = anstemp;
			}
			else
				max = mid - 1;
		}
		System.out.println(ans);
	}
}
