package ind.lgh.system.RWLock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class FileObj{
    public String context ;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
class ReadThread extends Thread{
    private FileObj fileObj;
    private ReentrantReadWriteLock.ReadLock lock;
    public  ReadThread(FileObj obj , ReentrantReadWriteLock.ReadLock lock){
        this.lock = lock;
        this.fileObj = obj;
    }
    @Override
    public void run(){

//        while(true){
            lock.lock();
            try{
                System.out.println(getName()+" 读取 "+ fileObj.getContext());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
//        }


    }
}
class WriteThread extends  Thread{
    Random random = new Random();
    private FileObj fileObj;
    private ReentrantReadWriteLock.WriteLock lock;
    private String[] str = {"Hello Java","Year Spark","Ok Flume"};
    public  WriteThread(FileObj obj , ReentrantReadWriteLock.WriteLock lock){
        this.lock = lock;
        this.fileObj = obj;
    }
    @Override
    public void run() {
//        while (true) {
            lock.lock();
            try {

                String tmp = str[random.nextInt(3)];
                System.out.println(getName() + " 写 " + tmp);
                fileObj.setContext(tmp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
//        }
    }
}
public class ReadAndWriteTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        FileObj obj  =  new FileObj();
        obj.setContext("Hello....");
        Thread r1 = new ReadThread(obj,lock.readLock());
        Thread r2 = new ReadThread(obj,lock.readLock());
        Thread r3 = new ReadThread(obj,lock.readLock());

        WriteThread w1 = new WriteThread(obj,lock.writeLock());
//        WriteThread w2 = new WriteThread(obj,lock.writeLock());
//        WriteThread w3 = new WriteThread(obj,lock.writeLock());

        executorService.execute(r1);
        executorService.execute(r2);
        executorService.execute(r3);
        executorService.execute(w1);
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.execute(r3);
       /* executorService.execute(w1);
        executorService.execute(w3);*/

        executorService.shutdown();


    }
}