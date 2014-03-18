/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class SendClass implements Runnable {

    private IoSession session;

    public SendClass(IoSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                session.write(new Integer[]{1,100});
            } catch (Exception ex) {
                Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
