package org.ussa.spring.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;

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
        java.sql.Date value = (java.sql.Date) getValue();
        return (value != null ? this.format.format(new java.util.Date(value.getTime())) : "");
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
                setValue(new java.sql.Date(this.format.parse(text).getTime()));
            } catch (ParseException e)
            {
                throw new IllegalArgumentException("Could not parse date: " + e.getMessage());
            }
        }
    }

}
