package com.hm.geek.beauty.design.ch41_ch73.creation.prototype;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * DeepCopyWayOfTwo.
 * 
 * @author huwenfeng
 */
@Slf4j
public class DeepCopyWayOfTwo {

  @SneakyThrows
  public static Object deepCopy(Object object) {
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    ObjectOutputStream oo = new ObjectOutputStream(bo);
    oo.writeObject(object);

    ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
    ObjectInputStream oi = new ObjectInputStream(bi);

    return oi.readObject();
  }


  public static void main(String[] args) {
    DeepCopyWayOfOne.SearchWord searchWord = new DeepCopyWayOfOne.SearchWord();
    List<String> subKeywords = new ArrayList<>();
    subKeywords.add("sub1");
    searchWord.setSubKeywords(subKeywords);
    searchWord.setCount(1);
    searchWord.setLastUpdateTime(System.currentTimeMillis());
    searchWord.setKeyword("kw1");
    log.info("searchWord = " + searchWord);

    DeepCopyWayOfOne.SearchWord clone = (DeepCopyWayOfOne.SearchWord) deepCopy(searchWord);
    clone.getSubKeywords().add("kw2");
    log.info("clone = " + clone);
    log.info("searchWord = " + searchWord);
  }

}