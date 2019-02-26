package sharing.ride.rideexchange;

public class Ride
{
    public String dep;
    public String des;
    public String dayy;
    public String monthh;
    public String yearr;
    public String hourr;
    public String minss;
    public String name;
    public int idDriver;
    public int nbPass;
    public int realNbPass;
    public int idList;
    public int idTravel;

    public Ride(String dep, String des, String dayy, String monthh, String yearr,
                String hourr, String minss, String name, int idDriver, int nbPass, int realNbPass,
                int idList, int idTravel)
    {
        this.dep = dep;
        this.des = des;
        this.dayy = dayy;
        this.monthh = monthh;
        this.yearr = yearr;
        this.hourr = hourr;
        this.minss = minss;
        this.name = name;
        this.idDriver = idDriver;
        this. nbPass = nbPass;
        this.realNbPass = realNbPass;
        this.idList = idList;
        this.idTravel = idTravel;
    }
}
