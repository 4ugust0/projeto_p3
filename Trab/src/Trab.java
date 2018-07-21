import java.util.Scanner;

public class Trab {
	public static void printHeader() {
		for(int i = 0; i < 20; ++i) {
			System.out.println();
		}
			
		System.out.println("\t IFace \t");
		System.out.println();
	}
	
	/* organizing numbers; */
	
	public static int maxCommunitiesCanCreate = 3;
	public static int maxCommunitiesCanMember = 20;
	public static int maxAccounts = 100;
	public static int maxMessages = 20;
	public static int maxAttributes = 5;
	public static int accountsSize = 0;
	
	public static int loginDef = 0;
	public static int passwordDef = 1;
	public static int profileNameDef = 2;
	public static int emailDef = 3;
	/**/
	
	public static Scanner inputTerminal = new Scanner(System.in);
	
	public static String allAccounts[][] = new String[4][maxAccounts];
	public static String allMessages[][] = new String[maxAccounts][maxMessages];
	public static int allLastMessage[] = new int[maxAccounts];
	public static String allAttributes[][] = new String[maxAccounts][maxAttributes];
	public static boolean allFriends[][] = new boolean[maxAccounts][maxAccounts];
	public static String allCommunities[][] = new String[maxAccounts][maxCommunitiesCanCreate];
	public static int allNumberOfCommunities[][] = new int[maxAccounts][1];
	public static String allCommunitiesMembers[][] = new String[maxCommunitiesCanCreate * maxAccounts][maxCommunitiesCanMember];
	
	public static void listMembers(int accountID) {
		System.out.println();
		System.out.println("digite o número da comunidade");
		System.out.println();
		
		int choosedSlot = Integer.parseInt(0 + inputTerminal.nextLine());
		
		System.out.println();
		for(int i = 0; i < maxCommunitiesCanMember; ++i) {
			if(allCommunitiesMembers[accountID + choosedSlot][i] != null) {
				System.out.println(allCommunitiesMembers[accountID + choosedSlot][i]);
			}
		}
		
		System.out.println();
		inputTerminal.nextLine();
	}
	
	public static void addMember(int accountID) {
		System.out.println();
		System.out.println("digite o número da comunidade");
		System.out.println();
		
		int choosedSlot = Integer.parseInt(0 + inputTerminal.nextLine());
		
		System.out.println();
		System.out.println("digite o membro que queira adicionar");
		System.out.println();
		
		String loginToAdd = inputTerminal.nextLine();
		
		for(int i = 0; i < maxAccounts; ++i) {
			if(allAccounts[loginDef][i] != null && allAccounts[loginDef][i].equals(loginToAdd)) {
				for(int j = 0; j < maxCommunitiesCanMember; ++j) {
					if(allCommunitiesMembers[accountID + choosedSlot][j] == null) {
						allCommunitiesMembers[accountID + choosedSlot][j] = allAccounts[profileNameDef][i] + 
								"(" + allAccounts[loginDef][i] + ")";
						
						System.out.println();
						System.out.println("membro adicionado com sucesso");
						System.out.println();
						
						inputTerminal.nextLine();
						return;
					}
				}
				
				System.out.println();
				System.out.println("sem vaga para adicionar o novo membro");
				System.out.println();
				
				inputTerminal.nextLine();
				return;
			}
		}
		
		System.out.println();
		System.out.println("login nao encontrado");
		System.out.println();
		
		inputTerminal.nextLine();
	}
	
	public static void removeCommunity(int accountID) {
		System.out.println();
		System.out.println("digite o número da comunidade");
		System.out.println();
		
		int choosedSlot = Integer.parseInt(0 + inputTerminal.nextLine());
		
		allCommunities[accountID][choosedSlot] = null;
		allNumberOfCommunities[accountID][0]--;
		
		for(int i = 0; i < maxCommunitiesCanMember; ++i) {
			allCommunitiesMembers[accountID + choosedSlot][i] = null;
		}
	}
	
	public static void manageCommunity(int accountID) {
		while(true) {
			printHeader();
			
			System.out.println("suas comunidades:");
			System.out.println();
			
			for(int i = 0; i < maxCommunitiesCanCreate; ++i) {
				if(allCommunities[accountID][i] != null) {
					System.out.println(i + " - " + allCommunities[accountID][i]);
				}
			}
			
			System.out.println();
			System.out.println("1 - adicionar membro");
			System.out.println("2 - listar membros");
			System.out.println("3 - remover comunidade");
			System.out.println("4 - sair");
			int choosedNumber = Integer.parseInt(0 + inputTerminal.nextLine());
			
			switch(choosedNumber) {
				case 0:
					return;
				case 1:
					addMember(accountID);
					break;
				case 2:
					listMembers(accountID);
					break;
				case 3:
					removeCommunity(accountID);
					break;
				case 4:
					return;
				default:
					return;
			}
		}
	}
	
