# 增量TF-IDF

**TF-IDF简介**

全称Term frequency-inverse document frequency， 是一种可以计算出词汇对文档重要性的数据统计方法， 词汇的TF-IDF值与其在对应

文档中出现的次数成正比，且受其在所有文档中出现的篇数权衡影响，出现的篇数越多，越代表该词并不是该文档的关键词。

计算公式如下：



$$
TF(词频) = \frac {词在文档中出现的次数}{词的总数}
$$



$$
IDF(逆文档频率) = inver-function(\frac{语料库中的文档总数}{出现该词的文档数+1})
$$



实际操作中，采用计数加一的平滑方法。

基于scikit-learn内置的TFIDFVectorizer，增加partial_fit函数，该函数可以持续对数据进行学习，更新IDF值。

具体操作如下



***partial_fit***

```python
\# update idf_

\# 计算原有idf分母 - 出现该词的文档数
df = (self.n_docs + int(self.smooth_idf)) / \
​    np.exp(self.idf_ - 1) - self.smooth_idf



\# 分子 - 文档总数加一
self.n_docs += 1

\# df.resize(len(self.vocabulary_))
for w in tokens:
​    df[self.vocabulary_[w]] += 1



\# 分子更新后，重新计算
idf = np.log(
​    (self.n_docs + self.smooth_idf) / (df + self.smooth_idf)
) + 1

self._tfidf._idf_diag = dia_matrix(
​    (idf, 0),
​    shape=(len(idf), len(idf))

)
```

***\*partial_transform\****

```python
if not self.vocabulary_ or not hasattr(self, 'vocabulary_'):
​    raise ValueError('vocabulary is empty, can not go one without vocabulary')
int2word = {v: k for k, v in self.vocabulary_.items()}
decoded_docs = list()
for doc in encoded_docs:
​    d = [int2word[i] for i in doc]
​    decoded_docs.append(' '.join(d))
docs = decoded_docs + docs
x = self.transform(docs)
```



# BM25

bm25是一种用来评价搜索词和文档之间相关性的算法，它是一种基于概率检索模型提出的算法。

**kapi BM25** (*BM* is an abbreviation of *best matching*) is a [ranking function](https://en.wikipedia.org/wiki/Ranking_function) used by [search engines](https://en.wikipedia.org/wiki/Search_engine) to estimate the [relevance](https://en.wikipedia.org/wiki/Relevance_(information_retrieval)) of documents to a given search query. It is based on the [probabilistic retrieval framework](https://en.wikipedia.org/wiki/Probabilistic_relevance_model) 



