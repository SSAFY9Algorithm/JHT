package study.homestudy.day0422;
import java.util.*;
/**
 *
 * 배열 사용 시간초과
 *
 */
public class Solution_시간초과 {
	public static void main(String[] args) {
		System.out.println(solution(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
		System.out.println(solution(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
	}
    static String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        boolean [] YN = new boolean [n];
        Stack<Integer> s = new Stack<>();
        for(int i = 0 ; i < cmd.length;i++){
            StringTokenizer st = new StringTokenizer(cmd[i], " ");
            String com = st.nextToken();
            System.out.println(k);
            if (com.equals("U")){
                int X = Integer.parseInt(st.nextToken());
                while (X > 0){
                    if (!YN[k - 1])
                        X--;
                    k--;
                }
            }else if (com.equals("D")){
                int X = Integer.parseInt(st.nextToken());
                while (X > 0){
                    if (!YN[k + 1])
                        X--;
                    k++;
                }
            }else if (com.equals("C")){
                s.add(k);
                YN[k] = true;
                while (k < n && YN[k]) k++;
                if (k == n){
                    while (k == n || YN[k]) k--;
                }
            }else if (com.equals("Z")){
                int re = s.pop();
                YN[re] = false;
            }
        }
        for(int i = 0 ;i < n; i++){
            if (YN[i])
                answer.append("X");
            else
                answer.append("O");
        }
        return answer.toString();
    }
}