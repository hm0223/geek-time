package com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method;

import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.IRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.JsonRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.XmlRuleConfigParser;

/**
 * RuleConfigSource.
 * 
 * @author huwenfeng
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        return parser.parse(configText);
    }

    @SuppressWarnings("unused")
    private String getFileExtension(String filePath) {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}

class RuleConfigParserFactory {
    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}