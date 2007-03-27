/*
 * $Id$
 * Created on 15.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.printing.business;

import java.io.File;
import java.io.IOException;


import com.idega.block.process.message.data.Message;
import com.idega.idegaweb.IWUserContext;

/**
 * 
 *  Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class DefaultLetterContext extends MessageLetterContext {
    
    public DefaultLetterContext(IWUserContext iwuc ,Message msg) {
        super(iwuc,msg);      
        init(iwuc);
    }
    
    private void init(IWUserContext iwuc){
        setResourceDirectory(new File(getResourceUrl(getBundle(iwuc),iwuc.getCurrentLocale())));
        try {
            setTemplateStream(getTemplateUrlAsStream(getBundle(iwuc),iwuc.getCurrentLocale(),"default_template.xml",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
