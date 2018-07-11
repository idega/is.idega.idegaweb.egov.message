/*
 * $Id$ Created on 4.11.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.idegaweb.egov.printing.business;

import java.util.Collection;

import com.idega.block.pdf.business.PrintingContext;
import com.idega.block.pdf.business.PrintingService;
import com.idega.business.IBOLookupException;
import com.idega.business.IBOService;
import com.idega.idegaweb.IWUserContext;

import is.idega.idegaweb.egov.message.business.CommuneMessageBusiness;
import is.idega.idegaweb.egov.message.data.PrintMessage;

/**
 *
 * Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public interface DocumentService extends IBOService {

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#createPDF
	 */
	public Integer createPDF(IWUserContext iwuc, PrintMessage msg, String fileName, boolean flagPrinted) throws ContentCreationException, java.rmi.RemoteException;

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#createPDF
	 */
	public Integer createPDF(IWUserContext iwuc, String[] ids, String type, String fileName, boolean flagPrinted) throws java.rmi.RemoteException;

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#createPDF
	 */
	public Integer createPDF(IWUserContext iwuc, Collection<PrintMessage> msgs, String type, String fileName, boolean flagPrinted) throws java.rmi.RemoteException;

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#getPrintingContext
	 */
	public PrintingContext getPrintingContext(IWUserContext iwuc, PrintMessage msg) throws java.rmi.RemoteException;

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#getPrintingService
	 */
	public PrintingService getPrintingService() throws IBOLookupException, java.rmi.RemoteException;

	/**
	 * @see se.idega.idegaweb.commune.printing.business.DocumentServiceBean#getMessageService
	 */
	public CommuneMessageBusiness getMessageService() throws IBOLookupException, java.rmi.RemoteException;

}
