package is.idega.idegaweb.egov.printing.presentation;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.business.IBORuntimeException;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWPropertyList;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.idegaweb.IWResourceMessage;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.IWContext;
import com.idega.presentation.Image;
import com.idega.presentation.text.DownloadLink;
import com.idega.presentation.text.Link;
import com.idega.presentation.text.Text;
import com.idega.presentation.ui.CheckBox;
import com.idega.presentation.ui.DropdownMenu;
import com.idega.presentation.ui.GenericButton;
import com.idega.presentation.ui.InterfaceObject;
import com.idega.presentation.ui.RadioButton;
import com.idega.presentation.ui.ResetButton;
import com.idega.presentation.ui.SubmitButton;
import com.idega.presentation.ui.TextArea;
import com.idega.presentation.ui.TextInput;
import com.idega.user.business.UserBusiness;
import com.idega.user.business.UserSession;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 * 
 * @author
 * @version 1.0
 */

public class CommuneBlock extends com.idega.presentation.Block {

	protected static String LOCALIZATION_SAVE_KEY = "save";
	protected static String PARAM_SAVE = "cb_save";
	protected static String LOCALIZATION_CANCEL_KEY = "cancel";
	protected static String PARAM_CANCEL = "cb_cancel";
	protected static String LOCALIZATION_EDIT_KEY = "edit";
	protected static String PARAM_EDIT = "cb_edit";
	protected static String LOCALIZATION_DELETE_KEY = "delete";
	protected static String PARAM_DELETE = "cb_delete";
	protected static String LOCALIZATION_COPY_KEY = "copy";
	protected static String PARAM_COPY = "cb_copy";
	protected static String LOCALIZATION_CREATE_KEY = "create";
	protected static String PARAM_CREATE = "cb_create";
	protected static String LOCALIZATION_CLOSE_KEY = "close";
	protected static String PARAM_CLOSE = "cb_close";
	protected static String LOCALIZATION_SUBMIT_KEY = "submit";
	protected static String PARAM_SUBMIT = "cb_submit";
	protected static String LOCALIZATION_RESET_KEY = "reset";

	public final static String IW_BUNDLE_IDENTIFIER = "is.idega.idegaweb.egov.message";

	public final static String STYLENAME_BIG_HEADER = "BigHeader";
	public final static String STYLENAME_HEADER = "Header";
	public final static String STYLENAME_SMALL_HEADER = "SmallHeader";
	public final static String STYLENAME_SMALL_HEADER_LINK = "SmallHeaderLink";
	public final static String STYLENAME_LINK = "Link";
	public final static String STYLENAME_SMALL_LINK = "SmallLink";
	public final static String STYLENAME_LIST_HEADER = "ListHeader";
	public final static String STYLENAME_LIST_TEXT = "ListText";
	public final static String STYLENAME_LIST_LINK = "ListLink";
	public final static String STYLENAME_ERROR_TEXT = "ErrorText";
	public final static String STYLENAME_SMALL_ERROR_TEXT = "SmallErrorText";
	public final static String STYLENAME_INTERFACE = "Interface";
	public final static String STYLENAME_INTERFACE_BUTTON = "InterfaceButton";
	public final static String STYLENAME_CHECKBOX = "CheckBox";
	private final static String DEFAULT_BACKGROUND_COLOR = "#ffffff";
	private final static String DEFAULT_HEADER_COLOR = "#d0daea";
	private final static String DEFAULT_ZEBRA_COLOR_1 = "#ffffff";
	private final static String DEFAULT_ZEBRA_COLOR_2 = "#f4f4f4";
	private final static String DEFAULT_TEXT_FONT_STYLE = "font-weight:plain;";
	private final static String DEFAULT_SMALL_TEXT_FONT_STYLE = "font-style:normal;color:#000000;font-size:10px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:plain;";

