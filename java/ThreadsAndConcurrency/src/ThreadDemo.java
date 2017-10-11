

public class ThreadDemo{
    public static void main(String[] args){
        //Thread thd = Thread.currentThread();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while(!Thread.interrupted()) {
                    System.out.println(name + ": " + count++);
                    //thd.interrupt();
                    /*try{
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/
                }

            }
        };
        Thread thdA = new Thread(r);
        Thread thdB = new Thread(r);
        thdA.start();
        thdB.start();
        try{
            Thread.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        thdA.interrupt();
        thdB.interrupt();
    }
}
