package pageUIs;

public class ReceivedSharesPageUI {
    public static final String RECIPIENT_RECEIVED_SHARED_FILE_AT_RECEIVED_SHARES_PAGE = "//td[@data-title-text='Name']//span[text()='%s']/ancestor::td/following-sibling::td[@data-title-text='Expiration date' and text()=' %s ']/following-sibling::td[@data-title-text='Size' and text()=' %s ']";
    public static final String NAME_OF_RECIPIENT_SHARE_FILE_AT_RECEIVED_SHARES_PAGE = "//td[@data-title-text='Name']//span[text()='cat.jpg']/parent::span//span[@class='secondary-info-ctn']";
    public static final String MESSAGE_POPOP_VIEWING_RESTRICTION_UNDER_FILE_SELECTION = "//div[@class='toast-upper-panel']//span[text()='You are currently viewing a restricted file selection.']";
}
