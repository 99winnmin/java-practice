package next.bonus;

public class Bonus {

    @ElapsedTime
    public void run1() throws Exception {
        Thread.sleep(1100);
    }

    @ElapsedTime
    public void run2() throws Exception {
        Thread.sleep(2200);
    }

    public void run3() throws Exception {
        Thread.sleep(2000);
    }

}
