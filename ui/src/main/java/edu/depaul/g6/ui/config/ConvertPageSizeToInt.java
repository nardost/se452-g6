package edu.depaul.g6.ui.config;

import org.springframework.core.convert.converter.Converter;


public class ConvertPageSizeToInt implements Converter<PageSize, Integer> {
    @Override
    public Integer convert(PageSize value) {
        switch (value) {
            case TEN:    return 10;
            case TWENTY: return 20;
            case THIRTY: return 30;
        }
        return null;
    }
}
