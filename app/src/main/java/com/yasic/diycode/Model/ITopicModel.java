package com.yasic.diycode.Model;

import com.yasic.diycode.Bean.CallbackBean;

/**
 * Created by Yasic on 2016/5/18.
 */
public interface ITopicModel {
    CallbackBean getTopicList(String page);
    CallbackBean getTopicDetail(String topicSequence);
}
