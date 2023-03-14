package study.sevenWeeks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * (line = br.readLine())!= null로 EndOfFile 확인 하는 것 중요!
 * Q시간 넘어가면 while문 종료 했었는데 틀렸다나옴 왜 인지 모르겟음
 */
public class Main_백준_19583_싸이버개강총회_428ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int [] S = new int [2];
		int [] E = new int [2];
		int [] Q = new int [2];
		StringTokenizer stS = new StringTokenizer(st.nextToken(),":"); // 시작시간
		S[0] = Integer.parseInt(stS.nextToken());
		S[1] = Integer.parseInt(stS.nextToken());
		StringTokenizer stE = new StringTokenizer(st.nextToken(),":"); // 끝난시간
		E[0] = Integer.parseInt(stE.nextToken());
		E[1] = Integer.parseInt(stE.nextToken());
		StringTokenizer stQ = new StringTokenizer(st.nextToken(),":"); // 스트리밍 종료시간
		Q[0] = Integer.parseInt(stQ.nextToken());
		Q[1] = Integer.parseInt(stQ.nextToken());
		int ans = 0;
		Set<String> logs = new HashSet<>();
		String line = "";
		int beforeH = 0;
		int beforeM = 0;
		while ((line = br.readLine())!= null) { // 백준 제출시 eof확인
//		while (!(line = br.readLine()).equals("")) { // 콘솔에서 확인시 enter확인
			StringTokenizer chat = new StringTokenizer(line," ");
			StringTokenizer time = new StringTokenizer(chat.nextToken(),":");
			int H = Integer.parseInt(time.nextToken());
			int M = Integer.parseInt(time.nextToken());
			if (H < beforeH || (H == beforeH && M < beforeM))
				break;
			beforeH = H;
			beforeM = M;
			String name = chat.nextToken();
			if ((H < S[0]) || (S[0] == H && M <= S[1])) {
				logs.add(name);
			}
			else if ((E[0] < H || E[0] == H && M >= E[1]) && ((H < Q[0]) || (Q[0] == H && M <= Q[1]))) {
				if (logs.contains(name)) {
					logs.remove(name);
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
