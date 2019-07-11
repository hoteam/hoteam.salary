package com.github.hoteam.salary;

/**
 * @ Description：税率计算器
 * @ Author     ：丁明威
 * @ Date       ：Created in 8:46 2019/7/11
 * @ Modified By：
 */
public class RatioCalculator {
    public static Ratio getRatio(Float totalSalary) {
        if (totalSalary < 36000) {
            return new Ratio(0, 0.03F);
        } else if ((totalSalary < 144000)) {
            return new Ratio(2520, 0.10F);
        } else if (totalSalary < 300000) {
            return new Ratio(16920, 0.20F);
        } else if (totalSalary < 420000) {
            return new Ratio(31920, 0.25F);
        } else if (totalSalary < 660000) {
            return new Ratio(52920, 0.30F);
        } else if (totalSalary < 960000) {
            return new Ratio(85920, 0.35F);
        } else if (totalSalary >= 960000) {
            return new Ratio(181920, 0.45F);
        } else {
            return null;
        }
    }
}