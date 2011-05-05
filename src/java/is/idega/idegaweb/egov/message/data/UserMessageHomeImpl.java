/*
 * $Id$
 * Created on 7.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
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

/**
 *
 *  Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class UserMessageHomeImpl extends IDOFactory implements UserMessageHome {
    @Override
	protected Class getEntityInterfaceClass() {
        return UserMessage.class;
    }

    @Override
	public Message create() throws javax.ejb.CreateException {
        return (UserMessage) super.createIDO();
    }

    @Override
	public Message findByPrimaryKey(Object pk)
            throws javax.ejb.FinderException {
        return (UserMessage) super.findByPrimaryKeyIDO(pk);
    }

    @Override
	public Collection findMessages(User user) throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(user);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public Collection findMessagesByStatus(User user, String[] status)
            throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessagesByStatus(user, status);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public Collection findMessagesByStatus(User user, String[] status,
            int numberOfEntries, int startingEntry) throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessagesByStatus(user, status, numberOfEntries,
                        startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public Collection findMessagesByStatus(Group group, String[] status)
            throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessagesByStatus(group, status);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public Collection findMessagesByStatus(Group group, String[] status,
            int numberOfEntries, int startingEntry) throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessagesByStatus(group, status, numberOfEntries,
                        startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public Collection findMessagesByStatus(User user, Collection groups,
            String[] status, int numberOfEntries, int startingEntry)
            throws FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessagesByStatus(user, groups, status, numberOfEntries,
                        startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public int getNumberOfMessagesByStatus(User user, String[] status)
            throws IDOException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        int theReturn = ((UserMessageBMPBean) entity)
                .ejbHomeGetNumberOfMessagesByStatus(user, status);
        this.idoCheckInPooledEntity(entity);
        return theReturn;
    }

    @Override
	public int getNumberOfMessagesByStatus(User user, Collection groups,
            String[] status) throws IDOException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        int theReturn = ((UserMessageBMPBean) entity)
                .ejbHomeGetNumberOfMessagesByStatus(user, groups, status);
        this.idoCheckInPooledEntity(entity);
        return theReturn;
    }

    @Override
	public java.util.Collection findMessages(com.idega.user.data.User user,
            String[] status) throws javax.ejb.FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(user, status);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public java.util.Collection findMessages(com.idega.user.data.Group group,
            String[] status) throws javax.ejb.FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(group, status);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public java.util.Collection findMessages(com.idega.user.data.User user,
            String[] status, int numberOfEntries, int startingEntry)
            throws javax.ejb.FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(user, status, numberOfEntries, startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public java.util.Collection findMessages(com.idega.user.data.Group group,
            String[] status, int numberOfEntries, int startingEntry)
            throws javax.ejb.FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(group, status, numberOfEntries, startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public java.util.Collection findMessages(com.idega.user.data.User user,
            java.util.Collection groups, String[] status, int numberOfEntries,
            int startingEntry) throws javax.ejb.FinderException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity)
                .ejbFindMessages(user, groups, status, numberOfEntries,
                        startingEntry);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);
    }

    @Override
	public int getNumberOfMessages(com.idega.user.data.User user,
            String[] status) throws IDOException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        int theReturn = ((UserMessageBMPBean) entity)
                .ejbHomeGetNumberOfMessages(user, status);
        this.idoCheckInPooledEntity(entity);
        return theReturn;
    }

    @Override
	public int getNumberOfMessages(com.idega.user.data.User user,
            java.util.Collection groups, String[] status) throws IDOException {
        com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        int theReturn = ((UserMessageBMPBean) entity)
                .ejbHomeGetNumberOfMessages(user, groups, status);
        this.idoCheckInPooledEntity(entity);
        return theReturn;
    }

	@Override
	public Collection findMessages(User user, String caseId)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
        java.util.Collection ids = ((UserMessageBMPBean) entity).ejbFindMessages(user, caseId);
        this.idoCheckInPooledEntity(entity);
        return this.getEntityCollectionForPrimaryKeys(ids);

	}

}
