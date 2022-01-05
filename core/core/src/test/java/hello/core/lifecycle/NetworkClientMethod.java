package hello.core.lifecycle;

public class NetworkClientMethod {
    private String url;

    public NetworkClientMethod() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

    // 빈이 다 생성, 의존관계 주입되고 나서 호출
    public void init(){
        System.out.println("NetworkClientInterface.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈의 생성주기가 끝나갈 때 호출
    public void close(){
        System.out.println("NetworkClientInterface.close");
        disconnect();
    }


}