	public static void createCommunity(int accountID) {
		printHeader();
		
		if(allNumberOfCommunities[accountID][0] >= maxCommunitiesCanCreate) {
			System.out.println();
			System.out.println("você não pode ter mais que " + maxCommunitiesCanCreate + " comunidades");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		}
		
		System.out.print("nome da comunidade que deseja criar: ");
		String newCommunityName = inputTerminal.nextLine();
		
		allNumberOfCommunities[accountID][0]++;
		
		for(int i = 0; i < maxCommunitiesCanCreate; ++i) {
			if(allCommunities[accountID][i] == null) {
				allCommunities[accountID][i] = newCommunityName;
				break;
			}
		}
		
		System.out.println();
		System.out.println("comunidade criada com sucesso: ");
		System.out.println();
		
		inputTerminal.nextLine();
	}
	
	public static void removeAccount(int accountID) {
		for(int i = 0; i < maxAttributes; ++i) {
			allAttributes[accountID][i] = null;
		}
		
		for(int i = 0; i < maxAccounts; ++i) {
			allFriends[accountID][i] = false;
		}
		
		allAccounts[loginDef][accountID] = null;
		allAccounts[passwordDef][accountID] = null;
		allAccounts[profileNameDef][accountID] = null;
		allAccounts[emailDef][accountID] = null;
		
		allLastMessage[accountID] = 0;
		for(int i = 0; i < maxMessages; ++i) {
			allMessages[accountID][i] = null;
		}
		
		for(int i = accountID; i < maxAccounts - 1; ++i) {
			allAccounts[loginDef][i] = allAccounts[loginDef][i + 1];
			allAccounts[passwordDef][i] = allAccounts[passwordDef][i + 1];
			allAccounts[profileNameDef][i] = allAccounts[profileNameDef][i + 1];
			allAccounts[emailDef][i] = allAccounts[emailDef][i + 1];
		}
	}
	
	public static void recoveryInfo(int accountID) {
		printHeader();
		
		System.out.println("nome de perfil: " + allAccounts[profileNameDef][accountID]);
		System.out.println("login: " + allAccounts[loginDef][accountID]);
		System.out.println("email: " + allAccounts[emailDef][accountID]);
		
		System.out.println();
		System.out.println("Lista de atributos: ");
		
		for(int i = 0; i < maxAttributes; ++i) {
			System.out.println((i + 1) + " - " + allAttributes[accountID][i]);
		}
		
		System.out.println();
		System.out.println("Lista de amigos: ");
		
		for(int i = 0; i < maxAccounts; ++i) {
			if(allFriends[i][accountID] == true && allFriends[accountID][i] == true) {
				System.out.println(allAccounts[profileNameDef][i] + "(" + allAccounts[loginDef][i] + ")");
			}
		}
		
		System.out.println();
		System.out.println("Ultima mensagem: ");
		if(allLastMessage[accountID] == 0) {
			System.out.println("sem mensagems :(");
		} else {
			System.out.println(allMessages[accountID][--allLastMessage[accountID]]);
		}
		
		
		inputTerminal.nextLine();
	}
	
	public static void sendMessage(int accountID) {
		printHeader();
		
		System.out.print("digite o nome do usuário ou nome do perfil o qual queira enviar uma mensagem: ");
		String sendStr = inputTerminal.nextLine();
		
		System.out.println("Digite a mensagem a ser enviada: ");
		String sendMessage = inputTerminal.nextLine();
		
		for(int i = 0; i < maxAccounts; ++i) {
			if(allAccounts[loginDef][i] != null && (allAccounts[loginDef][i].equals(sendStr) || allAccounts[profileNameDef][i].equals(sendStr))) {
				if(allLastMessage[i] >= maxMessages) {
					System.out.println();
					System.out.println("caixa de mensagem do usuario cheia");
					System.out.println();
				} else {
					allMessages[i][allLastMessage[i]] = sendMessage + " --- enviado de: " + allAccounts[profileNameDef][accountID] + "(" + allAccounts[loginDef][accountID] + ")";
					allLastMessage[i]++;
				}
				
				System.out.println();
				System.out.println("mensagem enviada com sucesso");
				System.out.println();
				
				inputTerminal.nextLine();
				
				return;
			}
		}
		
		System.out.println();
		System.out.println("usuario nao encontrado tente novamente");
		System.out.println();
		
		inputTerminal.nextLine();
	}
	
