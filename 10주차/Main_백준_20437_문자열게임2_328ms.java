package study.day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main_백준_20437_문자열게임2_328ms {
	static int min, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int  T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 0; testCase < T; testCase++) {
			min = 2147483647;
			max = -2147483648;
			String str = br.readLine();
			int N = Integer.parseInt(br.readLine());
			HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
			for (int i = 0; i < str.length(); i++) {
				char now = str.charAt(i);
				if (map.containsKey(now)) {
					map.get(now).add(i);
				}else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(i);
					map.put(now, list);
				}
				count(map.get(now), N);
			}
			if (min == 2147483647)
				sb.append("-1\n");
			else
				sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void count(ArrayList<Integer> arrayList, int n) {
		if (arrayList.size() < n)
			return;
		int temp = arrayList.get(arrayList.size()-1) - arrayList.get(arrayList.size() - n) + 1;
		if (temp < min)
			min = temp;
		if (temp > max)
			max = temp;
	}
}
