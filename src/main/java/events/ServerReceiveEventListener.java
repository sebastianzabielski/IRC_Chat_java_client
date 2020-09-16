package events;

import dataStructures.Receive;

public interface ServerReceiveEventListener {

    void handleEventUpdate(Receive receive);

    void unsubscribe();
}
