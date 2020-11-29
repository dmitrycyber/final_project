package by.epamtc.utilities.view.tag;

import by.epamtc.utilities.controller.command.impl.AuthCommand;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterMessageTag extends TagSupport {
    private final static Logger log = Logger.getLogger(FooterMessageTag.class);
    private String footerMessage;

    public String getFooterMessage() {
        return footerMessage;
    }

    public void setFooterMessage(String footerMessage) {
        this.footerMessage = footerMessage;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            final JspWriter out = pageContext.getOut();
            out.write(footerMessage);
        } catch (IOException e) {
            log.error("Write footer message error", e);
        }
        return SKIP_BODY;
    }
}
