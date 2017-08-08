package com.lovecws.mumu.common.template.velocity;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

/**
 * Created by Administrator on 2017/3/17.
 */
@DefaultKey("escape")
@ValidScope(Scope.APPLICATION)
public class WebEscapeHtmlTag {

    /**
     * 转义html
     * @param value
     * @return
     */
    public String html(Object value) {
        if (value == null)
            return null;

        if (value instanceof String) {
            String result = value.toString();
            // "'<>&
            result = result.replaceAll("&amp;", "&").
                    replaceAll("&gt;", ">").
                    replaceAll("&lt;", "<").
                    replaceAll("&quot;", "\"").
                    replaceAll("↵","");
            return result;
        } else {
            return value.toString();
        }
    }

    public String escapeHtml(Object value) {
        if (value == null)
            return null;

        if (value instanceof String) {
            String result = value.toString();
            // "'<>&
            result = result.replaceAll("&", "&amp;").replaceAll(">", "&gt;")
                    .replaceAll("<", "&lt;").replaceAll("\"", "&quot;");
            return result;
        } else {
            return value.toString();
        }
    }
}
