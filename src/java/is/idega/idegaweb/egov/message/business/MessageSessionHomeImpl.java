/*
 * $Id$
 * Created on 2.11.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.business;


import com.idega.business.IBOHomeImpl;

/**
 * 
 *  Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class MessageSessionHomeImpl extends IBOHomeImpl implements
        MessageSessionHome {
    protected Class getBeanInterfaceClass() {
        return MessageSession.class;
    }

    public MessageSession create() throws javax.ejb.CreateException {
        return (MessageSession) super.createIBO();
    }

}
