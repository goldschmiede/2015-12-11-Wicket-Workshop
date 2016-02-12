package com.anderscore;

import java.io.IOException;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;

import com.anderscore.authenticate.AuthenticatedPage;
import com.anderscore.authenticate.BasicAuthenticationSession;
import com.anderscore.authenticate.SignInPage;

import de.agilecoders.wicket.core.Bootstrap;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.anderscore.Start#main(String[])
 */
public class WarehouseApplication extends AuthenticatedWebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return AuthenticatedPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		Bootstrap.install(this);
		
		// setup hsqldb
		startDatabase();
		
		// add your configuration here
		getComponentInstantiationListeners().add(new OutputMarkupIdListener());
		getComponentInstantiationListeners().add(new AddFeedbackListener());
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return BasicAuthenticationSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
	
	public static void startDatabase() {
		HsqlProperties hsqlProperties = new HsqlProperties();
		hsqlProperties.setProperty("server.database.0", "file:target\\db\\harley-warehouse");
		hsqlProperties.setProperty("server.dbname.0","testdb");

		org.hsqldb.Server server = new org.hsqldb.Server();
		try {
			server.setProperties(hsqlProperties);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerAcl.AclFormatException e) {
			e.printStackTrace();
		}
		server.setTrace(true);
		server.start();
	}
}
