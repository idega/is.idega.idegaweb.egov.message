/*
 * $Id$ Created on 8.10.2004
 * 
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.data;

import java.util.Collection;

import javax.ejb.FinderException;

import is.idega.idegaweb.egov.message.business.MessageContentValue;

import com.idega.data.IDOFactory;
import com.idega.data.query.SelectQuery;

/**
 * 
 * Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class MessageContentHomeImpl extends IDOFactory implements MessageContentHome {

	protected Class getEntityInterfaceClass() {
		return MessageContent.class;
	}

	public MessageContent create() throws javax.ejb.CreateException {
		return (MessageContent) super.createIDO();
	}

	public MessageContent findByPrimaryKey(Object pk) throws javax.ejb.FinderException {
		return (MessageContent) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MessageContentBMPBean) entity).ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findByCategory(String category) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MessageContentBMPBean) entity).ejbFindByCategory(category);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findByCategoryAndLocale(String category, Integer locale) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MessageContentBMPBean) entity).ejbFindByCategoryAndLocale(category, locale);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findByValue(MessageContentValue value) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MessageContentBMPBean) entity).ejbFindByValue(value);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public SelectQuery getFindQuery(MessageContentValue value) {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		SelectQuery theReturn = ((MessageContentBMPBean) entity).ejbHomeGetFindQuery(value);
		this.idoCheckInPooledEntity(entity);
		return theReturn;
	}

}
