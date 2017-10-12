public class DeadLockDemo {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public DeadLockDemo(){}

    public void method1(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("first thread in method1");
            }
        }
    }
    public void method2(){
        synchronized (lock2){
            synchronized (lock1){
                System.out.println("second thread in method2");
            }
        }
    }

    public static void main(String[] args){
        DeadLockDemo dld = new DeadLockDemo();
        Runnable r1 = () -> {
            while(true){
                dld.method1();
                try{
                    Thread.sleep(50);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            while(true){
                dld.method2();
                try{
                    Thread.sleep(50);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Thread thdA = new Thread(r1);
        Thread thdB = new Thread(r2);
        thdA.start();
        thdB.start();
    }
}
