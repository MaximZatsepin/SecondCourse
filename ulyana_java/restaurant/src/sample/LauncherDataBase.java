package sample;

import java.sql.SQLException;

public class LauncherDataBase {

    public static void main(String[] args) throws SQLException {

        HandlerDataBase base = new HandlerDataBase();

        base.createUsersTable();
        base.createDishTable();
        base.createOrderTable();
    }

}
