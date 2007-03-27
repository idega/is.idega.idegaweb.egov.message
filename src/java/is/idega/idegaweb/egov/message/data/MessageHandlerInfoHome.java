package is.idega.idegaweb.egov.message.data;


public interface MessageHandlerInfoHome extends com.idega.data.IDOHome
{
 public MessageHandlerInfo create() throws javax.ejb.CreateException;
 public MessageHandlerInfo findByPrimaryKey(Object pk) throws javax.ejb.FinderException;

}