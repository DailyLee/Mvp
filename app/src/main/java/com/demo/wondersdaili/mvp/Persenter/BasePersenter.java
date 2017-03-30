package com.demo.wondersdaili.mvp.Persenter;

import com.demo.wondersdaili.mvp.View.BaseView;

/**
 * Created by daili on 2017/3/30.
 */

public interface BasePersenter<T extends BaseView> {
    void register(T t);

    void unRegister();
}
