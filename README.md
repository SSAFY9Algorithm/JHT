# JHT 나만의 공간
## REadme  test
### '#'으로 강조가 가능
#### 4개
##### 5개
###### 6개
####### 7개

'tab'을 이용해서 코드 블록 만들기 가능 but 코드 블럭 하려면 앞뒤로 enter 해야됨


	package test;
	public class Main {
		public static void main(String[] args) {
			  System.out.println("이러면 코드 들어간데");
	  	}
  	}


여기가 코드 끝이라 위에는 블록처리 - 앞뒤에 enter 필수다. 안 그러면 원하는 결과 못얻음
  
그리고 '''와 '''사이에 코드 넣기

``` Java

package test;
public class Main {
	public static void main(String[] args) {
		System.out.println("이러면 코드 들어간데");
	}
}

```

위에는 테스트임 되냐?? 이거 왜 안되냐? '''가 아니라 ```였음

BlockQuote?
> 이러면
>> 된다는 데
>>> 네줄이면
>>>> 확인하고 인정함

1. 이거는
2. 노션이랑
3. 비슷한 것 같음 (리스트)

+ 이거도
  + tab으로
    + 구분함
      + 노션이랑 똑같음


* 이거도
  * tab으로
    * 구분함
      * 노션이랑 똑같음
      

- 이거도
  - tab으로
    - 구분함
      - 노션이랑 똑같음
  
 최대 3개 까지 구분가능
 
------------
이거는 그냥 ------------ 이거 작성하면 생김 개 신기함


![readme test](https://user-images.githubusercontent.com/72692422/217966233-8023928f-9b7f-486e-b4e5-b63434234d0e.png)

ㅇㅎ! issue에 새 이슈 만들고 이미지 넣고 기다리면 HTML 주소 나오는 데 그거 복사해서 넣으면됨.

링크 쓸일 없겠지?   

강조방법

**1번강조**   
__2번강조__   
*기울어지냐?*   
~~취소~~
# 끝!

매번 까먹음. 빨리 외워야지.   
git init   
git remote add origin https://github.com/~.git   
git pull origin 브런치이름   
git checkout -b 브런치이름   
git add .   
git commit -m "어쩌구"  
git push origin 브런치이름  
------------
git이름 & 이메일 변경 (타 컴퓨터에서)   
git config user.email   
git config --global user.email 변경이메일   
git config --global user.name 변경이름   
