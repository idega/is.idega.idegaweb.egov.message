/*
 * $Id$
 *
 * Copyright (C) 2002 Idega hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 *
 */
package is.idega.idegaweb.egov.message.business;

import is.idega.idegaweb.egov.message.data.MessageHandlerInfo;
import is.idega.idegaweb.egov.message.data.MessageHandlerInfoHome;
import is.idega.idegaweb.egov.message.data.MessageReceiver;
import is.idega.idegaweb.egov.message.data.MessageReceiverHome;
import is.idega.idegaweb.egov.message.data.PrintMessage;
import is.idega.idegaweb.egov.message.data.PrintedLetterMessage;
import is.idega.idegaweb.egov.message.data.PrintedLetterMessageHome;
import is.idega.idegaweb.egov.message.data.UserMessage;
import is.idega.idegaweb.egov.message.data.UserMessageHome;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.mail.MessagingException;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;
import com.idega.block.process.message.business.MessageBusiness;
import com.idega.block.process.message.business.MessageBusinessBean;
import com.idega.block.process.message.business.MessageTypeManager;
import com.idega.block.process.message.data.Message;
import com.idega.business.IBOLookupException;
import com.idega.business.IBORuntimeException;
import com.idega.core.component.data.ICObject;
import com.idega.core.contact.data.Email;
import com.idega.core.file.data.ICFile;
import com.idega.core.messaging.MessagingSettings;
import com.idega.data.IDOCreateException;
import com.idega.data.IDOException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.data.IDOStoreException;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWMainApplicationSettings;
import com.idega.idegaweb.IWPropertyList;
import com.idega.idegaweb.IWUserContext;
import com.idega.user.business.NoEmailFoundException;
import com.idega.user.business.UserBusiness;
import com.idega.user.business.UserProperties;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;
import com.idega.util.SendMail;

/**
 * @author Anders Lindman , <a href="mailto:tryggvi@idega.is">Tryggvi Larusson</a>
 * @version 1.0
 */
public class CommuneMessageBusinessBean extends MessageBusinessBean implements CommuneMessageBusiness, MessageBusiness {

	private final static String IW_BUNDLE_IDENTIFIER = "is.idega.idegaweb.egov.message";
	public static final String MESSAGE_PROPERTIES = "message_properties";
	public static final String MAIL_PROPERTIES = "mail_properties";

	public static final String USER_PROP_SEND_TO_MESSAGE_BOX = "msg_send_box";
	public static final String USER_PROP_SEND_TO_EMAIL = "msg_send_email";

	public CommuneMessageBusinessBean() {
		MessageTypeManager typeManager = MessageTypeManager.getInstance();
		typeManager.addDataClassForType(MessageConstants.TYPE_USER_MESSAGE, UserMessage.class);
		// typeManager.addDataClassForType(MessageConstants.TYPE_SYSTEM_PRINT_MAIL_MESSAGE, PrintedLetterMessage.class);
	}

	private UserMessageHome getUserMessageHome() throws RemoteException {
		return (UserMessageHome) this.getIDOHome(UserMessage.class);
	}

	protected PrintedLetterMessageHome getPrintedLetterMessageHome() {
		try {
			return (PrintedLetterMessageHome) this.getIDOHome(PrintedLetterMessage.class);
		}
		catch (RemoteException rme) {
			throw new IBORuntimeException(rme);
		}
	}

