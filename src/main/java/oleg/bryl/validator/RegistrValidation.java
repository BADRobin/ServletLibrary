package oleg.bryl.validator;

public class RegistrValidation {
    private static final String password = ".{6,}";
    private static final String userName = "^.{3,14}$";
    private static final String phone = "\\d{6,13}";
    private static final String datePublication = "\\d{4}-[01]\\d-[0-3]\\d";
    private static final String email = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

    public static boolean validateMailRegex(String login) {
        return login.matches(email);
    }
    public static boolean validatePassRegex(String login) {
        return login.matches(password);
    }
    public static boolean validateDateRegex(String login) {
        return login.matches(datePublication);
    }
    public static boolean validatePhoneRegex(String login) {
        return login.matches(phone);
    }
    public static boolean validateNameRegex(String login) {
        return login.matches(userName);
    }

}
