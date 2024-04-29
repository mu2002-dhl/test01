import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.platform.com.auth.model.WxyyAsk;
import cn.platform.com.auth.model.WxyyAskMessage;

import java.util.ArrayList;

/**
 * @Description
 * @Data 2024-04-24 11:45
 */
public class WxyyTest {

    public static void main(String[] args) {
       /* HttpRequest post = HttpUtil.createPost("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=hE5OaUs6TsUun8VBhAuHJAjU&client_secret=ZC6mDFZvHa1YaidTqvap3bG7TIqxQhBb");
        post.header("Content-Type", "application/json");
        post.header("Accept", "application/json");
        HttpResponse execute = post.execute();
        System.out.println(execute.body());*/

        //access_token：24.cdd3915095683a71e3bfb180cc5388cd.2592000.1716530815.282335-63281363

/*        "messages": [
        {"role":"user","content":"hi"},
        {"role":"assistant","content":"Hello! How can I assist you today? If you have any questions or need information on a specific topic, feel free to ask."},
        {"role":"user","content": "介绍下长城"}
         ]*/
        String askUrl = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/yi_34b_chat?access_token=24.cdd3915095683a71e3bfb180cc5388cd.2592000.1716530815.282335-63281363";
        HttpRequest post = HttpUtil.createPost(askUrl);
        ArrayList<WxyyAsk> wxyyAsks = new ArrayList<>();
        wxyyAsks.add(new WxyyAsk("user", "hi"));
        wxyyAsks.add(new WxyyAsk("assistant", "Hello! How can I assist you today?"));
        wxyyAsks.add(new WxyyAsk("user", "介绍下武汉东湖"));
        WxyyAskMessage wxyyAskMessage = new WxyyAskMessage(wxyyAsks);
        post.body(JSONUtil.toJsonStr(wxyyAskMessage));
        post.header("Content-Type", "application/json");
        post.header("Accept", "application/json");
        HttpResponse execute = post.execute();
        System.out.println(execute.body());
    }
}
