**什么是CUDA**
   CUDA(ComputeUnified Device Architecture)，是显卡厂商NVIDIA推出的运算平台。 CUDA是一种由NVIDIA推出的通用并行计算架构，该架构使GPU能够解决复杂的计算问题。

**什么是CUDNN**
        NVIDIA cuDNN是用于深度神经网络的GPU加速库。它强调性能、易用性和低内存开销。NVIDIA cuDNN可以集成到更高级别的机器学习框架中，如谷歌的Tensorflow、加州大学伯克利分校的流行caffe软件。
简单的插入式设计可以让开发人员专注于设计和实现神经网络模型，而不是简单调整性能，同时还可以在GPU上实现高性能现代并行计算。

**CUDA与CUDNN的关系**
      CUDA看作是一个工作台，上面配有很多工具，如锤子、螺丝刀等。cuDNN是基于CUDA的深度学习GPU加速库，有了它才能在GPU上完成深度学习的计算。它就相当于工作的工具，比如它就是个扳手。但是CUDA
这个工作台买来的时候，并没有送扳手。想要在CUDA上运行深度神经网络，就要安装cuDNN，就像你想要拧个螺帽就要把扳手买回来。这样才能使GPU进行深度神经网络的工作，工作速度相较CPU快很多。



**兼容版本**

```
python==3.5.x   cuda==9.0  cuDNN==7  tensorflow-gpu==1.9.0
python==3.6.x   cuda==10.0.0  cuDNN==7.5.0  tensorflow-gpu==1.13.1  torch==1.2.0
```



> bug: NVIDIA-SMI has failed because it couldn't communicate with the NVIDIA driver. Make sure that the latest NVIDIA driver is installed and running.

> 解决： 重装nvidia驱动

```
# 查看显卡可以安装的驱动版本
ubuntu-drivers devices
# 清除旧驱动
apt-get purge nvidia*
# 添加源
add-apt-repository ppa:graphics-drivers/ppa
apt-get update
# apt安装（注意根据可用版本选择）
apt-get install nvidia-driver-410
```

