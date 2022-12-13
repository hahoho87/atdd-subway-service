   # Step4 - 요금 조회

## 요구사항

- 경로 조회 시 거리 기준 요금 정보 포함
- 노선별 추가 요금 정책 추가
- 연령별 할인 정책 추가

## 요구사항 설명

### 거리별 요금 정책

- 기본운임(10km) 이내: 기본운임 - 1,250 원
- 이용거리 초과 시 추과운임 부과
    - 10km초과 ~ 50km까지(5km마다 100원)
    - 50km초과 시 (8km마다 100원)

#### 수정 된 인수조건 (지하철 경로 검색)

```text
Feature: 지하철 경로 검색

  Scenario: 두 역의 최단 거리 경로를 조회
    Given 지하철역이 등록되어있음
    And 지하철 노선이 등록되어있음
    And 지하철 노선에 지하철역이 등록되어있음
    When 출발역에서 도착역까지의 최단 거리 경로 조회를 요청
    Then 최단 거리 경로를 응답
    And 총 거리도 함께 응답함
    And ** 지하철 이용 요금도 함께 응답함 **
```

### 노선별 추가 요금 정책
- 노선에 추가 요금 필드를 추가
- 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
  - ex) 900원 추가 요금이 있는 노선 8km 이용 시 1,250원 -> 2,150원
  - ex) 900원 추가 요금이 있는 노선 12km 이용 시 1,350원 -> 2,250원
- 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
  - ex) 0원, 500원, 900원의 추가 요금이 있는 노선들을 경유하여 8km 이용 시 1,250원 -> 2,150원

### 로그인 사용자의 경우 연령별 할인 정책을 적용
- 청소년 (13세 이상 ~ 19세 미만) - 350원을 공제한 금액의 20% 할인
- 어린이 (6세 이상 ~ 13세 미만) - 350원을 공제한 금액의 50% 할인

#### LoginMember 처리
- 로그인 시 LoginMember 활용하여 연령별 요금 할인 적용
- 비로그인 시 LoginMember `null` 이므로 별도 처리 필요

## 구현 기능 목록
### 지하철 경로 검색 인수테스트 추가
- [ ] 어린이 회원 지하철 경로 검색
- [ ] 청소년 회원 지하철 경로 검색
- [ ] 성인 회원 지하철 경로 검색
- [ ] 로그인 하지 않은 회원 지하철 경로 검색

### 요금 정책 추가
- [ ] 거리별 요금 정책 추가
  - [ ] 총 거리 계산
  - [ ] 거리별 요금 계산
- [ ] 노선별 요금 정책 추가
  - [ ] 노선별 추가 요금 필드 추가
  - [ ] 최대 추가 요금 노선 검색 기능 추가
- [ ] 나이별 요금 정책 추가
  - [ ] 어린이 요금 정책 추가
  - [ ] 청소년 요금 정책 추가
  - [ ] 성인 요금 정책 추가
  - [ ] 로그인 하지 않은 회원 처리