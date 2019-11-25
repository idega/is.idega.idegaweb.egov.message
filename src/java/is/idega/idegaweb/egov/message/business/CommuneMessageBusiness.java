package is.idega.idegaweb.egov.message.business;


import java.io.File;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;
import com.idega.block.process.message.business.MessageBusiness;
import com.idega.block.process.message.data.Message;
import com.idega.business.IBOLookupException;
import com.idega.business.IBOService;
import com.idega.core.component.data.ICObject;
import com.idega.core.file.data.ICFile;
import com.idega.data.IDOException;
import com.idega.idegaweb.IWUserContext;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;

import is.idega.idegaweb.egov.message.data.MessageHandlerInfo;
import is.idega.idegaweb.egov.message.data.PrintMessage;
import is.idega.idegaweb.egov.message.data.PrintedLetterMessage;

public interface CommuneMessageBusiness extends IBOService, MessageBusiness {
	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#deleteUserMessage
	 */
	public void deleteUserMessage(int messageID) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getCaseCodeUserMessage
	 */
	public CaseCode getCaseCodeUserMessage() throws RemoteException,
			FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getCaseCodePrintedLetterMessage
	 */
	public CaseCode getCaseCodePrintedLetterMessage() throws RemoteException,
			FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getUserMessage
	 */
	public Message getUserMessage(int messageId) throws FinderException,
			RemoteException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getNumberOfMessages
	 */
	public int getNumberOfMessages(User user) throws Exception, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getNumberOfNewMessages
	 */
	public int getNumberOfNewMessages(User user) throws IDOException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getNumberOfMessages
	 */
	public int getNumberOfMessages(User user, Collection groups)
			throws Exception, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#findMessages
	 */
	public Collection findMessages(User user) throws Exception, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#findMessages
	 */
	public Collection findMessages(User user, int numberOfEntries,
			int startingEntry) throws Exception, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#findMessages
	 */
	public Collection findMessages(User user, Collection groups,
			int numberOfEntries, int startingEntry) throws Exception,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#findMessages
	 */
	public Collection findMessages(Group group) throws Exception,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#findMessages
	 */
	public Collection findMessages(Group group, int numberOfEntries,
			int startingEntry) throws Exception, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(User user, String subject, String body)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(User user, String subject, String body,
			boolean sendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(User user, String subject, Group handler,
			String body, boolean sendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(User user, String subject, Group handler,
			String body, boolean sendLetter, String contentCode)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(User receiver, String subject,
			String body, User sender, boolean sendLetter)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	@Override
	public Message createUserMessage(Case parentCase, User receiver,
			String subject, String body, boolean sendLetter)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			String subject, String body, boolean sendLetter,
			boolean alwaysSendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			String subject, String body, String letterBody, boolean sendLetter,
			boolean alwaysSendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			String subject, String body, String letterBody, File attachment,
			boolean sendLetter, boolean alwaysSendLetter)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, String subject, String body, boolean sendLetter)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			boolean sendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			boolean pSendLetterIfNoEmail, String contentCode)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			boolean pSendLetterIfNoEmail, String contentCode,
			boolean alwaysSendLetter) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, boolean pSendLetterIfNoEmail,
			String contentCode, boolean alwaysSendLetter)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, boolean sendLetterIfNoEmail, String contentCode,
			boolean alwaysSendLetter, boolean sendMail) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessageValue
	 */
	public MessageValue createUserMessageValue(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, File attachment, boolean sendLetterIfNoEmail,
			String contentCode, boolean alwaysSendLetter, boolean sendMail)
			throws RemoteException;

	public MessageValue createUserMessageValue(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, File attachment, boolean sendLetterIfNoEmail,
			String contentCode, boolean alwaysSendLetter, boolean sendMail, String bcc)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, File attachment, boolean sendLetterIfNoEmail,
			String contentCode, boolean alwaysSendLetter, boolean sendMail)
			throws RemoteException;

	public Message createUserMessage(Case parentCase, User receiver,
			User sender, Group handler, String subject, String body,
			String letterBody, File attachment, boolean sendLetterIfNoEmail,
			String contentCode, boolean alwaysSendLetter, boolean sendMail, boolean deleteAttachment)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(MessageValue msgValue)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createUserMessage
	 */
	public Message createUserMessage(int userID, String subject, String body)
			throws CreateException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getPrintedLetterMessages
	 */
	public Collection getPrintedLetterMessages() throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getPrintedLetterMessagesByType
	 */
	public Collection getPrintedLetterMessagesByType(String type,
			int resultSize, int startingIndex) throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getPrintedLetterMessagesByType
	 */
	public Collection getPrintedLetterMessagesByType(String type,
			IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getSinglePrintedLetterMessagesByType
	 */
	public Collection getSinglePrintedLetterMessagesByType(String type,
			IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getUnPrintedLetterMessages
	 */
	public Collection getUnPrintedLetterMessages() throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getUnPrintedLetterMessagesByType
	 */
	public Collection getUnPrintedLetterMessagesByType(String type,
			int resultSize, int startingIndex) throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getUnPrintedLetterMessagesByType
	 */
	public Collection getUnPrintedLetterMessagesByType(String type,
			IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getSingleUnPrintedLetterMessagesByType
	 */
	public Collection getSingleUnPrintedLetterMessagesByType(String type,
			IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getSingleLettersByTypeAndStatus
	 */
	public Collection getSingleLettersByTypeAndStatus(String type,
			String status, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getLettersByBulkFile
	 */
	public Collection getLettersByBulkFile(int file, String type,
			String status, int resultSize, int startingIndex)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#flagPrintedLetterAsPrinted
	 */
	public void flagPrintedLetterAsPrinted(User performer,
			PrintedLetterMessage message) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#flagMessageAsPrinted
	 */
	public void flagMessageAsPrinted(User performer, Message message)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#flagMessageAsUnPrinted
	 */
	public void flagMessageAsUnPrinted(User performer, Message message)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#flagMessageWithStatus
	 */
	public void flagMessageWithStatus(User performer, Message message,
			String status) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#flagMessagesWithStatus
	 */
	public void flagMessagesWithStatus(User performer, String[] msgKeys,
			String status) throws FinderException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createPrintArchivationMessage
	 */
	public Message createPrintArchivationMessage(User user, String subject,
			String body) throws CreateException, RemoteException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createPrintArchivationMessage
	 */
	public Message createPrintArchivationMessage(int userID, String subject,
			String body) throws CreateException, RemoteException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createPrintedPasswordLetterMessage
	 */
	public PrintedLetterMessage createPrintedPasswordLetterMessage(User user,
			String subject, String body) throws CreateException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createPasswordMessage
	 */
	public PrintedLetterMessage createPasswordMessage(User user,
			String username, String password) throws CreateException,
			RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createPrintedLetterMessage
	 */
	public Message createPrintedLetterMessage(int userID, String subject,
			String body) throws CreateException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#sendMessage
	 */
	public void sendMessage(String email, String subject, String body)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#sendMessage
	 */
	public void sendMessage(String email, String subject, String body,
			File attachment) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getIfUserPreferesMessageByEmail
	 */
	public boolean getIfUserPreferesMessageByEmail(User user)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getIfUserPreferesMessageInMessageBox
	 */
	public boolean getIfUserPreferesMessageInMessageBox(User user)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getIfCanSendEmail
	 */
	public boolean getIfCanSendEmail() throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#setIfUserPreferesMessageByEmail
	 */
	public void setIfUserPreferesMessageByEmail(User user, boolean preference)
			throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#setIfUserPreferesMessageInMessageBox
	 */
	public void setIfUserPreferesMessageInMessageBox(User user,
			boolean preference) throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getMessageSession
	 */
	public MessageSession getMessageSession(IWUserContext iwuc)
			throws IBOLookupException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#getBundleIdentifier
	 */
	public String getBundleIdentifier() throws RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#createMessageHandlerInfo
	 */
	public MessageHandlerInfo createMessageHandlerInfo(
			MessagePdfHandler handler, ICObject ico) throws CreateException,
			RemoteException, RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.message.business.CommuneMessageBusinessBean#setMessageFile
	 */
	public void setMessageFile(PrintMessage msg, boolean flagPrinted,
			User performer, ICFile file) throws RemoteException;
}