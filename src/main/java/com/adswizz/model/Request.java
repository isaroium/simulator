package com.adswizz.model;

/**
 * @author mihaiisaroiu
 */
public class Request {
    public long size;
    public int limitation;

    public Request(long duration, int limitation) {
        this.size = duration;
        this.limitation = limitation;
    }

    public static Request of(long duration, int limitation) {
        return new Request(duration, limitation);
    }

    @Override
    public String toString() {
        return "Request{" +
                "limitation=" + limitation +
                ", size=" + size +
                '}';
    }
}
