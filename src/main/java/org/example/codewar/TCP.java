package org.example.codewar;

import java.util.HashMap;
import java.util.Map;

public class TCP {

    enum Event {
        APP_PASSIVE_OPEN,
        APP_ACTIVE_OPEN,
        APP_SEND,
        APP_CLOSE,
        APP_TIMEOUT,
        RCV_SYN,
        RCV_ACK,
        RCV_SYN_ACK,
        RCV_FIN,
        RCV_FIN_ACK
    }

    enum State {
        CLOSED,
        LISTEN,
        SYN_SENT,
        SYN_RCVD,
        ESTABLISHED,
        CLOSE_WAIT,
        LAST_ACK,
        FIN_WAIT_1,
        FIN_WAIT_2,
        CLOSING,
        TIME_WAIT
    }


    static Map<State, Map<Event, State>> stateTransMap = new HashMap<>() {{
        // CLOSED: APP_PASSIVE_OPEN -> LISTEN
        // CLOSED: APP_ACTIVE_OPEN  -> SYN_SENT
        put(State.CLOSED, new HashMap<>() {{
            put(Event.APP_PASSIVE_OPEN, State.LISTEN);
            put(Event.APP_ACTIVE_OPEN, State.SYN_SENT);
        }});
        // LISTEN: RCV_SYN          -> SYN_RCVD
        // LISTEN: APP_SEND         -> SYN_SENT
        // LISTEN: APP_CLOSE        -> CLOSED
        put(State.LISTEN, new HashMap<>() {{
            put(Event.RCV_SYN, State.SYN_RCVD);
            put(Event.APP_SEND, State.SYN_SENT);
            put(Event.APP_CLOSE, State.CLOSED);
        }});
        // SYN_RCVD: APP_CLOSE      -> FIN_WAIT_1
        // SYN_RCVD: RCV_ACK        -> ESTABLISHED
        put(State.SYN_RCVD, new HashMap<>() {{
            put(Event.APP_CLOSE, State.FIN_WAIT_1);
            put(Event.RCV_ACK, State.ESTABLISHED);
        }});
        // SYN_SENT: RCV_SYN        -> SYN_RCVD
        // SYN_SENT: RCV_SYN_ACK    -> ESTABLISHED
        // SYN_SENT: APP_CLOSE      -> CLOSED
        put(State.SYN_SENT, new HashMap<>() {{
            put(Event.RCV_SYN, State.SYN_RCVD);
            put(Event.RCV_SYN_ACK, State.ESTABLISHED);
            put(Event.APP_CLOSE, State.CLOSED);
        }});
        // ESTABLISHED: APP_CLOSE   -> FIN_WAIT_1
        // ESTABLISHED: RCV_FIN     -> CLOSE_WAIT
        put(State.ESTABLISHED, new HashMap<>() {{
            put(Event.APP_CLOSE, State.FIN_WAIT_1);
            put(Event.RCV_FIN, State.CLOSE_WAIT);
        }});
        // FIN_WAIT_1: RCV_FIN      -> CLOSING
        // FIN_WAIT_1: RCV_FIN_ACK  -> TIME_WAIT
        // FIN_WAIT_1: RCV_ACK      -> FIN_WAIT_2
        put(State.FIN_WAIT_1, new HashMap<>() {{
            put(Event.RCV_FIN, State.CLOSING);
            put(Event.RCV_FIN_ACK, State.TIME_WAIT);
            put(Event.RCV_ACK, State.FIN_WAIT_2);
        }});
        // CLOSING: RCV_ACK         -> TIME_WAIT
        put(State.CLOSING, new HashMap<>() {{
            put(Event.RCV_ACK, State.TIME_WAIT);
        }});
        // FIN_WAIT_2: RCV_FIN      -> TIME_WAIT
        put(State.FIN_WAIT_2, new HashMap<>() {{
            put(Event.RCV_FIN, State.TIME_WAIT);
        }});
        // TIME_WAIT: APP_TIMEOUT   -> CLOSED
        put(State.TIME_WAIT, new HashMap<>() {{
            put(Event.APP_TIMEOUT, State.CLOSED);
        }});
        // CLOSE_WAIT: APP_CLOSE    -> LAST_ACK
        put(State.CLOSE_WAIT, new HashMap<>() {{
            put(Event.APP_CLOSE, State.LAST_ACK);
        }});
        // LAST_ACK: RCV_ACK        -> CLOSED
        put(State.LAST_ACK, new HashMap<>() {{
            put(Event.RCV_ACK, State.CLOSED);
        }});
    }};

    public static String traverseStates(String[] events) {
        State cur = State.CLOSED;
        for (String eventStr : events) {
            Event event = Event.valueOf(eventStr);
            if (stateTransMap.get(cur).containsKey(event)) {
                cur = stateTransMap.get(cur).get(event);
            } else {
                return "ERROR";
            }
        }

        return cur.name();
    }
}
