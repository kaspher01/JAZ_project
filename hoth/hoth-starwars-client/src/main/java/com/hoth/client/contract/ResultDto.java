package com.hoth.client.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultDto {

    private int count;
    private String next;
    private String previous;
    private List<FilmDto> results;
}
