package is.idega.idegaweb.egov.message.data;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import com.idega.block.process.data.AbstractCaseBMPBean;
import com.idega.block.process.data.Case;
import com.idega.core.file.data.ICFile;
import com.idega.data.IDOException;
import com.idega.data.IDORuntimeException;
import com.idega.data.query.Criteria;
import com.idega.data.query.InCriteria;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 *
 * @author Anders Lindman
 * @version 1.0
 */
public class PrintedLetterMessageBMPBean extends AbstractCaseBMPBean implements PrintedLetterMessage, PrintMessage, Case {

	private static final long serialVersionUID = -3476359538719379553L;

	private static final String COLUMN_SUBJECT = "SUBJECT";

	private static final String COLUMN_BODY = "BODY";

	private static final String COLUMN_MESSAGE_TYPE = "MESSAGE_TYPE";

	private static final String COLUMN_MESSAGE_DATA = "MESSAGE_DATA";

	private static final String COLUMN_LETTER_TYPE = "LETTER_TYPE";

	private static final String COLUMN_BULK_DATA = "BULK_DATA";

	private static final String COLUMN_CONTENT_CODE = "CONTENT_CODE";

	private static final String CASE_CODE_KEY = "SYMEBRV";

	private static final String CASE_CODE_DESCRIPTION = "Letter Message";

	@Override
	public String getEntityName() {
		return "MSG_LETTER_MESSAGE";
	}

	@Override
	public void initializeAttributes() {
		addGeneralCaseRelation();
		this.addAttribute(COLUMN_SUBJECT, "Message subject", String.class);
		this.addAttribute(COLUMN_BODY, "Message body", String.class, 4000);
		this.addAttribute(COLUMN_MESSAGE_TYPE, "Message type", String.class, 20);
		this.addManyToOneRelationship(COLUMN_MESSAGE_DATA, "Message data", ICFile.class);
		this.addAttribute(COLUMN_LETTER_TYPE, "Message SubType", String.class, 4);
		this.addManyToOneRelationship(COLUMN_BULK_DATA, "Message bulk data", ICFile.class);
		this.addAttribute(COLUMN_CONTENT_CODE, "Message contentcode", String.class, 20);
	}

	@Override
	public String getCaseCodeKey() {
		return CASE_CODE_KEY;
	}

	@Override
	public String getCaseCodeDescription() {
		return CASE_CODE_DESCRIPTION;
	}

	@Override
	public void setDefaultValues() {
		setLetterType(MessageConstants.LETTER_TYPE_DEFAULT);
	}

