package mvc.view;
class StartView {
    
    public static void main(String []args) {
        System.out.println("===== 모의 투자 프로그램을 시작합니다. =====");

        MenuView mv = new MenuView(); // 전역변수 초기화, 생성자 호출 
        mv.printMenu();

    }
    
}
