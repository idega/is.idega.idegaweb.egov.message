package is.idega.idegaweb.egov.message.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class MessageReceiverHomeImpl extends IDOFactory implements MessageReceiverHome {

	public Class getEntityInterfaceClass() {
		return MessageReceiver.class;
	}

	public MessageReceiver create() throws CreateException {
		return (MessageReceiver) super.createIDO();
	}

	public MessageReceiver findByPrimaryKey(Object pk) throws FinderException {
		return (MessageReceiver) super.findByPrimaryKeyIDO(pk);
	}
}