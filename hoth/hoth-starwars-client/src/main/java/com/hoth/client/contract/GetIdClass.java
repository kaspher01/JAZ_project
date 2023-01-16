package com.hoth.client.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class GetIdClass {

    private String url;

    public int getId() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            String number = matcher.group();
            return Integer.parseInt(number);
        }
        return -1;
    }

}
