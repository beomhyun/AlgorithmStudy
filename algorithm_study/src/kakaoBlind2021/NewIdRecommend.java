package kakaoBlind2021;

public class NewIdRecommend {
	public static String solution(String new_id) {
		StringBuilder answer = new StringBuilder(new_id.toLowerCase());
		// 1단계

		for (int i = 0; i < answer.length(); i++) {
			char temp = answer.charAt(i);
			if ((temp >= 'a' && temp <= 'z') || (temp >= '0' && temp <= '9') || temp == '-' || temp == '_'
					|| temp == '.') {

			} else {
				answer.deleteCharAt(i--);
			}
			// 2단계
		}
		for (int i = 0; i < answer.length(); i++) {
			char temp = answer.charAt(i);
			if (temp == '.' && i != answer.length() - 1) {
				for (int j = i + 1; j < answer.length(); j++) {
					if (answer.charAt(j) == '.') {
						answer.deleteCharAt(j--);
					} else {
						break;
					}
				}
			}
			// 3단계
		}
		while (answer.length() > 0 && answer.charAt(0) == '.') {
			answer.deleteCharAt(0);
		}

		while (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
			answer.deleteCharAt(answer.length() - 1);
		}
		// 4단계

		if (answer.length() == 0) {
			answer.append('a');
		}
		// 5단계

		if (answer.length() > 15) {
			answer.setLength(15);
		}

		while (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
			answer.deleteCharAt(answer.length() - 1);
		}

		// 6단계

		while (answer.length() < 3) {
			answer.append(answer.charAt(answer.length() - 1));
		}
		// 7단계

		return answer.toString();
	}

	public static void main(String[] args) {
		System.out.println(solution("b=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.x"));
	}
}
