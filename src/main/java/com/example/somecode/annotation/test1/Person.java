package com.example.somecode.annotation.test1;


public class Person {
    private String name;
    private String mobile;
    private Integer age;
    private String sex;
    public Person(){}
    public Person(String name,String mobile,Integer age,String sex){
        this.name=name;
        this.mobile=mobile;
        this.age=age;
        this.sex=sex;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    public void setAge(Integer age){
        this.age=age;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getName(){
        return this.name;
    }
    @ArgIntercept
    public String getMobile(){
        return this.mobile;
    }
    @ArgIntercept(required = false)
    public Integer getAge(){
        return this.age;
    }
    public String getSex(){
        return this.sex;
    }
}
