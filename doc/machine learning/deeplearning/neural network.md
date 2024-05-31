## 激活函数（Activation Function）



## 损失函数（Loss Function）

用于评估模型的输出与真实标签的差距，差距越小损失函数计算出来的值越接近于零

**均方差损失函数（Mean Squared Error，MSE）**



![image-20230517173559284](/Users/zhangran/Library/Application Support/typora-user-images/image-20230517173559284.png)

**平均绝对误差函数（Mean Average Error， MAE）**





**交叉熵函数（Cross Entropy Loss）**

![image-20230517173647458](/Users/zhangran/Library/Application Support/typora-user-images/image-20230517173647458.png)



**合页损失(Hinge Loss)**

## 优化器

## 批量梯度下降 - Batch gradient descent

每次迭代都使用训练集的所有数据来计算损失函数对参数的更新梯度

每步迭代过程：

学习速率 ϵ, 初始参数 θ

1. 提取训练集中的所有内容{x1...xn},以及相关的输出yi
2. 计算损失和各层参数的更新梯度

$$
\begin{align}
&\hat g \leftarrow +\frac{1}{n}\nabla_\theta \sum_i L(f(x_i;\theta),y_i) \\
&\theta \leftarrow \theta-\epsilon\hat g
\end{align}
$$



**优点**：
对于凸函数，可以找到全局最优点，对于非凸函数，可以收敛到局部极小值

**缺点**：
由于每一步都使用所有数据，所以随着数据集的增大，运行速度会越来越慢

## 2. 随机梯度下降 - Stochastic gradient descent

随机梯度下降，随机抽取一批样本，以此为依据来更新参数，这里的SGD和小梯度下降（minibatch gradient descent）一个意思。

每步迭代过程：

学习速率 ϵ, 初始参数 θ

1. 从训练集中随机抽取一批容量为m的样本{x1,...xm}，以及相关的输出yi

2. 计算损失和各层参数的更新梯度
   $$
   \begin{align}
   & \hat g \leftarrow +\frac{1}{m}\nabla_\theta \sum_i L(f(x_i;\theta),y_i)\\
   & \theta \leftarrow \theta-\epsilon\hat g
   \end{align}
   $$

**优点**：

训练速度快，即使对于很大的数据集，也能够以较快的速度收敛

**缺点**：

由于是随机抽取，因此不可避免得到的梯度肯定有误差，含有较大的噪声，不能很好地反应真实的梯度，所以学习速率需要逐渐减小，否侧模型无法收敛，损失函数会在极小值附近不停地震荡甚至偏离

**学习速率调整**：

保证SGD收敛，ϵ的衰减就应该满足如下两个要求：
$$
\begin{align}
&\sum_{k=1}^\infty \epsilon_k = \infty \\
&\sum_{k=1}^\infty \epsilon_k^2 <\infty
\end{align}
$$




而在实际操作中，一般是进行线性衰减：
$$
\begin{align}
&\epsilon_k=(1-\alpha)\epsilon_0+\alpha\epsilon_\tau\\
&\alpha = \frac{k}{\tau}
\end{align}
$$


其中ϵ0是初始学习率, ϵτ是最后一次迭代的学习率. τ自然代表迭代次数.一般来说,ϵτ 设为ϵ0的1%比较合适.而τ一般设为让训练集中的每个数据都输入模型上百次比较合适.那么初始学习率ϵ0ϵ0怎么设置呢?书上说,你先用固定的学习速率迭代100次,找出效果最好的学习速率,然后ϵ0设为比它大一点就可以了



## 3. Momentum

上述SGD有个问题，就是每次迭代计算的梯度含有比较大的噪音，而momentum方法可以比较好地缓解这个问题，尤其是在面对小而连续地梯度但是含有很多噪声地时候，就可以加大学习速率

具体实现

参数：**学习速率ϵ, 初始参数 θ, 初始速率v, 动量衰减参数α** 

每步迭代过程：

1. 从训练集中随机抽取一批容量为m的样本{x1,...xm},以及相关的输出

2. 计算损失和梯度，更新WB以及速率v和动量衰减参数α
   $$
   \begin{align}
   & \hat g \leftarrow +\frac{1}{m}\nabla_\theta \sum_i L(f(x_i;\theta),y_i)\\
   & v\leftarrow\alpha v-\epsilon\hat g \\
   & \theta\leftarrow\theta+v
   \end{align}
   $$
   其中参数α表示每回合速率v的衰减程度，同时也可以推断得到的v的稳定值：
   $$
   \frac{\epsilon\lVert g\rVert}{1-\alpha}
   $$

**特点**

前后梯度一致时可以加速学习，前后梯度不一致时可以抑制震荡

## 4. Adagrad





## 5. RMSProp

RMSProp通过引入一个衰减系数，让r(梯度累积量)每回合都衰减一定比例，类似Momentum中的做法

具体实现

参数：**全局学习速率 ϵ, 初始参数 θ, 数值稳定量δ，衰减速率ρ** 

中间变量: 梯度累计量r(初始化为0) 

每步迭代过程

1. 从训练集中的随机抽取一批容量为m的样本{x1,…,xm},以及相关的输出yi 

2. 计算梯度和误差,更新r,再根据r和梯度计算参数更新量 
   $$
   \begin{align}
   & \hat g \leftarrow +\frac{1}{m}\nabla_\theta \sum_i L(f(x_i;\theta),y_i)\\
   & r\leftarrow \rho r+(1-\rho)\hat g\odot \hat g \\
   & \triangle \theta = -\frac{\epsilon}{\delta+\sqrt{r}}\odot \hat g \\
   & \theta\leftarrow\theta+\triangle \theta
   \end{align}
   $$

**优点**

解决了深度学习中过早结束的问题，适合处理非平稳目标，对RNN效果很好

**缺点**

又引入了新的超参，依然依赖于全局学习速率

## 6. Adam







