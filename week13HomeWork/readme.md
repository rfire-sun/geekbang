# 作业2
## 弄了一下午，idea push不上来，只能先用压缩包代替了，快崩溃了😣

整体挪用kmq-core

#### 自定义内存 Message 数组模拟 Queue

#### 使用指针记录当前消息写入位置

将原有的blockingQueue改为了ArrayList，自定义数组会有capacity的问题，不符合题中只用考虑OOM的考虑，这里直接使用了ArrayList，其他相应的数据结构也可以用，不过暂且不考虑

> 生产数据直接进行add，并使用size作为生产者位置

> 消费数据使用arrayList的get方式，由consumer传入记录指针offset

> 这里由于消费的offset不固定，这里使用了线程同步进制进行数据生产消费的同步操作（ps：也可以用非同步的方式）

```java

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

```

#### 对于每个命名消费者，用指针记录消费位置。

一个cosumer对应一个topic

这里使用一个offset进行记录，这里都是从头开始消费

功能还不完善

```java
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
```



