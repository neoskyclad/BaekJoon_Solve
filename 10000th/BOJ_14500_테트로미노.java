import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author neoskyclad
 * @version 1.1
 * 
 * BOJ 14500 테트로미노
 * 1. 테스트 케이스 입력 받기
 * 	1-1. 세로 크기 N, 가로 크기 M 입력 받기
 * 	1-2. 배열 데이터 입력 받기
 * 
 * 2. 테트로미노의 원소 최대 합 구하기
 * 	2-1. 이차원 배열 탐색
 * 		2-1-1. 만약 해당 좌표를 방문하지 않았다면,
 * 			2-1-1-1. T자 테트로미노의 최대 합 구하기
 * 			2-1-1-2. 나머지 테트로미노의 최대 합 구하기
 * 			2-1-1-3. 로컬 최대 합과 비교하여 최대 값으로 갱신하기
 * 3. 결과 출력하기
 */
public class BOJ_14500_테트로미노 {

	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 테스트 케이스 입력 받기
		inputTestCase();

		// 2. 테트로미노의 원소 최대 합 구하기
		int maxSum = 0;
		maxSum = getMaxSumOfTetromino();

		// 3. 결과 출력하기
		System.out.println(maxSum);
	}

	static int localMaxSum;
	static boolean[][] visited;
	private static int getMaxSumOfTetromino() {
		int maxSum = 0; // 명시적으로 최대 합을 담을 변수
		visited = new boolean[rowSize][colSize]; // 방문 체크 배열 초기화

		// 2-1. 이차원 배열 탐색
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			for (int colIndex = 0; colIndex < colSize; colIndex++) {
				// 2-1-1. 만약 해당 좌표를 방문하지 않았다면,
				if (!visited[rowIndex][colIndex]) {
					localSum = 0;

					// 2-1-1-1. T자 테트로미노의 최대 합 구하기
					for (int dir = 0; dir < 4; dir++) {
						int TSum = array[rowIndex][colIndex];
						int check = 0;
						for (int otherDir = 0; otherDir < 3; otherDir++) {
							int nextRow = rowIndex + DELTA_TShape[dir][otherDir][0];
							int nextCol = colIndex + DELTA_TShape[dir][otherDir][1];
							
							if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
								continue;
							check++;
							TSum += array[nextRow][nextCol];
						}
						if (check == 3) {
							localSum = Math.max(localSum, TSum);
						}
					}
					// 2-1-1-2. 나머지 테트로미노의 최대 합 구하기
					getLocalSumOfTetromino(rowIndex, colIndex, 0, 0);
					// 2-1-1-3. 로컬 최대 합과 비교하여 최대 값으로 갱신하기
					localMaxSum = Math.max(localSum, localMaxSum);
				}
			}
		}
		maxSum = localMaxSum;
		return maxSum;
	}

	static int localSum;
	static final int[] DELTA_ROW = { -1, 0, 1, 0 };
	static final int[] DELTA_COL = { 0, -1, 0, 1 };
	static final int[][][] DELTA_TShape = { { { -1, 0 }, { 0, 1 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 }, { 1, 0 } },
			{ { 0, -1 }, { 0, 1 }, { -1, 0 } }, { { -1, 0 }, { 0, -1 }, { 1, 0 } }, };
	private static void getLocalSumOfTetromino(int rowIndex, int colIndex, int count, int sum) {
		visited[rowIndex][colIndex] = true;
		// 기저 조건
		if (count == 4) {
			localSum = Math.max(localSum, sum);
			return;
		}

		// 상하좌우 탐색
		for (int dir = 0; dir < 4; dir++) {
			int nextRow = rowIndex + DELTA_ROW[dir];
			int nextCol = colIndex + DELTA_COL[dir];

			if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
				continue;
			if (!visited[nextRow][nextCol]) {
				visited[nextRow][nextCol] = true;
				getLocalSumOfTetromino(nextRow, nextCol, count + 1, sum + array[nextRow][nextCol]);
				visited[nextRow][nextCol] = false;
			}
		}
		visited[rowIndex][colIndex] = false;
	}

	static int rowSize;
	static int colSize;
	static int[][] array;
	private static void inputTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 1-1. 세로 크기 N, 가로 크기 M 입력 받기
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		array = new int[rowSize][colSize]; // 배열 초기화
		// 1-2. 배열 데이터 입력 받기
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIndex = 0; colIndex < colSize; colIndex++) {
				array[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
