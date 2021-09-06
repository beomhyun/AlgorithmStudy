package programers.weekly;

public class week2 {
	public static String solution(int[][] scores) {
		String answer = "";

		for (int i = 0; i < scores.length; i++) {
			boolean maxDuplicate = false;
			boolean minDuplicate = false;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int sum = 0;
			for (int j = 0; j < scores.length; j++) {
				sum += scores[j][i];
				if (scores[j][i] == max) {
					maxDuplicate = true;
				} else {
					max = Math.max(max, scores[j][i]);
					maxDuplicate = false;
				}
				if (scores[j][i] == min) {
					minDuplicate = true;
				} else {
					min = Math.min(min, scores[j][i]);
					minDuplicate = false;
				}
			}
			int avg = 0;
			if ((!maxDuplicate && scores[i][i] == max) || (!minDuplicate && scores[i][i] == min)) {
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
	}
}
