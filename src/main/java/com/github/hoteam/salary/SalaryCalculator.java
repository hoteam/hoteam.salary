package com.github.hoteam.salary;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description：工资计算器
 * @ Author     ：丁明威
 * @ Date       ：Created in 19:51 2019/7/10
 * @ Modified By：
 */
public class SalaryCalculator {

    public static final Float SB_RATIO = 0.08F + 0.02F + 0.005F;
    public static final Float GJ_RATIO = 0.12F;

    /**
     * 月工资
     */
    private Float salary;
    /**
     * 社保基数或者五险一金个人缴费数额
     */
    private Float shebao;
    /**
     * 公积金基数
     */
    private Float gongjj;
    /**
     * 免税金额：包括5000+专项扣除金额
     */
    private Float noFax;

    /**
     * @param salary 月工资
     * @param shebao 社保基数
     * @param gongjj 公积金基数
     * @param noFax  免税基数
     */
    public SalaryCalculator(Float salary, Float shebao, Float gongjj, Float noFax) {
        this.salary = salary;
        this.shebao = shebao;
        this.gongjj = gongjj;
        this.noFax = noFax;
    }

    /**
     * @param salary 月工资
     * @param shebao 五险一金个人缴费数额
     * @param noFax  免税基数
     */
    public SalaryCalculator(Float salary, Float shebao, Float noFax) {
        this.salary = salary;
        this.shebao = shebao;
        this.noFax = noFax;
    }

    public static void main(String[] args) throws Exception {
        //适用单独知道社保基数和公积金基数的情况
        SalaryCalculator calculator = new SalaryCalculator(30000F, 4000F, 6000F, 6500F);
        calculator.calc(calculator.init());
        //适用知道五险一金个人缴费金额的情况
        SalaryCalculator calculator2 = new SalaryCalculator(30000F, 4500F, 7000F);
        calculator2.calc(calculator2.init0());
    }

    public List<Salary> init0() throws Exception {
        List<Salary> salaries = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            salaries.add(new Salary(i + 1, this.salary, this.shebao, this.noFax));
        }
        return salaries;
    }

    public List<Salary> init() {
        List<Salary> salaries = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            salaries.add(new Salary(i + 1, this.salary, this.shebao * SB_RATIO + this.gongjj * GJ_RATIO, this.noFax));
        }
        return salaries;
    }


    public List<Salary> calc(List<Salary> salaries) {
        Float need2Fax = 0.0F;
        Float totalFax = 0.0F;
        Float totalSalary = 0.0F;
        for (Salary salary : salaries) {
            System.out.println("----------------------当前月份:" + salary.getMonth() + "月------------------------");
            need2Fax += salary.getNeed2Fax();
            Ratio ratio = RatioCalculator.getRatio(need2Fax);
            System.out.println("当月应纳税工资 = " + need2Fax + " 元  | 适用税率=" + ratio.getRatio() + " | 速算扣除数 = " + ratio.getFastFax());
            Float fax = need2Fax * ratio.getRatio() - ratio.getFastFax() - totalFax;
            System.out.println("当月应纳税额 = " + need2Fax + " * " + ratio.getRatio() + " - " + ratio.getFastFax() + " - "
                    + totalFax + " = " + fax + " 元");
            totalFax += fax;
            Float last = salary.getSalary() - salary.getSbfy() - fax;
            System.out.println("当月应发工资 = " + salary.getSalary() + " - " + salary.getSbfy() + " - " + fax + " = " + last + " 元");
            salary.setLast(last);
            totalSalary += last;
            System.out.println("截止当月以累计纳税 = " + totalFax + " 元");
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("全年累计纳税=" + totalFax + " 元");
        System.out.println("全年累计工资=" + totalSalary + " 元");
        System.out.println("---------------------------------------------------------");
        return salaries;
    }

}