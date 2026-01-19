package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

/*
NO         NOT NULL NUMBER       
CNO                 NUMBER       
TYPE                NUMBER       
ID                  VARCHAR2(20) 
NAME       NOT NULL VARCHAR2(51) 
SEX                 VARCHAR2(6)  
MSG        NOT NULL CLOB         
REGDATE             DATE         
GROUP_ID            NUMBER       
GROUP_STEP          NUMBER       
GROUP_TAB           NUMBER       
ROOT                NUMBER       
DEPTH               NUMBER  

	Spring Boot
	1. Spring 기반 => 애플리케이션을 빠르고 쉽게 개발을 위한 프레임 워크
		= 설정 최소화
		= 빠른 실행 (내장 => 톰캣서버) => CI / CD
		= 독립적 실행
	---------------------------------------------------------
		SpringFramework					Spring-Boot
	---------------------------------------------------------
		XML / JAVA 설정					자동 설정
		외부 Tocat 사용					네장 tomcat
		시작 : 복잡						속도 빠르다
 	---------------------------------------------------------
 		src/main/java
 			|	=> 자바 클래스
 		src/main/resource
 			|	=> image / css / js
 		=> ThymeLeaf 중심 : 보조 : JSP
 		=> 전자 정부 프레임 워크 : Spring 5 => SpringFramework 기반
 		-----------------------------------------------------
 		SpringFramework = XML + Annotation
 		Spring = Boot : Annotation
 		1. 구동 : Kotlin
 			@ SpringBootApplication => main
 		2. 메모리 할당
 			@Component / @Repository / @Service
 			@Controller / @RestController
 		3. DI : 객체 주입
 			@Autowired => @RequiredArgsConstructor
 							=> 생성자 만들고 => 생성자에서 주입
 		4. 웹 구동
 			@GetMapping / @PostMapping / @RequestMapping
 			=>	값을 받는 경우
 				@RequestParam
 				@ModelAttribute
 				@RequestBody => JSON을 객체 단위로
 				@PathVariable
 				---------------- React
 								| MySQL / JPA / PathVariable
 								------------------------------
 								JWT 인증
 		5. MVC 구조
 			User ==== Controller ==== Service ===== Mapper ===== DB
 														| Repository
 		6. XML => yml : 들여쓰기
 				  ------------- Spring-boot 설정
 				  ------------- ci/cd script : deploy.yml
 				  Git Action / docker / docker - compose
 				  ---------------------------------------
 				  jenkins : 모니터링
 		----------------------------------------------------------
 		7. ORM
 			= MyBatis
 				=> CRUD / 동적 쿼리
 			= JPA
 				=> CRUD (SQL, 메소드 규칙)
 		-----------------------------------------------------------
 		Security
 			=> 세션 / 쿠키
 					  | JWT
 			: 인증 / 인가
 		WebSocket
 			=> SockJS / Stomp
 			
 		FileUpload / FileDown
 		------------------------------------------------------------
 		Test : JUnit => 단위 테스트
 		------------------------------------------------------------
 		기타 : Spring AI / Kafka / Task (Batch)
 		------------------------------------------------------------
 		Front-End
 			= JQuery : Ajax
 			= VueJS : Pinia
 			------------------
 			= React : tnaStack-Query
 			= NodeJS / TypeScript
 			-------------------------HTML / CSS / JavaScript
 			
 		Docker / Docker-compose
 		-----------------------
 		애플리케이션과 실행 환경을 저장 => 필요하게 실행하는 플랫폼
 		-------------------------- 이미지로 만들어서 저장
 								   ---------------- 컨테이너
	    1. Docker-Compose : 여러개의 컨테이너를 한번에 관리 도구
	   		명령어 docker-compose up
	   		명령어 docker-compose down
	   		=>실시간 처리
		   		Git ACtion
		   		----------
		   		확인 : JDK / 인증
		   		docker build
		   		docker push
		   		docker-compose pull/up
		   		---------------------- PORT 충돌 / 권한 문제 => chmod
		   	=> 파이프라인
		   		=> Jenkins
		   		=> MSA => 서버가 여러개 => 쿠버네티스 : AWS에서 유료
		   								------- 우분투에서 연습
		   								------- 각 서버마다 자동 IP 생성
 		
 */
@Data
public class CommonsCommentVO {

	private int no, cno, type, group_id, group_step, group_tab, root, depth;
	private String id, name, sex, msg, dbday;
	private Date regdate;
}