	public static void addFriend(int accountID) {
		printHeader();
		
		System.out.print("Digite o login ou nome de perfil do individuo para enviar solicitação: ");
		String requestStr = inputTerminal.nextLine();
		
		for(int i = 0; i < maxAccounts; ++i) {
			if(allAccounts[loginDef][i] != null && (allAccounts[loginDef][i].equals(requestStr) || allAccounts[profileNameDef][i].equals(requestStr))) {
				allFriends[accountID][i] = true;
				
				System.out.println();
				System.out.println("solicitação enviada com sucesso");
				System.out.println();
				
				inputTerminal.nextLine();
				
				return;
			}
		}
		
		System.out.println();
		System.out.println("não foi encontrado o usuario, tente novamente");
		System.out.println();
		
		inputTerminal.nextLine();
	}
	
	public static void changeAttributes(int accountID) {
		printHeader();
		
		System.out.println();
		System.out.println("Lista de atributos: ");
		
		for(int i = 0; i < maxAttributes; ++i) {
			System.out.println((i+1) + " - " + allAttributes[accountID][i]);
		}
		
		System.out.println();
		System.out.println("Para editar digite o slot do atributo e, depois, o atributo");
		System.out.println("Para sair, digite um slot inválido");
		System.out.println();
		
		System.out.print("slot: ");
		int attributeSlot = Integer.parseInt(0 + inputTerminal.nextLine());
		
		if(attributeSlot > 5 || attributeSlot == 0) {
			return;
		}
		
		System.out.print("atributo: ");
		String newAttribute = inputTerminal.nextLine();
		
		allAttributes[accountID][attributeSlot - 1] = newAttribute;
	}
	
	public static void changeEmail(int accountID) {
		printHeader();
		
		System.out.print("digite seu novo email: ");
		String newEmail = inputTerminal.nextLine();
		
		System.out.print("digite sua senha atual para confirmar a modificação: ");
		String actualPassword = inputTerminal.nextLine();
		
		if(allAccounts[passwordDef][accountID].equals(actualPassword)) {
			allAccounts[emailDef][accountID] = newEmail;
			
			System.out.println();
			System.out.println("alterado com sucesso");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		} else {
			System.out.println();
			System.out.println("voce errou a senha tente novamente");
			System.out.println();
			
			inputTerminal.nextLine();
			return;
		}
	}
	
	public static void changePassword(int accountID) {
		printHeader();
		
		System.out.print("digite sua nova senha: ");
		String newPassword = inputTerminal.nextLine();
		
		System.out.print("digite sua senha atual para confirmar a modificação: ");
		String actualPassword = inputTerminal.nextLine();
		
		if(allAccounts[passwordDef][accountID].equals(actualPassword)) {
			allAccounts[passwordDef][accountID] = newPassword;
			
			System.out.println();
			System.out.println("alterado com sucesso");
			System.out.println();
			
			inputTerminal.nextLine();
			return;
		} else {
			System.out.println();
			System.out.println("voce errou a senha tente novamente");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		}
	}
	
	public static void changeLogin(int accountID) {
		printHeader();
		
		System.out.print("digite seu novo login: ");
		String newLogin = inputTerminal.nextLine();
		
		System.out.print("digite sua senha atual para confirmar a modificação: ");
		String actualPassword = inputTerminal.nextLine();
		
		if(allAccounts[passwordDef][accountID].equals(actualPassword)) {
			allAccounts[loginDef][accountID] = newLogin;

			System.out.println();
			System.out.println("alterado com sucesso");
			System.out.println();
			
			inputTerminal.nextLine();
			return;
		} else {
			
			System.out.println();
			System.out.println("voce errou a senha tente novamente");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		}
	}
	
	public static void changeProfileName(int accountID) {
		printHeader();
		
		System.out.print("digite seu novo nome de perfil: ");
		String newProfileName = inputTerminal.nextLine();
		
		System.out.print("digite sua senha para confirmar a modificação: ");
		String actualPassword = inputTerminal.nextLine();
		
		if(allAccounts[passwordDef][accountID].equals(actualPassword)) {
			allAccounts[profileNameDef][accountID] = newProfileName;
			

			System.out.println();
			System.out.println("alterado com sucesso");
			System.out.println();
			
			inputTerminal.nextLine();
			return;
		} else {
			
			System.out.println();
			System.out.println("voce errou a senha tente novamente");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		}
	}
	
