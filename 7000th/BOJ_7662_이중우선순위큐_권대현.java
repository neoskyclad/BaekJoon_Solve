import java.io.*;
import java.util.*;

public class BOJ_7662_이중우선순위큐_권대현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCount = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCount; tc++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int operationCount = Integer.parseInt(br.readLine());

            for (int i = 0; i < operationCount; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (op == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.isEmpty()) continue;

                    int key = (num == 1) ? map.lastKey() : map.firstKey();
                    if (map.get(key) > 1) {
                        map.put(key, map.get(key) - 1);
                    } else {
                        map.remove(key);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
