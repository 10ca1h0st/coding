import java.util.Timer;
import java.util.TimerTask;

/*
* 不能运行在IDEA上，因为IDEA的控制台不能解析退格键
*/

public class BackAndForth {
    private volatile static int pos = 0;
    private volatile static boolean sig = true;
    public static void main(String[] args){
        TimerTask ftask = new TimerTask() {
            @Override
            public void run() {
                if(sig){
                    System.out.print("*");
                    pos++;
                    if(pos == 20){
                        sig = false;
                    }
                }
            }
        };

        TimerTask btask = new TimerTask() {
            @Override
            public void run() {
                if(!sig){
                    System.out.print("\b?\b");
                    pos--;
                    if(pos == 0){
                        sig = true;
                    }
                }
            }
        };

        Timer ftimer = new Timer();
        Timer btimer = new Timer();
        ftimer.schedule(ftask,0,1000);
        btimer.schedule(btask,0,1000);
    }
}
