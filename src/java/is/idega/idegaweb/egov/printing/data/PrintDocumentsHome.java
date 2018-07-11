/*
 * $Id$
 * Created on 6.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.printing.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.IDOHome;
import com.idega.util.IWTimestamp;

/**
 *
 *  Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public interface PrintDocumentsHome extends IDOHome {
    public PrintDocuments create() throws javax.ejb.CreateException;

    public PrintDocuments findByPrimaryKey(Object pk)
            throws javax.ejb.FinderException;

    /**
     * @see se.idega.idegaweb.commune.printing.data.PrintDocumentsBMPBean#ejbFindAllPrintedLetterDocuments
     */
    public Collection findAllPrintedLetterDocuments() throws FinderException;

    /**
     * @see se.idega.idegaweb.commune.printing.data.PrintDocumentsBMPBean#ejbFindAllDocumentByType
     */
    public Collection findAllDocumentByType(String type) throws FinderException;

    /**
     * @see se.idega.idegaweb.commune.printing.data.PrintDocumentsBMPBean#ejbFindAllDocumentByType
     */
    public Collection findAllDocumentByType(String type, IWTimestamp from,
            IWTimestamp to) throws FinderException;

    /**
     * @see se.idega.idegaweb.commune.printing.data.PrintDocumentsBMPBean#ejbFindAllDocumentByType
     */
    public Collection findAllDocumentByType(String type, int resultSize,
            int startingIndex) throws FinderException;

    /**
     * @see se.idega.idegaweb.commune.printing.data.PrintDocumentsBMPBean#ejbFindAllDocumentByType
     */
    public Collection<PrintDocuments> findAllDocumentByType(String type, IWTimestamp from,
            IWTimestamp to, int resultSize, int startingIndex)
            throws FinderException;

}
