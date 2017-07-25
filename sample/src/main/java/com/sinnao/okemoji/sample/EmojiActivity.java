package com.sinnao.okemoji.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sinnao.okemoji.EmojiCategory;
import com.sinnao.okemoji.EmojiEditText;
import com.sinnao.okemoji.EmojiIcon;
import com.sinnao.okemoji.view.EmojiPanel;


/**
 * 表情显示demo
 * Created by Liu Denghui on 16/8/10.
 */
public class EmojiActivity extends AppCompatActivity {

    private EmojiEditText mEdit;
    private TextView mShowRichText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        final EmojiPanel emoji = (EmojiPanel) findViewById(R.id.emoji);
        mShowRichText = (TextView) findViewById(R.id.showRichText);
        mEdit = (EmojiEditText) findViewById(R.id.edit);
        assert emoji != null;
        assert mEdit != null;
        EmojiIcon.ValueHandler valueHandler = new EmojiIcon.ValueHandler() {

            @Override
            public String getValue(String name, byte[] codePoint, int resId, String path, String url) {
                return name;
            }
        };
        emoji.addCategory(new EmojiCategory("表情大", R.mipmap.ic_launcher, new EmojiIcon[]{
                new EmojiIcon("惊讶1", R.drawable.emoji_11, "/ask/resources/images/facialExpression/qq/01.gif", valueHandler),
                new EmojiIcon("撇嘴1", R.drawable.emoji_12, "/ask/resources/images/facialExpression/qq/02.gif", valueHandler),
                new EmojiIcon("色1", R.drawable.emoji_13, "/ask/resources/images/facialExpression/qq/03.gif", valueHandler),
                new EmojiIcon("发呆1", R.drawable.emoji_14, "/ask/resources/images/facialExpression/qq/04.gif", valueHandler),
                new EmojiIcon("得意1", R.drawable.emoji_15, "/ask/resources/images/facialExpression/qq/05.gif", valueHandler),
                new EmojiIcon("害羞1", R.drawable.emoji_16, "/ask/resources/images/facialExpression/qq/06.gif", valueHandler),
                new EmojiIcon("闭嘴1", R.drawable.emoji_17, "/ask/resources/images/facialExpression/qq/07.gif", valueHandler),
                new EmojiIcon("睡1", R.drawable.emoji_18, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("骂1", R.drawable.emoji_19, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("怒1", R.drawable.emoji_10, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
        }, 2, 4));
        emoji.addCategory(new EmojiCategory(EmojiCategory.LOCAL_EMOJI, R.mipmap.ic_launcher, new EmojiIcon[]{
                new EmojiIcon("惊讶", "file:///android_asset/emoji/emoji_01.png", "/ask/resources/images/facialExpression/qq/01.gif", valueHandler),
                new EmojiIcon("撇嘴", "file:///android_asset/emoji/emoji_02.png", "/ask/resources/images/facialExpression/qq/02.gif", valueHandler),
                new EmojiIcon("色", "file:///android_asset/emoji/emoji_03.png", "/ask/resources/images/facialExpression/qq/03.gif", valueHandler),
                new EmojiIcon("发呆", "file:///android_asset/emoji/emoji_04.png", "/ask/resources/images/facialExpression/qq/04.gif", valueHandler),
                new EmojiIcon("得意", "file:///android_asset/emoji/emoji_05.png", "/ask/resources/images/facialExpression/qq/05.gif", valueHandler),
                new EmojiIcon("害羞", "file:///android_asset/emoji/emoji_06.png", "/ask/resources/images/facialExpression/qq/06.gif", valueHandler),
                new EmojiIcon("闭嘴", "file:///android_asset/emoji/emoji_07.png", "/ask/resources/images/facialExpression/qq/07.gif", valueHandler),
                new EmojiIcon("睡", "file:///android_asset/emoji/emoji_08.png", "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("骂", "file:///android_asset/emoji/emoji_09.png", "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("怒", R.drawable.emoji_10, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("惊讶g", R.drawable.emoji_11, "/ask/resources/images/facialExpression/qq/01.gif", valueHandler),
                new EmojiIcon("撇嘴g", R.drawable.emoji_12, "/ask/resources/images/facialExpression/qq/02.gif", valueHandler),
                new EmojiIcon("色g", R.drawable.emoji_13, "/ask/resources/images/facialExpression/qq/03.gif", valueHandler),
                new EmojiIcon("发呆g", R.drawable.emoji_14, "/ask/resources/images/facialExpression/qq/04.gif", valueHandler),
                new EmojiIcon("得意g", R.drawable.emoji_15, "/ask/resources/images/facialExpression/qq/05.gif", valueHandler),
                new EmojiIcon("害羞g", R.drawable.emoji_16, "/ask/resources/images/facialExpression/qq/06.gif", valueHandler),
                new EmojiIcon("闭嘴g", R.drawable.emoji_17, "/ask/resources/images/facialExpression/qq/07.gif", valueHandler),
                new EmojiIcon("睡g", R.drawable.emoji_18, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("骂g", R.drawable.emoji_19, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("怒g", R.drawable.emoji_10, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("惊讶g", R.drawable.emoji_11, "/ask/resources/images/facialExpression/qq/01.gif", valueHandler),
                new EmojiIcon("撇嘴g", R.drawable.emoji_12, "/ask/resources/images/facialExpression/qq/02.gif", valueHandler),
                new EmojiIcon("色g", R.drawable.emoji_13, "/ask/resources/images/facialExpression/qq/03.gif", valueHandler),
                new EmojiIcon("发呆g", R.drawable.emoji_14, "/ask/resources/images/facialExpression/qq/04.gif", valueHandler),
                new EmojiIcon("得意g", R.drawable.emoji_15, "/ask/resources/images/facialExpression/qq/05.gif", valueHandler),
                new EmojiIcon("害羞g", R.drawable.emoji_16, "/ask/resources/images/facialExpression/qq/06.gif", valueHandler),
                new EmojiIcon("闭嘴g", R.drawable.emoji_17, "/ask/resources/images/facialExpression/qq/07.gif", valueHandler),
                new EmojiIcon("睡g", R.drawable.emoji_18, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("骂g", R.drawable.emoji_19, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
                new EmojiIcon("怒g", R.drawable.emoji_10, "/ask/resources/images/facialExpression/qq/08.gif", valueHandler),
        }, 3, 7));
        mEdit.bindEmojiPanel(emoji);
    }

    public void send(View view) {
        String emojiText = mEdit.getValueText();
        System.out.println("要显示的emojiText========" + emojiText);
//        mEdit.clear();
    }
}
