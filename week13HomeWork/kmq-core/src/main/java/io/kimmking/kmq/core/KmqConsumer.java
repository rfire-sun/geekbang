package io.kimmking.kmq.core;

public class KmqConsumer {

    private final KmqBroker broker;

    private Kmq kmq;

    private int offset;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage poll() {

        KmqMessage kMsg =  kmq.poll(offset);
        offset++;
        return kMsg;
    }

}
