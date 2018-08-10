package com.barry.cloud.platform.common.id;
/**
 * ID WORKER
 * IdWorker idWorker = new IdWorker(1L);
 * Long id = idWorker.nextId();
 */
public class IdWorker {

    private final Long workerId;
    private final static Long twepoch = 1361753741828L;
    private Long sequence = 0L;
    private final static Long workerIdBits = 10L;
    public final static Long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static Long sequenceBits = 10L;
    private final static Long workerIdShift = sequenceBits;
    private final static Long timestampLeftShift = sequenceBits + workerIdBits;
    public final static Long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;
    public IdWorker(final Long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IdGenerateException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized Long nextId() {
        Long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new IdGenerateException(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.lastTimestamp = timestamp;
        Long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << this.workerIdShift) | (this.sequence);

        return nextId;
    }

    private Long tilNextMillis(final Long lastTimestamp) {
        Long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }
    private Long timeGen() {
        return System.currentTimeMillis();
    }
    /*
    public static void main(String[] args){
        IdWorker worker2 = new IdWorker(2);

        for(int i=0;i<100;i++) {
            Long id = worker2.nextId();
            System.out.println(id);
        }
    }*/

}
