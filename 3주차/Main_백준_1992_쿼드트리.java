package study.twoWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_1992_쿼드트리 {
	public static int [][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		System.out.println(ans(0,0,N));
	}
	private static String ans(int y, int x, int n) {
		if (n == 1)
			return (map[y][x] + "");
		String t1 = ans(y,x,n/2); //좌상
		String t2 = ans(y,x+n/2,n/2); // 우상
		String t3 = ans(y+n/2,x,n/2); // 좌하
		String t4 = ans(y+n/2,x+n/2,n/2); // 우하
		// 문자열만 비교하면 모두 1010일 경우 1010으로 출력 그래서 0이나 1이 하나라도 포함하지 않을 조건을 넣어야함 
		if (t1.equals(t2) && t1.equals(t3) && t1.equals(t4)&&(t1.contains("0") == false || t1.contains("1") == false))
			return t1;
		else
			return "(" + t1 + t2 + t3 + t4 + ")";
	}
}
