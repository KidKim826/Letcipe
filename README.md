# LET'CIPE  

스마트한 데일리 장보기, EAT, PLAT, SHARE

### [렛시피 바로가기](https://j7a705.q.ssafy.io/)




🙋‍♂️팀장 오성준

👨‍👩‍👦‍👦팀원 김정아, 민상규, 박지수, 이준성, 정기연



### Developer

- 김정아
- 박지수

## FRONT-END🖥️


### Skills

<p display="inline-block">
<img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">
<img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white">
<img src="https://img.shields.io/badge/Vuetify-1867C0?style=for-the-badge&logo=Vuetify&logoColor=white">
<img src="https://img.shields.io/badge/Nuxt.js-00DC82?style=for-the-badge&logo=Nuxt.js&logoColor=white">
</p>


### Developer

- 민상규
- 박지수
- 오성준
- 이준성


## BACK-END💽

### Skills

<p display="inline-block">
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring%20boot&logoColor=white">
<img src="https://img.shields.io/badge/JSON_Web_Tokens-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white">
<img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-3479A1?style=for-the-badge&logo=Mysql&logoColor=white">
</p>



### Developer

- 정기연

## Server📤

### Skills


<p display="inline-block">
<img src="https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazon%20ec2&logoColor=white">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/docker&nbsp;compose-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">
</p>




### Developer

- 정기연
- 이준성

## Hadoop🐘

<p display="inline-block">
<img src="https://img.shields.io/badge/Hadoop-ECD53F?style=for-the-badge&logo=Apache Hadoop&logoColor=black">
</p>


### Creator 🎞

- 박지수




## 프로젝트 기획 및 설계📋

## 기획

`매일 배달음식 830만개, 매년 전 세계 약 13억t의 식량이 낭비. 최근 이슈가 되고 있는 환경문제와 기후변화. 그 주범 중 하나인 음식물쓰레기의 하루 배출량은 1만 5천t.`

`Let’cipe는 가정에서 나오는 음식물쓰레기에 집중해보았습니다.`

`좋아하는 레시피에 필요한 만큼만, 똑똑하고 안전한 장보기 도우미.`

`Let'cipe 서비스는 레시피를 담으면, 딱 알맞는 만큼의 식재료를 보여줍니다.`

`최소한의 식재료로 만드는 맛있는 음식, 효과적으로 장을 볼 수 있는 나만의 노하우.`

`간편하게 사람들과 공유하고, Let'cipe와 함께 일상 속 환경보호에 동참해보아요.`

1. `레시피 검색과 장보기 목록을 한방에, 편리한 장보기 서비스`
2. `기간, 상황에 맞는 최적의 식재료로,스마트한 장보기 서비스`
3. `나만의 노하우를 공유하고, 다른 사람의 노하우를 엿볼 수 있는, 함께하는 장보기 서비스`


## 설계

### Wireframe은 아래 링크를 참고해주세요.

- [Wireframe](https://miro.com/app/board/uXjVPZPaM24=/)

### 기능 상세 내용은 아래 링크를 참고해주세요.

- [기능상세](/docs/기능명세서.md)

### DB 상세 내용은 아래 링크를 참고해주세요.

- [ERD](https://www.erdcloud.com/d/f5NYtLuAcKGhGZPw2)

![ERD](/docs/ERD.png)

#

# 디자인🎨

## 1. 로고와 색상표

### 로고 정보는 아래를 참고해주세요.

![로고](/logo/렛시피_로고_b1.png)


### 렛시피의 색상입니다.

```
 letcipe: '#FFA500',
```

# 



## 2. 폰트와 아이콘

![이서윤체](/docs/이서윤체.png)

![렛시피_이서윤](/docs/렛시피_이서윤.png)


### 렛시피의 폰트입니다.

```
@font-face {
  font-family: 'LeeSeoyun';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2202-2@1.0/LeeSeoyun.woff')
    format('woff');
  font-weight: bold;
  font-style: normal;
}
* {
  font-family: 'LeeSeoyun';
}
```



# 

# 컨밴션🤙

- ### JIRA

  - STORY: 사용자 입장에서 작성하는 거

    - 사용자가 서비스 사용 흐름을 STORY로 적어 놓고 TASK를 이에 묶어놓기

  - TASK

    - 기능

    - 앞에 태그 붙이기

      ```markdown
      [태그]<space>제목
      ```

      - [TEAM] : 팀 회의
      - [BE] : 백엔드
      - [FE] : 프론트엔드
      - [DOCS]: 문서 작성

  ### GIT

  - 태그 종류
    - `FEAT` : 새로운 기능 추가
    - `FIX` : 버그 수정
    - `DOCS` : 문서 수정
    - `STYLE` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    - `REFACTOR` : 코드 리펙토링
    - `TEST` : 테스트 코드, 리펙토링 테스트 코드 추가
    - `CHORE` : 빌드 업무 수정, 패키지 매니저 수정
  - 형식

  ```markdown
  [태그]<space>제목<space>(Jira Issue 번호) 
  ex) [FEAT] Add login [S07P21A705-21](<https://jira.ssafy.com/browse/S07P21A705-21>)
  ```

  - [template.md](http://template.md)

  ```markdown
  ## ⛅️ 내용
  
  > 이 PR의 작업 요약
  
  여기에 작성
  
  ## 🎸특이사항
  
  > 리뷰시 참고할만한 내용, 주의깊게 봐줬으면 하는 내용
  
  여기에 작성
  
  ## 🎸테스트
  
  > 어떻게 테스트 할 수 있을까요?
  
  여기에 작성
  ```

  - Branch

  ```markdown
  master
  ㄴdev
  		ㄴbackend
  			ㄴbe_feat/login
  		ㄴfrontend
  			ㄴfe_feat/register
  ```

  - fe_feature/login
  - be_feature/

  ### **BE**

  - 변수명 : 

    camelCase

    - 카멜케이스

  - 클래스명 : 

    PascalCase

    - 카멜케이스 & 첫글자 대문자
    - DTO명
      - rest method 따라가기
      - 맨 마지막 url 따라가기
      - 겹치면 앞에 url까지 쓰기

  ### **FE**

  - 파일명: 소문자

  - CSS : 

    kebab-case

    - 케밥케이스

  ### **DB**

  - table: **snake_case**
  - column
    - **snake_case:** 소문자&스네이크케이스
    - **primary key → id (Long, auto increment)**
    - full length → **column 명**


