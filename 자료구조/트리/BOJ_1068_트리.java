package study.DataStructure.Tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		List<Integer> dels = new ArrayList<Integer>();//제거해야되는 노드 목록
		dels.add(M);
		//제거 과정 - '-1'로 했다가 루트노드랑 비교가 안되어 '-2'로 수정
		while (dels.size() != 0) {
			int del = dels.get(0);
			arr[del] = -2;
			for (int i = 0; i < N; i++) {
				if (arr[i] == del) {
					arr[i] = -2;
					dels.add(i);
				}
			}
			dels.remove(0);
		}
		int ans = N;
		//정답 추출(삭제 || 부모로 가리킬 경우)N--
		for (int i = 0; i < N; i++) {
			if (arr[i] == -2) {
				ans--;
				continue;
			}
			else {
				for (int j = 0; j < N; j++) {
					if (arr[j] == i) {
						ans--;
						break;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
