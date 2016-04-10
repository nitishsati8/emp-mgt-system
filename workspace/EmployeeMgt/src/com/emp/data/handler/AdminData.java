package com.emp.data.handler;

public class AdminData {
	private static String adminId;
	private static String adminPassword;
	private static String adminStatus;
	public AdminData()
	{
		//Default Constructor
	}
	public AdminData(String adId,String adPass,String adStatus)
	{
		adminId=adId;
		adminPassword=adPass;
		setAdminStatus(adStatus);
	}
	public static String getAdminId() {
		return adminId;
	}
	public static void setAdminId(String adminId) {
		AdminData.adminId = adminId;
	}
	public static String getAdminPassword() {
		return adminPassword;
	}
	public static void setAdminPassword(String adminPassword) {
		AdminData.adminPassword = adminPassword;
	}
	public static String getAdminStatus() {
		return adminStatus;
	}
	public static void setAdminStatus(String adminStatus) {
		AdminData.adminStatus = adminStatus;
	}
	
}
