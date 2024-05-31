## ps 
用于查看当前运行的进程
常见用法 ps -aux 和 ps -ef, 两者的输出结果差别不大，但是展示风格不同，aux是BSD风格，-ef是System V风格。这是次要的区别，一个影响使用的区别是aux会截断command列，

常用选项

```
-a 显示所有的进程，包含每个命令的完整路径
-x 显示所有系统程序，包括那些没有终端的程序
-u 显示使用者的名称和起始时间
```


## netstat
用于打印网络连接、路由表、连接的数据统计、伪装连接以及广播域成员
常见用法： netstat -tulp

命令选项
```
-a all 默认不显示LISTEN相关
-t 只列出TCP协议的连接
-u 只列出UDP协议的连接
-n number 不显示别名，能显示数字的都用数字显示(禁用反向域名解析，加快查询速度,默认会反查主机名)
-l listen 仅列出正在监听的服务状态
-p program 获取进程名、进程号以及用户 ID

-r route 路由信息
-e extention 扩展信息，比如uid等
-s statistic 按各个协议进行统计
-c cycle 每隔一段时间周期执行netstat命令
```

## top
命令用于实时查看服务器进程等信息。
```
-d   指定每两次屏幕信息刷新之间的时间间隔。
-p   通过指定监控进程ID来仅仅监控某个进程的状态。
```

## fuser
用于报告进程使用的文件和网络套接字。fuser命令列出了本地进程的进程号，那些本地进程使用file，参数指定的本地或远程文件。对于阻塞特别设备，此命令列出了使用该设备上任何文件的进程。
```
c：指示进程的工作目录。
e：指示该文件为进程的可执行文件(即进程由该文件拉起)。
f：指示该文件被进程打开，默认情况下f字符不显示。
F：指示该文件被进程打开进行写入，默认情况下F字符不显示。
r：指示该目录为进程的根目录。
m：指示进程使用该文件进行内存映射，抑或该文件为共享库文件，被进程映射进内存。
-a：显示命令行中指定的所有文件；
-k：杀死访问指定文件的所有进程；
-i：杀死进程前需要用户进行确认；
-l：列出所有已知信号名；
-m：指定一个被加载的文件系统或一个被加载的块设备；
-n：选择不同的名称空间；
-u：在每个进程后显示所属的用户名。
语法
```
## wget 
下载文件工具，支持http、ftp、https协议，非常稳定，支持代理服务器以及断点下传

## curl

利用url规则在命令行下工作的文件传输工具，它支持文件的上传与下载

```
-A/--user-agent <string>              设置用户代理发送给服务器
-b/--cookie <name=string/file>    cookie字符串或文件读取位置
-c/--cookie-jar <file>                    操作结束后把cookie写入到这个文件中
-C/--continue-at <offset>            断点续转
-D/--dump-header <file>              把header信息写入到该文件中
-e/--referer                                  来源网址
-f/--fail                                          连接失败时不显示http错误
-o/--output                                  把输出写到该文件中
-O/--remote-name                      把输出写到该文件中，保留远程文件的文件名
-r/--range <range>                      检索来自HTTP/1.1或FTP服务器字节范围
-s/--silent                                    静音模式。不输出任何东西
-T/--upload-file <file>                  上传文件
-u/--user <user[:password]>      设置服务器的用户和密码
-w/--write-out [format]                什么输出完成后
-x/--proxy <host[:port]>              在给定的端口上使用HTTP代理
-#/--progress-bar                        进度条显示当前的传送状态
```

## tar

为linux的文件或者目录创建档案，压缩、解压缩

```
-B 设置区块大小
-c 建立新的压缩文件
-d 记录文件的差别
-r 添加文件到已经压缩的文件
-u 添加改变了和现有的文件到已经存在的压缩文件
-x 从压缩的文件中提取文件
-t 显示压缩文件的内容
-z 支持gzip解压文件
-j 支持bzip2解压文件
-Z 支持compress解压文件
-v 显示操作过程
-l 文件系统边界设置
-k 保留原有文件不覆盖
-m 保留文件不被覆盖
-W 确认压缩文件的正确性
-f 指定文件
```

常见用法

```
解压： tar -zxvf file.tar[.gz]           压缩  tar -zcvf f.tar f.xx     
```



## kill

用来终止指定进程的运行（terminate a process）, 通常终止一个前台进程可以使用ctrl+c键，终止一个后台进程就必须用kill命令，先用ps/top/pstree/pidof等命令获取pid，然后只用kill命令查杀该pid对应的进程。在默认情况下，采用编号为15的TERM信号。

