package com.example.grus95.codelibrary.test_activity.TVShowTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingho on 2016/12/7.
 */

public class TVShowFactory {
    public static List<TVShowModel> createOpenProjects() {
        List<TVShowModel> openProjects = new ArrayList<>();

        openProjects.add(new TVShowModel("湖南卫视", "http://119.6.100.4:9000/abc/prog_index.m3u8", "#FF1B6070"));
        openProjects.add(new TVShowModel("CCTV6 电影", "http://t.live.cntv.cn/m3u8/cctv6-380.m3u8", "#FF234458"));
        openProjects.add(new TVShowModel("广东卫视", "http://219.232.160.141:5080/hls/e6b65f216a24d0a17f993ee27396b47a.m3u8", "#FF700957"));
        openProjects.add(new TVShowModel("美国中文台", "http://ec.sinovision.net/video/ts/lv.m3u8", "#FF940044"));
        openProjects.add(new TVShowModel("星卫娱乐", "http://dlhls.cdn.zhanqi.tv/zqlive/1285_Zloy4.m3u8", "#FF5B83ED"));
        openProjects.add(new TVShowModel("香港卫视", "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8", "#FFC47B3F"));
        openProjects.add(new TVShowModel("爆笑頻道", "http://iliketot.dyndns.tv/tot/we ... bb9aac94b00d4f.m3u8", "#FF1B6070"));
        openProjects.add(new TVShowModel("Universal Channel環球影劇頻道", "http://iliketot.dyndns.tv/tot/we ... f4f71f2352b892.m3u8", "#FF234458"));
        openProjects.add(new TVShowModel("凤凰卫视装逼专属", "rtmp://live.hkstv.hk.lxdns.com/live/hks", "#FF940044"));

        return openProjects;
    }
}
