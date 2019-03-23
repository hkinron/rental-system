package com.hkinron.rentalsystem.backend.rentalsystembackend.Util;

public enum Fee {

    WATERFEE(1.3), ELECTRICFEE(4), INTERNET(20), AIRCONDITIONER(10);

    private double fee;

    Fee(double fee){
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

}
