/*
 * $Id$
 * Created on Oct 12, 2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.block.process.message.data.Message;
import com.idega.data.IDOException;
import com.idega.data.IDOFactory;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;


/**
 * Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:laddi@idega.com">laddi</a>
 * @version $Revision$
 */
public class PrintedLetterMessageHomeImpl extends IDOFactory implements PrintedLetterMessageHome {

	@Override
	protected Class getEntityInterfaceClass() {
		return PrintedLetterMessage.class;
	}

	@Override
	public Message create() throws javax.ejb.CreateException {
		return (PrintedLetterMessage) super.createIDO();
	}

	@Override
	public Message findByPrimaryKey(Object pk) throws javax.ejb.FinderException {
		return (PrintedLetterMessage) super.findByPrimaryKeyIDO(pk);
	}

	@Override
	public Collection findMessages(User user) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(user);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findMessagesByStatus(User user, String[] status) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessagesByStatus(user, status);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findAllUnPrintedLetters() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindAllUnPrintedLetters();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findAllPrintedLetters() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindAllPrintedLetters();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public int getNumberOfUnprintedLettersByType(String letterType) {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfUnprintedLettersByType(letterType);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfPrintedLettersByType(String letterType) {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfPrintedLettersByType(letterType);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfLettersByStatusAndType(String caseStatus, String letterType) {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfLettersByStatusAndType(caseStatus,
				letterType);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfUnPrintedPasswordLetters() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfUnPrintedPasswordLetters();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfPrintedPasswordLetters() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfPrintedPasswordLetters();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfUnPrintedDefaultLetters() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfUnPrintedDefaultLetters();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfPrintedDefaultLetters() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfPrintedDefaultLetters();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public String[] getLetterTypes() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		String[] theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetLetterTypes();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public Collection findPrintedLettersByType(String letterType, int resultSize, int startingIndex)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindPrintedLettersByType(letterType,
				resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindPrintedLettersByType(letterType, from, to,
				resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findSinglePrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindSinglePrintedLettersByType(letterType,
				from, to, resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findByBulkFile(int file, String letterType, String status, int resultSize, int startingIndex)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindByBulkFile(file, letterType, status,
				resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findSingleByTypeAndStatus(String letterType, String status, IWTimestamp from, IWTimestamp to,
			int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindSingleByTypeAndStatus(letterType, status,
				from, to, resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findUnPrintedLettersByType(String letterType, int resultSize, int startingIndex)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindUnPrintedLettersByType(letterType,
				resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findUnPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to, int resultSize,
			int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindUnPrintedLettersByType(letterType, from,
				to, resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findSingleUnPrintedLettersByType(String letterType, IWTimestamp from, IWTimestamp to,
			int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindSingleUnPrintedLettersByType(letterType,
				from, to, resultSize, startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findUnPrintedPasswordLetters(int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindUnPrintedPasswordLetters(resultSize,
				startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findPrintedPasswordLetters(int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindPrintedPasswordLetters(resultSize,
				startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findUnPrintedDefaultLetters(int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindUnPrintedDefaultLetters(resultSize,
				startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findPrintedDefaultLetters(int resultSize, int startingIndex) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindPrintedDefaultLetters(resultSize,
				startingIndex);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public String[] getPrintMessageTypes() {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		String[] theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetPrintMessageTypes();
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public Collection findLettersByChildcare(int providerID, String ssn, String msgId, IWTimestamp from, IWTimestamp to)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindLettersByChildcare(providerID, ssn, msgId,
				from, to);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findAllLettersBySchool(int providerID, String ssn, String msgId, IWTimestamp from, IWTimestamp to)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindAllLettersBySchool(providerID, ssn, msgId,
				from, to);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findLetters(String[] msgId) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindLetters(msgId);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public java.util.Collection findMessages(com.idega.user.data.User user, String[] status)
			throws javax.ejb.FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(user, status);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findMessages(User user, String[] status, int numberOfEntries, int startingEntry)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(user, status, numberOfEntries,
				startingEntry);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findMessages(Group group, String[] status) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(group, status);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findMessages(Group group, String[] status, int numberOfEntries, int startingEntry)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(group, status, numberOfEntries,
				startingEntry);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public Collection findMessages(User user, Collection groups, String[] status, int numberOfEntries, int startingEntry)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PrintedLetterMessageBMPBean) entity).ejbFindMessages(user, groups, status,
				numberOfEntries, startingEntry);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public int getNumberOfMessages(User user, Collection groups, String[] status) throws IDOException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfMessages(user, groups, status);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public int getNumberOfMessages(User user, String[] status) throws IDOException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		int theReturn = ((PrintedLetterMessageBMPBean) entity).ejbHomeGetNumberOfMessages(user, status);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

	@Override
	public Collection  <Message> findMessages(User user, String caseId)
			throws FinderException {
		return null;
	}
}
