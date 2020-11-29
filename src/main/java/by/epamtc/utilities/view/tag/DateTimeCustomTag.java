package by.epamtc.utilities.view.tag;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DateTimeCustomTag extends TagSupport {
    private final static Logger log = Logger.getLogger(DateTimeCustomTag.class);

    private DateTime dateTime;

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            final JspWriter out = pageContext.getOut();
            out.write(dateTimeConvert());
        } catch (IOException e) {
            log.error("Write date error", e);
        }
        return SKIP_BODY;
    }

    private String dateTimeConvert(){
        if (dateTime == null){
            dateTime = new DateTime();
        }

        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm");

        return dateTime.toString(dateTimeFormatter).replace(" ", "T");
    }
}
