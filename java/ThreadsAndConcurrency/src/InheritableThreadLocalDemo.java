public class InheritableThreadLocalDemo {

    private static final InheritableThreadLocal<Integer> intVal = new InheritableThreadLocal<>();
    public static void main(String[] main){
        Runnable rP = ()->{
            intVal.set(new Integer(10));
            Runnable rS = ()->{
                System.out.println(Thread.currentThread().getName()+" "+intVal.get());
            };
            Thread thdChild = new Thread(rS);
            thdChild.setName("child");
            thdChild.start();
        };
        new Thread(rP).start();
    }
}

