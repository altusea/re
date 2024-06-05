package org.example.playground;

import com.japplis.virtually.sync.BlockLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class VTTest {

    private static final Logger log = LoggerFactory.getLogger(VTTest.class);

    static class C {

        final BlockLock blockLock = new BlockLock();

        List<String> list = new ArrayList<>();

        public void add() {
            try (var _ = blockLock.lockBlock()) {
                Thread.sleep(Duration.ofSeconds(1));
                list.add("1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            C c = new C();

            for (int i = 0; i < 3; i++) {
                executorService.submit(c::add);
            }

            Thread.sleep(Duration.ofSeconds(5));
            System.out.println(c.list);
        } catch (InterruptedException e) {
            log.info("Interrupted", e);
        }
    }
}
