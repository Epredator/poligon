package pl.etroya.corejava.serialization;

@WorkHandler()
public class AccountWorker implements Runnable, TaskWorker {
    BankAccount ba;

    public void setTarget(Object target) {

    }

    public void doWork() {
        //  Thread t = new Thread(HighVolumeAccount.class.isInstance(ba) ? (HighVolumeAccount) ba : this);
        //t.start();

    }

    @Override
    public void run() {

    }
}
