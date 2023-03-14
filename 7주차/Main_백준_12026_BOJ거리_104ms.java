package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_12026_BOJ거리_104ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String street = br.readLine();
		ArrayList<Integer> B = new ArrayList<>();
		ArrayList<Integer> O = new ArrayList<>();
		ArrayList<Integer> J = new ArrayList<>();
		B.add(0);
		int [] weight = new int [N];
		for (int i = 1; i < N; i++) {
			weight[i] = 2147483647;
		}
		for (int i = 1; i < N; i++) {
			if (street.charAt(i) == 'O') {
				O.add(i);
				for (int j = 0; j < B.size(); j++) {
					int a = B.get(j);
					if (weight[a] == 2147483647)
						continue;
					weight[i] = Math.min(weight[i], weight[a] + (i - a)*(i - a));
				}
			}
			else if(street.charAt(i) == 'J') {
				J.add(i);
				for (int j = 0; j < O.size(); j++) {
					int a = O.get(j);
					if (weight[a] == 2147483647)
						continue;
					weight[i] = Math.min(weight[i], weight[a] + (i - a)*(i - a));
				}
			}
			else if(street.charAt(i) == 'B') {
				B.add(i);
				for (int j = 0; j < J.size(); j++) {
					int a = J.get(j);
					if (weight[a] == 2147483647)
						continue;
					weight[i] = Math.min(weight[i], weight[a] + (i - a)*(i - a));
				}
			}
		}
		if (weight[N-1] == 2147483647)
			System.out.println(-1);
		else
			System.out.println(weight[N-1]);
	}
}
