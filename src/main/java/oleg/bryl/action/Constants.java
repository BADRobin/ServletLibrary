package oleg.bryl.action;

public final class Constants {
    //pages
    public static final String REGISTER = "register";
    public static final String BOOKS = "books";
    public static final String WELCOME = "welcome";
    public static final String MAIN = "main";
    public static final String READERS = "readers";
    public static final String BASKET = "basket";
    public static final String ORDERS_USER = "orders_user";
    public static final String FORUM = "forum";
    public static final String ORDER_PAGE = "order_page";
    public static final String COMMENT = "comment";
    public static final String FOUND_BOOKS = "found_books";
    public static final String ADD_AUTHOR = "add_author";
    public static final String NEW_BOOK = "newbook";

    //RegisterAction constants
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "password_confirm";
    public static final String PHONE = "phone";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL_EXIST = "email_exist";
    public static final String PASSWORD_NOT_MACH = "password_not_mach";
    public static final String TRUE = "true";

    //LoginAction constants
    public static final String LOGIN = "login";
    public static final String LOGIN_ERROR = "login_error";

    //Session constants
    public static final String ATT_USER_ID = "userId";
    public static final String ATT_ROLE = "role";
    public static final String ATT_ROLE_ID = "role_id";
    public static final String ATT_NAME = "name";

    //View constants
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String JSP_FORMAT = ".jsp";

    //PageReaders constants
    public static final String ATT_READERS = "readers";
    public static final String PAGE = "page";
    public static final String ATT_NO_PAGES = "noOfPages";
    public static final String ATT_CURRENT_PAGE = "currentPage";

    //PageAboutReader constants
    public static final String READER_ID = "user_id";
    public static final String DELETE_ID = "delete_id";
    public static final String ADMIN = "admin";

    //PageBook constants
    public static final String GENRE_ID = "genre_id";
    public static final String ATT_BOOKS = "books";
    public static final String ATT_GENRES = "genres";
    public static final String ATT_AUTHORS = "authors";
    public static final double CONVERT_TO_DOUBLE = 1.0;

    //AddBookAction
    public static final String ISBN = "isbn";
    public static final String DESCRIPTION = "description";
    public static final String BOOK_NAME = "book_name";
    public static final String YEAR = "year";
    public static final String GENRE_NAME = "genre_name";
    public static final String AMOUNT = "amount";

    //SelectLanguageAction
    public static final String LANG = "lang";
    public static final String REFERER = "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final int HOUR = 24;
    public static final int MINUTE = 60;
    public static final int SEC = 60;

    //Basket&Order
    public static final String ID_BOOK = "id_book";
    public static final String BASKET_LIST = "basket_list";
    public static final String BASKET_SIZE = "basket_size";
    public static final String BASKET_EMPTY = "basket_empty";
    public static final String BASKET_BOOKS_LIST = "basket_books_list";
    public static final String NOT_AUTH = "not_auth";
    public static final String BOOK_NOT_AVAILABLE = "book_not_available";
    public static final String ONE_BOOK_ONLY = "one_book_only";
    public static final String ID_ORDER = "id_order";
    public static final String ORDERS = "orders";
    public static final String SEARCHER = "searcher";
    public static final String FIND_BOOKS = "findBooks";
    public static final String AUTHOR_1 = "author1";
    public static final String AUTHOR_2 = "author2";
    public static final String AUTHOR_3 = "author3";
    public static final String USER = "user";
    public static final String NEW = "New";

    public static final String EMAIL_ERROR = "email_error";
    public static final String PASSWORD_ERROR = "password_error";
    public static final String FIRST_NAME_ERROR = "first_name_error";
    public static final String LAST_NAME_ERROR = "last_name_error";
    public static final String MIDDLE_NAME_ERROR = "middle_name_error";
    public static final String PHONE_ERROR = "phone_error";
    public static final String BIRTHDAY_ERROR = "birthday_error";

    public static final String AMOUNT_ERROR = "amount_error";
    public static final String DESCRIPTION_ERROR = "description_error";
    public static final String BOOK_NAME_ERROR = "book_name_error";
    public static final String ISBN_ERROR = "isbn_error";
    public static final String YEAR_ERROR = "year_error";

    private Constants() {
    }
}
