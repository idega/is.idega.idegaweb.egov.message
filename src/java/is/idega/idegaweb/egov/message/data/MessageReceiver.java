package is.idega.idegaweb.egov.message.data;


import com.idega.user.data.User;
import com.idega.data.IDOEntity;

public interface MessageReceiver extends IDOEntity, User {

	/**
	 * @see is.idega.idegaweb.egov.message.data.MessageReceiverBMPBean#receiveEmails
	 */
	public boolean receiveEmails();

	/**
	 * @see is.idega.idegaweb.egov.message.data.MessageReceiverBMPBean#setReceiveEmails
	 */
	public void setReceiveEmails(boolean receiveEmails);

	/**
	 * @see is.idega.idegaweb.egov.message.data.MessageReceiverBMPBean#receiveMessagesToBox
	 */
	public boolean receiveMessagesToBox();

	/**
	 * @see is.idega.idegaweb.egov.message.data.MessageReceiverBMPBean#setReceiveMessagesToBox
	 */
	public void setReceiveMessagesToBox(boolean receiveMessages);
}