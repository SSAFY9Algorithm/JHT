package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_17609_회문_284ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			int front = 0;
			int back = str.length()-1;
			while (front < back && str.charAt(front) == str.charAt(back)) {
				front++;
				back--;
			}
			if (front >= back) {
				sb.append(0).append("\n");
				continue;
			}
			int testf =  front + 1;
			int testb = back;
			while (testf < testb && str.charAt(testf) == str.charAt(testb)) {
				testf++;
				testb--;
			}
			if (testf >= testb) {
				sb.append(1).append("\n");
				continue;
			}
			testf =  front;
			testb = back-1;
			while (testf < testb && str.charAt(testf) == str.charAt(testb)) {
				testf++;
				testb--;
			}
			if (testf >= testb) {
				sb.append(1).append("\n");
				continue;
			}
			sb.append(2).append("\n");
		}
		System.out.println(sb.toString());
	}
}
