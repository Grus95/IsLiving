package com.example.grus95.codelibrary.test_activity.TVShowTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingho on 2016/12/7.
 */

public class TVShowFactory {
    public static List<TVShowModel> createOpenProjects() {
        List<TVShowModel> openProjects = new ArrayList<>();

        openProjects.add(new TVShowModel("香港卫视", "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8", "#FFC47B3F"));
        openProjects.add(new TVShowModel("美国中文台", "http://ec.sinovision.net/video/ts/lv.m3u8", "#FF940044"));
        openProjects.add(new TVShowModel("核能小清新(韩主播)", "http://dlhls.cdn.zhanqi.tv/zqlive/88945_Kligp.m3u8", "#FF234458"));
        openProjects.add(new TVShowModel("中经电视", "rtmp://116.213.200.53/tslsChannelLive/PCG0DuD/live", "#FF1B6070"));
        openProjects.add(new TVShowModel("CCTV1高清", "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8", "#FF700957"));
        openProjects.add(new TVShowModel("CCTV3高清", "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8", "#FF5B83ED"));

        openProjects.add(new TVShowModel("CCTV5高清", "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8", "#FFC47B3F"));
        openProjects.add(new TVShowModel("CCTV5+高清", "http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8", "#FF940044"));
        openProjects.add(new TVShowModel("CCTV6高清", "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8", "#FF234458"));
        openProjects.add(new TVShowModel("香港卫视精品台 ", "http://221.120.177.59/hls/i3d7ragr.m3u8", "#FF1B6070"));
        openProjects.add(new TVShowModel("酷六V音乐", "http://main.gslb.ku6.com/broadcast/sub?channel=910", "#FF700957"));
        openProjects.add(new TVShowModel("本港台", "http://v.hrtv8.com/vip.hrtv8.com/stream/2506.m3u8", "#FF5B83ED"));
        return openProjects;
    }
}
