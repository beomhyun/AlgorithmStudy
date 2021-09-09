package kakaoBlind2021;

import java.util.ArrayList;
import java.util.Comparator;
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
		infoList.sort(new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {
				return o1.score - o2.score;
			}
		});

		for (int i = 0; i < query.length; i++) {
			String temp = query[i];
			StringTokenizer st = new StringTokenizer(temp.replace("and ", ""));
			String language = st.nextToken();
			String job = st.nextToken();
			String career = st.nextToken();
			String soulFood = st.nextToken();
			int score = Integer.parseInt(st.nextToken());

			int count = 0;
			int index = findClosest(infoList, score);
			for (int j = index; j < infoList.size(); j++) {
				if (infoList.get(j).ansQuery(language, job, career, soulFood, score)) {
					count++;
				}
			}
			answer[i] = count;
			System.out.println(count);
		}

		return answer;
	}

	static int findClosest(List<Info> infoList, int target) {
		int l = 0, h = infoList.size() - 1, diff = Integer.MAX_VALUE, mid = 0;
		while (l <= h) {
			mid = l + (h - l) / 2;
			if (Math.abs(target - infoList.get(mid).score) < diff) {
				diff = Math.abs(target - infoList.get(mid).score);
			}
			if (infoList.get(mid).score < target)
				l = mid + 1;
			else
				h = mid - 1;
		}
		return mid;
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
