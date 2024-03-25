Spring Boot
Todo App 만들기
Spring boot , SpringData JPA ,템플릿(HTML) , MySQL
Docker를 이용해서 MySQL을 설치

1.프로젝트를 생성합니다.
https://start.spring.io/ 을 통해서 프로젝트를 생성합니다.
서버 내장형은 jar (boot)
서버 배포형은 war

java / gradle / jar / java-17 / spring boot 3.2.4
Lombok
Spring Boot DevTools
Spring Web
Spring Data JPA
MySQL Driver 
Thymeleaf



id 'org.springframework.boot' version '3.2.4'
id 'io.spring.dependency-management' version '1.1.4'

dependency버전정보 없으면 최신버전을 사용하게 해주는 boot 설정입니다.

build.gradle을 열어서 프로젝트를 실행한다.

DB를 사용하기 위해 implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 를 추가했는데
이는 의존성을 가지게 된다,
DB프로그램이을 하기위해서는 커넥션풀이 필요한데
그 설정정보가 없어서 실행이 되지 않고 있다.

autoconfigure에 스프링부트가 처음 읽어들이는 설정들이 들어있다.

*SpringBoopApplication을 실행한다.
-TodoappApplication을 실행한다
-DataSource 객체를 생성하려면 url속성이 필요해
-Springboot 는 autoconfigure 라고 불리는 설정파일이 있는데
설정파일이 조건문이 있어서 dependency에 있는 설정에 맞는 조건을 올리려고하다가
DataSource 객체를 생성하려면 url 속성이 필요한데 없으면 오류나고 종료
그래서 DataSource에 대한 설정을 해줘야한다.
-

MySql 설치
1) Docker를 이용해서 설치하자
-Docker-Desktop을 설치
-docker desktop 을설치하면 docker-compose가 자동으로 설치된다.

-------

MySql 설치 & 실행
준비물은 1개
mkdir ~/devel/docker 폴더를 생성
mkdir ~/devel/docker/database 폴더를 생성
~/devel/docker/docker-compose.yml 파일 생성
docker-compose up -d 
-mysql이 손쉽게 설치된다.

version: "2"

services:
  vacation-db:
    image: mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: "root1234"
      MYSQL_DATABASE: "examplesdb"
      MYSQL_USER: "urstory"
      MYSQL_PASSWORD: "u1234"
    command:
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    volumes:
      - ./database/init/:/docker-entrypoint-initdb.d/
      - ./database/datadir/:/var/lib/mysql
    platform: linux/x86_64
    ports:
      - 3306:3306

위 설정을 해주고 cmd 로 docker-compose.yml 이 있는 곳으로 이동한다.
그 후 database 볼륨으로 쓸 폴더를 만들어주고
docker-compose up -d 로 docker-compose.yml을 실행시켜 준다.

MySQL 접속후 테스트

이제 DB를 설치했으니 URL 정보를 application properties 에 적어주면된다

application properties 보다는 yml 을 사용하자

spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/examplesdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul
    username: urstory
    password: u1234
    driver-class-name: com.mysql.cj.jdbc.Driver

이렇게 까지 하면 서버가 실행된다 .

이제 데이터를 DB에 저장하기 위해 JPA를 사용하자
JPA를 사용하기 위해선
jpa:
  hibernate:
    ddl-auto: update
  properties:
    hibernate:
      show_sql: true
      format_sql: true
  
설정을 application.yml에 추가해주어야 한다.

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    //data jpa 를 사용해서 crud를 간단하게 사용하자
    //JpaRepository<엔티티명, pk의 자료형> 을 선언한다.
}

간단하게 작성한 todo List 보여주기 & todo 저장하기


