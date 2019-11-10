package Controller;

import Message.Message;

/**
 * An Interface that store the Reponse of an action
 */
public interface Valve {

    /**
     * @param message
     * @return
     */
    ValveResponse execute(Message message);
}
