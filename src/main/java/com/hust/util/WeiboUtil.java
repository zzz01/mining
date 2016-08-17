package com.hust.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeiboUtil {
    public static List<String> filter(List<String> list) {
        if (null == list || list.size() == 0) {
            return null;
        }
        List<String> li = new ArrayList<String>();
        for (String str : list) {
            str = str.replaceAll("@\\S+\\s?[：|:]\\s?\\S*", "");
            str = str.replaceAll("@\\S+", "");
            str = str.replaceAll("\\\\", "");
            str = str.replaceAll("#.*#", "");
            str = str.replaceAll("#.*#", "");
            str = str.replaceAll("\\|\\s*\\S+", "");
            li.add(str);
        }
        return li;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("在我得知后，我报了警。我一时半会没回去到，当时她们到了派出所，"
                + "警察听了像泼妇一样的她们的片面之词。说是有短信为证，我没录音，"
                + "反而给我口头警告，她们在我家来闹了，诽谤我后，骂了我爸妈以后，"
                + "反而还要我爸妈给她们道歉！ @平安成都 @彭州法院 @直播成都 @平安彭州 | 成都·静安...#>>>四川");
        list.add("【盗窃高考考点财物团伙被邛崃警方抓获】6月3日，高考前夕，邛崃一中、"
                + "文昌中学两处高考考点接连被盗，损失电脑、相机等40余件，价值8万余元，影响恶劣。"
                + "案发后，我局民警迅速开展侦查工作，于6月15日在成都龙泉驿区将5人犯罪团伙中的4人抓获。"
                + "经调查，该团伙系盗窃惯犯，流窜作案数十起。目前，警方正全力抓捕另外一名在逃人员。 @平安成都 @醉美邛崃 @四川公安#>>>四川");
        list.add("#肉男被挑衅# 花样作死 不做不死，又一个笨贼去体校偷东西，最后靠警察解救，月17日，一名男子潜入成都体院行窃，"
                + "被发现后引来近百肌肉男的围追堵截，衣服被撕烂的小偷最后被警察“救”走。不作死就不会死 真的是不做不死，no do no die啊！ 鞋般翅蟓#>>>四川");
        list.add(
                "丢大成都的脸！！ @第四城社区 @成都全接触 @四川电视台 @成都电视台 // @宋仲基贴吧官博 :松果们，一定记得保护好自己手中的票！"
                        + " // @一直播 : 希望松果们没有因此受到影响。大家一起引以为戒吧。 @宋仲基贴吧官博 @Kiss宋仲基中文网 // @一直播 : 求助警察蜀黍。 @四川公安 @平安成都");
        List<String> li = filter(list);
        System.out.println(li);
    }
}
