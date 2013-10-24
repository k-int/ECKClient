package com.k_int.euinside.client.module.push;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.setmanager.SetManager;
import org.apache.http.entity.ContentType;

/**
 *  This class calls the DataPush module of SetManager
 */
public class DataPush {


        public static HttpResult push(String path){
                return (ClientHTTP.send(path, ContentType.TEXT_HTML));
        }

}
