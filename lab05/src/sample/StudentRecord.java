package sample;

public class StudentRecord {
    private String SID;
    private double midterm;
    private double assignment;
    private double fExam;
    private double fMark;
    private String lGrade;

    public StudentRecord(String SID, double assignment, double midterm, double fExam){
        this.SID = SID;
        this.assignment = assignment;
        this.midterm = midterm;
        this.fExam = fExam;
    }

    public String getSID(){
        return this.SID;
    }

    public double getAssignment(){
        return this.assignment;
    }

    public double getMidterm(){
        return this.midterm;
    }

    public double getFExam(){
        return this.fExam;
    }

    public double getFMark(){
        this.fMark = (this.assignment*0.2) + (this.midterm*0.3) + (this.fExam*0.5);
        return this.fMark;
    }

    public String getLGrade(){
        if (this.fMark >= 80 && this.fMark <= 100){
            return "A";
        }
        else if (this.fMark >= 70 && this.fMark <= 79){
            return "B";
        }
        else if (this.fMark >= 60 && this.fMark <= 69){
            return "C";
        }
        else if (this.fMark >= 50 && this.fMark <= 59){
            return "D";
        }
        else if (this.fMark >= 0 && this.fMark <= 49){
            return "F";
        }
        else{
            return "0";
        }
    }
}
