package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_5430_AC
 * 
 * 1. 테스트 케이스 입력 받기
 * 	1-1. 테스트 케이스 개수 입력 받기
 * 		1-1-1. 수행할 함수 p 입력 받기
 * 		1-1-2. 배열 원소 개수 n 입력 받기
 * 		1-1-3. 배열 원소 입력 받기 
 * 
 * 2. 각 테스트 케이스 별 함수 수행
 * 	2-1. 테스트 케이스 순회
 * 		2-1-1. 함수 명령어 한 글자씩 순회
 * 			2-1-1-1. 만약 R이라면,
 * 				2-1-1-1-1. 기준 인덱스에 -1을 곱하여 방향을 바꿈
 * 			2-1-1-2. 만약 D라면,
 * 				2-1-1-2-1. 만약 기준 인덱스가 top이라면,
 * 					2-1-1-2-1-1. top 인덱스 1 증가
 * 				2-1-1-2-2. 만약 기준 인덱스가 rear라면,
 * 					2-1-1-2-2-1. rear 인덱스 1 감소
 * 				2-1-1-2-3. 만약 top이 rear보다 크거나, rear가 top보다 작으면,
 * 					2-1-1-2-3-1. 결과에 error를 append하고 순회 멈추기.
 *		2-1-2. 기준 인덱스와 top, rear 인덱스로 결과에 원소 append하기
 *			2-1-2-1. 기준 인덱스가 top이라면,
 *				2-1-2-1-1. 순방향으로 top부터 rear까지 순회하여 원소 append
 *			2-1-2-2. 기준 인덱스가 rear라면,
 *				2-1-2-2-1. 역방향으로 rear부터 top까지 순회하여 원소 append
 * 
 * 3. 각 테스트 케이스 별 결과 출력
 */

public class BOJ_5430_AC_권대현 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 입력 받기
		inputTestCase();

		// 2. 각 테스트 케이스 별 함수 수행
		operateFunctionOfTestCases();
		
		// 3. 각 테스트 케이스 별 결과 출력
		System.out.println(sb);

	}

	private static void operateFunctionOfTestCases() {

		// 2-1. 테스트 케이스 순회
		for (int testCaseIndex = 0; testCaseIndex < testCaseCount; testCaseIndex++) {
			boolean isError = false;
			int operand = 1;	// 순방향: 1, 역방향: -1
			int top = 0;
			int rear = numberArrays[testCaseIndex].length - 1;
			// 2-1-1. 함수 명령어 한 글자씩 순회
			for (int functionIndex = 0; functionIndex < functions[testCaseIndex].length(); functionIndex++) {
				char currentFunction = functions[testCaseIndex].charAt(functionIndex);
				// 2-1-1-1. 만약 R이라면,
				if (currentFunction == 'R') {
					// 2-1-1-1-1. 기준 인덱스에 -1을 곱하여 방향을 바꿈
					operand *= -1;
				}
				// 2-1-1-2. 만약 D라면,
				else if (currentFunction == 'D') {
					// 2-1-1-2-3. 만약 top이 rear보다 크거나, rear가 top보다 작으면,
					if (top > rear || rear < 0) {
						isError = true;
						break;
					} else {
						// 2-1-1-2-1. 만약 기준 인덱스가 top이라면,
						if (operand > 0) {
							// 2-1-1-2-1-1. top 인덱스 1 증가
							top++;
						}
						// 2-1-1-2-2. 만약 기준 인덱스가 rear라면,
						else if (operand < 0) {
							// 2-1-1-2-2-1. rear 인덱스 1 감소
							rear--;
						}
					}
				}
			}
			
			if (isError) {
				sb.append("error").append("\n");
			} else {
				// 2-1-2. 기준 인덱스와 top, rear 인덱스로 결과에 원소 append하기
				sb.append("[");
				// 2-1-2-1. 기준 인덱스가 top이라면,
				if (operand > 0) {
					// 2-1-2-1-1. 순방향으로 top부터 rear까지 순회하여 원소 append
					for (int index = top; index <= rear; index += operand) {
						sb.append(numberArrays[testCaseIndex][index]).append(",");
						if (index == rear) {
							sb.deleteCharAt(sb.length() - 1);	// 마지막 쉼표 지우기
						}
					}
				}
				// 2-1-2-2. 기준 인덱스가 rear라면,
				else if (operand < 0) {
					// 2-1-2-2-1. 역방향으로 rear부터 top까지 순회하여 원소 append
					for (int index = rear; index >= top; index += operand) {
						sb.append(numberArrays[testCaseIndex][index]).append(",");
						if (index == top) {
							sb.deleteCharAt(sb.length() - 1);	// 마지막 쉼표 지우기
						}
					}
				}
				
				sb.append("]").append("\n");
			}
		}
	}

	static int testCaseCount;
	static String[] functions;
	static int[][] numberArrays;
	private static void inputTestCase() throws IOException {

		// 1-1. 테스트 케이스 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		testCaseCount = Integer.parseInt(st.nextToken());

		functions = new String[testCaseCount];
		numberArrays = new int[testCaseCount][];
		for (int testCaseIndex = 0; testCaseIndex < testCaseCount; testCaseIndex++) {
			// 1-1-1. 수행할 함수 p 입력 받기
			functions[testCaseIndex] = br.readLine().trim();

			// 1-1-2. 배열 원소 개수 n 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int numberCount = Integer.parseInt(st.nextToken());

			numberArrays[testCaseIndex] = new int[numberCount];
			// 1-1-3. 배열 원소 입력 받기
			st = new StringTokenizer(br.readLine().trim(), "[,]");
			int numberIndex = 0;
			while (st.hasMoreTokens()) {
				numberArrays[testCaseIndex][numberIndex++] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
