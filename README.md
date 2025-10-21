# 설계 고민 문서
<img width="2686" height="1291" alt="newOOP" src="https://github.com/user-attachments/assets/adaefb0d-2a30-4364-a398-89a103d97e8b" />

## 클래스, 인터페이스 종류
**1. 인터페이스 계층 (파란색):**
CardGameFactory, GameFactory, PlayerFactory
Game, Dealer, HandEvaluator, RewardDistributor
Manager, Printer, PlayerBuilder


**2.팩토리 구현체 (초록색)**
PokerCardFactory, FiveCardDrawFactory, DefaultPlayerFactory


**3. 게임 구현체 (빨간색):**
FiveCardDrawGame, FiveCardDrawDealer
FiveCardDrawHandEvaluator, StandardRewardDistributor


**4. 도메인 클래스 (보라색):**
Card (추상 클래스), PokerCard, Deck
Player (추상 클래스), PokerPlayer


**5. 관리/유틸 클래스 (주황색/노란색):**
GameManager
PokerCardPrinter
PlayerCreateDirector
PlayerInputValidator
PokerGameConfig (싱글톤)


**6. 빌더 클래스 (초록색):**
DefaultPlayerBuilder


**7. 메인 클래스 (회색):**
CardGameApp


## 확장성 있는 설계
카드의 종류는 다양하다. 할리갈리 카드, 포커카드, 화투 카드 등이 있고, 각 카드마다 할 수 있는 게임도 다양하다. 이를 바탕으로 확장성 있는 설계를 진행했다.
## 확장성 있는 설계
카드의 종류는 다양하다. 할리갈리 카드, 포커카드, 화투 카드 등이 있고, 각 카드마다 할 수 있는 게임도 다양하다. 이를 바탕으로 확장성 있는 설계를 진행했다.

## 인터페이스 설계 과정

#### 1. Flyweight 패턴 검토
처음에는 Person 클래스를 두고 플레이어와 딜러를 생성하는 Flyweight 패턴을 고려했다.
- 딜러와 플레이어 간 공통 부분이 많아야 효율적
- 그러나 카드 게임에 참여할 수 있는 플레이어와 딜러의 수는 한정적(4명)이어서 Flyweight 패턴을 적용해도 코드만 복잡해질 뿐 메모리 절약 효과는 미미했음
- 결론: Flyweight 패턴 대신 상속 구조 채택

#### 2. 추상화 수준 결정
주어진 과제는 포커게임에 대한 구현 과제이다. 새로운 카드 게임이 추가될 상황을 고려해야 하는지, 포커게임을 중심으로 개발하고 변동될 수 있는 세부사항에 집중하여 추상화를 해야 할지 고민했다.
- 결론: 확장 가능성을 열어두되, 현재는 포커 게임에 집중

#### 3. 변경 가능성에 따른 인터페이스 도출

**3-1. HandEvaluator 인터페이스**
- 요구사항: "딜러는 플레이어의 카드를 평가하고 결과를 점수로 반환한다"
- 단일 책임 원칙에 의해 딜러는 카드 분배만 담당
- 카드 평가는 별도의 HandEvaluator 인터페이스로 분리
- 점수 부여 방식이 변경될 가능성 존재

**3-2. RewardDistributor 인터페이스**
- 요구사항: "게임에서 승리한 플레이어는 상금 100원과 1승이 추가되고 나머지 플레이어는 상금 0원과 1패가 추가된다"
- 상금 부여 방식이 변경될 가능성 존재

**3-3. Printer 인터페이스**
- 요구사항: "최종 결과를 승리의 수가 많은 플레이어부터 내림차순으로 정렬하여 화면에 출력한다"
- 정렬 기준이나 출력 방식이 변경될 가능성 존재

#### 4. 팩토리 구조 설계

