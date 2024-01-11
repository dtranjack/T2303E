using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using CustomException;

/*
Tạo ứng dụng cho phép người dùng đăng nhập , thực hiện rút tiền , kiểm tra tài khoản , gửi tiền vào tài khoản .
- Khi rút tiền hoặc nạp tiền có các lựa chọn sẵn như 500k, 1000k , 1500k . Chỉ được thực hiện rút , nạp hay kiểm tra khi bạn đã 
đăng nhập vào tài khoản ngân hàng.
- Số tiền rút được là bội số của 50k và nhỏ hơn số tiền trong tài khoản - số dư tối thiểu.
- Khi khách hàng rút/nạp tiền  , thực hiện chiết khấu theo số tiền khách hàng thực hiện giao dịch như sau : rút tiền  (0.067%) , nạp tiền 
(0% ) - các tham số quy định dưới dạng hằng số  . Số tiền chiết khấu sẽ trừ vào tài khoản gốc của khách hàng
*/

namespace Bank
{
    class User
    {
        private string? userName { get; set; }
        private string? password;
        private string? name { get; set; }
        private decimal balance;
        private const decimal MIN_BALANCE = 50000;
        private const decimal interest = 0.067m;


        public bool VerifyCredentials(string inputUserName, string inputPassword)
        {
            while (string.IsNullOrWhiteSpace(inputUserName) || string.IsNullOrWhiteSpace(inputPassword))
            {
                Console.WriteLine("Username or password can't be empty. Please enter both username and password.");
                Console.Write("Enter username: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
                inputUserName = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
                Console.Write("Enter password: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
                inputPassword = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
            }

            return userName == inputUserName && password == inputPassword;
        }

        public User(string userName, string password, string name)
        {
            this.userName = userName;
            this.password = password;
            this.name = name;
            balance = MIN_BALANCE;
        }


        public User()
        {
        }

        public void withdrawMoney(decimal money)
        {
            decimal fee = money * interest;
            if (balance > MIN_BALANCE + money + fee)
            {
                Console.WriteLine("You have withdrawn: " + money);
                balance -= money + fee;
                Console.WriteLine("Your current balance: " + balance);
            }
            else
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.Error.WriteLine("Your balance is insufficient for the withdrawal");
                Console.ResetColor();
            }
        }

        private bool IsMultipleOf50k(decimal amount)
        {
            return amount % 50000 == 0;
        }

        public decimal getBalance()
        {
            return balance;
        }

        public string getName()
        {
#pragma warning disable CS8603 // Possible null reference return.
            return name;
#pragma warning restore CS8603 // Possible null reference return.
        }

        public void depositMoney(decimal amount)
        {
            Console.WriteLine("You have deposit: " + amount);
            balance += amount;
            Console.WriteLine("Your current balance: " + balance);
        }

        public decimal showBalance()
        {
            decimal currentBalane = getBalance();
            return currentBalane;
        }

    }


    // BankApp
    public class BankApp
    {
        static List<User> usersList = new List<User>();
        private void HandleLoggedInMenu(User currentUser)
        {
            bool logout = false;

            while (!logout)
            {
                Console.WriteLine("1. Deposit");
                Console.WriteLine("2. Withdraw Money");
                Console.WriteLine("3. Check Balance");
                Console.WriteLine("4. Logout");
                Console.Write("How can we help you today?");
                Console.WriteLine("Please select an action:");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
                string choice = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.

                switch (choice)
                {
                    case "1":
                        DepositMenu(currentUser);
                        break;
                    case "2":
                        WithdrawMenu(currentUser);
                        break;
                    case "3":
                        decimal currentBalane = currentUser.showBalance();
                        Console.WriteLine("Your current balance is: " + currentBalane + " VND");
                        break;
                    case "4":
                        logout = true;
                        break;
                    default:
                        Console.ForegroundColor = ConsoleColor.Red;
                        Console.Error.WriteLine("Invalid choice, please select a valid option");
                        Console.ResetColor();
                        break;
                }
            }
        }


