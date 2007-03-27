/*
 * $Id$
 * Created on Feb 13, 2006
 *
 * Copyright (C) 2006 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.business;

import com.idega.business.IBOHomeImpl;


/**
 * <p>
 * TODO laddi Describe Type CommuneMessageBusinessHomeImpl
 * </p>
 *  Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:laddi@idega.com">laddi</a>
 * @version $Revision$
 */
public class CommuneMessageBusinessHomeImpl extends IBOHomeImpl implements CommuneMessageBusinessHome {

	protected Class getBeanInterfaceClass() {
		return CommuneMessageBusiness.class;
	}

	public CommuneMessageBusiness create() throws javax.ejb.CreateException {
		return (CommuneMessageBusiness) super.createIBO();
	}
}
