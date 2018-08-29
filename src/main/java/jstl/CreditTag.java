package jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreditTag extends TagSupport implements DynamicAttributes {
    private Map<String,Object> map = new HashMap<>();

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<tr>");
            for(String key : map.keySet()){
                buffer.append("<th>");
                buffer.append(key);
                buffer.append("</th>");
                buffer.append("<td>");
                buffer.append(map.get(key).toString());
                buffer.append("</td>");

            }
            buffer.append("</tr>");
            /*
            for( String key : map.keySet() ){
                buffer.append( "<td>" )
                        .append( map.get(key) )
                        .append( " - " )
                        .append( map.get( name ) )
                        .append( "</li>" );
            }
            buffer.append( "</ul>" );
            */
            pageContext.getOut().print( buffer.toString() );
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public void setDynamicAttribute(String s, String key, Object o) throws JspException {
        map.put(key,o);
    }
}
