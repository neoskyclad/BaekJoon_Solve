import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_16928_뱀과사다리게임
 * 
 * 1. 테스트 케이스 입력 받기
 * 	1-1. 사다리의 수 N 입력 받기
 * 	1-2. 뱀의 수 M 입력 받기
 * 	1-3. 사다리와 뱀 입력 받기
 * 
 * 2. dp로 최소 이동 경우의 수 탐색하기
 * 	2-1. 주사위 굴리는 경우의 수
 * 	2-2. 
 * 3. 결과 출력하기
 */
public class BOJ_16928_뱀과사다리게임 {
	
	static class Move {
		int number;
		int depth;
		
		public Move(int number, int depth) {
			this.number = number;
			this.depth = depth;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 테스트 케이스 입력 받기
		inputTestCase();
		
		// 2. dp로 최소 이동 경우의 수 탐색하기
		searchMinMove();
		
		// 3. 결과 출력하기
		System.out.println(minMove);
	}
	
	static int minMove;
	private static void searchMinMove() {
		boolean[] visited = new boolean[101];
		Queue<Move> queue = new ArrayDeque<>();
		queue.offer(new Move(1, 0));
		
		while (!queue.isEmpty()) {
			Move move = queue.poll();
			
			visited[move.number] = true;
			
			if (move.number == 100) {
				minMove = move.depth;
				break;
			}
			
			for (int dice = move.number + 1; dice <= move.number + 6; dice++) {
				if (dice > 100)
					continue;
				if (visited[dice])
					continue;
				if (ladderMap.containsKey(dice)) {
					visited[ladderMap.get(dice)] = true;
					queue.offer(new Move(ladderMap.get(dice), move.depth + 1));
				} else if (snakeMap.containsKey(dice)) {
					visited[snakeMap.get(dice)] = true;
					queue.offer(new Move(snakeMap.get(dice), move.depth + 1));
				} else {
					visited[dice] = true;
					queue.offer(new Move(dice, move.depth + 1));
				}
			}
		}
	}
	
	static int ladderCount;
	static int snakeCount;
	static Map<Integer, Integer> ladderMap;
	static Map<Integer, Integer> snakeMap;
	private static void inputTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 1-1. 사다리의 수 N 입력 받기
		ladderCount = Integer.parseInt(st.nextToken());
		// 1-2. 뱀의 수 M 입력 받기
		snakeCount = Integer.parseInt(st.nextToken());
		// 1-3. 사다리와 뱀 입력 받기
		ladderMap = new HashMap<Integer, Integer>();
		snakeMap = new HashMap<Integer, Integer>();
		for (int ladderIndex = 0; ladderIndex < ladderCount; ladderIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			ladderMap.put(from, to);
		}
		for (int snakeIndex = 0; snakeIndex < snakeCount; snakeIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			snakeMap.put(from, to);
		}
	}
}