	private final static String DEFAULT_HEADER_FONT_STYLE = "font-weight:bold;";
	private final static String DEFAULT_SMALL_HEADER_FONT_STYLE = "font-style:normal;color:#000000;font-size:10px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:bold;";
	private final static String DEFAULT_LINK_FONT_STYLE = "color:#0000cc;";
	private final static String DEFAULT_LIST_HEADER_FONT_STYLE = "font-style:normal;color:#000000;font-size:11px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:bold;";
	private final static String DEFAULT_LIST_FONT_STYLE = "font-style:normal;color:#000000;font-size:11px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:plain;";
	private final static String DEFAULT_LIST_LINK_FONT_STYLE = "font-style:normal;color:#0000cc;font-size:11px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:plain;";
	private final static String DEFAULT_ERROR_TEXT_FONT_STYLE = "font-weight:plain;color:#ff0000;";
	private final static String DEFAULT_SMALL_ERROR_TEXT_FONT_STYLE = "font-style:normal;color:#ff0000;font-size:10px;font-family:Verdana,Arial,Helvetica,sans-serif;font-weight:plain;";

	private String backgroundColor = DEFAULT_BACKGROUND_COLOR;
	private String textFontStyle = DEFAULT_TEXT_FONT_STYLE;
	private String smallTextFontStyle = DEFAULT_SMALL_TEXT_FONT_STYLE;
	private String linkFontStyle = DEFAULT_LINK_FONT_STYLE;
	private String headerFontStyle = DEFAULT_HEADER_FONT_STYLE;
	private String smallHeaderFontStyle = DEFAULT_SMALL_HEADER_FONT_STYLE;
	private String listHeaderFontStyle = DEFAULT_LIST_HEADER_FONT_STYLE;
	private String listFontStyle = DEFAULT_LIST_FONT_STYLE;
	private String listLinkFontStyle = DEFAULT_LIST_LINK_FONT_STYLE;
	private String errorTextFontStyle = DEFAULT_ERROR_TEXT_FONT_STYLE;
	private String smallErrorTextFontStyle = DEFAULT_SMALL_ERROR_TEXT_FONT_STYLE;

	private final static String HEADER_COLOR_PROPERTY = "header_color";
	private final static String ZEBRA_COLOR1_PROPERTY = "zebra_color_1";
	private final static String ZEBRA_COLOR2_PROPERTY = "zebra_color_2";
	private final static String CELLPADDING_PROPERTY = "cellpadding";
	private final static String CELLSPACING_PROPERTY = "cellspacing";

	private IWResourceBundle iwrb = null;
	private IWBundle iwb = null;
	private boolean iUseStyleNames;

	public String getBundleIdentifier() {
		return IW_BUNDLE_IDENTIFIER;
	}

	public void setResourceBundle(IWResourceBundle iwrb) {
		this.iwrb = iwrb;
	}

	public void setBundle(IWBundle iwb) {
		this.iwb = iwb;
	}

	public void _main(IWContext iwc) throws Exception {
		this.setResourceBundle(getResourceBundle(iwc));
		setBundle(getBundle(iwc));
		this.iUseStyleNames = new Boolean(this.iwb.getProperty("layout.use_style_names", "false")).booleanValue();
		super._main(iwc);
	}

	public IWResourceBundle getResourceBundle() {
		return this.iwrb;
	}

	public IWBundle getBundle() {
		return this.iwb;
	}

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public String getTextFontStyle() {
		return this.textFontStyle;
	}

	public String getSmallTextFontStyle() {
		return this.smallTextFontStyle;
	}

	public String getLinkFontStyle() {
		return this.linkFontStyle;
	}

	public String getHeaderFontStyle() {
		return this.headerFontStyle;
	}

	public String getSmallHeaderFontStyle() {
		return this.smallHeaderFontStyle;
	}

	public String getListHeaderFontStyle() {
		return this.listHeaderFontStyle;
	}

	public String getListFontStyle() {
		return this.listFontStyle;
	}

