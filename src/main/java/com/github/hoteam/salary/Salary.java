package com.github.hoteam.salary;

/**
 * @ Description：薪水
 * @ Author     ：丁明威
 * @ Date       ：Created in 8:45 2019/7/11
 * @ Modified By：
 */
public class Salary {
    private Float sbfy;//社保费用
    private Float zero;//免税基数
    private Float free;//免税金额

    private Integer month;//当前月份
    private Float salary;//当月工资
    private Float need2Fax;//当月纳税金额

    private Float last;//当月应发薪水

    public Salary(Integer month, Float salary, Float sbfy, Float zero) {
        this.month = month;
        this.salary = salary;
        this.sbfy = sbfy;
        this.zero = zero;
        this.free = this.sbfy + this.zero;
        this.need2Fax = this.salary - free;
    }

    public Float getFree() {
        return free;
    }

    public Integer getMonth() {
        return month;
    }

    public Float getSalary() {
        return salary;
    }

    public Float getNeed2Fax() {
        return need2Fax;
    }

    public void setLast(Float last) {
        this.last = last;
    }

    public Float getSbfy() {
        return sbfy;
    }

    public Float getZero() {
        return zero;
    }

    public Float getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "month=" + month +
                ", salary=" + salary +
                ", free=" + free +
                ", faxSalary=" + need2Fax +
                ", last=" + last +
                '}';
    }
}