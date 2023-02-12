package study.twoWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_1389_케빈베이컨의6단계법췩 {
	static Node[] map = new Node[101]; // 100명
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (map[a] == null)
				map[a] = new Node();
			if (map[b] == null)
				map[b] = new Node();
			map[a].fr.add(b);
			map[b].fr.add(a);
		}
		int min = 2147483647;
		int ans = -1;
		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if(i == j)continue;
				temp += find(i,j);
			}
			if (temp < min) {
				min = temp;
				ans = i;
			}
		}
		System.out.println(ans);
	}
	private static int find(int a, int b) { //남은 경로중 a 에서 b 찾는 제일 짧은 경로
		if (a == b)
			return 0;
		map[a].check = 1;
		int ans = 2147483647;
		for (int k = 0; k < map[a].fr.size(); k++) {
			if (map[map[a].fr.get(k)].check == 0)
				ans = Math.min(ans, find(map[a].fr.get(k),b));
		}
		map[a].check = 0;
		if (ans == 2147483647)
			return ans;
		return ans + 1;
	}
}
class Node{
	int check = 0; //지나 갔는지 표시
	List<Integer> fr = new ArrayList<Integer>(); //친구
}