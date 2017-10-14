public class Await {
    public static void main(String[] args){
        Counter counter = new Counter();
        Runnable r1 = ()->{
          System.out.println(Thread.currentThread().getName()+" am in waiting");
          counter.count++;
          try{
              System.out.println(Thread.currentThread().getName()+" will sleep");
              Thread.sleep(2000);
          }catch (InterruptedException ie){

          }
          synchronized (counter){
              while(!counter.wake){
                  try{
                      counter.wait();
                  }catch (InterruptedException ie){

                  }
              }
              System.out.println(Thread.currentThread().getName()+" am over");
          }

        };

        Runnable r2 = ()->{
            while(counter.count < 3){
                try{
                    Thread.sleep(200);
                }catch (InterruptedException ie){

                }
            }
            synchronized (counter){
                counter.wake = true;
                counter.notifyAll();
            }

        };

        Thread thd1 = new Thread(r1);
        Thread thd2 = new Thread(r1);
        Thread thd3 = new Thread(r1);
        Thread thd4 = new Thread(r2);
        thd1.start();
        thd2.start();
        thd3.start();
        thd4.start();

    }
}

class Counter{
    volatile int  count;
    volatile boolean wake;
    Counter(){
        this.count = 0;
        this.wake = false;
    }
    /*synchronized void setCount(){
        this.count++;
    }
    synchronized int getCount(){
        return this.count;
    }*/
}
