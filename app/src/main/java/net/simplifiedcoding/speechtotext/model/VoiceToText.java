package net.simplifiedcoding.speechtotext.model;

import org.chalup.microorm.annotations.Column;

import java.io.Serializable;

/**
 * Created by Ariful Islam on 12/20/2017.
 */

public class VoiceToText implements Serializable {

    @Column("id")
    public String id;
    @Column("text")
    public String text;
    @Column("name")
    public String name;
    @Column("phone")
    public String phone;
    @Column("datetime")
    public String datetime;
    @Column("time")
    public String time;
    @Column("callType")
    public String callType;
    @Column("audio_file")
    public String audio_file;

}
