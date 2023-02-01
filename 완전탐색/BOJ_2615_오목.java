package study.BF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {
	static int[][] map = new int [19][19];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int U = 19;
		int D = 0;
		int L = 19;
		int R = 0;		
		for (int i = 0; i < 19; i++) {
			String line = br.readLine();
			StringTokenizer lineSt = new StringTokenizer(line);
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(lineSt.nextToken());
				if (map[i][j] != 0) {
					if (i < U)
						U = i;
					if (i > D)
						D = i;
					if (j < L)
						L = j;
					if (j > R)
						R = j;
				}
			}
		}
		int x = -1;
		int y = -1;
		for (int i = U; i <= D; i++) {
			for (int j = L; j <= R; j++) {
				if (map[i][j] != 0 && check(i,j)) {
					x = i + 1;
					y = j + 1;
					break;
				}
			}
			if (x != -1)
				break;
		}
		if (x == -1)
			System.out.println(0);
		else {
			System.out.println(map[x-1][y-1]);
			System.out.println(x + " " + y);
		}
	}
	private static boolean check(int x, int y) {
		int check = map[x][y];
		boolean five1 = true;
		boolean five2 = true;
		boolean five3 = true;
		boolean five4 = true;
		if (y < 15) {
			for (int i = 0; i < 5; i++) {
				if (map[x][y+i] != check){
					five1 = false;
					break;
				}
			}
			if (five1 && y < 14 && map[x][y+5]== check)
				five1 = false;
			if (five1 && y > 0 && map[x][y-1]== check)
				five1 = false;
		}
		else
			five1 = false;
		if (x < 15) {
			for (int i = 0; i < 5; i++) {
				if (map[x+i][y] != check){
					five2 = false;
					break;
				}
			}
			if (five2 && x < 14 && map[x+5][y]== check)
				five2 = false;
			if (five2 && x >0 && map[x-1][y]== check)
				five2 = false;
		}
		else
			five2 = false;
		if (x < 15 && y < 15) {
			for (int i = 0; i < 5; i++) {
				if (map[x+i][y+i] != check){
					five3 = false;
					break;
				}
			}
			if (five3 && x < 14 && y < 14 && map[x+5][y+5]== check)
				five3 = false;
			if (five3 && x > 0 && y > 0 && map[x-1][y-1]== check)
				five3 = false;
		}
		else
			five3 = false;
		if (x > 3 && y < 15) {
			for (int i = 0; i < 5; i++) {
				if (map[x - i][y + i] != check){
					five4 = false;
					break;
				}
			}
			if (five4 && x > 4 && y < 14 && map[x-5][y+5]== check)
				five4 = false;
			if (five4 && x < 18 && y > 0 && map[x+1][y-1]== check)
				five4 = false;
		}
		else
			five4 = false;
		return (five1 || five2 || five3 || five4);
	}
}