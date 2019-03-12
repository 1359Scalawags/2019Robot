package org.usfirst.frc.team1359.robot;

public class SoftenOutput{
    private int samplesize;
    private double total = 0;
    private int index = 0;
    private double samples[];

    public SoftenOutput(int samplesize){
        this.samplesize = samplesize;
        samples = new double[samplesize];
        for(int i=0; i < samplesize; i++) samples[i] = 0d;
    }
    public double getOutput(double input){
        total -= samples[index];
        samples[index] = input;
        total += input;
        if(index < samplesize - 1){
            index++;
        }
        else{
            index = 0;
        }
        return total/samplesize;
    }
}