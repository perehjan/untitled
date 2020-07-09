import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExerciseTwo {
    List<String> lines;
    List<Transaction> transactions= new ArrayList<>();

    ExerciseTwo(List<String> lines) {
        this.lines = lines;
    }

    void addInformationOfTransaction(){
        int currentTransaction=1;
        int numberOfTransaction=0;
        for (String line: lines) {
            if(line.contains("Transaction "+(1+numberOfTransaction)+" begin:")) {
                String tempDate = line.substring(line.indexOf("[")+1,line.indexOf("]"));
                transactions.add(new Transaction());
                transactions.get(numberOfTransaction).setDate(tempDate);
                numberOfTransaction++;
            }
            if(line.contains("Transaction "+currentTransaction)&&line.contains("end")){
                currentTransaction++;
            }
            if(line.contains("EMV Scheme Matched:")&& numberOfTransaction>=currentTransaction){
                if(transactions.get(currentTransaction-1).getEMV_Scheme().equals("")) {
                    String tempEMV_Scheme = line.substring(line.indexOf("[")+1, line.indexOf("]"));
                    transactions.get(currentTransaction - 1).setEMV_Scheme(tempEMV_Scheme);
                }
            }
        }
    }

    void checkTypeOfApp(String date,String EMV_Scheme){
        addInformationOfTransaction();
        for (Transaction transaction: transactions) {
            if(transaction.getDate().equals(date)){
                if(transaction.getEMV_Scheme().equals(EMV_Scheme)) {
                    System.out.println("Тип приложения соответствует ожидаемому");
                } else System.out.println("Тип приложения не соответствует ожидаемому");
            }
        }
    }
}

class Transaction{
    private String date="";
    private String EMV_Scheme="";

    public Transaction() {

    }

    public String getDate() {
        return date;
    }

    public String getEMV_Scheme() {
        return EMV_Scheme;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEMV_Scheme(String EMV_Scheme) {
        this.EMV_Scheme = EMV_Scheme;
    }
}

class MainTwo {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\Андрей\\OneDrive\\Рабочий стол\\untitled\\A.log"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExerciseTwo logA = new ExerciseTwo(lines);
        logA.checkTypeOfApp("2018-02-19 16:35:59.574","M/Chip Advance");

        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\Андрей\\OneDrive\\Рабочий стол\\untitled\\B.log"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExerciseTwo logB = new ExerciseTwo(lines);
        logB.checkTypeOfApp("2018-02-19 16:36:42.174", "Chip Test");
    }
}
