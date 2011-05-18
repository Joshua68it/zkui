package org.grails.plugins.zkui.util;

public class UriUtil {
    public static String fixToZk(String uri, String contextPath) {
        if (uri != null && uri.startsWith(contextPath)) return uri.replaceFirst(contextPath, "");
        return uri;
    }
}
