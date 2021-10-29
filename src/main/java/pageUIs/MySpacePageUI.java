package pageUIs;

public class MySpacePageUI {
    public static final String TITLE_MYSPACE_PAGE = "//div[@class='card-header ng-scope']//span[string()='My files']";
    public static final String CHECKBOX_OF_FILE_MYSPACE_PAGE = "//div[@class='table-responsive']//span[string()='%s']/../../../../preceding-sibling::td//a[contains(@class, 'hidden-xs')]//div[contains(@class, 'checkbox-ctn-thumb')]";
    public static final String HINGLIGHT_FILE_MYSPACE_PAGE = "//div[@class='table-responsive']//span[text()='%s']/../../../../parent::tr[contains(@class, 'highlight-list-elem')]";
    public static final String DYNAMIC_FILE_NAME_MYSPACE_PAGE = "//div[@class='table-responsive']//span[text()='%s']";
    public static final String DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE = "//div[@class='toast-upper-panel']//span[text()='The item was deleted successfully.']";
    public static final String MULTIPLE_FILE_DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE = "//div[@class='toast-upper-panel']//span[text()='The items were deleted successfully.']";
    public static final String RENAME_FILE_MYSPACE_PAGE = "//div[@class='table-responsive']//span[text()='File Automation Testing']";
    public static final String DUPLICATE_TOAST_MESSAGE_MY_SPACE_PAGE = "//div[@class='toast-upper-panel']//span[text()='The file has been copied successfully.']";
    public static final String FULL_NAME_OF_RECIPIENT_AT_SHARES_TAB_MYSPACE_PAGE = "//p[@class='recipientsInfo']//span[@class='user-full-name']";
    public static final String EMAIL_RECIPIENT_SHARED_DETAIL = "//div[@class='design-content']//p";
    public static final String NAME_SHARED_FILE_IN_SHARED_DETAIL = "//div[@class='dash-widget-title']//p";
    public static final String SHARED_DATE_AT_SHARE_DETAIL = "//small[text()='Shared on the']/following-sibling::p";
    public static final String RECIPIENTS_LIST_SHARING_DETAILS = "//div[@class='recipients-list-sharing-details']";
    public static final String RECIPIENT_NAME = "//div[@class='recipientsAutocomplete actions ng-scope']";
    public static final String CANCEL_SHARE_BUTTON = "//span[text()='Cancel share']";
    public static final String SHARED_LINK_IN_FILE_NAME = "//div[@class='table-responsive']//span[text()='%s']/following-sibling::span//span[text()='Shared']";
    public static final String DOWNLOAD_BUTTON_AT_TOOLBAR_SELECTED_MULTIPLE_FILE = "//div[@id='selection-actions']//a[contains(@data-ng-click,'multiDownload')]";
    public static final String LIST_OF_NAME_FILE_MY_SPACE = "//div[@class='ctn-name-actions']//span[@class='file-name-disp single-line ng-binding']";
    public static final String LARGE_ICON = "//i[@class='fa fa-th-large']";
    public static final String SEARCH_TEXTBOX_CARD_HEADER = "//div[@id='search-field-ctn']//input[@type='text']";



}
