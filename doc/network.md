## 1.Open System Interconnection Model(OSI)
![img.png](osi.png)
### 1.1 Data Link Layer 数据链路层
Packets are framed and sent to the next device.
### 1.2 Physical Layer 物理层
Frames are converted into bits and transmitted physically.
接入网络的设备必须安装网络适配器-网卡，保证数据包在网卡与网络层正确无误传输，定义MAC地址，即网卡接受和发送数据包地址

### 1.3 Network Layer 网络层
Segments are packaged into packets and routed
网络层引入了IP协议，制定了一套新地址，使得我们能够区分两台主机是否同属一个网络，这套地址就是网络地址，也就是所谓的IP地址。IP协议将这个32位的地址分为两部分，前面部分代表网络地址，后面部分表示该主机在局域网中的地址。如果两个IP地址在同一个子网内，则网络地址一定相同。为了判断IP地址中的网络地址，IP协议还引入了子网掩码，IP地址和子网掩码通过按位与运算后就可以得到网络地址。

### 1.4 Transport Layer 传输层
Data is broken into segments for reliable delivery.
链路层定义了主机的身份，即MAC地址，而网络层定义了IP地址，明确了主机所在的网段，有了这两个地址，数据包就从可以从一个主机发送到另一台主机。 但实
际上数据包是从一个主机的某个应用程序发出，然后由对方主机的应用程序接收。而每台电脑都有可能同时运行着很多个应用程序，所以当数据包被发送到主机上以
后，是无法确定哪个应用程序要接收这个包。
- TCP：Transmission Control Protocol - 传输控制协议，提供面向连接、可靠的传输服务，客户端和服务端在交换数据之前会建立一个可靠的TCP连接，
并且提供超时重发、丢弃重复数据、检验数据等功能
- UDP：User Data Protocol -  用户数据报协议， 提供无连接无状态的传输服务，对数据安全性无特殊要求，丢包不重发，网络负担非常重， 但对响应速度
要求高，例如ping就是使用udp


### 1.5 Session Layer 会话层
Connections are established and managed.

### 1.6 Presentation Layer 展示层
Data is formatted and encrypted.

### 1.7 Application Layer 应用层
Applications create the data.

理论上讲，有了以上三层协议的支持，数据已经可以从一个主机上的应用程序传输到另一台主机的应用程序了，但此时传过来的数据是字节流，不能很好的被程序识别，操作性差，
- 因此，应用层定义了各种各样的协议来规范数据格式，常见的有http、ftp,等，在请求Header中，分别定义了请求数据格式Accept和响应数据格式Content-Type，
- 有了这个规范以后，当对方接收到请求以后就知道该用什么格式来解析，然后对请求进行处理，最后按照请求方要求的格式将数据返回，请求端接收到响应后，就按照规定的格式进行解读


Traceroute, Ping, MTR, and PathPing are network tools or utilities that use the ICMP protocol to perform testing to diagnose issues on a network.
ICMP(Internet Control Message Protocol) is actually a used of IP protocol 

## 从浏览器输入一个网址，到页面展示中间经历的过程：

- 检查url是否合法
- 浏览器检查是否使用缓存以及是否缓存过该页面
- DNS解析，获取url对应的ip地址
- 浏览器（客户端）向服务器发起tcp连接，三次握手的过程
- 三次握手成功，浏览器发送http请求，请求数据包
- 服务器返回请求处理结果
- 浏览器解析服务端响应结果
- 请求资源，渲染界面，结束

### http header 字段
Connection: keep-alive / close, 会话完成之后是否仍然保持连接
Keep-Alive: timeout=5, max=1000，超时时间和最大请求数

https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Redirections

## DNS

Domain Name Resolution， 将域名指向网址IP，

**递归查询** ：首先检查本地hosts文件是否有网址的映射关系，然后本地DNS服务器->根域名DNS服务器->顶级域名DNS逐层查询。

