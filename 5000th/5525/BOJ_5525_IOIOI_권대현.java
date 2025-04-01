import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5525_IOIOI_권대현 {

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 테스트 케이스 입력 받기
		inputTestCase();

		// 2. 문자열 포함 여부 탐색하기
		searchString();

		// 3. 포함된 개수 출력하기
		System.out.println(count);
	}

	static int count;
	static int patternCount;
	private static void searchString() {
		count = 0;
		patternCount = 0;
		
		for (int charIndex = 1; charIndex < stringLength - 1; charIndex++) {
			// "IOI" 패턴을 찾으면 patternCount 증가
			if (string.charAt(charIndex - 1) == 'I' && string.charAt(charIndex) == 'O' && string.charAt(charIndex + 1) == 'I') {
				patternCount++;

				// 목표하는 N 차수에 도달했을 때 count 증가
				if (patternCount >= cardinal) {
					count++;
				}
				charIndex++; // "O"를 건너뛰어 탐색 최적화
			} else {
				patternCount = 0; // 연속되지 않으면 초기화
			}
		}
	}

	static int cardinal;
	static int stringLength;
	static String string;

	private static void inputTestCase() throws IOException {
		// 1-1. IOI 차수 N 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		cardinal = Integer.parseInt(st.nextToken());
		// 1-2. 문자열 길이 M 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		stringLength = Integer.parseInt(st.nextToken());
		// 1-3. 문자열 정보 입력 받기
		string = br.readLine().trim();
	}
}