```
-l  信号，若果不加信号的编号参数，则使用“-l”参数会列出全部的信号名称
-a  当处理当前进程时，不限制命令名和进程号的对应关系
-p  指定kill 命令只打印相关进程的进程号，而不发送任何信号
-s  指定发送信号
-u  指定用户 
```

常见用法

```
> kill -2 等同ctrl+c 
> kill不带参数默认发送终止信号15
> kill -l
    1) SIGHUP	 2) SIGINT	 3) SIGQUIT	 4) SIGILL	 5) SIGTRAP
     6) SIGABRT	 7) SIGBUS	 8) SIGFPE	 9) SIGKILL	10) SIGUSR1
    11) SIGSEGV	12) SIGUSR2	13) SIGPIPE	14) SIGALRM	15) SIGTERM
    ....19)SIGSTOP
1 (SIGHUP): terminate a connection, or reload the configuration for daemons
2 (SIGINT): interrupt the session from the dialogue station
3 (SIGQUIT): terminate the session from the dialogue station
4 (SIGILL): illegal instruction was executed
5 (SIGTRAP): do a single instruction (trap)
6 (SIGABRT): abnormal termination
7 (SIGBUS): error on the system bus
8 (SIGFPE): floating point error
9 (SIGKILL): immmediately terminate the process
10 (SIGUSR1): user-defined signal
11 (SIGSEGV): segmentation fault due to illegal access of a memory segment
12 (SIGUSR2): user-defined signal
13 (SIGPIPE): writing into a pipe, and nobody is reading from it
14 (SIGALRM): the timer terminated (alarm)
15 (SIGTERM): terminate the process in a soft way
ps: The signals SIGSTOP and SIGKILL can not be caught, blocked, or ignored.
```



## chown 
将文件或者文件夹的拥有者改为指定的用户和群组
e.g: 将file.txt 的拥有者改为user，以及群体的使用者group
chown user:group file.txt

## apt
更新软件包列表索引、执行安装新软件包、升级现有软件包

```
apt update 从配置的源下载包信息，刷新数据库索引，update须在升级或者安装包前执行
apt upgrade 从配置源安装当前系统的所有包的升级，如果有依赖关系，则会安装新的包，不会删除现有包。如果包的升级需要删除现有包，则不会进行升级
apt full-upgrade 升级系统到最新版本，自动处理依赖关系
apt install 安装一个或多个包
apt remove 删除包，但会保留其配置
apt purge 删除包以及配置
apt autoremove 自动删除不需要的包
apt search 搜索应用程序
apt show 显示安装包细节，例如大小、来源、描述等
apt list 显示满足特定条件的包，--installed列出已安装的包
```



## cat

**命令格式** cat [参数选项] [文件]

用于显示文件内容，或者将几个文件连接起来显示，或者从标准输入读取内容并显示

**常用方式**

1、 一次性显示整个文件： cat filename

2、从键盘创建一个文件：cat > filename,

3、将几个文件合并为一个文件：cat file1 file2 > file3

**命令参数**

```
-A, --show-all           等价于 -vET
-b, --number-nonblank    对非空输出行编号
-e                       等价于 -vE
-E, --show-ends          在每行结束处显示 $
-n, --number     对输出的所有行编号,由1开始对所有输出的行数编号
-s, --squeeze-blank  有连续两行以上的空白行，就代换为一行的空白行 
-t                       与 -vT 等价
-T, --show-tabs          将跳格字符显示为 ^I
-u                       (被忽略)
-v, --show-nonprinting   使用 ^ 和 M- 引用，除了 LFD 和 TAB 之外
```



## head

**命令格式** head [参数选项] [文件]

head 用来显示档案的开头至标准输出中，默认head命令打印其相应文件的开头10行。

**命令参数**

```
-q 隐藏文件名
-v 显示文件名
-c<字节> 显示字节数
-n<行数> 显示的行数
```



## tail

**命令格式** tail [参数选项] [文件]

用于显示指定文件末尾内容，不指定文件时，作为输入信息进行处理。常用查看日志文件。

**命令参数**

```
-f 循环读取
-q 不显示处理信息
-v 显示详细的处理信息
-c<数目> 显示的字节数
-n<行数> 显示行数
--pid=PID 与-f合用,表示在进程ID,PID死掉之后结束. 
-q, --quiet, --silent 从不输出给出文件名的首部 
-s, --sleep-interval=S 与-f合用,表示在每次反复的间隔休眠S秒 
```



## more

**命令格式** more [参数选项] [文件]

