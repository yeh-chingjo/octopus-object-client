package com.ani.octopus.service.agent.service.websocket.observer;

/**
 * Created by zhaoyu on 15-10-30.
 */
public class AniAccountCallMessageObserver implements MessageObserver {
    @Override
    public void update(MessageObservable o, Object arg) {
        System.out.println(arg);
        System.out.println("AniAccountCallMessageObserver");
    }
}