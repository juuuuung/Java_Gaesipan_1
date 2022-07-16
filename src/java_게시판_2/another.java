package java_게시판_2;

import java.util.ArrayList;
import java.util.Scanner;

public class another {

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
//					for (int i = 0; i < Articles.size(); i++) {
//						System.out.printf("\n번호 : %d \n제목 : %s \n내용 : %s\n", Articles.get(i).TitleIndex,
//								Articles.get(i).Title, Articles.get(i).Article);
//					}

					// 최신글부터 호출
					for (int i = Articles.size() - 1; i >= 0; i--) {

						// 새클래스를 만들고 그곳에 저장 (일회성 인듯)
						Article article = Articles.get(i);

						System.out.printf("%d. %s\n", article.TitleIndex, article.Title);
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
			} else if (command.startsWith("article detail")) { // startswith = 이걸로 시작 한다면..

				String[] SplitsCommand = command.split(" ");
				int id = Integer.parseInt(SplitsCommand[2]);
				boolean found = false;

				// 찾은 게시물 클래스 저장 용도
				Article foundArticle = null;

				// ?=======만약 id가 Articles.size() 보다 크다면 실행 되지 않도록 수정========
				for (int i = 0; i < Articles.size(); i++) {
					Article article = Articles.get(i);
					if (article.TitleIndex == id) {
						found = true;
						foundArticle = article;
						break;
					}
				}
				if (found == false) {
					System.out.printf("%d번 게시물은 존재하지 않습니다. \n", id);
					continue;
				} else {
					System.out.printf("번호 : %d\n제목 : %s\n내용 : %s", foundArticle.TitleIndex, foundArticle.Title,
							foundArticle.Article);
				}
			} else {
				System.out.println("존재하지 않는 명령어");
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
