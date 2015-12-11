package com.anderscore;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;

public class OutputMarkupIdListener implements IComponentInstantiationListener {

	@Override
	public void onInstantiation(Component component) {
		component.setOutputMarkupId(true);
	}

}
