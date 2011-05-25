package org.grails.plugins.zkui

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.metainfo.EventHandler
import org.zkoss.zk.ui.metainfo.Property
import org.zkoss.zk.ui.metainfo.ZScript
import org.zkoss.zk.ui.sys.ComponentCtrl
import org.zkoss.zul.Html

abstract class AbstractTagLib {

    void doTag(attrs, body, ServletContext servletContext, HttpServletRequest request,
               HttpServletResponse response, Binding pageScope, Writer out) {
        def composeHandle = new ComposerHandler(attrs.remove("apply"))
        if (!pageScope.variables.containsKey("parent")) pageScope.parent = new LinkedList<Component>()
        Component component = (Component) componentClass.newInstance()
        composeHandle.doBeforeComposeChildren(component)
        pageScope.parent.push(component)
        if (pageScope.parent.size() > 1) pageScope.parent[pageScope.parent.size() - 2].appendChild(component)
        attrs.each {String attrName, value ->
            if (attrName.startsWith("on")) {
                final ZScript zScript = ZScript.parseContent(value.toString())
                zScript.language = "groovy"
                ((ComponentCtrl) component).addEventHandler(attrName, new EventHandler(zScript, null))
            } else if (attrName.startsWith("client_")) {
                component.setWidgetListener(attrName.toString().replaceFirst("client_", ''), value.toString())
            } else {
                def attrType = component.metaClass.getMetaProperty(attrName)?.type
                if (attrType?.isPrimitive() || attrType in String || attrType in Number || attrType in Boolean || attrType in Character || attrType in org.zkoss.zul.Constraint || attrType == null) {
                    Property.assign(component, attrName, value.toString())
                } else {
                    component[attrName] = value
                }
            }
        }
        String content = body.call()
        if (content && !content.allWhitespace) {
            if (component.metaClass.respondsTo(component, 'setContent', String)) {
                component.content = content
            } else {
                component.appendChild(new Html(content))
            }
        }
        composeHandle.doAfterCompose(component)
        if (pageScope.parent.size() == 1) {
            Renders.render(servletContext, request, response, (Component) pageScope.parent.remove(), null, out)
        } else {
            pageScope.parent.pop()
        }
    }

    abstract Class getComponentClass()
}