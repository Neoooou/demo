//package com.example.bcp;
//
//import com.alibaba.bcp.task.data.ServiceParams;
//import com.alibaba.bcp.task.data.service.GenericService;
//import com.alibaba.bcp.task.data.service.ServiceFactory;
//import com.alibaba.bcp.task.event.metaq.StoreMemberPointChangeEvent;
//import com.alibaba.bcp.task.runner.java.RuleExcutor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * bcp 数据校验
// * @Author: neo.zr
// * @Created on: 2021/9/8
// */
//
//public class BCPCheck implements RuleExcutor {
//
//    /**
//     *
//     * @param context
//     * @return
//     * - return null表示校验成功，
//     * - return 非null表示校验失败
//     * bcp会对校验失败的记录会进行保存，保存的内容为return内容的toString()的值，所以尽量return 字符串，
//     * 注意字符串只会取前1800长度的内容。
//     */
//    @Override
//    public Object excute(Map<String, Object> context) {
//        StoreMemberPointChangeEvent event = (StoreMemberPointChangeEvent)context.get("event");
//        Map data = (Map)context.get("store_member_point_change");
//
//        String interfaceName = "com.lazada.membership.api.facade.StoreMembershipFacade";
//        String version = "1.0.0";
//
//        String getPointDetailMethod = "getPointDetailByRequestId";
//        ServiceParams params = new ServiceParams();
//        List<String> serverCenters = new ArrayList();
//        String venture = (String)data.get("venture");
//
//        serverCenters.add(String.format("lazada-%s-pre", venture.toLowerCase()));
//        params.setConfigserverCenter(serverCenters);
//        params.setMethodName(getPointDetailMethod);
//        params.setServiceVersion(version);
//        params.setGroup("HSF");
//        params.setTimeout(3000);
//        GenericService service = ServiceFactory.createGenericService(interfaceName, params);
//
//        String[] paramTypes = new String[]{Long.class.getName(), String.class.getName()};
//
//        String requestId = (String)data.get("requestId");
//        Long buyerId = new Long(data.get("buyerId").toString());
//
//        boolean changeSuccess = (Boolean)data.get("changeSuccess");
//
//        Object[] args = new Object[]{buyerId, requestId};
//        Map re = (Map)service.$invoke(getPointDetailMethod, paramTypes, args);
//
//
//        Object module = re.get("module");
//        boolean isSuccess = (Boolean)re.get("success");
//        boolean isExist = isSuccess && module != null;
//
//        if(isExist != changeSuccess){
//            return "Error";
//        }
//        return null;
//
////        Object module = re.get("module");
////        Boolean isSuccess = (Boolean)re.get("success");
////        if(Objects.isNull(module) || !isSuccess){
////
////        }
////        return null;
//    }
//
//
//
//    /**
//     *
//     * {"buyerId":1004706014L, "requestId": "61556707506014-100072361-0-0", "venture": "SG", "changeSuccess":true}
//     */
//
//
//}
