package pl.etroya.design.proxy;

public class TwitterServiceStub implements TwitterService {
    @Override
    public String getTimeline(String screenName) {
        return "neato timeline :)";
    }

    @Override
    public void postToTimeline(String screenName, String message) {

    }
}
