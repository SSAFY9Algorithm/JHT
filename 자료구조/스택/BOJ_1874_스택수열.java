package study.DataStructure.Stack;

/** 
 * 직접 넣으면서 확인
 * 스텍에 1~N 넣다가(+) 동일한 값나오면 (-)  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1874_스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int [N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int [] stack = new int [N];
		int aIdx = 0; //arr_idx
		int sIdx = 0; //stack_idx
		StringBuffer ans = new StringBuffer("");
		for (int i = 1; i <= N; i++) { //스텍에 1~N까지 넣을 예정
			stack[sIdx++] = i;
			ans.append("+\n");
			while (stack[sIdx-1] == arr[aIdx]) {
				sIdx--;
				aIdx++;
				ans.append("-\n");
				if (sIdx == 0)
					break;
			}
		}
		if (aIdx == N)
			System.out.println(ans);
		else
			System.out.println("NO");
	}
}
