package study.DataStructure.Tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991_트리순회 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char [] tree = new char [67108864]; //2^26 = 67108864
		tree[0] = 'A';
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char p = st.nextToken().charAt(0);
			int pIdx = -1;
			for (int j = 0; j < tree.length; j++) {
				if (tree[j] == p) {
					pIdx = j;
					break;
				}
			}
			tree[(pIdx + 1) * 2 - 1] = st.nextToken().charAt(0); //왼쪽노드
			tree[(pIdx + 1) * 2] = st.nextToken().charAt(0); //오른쪽 노드
		}
		ans(tree);
	}

	public static void ans(char[] tree) {
		avant(tree, 0);//전
		System.out.println();
		lieuten(tree, 0);//중
		System.out.println();
		rear(tree, 0);//후
	}
	public static void avant(char[] tree, int i) {
		if (tree[i] != '.' && tree[i] != '\0') {
			System.out.print(tree[i]);
			avant(tree, (i+1)*2 -1);
			avant(tree, (i+1)*2);
		}
	}
	public static void lieuten(char[] tree, int i) {
		if (tree[i] == '.' || tree[i] == '\0')
			return;
		lieuten(tree, (i+1)*2 -1);
		System.out.print(tree[i]);
		lieuten(tree, (i+1)*2);
	}

	public static void rear(char[] tree, int i) {
		if (tree[i] == '.' || tree[i] == '\0')
			return;
		rear(tree, (i+1)*2 -1);
		rear(tree, (i+1)*2);
		System.out.print(tree[i]);
	}


}
