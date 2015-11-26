package com.ani.octopus.service.agent.test;

import com.ani.octopus.service.agent.core.AnicelMeta;
import com.ani.octopus.service.agent.core.websocket.AniServiceSession;
import com.ani.octopus.service.agent.core.websocket.WebSocketClient;
import com.ani.octopus.service.agent.core.websocket.WebSocketSessionFactory;
import com.ani.octopus.service.agent.service.websocket.*;
import com.ani.octopus.service.agent.service.websocket.observer.AniAccountCallMessageObserver;
import com.ani.octopus.service.agent.service.websocket.observer.AniObjectCallMessageObserver;
import com.ani.octopus.service.agent.service.websocket.observer.MessageObserver;
import com.ani.octopus.service.agent.service.websocket.dto.AniStub;

import javax.websocket.Session;
import java.util.Vector;

/**
 * Created by zhaoyu on 15-10-30.
 */
public class WebSocketSessionFactoryTest {

    public void testSessionFactory() {
        // you need to implement the Invokable interface and register on
        // WebSocketClient for anicloud platform to callback
        Invokable invokable = new ClientInvokerImpl();
        WebSocketClient socketClient = new WebSocketClient(invokable);

        // you need to implement your own observer and register on socketClient
        // to receive the message from anicloud platform
        Vector<MessageObserver> messageObservers = new Vector<>();
        messageObservers.add(new AniObjectCallMessageObserver());
        messageObservers.add(new AniAccountCallMessageObserver());
        socketClient.setObs(messageObservers);

        // inject your WebSocketClient instance and anicloud socket destination url to factory
        // and use factory to get the session, than you can use the session to communicate
        // with anicloud platform
        AnicelMeta anicelMeta = new AnicelMeta();
        WebSocketSessionFactory sessionFactory =
                new WebSocketSessionFactory(socketClient, anicelMeta, "123456", "clientSecret");
        AniServiceSession session = sessionFactory.getAniServiceSession();

        // use AniInvokerImpl service to call platform
        Invokable aniInvoker = new AniInvokerImpl(session);
        AniStub aniStub = new AniStub();
        aniStub.setObjectId(123L);
        aniStub.setStubId(456);


        try {
            aniInvoker.invokeAniObjectSync(aniStub);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}