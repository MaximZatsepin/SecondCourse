public class People {

    private String _surname;
    private String _name;
    private int _month;
    private int _year;

    public People(String surname, String name, int month, int year){
        setSurname(surname);
        setName(name);
        setMonth(month);
        setYear(year);
    }

    public String getSurname(){return _surname;}
    public void setSurname(String surname){_surname = surname;}

    public String getName(){return _name;}
    public void setName(String name){_name = name;}

    public int getMonth(){return _month;}
    public void setMonth(int month){_month = month;}

    public int getYear(){return _year;}
    public void setYear(int year){_year = year;}

    public String getData(){
        return getSurname() + " " + getName() + ", " + Integer.toString(getMonth()) + "." + Integer.toString(getYear());
    }
}