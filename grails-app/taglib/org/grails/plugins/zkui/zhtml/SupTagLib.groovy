package org.grails.plugins.zkui.zhtml

import org.grails.plugins.zkui.AbstractTagLib

class SupTagLib extends AbstractTagLib {
    static namespace = "h"

    def sup = {attrs, b ->
        doTag(attrs, b, servletContext, request, response, pageScope, out)
    }

    @Override
    Class getComponentClass() {
        return org.zkoss.zhtml.Sup
    }
}
