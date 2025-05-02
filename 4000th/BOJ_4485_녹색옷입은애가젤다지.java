import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷입은애가젤다지 {
	static class Node implements Comparable<Node> {
		int row;
		int col;
		int cost;

		public Node(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[] DELTA_ROW = { -1, 0, 1, 0 };
	static int[] DELTA_COL = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testIndex = 1;
		while (true) {
			int mapSize = Integer.parseInt(br.readLine().trim());

			if (mapSize == 0) {
				break;
			}

			int map[][] = new int[mapSize][mapSize];
			for (int rowIndex = 0; rowIndex < mapSize; rowIndex++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int colIndex = 0; colIndex < mapSize; colIndex++) {
					map[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
				}
			}

			int minCost = Integer.MAX_VALUE;
			int[][] costMap = new int[mapSize][mapSize];
			for (int rowIndex = 0; rowIndex < mapSize; rowIndex++) {
				Arrays.fill(costMap[rowIndex], Integer.MAX_VALUE);
			}
			
			Queue<Node> queue = new PriorityQueue<>();
			queue.offer(new Node(0, 0, map[0][0]));
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.row == mapSize - 1 && node.col == mapSize - 1) {
					minCost = node.cost;
					break;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nextRow = node.row + DELTA_ROW[dir];
					int nextCol = node.col + DELTA_COL[dir];

					if (nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize)
						continue;
					
					if (node.cost + map[nextRow][nextCol] < costMap[nextRow][nextCol]) {
						costMap[nextRow][nextCol] = node.cost + map[nextRow][nextCol];
						queue.add(new Node(nextRow, nextCol, node.cost + map[nextRow][nextCol]));
					}
				}
			}

			sb.append("Problem ").append(testIndex).append(": ").append(minCost).append("\n");
			testIndex++;
		}

		System.out.println(sb);
	}
}
