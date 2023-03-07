# Hospital_Reserve
### JPA활용 - 간단한 구현
---
* 환자 정보
개별 환자는 ID를 부여받아 식별되며 기본적으로 나이, 성별 등의 신상 정보를 유지한다.

* 병원 정보
병원은 ID를 부여받아 식별되며 이름, 주소, 병원에 설치되어 있는 진료 과에 대한 정보를 제공한다.

* 진료과 정보
진료과는 ID를 부여받아 식별되며 전화번호와 소속 의사들을 파악할 수 있어야 한다.

* 의사 정보
의사는 ID를 부여받아 식별되며, 의사 정보는 이름을 포함하여 소속되어 있는 병원, 진료과, 해당 진료과에서의 경력(년수) 등이 포함된다. 
환자는 병원에서 제공하는 의사의 정보를 보고 어느 의사에게 진료를 받을 것인지 선택할 수 있다.

* 예약 정보
예약정보는 진료의 주체가 되는 환자, 의사, 진료 과, 병원, 시간 등으로 구성되며 환자와 의사는 예약 정보를 조회할 수 있다.

---
#### 도메인 모델과 테이블 설계
![KakaoTalk_20230302_140939231](https://user-images.githubusercontent.com/126778700/222419709-81f8bc69-0c77-451d-b624-2bf109938533.png)

#### ERD
![KakaoTalk_20230302_141002278](https://user-images.githubusercontent.com/126778700/222419716-d44a70f2-b262-4dc9-91a3-c1a47b9266ce.png)

---
### H2 DB에 저장
![제목 없음](https://user-images.githubusercontent.com/126778700/222427281-3fcec295-f832-44e0-b332-e393130415f0.jpg)

---
### 병원 예약 웹페이지
![image](https://user-images.githubusercontent.com/126778700/223484326-4d78cde3-8626-4531-9a05-152ff510b01d.png)

![image](https://user-images.githubusercontent.com/126778700/223484983-e2b85916-f745-4f64-ab85-8d52cea52d6b.png)
![image](https://user-images.githubusercontent.com/126778700/223485095-cdd3412b-e326-49ef-b5af-6b96b2afa062.png)
![image](https://user-images.githubusercontent.com/126778700/223485169-020d3b75-708f-48c8-9594-7e705b7380ee.png)
![image](https://user-images.githubusercontent.com/126778700/223485249-bc35a9d7-8fae-4e08-91f9-da4b0e9d90f7.png)
![image](https://user-images.githubusercontent.com/126778700/223485303-3100062f-7e7c-4436-b199-9e3d562a373c.png)
![image](https://user-images.githubusercontent.com/126778700/223485386-0ddc1ef7-1d5c-4f4f-b895-80b4b72689e7.png)
![image](https://user-images.githubusercontent.com/126778700/223485453-a8d1ea51-e97e-45d9-969c-75b369eae5ac.png)
![image](https://user-images.githubusercontent.com/126778700/223485772-8ef0b776-a5fa-4bfd-9938-eba025f79642.png)
![image](https://user-images.githubusercontent.com/126778700/223485938-59999888-5b01-4912-87d2-c6729c61edf6.png)
![image](https://user-images.githubusercontent.com/126778700/223486023-a3f9d560-49d0-4ce3-90de-6c4885279a2b.png)
![image](https://user-images.githubusercontent.com/126778700/223486101-9f36cfca-ab26-4941-8911-6842e46c1cba.png)
![image](https://user-images.githubusercontent.com/126778700/223486235-763a1d63-6b83-4945-9011-e6a72d10f775.png)
