package Revati;

import java.util.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
     class Bankaccount {
	private double balance;
	public Bankaccount(double initialBalance) {
		balance=initialBalance;
	}
	public double getBalance() {
		return balance;
	}
	public void deposit(double amount) {
		balance += amount;
	}
	public boolean withdraw(double amount) {
		if(amount <= balance)
		{
			balance -= amount;
			return true;
		}else {
			return false;
		}
	}
}
	class ATM extends JFrame implements ActionListener{
		private Bankaccount account;
		private JTextField balanceField;
		private JTextField amountField;
		private JTextArea outputArea;
		public ATM(Bankaccount account){
			this.account = account;
			setTitle("ATM Machine");
			setSize(400,300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4,2));
			
			JLabel balanceLabel = new JLabel("Balance");
			balanceField = new JTextField(Double.toString(account.getBalance()));
			balanceField.setEditable(false);
			
			JLabel amountLabel = new JLabel("Amount");
			amountField = new JTextField();
			
			JButton checkBalanceButton =new JButton("Check Balance");
			checkBalanceButton.addActionListener(this);
			
			JButton depositButton = new JButton("Deposit");
			depositButton.addActionListener(this);
			
			JButton withdrawButton = new JButton("Withdraw");
			withdrawButton.addActionListener(this);
			
			outputArea =new JTextArea();
			outputArea.setEditable(false);
			
			panel.add(balanceLabel);
			panel.add(balanceField);
			panel.add(amountLabel);
			panel.add(amountField);
			panel.add(checkBalanceButton);
			panel.add(depositButton);
			panel.add(withdrawButton);
			panel.add(outputArea);
			
			add(panel);
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Check Balance")) {
				outputArea.setText("Your balance is:Rs."+account.getBalance());
			}else if(e.getActionCommand().equals("Deposite")){ 
				try {
				double amount =Double.parseDouble(amountField.getText());
				if(amount>0) 
				{
					account.deposit(amount);
					balanceField.setText(Double.toString(account.getBalance()));
					outputArea.setText("Deposit Successful.");				
				}else {
					outputArea.setText("Invalid deposite amount.");
				}
			}catch (NumberFormatException ex) {
				outputArea.setText("Invalid iput.Please enter a valid amount.");
			}	
			}else if(e.getActionCommand().equals("Withdraw")) {
				try {
					double amount =Double.parseDouble(amountField.getText());
					if(amount> 0 && account.withdraw(amount)) {
						balanceField.setText(Double.toString(account.getBalance()));
						outputArea.setText("Withdrawal successful.");
					}else  {
						outputArea.setText("Invalid Withdrawal amount or insufficient balance.");
					}
				}catch(NumberFormatException ex) {
					outputArea.setText("Invalid input.please enter a valid amount.");
				}
			}
			}
		}
	public class ATMmachinetasks{
    public static void main(String args[]) {
	Bankaccount userAccount = new Bankaccount(1000.0);
	ATM atmGUI = new ATM(userAccount);
	atmGUI.setVisible(true);
}
	}