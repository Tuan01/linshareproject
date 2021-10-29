package pageUIs;

public class WebMailPageUI {
    public static final String WEBMAIL_PAGE_LOADED_SUCESSFULL  = "//div[@id='taskbar']";
    public static final String NEW_EMAIL_RECEIVED_IN_LIST = "//table[@id='messagelist']//span[@class='msgicon unread status']/following-sibling::a//span[contains(text(), '%s')]";
    public  static final String FRAME = "//iframe[@id='messagecontframe']";
    public static final String HYPERLINK_TO_SHARED_FILE = "//a[text()='%s']";
    public static final String EMAIL_SUBJECT_AT_MESSAGE_HEADER_AT_WEBMAIL = "//h2[@class='subject']//span[contains(text(), 'by Walker MCCALLISTER')]";
    public static final String VALUE_EMAIL_OF_TO_FIELD_AND_REPLY_TO_FIELD_MESSAGE_HEADR_AT_WEBMAIL = "//td[text()='%s']/following-sibling::td//a[@title !='Add to address book']";
    public static final String LIST_ATTACHED_FILES_AT_WEBMAIL = "//span[text()='Attached files']/following-sibling::ul//a[text()='%s']";
    public static final String VALUE_FOR_CREATE_AND_EXPIRY_DATE_AT_WEBMAIL = "//span[text()='%s']/following-sibling::span";
}
