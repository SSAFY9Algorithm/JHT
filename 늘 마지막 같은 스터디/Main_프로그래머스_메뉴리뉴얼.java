package study.homestudy.day0424;
import java.util.*;
/**
 * 
 *	크기 별로 조합하여 map 과 pq에 저장
 *	pq에서 1 아니고 pq가 비어있을 때 까지 t(해당크기의 최대 개수) 비교하셔 list에 넣는다
 *
 */
public class Main_프로그래머스_메뉴리뉴얼 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(Solution.solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int [] {2, 3, 4})));
		System.out.println(Arrays.toString(Solution.solution(new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int [] {2, 3, 5})));
		System.out.println(Arrays.toString(Solution.solution(new String[] {"XYZ", "XWY", "WXA"}, new int [] {2, 3, 4})));
	}
}
class Solution {
    static Map<String, Integer> map;
    static PriorityQueue<Node> pq;
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> ansList = new ArrayList<>();
        for (int k : course){
            map = new HashMap<>();
            pq = new PriorityQueue<>((o1,o2)->(o2.cnt - o1.cnt));
            for(int i = 0 ; i < orders.length ; i++){
                comb(k, 0, 0, orders[i], new char [k]);
            }
            int t = 1;
            if (!pq.isEmpty())
            	t = pq.peek().cnt;
            while (t != 1 && !pq.isEmpty() && pq.peek().cnt == t){
                ansList.add(pq.poll().str);
            }
        }
        answer = new String [ansList.size()];
        Collections.sort(ansList);
        for(int i = 0; i < ansList.size() ; i++){
            answer[i] = ansList.get(i);
        }
        return answer;
    }
    public static void comb(int K, int cnt, int idx, String str, char [] sup){
        if (K == cnt){
            StringBuilder tempBu = new StringBuilder();
            char [] tempArr = new char[K];
            for (int i = 0 ; i < K; i++)
            	tempArr[i] = sup[i];
            Arrays.sort(tempArr);
            for (int i = 0 ; i < K; i++){
                tempBu.append(tempArr[i]);
            }
            String temp = tempBu.toString();
            if (map.containsKey(temp)){
                map.put(temp, map.get(temp) + 1);
                Node a = new Node(temp, map.get(temp) + 1);
                pq.offer(a);
            } else if (!map.containsKey(temp)){
                map.put(temp, 1);
                Node a = new Node(temp, 1);
                pq.offer(a);
            }
            return;
        }
        if (idx == str.length())return;
        comb(K, cnt, idx + 1, str, sup);
        sup[cnt] = str.charAt(idx);
        comb( K, cnt + 1, idx + 1, str, sup);
    }
    static class Node {
        String str;
        int cnt;
        public Node(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
}