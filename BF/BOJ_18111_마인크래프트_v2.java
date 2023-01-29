package study.BF;

/**
 *	시간초과의 원인으로 모든 맵을 배열에 담는 것으로 판단
 *	그래서 257의 배열을 만들어서 존재하는 높이의 개수를 저장
 *	(시간초과) 
 */

import java.util.Scanner;

public class BOJ_18111_마인크래프트_v2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int B = sc.nextInt();
		int [] map = new int [257];
		int min = 256;
		int max = 0;
		for (int i = 0; i < N*M; i++) {
			int a = sc.nextInt();
			map[a]++;
			if (a > max)
				max = a; 
			if (a < min)
				min = a; 
		}
		int ans = 2147283647;
		int check = 0;
		for (int i = min; i <= max; i++) {
			int time = 0;
			int B_check = B;
			for (int j = min; j <= max; j++) {
				if (map[j] == 0 || i == j)
					continue;
				if (i - j < 0) {
					time = time - 2 *(map[j] * (i - j));
					B_check = B_check - (map[j] * (i - j));
				}
				else {
					time = time + (map[j] * (i - j));
					B_check = B_check - (map[j] * (i - j));
				}
				if (time > ans)
					break;
			}
			if (B_check >= 0 && time <= ans) {
				ans = time;
				check = i;
			}
			if (time > ans)
				break;
		}
		System.out.println(ans + " " + check);
	}
}
