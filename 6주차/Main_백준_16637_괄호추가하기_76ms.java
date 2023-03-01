package study.sixWeeks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
// DP이용 && 최소값에서 연산을 통해 최대값이 나올 수 있음 -> 각 위치의 최소값도 구함  
public class Main_백준_16637_괄호추가하기_76ms {
	static char [] sign;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int [] nums = new int[N/2 + 1];
		sign = new char[N/2];
		int cnt1 = 0, cnt2 = 0;
		for (int i = 0; i < str.length(); i++) {
			if ('0' <= str.charAt(i) && str.charAt(i) <= '9')
				nums[cnt1++] = Character.getNumericValue(str.charAt(i));
			else
				sign[cnt2++] = str.charAt(i);
		}
		if (N == 1) {
			System.out.println(nums[0]);
			return;
		}
		int [][] ans = new int[N/2 + 1][2];
		ans[0][0] = nums[0];
		ans[0][1] = nums[0];
		ans[1][0] = con(nums[0], nums[1], sign[0]);
		ans[1][1] = con(nums[0], nums[1], sign[0]);
		for (int i = 2; i < ans.length; i++) {
			int temp = con(nums[i - 1], nums[i], sign[i-1]);
			int max1 = Math.max(con(ans[i - 1][0], nums[i], sign[i-1]), con(ans[i-2][0],temp,sign[i-2]));
			int max2 = Math.max(con(ans[i - 1][1], nums[i], sign[i-1]), con(ans[i-2][1],temp,sign[i-2]));
			ans[i][0] = Math.max(max1, max2);
			int min1 = Math.min(con(ans[i - 1][0], nums[i], sign[i-1]), con(ans[i-2][0],temp,sign[i-2]));
			int min2 = Math.min(con(ans[i - 1][1], nums[i], sign[i-1]), con(ans[i-2][1],temp,sign[i-2]));
			ans[i][1] = Math.min(min1, min2);
		}
		System.out.println(ans[N/2][0]);
	}
	private static int con(int a, int b, char op) {
		if (op == '+')
			return a+b;
		else if (op == '-')
			return a-b;
		else if (op == '*')
			return a*b;
		return 0;
	}
}
