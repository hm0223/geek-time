# Design-Pattern

# Doc

[(Back to top)](#table-of-contents)

# Table of contents

- [Design-Pattern](#design-pattern)
- [Doc](#doc)
- [Table of contents](#table-of-contents)
- [Creation](#creation)
    - [Singleton](#singleton)
    - [Factory](#factory)
    - [Builder](#builder)
    - [Prototype](#prototype)
- [Structural](#structural)
    - [Proxy](#proxy)
    - [Bridge](#bridge)
    - [Decorator](#decorator)

# Creation

[(Back to top)](#table-of-contents)

## Singleton

[(Back to top)](#table-of-contents)

## Factory

[(Back to top)](#table-of-contents)

## Builder

[(Back to top)](#table-of-contents)

## Prototype

[(Back to top)](#table-of-contents)

# Structural

[(Back to top)](#table-of-contents)

## Proxy

[(Back to top)](#table-of-contents)

## Bridge

[(Back to top)](#table-of-contents)

## Decorator

[(Back to top)](#table-of-contents)



# Create By
> see: https://github.com/hm0223/markdown-gen-tool

```java
package com.hm223.md;

import com.hm223.md.rest.GenMarkdownTemplateEndpoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenMarkdownTemplateEndpoint.class)
class MarkdownGenToolApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  @DisplayName("Gen Markdown File Test")
  void genMarkdownFileTest() throws Exception {
    String requestBody = "[\n" +
            "  {\"code\":\"1\", \"name\":\"Install\"},\n" +
            "  {\"code\":\"2\", \"name\":\"Table of contents\"},\n" +
            "  {\"code\":\"3\", \"name\":\"Creation\"},\n" +
            "  {\"code\":\"31\", \"name\":\"Singleton\"},\n" +
            "  {\"code\":\"32\", \"name\":\"Factory\"},\n" +
            "  {\"code\":\"32\", \"name\":\"Builder\"},\n" +
            "  {\"code\":\"32\", \"name\":\"Prototype\"},\n" +
            "  {\"code\":\"4\", \"name\":\"Structural\"},\n" +
            "  {\"code\":\"41\", \"name\":\"Proxy\"},\n" +
            "  {\"code\":\"42\", \"name\":\"Bridge\"},\n" +
            "  {\"code\":\"43\", \"name\":\"Decorator\"}\n" +
            "]";
    mvc.perform(MockMvcRequestBuilders
                    .post("/gen/Design-Pattern")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
  }
}
```