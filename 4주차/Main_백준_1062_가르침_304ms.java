package study.day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//1% -> 50%-> 100% 
/*
 *
(50 -> 100)테스트케이스
1 7
antabbtica
correct answer: 1

2 7
antaatica
antabtica
correct answer: 2

1 7
antabctica
correct answer: 1
 */
public class Main_백준_1062_가르침_304ms {
	static int N, K, ans;
	static Alpha [] chars;
	static int [] cntN;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		chars = new Alpha[26];
		cntN = new int [N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (str.length() > 8)// 기본으로 주어지는 actin제거 + 앞뒤 삭제
				str = str.substring(4,str.length()-4).replace("a", "").replace("c", "").replace("t", "").replace("i", "").replace("n", "");
			else {
				str = "";
			}
			for (int j = 0; j < str.length(); j++) { //문자열에 중복 제거
				if (chars[(str.charAt(j)-'a')] == null)
					chars[(str.charAt(j)-'a')] = new Alpha();
				if (chars[(str.charAt(j)-'a')].li.contains(i))continue;
				chars[(str.charAt(j)-'a')].li.add(i);
				cntN[i]++;
			}
		}
		if (K < 5) { // k < 5면 기본도 못읽음 return 0;
			System.out.println(ans);
			return;
		}
		K -= 5;
		for (int i = 0; i < N; i++) { //actin으로 읽을 수 있는 단어 개수
			if (cntN[i] == 0)
				ans++;
		}
		check(K, ans, 0);
		System.out.println(ans);
	}
	private static void check(int k, int temp, int start) {// 문자 26개 중 K개 뽑는 조합 이용, temp는 ans 개수
		if (k == 0 || start == 26) {// k개 전부 가르친경우 || z알려주고 k가 남은 경우
			if (temp > ans)
				ans = temp;
			return;
		}
		boolean checker = true; 
		for (int i = start; i < 26; i++) {
			if (chars[i] == null)continue;
			checker = false;
			int plus = 0;
			for (int j = 0; j < chars[i].li.size(); j++) {
				cntN[chars[i].li.get(j)]--;
				if (cntN[chars[i].li.get(j)] == 0)
					plus++;
			}
			check(k -1, temp + plus, i+1);
			for (int j = 0; j < chars[i].li.size(); j++) {
				cntN[chars[i].li.get(j)]++;
			}
		}
		if (checker) { // k가 남았는데 모든 단어를 아는 경우 체크
			if (temp > ans)
				ans = temp;
			return;
		}
	}
}
class Alpha{
	List<Integer> li = new ArrayList<Integer>();
}
