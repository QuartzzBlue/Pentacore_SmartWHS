# SmartWarehouse

## 빅데이터·IoT시스템 스마트창고

### (feat. SpringFramework, Android, Hadoop, R)

<br>

### [Github에서 진행중인 프로젝트 소스보기](https://github.com/QuartzzBlue/Pentacore_SmartWHS)

<br><br>

### 목차

I. 제안요청서(RFP) 요약

1. 배경 및 목적
2. 제안요청사항

II. 프로젝트 제안서(기획서)

1. 프로젝트 개괄

    1.1 배경

    1.2 목적 및 기대효과

    1.3 범위 (`물류ERP(Web)`+ `창고 자동화` )

    1.4 기간 및 인원

2. 시스템 구성 및 사용 기술

    2.1 시스템 구성도

    2.2 시스템 사양 및 기능

3. 데이터 베이스 구조(ERD)

4. 관리자 화면 UI

    4.1 물류ERP(Web) 기능별 화면

    - 재고 관리 화면
    - 지게차 관리 화면
    - 창고 통계 및 솔루션 화면

    4.2 자동화 창고 관리자 화면(Android Tablet)

5. 시스템 컴포넌트별 구현 기능

    <br><br><br><br><br><br>

---

# I. 제안요청서(RFP) 요약

[제안요청서(RFP) 전문全文 보기](https://docs.google.com/document/d/1GOaVv40XYRnGVVJXLMN-b9uWsZkayIvrZkZ-iNXEcWc/edit?usp=sharing)

<br>

### 1. 배경 및 목적

1.1 배경

- 사업확장에 따른 물동량 증가 및 창고 운영시간 증대 필요
- 기존 시스템에서의 창고 관리 어려움에 따른 물류 자동화 시스템 구축
  

1.2 목적

- 물류 운영 전반에 대한 전산화

- 기존 창고(지게차, 파렛트랙)운영 프로세스에 대한 자동화
  **기존 자원 활용 최대화*

  <br>

### 2. 제안요청사항

2.1 물류 운영 전산화

- 재고 등록 / 조회 / 입·출고 발주의 전산화
- 지게차 운영내역(구매일, 점검일, 주행거리 등) 및 창고운영기록의 자동화 및 통계 제공
  

2.2 창고 자동화(지게차, 파렛트랙)

- 24시간 운영 자동화
- 현재 운영중인 1개 창고 외의 추가 확장 고려, 창고당 지게차는 최대 4대
  

2.3 기타

- 4차 산업혁명 대비 신기술 접목 방안(IoT, 빅데이터 등)

- 창고 운영 효율화를 위한 통계 및 솔루션 제공

  <br><br><br><br><br><br>

---

# II. 프로젝트 제안서(기획서) 요약

[프로젝트 제안서 전문全文 보기](https://docs.google.com/document/d/1_CEFdjSlOQF8mxkZrsovvvnaO-2XrPDlIXyPk9BYgE0/edit?usp=sharing)

<br>

### 1. 프로젝트 개괄

1.1 목적 및 기대효과

- 창고 무인 자동화를 통한 24시간 운영
- 창고 운영 시간 증가를 통한 물류 처리량 증가 및 인건비 감소
- 창고 운영 기록 통계를 바탕으로 효율성 제고
  

1.2 범위 (`물류ERP(Web)`+ `창고 자동화`)

①물류 ERP(Web)

②창고(파렛트랙, 지게차) 자동화


1.3 기간 및 인원

- 6주 (3.23~4.30)
- 5인

    i) A : 프로젝트 관리, Tablet Server, 지게차(ECU)

    ii) B : TCP/IP Server 구축, 지게차(Informatics)

    iii) C : Web App Server 구축 , DBMS

    iv) D : Web App Server 구축, DBMS

    v) E : Hadoop Server 구축, 빅데이터 분석(Hadoop & R)
    

### 2. 시스템 구성 및 사용 기술

2.1 시스템 구성도

![SmartWarehouse_%20IoT/system_structure_whiteBack.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fc9398d52-8f30-4d8e-b272-b3c62128b5d3%2Fsystem_structure_whiteBack.png?table=block&id=618292a0-7ba7-4089-85cc-272eeb4d9ac4&width=4670&cache=v2)



2.2 시스템 사양 및 기능

![SmartWarehouse_%20IoT/skill_specification_whiteBack.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5889950b-c52b-406d-84d0-facc148ea294%2Fskill_specification_whiteBack.png?table=block&id=a59592fd-d609-4c8b-80c8-c7339714c4e5&width=3860&cache=v2)

<br><br><br><br>

### 3. 데이터 베이스 구조(ERD)

