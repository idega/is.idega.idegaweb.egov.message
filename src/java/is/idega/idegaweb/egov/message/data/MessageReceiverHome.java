package is.idega.idegaweb.egov.message.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface MessageReceiverHome extends IDOHome {

	public MessageReceiver create() throws CreateException;

	public MessageReceiver findByPrimaryKey(Object pk) throws FinderException;
}