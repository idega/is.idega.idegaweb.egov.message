/*
 * $Id$
 * Created on 4.11.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.printing.business;




import com.idega.business.IBOHomeImpl;

/**
 * 
 *  Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class DocumentServiceHomeImpl extends IBOHomeImpl implements
        DocumentServiceHome {
    protected Class getBeanInterfaceClass() {
        return DocumentService.class;
    }

    public DocumentService create() throws javax.ejb.CreateException {
        return (DocumentService) super.createIBO();
    }

}
