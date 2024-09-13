package org.example.security;

public interface Timestamped {

    String getCallerMark();

    void setCallerMark(String callerMark);

    String getCallTimestamp();

    void setCallTimestamp(String callTimestamp);
}
