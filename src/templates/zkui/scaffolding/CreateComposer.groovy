<% import grails.persistence.Event %><%=packageName ? "package ${packageName}\n\n" : ''%>import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.event.Event
import org.zkoss.zul.*
import ${domainClass.fullName}

class CreateComposer {
    static transactional = true
    Window self
    def afterCompose = {Component comp ->
        //todo initialize components here
    }

    void onClick_saveButton(Event e) {
        def ${propertyName} = new ${className}(self.params)
        if (!${propertyName}.save() && ${propertyName}.hasErrors()) {
            log.error ${propertyName}.errors
            self.renderErrors(bean: ${propertyName})
        } else {
            redirect(controller: "${domainClass.propertyName}", action: "list")
        }
    }
}