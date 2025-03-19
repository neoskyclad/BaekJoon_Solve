import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * BOJ 2667. 단지번호붙이기 - 실버 1 BFS 그래프 탐색 문제
 * 
 * 1. N과 함께 지도의 모양 입력 받기 2. 이중 for문을 통해 지도가 1이 나올 때까지 탐색 2-1. 1이 나오면 해당 좌표의
 * 상하좌우를 탐색하여 추가 영역 탐색 2-2. 만약 더 이상 인접 영역이 없다면 해당 영역의 원소 개수를 반환 2-3. 영역(단지) 개수 +
 * 1 3. 단지 개수 출력
 */

public class Main {
	
	static int[] rowDir = { -1, 0, 1, 0 };
	static int[] colDir = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int mapSize = Integer.parseInt(reader.readLine());
		
		int[][] map = new int[mapSize][mapSize];
		
		for (int row = 0; row < mapSize; row++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), "", true);
			int column = 0;
			while(tokenizer.hasMoreTokens())
				map[row][column++] = Integer.parseInt(tokenizer.nextToken());
		}
		
		ArrayList<Integer> 
		
		for (int row = 0; row < mapSize; row++)
			for (int column = 0; column < mapSize; column++) {
				if(map[row][column] != 1)
					continue;
				areaCount++;
				
			}

	}

}
