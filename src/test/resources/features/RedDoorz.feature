Feature: Redoorz Hotel Booking Feature

  @MobileTestrrrr
  Scenario Outline: 18881_Login with valid credentials 
	Given open the RedDoorz app on 
	When tap on action refrence run 
	And tap on login btn 
	And tap on google login btn 
	And select email 
	And tap on search 
	And select area 
	And select all localities
	And select rest from recommended 
	And tap on book now 
	And tap on pay now 
	And select payment method and confirm payment 
	Examples: 
			| DeviceDetails |	
			| RZ8MC0R95MJ 10| 
			
			
#@MobileTestrr
#	Scenario Outline: 18882_login with invalid credentials 
#		Given open the RedDoorz app on
#		When tap on action refrence run 
#		And tap on login btn 
#		And enter invalid credentials and tap on login 
#		Examples: 
#			| DeviceDetails |	
#			| RZ8MC0R95MJ 10| 