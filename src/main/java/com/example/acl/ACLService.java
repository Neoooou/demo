package com.example.acl;


import com.alibaba.boot.acl.builder.CheckPermissionsParamBuilder;
import com.alibaba.boot.acl.config.AclProperties;
import com.alibaba.buc.acl.api.input.check.CheckPermissionsParam;
import com.alibaba.buc.acl.api.input.permission.GetPermissionParam;
import com.alibaba.buc.acl.api.output.check.CheckPermissionsResult;
import com.alibaba.buc.acl.api.output.permission.PermissionResult;
import com.alibaba.buc.acl.api.service.AccessControlService;
import com.alibaba.buc.acl.api.service.ActionReadService;
import com.alibaba.buc.acl.api.service.PermissionReadService;
import com.alibaba.buc.api.UserPermissionService;
import com.alibaba.platform.buc.sso.common.tool.SSOEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/17
 */

@Component
public class ACLService {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Autowired
    private PermissionReadService permReadService;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private AclProperties properties;


    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private ActionReadService actionReadService;

    private void stdPrint(String opName, boolean isOK, String returnMsg){
        System.out.println(ANSI_RED+opName+" success = "+isOK+ANSI_RESET);
        System.out.println(ANSI_RED+opName+" return msg = "+returnMsg+ANSI_RESET);
    }


    public void checkPermWithUserId(Integer userId){
        String permissionName = "mtop-delete-commodity-test";
        CheckPermissionsParam param = CheckPermissionsParamBuilder.create()
                .accessKey(properties.getAccessKey())
                .operatorUserId(userId)
                .permission(permissionName)
                .userId(userId)
                .build();
        CheckPermissionsResult rslt = accessControlService.checkPermissions(param);
        stdPrint("Check Permission", rslt.isSuccess(), rslt.getMsg());
        if(rslt.getCheckPermissionResults() !=null){
            rslt.getCheckPermissionResults().forEach(o->{
                System.out.println(String.format("permission - %s, accessible - %s", o.getPermissionName(), o.isAccessible()));
            });
        }
    }

    public void permissionDetails(String permissionName){
        // 权限详情
        GetPermissionParam getPermissionParam = new GetPermissionParam();
        getPermissionParam.setPermissionName(permissionName);
        getPermissionParam.setAccessKey(properties.getAccessKey());
        PermissionResult permResult = permReadService.getPermission(getPermissionParam);
        System.out.println(permResult.getPath());
    }

    public void getUrl(){
//        GetActionParam param = new GetActionParam();
//        param.set
//        actionReadService.getAction()
    }
    public void grantQuery(){
        // 授权查询
//        PageUsersByPermissionParam pageUsersByPermissionParam = new PageUsersByPermissionParam();
//        pageUsersByPermissionParam.setAppName("lazada-peacock");
//        AclPagination aclPagination = new AclPagination(10, 10);
//        AclPageResult<UserResult> pageResult = null;
//        try {
//            pageResult = userPermissionService.pageUsersByPermission(pageUsersByPermissionParam, aclPagination);
//        } catch (BucException e) {
//            e.printStackTrace();
//        }
//        printObj(pageResult);
    }

    public void loadSSOUser(){
        String cookieContent = "521A2F5E765DB9E1D37A214DBA2C04E6491777FAF07EC4B5AD26EFD2DFB60204D0771734D8EAE35AB18F3F23EFFB0DB74E982332F54CCD0FCFC9B2131ECCA5D412B6E3F5EA24CEC0441D558E5CE5EBA7641E45D794DAAD00E352CFDF9BE72391";
        String userJsonStr =
                null;
        try {
            userJsonStr = SSOEncodeUtil.decodeText(SSOEncodeUtil.getCipher("",false),
                    cookieContent, "UTF-8");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(userJsonStr);
//        BucSSOCookieUser cookieUser = JSON.parseObject(userJsonStr, BucSSOCookieUser.class);
//        BucSSOUser ssoUser = BucSSOCookieUser.convertToBucSSOUser(cookieUser);
    }




}
