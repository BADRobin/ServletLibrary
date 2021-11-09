package oleg.bryl.validator;

public class BookAddValidation {
    private static final String bookName = "^.{2,50}$";
    private static final String isbn = "^\\d{9}[\\d|X]$";
    private static final String description = "^.{10,1000}$";
    private static final String amount = "^\\d+$";

    public static boolean validateAmountRegex(String login) {
        return login.matches(amount);
    }
    public static boolean validateDescriptionRegex(String login) {
        return login.matches(description);
    }
    public static boolean validateBookNameRegex(String login) {
        return login.matches(bookName);
    }
    public static boolean validateIsbnRegex(String login) {
        return login.matches(isbn);
    }
}
