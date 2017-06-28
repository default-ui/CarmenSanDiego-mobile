package com.unq.tpi.uis.carmensandiego_mobile.connection;

import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import retrofit.RestAdapter;

public class CarmenSanConnection {

    public CarmenSanDiegoService getService() {
        String SERVER_IP = "192.168.0.5"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.1.100";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":3000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        CarmenSanDiegoService carmenSanDiegoService = restAdapter.create(CarmenSanDiegoService.class);
        return carmenSanDiegoService;
    }
}
