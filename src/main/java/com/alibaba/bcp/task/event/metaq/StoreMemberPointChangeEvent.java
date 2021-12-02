//package com.alibaba.bcp.task.event.metaq;
//
//import com.aliyun.openservices.ons.api.Message;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class StoreMemberPointChangeEvent extends MetaqJsonStringDataEvent {
//
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * 当前事件消息的id(为了保证唯一,尽量使用消息中的唯一属性)
//     * id的最大长度为128,返回值不能超过128长度
//	 * 例如：
//	 * if(message==null){
//	 *		return null;
//	 *	}
//	 * return String.valueOf(message.get("bizEntityValue"));
//	 */
//	public String getId() {
//		return String.valueOf(message.get("requestId"));
//	}
//
//	/**
//	 * 当前事件类型(注入context的变量名,建议使用topic,必须是一个常量字符串)
//	 */
//	public String getType() {
//		return "store_member_point_change";
//	}
//
//	/**
//	 * 当前事件名称(用于前台显示,必须是一个常量字符串)
//	 */
//	public String getName() {
//		return "store member point change event";
//	}
//
//	/**
//	 * 当前消息的子状态(消息不存在子状态则为null)
//	 * 如果存在子状态,返回值需要在对应事件编辑界面的事件子状态值分类列表中存在
//	*/
//	public String getCurrentStatus() {
//		return null;
//	}
//
//	/**
//	 * 当前消息的父状态(消息不存在父状态则为null)
//	 * 如果存在父状态,返回值需要在对应事件编辑界面的事件父状态值分类列表中存在
//	*/
//	public String getParentCurrentStatus() {
//		return null;
//	}
//
//	/**
//	 * mock测试数据
//	 * @return
//	 */
//	public Map getMockData(){
//		return new HashMap(){
//			{
//				put("requestId", "mockRequestId");
//				put("venture", "SG");
//				put("changeSuccess", true);
//			}
//		};
//	}
//
//}
