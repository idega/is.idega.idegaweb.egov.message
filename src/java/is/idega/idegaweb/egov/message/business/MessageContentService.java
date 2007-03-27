/*
 * $Id$ Created on 8.10.2004
 * 
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.business;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import is.idega.idegaweb.egov.message.data.MessageContentHome;

import com.idega.business.IBOService;

/**
 * 
 * Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public interface MessageContentService extends IBOService {

	/**
	 * @see se.idega.idegaweb.commune.message.business.MessageContentServiceBean#getValues
	 */
	public Collection getValues(MessageContentValue value) throws RemoteException, FinderException;

	/**
	 * @see se.idega.idegaweb.commune.message.business.MessageContentServiceBean#storeValue
	 */
	public MessageContentValue storeValue(MessageContentValue value) throws FinderException, RemoteException, CreateException;

	/**
	 * @see se.idega.idegaweb.commune.message.business.MessageContentServiceBean#removeValue
	 */
	public void removeValue(Integer ID) throws RemoteException, RemoveException, FinderException;

	/**
	 * @see se.idega.idegaweb.commune.message.business.MessageContentServiceBean#getValue
	 */
	public MessageContentValue getValue(Integer valueID) throws RemoteException, FinderException;

	/**
	 * @see se.idega.idegaweb.commune.message.business.MessageContentServiceBean#getHome
	 */
	public MessageContentHome getHome() throws RemoteException;

}
