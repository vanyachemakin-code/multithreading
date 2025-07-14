package bank;

public class Account extends Thread{

    private long money;
    private String accNumber;

    public Account (String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    @Override
    public void run() {
        Bank.setAccounts(accNumber, this);
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}
