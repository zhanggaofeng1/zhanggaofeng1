/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.service;

import com.num.proto.req.AbstReqProto;
import com.num.proto.req.ReqLoginProto;
import com.num.proto.resp.ResultState;
import javax.annotation.PostConstruct;
import javolution.util.FastMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class RegisterProtoService {

    private final FastMap<Short, Class<? extends AbstReqProto>> reqProMap = new FastMap<>(100);
    private final FastMap<Class<?>, Short> respProMap = new FastMap<>(100);

    @PostConstruct
    public void init() {
        repProClassRegister();
        respProClassRegister();
    }
    
    // 根据协议id获取协议Class
    public Class<? extends AbstReqProto> getReqProtoById(Short proId) {
        return reqProMap.get(proId);
    }
    
    // 根据协议Class获取协议id
    public Short getRespProtoIdByClazz(Class<?> clazz) {
        return respProMap.get(clazz);
    }
    

    // 接收协议注册
    private void repProClassRegister() {
        reqProMap.put((short)0x1, ReqLoginProto.class);
    }

    // 发送协议注册
    private void respProClassRegister() {
        respProMap.put(ResultState.class, (short)0x1);
    }
}
