package study.twoWeeks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_16437_양구출작전 {
	public static Node[] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int island = Integer.parseInt(br.readLine());
		map = new Node[island + 1];
		for (int i = 1; i <= island; i++) {
			Node newI = new Node();
			map[i] = newI;
		}
		for (int i = 2; i <= island; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char ws = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			Node newI = map[i];
			int P = Integer.parseInt(st.nextToken());
			if (ws == 'W')
				newI.num = -num;
			else
				newI.num = num;
			newI.P = P;
			map[P].Cs.add(i);
		}
		long ans = check(1);
		System.out.println(ans);
	}
	private static long check(int is) {
		long ans = 0;
		Node now = map[is];
		for (int i = 0; i < now.Cs.size() ; i++) {
			ans += check(now.Cs.get(i));
		}
		ans = ans + now.num;
		if (ans < 0)
			return 0;
		else
			return ans;
	}
}
class Node{
	int num;
	int P;
	ArrayList<Integer> Cs = new ArrayList<>();
}
