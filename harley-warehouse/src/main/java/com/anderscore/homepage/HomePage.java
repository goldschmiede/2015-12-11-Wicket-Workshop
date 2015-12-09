package com.anderscore.homepage;

import com.anderscore.authenticate.AuthenticatedPage;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Link("goToAuthenticatedPage") {

			@Override
			public void onClick() {
				setResponsePage(AuthenticatedPage.class);
			}
		});

    }
}
