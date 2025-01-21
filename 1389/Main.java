import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
	
	/**
	 * BOJ 1389. 케빈 베이컨의 6단계 법칙
	 * 
	 * 그래프 탐색 문제, BFS
	 * 모든 사람 중에 케빈 베이컨이 가장 작은 사람을 구해야 됨 > O(n)
	 * 자신을 제외하고, 다른 사람을 만날 때까지 계산
	 * BFS를 위한 Pair 클래스 정의
	 * 
	 * 1. 유저의 수, 관계의 수, 그래프 입력 받기
	 * 2. 탐색을 위한 Visited 배열
	 * 3. 다 탐색하면 나오기
	 * 4. for(n)으로 다 탐색
	 * 
	 * @throws IOException 
	 */
	
	static class Pair<First, Second> {
		private First first;
		private Second second;
		
		public Pair(First first, Second second) {
			this.first = first;
			this.second = second;
		}
		
		public First getFirst() {
			return first;
		}
		
		public Second getSecond() {
			return second;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// 유저의 수와 친구 관계의 수 입력 받기
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		
		int userCount = Integer.parseInt(tokenizer.nextToken());
		int relationCount = Integer.parseInt(tokenizer.nextToken());
		
		boolean[][] relations = new boolean[userCount + 1][userCount + 1];
		
		// 친구 관계 입력 받기 (A와 B 서로 친구가 되도록 저장)
		for (int relation = 0; relation < relationCount; relation++) {
			
			tokenizer = new StringTokenizer(reader.readLine());
			
			int userA = Integer.parseInt(tokenizer.nextToken());
			int userB = Integer.parseInt(tokenizer.nextToken());
			
			relations[userA][userB] = true;
			relations[userB][userA] = true;
		}
		
		int userKevin = 0;
		int minKevin = Integer.MAX_VALUE;
		
		// visit처리와 depth 저장 동시에
		int[] kevins = new int[userCount + 1];
		
		// 유저의 수만큼 케빈 베이컨 수 탐색 O(n)
		for (int user = 1; user <= userCount; user++) {
			int currentKevin = 0;
			
			// kevins 배열 초기화
			for (int index = 1; index <= userCount; index++)
				kevins[index] = userCount;
			
			Queue<Pair<Integer, Integer>> userQueue = new LinkedList<>();
			userQueue.offer(new Pair<Integer, Integer>(user, 0));
			
			// BFS로 그래프 탐색 시작
			while(!userQueue.isEmpty()) {
				int currentUser = userQueue.peek().getFirst();
				int currentDepth = userQueue.peek().getSecond();
				
				kevins[currentUser] = currentDepth;
				
				for (int searchUser = 1; searchUser <= userCount; searchUser++) {
					// 만약 이미 방문한 노드라면
					if(kevins[searchUser] != userCount)
						continue;
					// 만약 이어진 관계가 아니라면
					if(!relations[currentUser][searchUser])
						continue;
					
					kevins[searchUser] = currentDepth + 1;
					
					userQueue.offer(new Pair<Integer, Integer>(searchUser, currentDepth + 1));
				}
				
				userQueue.poll();
			}
			
			// 케빈 베이컨 수 합산 하기
			for (int index = 1; index <= userCount; index++)
				currentKevin += kevins[index];
			
			if (minKevin > currentKevin) {
				minKevin = currentKevin;
				userKevin = user;
			}
		}
		
		System.out.println(userKevin);

	}

}
