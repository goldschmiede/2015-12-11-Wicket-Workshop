package com.anderscore.simpleform.modal;

import com.anderscore.simpleform.panel.SimpleFormPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class SimpleFormModal extends Modal<String> {

    private static final long serialVersionUID = 4591006733317646110L;

    /**
     * @param markupId markup id
     * @param panel panels
     */
    public SimpleFormModal(String markupId, SimpleFormPanel panel) {
        super(markupId);
        add(panel.withAjax());
    }
}
