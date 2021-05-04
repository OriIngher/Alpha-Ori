package com.example.alpha_ori;

public class Exercise {

    private int exeType, setNum, reps, weight;
    private String exeName;


    public Exercise(){};
    public Exercise(int exeType, int setNum , int reps , int weight, String exeName)
    {
        this.exeName =exeName;
        this.exeType=exeType;
        this.reps=reps;
        this.setNum=setNum;
        this.weight=weight;
    }
    public String getExeName(String exeName) {
        return exeName;
    }
    public void setExeName(String exeName) {
        this.exeName=exeName;
    }

    public int getExeType() {
        return exeType;
    }
    public void setExeType(int exeType) {
        this.exeType=exeType;
    }

    public int getReps(int reps) {
        return reps;
    }
    public void setReps(int reps) {
        this.reps=reps;
    }
    public int getSetNum() {
        return setNum;
    }
    public void setSetNum(int setNum) {
        this.setNum=setNum;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight=weight;
    }

}