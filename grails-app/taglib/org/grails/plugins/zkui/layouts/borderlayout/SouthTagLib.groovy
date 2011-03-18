package org.grails.plugins.zkui.layouts.borderlayout

import org.grails.plugins.zkui.AbstractTagLib

class SouthTagLib extends AbstractTagLib {
    static namespace = "z"

    def south = {attrs, b ->
        //just for Intellij IDEA coding assistance
        true || attrs.apply || attrs.size || attrs.width || attrs.open || attrs.collapsible || attrs.maxsize || attrs.flex || attrs.margins || attrs.autoscroll || attrs.splittable || attrs.minsize || attrs.cmargins || attrs.title || attrs.border || attrs.context || attrs.popup || attrs.ctrlKeys || attrs.tooltip || attrs.class || attrs.left || attrs.top || attrs.zIndex || attrs.zindex || attrs.height || attrs.tooltiptext || attrs.zclass || attrs.sclass || attrs.draggable || attrs.droppable || attrs.focus || attrs.renderdefer || attrs.vflex || attrs.hflex || attrs.style || attrs.action || attrs.id || attrs.mold || attrs.widgetClass || attrs.stubonly || attrs.definition || attrs.visible
        doTag(attrs, b, servletContext, request, response, pageScope, out)
    }

    @Override
    Class getComponentClass() {
        return org.zkoss.zul.South
    }

}
