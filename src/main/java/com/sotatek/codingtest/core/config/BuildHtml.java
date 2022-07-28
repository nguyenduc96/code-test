package com.sotatek.codingtest.core.config;

import java.util.Map;

public class BuildHtml {
    public static String build(String title, String actionUrl, Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        builder.append("<HTML>")
                .append("<HEAD>");
        if (title != null) {
            builder.append("<TITLE>" + title + "</TITLE>");
        }
        builder.append("</HEAD>")
                .append("<BODY Onload=\"document.forms[0].submit()\">")

                .append("<FORM METHOD=\"POST\" ACTION=\"").append(actionUrl).append("\">");
        for (Map.Entry<String, Object> param : params.entrySet()) {
            builder.append("<INPUT TYPE=\"HIDDEN\" NAME=\"").append(param.getKey())
                    .append("\"")
                    .append(" VALUE=\"")
                    .append(param.getValue()).append("\"/>");
        }


        builder.append("<NOSCRIPT>")
                .append("<P>JavaScript is disabled. We strongly recommend to enable it. Click the button below to continue.</P>")
                .append("<INPUT TYPE=\"SUBMIT\" VALUE=\"CONTINUE\" />")
                .append("</NOSCRIPT>")

                .append("</FORM></BODY></HTML>");

        return builder.toString();
    }
}
