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
            synchronized (s) {
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
