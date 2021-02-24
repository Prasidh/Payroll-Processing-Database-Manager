/**
 * 
 * @author Manveer Singh, Prasish Sriram
 */
package pkg;

import java.util.Scanner;

public class PayrollProcessing {

    Company newCompany = new Company();
    /**
     * 
     */
    public void run(){

        

        Scanner inputScanner = new Scanner(System.in);

        String input = "";
        while(!input.equals("Q")){
            input = inputScanner.nextLine();
            String [] tokens = input.split(" ");

            // Input is empty
            if(input.equals("") || input == null){
                invalidCommand(input);
                continue;
            }

            // Input is Add
            else if(tokens[0].equals("AM") || tokens[0].equals("AP") || tokens[0].equals("AF")){

                if(tokens.length != 5 && tokens.length != 6){
                    invalidCommand(input);
                    continue;
                }

                char type = tokens[0].charAt(0);
                String name = tokens[1];
                String department = tokens[2];
                if(!validateDepartment(department)){
                    continue;
                }
                Date dateHired = validateDate(tokens[3]);
                if(dateHired == null){
                    continue;
                }
                Double payRate = (double) -1;
                try{
                    payRate = Double.parseDouble(tokens[4]);
                }catch(Exception e){
                    
                }

                // Add a part time employee
                if(type == 'P'){
                    addParttime(name, department, dateHired, payRate);
                }

                // Add a full time employee
                if(type == 'F'){
                    addFulltime(name, department, dateHired, payRate);
                }

                // Add a Manager
                else{
                    int managerType;
                    try{
                        managerType = Integer.parseInt(tokens[5]);
                    }catch(Exception e){
                        System.out.println("Invalid management code.");
                        continue;
                    }
                    
                    if(!validateManagementCode(managerType)){
                        continue;
                    }
                    addManager(name, department, dateHired, payRate, managerType);
                }
            }
            
            // Input is Remove
            else if(tokens[0].equals("R")){

                if(tokens.length != 4){
                    invalidCommand(input);
                    continue;
                }

                String name = tokens[1];
                String department = tokens[2];
                if(!validateDepartment(department)){
                    continue;
                }
                Date dateHired = validateDate(tokens[3]);
                if(dateHired == null){
                    continue;
                }
            }
            
            // Input is Calculate
            else if(input.equals("C")){
                calculatePayments();
            }

            // Input is Set Hours
            else if(tokens[0].equals("S")){
                if(tokens.length != 5){
                    invalidCommand(input);
                    continue;
                }

                String name = tokens[0];
                String department = tokens[1];
                if(!validateDepartment(department)){
                    continue;
                }
                Date dateHired = validateDate(tokens[2]);
                if(dateHired == null){
                    continue;
                }


                

            }

            // Input is Print
            else if(input.equals("PA") || input.equals("PH") || input.equals("PD")){

                if(input.equals("PA")){
                    newCompany.print();
                }else if(input.equals("PH")){
                    newCompany.printByDate();
                }else if(input.equals("PD")){
                    newCompany.printByDepartment();
                }
            }

            // Input is Quit
            else if(input.equals("Q")){
                System.out.println("Payroll Processing completed.");
                break;
            }

            // Invalid input
            else{
                invalidCommand(input);
                continue;
            }
        }
        inputScanner.close();
    }

    /**
     * 
     */
    private void addParttime(String name, String department, Date date, Double payRate){
        Profile parttimeProfile = new Profile(name, department, date);
        newCompany.add(new Parttime(parttimeProfile,payRate));

    }

    /**
     * 
     */
    private void addFulltime(String name, String department, Date date, Double payRate){

        Profile fulltimeProfile = new Profile(name, department, date);
        newCompany.add(new Fulltime(fulltimeProfile,payRate));

    }

    /**
     * 
     */
    private void addManager(String name, String department, Date date, Double payRate, int managerType){
        
        Profile managerProfile = new Profile(name, department, date);
        newCompany.add(new Management(managerProfile,payRate, managerType));
    }

    /**
     * 
     */
    private void removeEmployee(){
        //newCompany.remove(employeeProfile);
    }

    /**
     * 
     */
    private void calculatePayments(){
        newCompany.processPayments();
    }

    /**
    * 
    */
    private void invalidCommand(String input){

        System.out.println("Command '"+input+"' not supported!");
    }

    /**
     * 
     */
    private boolean validateDepartment(String department){

        if(department.equals("CS") || department.equals("ECE") || department.equals("IT")){
            return true;
        }
        System.out.println("'"+department+"' is not a valid department code.");
        return false;
        
    }

    /**
     * 
     */
    private Date validateDate(String date){
        Date newDate;
        try{
            newDate = new Date(date);
        }catch(Exception e){
            System.out.println(date + " is not a valid date!");
            return null;
        }
        if(!newDate.isValid()){
            System.out.println(date + " is not a valid date!");
            return null;
        }
            
        return newDate;
    }

    private boolean validateManagementCode(int managementCode){

        if(managementCode  == 1 || managementCode == 2 || managementCode == 3){
            return true;
        }
        else{
            System.out.println("Invalid management code.");
            return false;
        }
    }
}
