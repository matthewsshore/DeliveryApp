/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.skantoro.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.utils.SystemProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.skantoro.example.com",
                ownerName = "backend.myapplication.skantoro.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    public String database_url = "jdbc:google:mysql://deliveryapp-testing:testing/DeliveryDatabase?user=root";

    @ApiMethod(name = "getUser")
    public final User getUser(@Named("email") final String email) throws ClassNotFoundException, SQLException{
        User signedInUser = retrieveUser(email);

        return signedInUser;
    }

    private User retrieveUser(String email) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("SELECT * FROM Users where Email = ?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        User signedInUser = new User();
        if (rs.next()) {
            signedInUser.setUserID(rs.getInt(1));
            signedInUser.setFirstName(rs.getString(2));
            signedInUser.setLastName(rs.getString(3));
            signedInUser.setMobileNumber(rs.getString(4));
            signedInUser.setEmail(rs.getString(5));
        }


        /*while (rs.next()) {
            User value = new User();
            value.setUserID(rs.getInt(1));
            value.setFirstName(rs.getString(2));
            value.setLastName(rs.getString(3));
            value.setMobileNumber(rs.getString(4));
            value.setRole(rs.getString(5));
            value.setEmail(rs.getString(6));
            signedInUser.add(value);
        }*/
        return signedInUser;
    }

    @ApiMethod(name = "getAllOrdersByUser")
    public final List<Order> getAllOrdersByUser(@Named("id") final Integer user) throws SQLException, ClassNotFoundException {
        List<Order> listOfOrders = retrieveUsersOrders(user);

        return listOfOrders;
    }

    private List<Order> retrieveUsersOrders(Integer user) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("SELECT * FROM Orders where IdUser = ?");
        stmt.setInt(1, user);
        ResultSet rs =  stmt.executeQuery();

        List<Order> listOrders = new ArrayList<Order>();


        while (rs.next()) {
            Order value = new Order();
            value.setOrderID(rs.getInt(1));
            value.setAddressID(rs.getInt(2));
            value.setOrderName(rs.getString(3));
            value.setStatus(rs.getInt(4));
            value.setEstPrice(rs.getDouble(5));
            value.setBid(rs.getDouble(6));
            value.setUserID(rs.getInt(7));
            listOrders.add(value);
        }
        return listOrders;
    }

    @ApiMethod(name = "getAllItemsByOrder")
    public final List<Item> getAllItemsByOrder(@Named("orderID") final Integer orderID) throws SQLException, ClassNotFoundException {
        List<Item> listOfItems = retrieveItems(orderID);

        return listOfItems;
    }

    private List<Item> retrieveItems(Integer orderID) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("SELECT * FROM Items where OrderID = ?");
        stmt.setInt(1, orderID);
        ResultSet rs =  stmt.executeQuery();

        List<Item> listItems = new ArrayList<Item>();


        while (rs.next()) {
            Item value = new Item();
            value.setItemID(rs.getInt(1));
            value.setOrderID(rs.getInt(2));
            value.setItemName(rs.getString(3));
            value.setPictureLocation(rs.getString(4));
            listItems.add(value);
        }
        return listItems;
    }

    @ApiMethod(name="setNewBidByOrderID")
    public Bid setNewBidByOrderID(@Named("orderID") final Integer orderID, @Named("bid") Double bid) throws SQLException, ClassNotFoundException {
        //setNewBid(orderID,bid);
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("Update Orders SET Bid = ? where ID = ?");
        stmt.setDouble(1, bid);
        stmt.setInt(2, orderID);
        stmt.executeUpdate();

        Bid newBid = new Bid();
        newBid.setBid(bid);

        return newBid;
    }

    @ApiMethod(name="getAddressesByUser")
    public final List<Address> getAddressesByUser(@Named("userID") final Integer userID) throws SQLException, ClassNotFoundException {
        List<Address> addresses = retrieveAddresses(userID);

        return addresses;
    }

    private List<Address> retrieveAddresses(Integer userID) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("SELECT * FROM Addresses where UserID = ?");
        stmt.setInt(1, userID);
        ResultSet rs =  stmt.executeQuery();

        List<Address> listAddresses = new ArrayList<Address>();


        while (rs.next()) {
            Address value = new Address();
            value.setAddressID(rs.getInt(1));
            value.setStreet(rs.getString(2));
            value.setCity(rs.getString(3));
            value.setState(rs.getString(4));
            value.setZip(rs.getString(5));
            value.setLat(rs.getDouble(6));
            value.setLong(rs.getDouble(7));
            value.setUserID(rs.getInt(8));
            listAddresses.add(value);
        }
        return listAddresses;
    }

    @ApiMethod(name="createNewOrder")
    public final List<Order> createNewOrder(@Named("addressID") final Integer addressID,  @Named("orderName") final String orderName,
                                            @Named("status") final Integer status, @Named("estPrice") final Double estPrice,
                                            @Named("bid") final Double bid, @Named("userID") final Integer userID) throws SQLException, ClassNotFoundException {

        createOrder(addressID, orderName, status, estPrice, bid, userID);

        List<Order> listOrders = retrieveUsersOrders(userID);

        return listOrders;
    }

    private void createOrder(Integer addressID, String orderName, Integer status, Double estPrice, Double bid, Integer userID) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("Insert Into Orders (AddressID, OrderName, Status, EstPrice, Bid, IdUser) VALUES (?, ?, ?, ?, ?, ?);");
        stmt.setInt(1, addressID);
        stmt.setString(2, orderName);
        stmt.setInt(3, status);
        stmt.setDouble(4, estPrice);
        stmt.setDouble(5, bid);
        stmt.setInt(6, userID);
        stmt.executeUpdate();
    }

    @ApiMethod(name="createNewUser")
    public final User createNewUser(@Named("firstName") final String firstName, @Named("lastName") final String lastName,
                                    @Named("mobileNumber") final String mobileNumber, @Named("email") final String email)
            throws SQLException, ClassNotFoundException {
        createUserProfile(firstName, lastName, mobileNumber, email);

        User signedInUser = getUser(email);

        return signedInUser;
    }

    private void createUserProfile(String firstName, String lastName, String mobileNumber, String email) throws ClassNotFoundException, SQLException {
        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");

            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        PreparedStatement stmt = conn1.prepareStatement("Insert Into Users (FirstName, LastName, MobileNumber, Email) VALUES (?, ?, ?, ?);");
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, mobileNumber);
        stmt.setString(4, email);
        stmt.executeUpdate();

    }


}







    /*@ApiMethod(name = "initialTest")
    public final List<MyBean> userTest() throws ClassNotFoundException, SQLException {

       List<MyBean> listUsers = retrieveTest();

        return listUsers;
    }

    private List<MyBean> retrieveTest() throws ClassNotFoundException, SQLException {

        String url = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");
            url = database_url;
        } else {
            // You may also assign an IP Address from the access control
            // page and use it to connect from an external network.
        }


        Connection conn1 = DriverManager.getConnection(url);
        ResultSet rs = conn1.createStatement().executeQuery("SELECT * FROM Test");

        List<MyBean> listUsers = new ArrayList<MyBean>();


        while (rs.next()) {
            MyBean value = new MyBean();
            value.setData(rs.getString(2));
            listUsers.add(value);
        }
        return listUsers;
    }*/