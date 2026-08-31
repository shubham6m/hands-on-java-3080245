package bank;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException{
    if(amount <= 0){
      throw new AmountException("The minimum is 1.00");
    }else{
      double newBalance = balance + amount;
      setBalance(newBalance);
      System.out.println("Updated Account Balance is : " + newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }
  public void withdraw(double amount) throws AmountException{
    if(amount <= 0){
      throw new AmountException("The minimum withdrawable account is 1.00");
    }else if(amount > getBalance()){
      throw new AmountException("You don't have sufficient balance to withdrawal.");
    }
    else{
      double newBalance = balance - amount;
      setBalance(newBalance);
      System.out.println("Remaining Account Balance is : " + newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }
}
