package kakaoBlind2021;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RankSearch {
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		List<Info> infoList = new ArrayList<>();

		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i]);
			while (st.hasMoreTokens()) {
				infoList.add(new Info(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
						Integer.parseInt(st.nextToken())));
			}
		}

		for (int i = 0; i < query.length; i++) {
			String temp = query[i];
			StringTokenizer st = new StringTokenizer(temp.replace("and ", ""));
			String language = st.nextToken();
			String job = st.nextToken();
			String career = st.nextToken();
			String soulFood = st.nextToken();
			int score = Integer.parseInt(st.nextToken());

			int count = 0;

			for (Info infoData : infoList) {
				if (infoData.ansQuery(language, job, career, soulFood, score)) {
					count++;
				}
			}
			answer[i] = count;
		}

		return answer;
	}

	public class Info {
		String language;
		String job;
		String career;
		String soulFood;
		int score;

		@Override
		public String toString() {
			return "Info [language=" + language + ", job=" + job + ", career=" + career + ", soulFood=" + soulFood
					+ ", score=" + score + "]";
		}

		public Info(String language, String job, String career, String soulFood, int score) {
			super();
			this.language = language;
			this.job = job;
			this.career = career;
			this.soulFood = soulFood;
			this.score = score;
		}

		boolean ansQuery(String language, String job, String career, String soulFood, int score) {
			return ((language.equals("-") || (this.language.equals(language)))
					&& (job.equals("-") || this.job.equals(job)) && ((career.equals("-") || this.career.equals(career))
							&& (soulFood.equals("-") || this.soulFood.equals(soulFood)) && (this.score >= score)));
		}
	}

	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };

		RankSearch rankSearch = new RankSearch();
		rankSearch.solution(info, query);
	}
}
