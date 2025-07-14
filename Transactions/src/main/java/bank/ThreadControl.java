package bank;

public class ThreadControl {

    private static Thread thread;

    public static void threadActivator(Thread threadObject) {
        thread = threadObject;
        thread.start();
    }

    public static void blockThread(Thread threadObject) {
        thread = threadObject;
        thread.interrupt();
    }
}
