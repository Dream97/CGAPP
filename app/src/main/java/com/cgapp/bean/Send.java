package com.cgapp.bean;

/**
 * Created by asus on 2017/3/29.
 */
/**
{
        "link_v2_sync_img": "http://jingxuan.guokr.com/pick/v2/81349/sync/",
        "source_name": "天了噜！",
        "video_url": "",
        "current_user_has_collected": false,
        "likings_count": 9,
        "images": [
        "http://1.im.guokr.com/AqNz8KqRjl-Erd1-cVi81pheTGRjIKrFjfOSygRimTcAAwAAJQIAAEpQ.jpg?imageView2/1/w/480/h/343",
        "http://3.im.guokr.com/N5-vsIeuogjhBdhgvGPaP1NncDKcPDm3zvT6fuQiSo-rAQAARwIAAEpQ.jpg"
        ],
        "video_duration": null,
        "id": 81349,
        "category": "life",
        "style": "article",
        "title": "医生在手术时说什么让你感到最恐怖？",
        "source_data": {
        "image": "http://2.im.guokr.com/r8PINb_RG_niPP_rsxxvHLK7HmQE9i1NZD6pWV_0VDKgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160",
        "summary": "吸走你的无聊时间！",
        "id": 52,
        "key": "天了噜！",
        "title": "天了噜！"
        },
*/
public class Send {
    private String title;
    private String[] images;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getImages() {
        return images;
    }
}
