package java_게시판_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		List<urticle> Articles = new ArrayList<>();

		int TextIndex = 0;
		System.out.println("=======프로그램 시작====");

		Scanner s = new Scanner(System.in);
		while (true) { // 명령어 반복
			System.out.printf("명령어 입력 : ");
			String command = s.nextLine(); // Scanner class

			command = command.trim(); // 공백제거후 command에 저장

			if (command.length() == 0) { // 만약 명령어 입력 하지 않고 enter 쳤을때 continue
				continue;
			}

			if (command.equals("exit")) { // exit입력 하면 break ? equals 뭔지 모름ss
				break;
			} else if (command.equals("article list")) { // article list 출력
				if(Articles.size() == 0){
					System.out.println("게시물이 없습니다.");
					continue;
				} else{
					for(int i=Articles.size()-1; i >= 0; i--){
						urticle article = Articles.get(i);
						System.out.printf("번호 : %d\n제목 : %s\n", article.TextNum, article.TextTitle);
					}
				}
			} else if (command.equals("article write")) { // 게시글 작성
				TextIndex++;

				System.out.printf("제목 : ");
				String TextTitle = s.nextLine();

				System.out.printf("내용 : ");
				String TextArticle = s.nextLine();

				urticle NewArticle = new urticle(TextIndex, TextTitle, TextArticle);
				Articles.add(NewArticle);
				

			} else if(command.startsWith("article detail")){

			}else {
				System.out.println("명령어 없음");
			}
		}
		s.close();
		System.out.println("=====프로그램 끝=========");
	}

}

class urticle{
	int TextNum;
	String TextTitle;
	String TextArticle;

	public urticle(int TextNum, String TextTitle, String TextArticle){
		this.TextNum = TextNum;
		this.TextTitle = TextTitle;
		this.TextArticle = TextArticle;
	}
}
