package is.idega.idegaweb.egov.message.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class CommuneMessageBusinessHomeImpl extends IBOHomeImpl implements
		CommuneMessageBusinessHome {
	public Class getBeanInterfaceClass() {
		return CommuneMessageBusiness.class;
	}

	public CommuneMessageBusiness create() throws CreateException {
		return (CommuneMessageBusiness) super.createIBO();
	}
}