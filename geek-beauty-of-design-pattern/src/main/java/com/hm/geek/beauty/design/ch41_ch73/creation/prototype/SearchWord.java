package com.hm.geek.beauty.design.ch41_ch73.creation.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchWord.
 *
 * @author huwenfeng
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class SearchWord implements Cloneable {

    private String keyword;
    private List<String> subKeywords;
    private int count;
    private Long lastUpdateTime;


    @SneakyThrows
    @Override
    protected SearchWord clone() {
        return (SearchWord) super.clone();
    }

    public static void copy() {
        SearchWord searchWord = new SearchWord();
        List<String> subKeywords = new ArrayList<>();
        subKeywords.add("sub1");
        searchWord.setSubKeywords(subKeywords);
        searchWord.setCount(1);
        searchWord.setLastUpdateTime(System.currentTimeMillis());
        searchWord.setKeyword("kw1");
        log.info("searchWord = " + searchWord);

        SearchWord clone = searchWord.clone();
        clone.getSubKeywords().add("kw2");
        log.info("clone = " + clone);
        log.info("searchWord = " + searchWord);
    }
}
