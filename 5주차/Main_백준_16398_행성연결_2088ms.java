package study.February.day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_16398_행성연결_2088ms {
	static int [] setNum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		setNum = new int [N];
		for (int i = 0; i < N; i++) {
			setNum[i] = i;
		}
		int [][] lines = new int[N*(N-1)/2][3];
		int index = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (i < j) {
					lines[index][0] = a;
					lines[index][1] = i;
					lines[index][2] = j;
					index++;
				}
			}
		}
		Arrays.sort(lines,(o1,o2)-> (o1[0] - o2[0]));
		long cost = 0;
		for (int i = 0; i < lines.length; i++) {
			int a = find(lines[i][1]);
			int b = find(lines[i][2]);
			if (a==b)continue;
			if (a<b)
				setNum[b] = a;
			else
				setNum[a] = b;
			cost += (long)lines[i][0];
		}
		System.out.println(cost);
	}
	
	private static int find(int i) {
		if (setNum[i] != i)
			setNum[i] = find(setNum[i]);
		return setNum[i];
	}
}
