package study.BF;
/**
 * 전체 맵을 담는 배열을 만들어서 계산
 * (시간초과)
 */
import java.util.Scanner;

public class BOJ_18111_마인크래프트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int B = sc.nextInt();
		int [] arr = new int [N*M];
		int min = 256;
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
			if (arr[i] > max)
				max = arr[i]; 
			if (arr[i] < min)
				min = arr[i]; 
		}
		int ans = 2147483647;
		int check = 0;
		for (int i = min; i <= max; i++) {
			int time = 0;
			int B_check = B;
			for (int j = 0; j < arr.length; j++) {
				if (i - arr[j] < 0) {
					time = time - 2 * (i - arr[j]);
					B_check = B_check - (i - arr[j]);
				}
				else if (i - arr[j] > 0) {
					time = time + (i - arr[j]);
					B_check = B_check - (i - arr[j]);
				}
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