Encoder-Decoder：编码解码器，一种应用于seq2seq的模型，seq2seq简单来说就是输入一个序列，输出一个序列。编码：将输入序列映射到一个语义空间向量，然后解码语义空间向量为输出序列。编码器解码器可选的神经单元包括CNN/RNN/BiRNN/GRU/LSTM.

**Encoder-decoder弊端**：

- 语义向量无法完全表示整个序列的信息
- 先输入的内容携带的信息会被后输入的信息稀释掉



一个注意力函数可以描述为将query与一组键值对（key-value）映射到输出，其中query、key、value和输出均为向量

