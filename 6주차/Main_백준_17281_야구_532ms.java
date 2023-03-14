package study.sixWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17281_야구_532ms {
	static int[]lineUp;
	static boolean [] visited;
	static int [][] mans;
	static int N, ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		mans = new int [N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				mans[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		lineUp = new int [9];
		visited = new boolean[9];
		visited[0] = true;
		makeLinrUp(0);
		System.out.println(ans);
	}
	private static void makeLinrUp(int cnt) {
		if (cnt == 3) {
			makeLinrUp(cnt + 1);
			return;
		}
		if (cnt == 9) {
			checkScore();
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (visited[i])continue;
			visited[i] = true;
			lineUp[cnt] = i;
			makeLinrUp(cnt + 1);
			visited[i] = false;
		}
	}
	private static void checkScore() {
		int innings = 0;
		int playerNum = 0;
		int score = 0;
		boolean[] base; 
		while (innings < N) {
			int outCount = 0;
			base = new boolean [3];
			while (outCount != 3) {
				switch (mans[innings][lineUp[playerNum]]) {
				case 0:
					outCount++;
					break;
				case 1:
					if (base[2] == true)
						score++;
					base[2] = base[1];
					base[1] = base[0];
					base[0] = true;
					break;
				case 2:
					if (base[2] == true)
						score++;
					if (base[1] == true)
						score++;
					base[2] = base[0];
					base[1] = true;
					base[0] = false;
					break;
				case 3 :
					if (base[2] == true)
						score++;
					if (base[1] == true)
						score++;
					if (base[0] == true)
						score++;
					base[2] = true;
					base[1] = false;
					base[0] = false;
					break;
				case 4 :
					if (base[2] == true)
						score++;
					if (base[1] == true)
						score++;
					if (base[0] == true)
						score++;
					score++;
					base[2] = false;
					base[1] = false;
					base[0] = false;
					break;
				}
				playerNum = (playerNum + 1) % 9;
			}
			innings++;
		}
		if (ans < score)
			ans = score;
	}
}
