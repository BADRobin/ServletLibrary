package oleg.bryl.jdbc;

import oleg.bryl.jdbc.exception.ResourcesException;
import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ResourcesQueue<Connection> {

    private static final Logger log = Logger.getLogger(ResourcesQueue.class);
    private final Semaphore semaphore;
    private final Queue<Connection> resource = new ConcurrentLinkedQueue<>();
    private final int timeOut;

    public ResourcesQueue(int count, int timeOut) {
        semaphore = new Semaphore(count, true);
        this.timeOut = timeOut;
    }

    public Connection takeResource() throws ResourcesException {
        try {
            if (semaphore.tryAcquire(timeOut, TimeUnit.SECONDS)) {
                log.info("Семафор свободен");
                return resource.poll();
            } else {
                log.info("Тайм-аут семафора");
            }
        } catch (InterruptedException e) {
            throw new ResourcesException("Semaphore timeout", e);
        }
        throw new ResourcesException("Semaphore timeout");
    }

    public void returnResource(Connection res) {
        resource.add(res);
        semaphore.release();
    }

    public void addResource(Connection connection) {
        resource.add(connection);
    }

    public int size() {
        return resource.size();
    }

}


