package com.opendevj.soap.demo.util;

public class Regexp {

	private Regexp() {}
	
    /**
     * Regular expresion solo digitos
     */
    public static final String DIGITS = "^[\\d]*$";

    public static final String LETTERS ="[a-zA-Z]*$";

    public static final String ALPHANUMERIC = "[a-zA-Z0-9]*$";
    
    /**
     * Regular expresion para validar correo electronico
     */
    public static final String EMAIL = "^$|^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$";
    
    /**
     * Regular expresion para validar loginID del sistema
     * Solo se consideran caracteres alphanumericos sin espacios en blanco.
     * Se ignoran ñ y tildes diacriticas.
     */
    public static final String USERNAME = "[a-zA-Z0-9\\_]*$";

    /**
     * Regular expresion para validar loginID de cuentas de servicio externas
     * Solo se consideran caracteres alphanumericos sin espacios en blanco.
     * Se ignoran ñ y tildes diacriticas.
     */
    public static final String USERNAME_EXT = ALPHANUMERIC;

    /**
     * Regular expresion para validar loginID del sistema
     * Solo se consideran caracteres alphanumericos sin espacios en blanco.
     * Se ignoran ñ y tildes diacriticas.
     */
    public static final String HOSTNAME = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";

    /**
     *  Validacion para nombres comerciales de entidades
     *  Se consideran caracteres alfanúmerica , acentos diacriticas y ciertos caracteres especiales
     *  [ @,/ ,# , -, & ]
     */
    public static final String COMMERCIAL_NAME = "^[ A-Za-z0-9\u00f1\u00d1\u00c1\u00c9\u00cd\u00d3\u00da\u00d6\u00dc\u00e1\u00e9\u00ed\u00f3\u00fa\u00f6\u00fc()!@./#&-]*$";

    /**
     * Regular expresion solo para nombres de personas
     * Incluye tildes diacriticas
     */
    public static final String PERSON_NAME = "^[ a-z\\u00d1\\u00f1\\u00e1\\u00e9\\u00ed\\u00f3\\u00fa\\u00c1\\u00c9\\u00cd\\u00d3\\u00da\\u00d6\\u00dc\\u00f6\\u00fc]*$";

}
