package com.k_int.euinside.client.module.setmanager.push;

import com.k_int.euinside.client.json.baseJSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class DataPushResult extends baseJSON {
    private int failedDeposits;
    private int successfulDeposits;
    private List<String> errorMessages;
    private String illegalArgument;
    private String serverException;
    private static Log log = LogFactory.getLog( DataPushResult.class );


    @Override
    protected Log getLogger() {
        return (log);
    }

    public int getFailedDeposits() {
        return failedDeposits;
    }

    public void setFailedDeposits( int failedDeposits ) {
        this.failedDeposits = failedDeposits;
    }

    public int getSuccessfulDeposits() {
        return successfulDeposits;
    }

    public void setSuccessfulDeposits( int successfulDeposits ) {
        this.successfulDeposits = successfulDeposits;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages( List<String> errorMessages ) {
        this.errorMessages = errorMessages;
    }

    public String getIllegalArgument() {
        return illegalArgument;
    }

    public void setIllegalArgument( String illegalArgument ) {
        this.illegalArgument = illegalArgument;
    }

    public String getServerException() {
        return serverException;
    }

    public void setServerException( String serverException ) {
        this.serverException = serverException;
    }

    public boolean hasClientException() {
        return serverException != null || illegalArgument != null;
    }

}
