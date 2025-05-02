import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11286_절댓값힙 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		Queue<Integer> posQueue = new PriorityQueue<Integer>();
		Queue<Integer> negQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
		int x = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < x; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			if (num == 0) {
				int num1 = posQueue.isEmpty() ? Integer.MAX_VALUE : Math.abs(posQueue.peek());
				int num2 = negQueue.isEmpty() ? Integer.MAX_VALUE : Math.abs(negQueue.peek());
				
				if (num1 > num2) {
					sb.append(negQueue.poll());
				} else if (num1 < num2) {
					sb.append(posQueue.poll());
				} else {
					if (posQueue.isEmpty() && negQueue.isEmpty()) {
						sb.append(0);
					} else {
						if (!negQueue.isEmpty()) {
							sb.append(negQueue.poll());
						} else {
							sb.append(posQueue.poll());
						}
					}
				}
				sb.append("\n");
			} else {
				if (num > 0) {
					posQueue.offer(num);
				} else {
					negQueue.offer(num);
				}
			}
		}

		System.out.println(sb);
	}

}
