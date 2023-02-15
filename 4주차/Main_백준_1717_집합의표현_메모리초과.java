package study.day0215;

/** 메모리 초과*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Main_백준_1717_집합의표현_메모리초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<Integer, Set<Integer>> arr = new HashMap<Integer, Set<Integer>>();// 각 원소는 본인이 속한 집합을 들고다님
		for (int i = 0; i < N+1; i++) {
			Set<Integer> add = new HashSet<>();
			add.add(i);
			arr.put(i, add);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int checker = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (checker == 0) {// 각각이 집합을 들고 다니기 때문에 a,b 둘다 집합에 서로 값을 넣어줌
				Set<Integer> temp = arr.get(b);
				for (Integer num : arr.get(a)) {
					temp.add(num);
				}
				arr.put(b, temp);
				temp = arr.get(a);
				for (Integer num : arr.get(b)) {
					temp.add(num);
				}
				arr.put(a, temp);
			}
			else {
				if (arr.get(a).contains(b))// a집합에 b가포함 되었는지
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}
}
