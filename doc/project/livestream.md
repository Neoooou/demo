推流：RTMP(real time message protocol)
基于TCP长连接的实时消息传输协议

拉流
flv（flash video），adobe公司开发的流文件传输协议，用于adobe flash播放器中播放视频内容。有http-flv与socket-flv，
直播一般使用http-flv，基于http协议，80端口，避免防火墙拦截。

hls：
http live stream，基于http 80端口，避免防火墙拦截
数据切片，分为多段ts文件，以及一个m3u8索引文件
