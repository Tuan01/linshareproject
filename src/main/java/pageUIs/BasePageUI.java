package pageUIs;

public class BasePageUI {

    // Side bar
    public static final String MENU_PAGE_LINK_BY_NAME = "//div[@class='sidebar-inner ng-scope']//span[string()='%s']";
    public static final String DEFAULT_HOME_PAGE_LINK_CHECKBOX = "//a[text()='%s']/../../following-sibling::div";

    // Logout
    public static final String AVATAR_HUMAN_ICON_AT_MY_FROFILE = "//i[@class='tm-icon ls-my-profile']";
    public static final String DYNAMIC_BUTTON_BY_NAME_DROPDOWN_AT_PROFILE ="//ul[@class='dropdown-menu dm-icon pull-right']//span[string()='%s']";
    public static final String UPLOAD_FILE_TYPE = "//div[@id='breadcrumb-wrap']//input[@type='file']";
    public static final String UPLOADED_FILE_NAME = "//div[text()='%s']//parent::div[@class='data-list-ctn']/small[text()='done']";
    public static final String MESSAGE_A_FILE_NAME = "//div[@class='toast-upper-panel']//span[@class='ng-scope ng-binding']";

    // toolbar-action
    public static final String DYNAMIC_ACTIONS_TOOLBAR = "//div[@id='selection-actions']//span[string()='%s']";

    // Action In more action - Toolbar
    public static final String DYNAMIC_ATIONS_IN_MORE_OPTIONS_TOOLBAR = "//span[string()='more actions']/parent::a/following-sibling::ul//span[text()='%s']";

    // Search bar
    public static final String SEARCH_FILE_BY_NAME_CARD_HEADER = "//div[@id='search-field-ctn']//input[@type='text']";

    // Table
    public static final String ROW_VALUE_BY_ALL_FIELD = "//td[@data-title-text='Name']//span[text()='%s']/ancestor::td/following-sibling::td[@data-title-text='Size' and text()=' %s ']";

    // Function items of files in list
    public static final String DYNAMIC_FUNCTION_ITEMS_OF_FILES_IN_LIST = "//div[@class='table-responsive']//span[text()='%s']/../../following-sibling::ls-documents-menu//a[@title='%s']";

    //Function items for workgroup or drive on list
    public static final String DYNAMIC_FUNCTION_ITEMS_OF_WORKGROUP_OR_DRIVE_LIST = "//span[text()=' %s ']/ancestor::div[@class='ctn-name-actions']//a[@title='%s']";

    // File name
    public static final String DYNAMIC_FILE_NAME= "//div[@class='table-responsive']//span[text()='%s']";

    // Function items of files in Dropdown menu
    public static final String DYNAMIC_FUNTION_ITEMS_OF_FILES_IN_DROPDOWN_MENU = "//ul[contains(@class, 'dropdown-menu open')]//span[string()='%s']";

    // Toolbar-Actions  toggle-checkbox-toolbar
    public static final String TOGGLE_CHECKBOX_AT_TOOLBAR =  "//table[@id='file-list-table']//i[contains(@class, 'ls-check-o checkbox-table')]";
    public static final String TOGGLE_CHECKBOX_SELECTED_AT_TOOLBAR = "//div[@id='selection-actions']//i[@class='ls-check-checked']";
    // Confimr popup - file
    public static final String CONFIRM_DELETED_FILE_POPUP_DIALOG = "//div[@class='swal-modal sweet-alert']//button[string()='%s']";

    // Rename popup
    public static final String RENAME_POPUP_DIALOG = "//md-dialog[@aria-label = 'ACTION.RENAME']//input";

    // Lisst

    public  static final String ALL_ELEMENT = "//div[@class='table-responsive']//p[@class='main-name-row display-flex']";

    // Toast Messsage Successfully: Delete, Duplicate, Share, Forward...
    public  static final String TOAST_MESSSAGE_FOR_ALL_ACTIONS = "//div[@class='toast-upper-panel']//span[text()='%s']";


    // Quick Share

    public static final String ADD_RECIPIENT = "//input[@placeholder='%s']";
    public static final String SUGGESSTION_LIST_RECIPIENT = "//ul[@class='dropdown-menu ng-isolate-scope']";
    public static final String EMAIL_SUBJECT_MESSAGE_AND_SHARE_NOTE_TEXTAREA = "//textarea[@placeholder='%s']";
    public  static final String MORE_OPTIONS_LINK = "//a[text()='more options']";
    public static final String SHARE_FORWARD_CANCEL_BUTTON = "//button[string()=' %s ']";
    public static final String GRID_DATEPICKER = "//table[@role='grid']";
    public static final String MONTH_IN_DATAPIKCER = "//div[@class='dp-title ng-binding']";
    public static final String NEXT_BUTTON = "//i[@class='zmdi zmdi-long-arrow-right']";
    public static final String PREV_BUTTON = "//i[@class='zmdi zmdi-long-arrow-left']";
    public static final String ALL_DAY = "//table[@role='grid']//button[not(@disabled='disabled')]//span[text()='%s']";
    public static final String EXPIRATION_DATE_FIELD = "//input[@name='expirationDate']";
    public static final String CLOSE_ICON_SIDEBAR_AT_SHARE_DETAIL = "//i[@class='zmdi zmdi-close zmdi-hc-lg']";

    // Share Detail
    public  static final String SHARED_LINK_NEAR_FILE_NAME = "//div[@class='table-responsive']//span[text()='%s']/following-sibling::span//span[text()='Shared']";




}
