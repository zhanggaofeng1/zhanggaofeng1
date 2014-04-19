
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.service;

import com.num.mina.enums.GmState;
import com.num.proto.req.AbstReqProto;
import com.num.proto.req.ReqLoginProto;
import com.num.proto.resp.AbstResp;
import com.num.proto.resp.ResultState;
import javax.annotation.PostConstruct;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class RegisterProtoService {

    @Autowired
    private ApplicationContext context;
    
    private final FastMap<Short, AbstReqProto> reqProMap = new FastMap<>(100);
    private final FastMap<Class<? extends AbstResp>, Short> respProMap = new FastMap<>(100);

    @PostConstruct
    public void init() {
        repProClassRegister();
        respProClassRegister();
    }
    
    // 根据协议id获取协议Class
    public AbstReqProto getReqProtoById(Short proId) {
        
        AbstReqProto reqProto = reqProMap.get(proId);
        if (reqProto != null) {
            return reqProto.clonePackage();
        }
        return null;
    }
    
    public short getRespProtoIdByClazz(Class<?> clazz) {
        return respProMap.get(clazz);
    }

    private void request_proto_rigister(AbstReqProto reqProto) {
        reqProto.setApplicationContext(context);
        reqProto.init();
        reqProMap.put(reqProto.getProtoId(), reqProto);
    }
    
    // 接收协议注册
    private void repProClassRegister() {
        request_proto_rigister(new ReqLoginProto((short)0x001));
    }
    
    // 发送协议注册
    private void respProClassRegister() {
        respProMap.put(ResultState.class, (short)0x1);
    }
    
}
