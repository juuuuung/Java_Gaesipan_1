package java_게시판_2;

import java.util.ArrayList;
import java.util.Scanner;

public class gaesi {

	public static void main(String[] args) {
		System.out.println("====program start====");

		int TitleIndex = 0;

		// 글의 정보를 담은 클래스를 ArrayList에 저장
		ArrayList<Article> Articles = new ArrayList<>();

		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.println("\n커맨드 입력");
			String command = s.nextLine();

			// exit 나가기
			if (command.equals("exit")) {
				break;
			} else if (command.equals("article list")) { // <==== 게시물 목록
				// 게시글의 유무 여부
				if (Articles.size() == 0) {
					System.out.println("게시글이 없음");
					continue;
				} else {
					// arrayList 안에 있는 클래스들의 변수들을 호출
					for (int i = 0; i < Articles.size(); i++) {
						System.out.printf("\n번호 : %d \n제목 : %s \n내용 : %s\n", Articles.get(i).TitleIndex,
								Articles.get(i).Title, Articles.get(i).Article);
					}
				}

			} else if (command.equals("article write")) { // <==== 새글 쓰기
				// ? 함수 호출시 마다 index 증가
				TitleIndex++;

				System.out.println("제목 입력");
				String Title = s.nextLine();

				System.out.println("내용 입력");
				String Article = s.nextLine();

				// ? 배열로 보내도 되지 않을까ㅇ
				Article NewArticle = new Article(TitleIndex, Title, Article);
				Articles.add(NewArticle);

				continue;
			} else if (command.equals("article remove")) { // <==== 글 삭제
				System.out.println("지울 글의 번호");
				int index = s.nextInt();

				// 없다면
				if (Articles.size() < index) {
					System.out.println("게시글 없음");
					continue;
				} else { // 게시글이 있다면
					Articles.remove(index - 1);

					// 게시글의 번호들을 앞으로 하나씩 땡김
					for (int i = 0; i < Articles.size(); i++) {
						Articles.get(i).TitleIndex = i + 1;
					}

					// 기존 글 TitleIndex를 하나씩 빼줘야 새글을 쓸때 정상 적으로 숫자가 증가
					TitleIndex--;

					continue;
				}
			}
		}
		s.close();
		System.out.println("끝");

	}

}

class Article {
	int TitleIndex;
	String Title;
	String Article;

	public Article(int TitleIndex, String Title, String Article) {
		this.TitleIndex = TitleIndex;
		this.Title = Title;
		this.Article = Article;
	}
}
