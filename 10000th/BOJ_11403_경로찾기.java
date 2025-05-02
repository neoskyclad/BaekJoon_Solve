import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_11403_경로찾기
 * 
 * 1. 테스트 케이스 입력 받기
 * 
 * 2. 플로이드워샬로 경로 여부 찾기
 * 
 * 3. 결과 출력
 */
public class BOJ_11403_경로찾기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 1. 테스트 케이스 입력 받기
		inputTestCase();
		// 2. 플로이드워샬로 경로 여부 찾기
		searchRoute();
		// 3. 결과 출력
		System.out.println(sb);
	}
	
	
	private static void searchRoute() {
		
		for (int k = 0; k < arraySize; k++) {
			for (int i = 0; i < arraySize; i++) {
				for (int j = 0; j < arraySize; j++) {
					if (array[i][k] && array[k][j]) {
						array[i][j] = true;
					}
				}
			}
		}
		
		for (int rowIndex = 0; rowIndex < arraySize; rowIndex++) {
			for (int colIndex = 0; colIndex < arraySize; colIndex++) {
				sb.append(array[rowIndex][colIndex] ? 1 : 0).append(" ");
			}
			sb.append("\n");
		}
	}
	
	static int arraySize;
	static boolean[][] array;
	private static void inputTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		arraySize = Integer.parseInt(st.nextToken());
		
		array = new boolean[arraySize][arraySize];
		for (int rowIndex = 0; rowIndex < arraySize; rowIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIndex = 0; colIndex < arraySize; colIndex++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					array[rowIndex][colIndex] = true;
				}
			}
		}
	}
}
