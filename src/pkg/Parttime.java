/**
 * 
 * @author Manveer Singh, Prasish Sriram
 */
package pkg;

public class Parttime extends Employee{

    private int hoursWorked; // Hours worked this period

    /**
     * 
     */
    public Parttime(Profile parttimeProfile, Double payRate) {
        super(parttimeProfile, payRate);
        this.hoursWorked = 0;
    }

    /**
     * 
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * 
     */
    public void calculatePayments(){

        if(hoursWorked <= 80){

            super.setPayments( hoursWorked * super.getPayRate() );
        }else{
            int overtimeHours = hoursWorked - 80;
            hoursWorked-=80;

            super.setPayments( (hoursWorked*super.getPayRate()) + (overtimeHours*super.getPayRate()*1.5) );
        }
    }

    /**
     * 
     */
    @Override
    public String toString(){
        return null;
    }

    /**
     * 
     */
    @Override
    public boolean equals(Object obj){
        return false;
    }
}
