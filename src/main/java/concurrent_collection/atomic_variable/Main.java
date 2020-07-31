package concurrent_collection.atomic_variable;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        Bank bank = new Bank(account);
        Thread bankThead = new Thread(bank);
        System.out.printf("Main: initial account:%s\n",account.getBalance());
        companyThread.start();
        bankThead.start();

        try {
            companyThread.join();
            bankThead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: final balance:%s\n",account.getBalance());
    }
}
