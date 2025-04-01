import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_7569_토마토
 * 
 * @author daehyun.kwon
 * 
 * 1. 테스트 케이스 입력 받기
 * 	1-1. 상자의 가로 크기 M, 세로 크기 N, 개수 H 입력 받기
 * 	1-2. 개수 H만큼 반복
 * 		1-2-1. 각 상자 별 정보 입력 받기
 * 			1-2-1-1. 만약 1를 입력 받았다면,
 * 				1-2-1-1-1. 토마토 리스트에 저장
 * 			1-2-1-2. 만약 0을 입력 받았다면,
 * 				1-2-1-2-1. 앞으로 남은 개수에 1 증가시키기
 * 
 * 2. 토마토 시뮬레이트하기
 * 	2-1. 토마토 큐에 현재 토마토 리스트 넣기
 * 	2-2. 토마토 큐가 빌 때까지 BFS 탐색
 * 		2-2-1. 방문 처리 및 1로 바꾸기
 * 		2-2-2. 날짜 갱신하기
 * 		2-2-3. 6방향 탐색하기
 * 			2-2-3-1. 만약 배열을 벗어난다면,
 * 				2-2-3-1-1. 루프 패스
 * 			2-2-3-2. 만약 아직 방문하지 않았고 익지 않은 토마토라면,
 * 				2-2-3-2-1. 토마토 큐에 해당 토마토 넣기
 * 				2-2-3-2-2. 방문처리 하기
 * 				2-2-3-2-3. 남아 있는 토마토 1 감소시키기
 * 
 * 3. 결과값 출력하기
 *
 */
public class BOJ_7569_토마토_권대현 {
	
	static class Tomato {
		int height;
		int row;
		int col;
		int day;
		
		public Tomato(int height, int row, int col, int day) {
			this.height = height;
			this.row = row;
			this.col = col;
			this.day = day;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 테스트 케이스 입력 받기
		inputTestCase();
		
		// 2. 토마토 시뮬레이트하기
		simulate();
		
		// 3. 결과값 출력하기
		System.out.println(maxDay);
	}
	
	static int maxDay;
	static final int[] DELTA_HEIGHT = {0, 0, 0, 0, -1, 1 };
	static final int[] DELTA_ROW = { -1, 0, 1, 0, 0, 0 };
	static final int[] DELTA_COL = { 0, -1, 0, 1, 0, 0};
	private static void simulate() {
		maxDay = 0;
		boolean[][][] visited = new boolean[heightSize][rowSize][colSize];
		
		// 2-1. 토마토 큐에 현재 토마토 리스트 넣기
		Queue<Tomato> tomatoQueue = new ArrayDeque<>();
		for (Tomato tomato : tomatoList) {
			tomatoQueue.offer(tomato);
		}
		// 2-2. 토마토 큐가 빌 때까지 BFS 탐색
		while(!tomatoQueue.isEmpty()) {
			Tomato tomato = tomatoQueue.poll();
			int height = tomato.height;
			int row = tomato.row;
			int col = tomato.col;
			int day = tomato.day;
			
			// 2-2-1. 방문 처리 및 1로 바꾸기
			visited[height][row][col] = true;
			box[height][row][col] = 1;
			// 2-2-2. 날짜 갱신하기
			maxDay = Math.max(maxDay, day);
			
			// 2-2-3. 6방향 탐색하기
			for (int dir = 0; dir < 6; dir++) {
				int nextHeight = height + DELTA_HEIGHT[dir];
				int nextRow = row + DELTA_ROW[dir];
				int nextCol = col + DELTA_COL[dir];
				
				// 2-2-3-1. 만약 배열을 벗어난다면,
				if (nextHeight < 0 || nextHeight >= heightSize || nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
					// 2-2-3-1-1. 루프 패스
					continue;
				// 2-2-3-2. 만약 아직 방문하지 않았고 익지 않은 토마토라면,
				if (!visited[nextHeight][nextRow][nextCol] && box[nextHeight][nextRow][nextCol] == 0) {
					// 2-2-3-2-1. 토마토 큐에 해당 토마토 넣기
					tomatoQueue.offer(new Tomato(nextHeight, nextRow, nextCol, day + 1));
					// 2-2-3-2-2. 방문처리 하기
					box[nextHeight][nextRow][nextCol] = 1;
					visited[nextHeight][nextRow][nextCol] = true;
					// 2-2-3-2-3. 남아 있는 토마토 1 감소시키기
					restTomatoCount--;
				}
			}
		}
		
		if (restTomatoCount != 0) {
			maxDay = -1;
		}
	}
	
	static int rowSize;
	static int colSize;
	static int heightSize;
	static int[][][] box;
	static List<Tomato> tomatoList;
	static int restTomatoCount;
	private static void inputTestCase() throws IOException {
		// 1-1. 상자의 가로 크기 M, 세로 크기 N, 개수 H 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		heightSize = Integer.parseInt(st.nextToken());
		
		box = new int[heightSize][rowSize][colSize];
		tomatoList = new ArrayList<>();
		
		// 1-2. 개수 H만큼 반복
		for (int heightIndex = 0; heightIndex < heightSize; heightIndex++) {
			// 1-2-1. 각 상자 별 정보 입력 받기
			for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
				st = new StringTokenizer(br.readLine().trim());
				int colIndex = 0;
				while(st.hasMoreTokens()) {
					box[heightIndex][rowIndex][colIndex] = Integer.parseInt(st.nextToken());
					// 1-2-1-1. 만약 1를 입력 받았다면,
					if (box[heightIndex][rowIndex][colIndex] == 1) {
						// 1-2-1-1-1. 토마토 리스트에 저장
						tomatoList.add(new Tomato(heightIndex, rowIndex, colIndex, 0));
					}	// 1-2-1-1. 만약 0을 입력 받았다면,
					else if (box[heightIndex][rowIndex][colIndex] == 0) {
						// 1-2-1-2-1. 앞으로 남은 개수에 1 증가시키기
						restTomatoCount++;
					}
					colIndex++;
				}
			}
		}
	}
}
