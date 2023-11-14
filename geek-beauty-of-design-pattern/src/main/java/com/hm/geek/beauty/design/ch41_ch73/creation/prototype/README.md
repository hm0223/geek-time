# 原型模式 Prototype

原型模式的实现方式：深拷贝和浅拷贝我们来看，在内存中，用散列表组织的搜索关键词信息是如何存储的。我画了一张示意图，大致结构如下所示。从图中我们可以发现，散列表索引中，每个结点存储的 key 是搜索关键词，value 是 SearchWord 对象的内存地址。SearchWord 对象本身存储在散列表之外的内存空间中。


## 浅拷贝
仅拷贝地址引用，一旦修改 拷贝源数据和目标数据都将会被修改

## 深拷贝
拷贝对象实例，跳过 `new` 关键字，直接通过已有对象 复制一份一样的数据，拷贝对象修改不会影响到源拷贝对象

方式1：
递归拷贝对象、对象的引用对象以及引用对象的引用对象……直到要拷贝的对象只包含基本数据类型数据，没有引用对象为止。

方式2：
先将对象序列化，然后再反序列化成新的对象。
```java
package com.hm.geek.beauty.design.ch41_ch73.creation.prototype;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Demo {

    @SneakyThrows
    public Object deepCopy(Object object) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(object);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);

        return oi.readObject();
    }

}
```