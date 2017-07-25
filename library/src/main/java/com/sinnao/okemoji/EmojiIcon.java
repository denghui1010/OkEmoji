package com.sinnao.okemoji;

import android.support.annotation.DrawableRes;

/**
 * 表情
 * Created by Liu Denghui on 16/8/8.
 */
public class EmojiIcon {
    /**
     * 表情名称, 例如smile
     */
    private String mName;
    /**
     * 表情别名, 例如[smile], <smile>
     */
    private String mAlias;
    /**
     * 表情在网络中的值
     */
    private String mValue;
    /**
     * unicode
     */
    private byte[] mCodePoint;
    /**
     * 表情路径
     */
    private String mLocalPath;
    /**
     * 表情url
     */
    private String mRemoteUrl;
    /**
     * 表情资源
     */
    @DrawableRes
    private int mResId;

    private ValueHandler mValueHandler;

    /**
     * 使用ValueHandler来生成表情的value
     *
     * @param name      表情名称
     * @param codePoint 表情unicode
     * @param value     表情在网络中的值
     * @param resId     表情资源
     * @param localPath 表情路径
     */
    public EmojiIcon(String name, byte[] codePoint, String value, int resId, String localPath) {
        mName = "[" + name + "]";
        mAlias = "[" + name + "]";
        mCodePoint = codePoint;
        mValue = value;
        mLocalPath = localPath;
        mResId = resId;
    }

    /**
     * 使用ValueHandler来生成表情的value
     *
     * @param name         表情名称
     * @param resId        表情资源
     * @param remoteUrl    表情路径
     * @param valueHandler ValueHandler
     */
    public EmojiIcon(String name, int resId, String remoteUrl, ValueHandler valueHandler) {
        mName = "[" + name + "]";
        mAlias = "[" + name + "]";
        mLocalPath = remoteUrl;
        mResId = resId;
        mValueHandler = valueHandler;
        mValue = valueHandler.getValue(name, null, resId, null, remoteUrl);
    }

    /**
     * 使用ValueHandler来生成表情的value
     *
     * @param name         表情名称
     * @param localPath    表情资源路径
     * @param remoteUrl    表情路径
     * @param valueHandler ValueHandler
     */
    public EmojiIcon(String name, String localPath, String remoteUrl, ValueHandler valueHandler) {
        mName = "[" + name + "]";
        mAlias = "[" + name + "]";
        mLocalPath = localPath;
        mRemoteUrl = remoteUrl;
        mValueHandler = valueHandler;
        mValue = valueHandler.getValue(name, null, 0, localPath, remoteUrl);
    }

    /**
     * 使用ValueHandler来生成表情的value
     *
     * @param name         表情名称
     * @param codePoint    表情unicode
     * @param remoteUrl    表情路径
     * @param valueHandler ValueHandler
     */
    public EmojiIcon(String name, byte[] codePoint, String remoteUrl, ValueHandler valueHandler) {
        mName = "[" + name + "]";
        mAlias = "[" + name + "]";
        mCodePoint = codePoint;
        mRemoteUrl = remoteUrl;
        mValueHandler = valueHandler;
        mValue = valueHandler.getValue(name, codePoint, 0, null, remoteUrl);
    }

    public String getAlias() {
        return mAlias;
    }

    public void setAlias(String alias) {
        mAlias = alias;
    }

    public ValueHandler getValueHandler() {
        return mValueHandler;
    }

    public void setValueHandler(ValueHandler valueHandler) {
        mValueHandler = valueHandler;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public byte[] getCodePoint() {
        return mCodePoint;
    }

    public void setCodePoint(byte[] codePoint) {
        mCodePoint = codePoint;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getLocalPath() {
        return mLocalPath;
    }

    public void setLocalPath(String localPath) {
        mLocalPath = localPath;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    @Override
    public String toString() {
        return "EmojiIcon{" +
                "mName='" + mName + '\'' +
//                ", mCodePoint=" + Arrays.toString(mCodePoint) +
//                ", mValue='" + mValue + '\'' +
//                ", mLocalPath='" + mLocalPath + '\'' +
//                ", mResId=" + mResId +
                '}';
    }

    public interface ValueHandler {
        String getValue(String name, byte[] codePoint, int resId, String path, String url);
    }
}
