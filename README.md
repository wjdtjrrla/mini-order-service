# Mini Order Service

## 프로젝트 소개

Spring Boot와 JPA를 공부하기 위해 시작한 미니 프로젝트.

상품(Product)과 주문(Order)을 관리하는 간단한 주문 서비스를 만들어보면서 Spring Boot, JPA, 데이터베이스를 학습하는 것이 목표.

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
* updatedAt
* visible

상품 삭제는 실제 삭제 대신 숨김 처리로 구현

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

## 1일차 진행 내용

### H2 Database 선택

처음에는 MySQL과 H2 중 어떤 DB를 사용할까?

현재 목표는 DB 운영이 아니라 Spring Boot와 JPA 학습이기 때문에 H2 In-Memory Database를 선택.

```yml
spring:
  datasource:
    url: jdbc:h2:mem:miniorder
```

---

### application.yml 사용

application.properties 대신 application.yml을 사용하기로 결정.

이유

* 계층 구조를 표현하기 쉽다.
* 설정이 많아질수록 가독성이 좋다.
* Spring 프로젝트에서 자주 사용된다.

---

### Product 설계

고민했던 내용

* 상품 삭제를 실제 삭제로 할 것인가?
* 숨김 처리로 할 것인가?

선택

```text
Soft Delete 방식
```

상품 데이터는 유지하고 visible 값만 변경하는 방식으로 구현.

---

### 객체 생성 방식

```java
new Product("콜라", 2000)
```

생성자를 사용하여 객체 생성 시 필요한 값을 강제하도록 설계.

---

## 2일차 진행 내용

### Product CRUD 시작

Product 도메인을 기준으로 CRUD 구현.

---

### 상품 등록(Create)

구현 API

```http
POST /products
```

학습 내용

* Controller → Service → Repository 구조 이해
* JpaRepository 사용
* ProductCreateRequest DTO 생성

---

### 상품 단건 조회(Read)

구현 API

```http
GET /products/{id}
```

학습 내용

* ProductResponse DTO 생성
* Entity를 직접 반환하지 않고 DTO로 변환

---

### 상품 수정(Update)

구현 API

```http
PUT /products/{id}
```

학습 내용

* ProductUpdateRequest DTO 생성
* Product 엔티티 내부 update() 메서드 구현
* updatedAt 필드 추가

알게 된 점

```text
Dirty Checking
```

JPA는 트랜잭션 안에서 변경된 엔티티를 감지하여 자동으로 UPDATE 쿼리를 실행.

---

### 상품 삭제(Delete)

구현 API

```http
DELETE /products/{id}
```

학습 내용

* 실제 삭제 대신 hide() 메서드 사용
* visible 값을 false로 변경

---

## 공부하면서 알게 된 점

### 왜 생성자를 사용할까?

```java
new Product("콜라", 2000)
```

처럼 객체 생성 시 필요한 값을 강제할 수 있다.

### 왜 상품명을 Order에 저장하지 않았나?

상품명이 변경되었을 때 주문 조회에도 변경된 이름이 보여야 하기 때문에.

그래서 상품 이름 대신 Product 객체를 참조하도록 설계.

### 왜 DTO를 사용할까?

* Entity를 직접 노출하지 않기 위해
* 필요한 데이터만 주고받기 위해
* 유지보수를 쉽게 하기 위해

### Dirty Checking이란?

JPA가 트랜잭션 안에서 변경된 엔티티를 감지하여 자동으로 UPDATE SQL을 실행하는 기능.

### Soft Delete란?

실제 삭제하지 않고 상태만 변경하는 방식.

현재 프로젝트에서는

```java
visible = false
```

를 통해 구현.

---

## 다음 목표

* Product 목록 조회 구현
* visible 조건 조회 구현
* Order Repository 작성
* 주문 생성 기능 구현
* 주문 조회 기능 구현
* Product와 Order 연관관계 활용하기
