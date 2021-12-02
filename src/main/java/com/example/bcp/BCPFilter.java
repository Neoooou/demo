//package com.example.bcp;
//
//import com.alibaba.bcp.sdk.main.Bootstrap;
//import com.alibaba.bcp.sdk.main.MetaqType;
//import com.alibaba.bcp.task.runner.java.RuleExcutor;
//
//import java.util.Map;
//
///**
// * bcp 数据过滤器
// * @Author: neo.zr
// * @Created on: 2021/9/8
// */
//
//public class BCPFilter implements RuleExcutor {
//
//    /**
//     *  * @return
//     *  * - return true或return null表示需要进行check校验
//     *  * - return false表示不需要进行校验(即这条数据被过滤掉了)；
//     *  * 不要return 其他内容，return非true非false非null的内容一律认为是false，
//     *  过滤脚本里抛出异常默认return false，没有过滤脚本表示全部return true。
//     */
//    @Override
//    public Object excute(Map<String, Object> map) {
//
//
//        return null;
//    }
//
//    public static void main(String[] args) throws Exception {
//        Bootstrap.generateMetaqEvent("store_member_point_change", MetaqType.Json,Object.class,null);
//    }
//}
