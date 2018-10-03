/*
 * $Id$
 * Created on 7.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.idegaweb.egov.message.data;



import com.idega.block.process.data.Case;
import com.idega.block.process.message.data.Message;
import com.idega.data.IDOEntity;
import com.idega.user.data.User;

/**
 *
 *  Last modified: $Date$ by $Author$
 *
 * @author <a href="mailto:aron@idega.com">aron</a>
 * @version $Revision$
 */
public interface CaseNote extends IDOEntity, Message, Case {
    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getCaseCodeKey
     */
    public String getCaseCodeKey();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getCaseCodeDescription
     */
    public String getCaseCodeDescription();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#setSubject
     */
    @Override
	public void setSubject(String subject);

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getSubject
     */
    @Override
	public String getSubject();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#setBody
     */
    @Override
	public void setBody(String body);

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getBody
     */
    @Override
	public String getBody();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getSenderID
     */
    public int getSenderID();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#setSenderID
     */
    public void setSenderID(int userID);

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#getSender
     */
    @Override
	public User getSender();

    /**
     * @see se.idega.idegaweb.commune.message.data.CaseNoteBMPBean#setSender
     */
    @Override
	public void setSender(User user);

	void setCaseId(String caseId);

	String getCaseId();

}
