<div align=center>
	<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Airbnb&fontSize=80" />	
</div>

<h1>숙박 예약 플랫폼</h1>
<p> · 팀 구성 - 설계 및 리딩 1명, BE 1명, FE 1명, 개발 보조 1명</p>
<p> · 상세 역할 - 개발 및 프로젝트 리딩 / 설계 및 디자인 / BE & FE</p>

<br />
<h2>기능 설계 및 구현 (기능 전체 설계, 기능 구현은 본인이 한 것만 o)</h2>

  - o 1. Oauth2 - 로그인 : Google, KaKao, Naver의 로그인 API와 Spring Security를 활용하여 Oauth2 로그인 시스템을 구현. JWT(Jason Web Token) 토큰을 이용하여 사용자의 인증 정보를 안전하게 처리, localStorage에 로그인 Token정보를 저장하고 refresh Token를 서버측과 통신하여 로그인 유지.
  - o 2. Calender - 달력 기능 구현 : React의 DatePicker를 커스터마이징하여 사용자들이 날짜를 입력할 수 있는 달력 기능을 구현.
  - o 3. Search - 숙소 검색 기능 구현 : 키워드, 날짜, 인원을 기반으로 숙소를 검색, 숙소 유형과 별점에 따른 필터 기능을 통해 사용자가 원하는 숙소 리스트를 제공. 사용자가 검색 결과를 끊임없이 확인할 수 있도록 무한 스크롤 기능을 구현
  - o 4. Reservation - 숙소 예약 기능 구현 : 사용자들이 선택한 날짜와 인원에 따라 숙소 예약 및 방 배정을 처리하는 알고리즘을 설계, 예약 가능 여부를 판단하는 별도의 알고리즘도 개발하여, 실시간으로 사용자에게 예약 가능 상태를 알려주는 기능을 구현
  - o 5. Map - 지도 기능 구현 : KaKao Map API를 이용하여 사용자가 키워드를 검색하거나 주변 숙소를 찾을 수 있는 기능을 구현
  - x 6. Payment - 결제 기능 구현 : 아임포트를 이용해 카카오 페이를 통한 테스트 결제 기능을 구현
  - x 7. Revier & Star - 사용자들이 리뷰를 작성, 조회, 수정, 삭제(CRUD)할 수 있도록 하는 기능과 함께 숙소에 대한 별점을 부여하는 기능을 구현
  - x 8. Admin - 숙소 관리자 인증 및 체크인 및 체크아웃을 관리할 수 있도록 하는 기능을 구현
  - o 9. Recommendation : 사용자의 위치 정보를 활용하여 인기/지역별/특가/얼리 체크인 가능한 숙소를 추천하는 기능을 구현. React 슬라이더를 이용하여 사용자가 추천 숙소를 편리하게 확인할 수 있도록 제공

<br />
 <h2>프로젝트 커뮤니케이션 및 리소스 관리</h2>
 
 - Notion을 활용한 목표 설정 및 리소스 분배:
 	- 프로젝트의 기간이 짧고, 달성해야 하는 목표가 높은 상황에서, 일주일 및 하루 단위로 테스크를 나누어 목표를 수행할 수 있도록 계획을 설정하였습니다.
  	- 또한 팀원들의 역량을 고려하여 각자에게 최적화된 리소스를 분배하는 방식으로 효율적인 업무 분배를 실현하였습니다.
 - Notion, Google Drive, Figma를 활용한 회의록 기록 및 목표 공유:
 	- 프로젝트의 원활한 소통을 위해 매일 진행되는 테스크를 상세하게 기록하고 팀원들과 공유하였습니다.
  	- 이를 통해 팀적인 테스크와 개인적인 테스크의 진행 상황을 투명하게 관리하고, 팀원 간의 업무 이해도를 높이는 데 기여하였습니다."

<br />
<h2>프로젝트 세부 구현 및 성과</h2>

 - REST API Endpoint 설계 및 React Component 매칭:
	- 각 페이지에 3~5개의 컴포넌트를 적용하여, 각 컴포넌트를 스프링 컨트롤러의 메소드와 1:1로 매칭시키는 REST API 설계를 수행하였습니다.
  	- 이를 통해 프론트엔드와 백엔드 간의 통신 효율을 크게 향상시켰고, 페이지 로딩 시간을 단축하며 시스템의 반응성을 향상시켰습니다.
  	- 또한, 이런 설계 방식은 프론트엔드와 백엔드의 개발을 독립적으로 진행할 수 있게 하여, 팀 전체의 개발 효율성을 높이는 데 기여하였습니다.
 - 전역 상태 관리 최적화:
 	- 프로젝트 초기에는 useContext를 통해 전역 상태 관리를 진행하였습니다.
  	- 이를 통해 전체 상태를 관리하는데 필요한 useContext와 각 페이지 별로 독립된 상태를 관리하는 useContext를 적용하여, 효율적인 상태 관리를 실현하였습니다.
  	- 그러나 프로젝트의 규모가 커짐에 따라 상태 관리의 유지 보수성과 확장성이 필요하다고 판단하였습니다.
   	- 따라서, React-Redux의 FLUX 패턴을 독학하여 폴더 구조 및 설계를 리팩토링하였습니다. 이를 통해 상태 관리의 유지 보수성과 확장성을 크게 향상시키는 데 성공하였습니다
 - 프로젝트 설계 및 관리 :
 	- UserFlow, WorkFlow, Class/Sequence/ERD Diagram, Functional Specification, Storyboard, WireFrame, Test Scenario 등 다양한 방법론을 활용하여 프로젝트의 구성을 체계적이고 탄탄하게 설계하였습니다.
  	- 이러한 접근 방식은 프로젝트의 효율성을 극대화하고, 오류를 최소화하는 데 크게 기여하였습니다.
   	- 각 기능과 요소들이 서로 어떻게 상호작용하는 지를 명확하게 이해하고, 이를 바탕으로 사용자 중심의 서비스를 제공하였습니다.