**4-1. 두 단계의 팩토리 구조**
- **CardGameFactory**: 카드 종류에 따른 팩토리 (포커카드, 할리갈리카드, 화투 등)
- **GameFactory**: 같은 카드로 할 수 있는 다양한 게임 팩토리 (파이브카드드로우, 텍사스홀덤 등)

**4-2. Card 인터페이스 도입 근거**
- 포커 카드만 사용한다는 보장이 없음
- 할리갈리 같은 다른 카드게임으로 변경 시 카드 종류도 변경될 것
- 따라서 Card를 추상 클래스로 설계

#### 5. Builder 패턴 적용

**5-1. Player 생성의 복잡성**
- 요구사항: "각 플레이어는 자신만의 고유한 nickname을 가지며 nickname의 길이는 20자를 넘지 못한다"
- Player 객체는 nickname, win, lose, money 등 여러 속성을 가짐
- 생성자 파라미터가 많아질 가능성 존재

**5-2. Builder 패턴 도입 이유**
- 최근 트렌드는 생성자 대신 Builder 패턴 사용
- 가독성 향상: `new Player("John", 0, 0, 10000)` vs `Player.builder().nickname("John").money(10000).build()`
- 선택적 파라미터 처리 용이
- 불변 객체 생성 가능

**5-3. Director 패턴 추가**
- PlayerCreateDirector를 통해 플레이어 생성 과정을 캡슐화
- 닉네임 유효성 검사 (20자 제한)
- 초기값 설정 (money: 10000, win: 0, lose: 0)

#### 6. Singleton 패턴 적용

**6-1. PokerGameConfig 설정 관리**
- 게임 전반의 설정값들을 중앙에서 관리
- 상금(100원), 초기 자금(10000원), 참가자 수(4명) 등

**6-2. Singleton 패턴 도입 이유**
- 설정값은 애플리케이션 전체에서 하나만 존재해야 함
- 여러 클래스에서 동일한 설정값 참조 필요
- 설정 변경 시 한 곳에서만 수정하면 전체 적용

**6-3. Thread-safe한 구현**
```java
private static class PokerGameConfigHolder {
    private static final PokerGameConfig Instance = new PokerGameConfig();
}
```
- Bill Pugh Solution 사용으로 lazy initialization과 thread safety 보장

#### 7. 최종 설계 결정

**디자인 패턴 조합**
1. **Abstract Factory Pattern**: 관련된 객체들의 생성을 캡슐화
2. **Strategy Pattern**: 평가, 보상 분배, 출력 등의 알고리즘 교체 가능
3. **Builder Pattern**: 복잡한 Player 객체의 생성 과정 단순화
4. **Singleton Pattern**: 게임 설정의 일관성 보장

이렇게 여러 디자인 패턴을 조합함으로써:
- 같은 카드(포커카드)로 다양한 게임(파이브카드드로우, 텍사스홀덤) 가능
- 다른 카드(할리갈리)로도 확장 가능
- 각 게임마다 고유한 딜러, 평가자, 보상 분배자를 가질 수 있음
- 플레이어 생성 과정이 유연하고 명확함
- 게임 설정이 중앙에서 일관되게 관리됨

# 결론
확장성과 유지보수성을 고려하여 추상 팩토리, 전략, 빌더, 싱글톤 패턴을 적절히 조합한 설계를 채택했다. 이를 통해 새로운 카드 종류나 게임 규칙이 추가되더라도 기존 코드 수정 없이 확장 가능한 구조를 만들었으며, 객체 생성과 설정 관리도 효율적으로 처리할 수 있게 되었다. 해당 코드에선 CardGameApp 클래스에서 다음 코드를 통해 포커카드를 사용한 파이브카드드로우 게임을 시작한다.
```java
        // 포커카드로 파이브카드드로우 게임
        GameManager manager = new GameManager(
                new PokerCardFactory(),      // 포커카드 사용
                new FiveCardDrawFactory()    // 파이브카드드로우 규칙
        );
```
