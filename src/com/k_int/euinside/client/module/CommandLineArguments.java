package com.k_int.euinside.client.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.k_int.euinside.client.module.dataTransformation.Format;
import com.k_int.euinside.client.module.setmanager.SetManager;

public class CommandLineArguments {
	static SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private String accessionNumber = "";
	private ArrayList<String> badFilenames = new ArrayList<String>();
	private String batch = "";
	private String coreBaseURL = "http://euinside.k-int.com/ECKCore2";
	private Date dateTime = null;
	private Integer days = null;
	private Long duration = null;
	private boolean deleteAll = false;
	private String errorCode = "";
	private String field = "";
	private ArrayList<String> filenames = new ArrayList<String>();
	private String group = null;
	private String institutionURL = "";
	private Integer itemsProcessed = null;
	private String language = "";
	private Integer limit = null;
	private String moduleName = null;
	private Integer numberFailed = null;
	private Integer numberSuccessful = null;
	private Integer offset = null;
	private String outputFile = null;
	private String pid = "";
	private String profile = "";
	private String provider = SetManager.PROVIDER_DEFAULT;
	private String query = null;
	private ArrayList<String> recordsToDelete = new ArrayList<String>();
	private String recordId = "";
	private String recordType = "";
	private boolean runAll = false;
	private boolean runCommit = false;
	private boolean runList = false;
	private boolean runPreview = false;
	private boolean runQuery = false;
	private boolean runStatus = false;
	private boolean runUpdate = false;
	private boolean runValidate = false;
	private String set = SetManager.SET_DEFAULT;
	private Format sourceFormat = Format.LIDO;
	private Format targetFormat = Format.EDM;

	public CommandLineArguments(String [] args) {
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-accessionNumber":
					i++;
					setAccessionNumber(args[i]);
					break;
					
				case "-all":
					setRunAll(true);
					break;
					
				case "-badFilename":
					i++;
					badFilenames.add(args[i]);
					break;
					
				case "-batch":
					i++;
					setBatch(args[i]);
					break;
					
				case "-commit":
					setRunCommit(true);
					break;
					
				case "-coreBaseURL":
					i++;
					setCoreBaseURL(args[i]);
					break;
					
				case "-dateTime":
					i++;
					setDateTime(args[i]);
					break;
					
				case "-days":
					i++;
					setDays(args[i]);
					break;
					
				case "-deleteAll":
					setDeleteAll(true);
					break;
					
				case "-duration":
					i++;
					setDuration(args[i]);
					break;
					
				case "-errorCode":
					i++;
					setErrorCode(args[i]);
					break;
					
				case "-field":
					i++;
					setField(args[i]);
					break;
					
				case "-filename":
					i++;
					filenames.add(args[i]);
					break;
					
				case "-group":
					i++;
					setGroup(args[i]);
					break;
					
				case "-institutionURL":
					i++;
					setInstitutionURL(args[i]);
					break;
					
				case "-itemsProcessed":
					i++;
					setItemsProcessed(args[i]);
					break;
					
				case "-language":
					i++;
					setLanguage(args[i]);
					break;
					
				case "-limit":
					i++;
					setLimit(args[i]);
					break;
					
				case "-list":
					setRunList(true);
					break;
					
				case "-moduleName":
					i++;
					setModuleName(args[i]);
					break;
					
				case "-numberFailed":
					i++;
					setNumberFailed(args[i]);
					break;
					
				case "-numberSuccessful":
					i++;
					setNumberSuccessful(args[i]);
					break;
					
				case "-offset":
					i++;
					setOffset(args[i]);
					break;
					
				case "-outputFile":
					i++;
					setOutputFile(args[i]);
					break;
					
				case "-pid":
					i++;
					setPid(args[i]);
					break;
					
				case "-preview":
					setRunPreview(true);
					break;
					
				case "-profile":
					i++;
					setProfile(args[i]);
					break;
					
				case "-provider":
					i++;
					setProvider(args[i]);
					break;
					
				case "-query":
					setRunQuery(true);
					break;
					
				case "-recordToDelete":
					i++;
					recordsToDelete.add(args[i]);
					break;
					
				case "-recordId":
					i++;
					setRecordId(args[i]);
					break;

				case "-recordType":
					i++;
					setRecordType(args[i]);
					break;

				case "-set":
					i++;
					setSet(args[i]);
					break;
					
				case "-sourceFormat":
					i++;
					setSourceFormat(args[i]);
					break;
					
				case "-status":
					setRunStatus(true);
					break;
					
				case "-targetFormat":
					i++;
					setTargetFormat(args[i]);
					break;
					
				case "-update":
					setRunUpdate(true);
					break;
					
				case "-validate":
					setRunValidate(true);
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
	
	public String getBatch() {
		return(batch);
	}
	
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	public String getCoreBaseURL() {
		return(coreBaseURL);
	}
	
	public void setCoreBaseURL(String coreBaseURL) {
		this.coreBaseURL = coreBaseURL;
	}
	
	public Date getDateTime() {
		return(dateTime);
	}

	public void setDateTime(String dateTime) {
		try {
			this.dateTime = expectedDateFormat.parse(dateTime);
		} catch (ParseException e) {
			// We ignore anything we cannot parse
		}
	}

	public Integer getDays() {
		return(days);
	}

	public void setDays(String days) {
		this.days = Integer.parseInt(days);
	}

	public boolean isDeleteAll() {
		return(deleteAll);
	}
	
	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}
	
	public Long getDuration() {
		return(duration);
	}

	public Integer getDurationInteger() {
		return(duration == null ? null : duration.intValue());
	}

	public void setDuration(String duration) {
		this.duration = Long.parseLong(duration);
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
	
	public String getGroup() {
		return(group);
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getInstitutionURL() {
		return(institutionURL);
	}

	public void setInstitutionURL(String institutionURL) {
		this.institutionURL = institutionURL;
	}

	public Integer getItemsProcessed() {
		return(itemsProcessed);
	}

	public void setItemsProcessed(String itemsProcessed) {
		this.itemsProcessed = Integer.parseInt(itemsProcessed);
	}

	public String getLanguage() {
		return(language);
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getLimit() {
		return(limit);
	}

	public void setLimit(String limit) {
		this.limit = Integer.parseInt(limit);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getNumberFailed() {
		return(numberFailed);
	}

	public void setNumberFailed(String numberFailed) {
		this.numberFailed = Integer.parseInt(numberFailed);
	}

	public Integer getNumberSuccessful() {
		return(numberSuccessful);
	}

	public void setNumberSuccessful(String numberSuccessful) {
		this.numberSuccessful = Integer.parseInt(numberSuccessful);
	}

	public Integer getOffset() {
		return(offset);
	}

	public void setOffset(String offset) {
		this.offset = Integer.parseInt(offset);
	}

	public String getOutputFile() {
		return(outputFile);
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
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
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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
	
	public Format getSourceFormat() {
		return(sourceFormat);
	}
	
	public void setSourceFormat(String sourceFormat) {
		this.sourceFormat = Format.get(sourceFormat);
	}
	
	public Format getTargetFormat() {
		return(targetFormat);
	}
	
	public void setTargetFormat(String targetFormat) {
		this.targetFormat = Format.get(targetFormat);
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
	
	public boolean isRunQuery() {
		return(runQuery);
	}
	
	public void setRunQuery(boolean runQuery) {
		this.runQuery = runQuery;
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
