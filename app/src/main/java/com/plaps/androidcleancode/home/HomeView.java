package com.plaps.androidcleancode.home;

import com.plaps.androidcleancode.models.CityListResponse;

/**
 * Created by ennur on 6/25/16.
 */
public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CityListResponse cityListResponse);

}
