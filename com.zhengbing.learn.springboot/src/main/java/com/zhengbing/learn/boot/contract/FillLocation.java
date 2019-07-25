package com.zhengbing.learn.boot.contract;

/**
 * 填充内容对象，包含需要填充内容的所在坐标和内容
 *
 * @author zhengbing
 * @descript
 * @date 2019-07-23
 * @email mydreambing@126.com
 * @since version 1.0
 */
public class FillLocation {

    private int lotPage;
    private float lotX;
    private float lotY;
    private String Content;

    public int getLotPage() {
        return lotPage;
    }

    public void setLotPage(int lotPage) {
        this.lotPage = lotPage;
    }

    public float getLotX() {
        return lotX;
    }

    public void setLotX(float lotX) {
        this.lotX = lotX;
    }

    public float getLotY() {
        return lotY;
    }

    public void setLotY(float lotY) {
        this.lotY = lotY;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
