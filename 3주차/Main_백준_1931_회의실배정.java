package study.twoWeeks;
/**
 *	v1 그냥 부분집합 전부구함 -> 시간초과
 * 
 *	v2 -> 시작시간으로 끝이 짧은 것만 고르면 될거 같아서 중복 없애고 map(start, end(start에서 최소값))로 값 저장 계산 -> 틀림
 * 		& 다시 모든 부분집합을 재귀로 구현 -> 메모리초과
 * 
 * 	9번정도 시도후 그리디를 공부할 필요를 느낌.
 * 	그리디가 뭔지 전혀 몰라 찾아봄 - 유명한 문제였음
 * 
 *	v3 -> map(start,end) 였던 것이 문제였음 => map(end, start(end에서 최대값)) 로 수정
 *		문제가 어렵고 내가 많이 부족하다는 것을 뼈져리게 느낌 (이번주 너무 어렵다...)
 *  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_1931_회의실배정 {
	public static Map<Integer, Integer> map; // map(end, start(end에서 최대값))값을 넣을 map
	public static Map<Integer,Integer> equals; // 시작과 끝이 동일한 것을 map(end, 개수) 값을 넣을 equals 
	public static int sMax = -1;
	public static int ans = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		equals = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // start
			int e = Integer.parseInt(st.nextToken()); // end
			if (sMax < s)
				sMax = s;
			if (s==e) {
				if (equals.containsKey(e))
					equals.put(e, equals.get(e)+1);
				else
					equals.put(e, 1);
			}
			else {
				if (map.containsKey(e))
					map.put(e,Math.max(map.get(e), s));
				else
					map.put(e, s);
			}
		}
		//키값을 저장할 배열 2개
		Object[] keyList1 = map.keySet().toArray();
		Object[] keyList2 = equals.keySet().toArray();
		
		//키값을 저장할 배열 2개 를 정렬
		Arrays.sort(keyList1); 
		Arrays.sort(keyList2);

		int L1 = keyList1.length; //mapMAXIndex
		int L2 = keyList2.length; //equalsMAXIndex
		
		int idx1 = 0;// map current Index
		int idx2 = 0;// equals current Index
		
		int ans = 0;
		int frontEnd = 0; // 이전 회의가 끝난 시간
		while(idx1 != L1 || idx2 != L2) {
			if (idx1 == L1) { // map에 있는 값을 모두 확인 한 경우
				if((Integer)keyList2[idx2] >= frontEnd) {
					frontEnd = (Integer)keyList2[idx2];
					ans += equals.get(keyList2[idx2]);
				}
				idx2++;
			} 
			else if (idx2 == L2) { // equals에 있는 값을 모두 확인 한 경우
				if (map.get(keyList1[idx1]) >= frontEnd) {
					frontEnd = (Integer)keyList1[idx1];
					ans++;
				}
				idx1++;
			}
			//keyList1[idx1]회의 가 keyList2[idx2]회의 보다 먼저 끝나는 경우
			else if ((Integer)keyList1[idx1] < (Integer)keyList2[idx2]) {
				if (map.get(keyList1[idx1]) >= frontEnd) {
					frontEnd = (Integer)keyList1[idx1];
					ans++;
				}
				idx1++;
			}
			//keyList2[idx2]회의 가 keyList1[idx1]회의 보다 먼저 끝나는 경우
			else if ((Integer)keyList1[idx1] > (Integer)keyList2[idx2]) {
				if((Integer)keyList2[idx2] >= frontEnd) {
					frontEnd = (Integer)keyList2[idx2];
					ans += equals.get(keyList2[idx2]);
				}
				idx2++;
			}
			//keyList2[idx2]회의 가 keyList1[idx1]회의랑 동일 할 때 두개 다 계산
			else {
				if((Integer)keyList1[idx1] >= frontEnd) {
					if (map.get(keyList1[idx1]) >= frontEnd) {
						ans++;
					}
					ans += equals.get(keyList2[idx2]);
					frontEnd = (Integer)keyList1[idx1];
				}
				idx1++;
				idx2++;
			}
		}
		System.out.println(ans);
	}
}
