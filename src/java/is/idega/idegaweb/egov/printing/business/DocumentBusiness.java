/*
 * $Id$ Created on 7.10.2004
 * 
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.idegaweb.egov.printing.business;

import is.idega.idegaweb.egov.message.data.PrintMessage;
import is.idega.idegaweb.egov.message.data.PrintedLetterMessageHome;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import javax.ejb.FinderException;

import com.idega.business.IBOService;
import com.idega.idegaweb.IWBundle;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public interface DocumentBusiness extends IBOService {

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getAddressString
	 */
	public String getAddressString(User user) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedDocuments
	 */
	public Collection getPrintedDocuments() throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedDocuments
	 */
	public Collection getPrintedDocuments(String type) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedDocuments
	 */
	public Collection getPrintedDocuments(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedMessages
	 */
	public Collection getPrintedMessages(String type, int resultSize, int startingIndex) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedMessagesByPrimaryKeys
	 */
	public Collection getPrintedMessagesByPrimaryKeys(String[] primaryKeys, String type) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedMessages
	 */
	public Collection getPrintedMessages(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnPrintedMessages
	 */
	public Collection getUnPrintedMessages(String type, int resultSize, int startingIndex) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnPrintedMessages
	 */
	public Collection getUnPrintedMessages(String type, IWTimestamp from, IWTimestamp to, int resultSize, int startingIndex) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnPrintedDefaultLettersCount
	 */
	public int getUnPrintedDefaultLettersCount() throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnPrintedPasswordLettersCount
	 */
	public int getUnPrintedPasswordLettersCount() throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedLettersCountByStatusAndType
	 */
	public int getPrintedLettersCountByStatusAndType(String caseStatus, String type) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnprintedLettersCountByType
	 */
	public int getUnprintedLettersCountByType(String type) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnprintedMessagesCountByType
	 */
	public int getUnprintedMessagesCountByType(String type) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedLettersCountByType
	 */
	public int getPrintedLettersCountByType(String type) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintMessageTypes
	 */
	public String[] getPrintMessageTypes() throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#isBulkLetterType
	 */
	public boolean isBulkLetterType(String type) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getUnPrintedLettersIDs
	 */
	public int[] getUnPrintedLettersIDs(String type, int resultSize, int startingIndex) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPointsFromMM
	 */
	public float getPointsFromMM(float millimeters) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getPrintedLetterMessageHome
	 */
	public PrintedLetterMessageHome getPrintedLetterMessageHome() throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#writeBulkPDF
	 */
	public int writeBulkPDF(String[] messageIDs, User performer, String fileName, Locale locale, String type, boolean isAddressMessages, boolean flagMessages, boolean registerBulkData) throws FinderException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#writeBulkPDF
	 */
	public int writeBulkPDF(Collection messages, User performer, String fileName, Locale locale, String type, boolean areAddressMessages, boolean flagMessages, boolean registerBulkData) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#writePDF
	 */
	public int writePDF(PrintMessage msg, User performer, String fileName, Locale locale, boolean flagPrinted) throws Exception, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createDefaultLetterHeader
	 */
	public int createDefaultLetterHeader(Document document, String addressString, PdfWriter writer) throws Exception, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createNewlinesContent
	 */
	public void createNewlinesContent(Document document) throws DocumentException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createLogoContent
	 */
	public void createLogoContent(Document document) throws BadElementException, MalformedURLException, IOException, DocumentException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createHeaderDate
	 */
	public void createHeaderDate(Document document, PdfWriter writer, String dateString) throws DocumentException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createAddressContent
	 */
	public void createAddressContent(String addressString, PdfWriter writer) throws DocumentException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getMessageTagMap
	 */
	public HashMap getMessageTagMap(PrintMessage msg, Locale locale) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createCommuneFooter
	 */
	public void createCommuneFooter(PdfWriter writer) throws Exception, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createArchiveMessageContent
	 */
	public void createArchiveMessageContent(DocumentPrintContext dpc) throws ContentCreationException, java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getXMLLetterUrl
	 */
	public String getXMLLetterUrl(IWBundle iwb, Locale locale, String name) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getXMLLetterUrl
	 */
	public String getXMLLetterUrl(IWBundle iwb, Locale locale, String name, boolean createIfNotExists) throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#getDefaultXMLTemplateValue
	 */
	public String getDefaultXMLTemplateValue() throws java.rmi.RemoteException;

	/**
	 * @see is.idega.idegaweb.egov.printing.business.DocumentBusinessBean#createMessageContent
	 */
	public void createMessageContent(DocumentPrintContext dpc) throws ContentCreationException, java.rmi.RemoteException;

}
