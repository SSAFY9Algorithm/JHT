package study.day0407;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_2015_수들의합4_380ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		int [] arr = new int [N];
		arr[0] = Integer.parseInt(st.nextToken());
		map.put(arr[0], 1);
		for (int i = 1; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1] + num;
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
			else
				map.put(arr[i], 1);
		}
		long ans = 0;
		if (map.containsKey(K))
			ans = map.get(K);
		for (int i = 0; i < N; i++) {
			map.put(arr[i],map.get(arr[i])-1);
			if (map.containsKey(K+arr[i])) {
				ans += map.get(K+arr[i]);
			}
		}
		System.out.println(ans);
	}
}