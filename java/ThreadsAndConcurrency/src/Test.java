import java.lang.Thread;
import java.lang.Runnable;
import java.lang.Runtime;


class Call {
    public Runnable r;
    public Call() {
        r = () -> {
            System.out.println("Hello from thread");
        };
    }

    public void test(){
        Thread t = new Thread(r,"thread-1");
        System.out.println("优先级:"+t.getPriority());
        System.out.println("最高优先级:"+Thread.MAX_PRIORITY+" 最小优先级:"+Thread.MIN_PRIORITY);
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());
        t.start();
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());
        System.out.println(t.getName());
        System.out.println("沉睡一会");
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println("Error");
        }
        System.out.println(t.getName());
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());

    }
}

public class Test{
    public static void main(String[] argv){
        System.out.println("可用的处理器数量:"+Runtime.getRuntime().availableProcessors());
        Call call = new Call();
        call.test();
    }
}
