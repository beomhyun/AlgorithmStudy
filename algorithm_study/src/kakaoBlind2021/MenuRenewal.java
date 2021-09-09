package kakaoBlind2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRenewal {
	private static List<String> combination;

	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		combination = new ArrayList<String>();

		for (int i = 0; i < orders.length; i++) {
			char[] order = orders[i].toCharArray();
			Arrays.sort(order);
			for (int j = 0; j < course.length; j++) {
				dfs(order, 0, course[j], "");
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String key : combination) {
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
		List<String> keySet = new ArrayList<String>(map.keySet());
		Collections.sort(keySet, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
		List<String> ansList = new ArrayList<String>();
		for (int i = 0; i < course.length; i++) {
			int max = 0;
			for (String key : keySet) {
				if (map.get(key) >= 2 && key.length() == course[i]) {
					if (map.get(key) >= max) {
						// keySet을 이미 sort 해뒀으므로
						ansList.add(key);
						max = map.get(key);
					}
				}
			}
		}
		Collections.sort(ansList);
		answer = new String[ansList.size()];
		ansList.toArray(answer);
		return answer;
	}

	public static void dfs(char[] order, int idx, int course, String str) {
		if (str.length() == course) {
			combination.add(str);
			return;
		}
		if (idx == order.length) {
			return;
		}
		dfs(order, idx + 1, course, str);
		dfs(order, idx + 1, course, str + order[idx]);
	}

	public static void main(String[] args) {
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };
		solution(orders, course);
	}
}
