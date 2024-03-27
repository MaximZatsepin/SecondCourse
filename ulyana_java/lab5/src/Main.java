import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CallManager callManager = new CallManager();
        callManager.loadFromFile("calls.bin");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Add Call");
            System.out.println("2. View Calls");
            System.out.println("3. Print Calls by Subscriber and Time Range");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter caller number:");
                    String callerNumber = scanner.next();
                    System.out.println("Enter receiver number:");
                    String receiverNumber = scanner.next();
                    System.out.println("Enter call date and time (yyyy-MM-dd HH:mm):");
                    String dateTimeString = scanner.next() + " " + scanner.next();
                    LocalDateTime callDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    System.out.println("Enter call duration (in minutes):");
                    int duration = scanner.nextInt();
                    callManager.addCall(callerNumber, receiverNumber, callDateTime, duration);
                    System.out.println("Call added successfully!");
                    break;
                case 2:
                    System.out.println("All calls:");
                    for (Call call : callManager.getCallList()) {
                        System.out.println(call);
                    }
                    break;
                case 3:
                    System.out.println("Enter subscriber number:");
                    String subscriberNumber = scanner.next();
                    System.out.println("Enter start date and time (yyyy-MM-dd HH:mm):");
                    String startDateTimeString = scanner.next() + " " + scanner.next();
                    LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    System.out.println("Enter end date and time (yyyy-MM-dd HH:mm):");
                    String endDateTimeString = scanner.next() + " " + scanner.next();
                    LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    callManager.printCallsBySubscriberAndTimeRange(subscriberNumber, startDateTime, endDateTime);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
                    break;
            }
        } while (choice != 4);
        callManager.saveToFile("calls.bin");
        scanner.close();
    }
}

class Call implements Serializable {
    private static final long serialVersionUID = 1L;

    private String callerNumber;
    private String receiverNumber;
    private LocalDateTime callDateTime;
    private int duration; // продолжительность звонка в минутах

    public Call(String callerNumber, String receiverNumber, LocalDateTime callDateTime, int duration) {
        this.callerNumber = callerNumber;
        this.receiverNumber = receiverNumber;
        this.callDateTime = callDateTime;
        this.duration = duration;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public LocalDateTime getCallDateTime() {
        return callDateTime;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Call{" +
                "callerNumber='" + callerNumber + '\'' +
                ", receiverNumber='" + receiverNumber + '\'' +
                ", callDateTime=" + callDateTime +
                ", duration=" + duration +
                '}';
    }
}

class CallManager {
    private List<Call> callList;

    public CallManager() {
        callList = new ArrayList<>();
    }

    public void addCall(String callerNumber, String receiverNumber, LocalDateTime callDateTime, int duration) {
        Call call = new Call(callerNumber, receiverNumber, callDateTime, duration);
        callList.add(call);
    }

    public List<Call> getCallList() {
        return callList;
    }

    public void printCallsBySubscriberAndTimeRange(String subscriberNumber, LocalDateTime startTime, LocalDateTime endTime) {
        int incomingTotalDuration = 0;
        int outgoingTotalDuration = 0;
        System.out.println("Calls for subscriber " + subscriberNumber + " between " + startTime + " and " + endTime + ":");
        for (Call call : callList) {
            if ((call.getCallerNumber().equals(subscriberNumber) || call.getReceiverNumber().equals(subscriberNumber)) &&
                    call.getCallDateTime().isAfter(startTime) && call.getCallDateTime().isBefore(endTime)) {
                System.out.println(call);
                if (call.getCallerNumber().equals(subscriberNumber)) {
                    outgoingTotalDuration += call.getDuration();
                } else if (call.getReceiverNumber().equals(subscriberNumber)) {
                    incomingTotalDuration += call.getDuration();
                }
            }
        }
        System.out.println("Total incoming call duration: " + incomingTotalDuration + " minutes");
        System.out.println("Total outgoing call duration: " + outgoingTotalDuration + " minutes");
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(callList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            callList = (List<Call>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
