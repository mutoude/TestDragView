package com.example.caixiaolei.testdragview;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by caixiaolei on 2017/11/3.
 */

public class FragmentFirst extends BaseFragment {

    private NetCallBack netCallBack;

    private TextView textView;
    private GetDataAsyn getDataAsyn;

    @Override
    protected void loadData() {
        stopAnsy();
        netCallBack = new NetCallBack() {
            @Override
            public void success(NetCode netCode) {
                Log.e("FragmentFirst", netCode.getJsonObject().toString());
                textView.setText(netCode.getJsonObject().toString());
            }

            @Override
            public void fail(String msg) {

            }

        };

        getDataAsyn = new GetDataAsyn("https://api.douban.com/v2/book/1220562", null, netCallBack);
        getDataAsyn.execute("");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        textView = (TextView) rootView.findViewById(R.id.tv);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (netCallBack != null) {
            netCallBack = null;
        }

        stopAnsy();
    }

    private void stopAnsy() {
        if (getDataAsyn != null) {
            if (!getDataAsyn.isCancelled()) {
                getDataAsyn.cancel(true);
            }
            getDataAsyn = null;
        }
    }
}
