/*
 * $Id$
 * Created on May 2, 2008
 *
 * Copyright (C) 2008 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.data;

import com.idega.user.data.User;
import com.idega.user.data.UserBMPBean;

public class MessageReceiverBMPBean extends UserBMPBean implements MessageReceiver, User {

	public static final String METADATA_RECEIVE_EMAILS = "receive_emails";
	public static final String METADATA_RECEIVE_MESSAGES_IN_BOX = "receive_messages";

	public boolean receiveEmails() {
		String meta = getMetaData(METADATA_RECEIVE_EMAILS);
		if (meta != null && meta.length() > 0) {
			return new Boolean(meta).booleanValue();
		}
		return true;
	}

	public void setReceiveEmails(boolean receiveEmails) {
		setMetaData(METADATA_RECEIVE_EMAILS, String.valueOf(receiveEmails));
	}

	public boolean receiveMessagesToBox() {
		String meta = getMetaData(METADATA_RECEIVE_MESSAGES_IN_BOX);
		if (meta != null && meta.length() > 0) {
			return new Boolean(meta).booleanValue();
		}
		return true;
	}

	public void setReceiveMessagesToBox(boolean receiveMessages) {
		setMetaData(METADATA_RECEIVE_MESSAGES_IN_BOX, String.valueOf(receiveMessages));
	}
}