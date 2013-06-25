package com.k_int.euinside.client.module;

import java.util.ArrayList;

import com.k_int.euinside.client.module.setmanager.SetManager;

public class CommandLineArguments {
	String accessionNumber = "";
	ArrayList<String> badFilenames = new ArrayList<String>();
	String coreBaseURL = "http://euinside.k-int.com/ECKCore2";
	boolean deleteAll = false;
	String errorCode = "";
	String field = "";
	ArrayList<String> filenames = new ArrayList<String>();
	String institutionURL = "";
	String language = "";
	String pid = "";
	String profile = "";
	String provider = SetManager.PROVIDER_DEFAULT;
	ArrayList<String> recordsToDelete = new ArrayList<String>();
	String recordId = "";
	String recordType = "";
	boolean runAll = false;
	boolean runCommit = false;
	boolean runList = false;
	boolean runPreview = false;
	boolean runStatus = false;
	boolean runUpdate = false;
	boolean runValidate = false;
	String set = SetManager.SET_DEFAULT;

	public CommandLineArguments(String [] args) {
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-accessionNumber":
					i++;
					accessionNumber = args[i];
					break;
					
				case "-all":
					runAll = true;
					break;
					
				case "-badFilename":
					i++;
					badFilenames.add(args[i]);
					break;
					
				case "-commit":
					runCommit = true;
					break;
					
				case "-coreBaseURL":
					i++;
					coreBaseURL = args[i];
					break;
					
				case "-deleteAll":
					deleteAll = true;
					break;
					
				case "-errorCode":
					i++;
					errorCode = args[i];
					break;
					
				case "-field":
					i++;
					field = args[i];
					break;
					
				case "-filename":
					i++;
					filenames.add(args[i]);
					break;
					
				case "-institutionURL":
					i++;
					institutionURL = args[i];
					break;
					
				case "-language":
					i++;
					language = args[i];
					break;
					
				case "-list":
					runList = true;
					break;
					
				case "-pid":
					i++;
					pid = args[i];
					break;
					
				case "-preview":
					runPreview = true;
					break;
					
				case "-profile":
					i++;
					profile = args[i];
					break;
					
				case "-provider":
					i++;
					provider = args[i];
					break;
					
				case "-recordToDelete":
					i++;
					recordsToDelete.add(args[i]);
					break;
					
				case "-recordId":
					i++;
					recordId = args[i];
					break;

				case "-recordType":
					i++;
					recordType = args[i];
					break;

				case "-set":
					i++;
					set = args[i];
					break;
					
				case "-status":
					runStatus = true;
					break;
					
				case "-update":
					runUpdate = true;
					break;
					
				case "-validate":
					runValidate = true;
					break;
			}
		}
		BaseModule.setCoreBaseURL(coreBaseURL);
		System.out.println("Using \"" + coreBaseURL + "\" as the base url");
	}
	
	public String getAccessionNumber() {
		return(accessionNumber);
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public ArrayList<String> getBadFilenames() {
		return(badFilenames);
	}
	
	public void setBadFilenames(ArrayList<String> badFilenames) {
		this.badFilenames = badFilenames;
	}
	
	public String getCoreBaseURL() {
		return(coreBaseURL);
	}
	
	public void setCoreBaseURL(String coreBaseURL) {
		this.coreBaseURL = coreBaseURL;
	}
	
	public boolean isDeleteAll() {
		return(deleteAll);
	}
	
	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}
	
	public String getErrorCode() {
		return(errorCode);
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getField() {
		return(field);
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public ArrayList<String> getFilenames() {
		return(filenames);
	}
	
	public void setFilenames(ArrayList<String> filenames) {
		this.filenames = filenames;
	}
	
	public String getInstitutionURL() {
		return(institutionURL);
	}

	public void setInstitutionURL(String institutionURL) {
		this.institutionURL = institutionURL;
	}

	public String getLanguage() {
		return(language);
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPid() {
		return(pid);
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProfile() {
		return(profile);
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProvider() {
		return(provider);
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public ArrayList<String> getRecordsToDelete() {
		return(recordsToDelete);
	}
	
	public void setRecordsToDelete(ArrayList<String> recordsToDelete) {
		this.recordsToDelete = recordsToDelete;
	}
	
	public String getRecordId() {
		return(recordId);
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordType() {
		return(recordType);
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public boolean isRunCommit() {
		return(runCommit);
	}
	
	public void setRunCommit(boolean runCommit) {
		this.runCommit = runCommit;
	}
	
	public String getSet() {
		return(set);
	}
	
	public void setSet(String set) {
		this.set = set;
	}
	
	public boolean isRunAll() {
		return(runAll);
	}
	
	public void setRunAll(boolean runAll) {
		this.runAll = runAll;
	}
	
	public boolean isRunList() {
		return(runList);
	}
	
	public void setRunList(boolean runList) {
		this.runList = runList;
	}
	
	public boolean isRunPreview() {
		return(runPreview);
	}
	
	public void setRunPreview(boolean runPreview) {
		this.runPreview = runPreview;
	}
	
	public boolean isRunStatus() {
		return(runStatus);
	}
	
	public void setRunStatus(boolean runStatus) {
		this.runStatus = runStatus;
	}
	
	public boolean isRunUpdate() {
		return(runUpdate);
	}
	
	public void setRunUpdate(boolean runUpdate) {
		this.runUpdate = runUpdate;
	}
	
	public boolean isRunValidate() {
		return(runValidate);
	}
	
	public void setRunValidate(boolean runValidate) {
		this.runValidate = runValidate;
	}
}
