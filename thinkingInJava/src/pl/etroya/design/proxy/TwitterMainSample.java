package pl.etroya.design.proxy;

public class TwitterMainSample {
    public static void main(String[] args){
        TwitterService service = (TwitterService)SecurityProxy.newInstance(new TwitterServiceImpl());
        System.out.println(service.getTimeline("bh5k"));
        //service.postToTimeline("test message");

    }
}
