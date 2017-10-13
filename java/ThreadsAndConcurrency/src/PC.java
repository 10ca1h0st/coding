import sun.security.provider.SHA;

public class PC {
    public static void main(String[] args){
        Shared s = new Shared();
        new Producer(s).start();
        new Consumer(s).start();
    }
}

class Shared{
    private char c;
    private volatile boolean writeable = true;
    synchronized void setSharedChar(char c){
        while(!writeable)
            try{
                //为了证明notifyAll()之后，所有的线程都是平等地竞争，不管是wait()醒来的线程，还是别的线程
                //System.out.println("PPPPPPPPP");
                wait();
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

        this.c = c;
        writeable = false;
        notifyAll();

    }
    synchronized char getSharedChar(){
        while(writeable)
            try{
                //System.out.println("CCCCCCCCC");
                wait();
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

        writeable = true;
        notifyAll();
        return c;
    }
}

class Producer extends Thread{
    private final Shared s;
    Producer(Shared s){
        this.s = s;
    }
    @Override
    public void run(){
        for(char ch = 'A';ch<='Z';ch++){
            synchronized (s) {//锁可重入
                s.setSharedChar(ch);
                System.out.println(ch + " produced by Producer");
            }
        }
    }

}

class Consumer extends Thread{
    private final Shared s;
    Consumer(Shared s){
        this.s = s;
    }

    @Override
    public void run(){
        char ch;
        do{
            synchronized (s) {
                ch = s.getSharedChar();
                System.out.println(ch + " consumed by Consumer");
            }
        }while(ch != 'Z');
    }
}
