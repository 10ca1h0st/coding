import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Thread;
import java.lang.Runnable;


class Call {
    public Runnable r;
    public Call() {
        r = () -> {
            System.out.println("Hello from thread");
        };
    }

    public void test(){
        Thread t = new Thread(r,"thread-1");
        System.out.println(t.getState());
        System.out.println(t.isAlive());
        t.start();
        System.out.println(t.isAlive());
        System.out.println(t.getState());
        System.out.println(t.getName());
        System.out.println(t.getName());
        System.out.println(t.getName());
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("Error");
        }
        System.out.println(t.getName());
        System.out.println(t.getName());
        System.out.println(t.getName());
        System.out.println(t.isAlive());

    }
}

public class Test{
    public static void main(String[] argv){
        Call call = new Call();
        call.test();
    }
}
