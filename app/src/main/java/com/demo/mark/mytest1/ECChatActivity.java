package com.demo.mark.mytest1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;

import static com.hyphenate.easeui.EaseConstant.CHATTYPE_GROUP;

/**
 * 作者：mark on 2017/7/6 11:45
 * <p>
 * 邮箱：2285581945@qq.com
 */
public class ECChatActivity extends AppCompatActivity {
    // 当前聊天的 ID
    private String mChatId;
    private EaseChatFragment chatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // 这里直接使用EaseUI封装好的聊天界面
        chatFragment = new EaseChatFragment();
        // 将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();

        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
