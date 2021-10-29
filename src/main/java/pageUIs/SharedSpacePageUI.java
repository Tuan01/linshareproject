package pageUIs;

public class SharedSpacePageUI {
    public static final String TITLE_SHARED_SPACE_PAGE = "//div[@class='card-header']//span[string()='Shared space']";
    public static final String ADD_MENU_GROUP_BUTTON = "//div[@id='breadcrumb-wrap']//a[@class='add-menu-group dropdown-toggle']";
    public static final String DYNAMIC_ITEM_IN_MENU_GROUP_BUTTON = "//ul[contains(@class, 'dropdown-divided open')]//span[text()='%s']";
    public static final String POPUP_CREATE_WR_OR_DRIVE_OR_FOLDER = "//md-dialog[@aria-label ='%s']";
    public static final String DEFAULT_NAME_TEXTBOX = "//input[@ng-keypress='dialogInputVm.keypress($event)']";
    public static final String ALL_LIST_NAME_WORKGROUP = "//span[@class='file-name-disp single-line']";
    public static final String NEW_NAME_WORKGROUP_DRIVE_FOLDER = "//span[text()=' %s ']";
    public static final String SEARCH_TEXTBOX = "//div[@id='search-field-ctn']//input[@type='text']";
    public static final String DETAIL_WORKGROUP = "//div[@id='root-level']//a[text()=' %s ']";
    public static final String POPUP_UPLOAD_SUCCESS = "//div[@class='toast-upper-panel']//span[@class='ng-scope ng-binding']";
    public static final String UPLOADED_FILE_NAME = "//td[@data-title-text='Name']//span[text()=' %s ']";
    public static final String DETAIL_FOLDER = "//div[@id='root-level']/following-sibling::div//a[text()=' %s ']";
    public static final String BACK_ICON_IN_WORKGROUP_OR_DRIVE = "//div[@id='breadcrumb-wrap']//i[@class='ls-previous-caret']";

    // Dropdown role
    public static final String DROPDOWN_SELECTE_ROLE_MEMEMBER = "//span[text()='Add team members :']/../../following-sibling::form//button[@type='button']";
    public static final String CHIILD_ROLE_OPTION_IN_DROPDOWN = "//span[text()='Add team members :']/ancestor::data/following-sibling::ul//a";
    public static final String ADD_MEMBER_AND_ROLE_SUCCESS = "//div[text()=' %s ']/following-sibling::div//span[text()=' %s ']";

    //Exist members
    public static final String REMOVE_OR_EDIT_MEMBER_ICON = "//div[text()=' %s ']/parent::div/following-sibling::div//a[@title='%s']";
    public static final String MESSAGE_REMOVED_MEMEBR_SUCCESS = "//div[@class='toast-upper-panel']//span[text()='%s has been removed successfully from the Workgroup']";

    // Drive/Workgroup Deleted Successfully
    public static final String MESSAGE_DELETED_WORKGROUP_OR_DRIVE_SUCCESS = "//div[@class='toast-upper-panel']//span[text()='The item was deleted successfully.']";

    // Role: Contributor
    public static final String DYNAMIC_FUNTION_ITEMS_WORKGROUP_OR_DRIVE_IN_DROPDOWN_MENU = "//ul[contains(@class, 'dropdown-menu open')]//span[string()='%s']";

    // Option file
    public static final String DYNAMIC_ITEMS_OF_FILE_IN_WORKGROUP_OR_DRIVE = "//ul[contains(@class, 'dropdown-submenu open')]//span[text()='%s']";
    public static final String COPY_TO_OPTION_IN_FOLDER_IN_FILE = "//ul[contains(@class, 'dropdown-submenu open')]//a[@class='parent-sub-menu-link']//span[text()='%s']";
    public static final String SHARED_SPACE_OPTION_IN_COPY_ITEM = "//ul[contains(@class, 'dropdown-submenu open')]//a[@class='parent-sub-menu-link']/following-sibling::ul//span[text()='%s']";

    //Pick the destination
    public static final String PICK_DESTINATION_POPUP_COPY = "//md-dialog[@aria-label='Pick the destination']//div[@id='lv-dialog-content-ctn']";
    public static final String COPYHERE_OR_CANCEL_BUTTON = "//md-dialog[@aria-label='Pick the destination']//button[text()='%s']";
    public static final String POPUP_MESSAGE_COPY_OR_MOVE_FILE_SUCCESS = "//div[@class='toast-upper-panel']//span[text()='The file or folder has been copied into : %s.']";
    public static final String FOLDER_AND_FILE_INSIDE_WORKGROUP = "//div[@class='lv-ctn']//p[text()='%s']";
    public static final String TITILE_NAME_FOLDER = "//div[@class='lv-header-dialog']//span[text()=' %s ']";
    public static final String VIEW_BUTTON_IN_MESSAGE_POPUP = "//div[@class='toast-upper-panel']//button[text()='View']";
    public static final String FILE_HIGHLIGHTED_COPY_SUCCESS = "//span[text()=' %s ']/ancestor::tr[contains(@class, 'highlight-list-elem')]";
    public static final String ADD_BUTTON_IN_POPUP = "//div[@id='lv-dialog-content-ctn']//i[@class='zmdi zmdi-plus']";
    public static final String INPUT_FOLDER_NAME_TEXTBOX_IN_POPUP = "//input[@placeholder='Enter your folder name here']";
    public static final String CREATE_FOLDER_TICK_ICON = "//div[@class='lv-actions-container']//i[@class='fa fa-check']";
    public static final String BACK_ICON_IN_POPUP = "//div[@id='lv-dialog-content-ctn']//a[@ng-click='browseVm.loadParentNode()']";
    public static final String SEARCH_ICON_IN_POPUP = "//div[@class='lv-header-dialog']//i[@class='zmdi zmdi-search']";
    public static final String SEARCH_BY_NAME_TEXTBOX_IN_POPUP = "//div[@id='js-filter-workgroup-input']//input[@placeholder='Search by name']";
    public static final String BACK_TITLE_NAME_WORKGROUP = "//div[@class='card-header']//a[@uib-tooltip='%s']";
    // Owner
    public static final String DYNAMIC_RECIPIENT_UPLOADED_FILE_BY_RECIPIENT = "//td[@data-title-text='Name']//span[text()=' %s ']/ancestor::td/following-sibling::td[@data-title-text='Size' and text()=' %s ']";

}
