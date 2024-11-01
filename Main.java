import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Authorize authorize = new Authorize();
        Bank bank = new Bank();
        do {
            System.out.println("-------------------------BANKING SYSTEM-----------------------------");
            System.out.println("1.admin");
            System.out.println("2.user");
            System.out.println("3.exit");
            int option = scan.nextInt();
            scan.nextLine();
            String password="";
            if(option!=3)
            {
                System.out.print("Enter password: ");
                password = scan.nextLine();
            }
            switch (option) {
                case 1:
                    if (authorize.authorize("admin", password)) {
                        boolean c = true;

                        do {
                            showAdminMenu();
                            option = scan.nextInt();
                            scan.nextLine();

                            switch (option) {
                                case 1:
                                    System.out.print("Enter name: ");
                                    String name = scan.nextLine();
                                    System.out.print("Enter age: ");
                                    int age = scan.nextInt();
                                    scan.nextLine();
                                    System.out.print("Enter adhaarnumber: ");
                                    String adhaarNumber = scan.nextLine();
                                    Person person = new Person(name, age, adhaarNumber);
                                    bank.registerUser(person);
                                    break;
                                case 2:
                                    System.out.print("Enter account Number: ");
                                    String accNumber = scan.nextLine();
                                    bank.removeUser(accNumber);
                                    break;
                                case 3:
                                    System.out.print("Enter account Number: ");
                                    accNumber = scan.nextLine();
                                    System.out.print("Enter which attribute want to change(name,age,adhaarNumber):");
                                    String attribute = scan.nextLine();
                                    System.out.print("Enter the value: ");
                                    Object value = scan.nextLine();
                                    bank.updateUserInfo(accNumber, attribute, value);
                                    break;
                                case 4:
                                    bank.printAllUsers();
                                    ;
                                    break;
                                case 5:
                                    c = false;
                                    break;
                                default:
                                    System.out.println("enter valid input!");
                            }
                        } while (c);
                    }
                    break;
                case 2:
                    if (authorize.authorize("user", password)) {
                        boolean c = true;
                        do {
                            showUserMenu();
                            option = scan.nextInt();
                            scan.nextLine();
                            switch (option) {
                                case 1:
                                    System.out.print("Enter account number: ");
                                    String accNumber = scan.nextLine();
                                    System.out.print("Enter amount to be added: ");
                                    int amount = scan.nextInt();
                                    scan.nextLine();
                                    bank.creditAmount(accNumber, amount);
                                    break;
                                case 2:
                                    System.out.print("Enter account number: ");
                                    accNumber = scan.nextLine();
                                    System.out.print("Enter amount to be widthraw: ");
                                    amount = scan.nextInt();
                                    scan.nextLine();
                                    bank.debitAmount(accNumber, amount);
                                    break;
                                case 3:
                                    System.out.print("Enter account sender number: ");
                                    String senderAccNumber = scan.nextLine();
                                    System.out.print("Enter account recevier number: ");
                                    String recevierAccNumber = scan.nextLine();
                                    System.out.print("Enter amount to send: ");
                                    amount = scan.nextInt();
                                    scan.nextLine();
                                    bank.sendAmount(senderAccNumber, recevierAccNumber, amount);
                                    break;
                                case 4:
                                    System.out.print("Enter the account number: ");
                                    String acNumber = scan.nextLine();
                                    bank.printStatement(acNumber);
                                    break;
                                case 5:
                                    c = false;
                                    break;

                            }
                        } while (c);
                    }
                    break;
                case 3:
                    scan.close();
                    return;
            }
        } while (true);

    }

    private static void showUserMenu() {
        System.out.println("1. Add amount");
        System.out.println("2. Widthraw amount");
        System.out.println("3. Send amount");
        System.out.println("4. Statement");
        System.out.println("5. back");
        System.out.print("enter your option: ");

    }

    private static void showAdminMenu() {
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4.display all users");
        System.out.println("5.back");
        System.out.print("enter your option: ");

    }

}
