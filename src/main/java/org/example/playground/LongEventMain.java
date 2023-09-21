package org.example.playground;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {
    public static void main(String[] args) throws Exception {
        int bufferSize = 1024;

        LongEventFactory eventFactory = new LongEventFactory();
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, DaemonThreadFactory.INSTANCE);

        LongEventHandler longEventHandler = new LongEventHandler();
        disruptor.handleEventsWith(longEventHandler);
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
