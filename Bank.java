import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Bank {

    Map<String, User> users;
    Map<String, List<Transaction>> transactions;
    int adminIdProvider;
    int userIdProvider;

    public Bank() {
        users = new HashMap<>();
        transactions = new HashMap<>();

    }

    public void creditAmount(String accountNumber, int amount) {
        User user = users.get(accountNumber);

        user.creditMoney(amount);
        String formatedTime = getFormatedTime();
        pushTransaction(accountNumber, user.getTransactionCount() + 1, "credit", amount, formatedTime, "Local",
                accountNumber);

    }

    public void debitAmount(String accountNumber, int amount) {
        User user = users.get(accountNumber);
        if (user == null) {
            System.out.println("no user acc found!");
            return;
        }
        if (user.getBalanceAmount() >= amount) {
            user.debitMoney(amount);
            String formatedTime = getFormatedTime();
            pushTransaction(accountNumber, user.getTransactionCount() + 1, "debit", amount, formatedTime, accountNumber,
                    "Local");
        } else {
            System.out.println("Insufficaiant balance! in " + accountNumber);
        }
    }

    public void sendAmount(String senderAccountNumber, String receiverAccountNumber, int amount) {
        User sender = users.get(senderAccountNumber);
        User receiver = users.get(receiverAccountNumber);
        if (sender == null || receiver == null) {
            System.out.println("No sender or recevier found!");
            return;
        }
        if (sender.getBalanceAmount() >= amount) {
            sender.debitMoney(amount);
            receiver.creditMoney(amount);
            System.out.println("amount sended!");

            // transaction storing
            String formatedTime = getFormatedTime();

            System.out.println(formatedTime);
            pushTransaction(receiverAccountNumber, receiver.getTransactionCount() + 1, "credit", amount, formatedTime,
                    senderAccountNumber, receiverAccountNumber);
            pushTransaction(senderAccountNumber, sender.getTransactionCount() + 1, "debit", amount, formatedTime,
                    senderAccountNumber, receiverAccountNumber);
        } else {
            System.out.println("Insufficiant balance! in " + senderAccountNumber);
        }

    }

    public void registerUser(Person person) {

        String accountNumber = generateAccountNumber();
        if (users.containsKey(accountNumber)) {
            System.out.println("already have this account number!");
            return;
        }
        User user = new User(person, ++userIdProvider, accountNumber);
        users.put(user.getAccountNumber(), user);
        System.out.println("user added! acc number:" + accountNumber);

    }

    private String generateAccountNumber() {

        String accNumber = "";
        Random rand = new Random();
        do {

            for (int i = 0; i < 4; i++) {
                int number = rand.nextInt(10);
                accNumber += number;
            }
        } while (users.containsKey(accNumber));

        return accNumber;

    }

    public void removeUser(String accountNumber) {
        User user = users.get(accountNumber);
        if (user == null) {
            System.out.println("no account founded!");
            return;
        }

        users.remove(user.getAccountNumber());
        transactions.remove(accountNumber);
    }

    public void updateUserInfo(String accountNumber, String attribute, Object value) {
        User user = users.get(accountNumber);
        if (user == null) {
            System.out.println("no user found!");
        }
        switch (attribute) {
            case "name":
                user.getPerson().setName((String) value);
                break;
            case "age":
                user.getPerson().setAge((Integer) value);
                break;
            case "adhaarNumber":
                user.getPerson().setAdhaarNumber((String) attribute);
                break;
            default:
                System.out.println("invalid input!");
                return;
        }
    }

    public void printAllUsers() {
        System.out.println(" user id \t name \t\t acc number \t transaction count \t balance");
        for (User user : users.values()) {
            System.out.println(
                    " " + user.getUserId() + " \t\t " + user.getPerson().getName() + " \t\t " + user.getAccountNumber()
                            + " \t\t " + user.getTransactionCount() + " \t\t " + user.getBalanceAmount());

        }
    }

    public void printStatement(String accountNumber) {
        List<Transaction> transList = transactions.get(accountNumber);
        if (transList == null) {
            System.out.println("no statement found!");
            return;
        }

        System.out.println("Trn id \t\t type \t\t amount \t\t date \t\t from \t\t to");

        for (Transaction trans : transList) {
            System.out.println(trans.getTransactionId() + "\t\t" + trans.getType() + "\t\t" + trans.getAmount() + "\t\t"
                    + trans.getDate() + "\t\t" + trans.getFrom() + "\t\t" + trans.getTo());
        }

    }

    private void pushTransaction(String transactAcc, int transactionId, String type, int amount, String formatedTime,
            String from, String to) {
        if (!transactions.containsKey(transactAcc))
            transactions.put(transactAcc, new ArrayList<>());

        transactions.get(transactAcc).add(new Transaction(transactionId, type, amount, formatedTime, from, to));
    }

    private String getFormatedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
