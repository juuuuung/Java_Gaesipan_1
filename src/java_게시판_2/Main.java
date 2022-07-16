package java_게시판_2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String[] datas = new String[100];
		int lastIndex = 0; // datas의 마지막 인덱스를 확인 하기 위한 변수
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
				if (lastIndex != 0) {
					for (int i = 0; i < lastIndex; i++) { // datas에 저장된것들 출력
						System.out.printf("%d번 %s\n", i + 1, datas[i]);
					}
				} else
					continue;
			} else if (command.equals("article write")) { // 게시글 작성

				System.out.printf("제목 입력 : ");
				datas[lastIndex] = s.nextLine();
				System.out.printf("내용 입력 : ");
				String article = s.nextLine();
				System.out.println((lastIndex + 1) + "번 글 제목 : " + datas[lastIndex] + " 내용 : " + article);
				lastIndex++;

			} else {
				System.out.println("명령어 없음");
			}
		}
		s.close();
		System.out.println("=====프로그램 끝=========");
	}

}
