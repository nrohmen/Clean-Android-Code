package com.plaps.androidcleancode;

import com.plaps.androidcleancode.home.HomePresenter;
import com.plaps.androidcleancode.home.HomeView;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.NetworkService;
import com.plaps.androidcleancode.networking.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private NetworkService networkService;

    @Mock
    private HomeView homeView;

    @Mock
    private CityListResponse cityListResponse;

    private Service service;
    private HomePresenter homePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new Service(networkService);
        homePresenter = new HomePresenter(service, homeView);
    }

    @After
    public void teardown() {
        homePresenter.onStop();
    }

    @Test
    public void loadCitiesFromAPIAndLoadIntoView() {

        Observable<CityListResponse> responseObservable = Observable.just(cityListResponse);
        when(networkService.getCityList()).thenReturn(responseObservable);

        homePresenter.getCityList();

        InOrder inOrder = Mockito.inOrder(homeView);
        inOrder.verify(homeView).showWait();
        inOrder.verify(homeView).removeWait();
        inOrder.verify(homeView).getCityListSuccess(cityListResponse);
    }
}
