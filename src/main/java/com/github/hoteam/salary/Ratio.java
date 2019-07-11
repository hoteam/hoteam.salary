package com.github.hoteam.salary;

/**
 * @ Description：税率
 * @ Author     ：丁明威
 * @ Date       ：Created in 8:46 2019/7/11
 * @ Modified By：
 */
public class Ratio {
    private Integer fastFax;//速算扣除
    private Float ratio;//适用税率

    public Ratio(Integer fastFax, Float ratio) {
        this.fastFax = fastFax;
        this.ratio = ratio;
    }

    public Integer getFastFax() {
        return fastFax;
    }

    public Float getRatio() {
        return ratio;
    }
}