	public String getListLinkFontStyle() {
		return this.listLinkFontStyle;
	}

	public String getErrorTextFontStyle() {
		return this.errorTextFontStyle;
	}

	public String getSmallErrorTextFontStyle() {
		return this.smallErrorTextFontStyle;
	}

	public void setBackroundColor(String color) {
		this.backgroundColor = color;
	}

	public void setTextFontStyle(String fontStyle) {
		this.textFontStyle = fontStyle;
	}

	public void setSmallTextFontStyle(String fontStyle) {
		this.smallTextFontStyle = fontStyle;
	}

	public void setLinkFontStyle(String fontStyle) {
		this.linkFontStyle = fontStyle;
	}

	public void setHeaderFontStyle(String fontStyle) {
		this.headerFontStyle = fontStyle;
	}

	public void setSmallHeaderFontStyle(String fontStyle) {
		this.smallHeaderFontStyle = fontStyle;
	}

	public void setListHeaderFontStyle(String fontStyle) {
		this.listHeaderFontStyle = fontStyle;
	}

	public void setListFontStyle(String fontStyle) {
		this.listFontStyle = fontStyle;
	}

	public void setListLinkFontStyle(String fontStyle) {
		this.listLinkFontStyle = fontStyle;
	}

	public void setErrorTextFontStyle(String fontStyle) {
		this.errorTextFontStyle = fontStyle;
	}

	public void setSmallErrorTextFontStyle(String fontStyle) {
		this.smallErrorTextFontStyle = fontStyle;
	}

	public String localize(String textKey, String defaultText) {
		if (this.iwrb == null) {
			return defaultText;
		}
		return this.iwrb.getLocalizedString(textKey, defaultText);
	}

	public String localize(IWResourceMessage message) {
		return localize(message.getKey(), message.getMessage());
	}

	/**
	 * Method localize.
	 * 
	 * @param text
	 *          text[0] is key, text[1] is default value.
	 * @return String The locale text
	 */
	public String localize(String[] text) {
		return localize(text[0], text[1]);
	}

	public Text getHeader(String s) {
		return getStyleText(s, STYLENAME_HEADER);
	}

	public Text getLocalizedHeader(String s, String d) {
		return getHeader(localize(s, d));
	}

	public Text getSmallHeader(String s) {
		return getStyleText(s, STYLENAME_SMALL_HEADER);
	}

	public Link getSmallHeaderLink(String s) {
		return getStyleLink(new Link(s), STYLENAME_SMALL_HEADER_LINK);
	}

	public Text getLocalizedSmallHeader(String s, String d) {
		return getSmallHeader(localize(s, d));
	}

	public Link getLocalizedSmallHeaderLink(String s, String d) {
		return getSmallHeaderLink(localize(s, d));
	}

	public Link getLink(String s) {
		return getStyleLink(new Link(s), STYLENAME_LINK);
	}

	public Link getSmallLink(String link) {
		return getStyleLink(new Link(link), STYLENAME_SMALL_LINK);
	}

	public Link getLocalizedLink(String s, String d) {
		return getLink(localize(s, d));
	}

	public Text getErrorText(String s) {
		return getStyleText(s, STYLENAME_ERROR_TEXT);
	}

	public Text getBigHeader(String s) {
		return getStyleText(s, STYLENAME_BIG_HEADER);
	}

	public Text getSmallErrorText(String s) {
		return getStyleText(s, STYLENAME_SMALL_ERROR_TEXT);
	}

	public InterfaceObject getStyledInterface(InterfaceObject obj) {
		return (InterfaceObject) setStyle(obj, STYLENAME_INTERFACE);
	}

	public boolean useStyleNames() {
		return this.iUseStyleNames;
	}

	public String getHeaderColor() {
		return getProperty(HEADER_COLOR_PROPERTY, DEFAULT_HEADER_COLOR);
	}

