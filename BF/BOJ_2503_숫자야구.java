package study.BF;

import java.util.Scanner;

public class BOJ_2503_숫자야구 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int [][] ques = new int [N][5];
		for (int i = 0; i < N; i++) {
			int a = sc.nextInt();
			ques[i][0] = a%10;
			ques[i][1] = (a/10) % 10;
			ques[i][2] = a/100;
			ques[i][3] = sc.nextInt();
			ques[i][4] = sc.nextInt();
		}
		int cnt = 0;
		for (int hun = 1; hun < 10; hun++) {
			for (int ten = 1; ten < 10; ten++) {
				for (int one = 1; one < 10; one++) {
					if (one!=ten && ten != hun && hun != one) {
						int impossible = 0;
						for (int i = 0; i < N; i++) {
							if (imCheck(hun,ten,one,ques[i])) { 
								impossible = 1;
								break;
							}
						}
						if (impossible == 0)
							cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}

	private static boolean imCheck(int hun, int ten, int one, int[] is) {
		int st = 0;
		int ba = 0;
		if (is[0] == hun)
			st++;
		if (is[1] == ten)
			st++;
		if (is[2] == one)
			st++;
		if (is[0] == ten || is[0] == one )
			ba++;
		if (is[1] == hun || is[1] == one)
			ba++;
		if (is[2] == hun || is[2] == ten)
			ba++;
		if (st == is[3] && ba == is[4])
			return false;
		return true;
	}
}
