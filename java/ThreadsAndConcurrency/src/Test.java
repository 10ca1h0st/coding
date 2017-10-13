import java.lang.Thread;
import java.lang.Runnable;
import java.lang.Runtime;


class Call {
    public Runnable r;
    public Call() {
        r = () -> {
            Thread me = Thread.currentThread();
            System.out.println("Hello from thread:"+me.getName());
            //当设置了守护线程后，只要主线程结束，就退出，而不是继续沉睡
            /*try{
                Thread.sleep(100000);
            }catch (Exception e){
                System.out.println("Error");
            }*/
        };
    }

    public void test(){
        Thread t = new Thread(r,"thread-1");
        System.out.println("优先级:"+t.getPriority());
        System.out.println("最高优先级:"+Thread.MAX_PRIORITY+" 最小优先级:"+Thread.MIN_PRIORITY+" 默认优先级:"+Thread.NORM_PRIORITY);
        System.out.println("线程是否为守护线程:"+t.isDaemon());
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());
        //t.setDaemon(true);
        t.start();
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());
        System.out.println("线程的名字:"+t.getName());
        System.out.println("沉睡一会");
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println("Error");
        }
        System.out.println("线程的名字:"+t.getName());
        System.out.println("线程的状态："+t.getState());
        System.out.println("线程是否存活:"+t.isAlive());

    }
}

public class Test{
    public Test(){

    }
    public static void main(String[] args){
        System.out.println("可用的处理器数量:"+Runtime.getRuntime().availableProcessors());
        Call call = new Call();
        call.test();
    }
}


