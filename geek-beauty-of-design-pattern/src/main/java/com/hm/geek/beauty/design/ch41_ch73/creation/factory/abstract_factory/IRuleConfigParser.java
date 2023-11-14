package com.hm.geek.beauty.design.ch41_ch73.creation.factory.abstract_factory;

import com.hm.geek.beauty.design.ch41_ch73.creation.factory.factory_method.RuleConfig;

/**
 * IRuleConfigParser.
 *
 * @author huwenfeng
 */
public interface IRuleConfigParser {
    RuleConfig parse(String configText);
}
