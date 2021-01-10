## 인터넷 네트워크<br>
TCP (Transmission Control Protocol) : 전송 제어 프로토콜
 - TCP 3 way handshake (syn, syn/act, act), 데이터 전달 보증, 순서 보장, 신뢰할 수 있다.

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
