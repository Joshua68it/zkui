package org.grails.plugins.zkui.layouts.borderlayout

import org.grails.plugins.zkui.AbstractTagLib

class BorderLayoutTagLib extends AbstractTagLib {
    static namespace = "z"

    def borderlayout = {attrs, b ->
        doTag(attrs, b, servletContext, request, response, pageScope, out)
    }

    @Override
    Class getComponentClass() {
        return org.zkoss.zul.Borderlayout
    }

}
