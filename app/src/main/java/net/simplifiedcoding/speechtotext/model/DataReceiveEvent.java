package net.simplifiedcoding.speechtotext.model;

/**
 * Created by Dream71 on 31/10/2017.
 */

public class DataReceiveEvent {
    private String eventTag;
    private VoiceToText responseMessage;
    public DataReceiveEvent(String eventTag, VoiceToText responseMessage) {
        this.eventTag = eventTag;
        this.responseMessage = responseMessage;
    }

    public boolean isTagMatchWith(String tag){
        return eventTag.equals(tag);
    }

    public VoiceToText getResponseMessage() {
        return responseMessage;
    }
}
