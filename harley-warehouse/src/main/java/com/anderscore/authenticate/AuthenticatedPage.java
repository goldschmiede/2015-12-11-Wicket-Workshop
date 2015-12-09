package com.anderscore.authenticate;

import com.anderscore.manuals.ManualPage;
import com.anderscore.news.NewsContentPage;
import com.anderscore.reports.ReportsPage;
import com.anderscore.stockitems.StockItemsPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class AuthenticatedPage extends WebPage {

    public AuthenticatedPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onConfigure() {
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) AuthenticatedWebApplication.get();

        if(!AuthenticatedWebSession.get().isSignedIn())
            app.restartResponseAtSignInPage();
    }


    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("newsContent") {
            @Override
            public void onClick() {
                setResponsePage(NewsContentPage.class);
            }
        });

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("stockItemsContent") {
            @Override
            public void onClick() {
                setResponsePage(StockItemsPage.class);
            }
        });

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("manualsContent") {
            @Override
            public void onClick() {
                setResponsePage(ManualPage.class);
            }
        });

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("reportsContent") {
            @Override
            public void onClick() {
                setResponsePage(ReportsPage.class);
            }
        });

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("goToHomePage") {

            @Override
            public void onClick() {
                setResponsePage(getApplication().getHomePage());
            }
        });

        add(new com.googlecode.wicket.jquery.ui.markup.html.link.Link("logOut") {

            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}
