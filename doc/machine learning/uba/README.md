# 验证码调研

## 滑动验证码：
	采集数据：
		滑块的响应时间，拖拽速度，时间，位置，轨迹，重试次数，IP，时间，URL，Status，User-Agent
		
	淘宝UA算法：
		记录参数：时间戳，浏览器，屏幕分辨率，随机数，鼠标移动，鼠标点击，键盘输入记录，将采集数据通过一个很复杂的算法加密成一个字符串
	
	特征工程：
		取时间戳的小时数作为一个特征
		取用户发生交易的时间与用户的登录时间戳的差值作为一个新的特征
		city,ip,device,log_form, type是否频繁变化作为新的特征
		对log_form,type进行one-hot编码
		
## 基于鼠标行为特征的用户身份认证 Reference:沈超，etc，基于鼠标行为特征的用户身份认证与监控(User authentication and monitoring based on mouse behavioral features)
	目的：通过监测用户日常的鼠标输入，获取用户使用鼠标的行为特征数据，分析用户的行为模式，以此为依据进行用户身份的认证
	特征提取：鼠标操作频率分布，静止事件占空比，操作屏幕范围分布，移动时间频率，移动方向频率，单击时间间隔，双击时间间隔，平均移动速度
	顺序前进贪婪特征选择：首先从所有特征选取达到最大预测概率的一维特征，然后以此特征为基础，计算所有二维可能组合的准确率，选出最佳值，依次直到准确率不再提高为止
	
		
## 计划：
前期准备：
	1）创立滑动验证块登录网站
	2）收集登录数据，分两类：真人登录数据和机器登录数据（收集所有可能构成辨识特性的数据，包括但不限于拖拽速度，平均速度，停留时间，位置，轨迹，重试次数）：
		a.人为登录去收集数据
		b.编写脚本自动拖拽滑动块并登录，收集数据，PS：ip和登录时间难以模拟
机学模型：
	3）特征工程，将收集到的数据向量化，并做归一化、标准化处理
	4）基于数据建模，可选模型：SVM，LR，随机森林，神经网络。训练指标：验证准确度，若模型结果不可取，返回第2）步，加宽登录数据采集范围
 
测试：
	5）交叉验证+用户测试+自动测试-使用脚本攻击该登录网站，判断模型实际效果
	6）基于后期测试结果进行模型优化
	
User-Agent：
	UserAgent是指浏览器,它的信息包括硬件平台、系统软件、应用软件和用户个人偏好，通过UA可以分析出浏览器名称、浏览器版本号、渲染引擎、操作系统。使用方法直接打开查看即可，也可以把其它浏览器的UA复制过来进行分析。
	
## cross origin/domain (跨域)：
	CORS(Cross-Origin Resources Sharing):a mechanism that allows resources on a web page to be requested from another domain outside the domain the resource originated from. In particular, Javascript's Ajax
	calls can use the XML HTTPRequest mechanism. Such "cross-domain" requests would be blocked by web browsers because of the same origin policy,To allow requests from all domains, a server can send the following resposne header:
  	1)Access-Controll-Allow-Origin:*
	2）jsonp: json with padding, takes advantage of the fact that browser dont enforce same origin policy on <script> tags
		$.getJSON( url+"?callback=?", function( data ){
		  console.log( data.title ); // Logs "jQuery Howto"
		});

		// OR using $.ajax()
		$.ajax({
		  type:     "GET",
		  url:      url,
		  dataType: "jsonp",
		  success: function(data){
			console.log(data);
		  }
		});
	
时序数据特征提取：tsfresh
	