	@Override
	public void deleteUserMessage(int messageID) {
		try {
			Message message = getUserMessage(messageID);
			changeCaseStatus(message, getCaseStatusInactive().getPrimaryKey().toString(), message.getOwner());
		}
		catch (FinderException fe) {
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private String getTypeUserMessage() {
		return MessageConstants.TYPE_USER_MESSAGE;
	}

	private String getTypeMailMessage() {
		return MessageConstants.TYPE_SYSTEM_PRINT_MAIL_MESSAGE;
	}

	@Override
	public CaseCode getCaseCodeUserMessage() throws RemoteException, FinderException {
		return getCaseBusiness().getCaseCode(getTypeUserMessage());
	}

	@Override
	public CaseCode getCaseCodePrintedLetterMessage() throws RemoteException, FinderException {
		return getCaseBusiness().getCaseCode(getTypeMailMessage());
	}

	protected CaseBusiness getCaseBusiness() {
		return this;
	}

	@Override
	public Message getUserMessage(int messageId) throws FinderException, RemoteException {
		return getMessage(getTypeUserMessage(), new Integer(messageId));
	}

	@Override
	public int getNumberOfMessages(User user) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().getNumberOfMessages(user, validStatuses);
	}

	@Override
	public int getNumberOfNewMessages(User user) throws IDOException {
		try {
			String[] validStatuses = { getCaseStatusOpen().getStatus() };
			return getUserMessageHome().getNumberOfMessages(user, validStatuses);
		}
		catch (RemoteException re) {
			throw new IBORuntimeException(re);
		}
	}

	@Override
	public int getNumberOfMessages(User user, Collection groups) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().getNumberOfMessages(user, groups, validStatuses);
	}

