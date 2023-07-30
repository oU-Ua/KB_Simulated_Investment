# KB_Simulated_Investment
주린이들을 위한 KB 모의 투자

KB 모의 투자는 20가지의 주식 종류로 구성되어 있으며, 매일 뉴스 헤드라인과 해당 종목의 정보를 제공합니다.

회원가입 없이 주식을 매수하고 매도하여 수익률을 비교할 수 있으며, 마지막에는 작년 기준 실제 주식 거래 이용자들의 평균 수익률과 유저의 수익률을 비교합니다.
<br/> <br/> <br/> 
## DB 설계
### **사용자 DB**

USER_SEQ : 유저 번호

USER_NAME : 유저 이름

BALANCE : 초기예산

RATIO : 수익률
| USER_SEQ(PK) | USER_NAME(NN) | BALANCE | RATIO |
| --- | --- | --- | --- |
| NUMBER | VARCHAR(10) | NUMBER | NUMBER |

<br/> <br/> <br/> 
### **주식정보 DB**

STOCK_SEQ : 종목 번호

STOCK_NAME : 종목 이름

PRICE : 종목가격

AMOUNT : 종목양…?

RATIO_AVG : 평균 수익률 → 마지막에 지난 정보에서 평균 수익률이랑 비교한다고 해서 넣었는데 여기에 넣어야할지 종목정보DB에 넣어야할지 모르겠어요

| STOCK_SEQ(PK) | STOCK_NAME(NN) | PRICE | AMOUNT | RATIO_AVG |
| --- | --- | --- | --- | --- | --- |
| NUMBER | VARCHAR(20) | NUMBER | NUMBER | NUMBER |

<br/> <br/> <br/> 
### 종목정보 DB

STOCK_SEQ : 종목 번호

RELATED_STOCK : 관련주

RATIO_PERIOD : 기간별 수익률

| STOCK_SEQ(PK,FK) | RELATED_STOCK(NN) | RATIO_PERIOD |
| --- | --- | --- |
| NUMBER | VARCHAR(20) | NUMBER |

<br/> <br/> <br/> 
### 매수 정보 DB

USER_SEQ : 유저 번호

STOCK_SEQ : 종목 번호

PRICE : 종목가격

AMOUT_BUY : 매수한 양

AMOUT_BUY : 총 매수한 금액

| USER_SEQ(PK,FK) | STOCK_SEQ(FK) | PRICE(FK) | AMOUNT_BUY | PRICE_BUY |
| --- | --- | --- | --- | --- |
| NUMBER | NUMBER | NUMBER | NUMBER | NUMBER |

<br/> <br/> <br/> 
### 매도 정보 DB

USER_SEQ : 유저 번호

STOCK_SEQ : 종목 번호

PRICE : 종목가격

AMOUT_SELL : 매도한 양

AMOUT_SELL: 총 매도한 금액

| USER_SEQ(PK,FK) | STOCK_SEQ(FK) | PRICE(FK) | AMOUNT_SELL | PRICE_SELL |
|  --- | --- | --- | --- | --- |
| NUMBER | NUMBER | NUMBER | NUMBER | NUMBER |
