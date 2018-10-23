package vk;


import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQuery;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.REDIRECT_URI;
// app_id 6729060
// token 7344fdf38b759baaa550b5129b5b8cf21bbc21e68b601ab5c0a1228c858cfda526b9b96a8b4190cdc97f5
public class VKClient {
    static ServiceActor actor = getActor();
    static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance() );

    public static ServiceActor getActor(){
        Integer appId = 6729060;
        String accessToken = "2f306162b701572d134ccf8b87f045fff7bc9746d82db0fc9154c9b42188ec3dd54d7f90e598994bbe4d7";
        ServiceActor actor = new ServiceActor(appId, accessToken);
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
    }

    public static void main(String[] args) {
       //Create service actor
//        ServiceActor actor = getActor();
        printFriends();

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
}
