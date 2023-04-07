package study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_178525_주사위윷놀이_120ms {
	static int [] arr = new int [10];
	static int [] map = new int [35];
	static int [] horse = new int [4];
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		makeMap();
		play(0, 0);
		System.out.println(ans);
	}
	private static void play(int cnt, int sum) {
		if (cnt == 10) {
			if (ans < sum) {
				ans = sum;
			}
			return;
		}
		if (cnt == 0) {
			moveHorse(0, 0);
			play(cnt + 1, sum + map[horse[0]]);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (horse[i] == 21)continue;
			int temp = horse[i];
			moveHorse(cnt, i);
			//이동후 위치에 말이 없을 경우 다음으로 이동
			if (!(horse[i]!= 21 && (horse[i] == horse[(i + 1)%4] || horse[i] == horse[(i + 2)%4] || horse[i] == horse[(i + 3)%4]))) {
				play(cnt + 1, sum + map[horse[i]]);
			}
			horse[i] = temp;
		}
	}
	private static void moveHorse(int cnt, int num) {
		if (horse[num] == 5) {
			horse[num] = 21;
		}
		else if (horse[num] == 10) {
			horse[num] = 29;
		}
		else if (horse[num] == 15) {
			horse[num] = 25;
		}
		for (int i = 0; i < arr[cnt]; i++) {
			horse[num]++;
			if (horse[num] == 21) {
				break;
			}
			else if (horse[num] == 25 || horse[num] == 29) {
				horse[num] = 32;
			}else if (horse[num] == 35) {
				horse[num] = 20;
			}
		}
	}
	private static void makeMap() { // 끝idx = 21
		for (int i = 1; i < 21; i++) { // 시작 ~ 40
			map[i] = i * 2;
		}
		for (int i = 1; i < 4; i++) { // 왼쪽
			map[21 + i] = map[5] + i * 3;
		}
		for (int i = 1; i < 4; i++) { // 오른쪽 
			map[25 + i] = map[15] - i - 1;
		}
		{// 아래
		map[30]=22;
		map[31]=24;
		}
		for (int i = 0; i < 3; i++) {
			map[32+i] = 25 + i * 5;
		}
	}
}
