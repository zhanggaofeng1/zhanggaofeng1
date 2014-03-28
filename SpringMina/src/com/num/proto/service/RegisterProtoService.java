/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.service;

import com.num.mina.enums.GmState;
import com.num.proto.req.AbstReqProto;
import com.num.proto.req.ReqLoginProto;
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

    private final FastMap<GmState, FastMap<Short, AbstReqProto>> reqProMap = new FastMap<>(100);
    private final FastMap<Class<?>, Short> respProMap = new FastMap<>(100);
    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        requestProtoLoad();
        responseProtoLoad();
    }

    // 接收协议注册
    private void requestProtoLoad() {
        request_proto_register(new ReqLoginProto((short) 0x001), GmState.GAME_NOT);
    }

    // 发送协议注册
    private void responseProtoLoad() {
        respProMap.put(ResultState.class, (short) 0x001);
    }
    
    public AbstReqProto getReqProtoById(Short proId, GmState state) {
        FastMap<Short, AbstReqProto> reqProtos = reqProMap.get(state);
        if (reqProtos == null || reqProtos.isEmpty()) {
            return null;
        }
        return reqProtos.get(proId);
    }

    public Short getRespProtoIdByClazz(Class<?> clazz) {
        return respProMap.get(clazz);
    }


    private void request_proto_register(AbstReqProto reqProto, GmState state) {
        
        reqProto.setContext(context);
        reqProto.init();
        FastMap<Short, AbstReqProto> reqProtos = reqProMap.get(state);
        if (reqProtos == null) {
            reqProtos = new FastMap<Short, AbstReqProto>();
            reqProMap.put(state, reqProtos);
        }
        reqProtos.put(reqProto.getProtoId(), reqProto);
    }
}
