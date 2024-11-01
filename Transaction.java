
public class Transaction {

    private int transactionId;
    private String type;
    private int amount;
    private String date;
    private String from;
    private String to;

    public Transaction(int transactionId,String type,int amount,String date,String from,String to)
    {
        this.transactionId=transactionId;
        this.type=type;
        this.amount=amount;
        this.date=date;
        this.from=from;
        this.to=to;
    }

    public String  getFrom()
    {
        return this.from;
    }
    public String getTo()
    {
        return this.to;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public String getType() {
        return this.type;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getDate() {
        return this.date;
    }
    
    
}
