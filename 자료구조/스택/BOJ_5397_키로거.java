package study.DataStructure.Stack;
/**
 *	system 사용시 시간초과 나옴
 *	그래서 BufferedWriter 사용했음 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_5397_키로거 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String st = br.readLine();
			int check = 0;
			//시작이 커서 이동 + 백스페이스 일경우
			while(st.charAt(check) == '<' || st.charAt(check) == '>' || st.charAt(check) == '-')
				check++;
			char [] Arr = new char [st.length()];
			int A = 0;
			char [] Brr = new char [st.length()];
			int B = 0;
			//나머지 문자체크 
			for (int j = check; j < Brr.length; j++) {
				//백스페이스 일 경우 Arr에서 1개 삭제
				if (st.charAt(j) == '-' && A > 0)
					A--;
				//커서이동<일 경우 Arr에서 Brr로 데이터 이동
				else if (st.charAt(j) == '<' && A > 0) {
					Brr[B++] = Arr[A-1];
					A--;
				}
				//커서이동<일 경우 Brr에서 Arr로 데이터 이동
				else if (st.charAt(j) == '>' && B > 0) {
					Arr[A++] = Brr[B-1];
					B--;
				}
				//문자 일경우 Arr에 입력
				else if (st.charAt(j) != '<' && st.charAt(j) != '>' && st.charAt(j) != '-')
					Arr[A++] = st.charAt(j);
			}
			for (int j = 0; j < A; j++) {
				bw.write(Arr[j]);
			}
			for (int j = 0; j < B; j++) {
				bw.write(Brr[B-1-j]);
			}
			bw.write("\n");
		}
		bw.close();
	}
}
