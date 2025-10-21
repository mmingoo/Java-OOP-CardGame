package domain.player;

import config.PokerGameConfig;

import java.util.Scanner;

public class PlayerInputValidator {
    private Scanner scanner;
    PokerGameConfig pokerGameConfig = PokerGameConfig.getInstance();

    public PlayerInputValidator() {
        this.scanner = new Scanner(System.in);
    }
    
    // 닉네임 입력 및 유효성 검사
    public String getValidNickname(int playerNumber) {
        while(true) {
            System.out.print(playerNumber + "번째 플레이어의 닉네임을 20자 이내로 입력해주세요: ");
            String nickName = scanner.nextLine();
            
            if(nickName.length() <= 20) {
                return nickName;
            } else {
                System.out.println("닉네임의 길이가 20글자를 초과하였습니다. 다시 작성해주세요.");
                System.out.println();
            }
        }
    }

    // 참가자 수 유효성 검사
    public int validatePeopleCount() {
        return pokerGameConfig.getPeopleCnt() <= 0 ? pokerGameConfig.getDefaultPeopleCnt() : pokerGameConfig.getPeopleCnt(); // 기본값 4
    }

}