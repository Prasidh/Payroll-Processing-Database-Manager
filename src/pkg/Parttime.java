/**
 * 
 * @author Manveer Singh, Prasish Sriram
 */
package pkg;

public class Parttime extends Employee{

    int hoursWorked; // Hours worked this period

    public Parttime(Profile parttimeProfile, Double payRate) {
        super(parttimeProfile, payRate);
        this.hoursWorked = 0;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }
}
