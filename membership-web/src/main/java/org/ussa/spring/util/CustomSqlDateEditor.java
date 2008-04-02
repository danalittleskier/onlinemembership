package org.ussa.spring.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomSqlDateEditor extends PropertyEditorSupport
{
    DateFormat format = null;

    private final boolean allowEmpty;

    public CustomSqlDateEditor(DateFormat format, boolean allowEmpty)
    {
        super();
        this.format = format;
        this.allowEmpty = allowEmpty;
    }

    public String getAsText()
    {
        Date value = (Date) getValue();
        return (value != null ? this.format.format(new Date(value.getTime())) : "");
    }

    public void setAsText(String text) throws IllegalArgumentException
    {
        if (this.allowEmpty && !StringUtils.hasText(text))
        {
            // Treat empty String as null value.
            setValue(null);
        }
        else
        {
            try
            {
                setValue(new Date(this.format.parse(text).getTime()));
            } catch (ParseException e)
            {
                throw new IllegalArgumentException("Could not parse date: " + e.getMessage());
            }
        }
    }

}
