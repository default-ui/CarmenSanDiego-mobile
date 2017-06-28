package com.unq.tpi.uis.carmensandiego_mobile.connection;

import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import retrofit.RestAdapter;

public class CarmenSanConnection {

    public CarmenSanDiegoService getService() {
        String SERVER_IP = "10.9.0.219"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "10.12.2.128";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":3000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService carmenSanDiegoService = restAdapter.create(CarmenSanDiegoService.class);
        return carmenSanDiegoService;
    }
}
