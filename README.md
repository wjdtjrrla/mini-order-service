# Mini Order Service

## 프로젝트 소개

Spring Boot와 JPA를 공부하기 위해 시작한 미니 프로젝트.

상품(Product)과 주문(Order)을 관리하는 간단한 주문 서비스를 만들어보면서 Spring Boot, JPA, 데이터베이스를 학습하는 것이 목표입니다.

---

## 개발 목표

* Spring Boot 프로젝트 구조 익히기
* JPA 사용법 익히기
* Product / Order 도메인 설계하기
* CRUD 구현하기
* 주문 생성 및 조회 기능 구현하기

---

## 기술 스택

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Gradle

---

## 현재 진행 상황

### 프로젝트 생성

* Spring Boot 프로젝트 생성
* H2 Database 연결
* application.yml 설정

### Entity 설계

#### Product

생각한 필드

* id
* name
* price
* createdAt
* visible

상품 삭제는 실제 삭제 대신 숨김 처리로 구현할 예정

---

#### Order

생각한 필드

* id
* product
* orderPrice
* orderedAt
* status

주문 상태는 Enum으로 관리

* ORDERED
* CANCELED

---

### 연관관계

현재는 단방향 연관관계 사용

```text
Order → Product
```

주문은 상품을 알아야 하지만,
상품은 주문 목록을 알 필요가 없다고 판단하여 단방향으로 설계

---

## 공부하면서 알게 된 점

### 왜 생성자를 사용할까?

```java
new Product("콜라", 2000)
```


처럼 객체 생성 시 필요한 값을 강제할 수 있다.

### 왜 상품명을 Order에 저장하지 않을까?

상품명이 변경되었을 때 주문 조회에도 변경된 이름이 보여야 하기 때문이다.

그래서 상품 이름 대신 Product 객체를 참조하도록 설계했다.

---

## 다음 목표

* Order Entity 완성
* JPA 테이블 생성 확인
* Repository 작성
* Product CRUD 구현
* Order 생성 구현