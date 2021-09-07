package programers.weekly;

public class week2 {
	public static String solution(int[][] scores) {
		String answer = "";

		for (int i = 0; i < scores.length; i++) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int sum = 0;
			int temp = scores[i][i];
			boolean check = false;

			for (int j = 0; j < scores.length; j++) {
				max = Math.max(max, scores[j][i]);
				min = Math.min(min, scores[j][i]);
				sum += scores[j][i];
				if (i != j) {
					if (scores[j][i] == temp) {
						check = true;
					}
				}
			}
			int avg = 0;
			if (!check && (max == temp || min == temp)) {
				sum -= scores[i][i];
				avg = sum / (scores.length - 1);
			} else {
				avg = sum / scores.length;
			}
			if (avg >= 90) {
				answer += "A";
			} else if (avg >= 80) {
				answer += "B";
			} else if (avg >= 70) {
				answer += "C";
			} else if (avg >= 50) {
				answer += "D";
			} else {
				answer += "F";
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] scores = { { 50, 90 }, { 50, 87 } };
		solution(scores);
	}
}
