package utilities;

public class Get {

    public static String getVK(String client_id, String scope){
        return "https://oauth.vk.com/authorize?client_id=" + client_id
                + "&display=page&https://oauth.vk.com/blank.html&scope="+scope+"&response_type=token&v=5.87";
    }

    public static void main(String[] args) {
        String vk = getVK("6729060", "wall,offline,groups,messages,friends");
        // 7344fdf38b759baaa550b5129b5b8cf21bbc21e68b601ab5c0a1228c858cfda526b9b96a8b4190cdc97f5
        // 23a05e55cee64bf695a4c0b5b59d738605a010ed463f064f26bd2fe805ac0a0fe1bf2330b2f05252b8eb0
        // 6d2775b6ef15d95777b774cacd1a84bcb94109125a52c5774859dda6adcd24f1a254d0768ca4569c58edd
        // 2f306162b701572d134ccf8b87f045fff7bc9746d82db0fc9154c9b42188ec3dd54d7f90e598994bbe4d7
        Clipboard.clip(vk);
        System.out.println(vk);
    }
}
