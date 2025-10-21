package builder;

import config.PokerGameConfig;
import domain.player.Player;
import domain.player.PlayerInputValidator;

import java.util.ArrayList;

public class PlayerCreateDirector {
    PlayerBuilder playerBuilder;
    ArrayList<Player> arrayList = new ArrayList<>();
    private PlayerInputValidator playerInputValidator = new PlayerInputValidator();

    // 싱글톤으로 설정파일 관리
    PokerGameConfig pokerGameConfig = PokerGameConfig.getInstance();


    public PlayerCreateDirector(PlayerBuilder playerBuilder){
        this.playerBuilder = playerBuilder;
    }


    // 참가자 목록 반환
    public ArrayList<Player> getPlayerList() {
        int peopleCnt = playerInputValidator.validatePeopleCount();
        createPlayers(peopleCnt);
        return this.arrayList;
    }



    // 1. 참가자 닉네임 유효성 검사
    private void createPlayers(int peopleCnt) {
        for(int i = 1; i <= peopleCnt; i++) {
            String nickName = playerInputValidator.getValidNickname(i);
            registerPlayer(nickName);
        }
    }

    //2. 참가자 생성 후 참가자 등록
    private void registerPlayer(String nickName) {
        // 참가자 생성
        Player player = createPlayer(nickName);

        // 참가자 목록에 추가
        arrayList.add(player);
        System.out.println(nickName + "이 입장하였습니다.");
        System.out.println();
    }

    // 참가자 생성
    private Player createPlayer(String nickname){
        return playerBuilder.nickname(nickname)
                .win(0)
                .lose(0)
                .money(10000)
                .getPlayer();
    }



//    }
//    // 플레이어 생성 및 참가자 목록 반환
//    public ArrayList<Player> getPlayerList(int peopleCnt){
//        int i = 1;
//        Scanner sc = new Scanner(System.in);
//
//        // 참가자수 유효성 검사
//        if(peopleCnt <= 0)
//            this.peopleCnt = peopleCnt;
//        else
//            this.peopleCnt = 4;
//
//
//        // 참가자 목록 생성
//        while (i < peopleCnt){
//            System.out.print(i+"번째 플레이어의 닉네임을 20자 이내로 입력해주세요: ");
//            String nickName = sc.nextLine();
//
//            //닉네임이 20글자 이하면
//            if(nickName.length() <= 20){
//                // 참가자 목록에 추가
//                Player player = createPlayer(nickName);
//                arrayList.add(player);
//
//                i++;
//                System.out.println(nickName+"이 입장하였습니다.");
//
//            }else {
//                System.out.println("닉네임의 길이가 20글자를 초과하였습니다. 다시 작성해주세요.");
//            }
//            System.out.println();
//
//
//        }
//        return this.arrayList;
//    }

    }
