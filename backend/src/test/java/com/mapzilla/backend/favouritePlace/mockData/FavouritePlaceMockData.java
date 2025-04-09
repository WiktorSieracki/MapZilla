package com.mapzilla.backend.favouritePlace.mockData;

import com.mapzilla.backend.model.FavouriteLocation;
import com.mapzilla.backend.request.AddFavouriteLocationRequest;
import com.mapzilla.backend.request.UpdateFavouriteLocationRequest;

import java.math.BigDecimal;
import java.util.List;

public class FavouritePlaceMockData {

    public static AddFavouriteLocationRequest createMockAddFavouriteRequestBadScore(){
        AddFavouriteLocationRequest request = new AddFavouriteLocationRequest();
        request.setScore(BigDecimal.valueOf(-10.00));
        request.setLat(BigDecimal.valueOf(50.0));
        request.setLon(BigDecimal.valueOf(20.0));
        request.setAvailablePlaces(List.of("A1", "A2"));
        request.setNotAvailablePlaces(List.of("B1"));
        return request;
    }

    public static AddFavouriteLocationRequest createMockAddFavouriteRequestBadLat(){
        AddFavouriteLocationRequest request = new AddFavouriteLocationRequest();
        request.setScore(BigDecimal.valueOf(10.00));
        request.setLat(BigDecimal.valueOf(-50.0));
        request.setLon(BigDecimal.valueOf(20.0));
        request.setAvailablePlaces(List.of("A1", "A2"));
        request.setNotAvailablePlaces(List.of("B1"));
        return request;
    }

    public static AddFavouriteLocationRequest createMockAddFavouriteRequestBadLon(){
        AddFavouriteLocationRequest request = new AddFavouriteLocationRequest();
        request.setScore(BigDecimal.valueOf(10.00));
        request.setLat(BigDecimal.valueOf(50.0));
        request.setLon(BigDecimal.valueOf(-20.0));
        request.setAvailablePlaces(List.of("A1", "A2"));
        request.setNotAvailablePlaces(List.of("B1"));
        return request;
    }


    public static AddFavouriteLocationRequest createMockAddFavouriteRequest(){
        AddFavouriteLocationRequest request = new AddFavouriteLocationRequest();
        request.setScore(BigDecimal.valueOf(10.00));
        request.setLat(BigDecimal.valueOf(50.0));
        request.setLon(BigDecimal.valueOf(20.0));
        request.setAvailablePlaces(List.of("A1", "A2"));
        request.setNotAvailablePlaces(List.of("B1"));
        return request;
    }

    public static FavouriteLocation createMockFavouriteLocation(AddFavouriteLocationRequest request){
        FavouriteLocation favouriteLocation = new FavouriteLocation();
        favouriteLocation.setScore(request.getScore());
        favouriteLocation.setLat(request.getLat());
        favouriteLocation.setLon(request.getLon());
        favouriteLocation.setAvailablePlaces(request.getAvailablePlaces());
        favouriteLocation.setNotAvailablePlaces(request.getNotAvailablePlaces());

        return favouriteLocation;
    }
    public static UpdateFavouriteLocationRequest createMockUpdateRequest() {
        UpdateFavouriteLocationRequest request = new UpdateFavouriteLocationRequest();
        request.setLat(BigDecimal.valueOf(50.061947));
        request.setLon(BigDecimal.valueOf(19.936856));
        request.setScore(BigDecimal.valueOf(9.0));
        return request;
    }
    public static FavouriteLocation createMockUpdatedFavouriteLocation(UpdateFavouriteLocationRequest request) {
        FavouriteLocation location = new FavouriteLocation();
        location.setLat(request.getLat());
        location.setLon(request.getLon());
        location.setScore(request.getScore());
        return location;
    }

    public static UpdateFavouriteLocationRequest createMockUpdateRequestBadScore() {
        UpdateFavouriteLocationRequest request = new UpdateFavouriteLocationRequest();
        request.setLat(BigDecimal.valueOf(50.061947));
        request.setLon(BigDecimal.valueOf(19.936856));
        request.setScore(BigDecimal.valueOf(-5));
        return request;
    }

    public static UpdateFavouriteLocationRequest createMockUpdateRequestBadLat() {
        UpdateFavouriteLocationRequest request = new UpdateFavouriteLocationRequest();
        request.setLat(BigDecimal.valueOf(-50.061947));
        request.setLon(BigDecimal.valueOf(19.936856));
        request.setScore(BigDecimal.valueOf(5));
        return request;
    }

    public static UpdateFavouriteLocationRequest createMockUpdateRequestBadLon() {
        UpdateFavouriteLocationRequest request = new UpdateFavouriteLocationRequest();
        request.setLat(BigDecimal.valueOf(50.061947));
        request.setLon(BigDecimal.valueOf(-19.936856));
        request.setScore(BigDecimal.valueOf(5));
        return request;
    }

}