	public String getZebraColor1() {
		return getProperty(ZEBRA_COLOR1_PROPERTY, DEFAULT_ZEBRA_COLOR_1);
	}

	public String getZebraColor2() {
		return getProperty(ZEBRA_COLOR2_PROPERTY, DEFAULT_ZEBRA_COLOR_2);
	}

	protected int getCellpadding() {
		return Integer.parseInt(getProperty(CELLPADDING_PROPERTY, "2"));
	}

	protected int getCellspacing() {
		return Integer.parseInt(getProperty(CELLSPACING_PROPERTY, "2"));
	}

	private String getProperty(String propertyName, String nullValue) {
		IWPropertyList property = getIWApplicationContext().getSystemProperties().getProperties("layout_settings");
		if (property != null) {
			String propertyValue = property.getProperty(propertyName);
			if (propertyValue != null) {
				return propertyValue;
			}
		}
		return nullValue;
	}

	protected CheckBox getCheckBox(String name, String value) {
		return (CheckBox) setStyle(new CheckBox(name, value), STYLENAME_CHECKBOX);
	}

	protected RadioButton getRadioButton(String name, String value) {
		return (RadioButton) setStyle(new RadioButton(name, value), STYLENAME_CHECKBOX);
	}

	protected GenericButton getButton(GenericButton button) {
		// temporary, will be moved to IWStyleManager for handling...
		button.setHeight("20");
		return (GenericButton) setStyle(button, STYLENAME_INTERFACE_BUTTON);
	}

	protected GenericButton getSaveButton() {
		return getSaveButton(PARAM_SAVE);
	}

