package study.sixWeeks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 괄호안에 여러 연산 가능 : 문제 잘못 봄
public class Main_백준_16637_괄호추가하기 {
	static char [] sign;
	static int ans = -2147483647;
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
		int [] temp = new int[N/2 + 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = nums[i];
		}
		CheckAll(0, temp);
		System.out.println(ans);
	}
	private static void CheckAll(int cnt, int[] temp) {
		if (cnt == sign.length) {
			if (ans < temp[0])
				ans =temp[0];
			return;
		}
		for (int i = 0; i < sign.length; i++) {
			if (sign[i] == '.')continue;
			int a = temp[i];
			int b = temp[i + 1];
			char op = sign[i];
			sign[i] = '.';
			int opNum = con(a, b, op);
			int j = i -1;
			int k = i + 1;
			while (j >= 0 && sign[j] == '.')j--;
			temp[j + 1] = opNum;
			while (k < sign.length && sign[k] == '.')k++;
			temp[k] = opNum;
			CheckAll(cnt + 1, temp);
			sign[i] = op;
			temp[j + 1] = a;
			temp[k] = b;
		}
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
