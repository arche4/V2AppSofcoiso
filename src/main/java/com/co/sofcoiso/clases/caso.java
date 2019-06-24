package com.co.sofcoiso.clases;

import java.sql.Time;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
public class caso {

    public String obtenerFechaActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturar_fecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.DATE)));
    }

    private String agregarCerosIzquierda(final String diames) {
        final StringBuffer retorno = new StringBuffer();
        if (Integer.parseInt(diames) < 10) {
            final String add = "0" + diames;
            retorno.append(add);
        } else {
            retorno.append(diames);
        }
        return retorno.toString();
    }

    public String obtenerHoraActual() {
        final Time sqlTime = new Time(new java.util.Date().getTime());
        return sqlTime.toString();
    }

}
