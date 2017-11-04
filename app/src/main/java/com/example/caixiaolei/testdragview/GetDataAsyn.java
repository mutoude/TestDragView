package com.example.caixiaolei.testdragview;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by caixiaolei on 2017/11/3.
 */

public class GetDataAsyn extends AsyncTask<String, Integer, NetCode> {

    private String path;
    private List<NameValueParama> list;
    private NetCallBack netCallBack;


    public GetDataAsyn(String path, List<NameValueParama> list, NetCallBack netCallBack) {
        this.path = path;
        this.list = list;
        this.netCallBack = netCallBack;
    }

    @Override
    protected NetCode doInBackground(String... pa) {
        NetCode netCode = new NetCode();
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                netCode.setState(NetCode.STATE_OK);
                JSONObject jsonObject = new JSONObject(baos.toString("utf-8"));
                netCode.setMessage(baos.toString("utf-8"));
                netCode.setJsonObject(jsonObject);


            } else {
                netCode.setState(NetCode.STATE_ERROR);
                netCode.setMessage("请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            netCode.setState(NetCode.STATE_ERROR);
            netCode.setMessage("请求失败");
        }
        return netCode;
    }

    @Override
    protected void onPostExecute(NetCode netCode) {
        super.onPostExecute(netCode);
        //异步任务要及时停止
        if (!isCancelled()) {
            if (netCode.getState() == NetCode.STATE_OK) {
                if (netCallBack != null) {
                    netCallBack.success(netCode);
                }
            } else {
                if (netCallBack != null) {
                    netCallBack.fail("出异常了");
                }
            }
        }
    }
}
