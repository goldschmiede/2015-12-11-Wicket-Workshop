package com.anderscore;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.anderscore.AddFeedbackListener.AjaxUpdater;

public class AddFeedbackListener implements IComponentInstantiationListener {

	public static class AjaxUpdater extends Behavior {
		@Override
		public void onEvent(Component component, IEvent<?> event) {
			if(event.getPayload() instanceof AjaxRequestTarget) {
				((IPartialPageRequestHandler) event.getPayload()).add(component);
			}
		}
	}

	@Override
	public void onInstantiation(Component component) {
		if(component instanceof FeedbackPanel) {
			component.add(new AjaxUpdater());
		}
	}

}
