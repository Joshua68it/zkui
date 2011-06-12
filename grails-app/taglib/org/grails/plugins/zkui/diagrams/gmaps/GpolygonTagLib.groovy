package org.grails.plugins.zkui.diagrams.gmaps

import org.grails.plugins.zkui.AbstractTagLib

class GpolygonTagLib extends AbstractTagLib {
    static namespace = "z"

    def gpolygon = {attrs, b ->
        doTag(attrs, b, servletContext, request, response, pageScope, out)
    }

    Class getComponentClass() {
        return org.zkoss.gmaps.Gpolygon
    }
}