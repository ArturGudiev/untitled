package vk;


import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Chat;
import com.vk.api.sdk.objects.messages.responses.GetDialogsResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQuery;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.REDIRECT_URI;
import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

// app_id 6729060
// token 7344fdf38b759baaa550b5129b5b8cf21bbc21e68b601ab5c0a1228c858cfda526b9b96a8b4190cdc97f5
public class VK {
    static ServiceActor actor = getActor();
    static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance() );

    public static ServiceActor getActor(){
        Integer appId = 6729060;
//        String accessToken = "2f306162b701572d134ccf8b87f045fff7bc9746d82db0fc9154c9b42188ec3dd54d7f90e598994bbe4d7";
        String accessToken = "d9238044fa4ac5151de31f3d62f0351b8f564037e6818a04bf72b8105c038a8184a1bb8a48c1460a33df2";
        ServiceActor actor = new ServiceActor(appId, accessToken);
        return actor;
    }

    public static UserActor getUserActor(){
        Integer appId = 6729060;
        String accessToken = "2f306162b701572d134ccf8b87f045fff7bc9746d82db0fc9154c9b42188ec3dd54d7f90e598994bbe4d7";
        UserActor actor = new UserActor(appId, accessToken);
        return actor;
    }

    public static void printFriends(){
        try {
            com.vk.api.sdk.objects.friends.responses.GetResponse friends = vk.friends().get(actor).execute();
            List<UserXtrCounters> friendsAccounts = vk.users().get(actor).userIds(String.valueOf(friends.getItems())).execute();
//            System.out.println(friends);
            for (int i = 0; i < friendsAccounts.size(); i++) {
                String firstName = friendsAccounts.get(i).getFirstName();
                String lastName = friendsAccounts.get(i).getLastName();
                System.out.println(i + " " + firstName + " " + lastName);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void unreadMessages(){
//        vk.messages().get(actor);
        try {
            UserActor userActor = getUserActor();
            List<Integer> list = Arrays.asList(12);
            List<Integer> range = IntStream.range(0, 20).boxed().collect(Collectors.toList());
            vk.messages().getChat(userActor, range).execute();
            GetDialogsResponse messages = vk.messages().getDialogs(userActor).unread(true).execute();
            System.out.println(messages.getCount());
            for (int i = 0; i < messages.getCount(); i++) {
                String title = messages.getItems().get(i).getMessage().getTitle();
                String text = messages.getItems().get(i).getMessage().getActionText();
                System.out.println(title + " --- " + text);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void news(){
        try {
            UserActor userActor = getUserActor();
            List<Integer> list = Arrays.asList(12);
            vk.newsfeed().get(getUserActor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unreadMessages();
        if(args.length > 0 && args[0].equals("unread")){
            unreadMessages();
            executeCommand("exit");
//            Thread.interrupted();
        }
        System.exit(1);
        //Create service actor
//        ServiceActor actor = getActor();

//        printFriends();
//        unreadMessages();

//        news();
//        System.out.println(getResponse.count(2));
//        vk.oauth()
//                .userAuthorizationCodeFlow(6729060, CLIENT_SECRET, REDIRECT_URI, code)
//                .execute();

//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
//        UserAuthResponse authResponse = vk.oauth()
//                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
//                .execute();
//
//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

//        UserAuthResponse authResponse = vk.oauth()
//                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
//                .execute();

//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
    }

    private static void executeCommand(String s) {
    }
}
