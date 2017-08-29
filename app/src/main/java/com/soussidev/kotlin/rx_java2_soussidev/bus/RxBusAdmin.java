package com.soussidev.kotlin.rx_java2_soussidev.bus;

import com.soussidev.kotlin.rx_java2_lib.RxBus_java;
import com.soussidev.kotlin.rx_java2_soussidev.model.Admin;

import io.reactivex.Observable;

/**
 * Created by Soussi on 28/08/2017.
 */

public class RxBusAdmin extends RxBus_java {

    private static RxBusAdmin instance = new RxBusAdmin();

    public static RxBusAdmin getInstance() {
        return instance;
    }

    public Observable<Admin> onAdminChanged(){
        return super.onEvent(Admin.class);
    }

    public void userChanged(Admin admin){
        super.post(admin);
    }

    public void displayAdmin(boolean display){
        super.post(new DisplayAdminCustomEvent(display));
    }

    public Observable<Boolean> onDisplayAdmin(){
        return super.onEvent(DisplayAdminCustomEvent.class)
                .map(DisplayAdminCustomEvent::displayAdmin);
    }

    protected class DisplayAdminCustomEvent {

        public boolean displayAdmin;

        public DisplayAdminCustomEvent(boolean displayAdmin) {
            this.displayAdmin = displayAdmin;
        }

        public boolean displayAdmin() {
            return displayAdmin;
        }
    }

}
