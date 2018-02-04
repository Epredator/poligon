package pl.etroya.design.proxy;

public class TwitterMainSample {
    public static void main(String[] args){
        TwitterService service = (TwitterService)SecurityProxy.newInstance(new TwitterServiceStub());
        System.out.println(service.getTimeline("test timeline"));

    }
}
