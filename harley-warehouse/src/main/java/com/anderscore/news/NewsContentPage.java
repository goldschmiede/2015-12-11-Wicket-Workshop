package com.anderscore.news;

import com.anderscore.authenticate.AuthenticatedPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class NewsContentPage extends AuthenticatedPage {
    public NewsContentPage(PageParameters parameters) {
        super(parameters);
    }
}