域名解析服务，由低到高依次向域名服务器，发起解析请求，本地域名服务器 -> 根域名服务器 -> 顶级域名服务器，
直至拿到服务器IP地址，会将IP地址进行缓存一段时间TTL，方便下次使用.
DNS 记录类型
A - address， 域名指向的IP地址
C - cname，跳转的域名地址
简单的轮询算法，一次将服务器IP返回

## TCP
### 三次握手

是指在建立连接时，需要客户端与服务端发送三次包，来连接服务器指定端口，建立TCP连接

- 第一次握手：客户端发送syn包到服务器，syn包中包含随机生成的同步序列号seq=x，并进入syn_sent状态，等待服务器确认
- 第二次握手：服务端接受syn包，同时返回给客户端ack+syn包，其中ack序列号=x+1，syn序列号随机成为y，进入syn_recv状态
- 第三次握手：客户端确认ack序列号是否为x+1，无误则返回给服务器ack=y+1序列号，服务器检查正确则成功建立连接，进入established状态

**syn攻击：**

 第二次握手时，服务器处于半连接状态，可通过伪造大量不存在的IP地址，向server不断发送syn包，server需回复ack包，等待客户端确认，因此这些伪造的syn包会占用未连接队列，从而使得正常的连接请求因为队列满而被丢弃

- 如何检测 SYN 攻击？

  检测 SYN 攻击非常的方便，当你在服务器上看到大量的半连接状态时，特别是源IP地址是随机的，基本上可以断定这是一次SYN攻击。在 Linux/Unix 上可以使用系统自带的 netstats 命令来检测 SYN 攻击。

- 如何防御 SYN 攻击？

  SYN攻击不能完全被阻止，除非将TCP协议重新设计。我们所做的是尽可能的减轻SYN攻击的危害，常见的防御 SYN 攻击的方法有如下几种：

  - 缩短超时（SYN Timeout）时间
  - 增加最大半连接数
  - 过滤网关防护
  - SYN cookies技术

### 四次挥手

TCP连接在断开时需要发送四个包，是改进版的三次握手，不同的是客户端和服务端均可发起挥手动作，在编程中，任何一方的close动作即发起挥手

- 第一次挥手（FIN=1, seq=x）

  客户端想要关闭一个连接，发送一个FIN标志位置为1的包，发送完毕进入FIN_WAIT_1状态

- 第二次挥手（ACK=1， seq=x+1）

  服务端接收到这个FIN，发回一个ACK，确认序列号为收到的序号加一，发送完毕，服务端进入CLOSE_WAIT状态，客户端收到确认包进入FIN_WAIT_2状态

- 第三次挥手

  服务端准备关闭连接，向客户端发送结束连接请求（FIN=1），发送完毕服务端进入LAST_ACK状态，等待客户端最后的ACK

- 第四次挥手

客户端收到服务端的关闭请求，发送ACK 确认包，进入TIME_WAIT状态。同时服务端接收确认包，关闭连接，进入CLOSED状态。客户端等待了某个固定时间
（两个最大段生命周期，2MSL，2 Maximum Segment Lifetime， 2min）之后，没有收到服务器端的 ACK ，认为服务器端已经正常关闭连接，于是自己也
关闭连接，进入 `CLOSED` 状态

客户端：FIN_WAIT_1 -> FIN_WAIT_2 -> TIME_WAIT -> CLOSED
服务端：CLOSE_WAIT -> LAST_ACK -> CLOSED

https://lotabout.me/2019/TCP-connection-establish-and-termination/

等待2MSL原因：
- 防止滞后的报文包被后续建立的连接接受
### 连接状态