	@Override
	public User getSender() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSender(User sender) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSenderID() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSenderID(int senderID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSubject(String subject) {
		this.setColumn(COLUMN_SUBJECT, subject);
	}

	@Override
	public String getSubject() {
		return this.getStringColumnValue(COLUMN_SUBJECT);
	}

	@Override
	public void setBody(String body) {
		this.setColumn(COLUMN_BODY, body);
	}

	@Override
	public String getBody() {
		return this.getStringColumnValue(COLUMN_BODY);
	}

	@Override
	public String getMessageType() {
		return this.getStringColumnValue(COLUMN_MESSAGE_TYPE);
	}

	@Override
	public boolean isPrinted() {
		return this.getCaseStatus().getStatus().equals(getCaseStatusReady());
	}

	@Override
	public void setMessageType(String type) {
		this.setColumn(COLUMN_MESSAGE_TYPE, type);
	}

	@Override
	public ICFile getMessageData() {
		return (ICFile) this.getColumnValue(COLUMN_MESSAGE_DATA); // Replace this
																															// later
	}

	@Override
	public int getMessageDataFileID() {
		return this.getIntColumnValue(COLUMN_MESSAGE_DATA);
	}

	@Override
	public void setMessageData(ICFile file) { // Temp (test) method
		this.setColumn(COLUMN_MESSAGE_DATA, file);
	}

	@Override
	public void setMessageData(int fileID) { // Temp (test) method
		this.setColumn(COLUMN_MESSAGE_DATA, fileID);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see se.idega.idegaweb.commune.message.data.PrintMessage#getContentType()
	 */
	@Override
	public String getContentCode() {
		return this.getStringColumnValue(COLUMN_CONTENT_CODE);
	}

	@Override
	public void setContentCode(String contentCode) {
		this.setColumn(COLUMN_CONTENT_CODE, contentCode);
	}

	@Override
	public ICFile getMessageBulkData() {
		return (ICFile) this.getColumnValue(COLUMN_BULK_DATA); // Replace this
																														// later
	}

	@Override
	public int getMessageBulkDataFileID() {
		return this.getIntColumnValue(COLUMN_BULK_DATA);
	}

	@Override
	public void setMessageBulkData(ICFile file) { // Temp (test) method
		this.setColumn(COLUMN_BULK_DATA, file);
	}

	@Override
	public void setMessageBulkData(int fileID) { // Temp (test) method
		this.setColumn(COLUMN_BULK_DATA, fileID);
	}

	@Override
	public String getSenderName() {
		return getOwner().getName();
	}

	@Override
	public String getDateString() {
		/**
		 * @todo: implement
		 */
		return "";
	}

	@Override
	public void setLetterType(String letterType) {
		setColumn(COLUMN_LETTER_TYPE, letterType);
	}

	@Override
	public String getLetterType() {
		return getStringColumnValue(COLUMN_LETTER_TYPE);
	}

	@Override
	public void setAsPasswordLetter() {
		setLetterType(MessageConstants.LETTER_TYPE_PASSWORD);
	}

	public Collection ejbFindMessages(User user) throws FinderException {
		return super.ejbFindAllCasesByUser(user);
	}

	public Collection ejbFindMessagesByStatus(User user, String[] status) throws FinderException {
		return super.ejbFindAllCasesByUserAndStatusArray(user, status);
	}

	public Collection ejbFindAllUnPrintedLetters() throws FinderException {
		return super.ejbFindAllCasesByStatus(super.getCaseStatusOpen());
	}

	public Collection ejbFindAllPrintedLetters() throws FinderException {
		return super.ejbFindAllCasesByStatus(super.getCaseStatusReady());
	}

	protected SelectQuery idoSelectQueryGetCountDefaultLettersWithStatus(String caseStatus) {
		return idoSelectQueryGetLettersCountByStatusAndType(caseStatus, MessageConstants.LETTER_TYPE_DEFAULT);
	}

	protected SelectQuery idoSelectQueryGetCountPasswordLettersWithStatus(String caseStatus) {
		return idoSelectQueryGetLettersCountByStatusAndType(caseStatus, COLUMN_LETTER_TYPE);
	}

	@Override
	public String getUnPrintedCaseStatusForType(String type) {
		return getCaseStatusOpen();
	}

	@Override
	public String getPrintedCaseStatusForType(String type) {
		return getCaseStatusReview();
	}

	/**
	 * Counts the number of unprinted letters of the given type.
	 */
	public int ejbHomeGetNumberOfUnprintedLettersByType(String letterType) {
		try {
			SelectQuery sql = idoSelectQueryGetLettersCountByStatusAndType(getUnPrintedCaseStatusForType(letterType),
					letterType);
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of printed letters of the given type.
	 */
	public int ejbHomeGetNumberOfPrintedLettersByType(String letterType) {
		try {
			SelectQuery sql = idoSelectQueryGetLettersCountByStatusAndType(getUnPrintedCaseStatusForType(letterType),
					letterType);
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of letters of the given type and status.
	 */
	public int ejbHomeGetNumberOfLettersByStatusAndType(String caseStatus, String letterType) {
		try {
			SelectQuery sql = idoSelectQueryGetLettersCountByStatusAndType(caseStatus, letterType);
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of letters that are of type password and unprinted
	 */
	public int ejbHomeGetNumberOfUnPrintedPasswordLetters() {
		try {
			SelectQuery sql = idoSelectQueryGetCountPasswordLettersWithStatus(getCaseStatusOpen());
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of letters that are of type password and printed
	 */
	public int ejbHomeGetNumberOfPrintedPasswordLetters() {
		try {
			SelectQuery sql = idoSelectQueryGetCountPasswordLettersWithStatus(getCaseStatusReview());
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of letters that are of type default and unprinted
	 */
	public int ejbHomeGetNumberOfUnPrintedDefaultLetters() {
		try {
			SelectQuery sql = idoSelectQueryGetCountDefaultLettersWithStatus(getCaseStatusOpen());
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	/**
	 * Counts the number of letters that are of type default and printed
	 */
	public int ejbHomeGetNumberOfPrintedDefaultLetters() {
		try {
			SelectQuery sql = idoSelectQueryGetCountDefaultLettersWithStatus(getCaseStatusReview());
			return super.idoGetNumberOfRecords(sql);
		}
		catch (IDOException sqle) {
			throw new EJBException(sqle.getMessage());
		}
	}

	public String[] ejbHomeGetLetterTypes() {
		String[] types = { MessageConstants.LETTER_TYPE_DEFAULT, MessageConstants.LETTER_TYPE_PASSWORD };
		return types;
	}

	protected SelectQuery idoSelectQueryGetPrintedLettersByType(String letterType) {
		try {
			return idoSelectQueryGetLettersByStatusAndType(getCaseStatusReady(), letterType);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected SelectQuery idoSelectQueryGetPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to) {
		try {
			return idoSelectQueryGetLettersByStatusAndType(getCaseStatusReady(), letterType, from, to);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected SelectQuery idoSelectQueryGetSinglePrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to) {
		try {
			return idoSelectQueryGetSingleLettersByStatusAndType(getCaseStatusReady(), letterType, from, to);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected SelectQuery idoSelectQueryGetUnPrintedLettersByType(String letterType) {
		try {
			return idoSelectQueryGetLettersByStatusAndType(getCaseStatusOpen(), letterType);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected SelectQuery idoSelectQueryGetUnPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to) {
		try {
			return idoSelectQueryGetLettersByStatusAndType(getCaseStatusOpen(), letterType, from, to);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected SelectQuery idoSelectQueryGetSingleUnPrintedLettersByType(String letterType, IWTimestamp from,
			IWTimestamp to) {
		try {
			return idoSelectQueryGetSingleLettersByStatusAndType(getCaseStatusOpen(), letterType, from, to);
		}
		catch (Exception e) {
			throw new IDORuntimeException(e, this);
		}
	}

	protected Criteria idoCriteriaForLetterType(String type) {
		return new MatchCriteria(idoTableSubCase(), COLUMN_LETTER_TYPE, MatchCriteria.EQUALS, type, true);
	}

	protected SelectQuery idoSelectQueryGetLettersByStatusAndType(String caseStatus, String letterType) {
		SelectQuery query = idoSelectQueryGetAllCasesByStatus(caseStatus);
		query.addCriteria(idoCriteriaForLetterType(letterType));
		return query;
	}

	protected SelectQuery idoSelectQueryGetLettersByStatusAndType(String caseStatus, String letterType, IWTimestamp from,
			IWTimestamp to) {
		SelectQuery query = idoSelectQueryGetAllCases();
		query.addCriteria(idoCriteriaForStatus(caseStatus));
		query.addCriteria(idoCriteriaForCreatedWithinDates(from, to));
		query.addCriteria(idoCriteriaForLetterType(letterType));
		query.addOrder(idoOrderByCreationDate(false));
		return query;
	}

	protected SelectQuery idoSelectQueryGetSingleLettersByStatusAndType(String caseStatus, String letterType,
			IWTimestamp from, IWTimestamp to) {
		SelectQuery query = idoSelectQueryGetAllCases();
		query.addCriteria(idoCriteriaForStatus(caseStatus));
		query.addCriteria(idoCriteriaForCreatedWithinDates(from, to));
		query.addCriteria(idoCriteriaForLetterType(letterType));
		query.addOrder(idoOrderByCreationDate(false));
		return query;
	}

	protected SelectQuery idoSelectQueryGetLettersByBulkFile(int file, String caseStatus, String letterType) {
		SelectQuery query = idoSelectQueryGetAllCases();
		query.addCriteria(idoCriteriaForStatus(caseStatus));
		query.addCriteria(idoCriteriaForLetterType(letterType));
		query.addCriteria(new MatchCriteria(idoTableSubCase(), COLUMN_BULK_DATA, MatchCriteria.EQUALS, file));
		query.addOrder(idoOrderByCreationDate(false));
		return query;
	}

	protected SelectQuery idoSelectQueryGetLettersCountByStatusAndType(String caseStatus, String letterType) {
		SelectQuery query = super.idoSelectQueryGetCountCasesWithStatus(caseStatus);
		query.addCriteria(idoCriteriaForLetterType(letterType));
		return query;
	}

	public Collection ejbFindPrintedLettersByType(String letterType, int resultSize, int startingIndex)
			throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetPrintedLettersByType(letterType), resultSize, startingIndex);
	}

	public Collection ejbFindPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetPrintedLettersByType(letterType, from, to), resultSize,
				startingIndex);
	}

	public Collection ejbFindSinglePrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to,
			int resultSize, int startingIndex) throws FinderException {
		SelectQuery query = idoSelectQueryGetSinglePrintedLettersByType(letterType, from, to);
		return super.idoFindPKsByQuery(query, resultSize, startingIndex);
	}

	public Collection ejbFindByBulkFile(int file, String letterType, String status, int resultSize, int startingIndex)
			throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetLettersByBulkFile(file, status, letterType), resultSize,
				startingIndex);
	}

	public Collection ejbFindSingleByTypeAndStatus(String letterType, String status, IWTimestamp from, IWTimestamp to,
			int resultSize, int startingIndex) throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetSingleLettersByStatusAndType(status, letterType, from, to),
				resultSize, startingIndex);
	}

	public Collection ejbFindUnPrintedLettersByType(String letterType, int resultSize, int startingIndex)
			throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetUnPrintedLettersByType(letterType), resultSize, startingIndex);
	}

	public Collection ejbFindUnPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetUnPrintedLettersByType(letterType, from, to), resultSize,
				startingIndex);
	}

	public Collection ejbFindSingleUnPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to,
			int resultSize, int startingIndex) throws FinderException {
		return super.idoFindPKsByQuery(idoSelectQueryGetSingleUnPrintedLettersByType(letterType, from, to), resultSize,
				startingIndex);
	}

	public Collection ejbFindUnPrintedPasswordLetters(int resultSize, int startingIndex) throws FinderException {
		String letterType = MessageConstants.LETTER_TYPE_PASSWORD;
		return ejbFindUnPrintedLettersByType(letterType, resultSize, startingIndex);
	}

	public Collection ejbFindPrintedPasswordLetters(int resultSize, int startingIndex) throws FinderException {
		String letterType = MessageConstants.LETTER_TYPE_PASSWORD;
		return ejbFindPrintedLettersByType(letterType, resultSize, startingIndex);
	}

	public Collection ejbFindUnPrintedDefaultLetters(int resultSize, int startingIndex) throws FinderException {
		String letterType = MessageConstants.LETTER_TYPE_DEFAULT;
		return ejbFindUnPrintedLettersByType(letterType, resultSize, startingIndex);
	}

	public Collection ejbFindPrintedDefaultLetters(int resultSize, int startingIndex) throws FinderException {
		String letterType = MessageConstants.LETTER_TYPE_DEFAULT;
		return ejbFindPrintedLettersByType(letterType, resultSize, startingIndex);
	}

	@Override
	public String getPrintType() {
		return getMessageType();
	}

	public String[] ejbHomeGetPrintMessageTypes() {
		return ejbHomeGetLetterTypes();
	}

	// TODO Handle this in more general way...
	public Collection ejbFindLettersByChildcare(int providerID, String ssn, String msgId, IWTimestamp from, IWTimestamp to)
			throws FinderException {
		com.idega.data.IDOQuery sql = idoQuery();
		String sqlFrom = this.getEntityName() + " m, proc_case p, comm_childcare c";
		if (ssn != null && !ssn.equals("")) {
			sqlFrom += ", ic_user u";
		}
		sql.appendSelectAllFrom(sqlFrom);
		sql.appendWhereEquals("m.msg_letter_message_id", "p.proc_case_id");
		sql.appendAndEquals("p.parent_case_id", "c.comm_childcare_id");
		sql.appendAndEquals("c.provider_id", providerID);
		if (ssn != null && !ssn.equals("")) {
			sql.appendAndEquals("p.user_id", "u.ic_user_id");
			sql.appendAnd().append("u.personal_id").appendLike().appendWithinSingleQuotes(ssn);
		}
		if (msgId != null && !msgId.equals("")) {
			sql.appendAndEqualsQuoted("m.msg_letter_message_id", msgId);
		}
		to.setHour(23);
		to.setMinute(59);
		to.setSecond(59);
		from.setHour(0);
		from.setMinute(0);
		from.setSecond(0);
		sql.appendAnd();
		sql.append("p.created");
		sql.append(" >= ");
		sql.append(from.getTimestamp());
		sql.append("");
		sql.appendAnd();
		sql.append("p.created");
		sql.append(" <= ");
		sql.append(to.getTimestamp());
		sql.append(" ");
		sql.appendOrderBy("p.created");
		// System.out.println("########### SQL:" + sql.toString() + ".");
		Collection tmp = this.idoFindPKsByQuery(sql);
		return tmp;
	}

	public Collection ejbFindAllLettersBySchool(int providerID, String ssn, String msgId, IWTimestamp from, IWTimestamp to)
			throws FinderException {
		com.idega.data.IDOQuery sql = idoQuery();
		String sqlFrom = this.getEntityName() + " m, proc_case p, comm_sch_choice c";
		if (ssn != null && !ssn.equals("")) {
			sqlFrom += ", ic_user u";
		}
		sql.appendSelectAllFrom(sqlFrom);
		sql.appendWhereEquals("m.msg_letter_message_id", "p.proc_case_id");
		sql.appendAndEquals("p.parent_case_id", "c.comm_sch_choice_id");
		sql.appendAndEquals("c.school_id", providerID);
		if (ssn != null && !ssn.equals("")) {
			sql.appendAndEquals("p.user_id", "u.ic_user_id");
			sql.appendAnd().append("u.personal_id").appendLike().appendWithinSingleQuotes(ssn);
		}
		if (msgId != null && !msgId.equals("")) {
			sql.appendAndEqualsQuoted("m.msg_letter_message_id", msgId);
		}
		to.setHour(23);
		to.setMinute(59);
		to.setSecond(59);
		from.setHour(0);
		from.setMinute(0);
		from.setSecond(0);
		sql.appendAnd();
		sql.append("p.created");
		sql.append(" >= ");
		sql.append(from.getTimestamp());
		sql.append(" ");
		sql.appendAnd();
		sql.append("p.created");
		sql.append(" <= ");
		sql.append(to.getTimestamp());
		sql.append(" ");
		sql.appendOrderBy("p.created");

		return idoFindPKsByQuery(sql);
	}

	public Collection ejbFindLetters(String[] msgId) throws FinderException {
		SelectQuery query = idoSelectQuery();
		query.addCriteria(new InCriteria(idoQueryTable(), getIDColumnName(), msgId));
		Collection tmp = this.idoFindPKsByQuery(query);
		return tmp;
	}

	public java.util.Collection ejbFindMessages(com.idega.user.data.User user, String[] status)
			throws javax.ejb.FinderException {
		return super.ejbFindAllCasesByUserAndStatusArray(user, status);
	}

	public Collection ejbFindMessages(User user, String[] status, int numberOfEntries, int startingEntry)
			throws FinderException {
		return super.ejbFindAllCasesByUserAndStatusArray(user, status, numberOfEntries, startingEntry);
	}

	public Collection ejbFindMessages(Group group, String[] status) throws FinderException {
		return super.ejbFindAllCasesByGroupAndStatusArray(group, status);
	}

	public Collection ejbFindMessages(Group group, String[] status, int numberOfEntries, int startingEntry)
			throws FinderException {
		return super.ejbFindAllCasesByGroupAndStatusArray(group, status, numberOfEntries, startingEntry);
	}

	public Collection ejbFindMessages(User user, Collection groups, String[] status, int numberOfEntries,
			int startingEntry) throws FinderException {
		return super.ejbFindAllCasesByUserAndGroupsAndStatusArray(user, groups, status, numberOfEntries, startingEntry);
	}

	public int ejbHomeGetNumberOfMessages(User user, Collection groups, String[] status) throws IDOException {
		return super.ejbHomeGetCountCasesByUserAndGroupsAndStatusArray(user, groups, status);
	}

	public int ejbHomeGetNumberOfMessages(User user, String[] status) throws IDOException {
		return super.ejbHomeGetCountCasesByUserAndStatusArray(user, status);
	}

	@Override
	public int getCreatorId() {
		User creator = getCreator();
		return creator == null ? -1 : (Integer) creator.getPrimaryKey();
	}

}