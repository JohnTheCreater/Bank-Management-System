public class Person{

    private String name;
    private int age;
    private String adhaarNumber;

    public Person(String name,int age,String adhaarNumber)
    {
        this.name=name;
        this.age=age;
        this.adhaarNumber=adhaarNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

   

    public String getName()
    {
        return this.name;
    }

    public int age()
    {
        return this.age;

    }

    public String getAdhaarNumber()
    {
        return this.adhaarNumber;
    }


}