public class Ticket
{
    //private string _personName; // Имя человека (default - null)
    private string _eventName; // Название мероприятия
    private string _data; // День и время мероприятия
    private string _seats; // Место в зале
    private string _price; // Стоимость

    //////////////////////
    
    public string EventName
    {
        get { return _eventName;}
        set { _eventName = value;} // Можно добавить проверку ? или отдельным методом
    }

    public string Data
    {
        get { return _data;}
        set { _data = value;}
    }
    
    public string Seats
    {
        get { return _seats;}
        set { _seats = value;}
    }

    public string Price
    {
        get { return _price;}
        set { _price = value;}
    }

    public Ticket(string eventName, string data, string seats, string price)
    {
        _eventName = eventName;
        _data = data;
        _seats = seats;
        _price = price;
    }

    public bool Checker()
    {
    
    }
    // У человека можно переопределить вывод информации в Label
}