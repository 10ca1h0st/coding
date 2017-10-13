public class CanWaitBeInterrupted {
    public static void main(String[] args){
        Add lock = new Add();
        MyThread mythd = new MyThread(lock);
        mythd.start();
        /*System.out.println(mythd.isInterrupted());
        mythd.interrupt();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ie){}
        System.out.println(mythd.isInterrupted());*/
        try{
            Thread.sleep(50);
        }catch (InterruptedException ie){}
        synchronized (lock){
            lock.notify();
        }
    }
}

class MyThread extends Thread{
    private Add lock;
    MyThread(Add lock){
        this.lock = lock;
    }
    @Override
    public void run(){
        synchronized (lock){
            try{
                lock.wait();
            }catch (InterruptedException ie){
                System.out.println("i am interrupted");
            }
            System.out.println("i am alive again");
        }
    }

}

class Add{}
