package lottoExam;

import java.util.Scanner;

public class LottoSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1부터 45번의 공을 모두 가지는 배열 생성
		int[] lottoMachine = new int[45];

		// 선택된 로또 번호를 저장할 배열
		int[] lottoBalls = new int[6];

		// 보너스번호 저장
		int bonusNumber = 0;

		// 1 ~ 45개 공을 생성
		for (int i = 0; i < lottoMachine.length; i++) {
			lottoMachine[i] = i + 1;
		}

		int count = 0;

		while (count < 7) {
			// 공을 선택하는 위치(배열의 index)를 랜덤함수로 pick
			int index = (int) (Math.random() * 45);

			// 선택된 index의 값이 0이 아니면 아직 미사용 0이면 사용된 위치
			if (lottoMachine[index] != 0) {

				if (count < 6) {
					lottoBalls[count++] = lottoMachine[index];
				} else {
					bonusNumber = lottoMachine[index];
					count++;
				}

				lottoMachine[index] = 0; // 사용했다는 증명
			}
		}

		// system 로또 생성 검증
		for (int i = 0; i < lottoBalls.length; i++) {
			System.out.print(lottoBalls[i] + " ");
		}
		System.out.println("보너스 번호 : " + bonusNumber);

		// 사용자가 로또 번호 선택
		count = 0;
		Scanner sc = new Scanner(System.in);

		// 사용자가 뽑은 번호 저장하는 배열 생성
		int[] userLotto = new int[6];

		while (count < 6) {
			System.out.println((count + 1) + " 번째 로또 번호");
			int balls = sc.nextInt();

			// 중복체크
			for (int i = 0; i < count; i++) {
				if (userLotto[i] == balls) {
					System.out.println(balls + "는 이미 존재하는 번호입니다.");
					balls = 0; // 중복되었다는 의미
					break;
				}
			}

			// 0은 중복의 의미
			if (balls != 0) {
				userLotto[count++] = balls;
			}
		}

		sc.close();

		System.out.println("사용자 로또 : ");
		for (int i = 0; i < lottoBalls.length; i++) {
			System.out.print(userLotto[i] + " ");
		}

		System.out.println();

		int matchCount = 0;
		boolean isBonus = false;

		// 비교
		for (int i = 0; i < userLotto.length; i++) {
			for (int j = 0; j < lottoBalls.length; j++) {
				if (userLotto[i] == lottoBalls[j]) {
					matchCount++;
					break;
				}
			}

			// 보너스번호 맞는 것 찾기
			if (!isBonus) {
				if (userLotto[i] == bonusNumber) {
					isBonus = true;
				}
			}
		}

		// 등수 출력
		if (matchCount == 6) {
			System.out.println("1등!!!");
		} else if (matchCount == 5 && isBonus) {
			System.out.println("2등!!!");
		} else if (matchCount == 5) {
			System.out.println("3등!!!");
		} else if (matchCount == 4) {
			System.out.println("4등!");
		} else if (matchCount == 3) {
			System.out.println("5등");
		} else {
			System.out.println("낙첨");
		}

	}

}
