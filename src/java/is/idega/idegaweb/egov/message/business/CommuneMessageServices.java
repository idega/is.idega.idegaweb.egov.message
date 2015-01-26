package is.idega.idegaweb.egov.message.business;

import java.util.logging.Level;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.email.event.UserMessage;
import com.idega.block.process.message.data.Message;
import com.idega.core.business.DefaultSpringBean;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CommuneMessageServices extends DefaultSpringBean implements ApplicationListener<UserMessage> {

	@Override
	public void onApplicationEvent(UserMessage message) {
		try {
			CommuneMessageBusiness cmb = getServiceInstance(CommuneMessageBusiness.class);
			Message msg = cmb.createUserMessage(message.getReceiver(), message.getSubject(), message.getMessage(), message.getSender(), message.isSendLetter());
			if (msg == null) {
				getLogger().warning("Failed to create commune message: " + message);
			}
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error while creating message: " + message, e);
		}
	}

}