package com.hm.geek.beauty.design.ch41_ch73.creation.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * DeepCopyWayOfTwo.
 * 
 * @author huwenfeng
 */
@Slf4j
public class DeepCopyWayOfOne {
  
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class SearchWord implements Cloneable, Serializable {

    private String keyword;
    private List<String> subKeywords;
    private int count;
    private Long lastUpdateTime;


    @SneakyThrows
    @Override
    protected SearchWord clone() {
      SearchWord clone = (SearchWord) super.clone();
      clone.setSubKeywords(new ArrayList<>(this.subKeywords));
      return clone;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", SearchWord.class.getSimpleName() + "[", "]")
              .add("keyword='" + keyword + "'")
              .add("subKeywords=" + subKeywords)
              .add("count=" + count)
              .add("lastUpdateTime=" + lastUpdateTime)
              .toString();
    }
  }

  public static void main(String[] args) {
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