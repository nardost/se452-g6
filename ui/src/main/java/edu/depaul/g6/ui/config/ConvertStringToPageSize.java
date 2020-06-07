package edu.depaul.g6.ui.config;

import org.springframework.core.convert.converter.Converter;


public class ConvertStringToPageSize implements Converter<String, PageSize> {
    @Override
    public PageSize convert(String value) {
        if (value.equals("20"))      return PageSize.TWENTY;
        else if (value.equals("30")) return PageSize.THIRTY;
        return PageSize.TEN;
    }
}
