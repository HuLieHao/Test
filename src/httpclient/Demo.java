package httpclient;

import com.google.gson.Gson;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User:  maktub
 * Date:   16/1/16 下午12:13
 */
public class Demo {

//    public static final String openIdUrl = "http://api.idoipo.com/third_weixin/auth/getOpenidByCode";

    public static final String voteUrl = "http://api.idoipo.com/third_weixin/auth/vote";

    public static int total = -1;

    public static String voteid = "17";  //刷票ID
    public static int votenum = 260;  //刷票数

    public String postMethod(String url, NameValuePair[] params) {

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        String result = "";
        try {
            postMethod.setRequestBody(params);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
            }
            byte[] responseBody = postMethod.getResponseBody();
            result = new String(responseBody);

        }catch (HTTPException e) {
            //发生致命异常
            e.printStackTrace();
        }catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        }finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return result;
    }

    public String setVote(int n) {
        Random randData = new Random();
        NameValuePair voteid = new NameValuePair("voteid", this.voteid);
        NameValuePair memberid = new NameValuePair("memberid", randData.nextInt(7069) + "");

        NameValuePair[] params = {voteid, memberid};

        return postMethod(voteUrl, params);

    }

    public static void main(String[] args) throws InterruptedException {

        Demo demo = new Demo();
        Gson gson = new Gson();

        int i = 0;
        int n = 0;

        int m = 0;

        Random rand = new Random();

        n = 3871;
        while (true) {

            String result = demo.setVote(++n);
            Data data = gson.fromJson(result, Data.class);

//            if ("粉丝记录不存在".equals(data.getMsg())) { // || total == data.getTotal()
//                System.out.println(n + " 位粉丝全部投票完毕!");
//                return;
//            }

            if (!"".equals(data.getMsg()) || total == data.getTotal()) {
                continue;
            }else {
                total = data.getTotal();
                i++;
            }

            System.out.println(result);

            if (i == votenum)  {
                System.out.println("刷到第 " + n + " 位");
                return;
            }

//            n = (rand.nextInt(5) + 1);
//            System.out.println("休息 " + n + " 分钟");
//            Thread.sleep(1000 * 60 * n);

//            if (n == 77) {
//                System.out.println("粉丝全部投票完毕!");
//                return;
//            }
        }
    }


    class Data {
        public String code;

        public String msg;

        public Object info;

        public int total;

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public Object getInfo() {
            return info;
        }

        public String getMsg() {
            return msg;
        }
    }



}
