package com.nttdata.accountmicroservice.util;

public class Constantes {
    private Constantes(){}

    public static final String PERSON_TYPE_PERSONAL = "01";
    public static final String PERSON_TYPE_BUSINESS = "02";
    public static final String PERSON_VIP_TYPE_PERSONAL = "03";
    public static final String PERSON_MYPE_TYPE_BUSINESS = "04";


    public static final String CODE_SUCCESS = "1";
    public static final String CODE_ERROR = "0";

    public static final String SAVE_SUCCESS = "Registro exitoso";
    public static final String SAVE_VALIDATION_COUNT_ACCOUNT = "la cuenta del cliente no puede tener más de una cuenta bancaria\"";
    public static final String SAVE_VALIDATION_CURRENT_ACCOUNT = "sólo puede tener cuentas corrientes";

    public static final String DEPOSITO = "D";
    public static final String RETIRO = "R";
    public static final String PAGO = "P";
    public static final String CONSUMO = "C";

    public static final String CUENTAS_BANCARIAS = "PASIVOS - CUENTAS BANCARIAS";
    public static final String CREDITOS = "ACTIVOS - CRÉDITOS";
    public static final String CURRENT_ACCOUNT = "currentAccount";

    public static final String TARJETA_CREDITO_PERSONAL = "tarjeta credito personal";
    public static final String TARJETA_CREDITO_EMPRESARIAL = "tarjeta credito empresarial";

    public static final Integer MAXIMO_TRANSACCIONES = 20;
    public static final Double COMISION = 10.0;

}
