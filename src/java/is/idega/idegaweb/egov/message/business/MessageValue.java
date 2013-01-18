/*
 * $Id$
 * Created on 7.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.business;

import java.io.File;

/**
 * 
 *  Last modified: $Date$ by $Author$
 * 
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public class MessageValue extends com.idega.block.process.message.business.MessageValue {
	
   private String letterBody;
   private Boolean sendLetterIfNoEmail;
   private String contentCode;
   private Boolean alwaysSendLetter; 
   private Boolean sendMail;
   private String printedLetterType;
   private File attachment = null;
   private String bcc;
	
	public Boolean getAlwaysSendLetter() {
		return this.alwaysSendLetter;
	}
	
	public void setAlwaysSendLetter(Boolean alwaysSendLetter) {
		this.alwaysSendLetter = alwaysSendLetter;
	}
	
	public String getContentCode() {
		return this.contentCode;
	}
	
	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}
	
	public String getLetterBody() {
		return this.letterBody;
	}
	
	public void setLetterBody(String letterBody) {
		this.letterBody = letterBody;
	}
	
	public String getPrintedLetterType() {
		return this.printedLetterType;
	}
	
	public void setPrintedLetterType(String printedLetterType) {
		this.printedLetterType = printedLetterType;
	}
	
	public Boolean getSendLetterIfNoEmail() {
		return this.sendLetterIfNoEmail;
	}
	
	public void setSendLetterIfNoEmail(Boolean sendLetterIfNoEmail) {
		this.sendLetterIfNoEmail = sendLetterIfNoEmail;
	}
	
	public Boolean getSendMail() {
		return this.sendMail;
	}
	
	public void setSendMail(Boolean sendMail) {
		this.sendMail = sendMail;
	}
	
	public File getAttachment() {
		return this.attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	public String toString() {
		return "To: " + getReceiver() + ", from: " + getSender() + ", subject: " + getSubject() + ", message: " + getLetterBody();
	}

}