![SmartWarehouse_%20IoT/ERD.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ff37987bf-5d1c-4d03-a76f-0a9ba9b95640%2FERD.png?table=block&id=8cc0d534-2e75-4688-b86f-2d11988eb3b0&width=3390&cache=v2)

<br><br><br><br><br>

### 4. 관리자 화면 UI

4.1 물류ERP(Web) 기능별 화면

- 재고 관리 화면

![SmartWarehouse_%20IoT/UI_2-1_highlighted.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F4c0b305d-5caf-4779-89e4-c90c0b6bb7d0%2FUI_2-1_highlighted.png?table=block&id=adbc0e24-9e53-48bb-a25c-98495778f051&width=3170&cache=v2)

<br>

- 지게차 관리 화면

![SmartWarehouse_%20IoT/UI_2-2_highlighted.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F6a821e95-cab1-41b0-a67b-e9ad58d68e96%2FUI_2-2_highlighted.png?table=block&id=0eef14ec-fea6-4788-b249-93b3e47243cc&width=3170&cache=v2)

<br>

- 창고 통계 및 솔루션 화면

![SmartWarehouse_%20IoT/UI_2-3_highlighted.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fe8713239-6334-44d4-a4eb-2e02320ae4c5%2FUI_2-3_highlighted.png?table=block&id=4200eb18-d30a-4dd1-857e-5f4bb5316540&width=3170&cache=v2)

<br>

4.2 자동화 창고 관리자 화면(Android Tablet)

![SmartWarehouse_%20IoT/Tablet_UI_.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fbce61b7f-5ef2-4091-9906-0c1db9f8a930%2FTablet_UI_.png?table=block&id=12ac828f-e147-4d1e-a584-8a4e820a7936&width=2570&cache=v2)

<br><br><br><br><br>

### 5. 시스템 컴포넌트별 구현 기능

5.1 TCP/IP Server

1. Web App Server와 Tablet Server는 Client로 TCP/IP Server에 접속
2. Web App Server(ERP)에서 지시한 Task를 실시간으로 Tab Server(창고 관리자)로 전달
   

5.2 Web App Server

1. 상품 재고 관리
    - 상품 등록 : 업체에서 취급하는 품목들을 데이터베이스에 등록한다.
    - 상품 조회 : 1 에서 등록한 상품 리스트를 데이터베이스에서 불러와 출력한다.
    - 상품 입고 및 출고 : 등록되어 있는 상품 품목들에 한해 입고 및 출고를 진행한다. 입출고 내역과 변경된 상품 재고는 데이터베이스에 업데이트된다.
    - 입/출고 내역 조회 : 3 에서 진행한 입 출고 내역을 데이터베이스에서 불러와 출력한다.
      
2. 지게차 관리
    - 지게차 사용량 그래프
    - 지게차 리스트 : 최초등록일, 마지막 점검일, 누적/기간별 주행거리
      
3. 통계 및 솔루션
    - 단위기간별, 카테고리별 입출고량 표시
    - 단위기간별 재고 재배치를 통한 창고 효율화 솔루션 제시
      

5.3 Tablet Server

1. 창고 도면 상 실시간 지게차 위치 확인
    - 각 지게차는 1초마다 자기 위치와 상태를 Tablet Server 로 전송한다.
      
2. 지게차 상태 화면
    - 지게차별 상태(Task 할당 여부, 배터리 상태, 작업/충전/대기 상태 ) 조회
    - 각 지게차 Informatics 와 ECU 의 데이터 조회 및 제어
      
3. Task Queue 화면
    - 스크롤 뷰로 Task 별 입고/상품/수량/배치/상태 조회
      
4. Console 화면
    - 지게차 상태, Task 할당, 데이터 송수신 내역 출력
      
5. Work Flow
    - TCP/IP 서버로부터 Task 수신 (상품 종류, 위치, 입/출고 여부 정보)
    - 대기중인 지게차가 있고, 대기중인 Task 가 있으면 Task 를 할당한다.
    - 지게차의 상태가 작업중에서 다른 상태로 바뀐 경우, Task 상태를 작업중 으로 전환 후 메인 서버로 전달
    - 주행거리, 상품 위치 등의 정보를 HTTP 통신을 통해 Web App Server 로 전달
      

5.4 지게차 Informatics

1. ECU 로부터 받은 데이터를 TCP/IP 통신으로 Tablet Server 에 전송
2. Tablet Server 에서 제어시 ID 에 해당하는 ECU에 제어 송신
   

5.5 지게차 ECU

1. 측정한 데이터를 CAN통신으로 Informatics 로 전달
2. Informatics 를 통해 Tablet Server 에서 받은 제어 수신
   

5.6 BigData

1. Hadoop을 이용하여 지게차에서 송신한 운행정보 로그 및 Web App Server 로그 분석
2. R을 이용한 데이터 시각화