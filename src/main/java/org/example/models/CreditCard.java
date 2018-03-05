package org.example.models;

import simudyne.core.Model;
import simudyne.core.annotations.Constant;
import simudyne.core.annotations.Input;
import simudyne.core.annotations.Variable;

public class CreditCard implements Model {
  @Constant public long credit_limit = 0;

  @Input public boolean isSpending = true;

  @Input(name = "Spending")
  public float spending = 250f;

  @Input(name = "Repayment amount")
  public float repayment = 200f;

  @Input(name = "Interest Rate")
  public float interest = 0.03f;

  @Variable(name = "Balance", initializable = true)
  public float balance = 400;

  @Variable(name = "Interest Charge")
  public float interest_charge() {
    return interest * balance;
  }

  @Variable(name = "Balance Additions")
  public float balance_additions() {
    return interest_charge() + (isSpending ? spending : 0);
  }

  // SDModel Interface Methods

  @Override
  public void step() {
    balance += balance_additions() - repayment;

    if (balance < 0) {
      balance = 0;
    }
  }
}
