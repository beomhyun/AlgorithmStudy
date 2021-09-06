package programers.weekly;

public class week1 {
	public long solution(int price, int money, int count) {
		long answer = 0;

		for (int i = 1; i <= count; i++) {
			answer += i;
		}

		answer *= price;

		if (answer <= money) {
			return 0;
		} else {
			return answer - money;
		}
	}
}
