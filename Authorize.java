public class Authorize {

    private final String userPassword="!";
    private final String adminPassword="#";


    public boolean authorize(String role,String passValue)
    {
        switch(role)
        {
            case "admin":
                return passValue.equals(adminPassword);
            case "user":
                return passValue.equals(userPassword);
        }
        return false;

    }

    
}
