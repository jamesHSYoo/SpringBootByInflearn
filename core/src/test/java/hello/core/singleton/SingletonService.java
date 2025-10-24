package hello.core.singleton;

public class SingletonService {

    //static 이므로 static 영역에 딱 하나만 생성됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 생성");
    }
    /*
     * 동작 원리
     * 1. static 영역에 객체 instance 를 미리 하나 생성해서 올려둔다.
     * 2. 이 객체 인스턴스가 필요 하면 오직 getInstance() 메서드를 통해서만 조회 할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
     * 3. 딱 1개의 객체 인스턴스만 존재 해야 하므로, 생성자를 private로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다. 
     */
}
