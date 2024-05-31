## 神经网络训练中的问题

Internal covariate shift，网络参数变化，后面的网络参数需要调整以适应这种变化，从而要求降低学习率，谨慎的参数初始化，来训练模型，不然训练过程陷入非线性饱和，并很难收敛

**Internal covariate shift**： defined as the change in the distribution of network activations due to the change in network parameters during training. 由于训练过程中参数改变带来的激活值分布改变。在神经网络中，前一层的activation传至后面的网络层作为输入，当前网络层参数改变时，带来的是activation分布的改变，这将给深度学习过程带来影响，尤其是网络层数深的模型，例如残差神经网络。



## 介绍

BatchNormalization对每次迭代训练时，网络层输出做归一化处理，解决Internal covariate shift问题，加快模型收敛，减少训练时长，同时允许我们调高学习率，使用不同的方法来初始化超参数

当使用SGD优化器时，神经网络训练过程中，每层神经网络的输出受到之前神经网络参数的影响，当网络层数很深时，一点点小的变化会给超参数带来较大的改变。由于网络层输入分布改变时，参数得调整来适应新的分布，所以当输入分布保持不变时，会给模型训练带来很大的便利。另外，当训练和测试数据的输入分布相同时，这会保证训练出来的模型在测试数据上有一个好的表现，这在子网络层中同样是适用的。

例如，如下F1、F2是对于学习参数Θ1和Θ2而言的转换函数，ℓ为损失。

​	loss = F2(F1(u, Θ1), Θ2)

F1， F2为任意转换函数，参数θ1和θ2为最小化损失函数的学习参数，学习θ2的过程可看作x=F1（u, θ1）在F2函数（x, θ2）.若x的分布保持固定，那么F2函数则不必调整θ2参数来适应x的不同分布。

**作用**

- 加速收敛

- 控制过拟合，可以少用或不用Dropout和正则

- 降低网络对初始化权重不敏感

- 允许使用较大的学习率

  

## 设想

whitening activations： 通过直接修改activations或者网络参数修改的方式来达到activation归一化的目的

ps： Whitening Transformation： 将随机变量组成的向量的协方差转换为带有单位矩阵为协方差的新变量组成的向量。三种相关的whitening方式：a,  decorrelation transformation (解相关性转换)， 消除相关性，保持方差     b， standardization transformation，将方差设为一，保持相关性  c， coloring transformation，将向量转换为带有特定协方差的向量

这两种修改方式，由于需要保持activation归一化，将降低梯度更新对模型参数的影响

例如：计算归一化时，x = x - E(X), X为训练批量的网络层输入，同时x = u + b ,更新时计算： b<-- b + Δb， 同时b∝∂l/∂x，最后得更新值u+b+Δb - E(X+(b+Δb)) = u+b-E(u+b) , 可知对b的更新并没有修改输出，这会导致b无限增长但损失保持不变。

所以我们要保证在任意网络参数的情况下，都有理想的分布。



## 计算过程

由于对批量样本进行整体计算，所需计算量太大， 并且考虑到反向传导。我们做了两个必要简单化操作，第一， 不对输入与输出的特征之间做解关联操作，而是各特征独立做归一化操作，使之均值为0、方差为1。但是这可能导致归一化的表示后并不能代表其原有的信息，为了解决这个问题, 我们引入一对学习参数（特征的均值和标准差），来调整归一化值，这样来恢复网络的表现力，其次，在进行梯度下降更新时，输出的均值和方差由该小批量计算得知。

**正向传播计算**

![](C:\Users\DUOYI\OneDrive\文档\research\documents\batchnorm.jpg)



**反向传播计算**

![](C:\Users\DUOYI\OneDrive\文档\research\documents\batchnorm_backp.jpg)

## 使用

该层在每个batch上将前一层的激活值重新规范化，即使得其输出数据的均值接近0，其标准差接近1

```python
# use in keras
model = Sequential()
model.add(Conv2D(6, 2, input_shape(None,128, 128, 3)))
model.add(Flatten())
model.add(BatchNormalization())


# use in pytorch
# with learnable parameters
m = nn.BatchNorm1d(100)
# withour learnable parameters
m = nn.BatchNorm1d(affline=False)
input = torch.randn(20, 100)
output = m(input)
```



 