        private User Login(List<User> users)
        {
            Console.Write("Enter username: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
            string username = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
            Console.Write("Enter password: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
            string password = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
#pragma warning disable CS8604 // Possible null reference argument.
#pragma warning disable CS8604 // Possible null reference argument.
            User currentUser = users.Find(u => u.VerifyCredentials(username, password));
#pragma warning restore CS8604 // Possible null reference argument.
#pragma warning restore CS8604 // Possible null reference argument.
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
#pragma warning disable CS8603 // Possible null reference return.
            return currentUser;
#pragma warning restore CS8603 // Possible null reference return.
        }

        private void DepositMenu(User currentUser)
        {

            Console.WriteLine("Please select the amount you want to deposit:");
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
            Console.WriteLine("1. 500k VND");
            Console.WriteLine("2. 1m VND");
            Console.WriteLine("3. 1.5m VND");
            Console.WriteLine("4. Other Amount");
            Console.Write("Please select an option: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
            string choice3 = Console.ReadLine();
            switch (choice3)
            {
                case "1":
                    Console.WriteLine("500000");
                    currentUser.depositMoney(500000);
                    break;
                case "2":
                    Console.WriteLine("1000000");
                    currentUser.depositMoney(1000000);
                    break;
                case "3":
                    Console.WriteLine("1500000");
                    currentUser.depositMoney(1500000);
                    break;
                case "4":
                    Console.WriteLine("Other amount");
                    decimal DPmoney = 0;
                    decimal validAmount = 0;
                    bool isValidAmount = false;

                    while (!isValidAmount)
                    {
                        string userInput = Console.ReadLine();

                        if (decimal.TryParse(userInput, out DPmoney) && DPmoney > 0)
                        {
                            validAmount = DPmoney;
                            isValidAmount = true;
                        }
                        else
                        {
                            Console.ForegroundColor = ConsoleColor.Red;
                            Console.Error.WriteLine("Invalid input. Please enter a valid amount of money to be deposited: ");
                            Console.ResetColor();
                        }
                    }

                    currentUser.depositMoney(validAmount);
                    break;

                default:
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.Error.WriteLine("Invalid choice, please select a valid option");
                    Console.ResetColor();
                    break;
            }
        }
        private void WithdrawMenu(User currentUser)
        {
            Console.WriteLine("Please select the amount you want to withdraw:");
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
            Console.WriteLine("1. 500k VND");
            Console.WriteLine("2. 1m VND");
            Console.WriteLine("3. 1.5m VND");
            Console.WriteLine("4. Other Amount");
            Console.Write("Please select an option: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
            string choice2 = Console.ReadLine();
            switch (choice2)
            {
                case "1":
                    Console.WriteLine("500000");
                    currentUser.withdrawMoney(500000);
                    break;
                case "2":
                    Console.WriteLine("1000000");
                    currentUser.withdrawMoney(1000000);
                    break;
                case "3":
                    Console.WriteLine("1500000");
                    currentUser.withdrawMoney(1500000);
                    break;
                case "4":
                    Console.WriteLine("Please input the amount: \nImportant: Please enter the amount which is a multiple of 50000 VND");
                    decimal WDmoney = 0;
                    decimal validAmount = 0;
                    bool isValidAmount = false;

                    while (!isValidAmount)
                    {
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
                        string userInput = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.
                        if (decimal.TryParse(userInput, out WDmoney))
                        {
                            if (WDmoney % 50000 == 0)
                            {
                                isValidAmount = true;
                                validAmount = WDmoney;
                            }
                            else
                            {
                                Console.ForegroundColor = ConsoleColor.Red;
                                Console.Error.WriteLine("Amount must be a multiple of 50000. Please enter a valid amount:");
                                Console.ResetColor();
                            }
                        }
                        else
                        {
                            Console.ForegroundColor = ConsoleColor.Red;
                            Console.Error.WriteLine("Invalid input. Please enter an amount of money to withdraw in numbers");
                            Console.ResetColor();
                        }
                    }

                    currentUser.withdrawMoney(validAmount);
                    break;
                default:
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.Error.WriteLine("Invalid choice, please select a valid option");
                    Console.ResetColor();
                    break;
            }
        }




        public void RunBankApp()
        {

            bool exit = false;
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
            User currentUser = null;
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.

            usersList.Add(new User("dTran", "admin123@", "Duong"));
            usersList.Add(new User("luuNg", "admin234@", "Luu"));
            usersList.Add(new User("nghiaNg", "admin456@", "Nghia"));
            usersList.Add(new User("jack", "1234", "Jack"));
            while (!exit)
            {
                Console.WriteLine("1. Login");
                Console.WriteLine("2. Exit");
                Console.Write("Please select an option: ");
#pragma warning disable CS8600 // Converting null literal or possible null value to non-nullable type.
                string choice = Console.ReadLine();
#pragma warning restore CS8600 // Converting null literal or possible null value to non-nullable type.

                switch (choice)
                {
                    case "1":
                        currentUser = Login(usersList);
                        if (currentUser != null)
                        {
                            Console.WriteLine("Welcome back: " + currentUser.getName());
                            HandleLoggedInMenu(currentUser);
                        }
                        else
                        {
                            Console.ForegroundColor = ConsoleColor.Red;
                            Console.Error.WriteLine("Wrong username or password, please try again");
                            Console.ResetColor();
                        }
                        break;
                    case "2":
                        exit = true;
                        Console.WriteLine("Thank you for using our bank service, please come again.");
                        Console.WriteLine("Application exited successfully.");
                        break;
                    default:
                        Console.ForegroundColor = ConsoleColor.Red;
                        Console.Error.WriteLine("Invalid choice, please select a valid option");
                        Console.ResetColor();
                        break;
                }
            }
            Environment.Exit(0);
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            BankApp bankApp = new BankApp();
            bankApp.RunBankApp();
        }
    }
}