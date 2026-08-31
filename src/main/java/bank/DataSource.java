package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class DataSource{
    // connect with DB
    public static Connection connect(){
      String db_file = "jdbc:sqlite:resources/bank.db";
      Connection connection = null;
      try{
        connection = DriverManager.getConnection(db_file);
        System.out.println("we're connected!!");
      }catch(SQLException e){
        e.printStackTrace();
      }
      return connection;
    }

    // customer object to fetch the account data
    public static Customer getCustomer(String username){
      // query to fetch data
      String query = "select * from customers where username = ?";
      Customer customer = null;
      // connect with db
      try(Connection connection = connect();
          PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, username);
            try(ResultSet rs = statement.executeQuery()){
              customer = new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("account_id")
              );
            }

      }catch(SQLException e){
        e.printStackTrace();
      }
      return customer;
    }

    //account object to fetch the account 
    public static Account getAccount(int accountId){
      // query to fetch details
      String query = "Select * from account where id = ?";
      Account account = null;
      //connect with db
      try(Connection connection = connect();
      // prepared statement to pass the exact query
      PreparedStatement statement = connection.prepareStatement(query)){
        statement.setInt(1, accountId);
        try (ResultSet rs = statement.executeQuery()) {
          account = new Account(
              rs.getInt("id"),
              rs.getString("type"),
              rs.getDouble("balance"));
        }
      }catch(SQLException e){
        e.printStackTrace();
      }
      return account;
    }
    public static void main(String[] args) {
      Customer customer = getCustomer("telloy3x@bigcartel.com");
      Account account = getAccount(customer.getAccountId());
      System.out.println(account.getBalance());
    }
}
