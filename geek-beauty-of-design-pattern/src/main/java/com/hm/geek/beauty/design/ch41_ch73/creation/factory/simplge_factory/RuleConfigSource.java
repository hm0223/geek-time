package com.hm.geek.beauty.design.ch41_ch73.creation.factory.simplge_factory;

import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.IRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.JsonRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory.XmlRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method.InvalidRuleConfigException;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method.PropertiesRuleConfigParser;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method.RuleConfig;
import com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method.YamlRuleConfigParser;

/**
 * RuleConfigSource.
 *
 * @author huwenfeng
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new PropertiesRuleConfigParser();
        }
        if (parser == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}