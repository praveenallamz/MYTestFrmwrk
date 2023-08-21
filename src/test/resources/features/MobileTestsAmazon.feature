Feature: Amazon Search and Add to Cart 

@MobileTest
Scenario Outline: 18881_Searching a product and add to cart for Guest User 
	Given open the Amazon app on "<DeviceDetails>" 
	And click on skip sign in 
	When select for an item by category 
	And add the item to cart 
	Then verify item is added to cart 
	
	Examples: 
		| DeviceDetails |	
	    | Google Pixel 3_9.0| 
	    #| Samsung Galaxy S10e_9.0|
		 
		 
		
		
		#BELOW ARE USED FOR local devices
		#| emulator-5554_7.1.1 | 
		#| emulator-5556_8.1   | 
		#| emulator-5554_7.1.1| 
		 
		#| Google Pixel 3_9.0      | 
		#| Samsung Galaxy S10e_9.0 | 
		
		
