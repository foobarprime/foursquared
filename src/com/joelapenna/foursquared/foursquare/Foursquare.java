/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquared.foursquare;

import com.joelapenna.foursquared.Foursquared;
import com.joelapenna.foursquared.foursquare.error.FoursquareError;
import com.joelapenna.foursquared.foursquare.error.FoursquareParseException;
import com.joelapenna.foursquared.foursquare.types.Auth;
import com.joelapenna.foursquared.foursquare.types.Group;
import com.joelapenna.foursquared.foursquare.types.IncomingCheckin;
import com.joelapenna.foursquared.foursquare.types.Venue;

import android.util.Log;

import java.io.IOException;

/**
 * @author Joe LaPenna (joe@joelapenna.com)
 */
public class Foursquare {
    private static final String TAG = "Foursquare";
    public static final boolean DEBUG = Foursquared.DEBUG;

    private String mPhone;
    private String mPassword;
    private FoursquareHttpApi mFoursquare;

    public Foursquare(String phone, String password) {
        mFoursquare = new FoursquareHttpApi(FoursquareHttpApi.createHttpClient());
        setCredentials(phone, password);
    }

    public void setCredentials(String phone, String password) {
        mPhone = phone;
        mPassword = password;
        mFoursquare.setCredentials(phone, password);
    }

    public boolean login() throws FoursquareError, FoursquareParseException, IOException {
        if (DEBUG) Log.d(TAG, "login()");
        Auth auth = mFoursquare.login(mPhone, mPassword);
        return (auth != null && auth.status());

    }

    public String breakdown(String userId, String checkinId) throws FoursquareError,
            FoursquareParseException, IOException {
        return mFoursquare.breakdown(userId, checkinId);
    }

    public IncomingCheckin checkin(String venue, boolean silent, boolean twitter, String lat,
            String lng) throws FoursquareError, FoursquareParseException, IOException {
        return mFoursquare.checkin(mPhone, venue, silent, twitter, lat, lng, null);
    }

    public Group venues(String lat, String lng, int radius, int length) throws FoursquareError,
            FoursquareParseException, IOException {
        return mFoursquare.venues(lat, lng, radius, length);
    }

    public Group checkins(String cityId) throws FoursquareError, FoursquareParseException,
            IOException {
        return mFoursquare.checkins(cityId);
    }

    public Venue venue(String id) throws FoursquareError, FoursquareParseException, IOException {
        return mFoursquare.venue(id);
    }
}
