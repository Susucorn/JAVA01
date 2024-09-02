// 관리자 클래스(User 클래스를 상속 받음)

import java.util.Scanner;

class ProgramManager extends User {
	// Payment 클래스의 객체를 생성하여 접근
	Payment pay = new Payment();
	
	// 속성
	private int using_pc = 50;
	
	// 관리자의 아이디와 비밀번호를 입력하게 하는 메소드
	public void pmlogin() {
		String[] admin = users.get(0);
	    Scanner stdIn = new Scanner(System.in);
	    System.out.println("\n<< 관리자 ID와 비밀번호를 입력해주세요(입력 후 Enter) >>");
	    System.out.print("ID : ");
	    String pmID = stdIn.nextLine();
	    System.out.print("PW : ");
	    String pmPW = stdIn.nextLine();
	    
	    if (admin[1].equals(pmID) && admin[2].equals(pmPW)) { pmMenu(); }
	    else {
	        System.out.println("\n관리자 ID 혹은 비밀번호가 틀려 사용을 제한합니다.");
	        System.out.println("처음화면으로 돌아갑니다.\n");
	    }
	}
	
	// 오버라이딩 관리자의 메뉴를 출력하는 메소드 --> 부모 클래스의 pm_menu_option 메소드 
	@Override
    public void pm_menu_option() { 
		System.out.println("\n<< GETO SHOP 관리자 설정입니다 >>");
    	System.out.println("1. 회원관리");
    	System.out.println("2. 총 수익");
    	System.out.println("3. 자리현황");
    	System.out.println("0. 처음화면으로");
    	System.out.print("(입력 후 Enter) : ");
	}
	
	// 관리자가 선택한 메뉴에 대한 동작을 수행하는 메소드
    private void pmMenu() {
    	// Scanner 객체 생성
        Scanner stdIn = new Scanner(System.in);
        
        // 속성
        int choice = 9;
        
        // 0번을 선택하지 않을 때까지 반복하는 문장
        while (choice != 0) {
        	pm_menu_option(); 			// 부모 클래스 메소드를 오버라이딩 한 pm_menu_option 메소드 호출
            choice = stdIn.nextInt(); 	// 입력 받은 메뉴를 choice 저장

            switch (choice) {
                case 1: // 회원관리
                	displayUsers();
                    break;
                case 2: // 총 수익
                	pay.pm_Payment();
                    break;
                case 3: // 자리현황
                	Using_PC();
                    break;
                case 0:
                    System.out.println("\n처음 화면으로 돌아갑니다.\n");
                    break;
                default:
                    System.out.println("\n잘못된 선택입니다. 다시 선택해주세요.\n");
            }
        }
    }
    
// 회원 관리 메소드
	private void displayUsers() {
    	// Scanner 객체 생성
        Scanner stdIn = new Scanner(System.in);
		char res = 'y';
        while(res == 'y' || res == 'Y') {
        	System.out.println("\n<< 회원 목록 >>");
        	int count = 0;
        	// 리스트에서 등록된 사용자 정보 출력
        	for (int i = 1; i < users.size(); i++) {
        	    String[] userInfo = users.get(i);
        	    System.out.println(++count + ". 이름 : " + userInfo[0] + " | " + "아이디 : " + userInfo[1] + " | " + "이메일 : " + userInfo[3]);
        	}
	        
	        if (count != 0){ System.out.println("총 " + count + "명의 회원이 등록되어 있습니다.\n"); }
	        else { System.out.println("현재 등록된 회원이 없습니다.\n"); }

        	System.out.println("회원을 수정하시겠습니까?[y/n]");
        	System.out.print("(입력 후 Enter) : ");
        	res = stdIn.next().charAt(0);
        	if (res == 'y' || res == 'Y') {
        		System.out.println("회원 삭제는 번호 입력란에 등록된 회원 번호를 입력,");
        		System.out.println("회원 추가를 원하시면 번호 입력란에 \"0\"을 입력하시고 회원 정보를 입력하여 주십시오.");
        		System.out.println("입력 즉시 회원 정보가 변경됩니다. 신중하게 입력하여 주십시오.(입력 후 Enter)\n");
        		System.out.print("번호 : ");
        		int index = stdIn.nextInt();
        		stdIn.nextLine();
        		if(index == 0) {
            		System.out.print("이름 : ");
            		String name = stdIn.nextLine();
            		System.out.print("아이디 : ");
            		String id = stdIn.nextLine();
            		System.out.print("비밀번호 : ");
            		String password = stdIn.nextLine();
            		System.out.print("이메일 : ");
            		String mail = stdIn.nextLine();
            		
                    user = new String[] { name, id, password, mail }; // 새로운 배열에 사용자 이름, 아이디, 비밀번호, 이메일 저장
                    users.add(user); // 생성한 배열을 리스트에 추가
                    
            		System.out.println("\n회원 등록이 완료 되었습니다.");
        		}
        		else {
        			if(index > users.size() - 1 || index < 0) {
        				System.out.println("존재하지 않은 회원입니다.");
        			}
        			else {
        				int i = 1;
        				String[] finduser = users.get(index);
        				for(; i < users.size(); i++) {
        					String[] userInfo = users.get(i);
        					if(finduser[0] != userInfo[0] || finduser[1] != userInfo[1] || finduser[3] != userInfo[3]) {
        						continue;
        					}
        					users.remove(index);
        					System.out.println("해당 회원이 성공적으로 삭제되었습니다!");
        				}
        				if(i == users.size()) { System.out.println("존재하지 않은 회원입니다."); }
        			}
        		}
        	}
        }
    }
	
	private void Using_PC() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("\n<< 좌석 현황 >>");
		System.out.println("현재 좌석은 총 " + using_pc + "석 있습니다.");
		System.out.print("수정하시겠습니까? (y/n): ");
    	char res = stdIn.next().charAt(0);
    	if (res == 'y' || res == 'Y' ) {
    		System.out.println("\n<< 좌석 변경 >>");
    		System.out.println("\n변경할 좌석의 개수를 입력하세요(입력 후 Enter)");
    		System.out.print("현재 " + using_pc + "석 -> ");
    		using_pc = stdIn.nextInt();
    		System.out.print("\n좌석이 " + using_pc + "석으로 변경되었습니다.");
    	}
    	else { System.out.println("\n이전 화면으로 돌아갑니다."); }
	}
}