	@Override
	public Collection findMessages(User user) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().findMessages(user, validStatuses);
	}

	@Override
	public Collection findMessages(User user, int numberOfEntries, int startingEntry) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().findMessages(user, validStatuses, numberOfEntries, startingEntry);
	}

	@Override
	public Collection findMessages(User user, Collection groups, int numberOfEntries, int startingEntry) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().findMessages(user, groups, validStatuses, numberOfEntries, startingEntry);
	}

	@Override
	public Collection findMessages(Group group) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().findMessages(group, validStatuses);
	}

	@Override
	public Collection findMessages(Group group, int numberOfEntries, int startingEntry) throws Exception {
		String[] validStatuses = { getCaseStatusOpen().getStatus(), getCaseStatusGranted().getStatus() };
		return getUserMessageHome().findMessages(group, validStatuses, numberOfEntries, startingEntry);
	}

	@Override
	public Message createUserMessage(User user, String subject, String body) {
		return createUserMessage(null, user, subject, body, true);
	}

	@Override
	public Message createUserMessage(User user, String subject, String body, boolean sendLetter) {
		return createUserMessage(null, user, subject, body, sendLetter);
	}

	@Override
	public Message createUserMessage(User user, String subject, Group handler, String body, boolean sendLetter) {
		return createUserMessage(null, user, null, handler, subject, body, sendLetter);
	}

	@Override
	public Message createUserMessage(User user, String subject, Group handler, String body, boolean sendLetter, String contentCode) {
		return createUserMessage(null, user, null, handler, subject, body, sendLetter, contentCode, false);
	}

	@Override
	public Message createUserMessage(User receiver, String subject, String body, User sender, boolean sendLetter) {
		return createUserMessage(null, receiver, sender, subject, body, sendLetter);
	}

	/**
	 * note: this method overwrites a method in MessageBusinessBean
	 */
	@Override
	public Message createUserMessage(Case parentCase, User receiver, String subject, String body, boolean sendLetter) {
		return createUserMessage(parentCase, receiver, null, subject, body, sendLetter);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, String subject, String body, boolean sendLetter, boolean alwaysSendLetter) {
		return createUserMessage(parentCase, receiver, null, null, subject, body, sendLetter, null, alwaysSendLetter);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, String subject, String body, String letterBody, boolean sendLetter, boolean alwaysSendLetter) {
		return createUserMessage(parentCase, receiver, null, null, subject, body, letterBody, sendLetter, null, alwaysSendLetter);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, String subject, String body, String letterBody, File attachment, boolean sendLetter, boolean alwaysSendLetter) {
		return createUserMessage(parentCase, receiver, null, null, subject, body, letterBody, attachment, sendLetter, null, alwaysSendLetter, true);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, String subject, String body, boolean sendLetter) {
		return createUserMessage(parentCase, receiver, sender, null, subject, body, sendLetter);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, boolean sendLetter) {
		return createUserMessage(parentCase, receiver, sender, handler, subject, body, sendLetter, null, false);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, boolean pSendLetterIfNoEmail, String contentCode) {
		return createUserMessage(parentCase, receiver, sender, handler, subject, body, pSendLetterIfNoEmail, contentCode, false);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, boolean pSendLetterIfNoEmail, String contentCode, boolean alwaysSendLetter) {
		return createUserMessage(parentCase, receiver, sender, handler, subject, body, body, pSendLetterIfNoEmail, contentCode, alwaysSendLetter);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, String letterBody, boolean pSendLetterIfNoEmail, String contentCode, boolean alwaysSendLetter) {
		return createUserMessage(parentCase, receiver, sender, handler, subject, body, letterBody, pSendLetterIfNoEmail, contentCode, alwaysSendLetter, true);
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, String letterBody, boolean sendLetterIfNoEmail, String contentCode, boolean alwaysSendLetter, boolean sendMail) {
		return createUserMessage(parentCase, receiver, sender, handler, subject, body, letterBody, null, sendLetterIfNoEmail, contentCode, alwaysSendLetter, sendMail);
	}

	@Override
	public MessageValue createUserMessageValue(Case parentCase, User receiver, User sender, Group handler, String subject, String body, String letterBody, File attachment, boolean sendLetterIfNoEmail, String contentCode, boolean alwaysSendLetter, boolean sendMail) {
		MessageValue value = new MessageValue();
		setSimpleMessage(value, parentCase, receiver, subject, body);
		value.setHandler(handler);
		value.setLetterBody(letterBody);
		value.setSender(sender);
		value.setSendLetterIfNoEmail(new Boolean(sendLetterIfNoEmail));
		value.setContentCode(contentCode);
		value.setAlwaysSendLetter(new Boolean(alwaysSendLetter));
		value.setSendMail(new Boolean(sendMail));
		value.setAttachment(attachment);
		return value;
	}

	@Override
	public Message createUserMessage(Case parentCase, User receiver, User sender, Group handler, String subject, String body, String letterBody, File attachment, boolean sendLetterIfNoEmail, String contentCode, boolean alwaysSendLetter, boolean sendMail) {

		MessageValue mv = createUserMessageValue(parentCase, receiver, sender, handler, subject, body, letterBody, attachment, sendLetterIfNoEmail, contentCode, alwaysSendLetter, sendMail);
		return createUserMessage(mv);
	}

	@Override
	public Message createUserMessage(MessageValue msgValue) {

		try {
			if (msgValue.getLetterBody() == null) {
				msgValue.setLetterBody(msgValue.getBody());
			}
			Message message = null;
			boolean sendMail = getIfUserPreferesMessageByEmail(msgValue.getReceiver()) && msgValue.getSendMail().booleanValue();
			boolean sendToBox = getIfUserPreferesMessageInMessageBox(msgValue.getReceiver());
			boolean canSendEmail = getIfCanSendEmail();
			boolean sendLetterEvenWhenHavingEmail = getIfCreateLetterMessageHavingEmail();
			// By default: copies in-parameter value:
			// boolean doSendLetter=msgValue.sendLetterIfNoEmail.booleanValue();
			boolean doSendLetter = msgValue.getAlwaysSendLetter().booleanValue() | sendLetterEvenWhenHavingEmail;

			// send to box
			if (sendToBox) {
				msgValue.setMessageType(getTypeUserMessage());
				message = createMessage(msgValue);
				message.setContentCode(msgValue.getContentCode());
				message.store();
			}

			// send as email
			if (canSendEmail) {
				if (sendMail) {
					if (!sendEmail(msgValue)) {
						// failed to send this email
						doSendLetter |= msgValue.getSendLetterIfNoEmail().booleanValue();
					}
				}
			}
			else {
				// can not send emails at all
				doSendLetter |= msgValue.getSendLetterIfNoEmail().booleanValue();
			}

			//
			// !!! thomas says: I think there is a bug above: Should not a letter be send if both sentToBox and sendMail are set to false?
			//

			// else {
			// if (pSendLetterIfNoEmail)
			// createPrintedLetterMessage(parentCase, receiver, subject, body,null,contentCode);
			// }
			// else {
			// if (pSendLetterIfNoEmail)
			// createPrintedLetterMessage(parentCase, receiver, subject, body,null,contentCode);
			// }

			// send as letter
			if (doSendLetter) {
				createPrintedLetterMessage(msgValue);
			}

			// debug
			if (IWMainApplication.isDebugActive()) {
				System.out.println("[MessageBusiness] Creating user message with subject:" + msgValue.getSubject());
				System.out.println("[MessageBusiness] Body: " + msgValue.getBody());
				if (msgValue.getParentCase() != null) {
					debug("[MessageBusiness] Parent case:" + msgValue.getParentCase().getClass().getName() + " (" + msgValue.getParentCase().getPrimaryKey().toString() + ")");
				}
				System.out.println("[MessageBusiness] Receiver: " + msgValue.getReceiver().getName() + " (" + msgValue.getReceiver().getPrimaryKey().toString() + ")");
				if (msgValue.getSender() != null) {
					debug("[MessageBusiness] Sender: " + msgValue.getSender().getName() + " (" + msgValue.getSender().getPrimaryKey().toString() + ")");
				}
				if (msgValue.getHandler() != null) {
					debug("[MessageBusiness] Handler: " + msgValue.getHandler().getName() + " (" + msgValue.getHandler().getPrimaryKey().toString() + ")");
				}
			}
			return message;
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

	private boolean sendEmail(MessageValue msgValue) throws IBOLookupException, RemoteException {
		try {
			Email mail = ((UserBusiness) getServiceInstance(UserBusiness.class)).getUsersMainEmail(msgValue.getReceiver());
			if (mail != null) {
				String emailAddress = mail.getEmailAddress();
				if (emailAddress != null) {
					try {
						sendMessage(emailAddress, msgValue.getSubject(), msgValue.getBody(), msgValue.getAttachment());
						return true;
					}
					catch (Exception ex) {
						System.err.println("Couldn't send message to user via e-mail.");
					}
				}
			}
		}
		catch (NoEmailFoundException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	/**
	 * This property is for setting if to create letter messages even when the
	 * user has an email address.
	 *
	 * @return value of the set property. Default is false.
	 */
	protected boolean getIfCreateLetterMessageHavingEmail() {
		return getBundle().getBooleanProperty("create_letter_message_having_email", false);
	}

	@Override
	public Message createUserMessage(int userID, String subject, String body) throws CreateException {
		User user;
		try {
			user = getUser(userID);
		}
		catch (FinderException fex) {
			throw new IDOCreateException(fex);
		}

		return createUserMessage(user, subject, body);
	}

	/**
	 * @return Collection of PrintedLetterMessage that have already been printed
	 */
	@Override
	public Collection getPrintedLetterMessages() throws FinderException {
		return getPrintedLetterMessageHome().findAllPrintedLetters();
	}

	/**
	 * @return Collection of PrintedLetterMessage that have already been printed
	 */
	@Override
	public Collection getPrintedLetterMessagesByType(String type, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findPrintedLettersByType(type, resultSize, startingIndex);
	}

	/**
	 * @return Collection of PrintedLetterMessage that have already been printed,
	 *         created between dates
	 */
	@Override
	public Collection getPrintedLetterMessagesByType(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findPrintedLettersByType(type, from, to, resultSize, startingIndex);
	}

	@Override
	public Collection getSinglePrintedLetterMessagesByType(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findSinglePrintedLettersByType(type, from, to, resultSize, startingIndex);
	}

	/**
	 * @return Collection of PrintedLetterMessage that have not been printed
	 */
	@Override
	public Collection getUnPrintedLetterMessages() throws FinderException {
		return getPrintedLetterMessageHome().findAllUnPrintedLetters();
	}

	/**
	 * @return Collection of PrintedLetterMessage that have not been printed
	 */
	@Override
	public Collection getUnPrintedLetterMessagesByType(String type, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findUnPrintedLettersByType(type, resultSize, startingIndex);
	}

	/**
	 * @return Collection of PrintedLetterMessage that have not been printed
	 */
	@Override
	public Collection getUnPrintedLetterMessagesByType(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findUnPrintedLettersByType(type, from, to, resultSize, startingIndex);
	}

	@Override
	public Collection getSingleUnPrintedLetterMessagesByType(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findSingleUnPrintedLettersByType(type, from, to, resultSize, startingIndex);
	}

	@Override
	public Collection getSingleLettersByTypeAndStatus(String type, String status, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findSingleByTypeAndStatus(type, status, from, to, resultSize, startingIndex);
	}

	@Override
	public Collection getLettersByBulkFile(int file, String type, String status, int resultSize, int startingIndex) throws FinderException {
		return getPrintedLetterMessageHome().findByBulkFile(file, type, status, resultSize, startingIndex);
	}

	/**
	 * Mark the status of the message so that it is printed.
	 *
	 * @param performer
	 *          The User that makes the change
	 * @param message
	 *          the message to be marked
	 */
	@Override
	public void flagPrintedLetterAsPrinted(User performer, PrintedLetterMessage message) {
		String newCaseStatus = getCaseStatusReady().getStatus();
		super.changeCaseStatus(message, newCaseStatus, performer);
	}

	@Override
	public void flagMessageAsPrinted(User performer, Message message) {
		String newCaseStatus = getCaseStatusReady().getStatus();
		super.changeCaseStatus(message, newCaseStatus, performer);
	}

	@Override
	public void flagMessageAsUnPrinted(User performer, Message message) {
		String newCaseStatus = getCaseStatusOpen().getStatus();
		super.changeCaseStatus(message, newCaseStatus, performer);
	}

	@Override
	public void flagMessageWithStatus(User performer, Message message, String status) {
		super.changeCaseStatus(message, status, performer);
	}

	@Override
	public void flagMessagesWithStatus(User performer, String[] msgKeys, String status) throws FinderException {
		for (int i = 0; i < msgKeys.length; i++) {
			super.changeCaseStatus(Integer.parseInt(msgKeys[i]), status, performer);
		}
	}

	@Override
	public Message createPrintArchivationMessage(User user, String subject, String body) throws CreateException, RemoteException {
		MessageValue msgValue = new MessageValue();
		msgValue.setReceiver(user);
		msgValue.setSubject(subject);
		msgValue.setBody(body);
		Message message = createMessage(msgValue);
		return message;
	}

	@Override
	public Message createPrintArchivationMessage(int userID, String subject, String body) throws CreateException, RemoteException {
		User user;
		try {
			user = getUser(userID);
		}
		catch (FinderException fex) {
			throw new IDOCreateException(fex);
		}

		return createPrintArchivationMessage(user, subject, body);
	}

	@Override
	public PrintedLetterMessage createPrintedPasswordLetterMessage(User user, String subject, String body) throws CreateException {
		PrintedLetterMessageHome home = getPrintedLetterMessageHome();
		PrintedLetterMessage message = (PrintedLetterMessage) home.create();
		message.setOwner(user);
		message.setSubject(subject);
		message.setBody(body);
		message.setAsPasswordLetter();
		try {
			message.store();
		}
		catch (IDOStoreException idos) {
			throw new IDOCreateException(idos);
		}
		return message;
	}

	@Override
	public PrintedLetterMessage createPasswordMessage(User user, String username, String password) throws CreateException {
		PrintedLetterMessageHome home = getPrintedLetterMessageHome();
		PrintedLetterMessage message = (PrintedLetterMessage) home.create();
		message.setOwner(user);
		message.setSubject("Username and Password");
		message.setBody(username + "|" + password);
		message.setAsPasswordLetter();
		try {
			message.store();
		}
		catch (IDOStoreException idos) {
			throw new IDOCreateException(idos);
		}
		return message;
	}

	private PrintedLetterMessage createPrintedLetterMessage(MessageValue msgValue) throws CreateException {
		PrintedLetterMessageHome home = getPrintedLetterMessageHome();
		PrintedLetterMessage message = (PrintedLetterMessage) home.create();
		message.setOwner(msgValue.getReceiver());
		message.setSubject(msgValue.getSubject());
		message.setBody(msgValue.getBody());
		if (msgValue.getParentCase() != null) {
			message.setParentCase(msgValue.getParentCase());
		}
		if (msgValue.getPrintedLetterType() != null) {
			message.setLetterType(msgValue.getPrintedLetterType());
		}
		if (msgValue.getContentCode() != null) {
			message.setContentCode(msgValue.getContentCode());
		}
		try {
			message.store();
		}
		catch (IDOStoreException idos) {
			throw new IDOCreateException(idos);
		}
		return message;
	}

	@Override
	public Message createPrintedLetterMessage(int userID, String subject, String body) throws CreateException {
		try {
			MessageValue msgValue = new MessageValue();
			msgValue.setReceiver(getUser(userID));
			msgValue.setSubject(subject);
			msgValue.setBody(body);
			return createPrintedLetterMessage(msgValue);
		}
		catch (FinderException e) {
			throw new IDOCreateException(e);
		}
	}

	@Override
	public void sendMessage(String email, String subject, String body) {
		sendMessage(email, subject, body, null);
	}

	@Override
	public void sendMessage(String email, String subject, String body, File attachment) {
		String receiver = email.trim();
		String mailServer = MessagingSettings.DEFAULT_SMTP_MAILSERVER;
		String fromAddress = MessagingSettings.DEFAULT_MESSAGEBOX_FROM_ADDRESS;
		String forcedToAddress = null;
		String bccReceiver = null;
		try {
			mailServer = getPropertyValue(MessagingSettings.PROP_SYSTEM_SMTP_MAILSERVER, MessagingSettings.DEFAULT_SMTP_MAILSERVER);
			fromAddress = getPropertyValue(MessagingSettings.PROP_MESSAGEBOX_FROM_ADDRESS, MessagingSettings.DEFAULT_MESSAGEBOX_FROM_ADDRESS);
			forcedToAddress = getPropertyValue(MessagingSettings.PROP_SYSTEM_FORCED_RECEIVER, "notset");
			bccReceiver = getPropertyValue(MessagingSettings.PROP_SYSTEM_BCC_RECEIVER, "notset");
		}
		catch (Exception e) {
			System.err.println("MessageBusinessBean: Error getting mail property from bundle");
			e.printStackTrace();
		}

		if (forcedToAddress != null && !"notset".equals(forcedToAddress)) {
			receiver = forcedToAddress;
		}

		if ("notset".equals(bccReceiver)) {
			bccReceiver = null;
		}

		if (IWMainApplication.isDebugActive()) {
			System.out.println("sending message in commune message business");
			System.out.println("mailServer: " + mailServer);
			System.out.println("fromAddress: " + fromAddress);
			System.out.println("receiver: " + receiver);
			System.out.println("subject: " + subject);
		}

		try {
			SendMail.send(fromAddress, receiver, "", bccReceiver, mailServer, subject, body, attachment);
		} catch (MessagingException me) {
			System.err.println("Error sending mail to address: " + email + " Message was: " + me.getMessage());
		}
	}

	protected UserProperties getUserPreferences(User user) throws Exception {
		UserProperties property = getUserBusiness().getUserProperties(user);
		return property;
	}

	protected IWPropertyList getUserMessagePreferences(User user) {
		try {
			IWPropertyList messageProperties = getUserPreferences(user).getProperties(MESSAGE_PROPERTIES);
			return messageProperties;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean getIfUserPreferesMessageByEmail(User user) {
		MessageReceiver receiver = getMessageReceiver(user);
		if (receiver != null) {
			return receiver.receiveEmails();
		}
		else {
			IWPropertyList propertyList = getUserMessagePreferences(user);

			if (propertyList != null) {
				String property = propertyList.getProperty(USER_PROP_SEND_TO_EMAIL);
				if (property != null) {
					return Boolean.valueOf(property).booleanValue();
				}
			}
		}
		return true;
	}

	@Override
	public boolean getIfUserPreferesMessageInMessageBox(User user) {
		MessageReceiver receiver = getMessageReceiver(user);
		if (receiver != null) {
			return receiver.receiveMessagesToBox();
		}
		else {
			IWPropertyList propertyList = getUserMessagePreferences(user);
			if (propertyList != null) {
				String property = propertyList.getProperty(USER_PROP_SEND_TO_MESSAGE_BOX);
				if (property != null) {
					return Boolean.valueOf(property).booleanValue();
				}
			}
		}
		return true;
	}

	@Override
	public boolean getIfCanSendEmail() {
		return Boolean.valueOf(getPropertyValue(MessageConstants.CAN_SEND_EMAIL, Boolean.FALSE.toString())).booleanValue();
	}

	@Override
	public void setIfUserPreferesMessageByEmail(User user, boolean preference) {
		MessageReceiver receiver = getMessageReceiver(user);
		if (receiver != null) {
			receiver.setReceiveEmails(preference);
			receiver.store();
		}
		else {
			IWPropertyList propertyList = getUserMessagePreferences(user);
			propertyList.setProperty(USER_PROP_SEND_TO_EMAIL, new Boolean(preference));
		}
	}

	@Override
	public void setIfUserPreferesMessageInMessageBox(User user, boolean preference) {
		MessageReceiver receiver = getMessageReceiver(user);
		if (receiver != null) {
			receiver.setReceiveMessagesToBox(preference);
			receiver.store();
		}
		else {
			IWPropertyList propertyList = getUserMessagePreferences(user);
			propertyList.setProperty(USER_PROP_SEND_TO_MESSAGE_BOX, new Boolean(preference));
		}
	}

	private MessageReceiver getMessageReceiver(User user) {
		try {
			MessageReceiverHome home = (MessageReceiverHome) IDOLookup.getHome(MessageReceiver.class);
			return home.findByPrimaryKey(user.getPrimaryKey());
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		catch (FinderException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserBusiness getUserBusiness() throws RemoteException {
		return (UserBusiness) getServiceInstance(UserBusiness.class);
	}

	@Override
	public MessageSession getMessageSession(IWUserContext iwuc) throws IBOLookupException {
		return (MessageSession) getSessionInstance(iwuc, MessageSession.class);
	}

	@Override
	public String getBundleIdentifier() {
		return IW_BUNDLE_IDENTIFIER;
	}

	@Override
	public MessageHandlerInfo createMessageHandlerInfo(MessagePdfHandler handler, ICObject ico) throws CreateException, RemoteException {
		MessageHandlerInfoHome mhhome = (MessageHandlerInfoHome) getIDOHome(MessageHandlerInfo.class);
		MessageHandlerInfo info = mhhome.create();
		info.setHandlerCode(handler.getHandlerCode());
		info.setICObject(ico);
		info.store();
		return info;
	}

	@Override
	public void setMessageFile(PrintMessage msg, boolean flagPrinted, User performer, ICFile file) {
		msg.setMessageData(file);

		if (flagPrinted) {
			flagMessageAsPrinted(performer, msg);
		}
		else {
			msg.store();
		}

	}

	/**
	 * Gets the value for a property name ... replaces the bundle properties that
	 * were used previously
	 *
	 * @param propertyName
	 * @return
	 */
	private String getPropertyValue(String propertyName, String defaultValue) {
		IWMainApplicationSettings settings = getIWMainApplication().getSettings();
		String value = settings.getProperty(propertyName);
		if (value != null) {
			return value;
		}
		IWBundle iwb = getIWApplicationContext().getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);
		value = iwb.getProperty(propertyName);
		settings.setProperty(propertyName, value != null ? value : defaultValue);
		return defaultValue;
	}
}