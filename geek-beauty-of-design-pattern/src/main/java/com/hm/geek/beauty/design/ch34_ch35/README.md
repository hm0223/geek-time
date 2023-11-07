
# ID 生成器 LAB 

[TOC]

## 34 | 实战一（上）：通过一段ID生成器代码，学习如何发现代码质量问题

### ID 生成器需求背景介绍
“ID”中文翻译为“标识（Identifier）”。
假设你正在参与一个后端业务系统的开发，为了方便在请求出错时排查问题，我们在编写代码的时候会在关键路径上打印日志。某个请求出错之后，我们希望能搜索出这个请求对应的所有日志，以此来查找问题的原因。而实际情况是，在日志文件中，不同请求的日志会交织在一起。如果没有东西来标识哪些日志属于同一个请求，我们就无法关联同一个请求的所有日志。这听起来有点像微服务中的调用链追踪。不过，微服务中的调用链追踪是服务间的追踪，我们现在要实现的是服务内的追踪。借鉴微服务调用链追踪的实现思路，我们可以给每个请求分配一个唯一 ID，并且保存在请求的上下文（Context）中，比如，处理请求的工作线程的局部变量中。在 Java 语言中，我们可以将 ID 存储在 Servlet 线程的 ThreadLocal 中，或者利用 Slf4j 日志框架的 MDC（Mapped Diagnostic Contexts）来实现（实际上底层原理也是基于线程的 ThreadLocal）。每次打印日志的时候，我们从请求上下文中取出请求 ID，跟日志一块输出。这样，同一个请求的所有日志都包含同样的请求 ID 信息，我们就可以通过请求 ID 来搜索同一个请求的所有日志了。好了，需求背景我们已经讲清楚了，至于具体如何实现整个需求，我就不展开来讲解了。如果你感兴趣的话，可以自己试着去设计实现一下。我们接下来只关注其中生成请求 ID 这部分功能的开发。


### 一份“能用”的代码实现
假设 leader 让小王负责这个 ID 生成器的开发。对于稍微有点开发经验的程序员来说，实现这样一个简单的 ID 生成器，并不是件难事。所以，小王很快就完成了任务，将代码写了出来，具体如下所示：
```java
public class IdGenerator {
  private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

  public static String generate() {
    String id = "";
    try {
      String hostName = InetAddress.getLocalHost().getHostName();
      String[] tokens = hostName.split("\\.");
      if (tokens.length > 0) {
        hostName = tokens[tokens.length - 1];
      }
      char[] randomChars = new char[8];
      int count = 0;
      Random random = new Random();
      while (count < 8) {
        int randomAscii = random.nextInt(122);
        if (randomAscii >= 48 && randomAscii <= 57) {
          randomChars[count] = (char)('0' + (randomAscii - 48));
          count++;
        } else if (randomAscii >= 65 && randomAscii <= 90) {
          randomChars[count] = (char)('A' + (randomAscii - 65));
          count++;
        } else if (randomAscii >= 97 && randomAscii <= 122) {
          randomChars[count] = (char)('a' + (randomAscii - 97));
          count++;
        }
      }
      id = String.format("%s-%d-%s", hostName,
              System.currentTimeMillis(), new String(randomChars));
    } catch (UnknownHostException e) {
      logger.warn("Failed to get the host name.", e);
    }

    return id;
  }
}
```

上面的代码生成的 ID 示例如下所示。整个 ID 由三部分组成。第一部分是本机名的最后一个字段。第二部分是当前时间戳，精确到毫秒。第三部分是 8 位的随机字符串，包含大小写字母和数字。尽管这样生成的 ID 并不是绝对唯一的，有重复的可能，但事实上重复的概率非常低。对于我们的日志追踪来说，极小概率的 ID 重复是完全可以接受的。

```shell
103-1577456311467-3nR3Do45
103-1577456311468-0wnuV5yw
103-1577456311468-sdrnkFxN
103-1577456311468-8lwk0BP0
```

### 如何发现代码质量问题
从大处着眼的话，我们可以参考之前讲过的代码质量评判标准，看这段代码是否可读、可扩展、可维护、灵活、简洁、可复用、可测试等等。落实到具体细节，
我们可以从以下几个方面来审视代码。

- 目录设置是否合理、模块划分是否清晰、代码结构是否满足"高内聚、松耦合"？
- 是否遵循经典的设计原则和设计思想（SOLID、DRY、KISS、YAGNI、LOD等）？
- 设计模式是否应用得当？是否有过度设计？
- 代码是否容器扩展？如果要添加新功能，是否容易实现？
- 代码是否可以复用？是否可以复用已有的项目代码或类库？是否有重复造轮子？
- 代码是否容易测试？单元测试是否全面覆盖了各种正常和异常的情况？
- 代码是否易读？是否符合编码规范（比如命名和注释是否恰当、代码风格是否一致等）？

以上是一些通用的关注点，可以作为常规检查项，套用在任何代码的重构上。除此之外，我们还要关注代码实现是否满足业务本身特有的功能和非功能需求。我罗列了一些比较有共性的问题，如下所示。这份列表可能还不够全面，剩下的需要你针对具体的业务、具体的代码去具体分析。

- 代码是否实现了预期的业务需求？
- 逻辑是否正确？是否处理了何种异常情况？
- 日期打印是否得当？是否方便debug排查问题？
- 接口是否易用？是否支持幂等、事务等？
- 代码是否存在并发问题？是否线程安全？
- 性能是否有优化空间，比如，SQL、算法是否可以优化？
- 是否有安全漏洞？比如输入输出安全校验是否全面？


## 35 | 实战一（下）：手把手带你将ID生成器代码从“能用”重构为“好用”

### 制定重构计划

- 第一轮重构：提高代码的可读性 
- 第二轮重构：提高代码的可测试性
- 第三轮重构：编写完善的单元测试
- 第四轮重构：所有重构完成之后添加注释

### 第一轮重构：提高代码的可读性
提高代码的可读性首先，我们要解决最明显、最急需改进的代码可读性问题。具体有下面几点：
- hostName 变量不应该被重复使用，尤其当这两次使用时的含义还不同的时候
- 将获取 hostName 的代码抽离出来，定义为 getLastfieldOfHostName() 函数；
- 删除代码中的魔法数，比如，57、90、97、122；
- 将随机数生成的代码抽离出来，定义为 generateRandomAlphameric() 函数；
- generate() 函数中的三个 if 逻辑重复了，且实现过于复杂，我们要对其进行简化；
- 对 IdGenerator 类重命名，并且抽象出对应的接口

### 第二轮重构：提高代码的可测试性

- generate() 函数定义为静态函数，会影响使用该函数的代码的可测试性
- generate() 函数的代码实现依赖运行环境（本机名）、时间函数、随机函数，所以 generate() 函数本身的可测试性也不好。

### 第三轮重构：编写完善的单元测试
详见 单元测试代码

### 第四轮重构：添加注释
详见 refactor4 包