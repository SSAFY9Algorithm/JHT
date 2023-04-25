package study.day0410;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_백준_16934_게임닉네임_552ms {
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node start = new Node();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			makeNice(start, str, 0, false);
		}
		System.out.println(ans.toString());
	}

	private static void makeNice(Node start, String str, int idx, boolean make) {
		if (str.length() == idx) {
			start.cnt++;
			if (!make) {
				ans.append(str.substring(0, idx));
				if (start.cnt != 1)
					ans.append(start.cnt);
				ans.append("\n");
			}
			return;
		}
		if (start.map.containsKey(str.charAt(idx))) {
			makeNice(start.map.get(str.charAt(idx)), str, idx + 1, make);
		} else {
			if (!make)
				ans.append(str.substring(0, idx + 1)).append("\n");
			start.map.put(str.charAt(idx), new Node());
			makeNice(start.map.get(str.charAt(idx)), str, idx + 1, true);
		}
	}

	static class Node {
		int cnt = 0;
		HashMap<Character, Node> map = new HashMap<>();
	}
}