分布式事务
Transaction Coordinator（TC）:事务协调者
Resource Manager（RM）: 事务参与者
二阶段提交（2pc）:
1阶段，preCommit, TC向所有RM发送请求是否可以执行分布式事务，可以返回成功，并且RM锁定数据库资源，
若RM反馈超时，属于反馈失败

2阶段，commit / rollback
TC收到所有RM的反馈后，所有RM反馈成功，TC发送Commit请求提交，否则rollback



三阶段提交（3pc）
1阶段，canCommit，TC询问RM，是否可以执行事务，
2阶段，preCommit，执行，锁定数据库资源
3阶段，commit / rollback，所有RM反馈成功，TC发送Commit，否则rollback

3pc改进：
 - 在参与者引入超时机制，防止在TC挂掉时，长时间收不到TC请求而阻塞.

2pc / 3pc都存在的问题：
 - TC 发送Commit / RollBack请求，只有部分RM收到，则出现不一致
 - 资源锁定，当其他业务需要访问资源时被阻塞


实现方式

## XA
强一致性的事务方案，基于2pc，牺牲一定系统可用性，无代码侵入
第一阶段锁住数据库资源
第二阶段Commit或者Rollback


## TCC
弱一致性的事务方案，基于2pc，有代码侵入

事物参与方实现Try， Confirm， Cancel接口，

空回滚：cancel先与try到达
防悬挂：cancel先到，try后到，拒绝try
业务幂等
只try了，数据展示。宁可少展示





## AT
最终一致的分阶段事务模式，无业务侵入，也是Seata的默认模式
全局锁
before Image
after Image

roll back：对比after image
commit： 释放锁。

## SAGA


