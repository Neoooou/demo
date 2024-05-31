# 条件随机场-Conditional Random Field（CRF）

一种应用在模式识别和结构预测的统计学建模方法

**特征函数**

参数：

- 句子s，表示待标注序列
- i， 表示s中第i个单词
- l_i,  表示给待标注序列第i个单词标注的词性
- l_i-1, 表示给待标注序列第i-1个单词标注的词性

$$
score(l | s) = \sum_{j = 1}^m \sum_{i = 1}^n \lambda_j f_j(s, i, l_i, l_{i-1})
$$

第一个和函数在所有特征方程fj()上运行，第二个和函数在句子的所有位置i上运行

我们可以通过幂乘和归一化操作将scores转换到0-1区间内，与softmax同原理，以如下操作公式
$$
p(l | s) = \frac{exp[score(l|s)]}{\sum_{l’} exp[score(l’|s)]} = \frac{exp[\sum_{j = 1}^m \sum_{i = 1}^n \lambda_j f_j(s, i, l_i, l_{i-1})]}{\sum_{l’} exp[\sum_{j = 1}^m \sum_{i = 1}^n \lambda_j f_j(s, i, l’_i, l’_{i-1})]}
$$
**特征函数举例**
$$
f_1(s, i, l_i, l_{i-1}) = 1如果l_{i}是副词，且以ly结尾，其它情况则为0.
$$

$$
同时，该特征方程的权重值λ应该为一个较大的正数，来表示这种标注方式的正确性
$$





