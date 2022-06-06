package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic) {
        this.topic = topic;

        // 实际上用的是一个arrayList，这样就不用考虑越界了，就像题意一样，只用考虑OOM
        this.queue = new ArrayList<>();
    }

    private final String topic;

    private int queueLocation;

    private final List<KmqMessage> queue;


    // 这里直接用的synchronized
    public synchronized boolean send(KmqMessage message) {
        boolean flag =  queue.add(message);
        this.notifyAll();

        this.queueLocation=queue.size();
        return flag;
    }

    public synchronized KmqMessage poll(int offset){
        while(offset >= queue.size()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return queue.get(offset);
    }

    public String getTopic() {
        return topic;
    }

    public int getQueueLocation() {
        return queueLocation;
    }
}
