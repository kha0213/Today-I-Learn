## 인터넷 네트워크<br>
IP (Internet Protocol)
* 지정한 IP 주소(IP Address)에 데이터 전달
* 패킷(Packet)이라는 통신 단위로 데이터 전달   

TCP (Transmission Control Protocol) : 전송 제어 프로토콜
* UDP (User Datagram Protocol) 와는 다르게 순서 보장과 데이터 전달 보증이 된다. (UDP는 IP와 거의 같고 PORT와 체크섬 정도만 추가된 것이어서 커스터마이징 하기 편하다.)
* TCP 3 way handshake (syn, syn/act, act+data), 데이터 전달 보증, 순서 보장, 신뢰할 수 있다.   

DNS (Domain Name System) : ip는 변경될 수 있고 기억하기 어려워 도메인의 이름으로 해당 ip와 port로 찾아가게 하는 방법

## URI (Uniform Resource Identifier)<br>
URI는 로케이터(locator), 이름(name) 또는 둘 다 추가로 분류될 수 있다
- Uniform : 리소스 식별하는 통일된 방식
- Resource : 자원, URI로 식별할 수 있는 모든 것
- Identifier : 다른 항목과 구분하는데 필요한 정보
<pre>
Ex)
scheme://[userinfo@]host[:port][/path][?query][#fragment]
https://www.google.com:443/search?q=hello&hl=ko
</pre>
fragment는 화면 내의 id로 이동 할 수 있다.

## HTTP의 메시지   
GET : GET를 통해 해당 리소스를 조회합니다. 리소스를 조회하고 해당 도큐먼트에 대한 자세한 정보를 가져온다.   
POST : POST를 통해 해당 URI를 요청하면 리소스를 생성합니다. (CREATE)   
PUT : 해당 리소스 전체를 수정합니다. (파일 덮어쓰기 같은 효과)   
PATCH : DATA의 일부를 수정합니다.   
DELETE : 	DELETE를 통해 리소스를 삭제합니다.   
