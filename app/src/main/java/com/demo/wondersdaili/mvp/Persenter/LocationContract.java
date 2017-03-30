package com.demo.wondersdaili.mvp.Persenter;


import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.View.BaseView;

/**
 * Created by daili on 2017/3/9.
 */

public interface LocationContract {

    interface View extends BaseView {
        void loadLocation(BDLocation location);

        void loadLateKnownLocation(BDLocation location);

        void loadLateKnownLocationError(BDLocation location);

    }

    interface Persenter extends BasePersenter {
        void queryLocation();

        void queryLateKnownLocation();

    }
}
