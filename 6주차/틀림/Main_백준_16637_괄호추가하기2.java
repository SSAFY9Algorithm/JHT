package study.sixWeeks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
// DP이용
public class Main_백준_16637_괄호추가하기2 {
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
		int [] ans = new int[N/2 + 1];
		ans[0] = nums[0];
		ans[1] = con(nums[0], nums[1], sign[0]);
		for (int i = 2; i < ans.length; i++) {
			int temp = con(nums[i - 1], nums[i], sign[i-1]);
			ans[i] = Math.max(con(ans[i - 1], nums[i], sign[i-1]), con(ans[i-2],temp,sign[i-2]));
		}
		System.out.println(ans[N/2]);
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
