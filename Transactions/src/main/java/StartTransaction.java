import bank.Account;
import bank.Bank;
import bank.ThreadControl;

public class StartTransaction {
    public static void main(String[] args) {

        Bank bank = new Bank();

        ThreadControl.threadActivator(new Account("1", 50_000L));
        ThreadControl.threadActivator(new Account("2", 50_000L));
        ThreadControl.threadActivator(new Account("3", 30_000L));

            long amount = 40_000L;
            System.out.println("Сумма транзакции " + amount +
                    "\nДля аккаунтов: " + bank.getAccounts().get("1").getAccNumber() +
                    " и " + bank.getAccounts().get("2").getAccNumber() + "\n"
            );
            System.out.println("Остаток до транзакции");
            System.out.println(bank.getBalance("1"));
            System.out.println(bank.getBalance("2"));
            System.out.println();

            bank.transfer("1", "2", amount);

            System.out.println();
            System.out.println("Остаток после транзакции");
            System.out.println(bank.getBalance("1"));
            System.out.println(bank.getBalance("2"));
            System.out.println();


        System.out.println("Сумма транзакции " + amount +
                "\nДля аккаунтов: " + bank.getAccounts().get("1").getAccNumber() +
                " и " + bank.getAccounts().get("3").getAccNumber() + "\n"
        );
        System.out.println("Остаток до транзакции");
        System.out.println(bank.getBalance("1"));
        System.out.println(bank.getBalance("3"));
        System.out.println();

        bank.transfer("1", "3", amount);

        System.out.println();
        System.out.println("Остаток после транзакции");
        System.out.println(bank.getBalance("1"));
        System.out.println(bank.getBalance("3"));
        System.out.println();
        System.out.println(bank.getSumAllAccounts());
    }
}
