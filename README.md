## [ 모두의 갤러리 ]

---

### **웹 애플리케이션 바로가기**

> **[http://ec2-13-125-255-221.ap-northeast-2.compute.amazonaws.com/](http://ec2-13-125-255-221.ap-northeast-2.compute.amazonaws.com/)**

### DB를 제외한 Docker Image Download
```
docker pull castlemj1513/cloud-image-server:latest
```

---

### 구현 방법


----
### 웹 애플리케이션 사용 방법

1. 해당 주소에 접속한다. http://ec2-13-125-255-221.ap-northeast-2.compute.amazonaws.com/
2.  화면에서 파일을 선택하여 업로드한다. 
<img src="https://kit-cloud-image.s3.ap-northeast-2.amazonaws.com/2025/5/31/3000123f4a72451cb5bb47bb5906aa7a.jpg">
<img src="https://kit-cloud-image.s3.ap-northeast-2.amazonaws.com/2025/5/31/1cb23cd3afaa422ab112942672eecd29.jpg">
3. 업로드된 사진을 확인한다. 
4. 우클릭으로 이미지 경로를 복사해서 필요한 곳에 사용한다.

----

## 로컬 환경에서 실행하기 (Windows 기준)

###  **사전 준비 사항**

* **Docker Desktop**이 설치되어 있어야함. ([Docker Desktop 다운로드](https://www.docker.com/products/docker-desktop/))
* AWS S3 Access Key 및 Secret Key, Bucket 이름, Region이 있어야함 

###  **실행 단계**

1. **️ 작업 폴더 생성**
    * 애플리케이션 관련 파일을 저장할 빈 루트 폴더를 생성
    * 예시: `E:\docker\compose-test`
2. **️ Windows PowerShell 실행**
    * 시작 메뉴에서 `PowerShell`을 검색하여 실행.
    * `cd` 명령어를 사용하여 1번 단계에서 생성한 폴더로 이동.
    * 예시
```powershell
cd E:\docker\compose-test
```

3. ** `docker-compose.yml` 파일 작성**
    * 생성한 폴더 내에 `docker-compose.yml` 파일을 만들고 아래 내용을 복붙.

```yaml
version: '3.8'

services:
  app:
    image: ${DOCKER_USERNAME}/cloud-image-server:latest
    container_name: cloud-image-server
    ports:
      - "8080:8080"
    volumes:
      - ./upload:/app/upload
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://cloud-image-mysql:3306/cloud_image?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul
      - SPRING_DATASOURCE_USERNAME=${MYSQL_USERNAME}
      - SPRING_DATASOURCE_PASSWORD=${MYSQL_ROOT_PASSWORD}
      - ACTIVE_PROFILE=prod
      - AWS_S3_ACCESS_KEY=${AWS_S3_ACCESS_KEY}
      - AWS_S3_SECRET_KEY=${AWS_S3_SECRET_KEY}
      - AWS_S3_REGION=${AWS_S3_REGION}      
      - AWS_S3_BUCKET=${AWS_S3_BUCKET}   
    depends_on:
      - mysql
    restart: unless-stopped

  mysql:
    image: mysql:8.0
    container_name: cloud-image-mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} 
      - MYSQL_DATABASE=cloud_image                 
    volumes:
      - mysql-data:/var/lib/mysql 
    command: --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    restart: unless-stopped

volumes:
  mysql-data:
```

4. ** `.env` 파일 작성**
    * `docker-compose.yml` 파일과 동일한 경로에 `.env` 파일을 생성하고 아래 내용을 환경에 맞게 수정하여 입력
    * **❗❗❗ 주의: 파일이 `.env.txt`가 아닌 `.env` 임을 확인 ❗❗❗**
    * AWS S3 키 발급 참고 : https://jaehee1007.tistory.com/161

```env
DOCKER_USERNAME=castlemj1513
MYSQL_ROOT_PASSWORD=0221
MYSQL_USERNAME=root
AWS_S3_ACCESS_KEY=[ 당신의 AWS S3 Access Key ]
AWS_S3_SECRET_KEY=[ 당신의 AWS S3 Secret Key ]
AWS_S3_REGION=[ 당신의 AWS S3 Region (예: ap-northeast-2)]
AWS_S3_BUCKET=[ 당신의 AWS S3 버킷 이름 ]
```

만약 파일이 `.env.txt`로 저장된 경우, PowerShell에서 아래 명령어를 실행하여 파일명을 변경


```powershell
Rename-Item .\.env.txt .\.env
```

5. **️ 애플리케이션 실행**
    * PowerShell에서 아래 명령어를 순서대로 입력.
    * **️ 주의: 호스트 PC의 `3306`번 포트와 `8080`번 포트가 다른 프로그램에서 사용 중일 경우 애플리케이션이 정상적으로 실행되지 않음.**

```powershell
docker-compose pull
docker-compose up -d
```

6. **실행 확인**
    * 웹 브라우저를 열고 주소창에 `http://localhost:8080` 을 입력하여 애플리케이션이 정상적으로 실행되는지 확인.
    * 확인 완료시 아래 명령어 실행
```````
docker-compose down --rmi all
```````

---

## GIT HUB CODE

> **[https://github.com/smj1513/cloudImageServer](https://github.com/smj1513/cloudImageServer)**