	protected GenericButton getSaveButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_SAVE_KEY, "Save")));
		return button;
	}

	protected GenericButton getCancelButton() {
		return getCancelButton(PARAM_CANCEL);
	}

	protected GenericButton getCancelButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_CANCEL_KEY, "Cancel")));
		return button;
	}

	protected GenericButton getEditButton() {
		return getEditButton(PARAM_EDIT);
	}

	protected GenericButton getEditButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_EDIT_KEY, "Edit")));
		return button;
	}

	protected GenericButton getDeleteButton() {
		return getDeleteButton(PARAM_DELETE);
	}

	protected GenericButton getDeleteButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_DELETE_KEY, "Delete")));
		return button;
	}

	protected GenericButton getCopyButton() {
		return getCopyButton(PARAM_COPY);
	}

	protected GenericButton getCopyButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_COPY_KEY, "Copy")));
		return button;
	}

	protected GenericButton getCreateButton() {
		return getCreateButton(PARAM_CREATE);
	}

	protected GenericButton getCreateButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_CREATE_KEY, "Create")));
		return button;
	}

	protected GenericButton getSubmitButton() {
		return getSubmitButton(PARAM_SUBMIT);
	}

	protected GenericButton getSubmitButton(String parameterName) {
		GenericButton button = getSubmitButton2(parameterName, null);
		return button;
	}

	// TODO: Rename this method getSubmitButton!
	protected GenericButton getSubmitButton2(String parameterName, String parameterValue) {
		GenericButton button = null;
		if (parameterValue == null) {
			button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_SUBMIT_KEY, "Submit")));
		}
		else {
			button = getButton(new SubmitButton(localize(LOCALIZATION_SUBMIT_KEY, "Submit"), parameterName, parameterValue));
		}
		return button;
	}

	protected GenericButton getResetButton() {
		GenericButton button = getButton(new ResetButton(localize(LOCALIZATION_RESET_KEY, "Reset")));
		return button;
	}

	protected GenericButton getCloseButton() {
		return getCloseButton(PARAM_CLOSE);
	}

	protected GenericButton getCloseButton(String parameterName) {
		GenericButton button = getButton(new SubmitButton(parameterName, localize(LOCALIZATION_CLOSE_KEY, "Close")));
		return button;
	}

	/**
	 * Returns the default edit icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The edit icon.
	 */
	protected Image getEditIcon(String toolTip) {
		Image editImage = this.iwb.getImage("shared/edit.gif", 12, 12);
		editImage.setToolTip(toolTip);
		return editImage;
	}

	/**
	 * Returns the default delete icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The delete icon.
	 */
	protected Image getDeleteIcon(String toolTip) {
		Image deleteImage = this.iwb.getImage("shared/delete.gif", 12, 12);
		deleteImage.setToolTip(toolTip);
		return deleteImage;
	}

	/**
	 * Returns a PDF icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The PDF icon.
	 */
	protected Image getPDFIcon(String toolTip) {
		Image pdfImage = this.iwb.getImage("shared/pdf-small.gif", 12, 12);
		pdfImage.setToolTip(toolTip);
		return pdfImage;
	}

	/**
	 * Retuns a download link to a file, with a pdf icon
	 * 
	 * @param fileID
	 * @param tooltip
	 * @return
	 */
	protected DownloadLink getPDFLink(int fileID, String tooltip) {
		DownloadLink link = new DownloadLink(fileID);
		link.setPresentationObject(getPDFIcon(tooltip));
		return link;
	}

	/**
	 * Returns a copy icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The copy icon.
	 */
	protected Image getCopyIcon(String toolTip) {
		Image copyImage = this.iwb.getImage("shared/copy.gif", 12, 12);
		copyImage.setToolTip(toolTip);
		return copyImage;
	}

	/**
	 * Returns a question icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The question icon.
	 */
	protected Image getQuestionIcon(String toolTip) {
		Image questionImage = this.iwb.getImage("shared/question.gif", 12, 12);
		questionImage.setToolTip(toolTip);
		return questionImage;
	}

	/**
	 * Returns an information icon with the tooltip specified.
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The information icon.
	 */
	protected Image getInformationIcon(String toolTip) {
		Image informationImage = this.iwb.getImage("shared/info.gif", 12, 12);
		informationImage.setToolTip(toolTip);
		return informationImage;
	}

	/**
	 * Returns the default various icon with the tooltip specified. May be used for various purposes (handle, go, whatever...)
	 * 
	 * @param toolTip
	 *          The tooltip to display on mouse over.
	 * @return Image The various icon.
	 */
	protected Image getVariousIcon(String toolTip) {
		return getEditIcon(toolTip);
	}

	protected UserBusiness getUserBusiness(IWApplicationContext iwc) throws IBOLookupException {
		return (UserBusiness) IBOLookup.getServiceInstance(iwc, UserBusiness.class);
	}

	protected UserSession getUserSession(IWUserContext iwuc) {
		try {
			return (UserSession) IBOLookup.getSessionInstance(iwuc, UserSession.class);
		}
		catch (IBOLookupException ile) {
			throw new IBORuntimeException(ile);
		}
	}

	protected DropdownMenu getDropdown(String parameterName, Object selectedElement) {
		DropdownMenu drop = (DropdownMenu) getStyledInterface(new DropdownMenu(parameterName));
		drop.setWidth("100%");
		if (selectedElement != null) {
			drop.setSelectedElement(selectedElement.toString());
		}

		return drop;
	}

	protected TextInput getTextInput(String parameterName, Object content) {
		return getTextInput(parameterName, content, false);
	}

	protected TextInput getTextInput(String parameterName, Object content, boolean disabled) {
		TextInput input = (TextInput) getStyledInterface(new TextInput(parameterName));
		input.setWidth("100%");
		if (content != null) {
			input.setContent(content.toString());
		}
		input.setDisabled(disabled);

		return input;
	}

	protected TextArea getTextArea(String parameterName, Object content) {
		TextArea area = (TextArea) getStyledInterface(new TextArea(parameterName));
		area.setWidth("100%");
		if (content != null) {
			area.setContent(content.toString());
		}

		return area;
	}
}