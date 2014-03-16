/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import java.net.InetSocketAddress;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 *
 * @author Administrator
 */
public class ClientTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        NioSocketConnector connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        chain.addLast("keep-alive", new KeepAliveFilter(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE, new KeepAliveExceptionHandler(), 10, 5));
        chain.addLast("codec", new ProtocolCodecFilter(new GameProtoCodecFactory()));
        connector.setHandler(new GameHandlerService());
        connector.setConnectTimeoutCheckInterval(30);
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 8888));
        cf.awaitUninterruptibly();
        Thread th = new Thread(new SendClass(cf.getSession()));
        th.start();
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }

}
