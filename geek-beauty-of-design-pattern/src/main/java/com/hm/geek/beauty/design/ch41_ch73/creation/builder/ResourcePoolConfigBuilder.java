package com.hm.geek.beauty.design.ch41_ch73.creation.builder;

import org.springframework.util.ObjectUtils;

/**
 * ResourcePoolConfigBuilderSamples.
 *
 * @author huwenfeng
 */
@SuppressWarnings("all")
class ResourcePoolConfigBuilderSamples {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    private ResourcePoolConfigBuilderSamples(Builder builder) {
      this.name = builder.name;
      this.maxTotal = builder.maxTotal;
      this.maxIdle = builder.maxIdle;
      this.minIdle = builder.minIdle;
    }

    public void setMaxTotal(int maxTotal) {
        if (maxTotal <= 0) {
            throw new IllegalArgumentException("maxTotal should be positive.");
        }
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        if (maxIdle < 0) {
            throw new IllegalArgumentException("maxIdle should not be negative.");
        }
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        if (minIdle < 0) {
            throw new IllegalArgumentException("minIdle should not be negative.");
        }
        this.minIdle = minIdle;
    }
    // ...省略getter方法...


    @Override
    public String toString() {
        return "ResourcePoolConfigBuilderSamples{" +
                "name='" + name + '\'' +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                '}';
    }

    static class Builder {
        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public Builder(String name) {
            if (ObjectUtils.isEmpty(name)) {
                throw new IllegalArgumentException("name should not be empty.");
            }
            this.name = name;
        }

        public Builder maxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder maxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder minIdle(int minIdle) {
            this.minIdle = minIdle;
            return this;
        }

        public ResourcePoolConfigBuilderSamples build() {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等 
            if (ObjectUtils.isEmpty(name)) {
                throw new IllegalArgumentException("...");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }
            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("...");
            }
            return new ResourcePoolConfigBuilderSamples(this);
        }

    }

    public static void main(String[] args) {
        ResourcePoolConfigBuilderSamples samples = new Builder("samples")
                .maxTotal(16)
                .maxIdle(12)
                .minIdle(10)
                .build();
    }
}