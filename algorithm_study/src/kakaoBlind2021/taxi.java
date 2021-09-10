package kakaoBlind2021;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class taxi {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;

		int[] fromS = new int[n + 1];
		int[] fromA = new int[n + 1];
		int[] fromB = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			fromS[i] = Integer.MAX_VALUE;
			fromA[i] = Integer.MAX_VALUE;
			fromB[i] = Integer.MAX_VALUE;
		}
		fromS[s] = 0;
		fromA[a] = 0;
		fromB[b] = 0;

		Map<Integer, Integer>[] canGoInfo = new HashMap[n + 1];
		for (int i = 1; i < canGoInfo.length; i++) {
			canGoInfo[i] = new HashMap<Integer, Integer>();
		}
		for (int i = 0; i < fares.length; i++) {
			canGoInfo[fares[i][0]].put(fares[i][1], fares[i][2]);
			canGoInfo[fares[i][1]].put(fares[i][0], fares[i][2]);
		}

		Queue<Integer> que = new LinkedList<Integer>();

		que.add(s);
		while (!que.isEmpty()) {
			int from = que.poll();
			for (int to : canGoInfo[from].keySet()) {
				if (to != s && (fromS[to] > fromS[from] + canGoInfo[from].get(to))) {
					fromS[to] = fromS[from] + canGoInfo[from].get(to);
					que.add(to);
				}
			}
		}

		que.add(a);
		while (!que.isEmpty()) {
			int from = que.poll();
			for (int to : canGoInfo[from].keySet()) {
				if (to != a && (fromA[to] > fromA[from] + canGoInfo[from].get(to))) {
					fromA[to] = fromA[from] + canGoInfo[from].get(to);
					que.add(to);
				}
			}
		}
		que.add(b);
		while (!que.isEmpty()) {
			int from = que.poll();
			for (int to : canGoInfo[from].keySet()) {
				if (to != b && (fromB[to] > fromB[from] + canGoInfo[from].get(to))) {
					fromB[to] = fromB[from] + canGoInfo[from].get(to);
					que.add(to);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			int fee = fromS[i] + fromA[i] + fromB[i];
			if (fee == 0) {
				continue;
			}
			answer = answer < fee ? answer : fee;
		}

		return answer;
	}

	public static void main(String[] args) {
		taxi taxi = new taxi();
		int[][] fares = { { 2, 6, 6 }, { 6, 3, 7 }, { 4, 6, 7 }, { 6, 5, 11 }, { 2, 5, 12 }, { 5, 3, 20 }, { 2, 4, 8 },
				{ 4, 3, 9 } };

		System.out.println(taxi.solution(6, 4, 5, 6, fares));

	}
}
