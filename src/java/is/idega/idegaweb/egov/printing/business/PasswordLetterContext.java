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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;

import com.idega.block.process.message.data.Message;
import com.idega.core.location.data.Address;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWMainApplicationSettings;
import com.idega.idegaweb.IWUserContext;
import com.idega.user.business.CompanyHelper;
import com.idega.user.data.User;
import com.idega.util.expression.ELUtil;
import com.idega.xml.XMLDocument;
import com.idega.xml.XMLElement;

/**
 *
 *  Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class PasswordLetterContext extends MessageLetterContext {

    public PasswordLetterContext(IWUserContext iwac, Message msg) {
        super(iwac,msg);
        init(iwac,msg);
    }

    private Address getUserAddress(User receiver) {
    	if (receiver == null) {
    		return null;
    	}

    	try {
    		return receiver.getUsersMainAddress();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting address for user " + receiver, e);
		}

    	return null;
    }

    private Address getCompanyAddress(User receiver) {
    	if (receiver == null) {
    		return null;
    	}

    	try {
    		CompanyHelper companyHelper = ELUtil.getInstance().getBean(CompanyHelper.SPRING_BEAN_IDENTIFIER);
    		return companyHelper == null ? null : companyHelper.getCompanyAddress(receiver.getPersonalID());
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting address for company with personal ID " + receiver.getPersonalID(), e);
		}

    	return null;
    }

    private Address getAddress(User receiver) {
    	Address address = getUserAddress(receiver);
        if (address == null) {
        	address = getCompanyAddress(receiver);
        }
    	return address;
    }

    private void init(IWUserContext iwuc, Message msg) {
        Map<String, Object> props = new HashMap<String, Object>();
        if (msg.getBody().indexOf("|") > 0) {
			StringTokenizer tokenizer = new StringTokenizer(msg.getBody(), "|");

			if (tokenizer.hasMoreTokens()) {
			    props.put("username",tokenizer.nextToken());
			}
			if (tokenizer.hasMoreTokens()) {
			    props.put("password",tokenizer.nextToken());
			}

		}

        User receiver = msg.getOwner();
        Address address = getAddress(receiver);
        if (address == null) {
        	getLogger().warning("Failed to resolve address for receiver " + receiver + " of message " + msg);
        } else {
        	props.put("address", address);
       	}

        IWMainApplicationSettings settings = IWMainApplication.getDefaultIWMainApplication().getSettings();
        props.put("signature", settings.getProperty("citizen.mayor.signature.file.name", "signature_dagur_2014.gif"));
        props.put("mayorName", settings.getProperty("citizen.mayor.name", "Dagur B. Eggertsson"));

        addDocumentProperties(props);
        setResourceDirectory(new File(getResourceUrl(getBundle(iwuc),iwuc.getCurrentLocale())));
        try {
            setTemplateStream(getTemplateUrlAsStream(getBundle(iwuc),iwuc.getCurrentLocale(),"password_template.xml",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see se.idega.idegaweb.commune.printing.business.MessageLetterContext#getTemplateXMLDocument()
     */
    @Override
	protected XMLDocument getTemplateXMLDocument() {
        XMLDocument doc =  super.getBasicXMLDocument();
        XMLElement root = doc.getRootElement();
        XMLElement pass = new XMLElement("paragraph");
        pass.addContent("Username: \t<b>${username}</b> \n");
        pass.addContent("Password: \t<b>${password}</b> \n");
        root.addContent(pass);
        return doc;
    }

}