类似cat命令，将整个文件内容从上至下显示在屏幕上，一页一页方便使用者阅读，而最基本的指令就是按空白键（space）就往下一页显示，按 b 键就会往回（back）一页显示，而且还有搜寻字串的功能

**命令参数**

```
+n      从笫n行开始显示
-n       定义屏幕大小为n行
+/pattern 在每个档案显示前搜寻该字串（pattern），然后从该字串前两行之后开始显示  
-c       从顶部清屏，然后显示
-d       提示“Press space to continue，’q’ to quit（按空格键继续，按q键退出）”，禁用响铃功能
-l        忽略Ctrl+l（换页）字符
-p       通过清除窗口而不是滚屏来对文件进行换页，与-c选项相似
-s       把连续的多个空行显示为一行
-u       把文件内容中的下画线去掉
```



## less

**命令格式** less[参数选项] [文件]

less与more类似，用于分页查看文件，但是相比more，less不但能向前移动查看，也能向后移动查看，更加灵活

**命令参数**

```
-b <缓冲区大小> 设置缓冲区的大小
-e  当文件显示结束后，自动离开
-f  强迫打开特殊文件，例如外围设备代号、目录和二进制文件
-g  只标志最后搜索的关键词
-i  忽略搜索时的大小写
-m  显示类似more命令的百分比
-N  显示每行的行号
-o <文件名> 将less 输出的内容在指定文件中保存起来
-Q  不使用警告音
-s  显示连续空行为一行
-S  行过长时间将超出部分舍弃
-x <数字> 将“tab”键显示为规定的数字空格
/字符串：向下搜索“字符串”的功能
?字符串：向上搜索“字符串”的功能
n：重复前一个搜索（与 / 或 ? 有关）
N：反向重复前一个搜索（与 / 或 ? 有关）
b  向后翻一页
d  向后翻半页
h  显示帮助界面
Q  退出less 命令
u  向前滚动半页
y  向前滚动一行
空格键 滚动一行
回车键 滚动一页
[pagedown]： 向下翻动一页
[pageup]：   向上翻动一页
```



## grep

**命令格式** grep [参数选项] [文件]

全称Global Regular Expression Print, 标识全局正则表达式版本，它的使用权限是所有用户，用于过滤/搜索指定的字符串，配合正则表达式使用，十分灵活

**命令参数**

```
-a   --text   #不要忽略二进制的数据。    
-A<显示行数>   --after-context=<显示行数>   #除了显示符合范本样式的那一列之外，并显示该行之后的内容。 
-b   --byte-offset   #在显示符合样式的那一行之前，标示出该行第一个字符的编号。   
-B<显示行数>   --before-context=<显示行数>   #除了显示符合样式的那一行之外，并显示该行之前的内容。   
-c    --count   #计算符合样式的列数。   
-C<显示行数>    --context=<显示行数>或-<显示行数>   #除了显示符合样式的那一行之外，并显示该行之前后的内容。   
-d <动作>      --directories=<动作>   #当指定要查找的是目录而非文件时，必须使用这项参数，否则grep指令将回报信息并停止动作。   
-e<范本样式>  --regexp=<范本样式>   #指定字符串做为查找文件内容的样式。   
-E      --extended-regexp   #将样式为延伸的普通表示法来使用。   
-f<规则文件>  --file=<规则文件>   #指定规则文件，其内容含有一个或多个规则样式，让grep查找符合规则条件的文件内容，格式为每行一个规则样式。   
-F   --fixed-regexp   #将样式视为固定字符串的列表。   
-G   --basic-regexp   #将样式视为普通的表示法来使用。   
-h   --no-filename   #在显示符合样式的那一行之前，不标示该行所属的文件名称。   
-H   --with-filename   #在显示符合样式的那一行之前，表示该行所属的文件名称。   
-i    --ignore-case   #忽略字符大小写的差别。   
-l    --file-with-matches   #列出文件内容符合指定的样式的文件名称。   
-L   --files-without-match   #列出文件内容不符合指定的样式的文件名称。   
-n   --line-number   #在显示符合样式的那一行之前，标示出该行的列数编号。   
-q   --quiet或--silent   #不显示任何信息。   
-r   --recursive   #此参数的效果和指定“-d recurse”参数相同。   
-s   --no-messages   #不显示错误信息。   
-v   --revert-match   #显示不包含匹配文本的所有行。   
-V   --version   #显示版本信息。   
-w   --word-regexp   #只显示全字符合的列。  
-x    --line-regexp   #只显示全列符合的列。   
-y   #此参数的效果和指定“-i”参数相同。
```

