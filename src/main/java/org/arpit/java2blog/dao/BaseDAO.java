package org.arpit.java2blog.dao;

import org.arpit.java2blog.jms.JmsMessageSender;
import org.arpit.java2blog.model.Audit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BaseDAO {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	private JmsMessageSender jmsMessageSender;
	
	@Value("${audit.enabled:false}")
	boolean auditEnabled;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected void audit(String action) {
		if (auditEnabled) {
			ObjectMapper mapper = new ObjectMapper();
			Audit audit = new Audit(action);
			String jsonInString = null;
			try {
				jsonInString = mapper.writeValueAsString(audit);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if (jsonInString != null) {
				jmsMessageSender.send(jsonInString);
			}
		}
	}
}
