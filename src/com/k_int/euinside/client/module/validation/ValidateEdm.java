package com.k_int.euinside.client.module.validation;

import com.k_int.euinside.client.module.Module;

/**
 * Created by Jacob on 16/08/2017.
 */
public class ValidateEdm extends ValidateBase {
//    private static final String DEFAULT_EDM_PROFILE_FILENAME = "EdmValidationProfile.xml";
    private static final ValidateEdm workerValidate = new ValidateEdm();

    public ValidateEdm(){}

    //Validating against Metis. No need for a validation profile.
    @Override
    protected String getDefaultLidoProfile()
    {
//        //ToDo: Check this with Chas. Find out EDM validation schema
//        //return "LidoValidationProfile_Monguz.xml";
//        return "EdmValidationProfile.xml";
        return null;
    }

    protected Module getModule()
    {
        return Module.VALIDATION;
    }


    public static ValidationResult sendBytes(String provider, byte[] xmlRecord) {
        return workerValidate.send(provider, xmlRecord);
    }

    public static ValidationResult sendFiles(String provider, String filename) {
        return workerValidate.send(provider, filename);
    }
}

