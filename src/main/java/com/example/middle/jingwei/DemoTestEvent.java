//package com.example.middle.jingwei;
//
//import com.alibaba.bcp.task.event.jingwei.CommonEvent;
//
//import java.io.Serializable;
//import java.util.Map;
//
///**
// * @Author: neo.zr
// * @Created on: 2021/9/1
// */
//
//public class DemoTestEvent extends CommonEvent {
//
//    /**
//     * 表名
//     * @return
//     */
//    @Override
//    public String getTableName() {
//        return "order_event_process_tbl";
//    }
//
//    /**
//     * 事件消息的Id
//     * @return
//     */
//    @Override
//    public String getId() {
//        return reader.strV("id");
//    }
//
//    /**
//     * 当前事件类型
//     * @return
//     */
//    @Override
//    public String getType() {
//        return "point_order_event_process";
//    }
//
//    /**
//     * 当前事件名称
//     * @return
//     */
//    @Override
//    public String getName() {
//        return "积分事件之订单交易";
//    }
//
//    @Override
//    public Map<String, Serializable> getMockNewData() {
//        return super.getMockNewData();
//    }
//}
