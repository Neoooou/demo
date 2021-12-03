//package com.example.demo;
//
//import com.alibaba.middleware.jingwei.client.custom.EventMessage;
//import com.alibaba.middleware.jingwei.client.custom.InsertEvent;
//import com.alibaba.middleware.jingwei.client.custom.SimpleMessageListener;
//import com.alibaba.middleware.jingwei.client.custom.UpdateEvent;
//import com.alibaba.middleware.jingwei.common.exception.RetryLaterException;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author: neo.zr
// * @Created on: 2021/9/1
// */
//
//public class JingweiTest extends SimpleMessageListener {
//
//
//    @Override
//    public Result onReceiveMessage(List<EventMessage> list) {
//        // 自主消费逻辑
//        for(EventMessage eventMessage : list){
//            try{
//                String table = eventMessage.getTableName();
//                List<Map<String, Serializable>> rowDataMaps = null;
//                if(eventMessage instanceof InsertEvent){
//                    rowDataMaps = eventMessage.getRowDataMaps();
//                }else if(eventMessage instanceof UpdateEvent){
//                    UpdateEvent ue = (UpdateEvent)eventMessage;
//                    rowDataMaps = ue.getModifyRowDataMaps();
//                }
//                System.out.println(table);
//                System.out.println(rowDataMaps);
//            }catch (Exception e){
//                throw new RetryLaterException("Jinhwei:ostenser failed", e);
//            }
//        }
//        return null;
//    }
//}
