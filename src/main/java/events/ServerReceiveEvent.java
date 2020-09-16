package events;

import dataStructures.Receive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerReceiveEvent {
    private static Map<Integer, Set<ServerReceiveEventListener>> listeners;


    public ServerReceiveEvent() {
        this.initListenersMap();
    }


    public ServerReceiveEvent(Integer eventType, ServerReceiveEventListener item) {
        this.initListenersMap();
        this.subscribe(eventType, item);
    }

    public void emit(Integer eventType, Receive receive) {
        try {
            Set<ServerReceiveEventListener> listener = ServerReceiveEvent.listeners.get(eventType);
            for (ServerReceiveEventListener eventListener : listener) {
                eventListener.handleEventUpade(receive);
            }
        } catch (NullPointerException ignored) {
        }
    }


    private void initListenersMap() {
        if (ServerReceiveEvent.listeners == null) {
            ServerReceiveEvent.listeners = new HashMap<>();
        }
    }


    public void subscribe(Integer eventType, ServerReceiveEventListener item) {
        Set<ServerReceiveEventListener> listener = ServerReceiveEvent
                .listeners
                .computeIfAbsent(eventType, k -> new HashSet<>());

        listener.add(item);
    }

    public void unsubscribe(Integer eventType, ServerReceiveEventListener item) {
        Set<ServerReceiveEventListener> listener = ServerReceiveEvent.listeners.get(eventType);
        listener.remove(item);

    }

}
