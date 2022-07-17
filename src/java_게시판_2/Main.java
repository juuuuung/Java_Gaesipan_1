package java_게시판_2;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// TODO: article list 해서 Articles의 수가 5개가 넘어가면 페이지 이동할 수 있도록 하기

		List<article> Articles = new ArrayList<>();

		int TextIndex = 0;
		System.out.println("=======프로그램 시작====");

		Scanner s = new Scanner(System.in);
		while (true) { // 명령어 반복
			System.out.printf("명령어 입력 = > ");
			String command = s.nextLine(); // Scanner class

			command = command.trim(); // 공백제거후 command에 저장

			if (command.length() == 0) { // 만약 명령어 입력 하지 않고 enter 쳤을때 continue
				continue;
			}

			if (command.equals("exit")) { // exit입력 하면 break ? equals 뭔지 모름
				break;
			} else if (command.equals("article list")) { // article list 출력
				if (Articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				} else if (Articles.size() > 5) {
					while (true) {
						int LastPage = Articles.size() / 5 + Articles.size() % 5;
						System.out.printf("보고싶은 페이지 (현재 페이지 %d) : ", LastPage);
						int page = s.nextInt();
						if (page > LastPage || page == 0000) {
							System.out.println("NO page.....");
							// 현재 while 문을 나감
							break;
						} else {
							if (page == LastPage) {
								for (int i = (page * 5) - 5; i < Articles.size(); i++) {
									article article = Articles.get(i);
									System.out.printf("번호 : %d\n제목 : %s\n", article.TextNum, article.TextTitle);
								}
							}
							for (int i = (page * 5) - 5; i < page * 5; i++) {
								article article = Articles.get(i);
								System.out.printf("번호 : %d\n제목 : %s\n", article.TextNum, article.TextTitle);
							}
						}

					}
				} else {

//					for(int i = Articles.size() / 5; i <= 0;  )

					for (int i = Articles.size() - 1; i >= 0; i--) {

						PrintEquals.PrintFormat("List", i + 1); // ==

						article article = Articles.get(i);
						System.out.printf("번호 : %d\n제목 : %s\n", article.TextNum, article.TextTitle);
					}

					PrintEquals.main(); // ==
				}
			} else if (command.equals("article write")) { // 게시글 작성
				TextIndex++;

				PrintEquals.PrintFormat("Write", TextIndex); // ==

				System.out.printf("제목 : ");
				String TextTitle = s.nextLine();

				System.out.printf("내용 : ");
				String TextArticle = s.nextLine();

				article NewArticle = new article(TextIndex, TextTitle, TextArticle, date.main());
				Articles.add(NewArticle);

				PrintEquals.main(); // ==

			} else if (command.startsWith("article detail")) {
				String[] SplitCommand = command.split(" ");
				int id = Integer.parseInt(SplitCommand[2]);

				if (id > Articles.size()) {
					System.out.println("게시글이 없음");
				} else {

					PrintEquals.PrintFormat("Detail", id); // ==

					article newarticle = Articles.get(id - 1);
					System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n날짜 : %s\n", newarticle.TextNum, newarticle.TextTitle,
							newarticle.TextArticle, newarticle.FormatedNow);

					PrintEquals.main(); // ==
				}

			} else if (command.startsWith("article delete")) {
				String[] SplitCommand = command.split(" ");
				int id = Integer.parseInt(SplitCommand[2]);

				// <==============방법 1==============>
//				int foundIndex = -1;
//
//				for (int i = 0; i < Articles.size(); i++) {
//					article newarticle = Articles.get(i);
//					if (newarticle.TextNum == id) {
//						foundIndex = i;
//						break;
//					}
//				}
//				if (foundIndex == -1) {
//					System.out.printf("%d 존재 X", id);
//				}
//				Articles.remove(foundIndex);
//				System.out.printf("%d 게시물 삭제 완료", id);

				// <==============방법 2===================>

				if (id > Articles.size()) {
					System.out.println("게시물이 없음");
				} else {
					Articles.remove(id - 1); // 앞으로 땡겨줌 글 번호를
					for (int i = 0; i < Articles.size(); i++) {
						Articles.get(i).TextNum = i + 1;
					}
					System.out.printf("\n%d번 게시글이 삭제되었습니다\n", id);
					TextIndex--;
				}

			} else if (command.startsWith("article correction")) {
				String[] SplitCommand = command.split(" ");
				int id = Integer.parseInt(SplitCommand[2]);

				if (id > Articles.size()) {
					System.out.println("게시물 없음");
					continue;
				}

				PrintEquals.PrintFormat("Correction", id); // ==

				System.out.printf("제목 : ");
				String TextTitle = s.nextLine();

				System.out.printf("내용 : ");
				String TextArticle = s.nextLine();

				// todo 저장하기

				Articles.get(id - 1).TextTitle = TextTitle;
				Articles.get(id - 1).TextArticle = TextArticle;
				Articles.get(id - 1).FormatedNow = date.main();

				PrintEquals.main(); // ==

				/*
				 * LocalDateTime now = LocalDateTime.now();
				 * 
				 * String FormatedNow =
				 * now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
				 */

			} else {
				System.out.println("명령어 없음");
			}
		}
		s.close();
		System.out.println("=====프로그램 끝=========");
	}

}

class article {
	int TextNum;
	String TextTitle;
	String TextArticle;
	String FormatedNow;

	public article(int TextNum, String TextTitle, String TextArticle, String FormatedNow) {
		this.TextNum = TextNum;
		this.TextTitle = TextTitle;
		this.TextArticle = TextArticle;
		this.FormatedNow = FormatedNow;
	}
}
