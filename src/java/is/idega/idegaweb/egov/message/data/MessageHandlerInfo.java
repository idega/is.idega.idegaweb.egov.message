package is.idega.idegaweb.egov.message.data;

public interface MessageHandlerInfo extends com.idega.data.IDOEntity {

	public is.idega.idegaweb.egov.message.business.MessagePdfHandler getHandler();

	public java.lang.String getHandlerCode();

	public com.idega.core.component.data.ICObject getICObject();

	public java.lang.Class getPrimaryKeyClass();

	public void setHandlerCode(java.lang.String p0);

	public void setICObject(com.idega.core.component.data.ICObject p0);
}
