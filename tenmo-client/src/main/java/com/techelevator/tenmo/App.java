package com.techelevator.tenmo;


import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AccountServiceException;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.TransferService;
import com.techelevator.tenmo.services.TransferServiceException;
import com.techelevator.tenmo.services.UserService;
import com.techelevator.tenmo.services.UserServiceException;
import com.techelevator.view.ConsoleService;

public class App {

private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	
    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu(){
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				try {
					viewCurrentBalance();
				} catch (AccountServiceException e) {
					e.printStackTrace();
				}
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				try {
					viewTransferHistory();
				} catch (TransferServiceException e) {
					e.printStackTrace();
				}
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				try {
					try {
						sendBucks();
					} catch (UserServiceException e) {
	
						e.printStackTrace();
					}
				} catch (TransferServiceException e) {
					e.printStackTrace();
				}
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				try {
					try {
						requestBucks();
					} catch (UserServiceException e) {
	
						e.printStackTrace();
					}
				} catch (TransferServiceException e) {
					e.printStackTrace();
				}
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() throws AccountServiceException {
		AccountService as = new AccountService();
		
		System.out.println("--------------------------------------");
		System.out.println("Your current balance is " + as.viewCurrentBalance());
		System.out.println("--------------------------------------");
		System.out.println("Please visit http://localhost/8080/catcards/random");
		System.out.println("--------------------------------------");
		
	}
	
	private void viewTransferHistory() throws TransferServiceException {
		TransferService ts = new TransferService();
		
		System.out.println("--------------------------------------");
		System.out.println("Your Transfer History:");
		System.out.println("--------------------------------------");
		
		for (Transfer transfer : ts.viewTransferHistory()) {
			System.out.println (transfer);
		}
		
		System.out.println("--------------------------------------");
		System.out.println("--------------------------------------");
		
		String prompt = "Please enter transfer ID to view details (0 to cancel)";
		int transferDetailId = console.getUserInputInteger(prompt);
		System.out.println("---------------------------------");
		System.out.println("Transfer Details");
		System.out.println("---------------------------------");
		
		Transfer transferDetails = new Transfer();
		transferDetails.setTransferId(transferDetailId);
		
		System.out.println(ts.viewTransferDetailsByTransferId(transferDetails));
		
		
		System.out.println("--------------------------------------");
		System.out.println("Please visit http://localhost/8080/catcards/random");
		System.out.println("--------------------------------------");		
		
		
	}
	
	

	
	private void viewPendingRequests() {

		System.out.println("--------------------------------------");
		System.out.println("Give Us A Break!");
		System.out.println("--------------------------------------");	
		System.out.println("--------------------------------------");
		System.out.println("Please visit http://localhost/8080/catcards/random");
		System.out.println("--------------------------------------");	
		
		
	}

	private void sendBucks() throws TransferServiceException, UserServiceException {
		TransferService ts = new TransferService();
		UserService us = new UserService();
		
		System.out.println("--------------------------------------");
		System.out.println("Who Deserves Your Money??? ");
		System.out.println("--------------------------------------");
		
			
		for (User user : us.listAllUsers()) {
			System.out.print ("Username: " + user.getUsername() + " ID: ");
			System.out.print (user.getId());
			System.out.println("");
		}
		
		System.out.println("--------------------------------------");
		String promptId = "Type The ID# Of The Person You're Sending Money To, or Press 0 To Cancel: ";
		int account = console.getUserInputInteger(promptId);
		System.out.println("--------------------------------------");
		
		
		
		System.out.println("--------------------------------------");
		String promptAmount = "How much money do you want to send? Enter in the following format: 10.00";
		double formattedAmount = Double.parseDouble(console.getUserInput(promptAmount));
		System.out.println("--------------------------------------");
		
		Transfer sendBucks = new Transfer();
		sendBucks.setTransferTypeId(2);
		sendBucks.setTransferStatus(2);
		sendBucks.setAmount(formattedAmount);
		sendBucks.setAccountTo(account);
		sendBucks.setAccountFrom(currentUser.getUser().getId());
		
		ts.sendBucksCreatesNewTransfer(sendBucks, currentUser);
		
		ts.sendUpdatesUserBalance(sendBucks, currentUser);
		
		System.out.println("--------------------------------------");
		System.out.println("Thanks Have A Great Day!");	
		System.out.println("--------------------------------------");
		System.out.println("Please visit http://localhost/8080/catcards/random");
		System.out.println("--------------------------------------");
		
	}

	
	private void requestBucks() throws TransferServiceException, UserServiceException {
		TransferService ts = new TransferService();	
		UserService us = new UserService();
		
		System.out.println("--------------------------------------");
		System.out.println("Select The Person Who Owes You Money: ");
		System.out.println("--------------------------------------");
		
			
		for (User user : us.listAllUsers()) {
			System.out.print ("Username: " + user.getUsername() + " ID: ");
			System.out.print (user.getId());
			System.out.println("");
			
			}
			
		System.out.println("--------------------------------------");
		String promptId = "Type The ID# Of The Person Who Owes You Money, or Press 0 to cancel: ";
		int account = console.getUserInputInteger(promptId);
		System.out.println("--------------------------------------");
		
		
		
		System.out.println("--------------------------------------");
		String promptAmount = "How much money do they owe you? Enter in the following format: 10.00";
		double formattedAmount = Double.parseDouble(console.getUserInput(promptAmount));
		System.out.println("--------------------------------------");
		
		Transfer requestBucks = new Transfer();
		requestBucks.setTransferTypeId(1);
		requestBucks.setTransferStatus(1);
		requestBucks.setAmount(formattedAmount);
		requestBucks.setAccountTo(account);
		requestBucks.setAccountFrom(currentUser.getUser().getId());
		
		ts.requestBucks(requestBucks, currentUser);
		
		System.out.println("--------------------------------------");
		System.out.println("Your Request Is Pending Approval");	
		System.out.println("--------------------------------------");
		System.out.println("Thanks Have A Great Day!");	
		System.out.println("--------------------------------------");
		System.out.println("Please visit http://localhost/8080/catcards/random");
		System.out.println("--------------------------------------");
	}
	
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
				TransferService.AUTH_TOKEN = currentUser.getToken();
				AccountService.AUTH_TOKEN = currentUser.getToken();
				UserService.AUTH_TOKEN = currentUser.getToken();
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
	
	
	
}
