public class User {

    private Person person;
    private int userId;
    private int transactionCount;
    private String accountNumber;
    private int balanceAmount;

    public User(Person person,int userId,String accountNumber)
    {
        this.person=person;
        this.userId=userId;
        this.accountNumber=accountNumber;
        this.balanceAmount=0;
    }

    
    public void creditMoney(int amount)
    {
        this.balanceAmount+=amount;
        transactionCount++;
    }

    public void debitMoney(int amount)
    {
        this.balanceAmount-=amount;
        transactionCount++;
    }


    public Person getPerson()
    {
        return this.person;
    }

    public int getUserId() {
        return userId;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }



    

    
}
