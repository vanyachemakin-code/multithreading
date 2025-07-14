package bank;

import java.util.*;

public class Bank {

    private static final Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public static void setAccounts(String accNum, Account account) {
        accounts.put(accNum, account);
    }

    private synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    private void doTransfer(Account fromAcc, Account toAcc, long amount) {
        if (hasRequiredMoney(fromAcc.getAccNumber(), amount)) {
            try {
                boolean blockAccounts = amount > 50_000L ?
                        isFraud(fromAcc.getAccNumber(), toAcc.getAccNumber(), amount)
                        : false;
                if (blockAccounts) {
                    block(fromAcc.getAccNumber());
                    block(toAcc.getAccNumber());
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            credit(fromAcc.getAccNumber(), amount);
            debit(toAcc.getAccNumber(), amount);
            System.out.println("ТРАНЗАКИЯ УСПЕШНА!!");
        } else {
            System.out.println("НЕДОСТАТОЧНО СРЕДСТВ!!");
        }

    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (hasAccount(fromAccountNum) && hasAccount(toAccountNum)) {
            Account fromAcc = accounts.get(fromAccountNum);
            Account toAcc = accounts.get(toAccountNum);

            if (fromAccountNum.compareTo(toAccountNum) > 0) {
                synchronized (fromAcc) {
                    synchronized (toAcc) {
                        doTransfer(fromAcc, toAcc, amount);
                    }
                }
            } else {
                synchronized (toAcc) {
                    synchronized (fromAcc) {
                        doTransfer(fromAcc, toAcc, amount);
                    }
                }
            }
        } else {
            System.out.println("АККАУНТ НЕ СУЩЕСТВУЕТ!!");
        }
    }

    private void debit(String accNum, long money) {
        Account account = accounts.get(accNum);
        account.setMoney(account.getMoney() + money);
    }

    private void credit(String accNum, long money) {
        Account account = accounts.get(accNum);
        account.setMoney(account.getMoney() - money);
    }

    private synchronized void block(String accNum) {
        ThreadControl.blockThread(accounts.get(accNum));
        System.out.println("АККАУНТ "+ accNum + " ЗАБЛОКИРОВАН :(");
    }

    private boolean hasAccount(String accNum) {
        Set<String> accNumbers = accounts.keySet();
        return accNumbers.contains(accNum);
    }

    private boolean hasRequiredMoney(String accNum, long money) {
        return getBalance(accNum) >= money;
    }

    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        synchronized (account) {
            return account.getMoney();
        }
    }

    public long getSumAllAccounts() {
        long sum = 0L;
        Set<String> accountSet = accounts.keySet();
        for (String accNum: accountSet) {
            sum += getBalance(accNum);
        }
        return sum;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