	public static void editProfile(int accountID) {
		printHeader();
		
		System.out.println("nome de perfil: " + allAccounts[profileNameDef][accountID]);
		System.out.println("login: " + allAccounts[loginDef][accountID]);
		System.out.println("email: " + allAccounts[emailDef][accountID]);
		
		System.out.println();
		System.out.println("Lista de atributos: ");
		
		for(int i = 0; i < maxAttributes; ++i) {
			System.out.println((i + 1) + " - " + allAttributes[accountID][i]);
		}
		
		System.out.println();
		System.out.println("1 - mudar nome de perfil");
		System.out.println("2 - mudar login");
		System.out.println("3 - mudar senha");
		System.out.println("4 - mudar email");
		System.out.println("5 - mudar atributos");
		System.out.println("6 - sair");
		
		int choosedNumber = Integer.parseInt(0 + inputTerminal.nextLine());
		
		switch(choosedNumber) {
			case 0:
				return;
			case 1:
				changeProfileName(accountID);
				break;
			case 2:
				changeLogin(accountID);
				break;
			case 3:
				changePassword(accountID);
				break;
			case 4:
				changeEmail(accountID);
				break;
			case 5:
				changeAttributes(accountID);
				break;
			case 6:
				return;
			default:
				return;
		}
		
	}
	
	public static void manageAccount(int accountID) {
		while(true) {
			printHeader();
			
			System.out.println("1 - editar perfil");
			System.out.println("2 - adicionar amigo");
			System.out.println("3 - enviar messagem");
			System.out.println("4 - criar comunidade");
			System.out.println("5 - gerenciar comunidade");
			System.out.println("6 - recuperar info");
			System.out.println("7 - remover conta");
			System.out.println("8 - sair");
			
			int choosedNumber = Integer.parseInt(0 + inputTerminal.nextLine());
			
			switch(choosedNumber) {
				case 1:
					editProfile(accountID);
					break;
				case 2:
					addFriend(accountID);
					break;
				case 3:
					sendMessage(accountID);
					break;
				case 4:
					createCommunity(accountID);
					break;
				case 5:
					manageCommunity(accountID);
					break;
				case 6:
					recoveryInfo(accountID);
					break;
				case 7:
					removeAccount(accountID);
					return;
				default:
					return;
			}
		}
	}
	
	public static void loginAccount() {
		printHeader();
			
		System.out.print("Digite seu login: ");
		String myLogin = inputTerminal.nextLine();
			
		System.out.print("Digite sua senha: ");
		String myPassword = inputTerminal.nextLine();
			
		for(int i = 0; i < accountsSize; ++i) {
			if(allAccounts[loginDef][i] != null && (allAccounts[loginDef][i].equals(myLogin) && allAccounts[loginDef][i].equals(myPassword))) {
				manageAccount(i);
			}
		}
	}
	
	public static void createAccount() {
		while(true) {
			printHeader();
			
			System.out.print("Digite seu login: ");
			String newLogin = inputTerminal.nextLine();
			if(newLogin.length() == 0) {
				return;
			}
			
			System.out.print("Digite sua senha: ");
			String newPassword = inputTerminal.nextLine();
			if(newPassword.length() == 0) {
				return;
			}
			
			System.out.print("Digite seu nome de perfil: ");
			String newProfileName = inputTerminal.nextLine();
			if(newProfileName.length() == 0) {
				return;
			}
			
			System.out.print("Digite seu email: ");
			String newEmail = inputTerminal.nextLine();
			if(newEmail.length() == 0) {
				return;
			}
			
			for(int i = 0; i < accountsSize; ++i) {
				if(allAccounts[loginDef][i] != null && (allAccounts[loginDef][i].equals(newLogin) || allAccounts[emailDef][i].equals(newEmail))) {
					
					System.out.println();
					System.out.println("login ou email já existente, tente novamente");
					System.out.println();
					
					inputTerminal.nextLine();
					
					return;
				}
			}
			
			allAccounts[loginDef][accountsSize] = newLogin;
			allAccounts[passwordDef][accountsSize] = newPassword;
			allAccounts[profileNameDef][accountsSize] = newProfileName;
			allAccounts[emailDef][accountsSize] = newEmail;
			
			accountsSize++;
			
			System.out.println();
			System.out.println("conta criada com sucesso, basta logar");
			System.out.println();
			
			inputTerminal.nextLine();
			
			return;
		}
	}
	
	public static void firstScreen() {
		while(true) {
			printHeader();
			
			System.out.println("1 - logar na conta");
			System.out.println("2 - criar conta");
			System.out.println("3 - sair");
			
			int choosedNumber = Integer.parseInt(0 + inputTerminal.nextLine());
			
			if(choosedNumber <= 3) {
				switch(choosedNumber) {
					case 1:
						loginAccount();
						break;
					case 2:
						createAccount();
						break;
					case 3:
						System.exit(0);
						break;
					default:
						return;
				}
			}
		}
	}
			
	public static void main(String[] args) {
		firstScreen();
	}
}
