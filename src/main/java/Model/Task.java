package Model;

public class Task {
    int id;
    private int arrivalTime;
    private int serviceTime;
    private int currentTaskProcessingTime;
    private static int order = 0;

    public Task(){
        this.id = order+1;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        order++;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

//    public void setCurrentTaskProcessingTime(int currentTaskProcessingTime){
//        this.currentTaskProcessingTime = currentTaskProcessingTime;
//    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCurrentTaskProcessingTime() {
        return currentTaskProcessingTime;
    }

    public String toString(){
        return "Client with id " + id + " has timeArrival = " + arrivalTime + " and timeService = " + serviceTime + "\n";
    }
}