<br />
<h2>클라우드 기반 서비스 배포 및 CI/CD 관리</h2>

- 3-tier 구조 구축 및 관리 :
	- AWS Ubuntu 환경에서 3-tier 구조를 구축하고 관리하였습니다.
 	- 프론트엔드로는 React를 사용하여 웹 서버에 배포, 백엔드는 Spring Boot를 Apache Tomcat WAS에 배포하였습니다. 데이터베이스는 AWS RDS를 활용하였습니다.
- 컨테이너화 및 이미지 관리 :
	- Docker를 사용하여 각 서비스를 이미지화하였습니다. 이를 통해 서비스의 안정성과 확장성을 보장하였습니다.
 	- 성공적인 빌드 이후 생성된 Docker 이미지는 Docker Hub에 푸시되었습니다.
- CI/CD 파이프라인 구축 :
	- Jenkins를 사용하여 CI/CD 파이프라인을 구축하였고, 소스 코드 변경 감지를 위해 SCM polling을 활용하였습니다.
 	- 이를 통해 자동으로 빌드와 테스트가 진행되었습니다.
- Kubernetes를 활용한 효율적 서비스 배포 및 관리 :
	- Docker 이미지화된 서비스 배포에 Kubernetes를 활용하였습니다.
 	- Kubernetes의 클러스터 관리 기능을 이용해 서비스의 확장성과 안정성을 높였고, 리소스 사용량을 최적화하였습니다.
- 실시간 배포 모니터링:
	- 배포 과정에서 Jenkins pipeline 단계별로 Slack 알림을 설정하여 배포 상황을 실시간으로 모니터링하였습니다.
 	- 이를 통해 배포 상황을 실시간으로 파악하고, 이에 신속하게 대응하였습니다.'

<br />
<h2>프로젝트 아키텍처 및 기술 스택</h2>

     · 프론트엔드 
         · 프레임워크 : React
         · 상태 관리 : React-Redux
         · 스타일 : TailwindCSS, Styled-Component
     · 백엔드 :
         · 클라우드 : AWS
         · 운영체제 : Ubuntu 22.04
         · 3-Tier 구조 :
             · 웹서버 : Apache HTTP Server
             · WAS : Apache Tomcat 10(Spring Boot 내장)
             · DBMS : RDS(MySQL)
     · Build : Spring Boot, MyBatis 
     · 인증 : OAuth2, JWT
     · 배포 : Docker & Kubernetes 
     · CI/CD : Jenkins

## 목차 

### 0. [Background](#Background)
### 1. [Topic](#Topic)
### 2. [Communication](#Communication)
### 3. [Function](#Function)
### 4. [Reference](#Reference)
### 5. [API](#API)
### 6. [WorkFlow](#WorkFlow)
### 7. [Database](#Database)
### 8. [Architecture](#Architecture)
### 9. [Design](#Design)
### 10. [Sequence_Diagram](#Sequence_Diagram)

# 0. Background
## Business Value

<div>감성 숙소의 6294억원에서 8577억원으로 Business 시장 규모 전년 대비 36.3% 상승</div>
<div>MZ세대가 늘어나는 추세인 만큼 감성숙소 시장 규모도 갈수록 커질 것</div>
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/c881c8c5-a136-4354-a857-a55e09cafc20" width="800" height="450">
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/19edb61e-df54-42cb-b1db-e1b8579ecc1d" width="800" height="450">
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/dd165d8f-87f4-483a-afd9-b20e0813c192" width="800" height="450">

# 1. Topic

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/9bf224f9-2738-4585-b997-41113967fda1" width="800" height="450">


# 2. Communication

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/ad2eafe6-dcdb-417d-92e7-d62bc9dd33e3" width="800" height="450">
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/858a2ac0-b291-45cc-a2a7-50975dbf83ba" width="800" height="450">

<h3>매일 회의록 기록</h3>
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/a35506b4-2be0-4fde-9c90-eb65a4af4a8f" width="800" height="450">


# 3. Function

# 4. Reference

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/d8d856bf-dd0b-4bbd-bad4-72fcf6aa8ed0" width="800" height="450">

# 5. API

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/ac6496b7-6364-46ef-8376-640b0ce4599a" width="800" height="450">

# 6. WorkFlow

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/896e1b9b-e5d9-458e-be51-b325e94ec710" width="800" height="450">

# 7. Database

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/896e1b9b-e5d9-458e-be51-b325e94ec710" width="800" height="450">

# 8. Architecture

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/773ff94b-cfda-4d9f-8e0c-c1a8514b5b82" width="800" height="450">
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/1cfeeabb-6311-4aeb-a151-b786f0496f91" width="800" height="450">
<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/b6d8e566-79cb-41e5-b442-2b06e4c17b0d" width="800" height="450">



# 9. Design

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/7ec9fa45-9b72-4ec3-a95f-707b2d603ca8" width="800" height="450">


# 10. Sequence_Diagram

<img src="https://github.com/jingom368/Team_Project_Spring/assets/67932739/896e1b9b-e5d9-458e-be51-b325e94ec710" width="800" height="450">