- CLOSED：初始状态，表示关闭着的或者未连接
- LISTEN：表示服务器的某个socket处于监听状态，可以接收客户端的连接
- SYN_SENT:客户端发送syn包后，进入SYN_SENT状态
- SYN_RCVD：服务端接收到客户端的syn包时，进入SYN_RCVD
- ESTABLISHED：表示TCP已经建立连接
- FIN_WAIT_1:IN_WAIT_1 和FIN_WAIT_2 两种状态的真正含义都是表示等待对方的FIN报文。而这两种状态的区别是：FIN_WAIT_1状态实际上是当SOCKET在ESTABLISHED状态时，它想主动关闭连接，向对方发送了FIN报文，此时该SOCKET进入到FIN_WAIT_1 状态。而当对方回应ACK报文后，则进入到FIN_WAIT_2 状态。当然在实际的正常情况下，无论对方处于任何种情况下，都应该马上回应ACK报文，所以FIN_WAIT_1 状态一般是比较难见到的，而FIN_WAIT_2 状态有时仍可以用netstat看到
- FIN_WAIT_2:FIN_WAIT_2 是没有超时的（不像TIME_WAIT 状态），这种状态下如果对方不关闭（不配合完成4次挥手过程），那这个 FIN_WAIT_2 状态将一直保持到系统重启，越来越多的FIN_WAIT_2 状态会导致内核crash
- TIME_WAIT:表示接收到了对方的FIN报文
- LAST_ACK ：当被动关闭的一方在发送FIN报文后，等待对方的ACK报文的时候，就处于LAST_ACK 状态。当收到对方的ACK报文后，也就可以进入到CLOSED 可用状态了。


### Websocket
websocket，使用长连接，双全工的通信机制，第一次TCP链路建立起来后，后续数据客户端和服务端均可主动发送，且不需要发送请求头，这个连接会一直持续存在到客户端或者服务端主动关闭连接。

### 长/短连接

- 短连接：每次http请求都会新建TCP连接，兼容性好，只支持http协议，方便管理

 * 长连接：只需要建立一次TCP连接，以后的http请求复用这个TCP连接，但是随着客户端连接数增加，会大量消耗服务端资源；适用场景：追求实时性高、两端频繁通信的场景，例如数据库连接、聊天,connection:keep-alive，keep-alive:timeout=20

### 长/短轮询

- 短轮询：向服务端重复发送请求，不管服务端是否有数据，都直接响应请求，实时性取决于请求的频率

  优点：实现简单

  缺点：客户端的频繁的请求，服务端的数据无变化，造成通信低效

 * 长轮询：向服务端发送请求，如是没有数据返回，会hold住客户端的请求一段时间，直到有数据更新或者连接超时，

   优点：减少了不必要的http请求次数，但是连接挂起会浪费服务器资源

   缺点：实现复杂

   Ajax的 连接与轮训无任何关联，长/短连接表示建立和保持连接的方式，长/短轮训表示服务端响应请求的方式

## HTTP

###  请求方法
GET、POST、PUT、DELETE...
GET VS POST
- GET请求一般是去获取数据，POST请求一般作为发送数据到后台时使用
- GET请求通过在url中进行传参，安全性和隐私性都比较差，而且长度也有限制；POST请求将数据放在request body中，安全性高，且容量大
- GET请求可被缓存，POST请求不会被缓存
- GET请求只产生一个TCP包，而POST请求会产生两个，在网络状况良好的情况下，发一次包与两次的时间差可忽略，但是两次TCP包在数据校验上的优点是比较显著的。

### Status code
2xx: 请求成功
200: 请求完成
201: 请求完成，资源已创建

3xx: 重定向
301: 用永久性重定向
302：临时重定向

4xx: 客户端错误
400：bad request，请求参数问题
401：unauthorized， 缺少鉴权信息
403：forbidden， 权限不足，禁止访问
404：not found，资源未找到
405：method not allowed. http请求方法不被允许

5xx、6xx:服务端错误
500：Internal Serve Error，服务器内部错误
501：Not Implemented, 服务未实现
502：bad geteway，网关服务执行请求时，收到无效响应
503：Service unavailable, 服务暂时不可用 
