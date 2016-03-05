Feature: Map Csv file to Json and save output as a file
	This feature describes a basic scenario where a CSV file is picked up from a folder on a file system, mapped
	to a POJO and mapped from POJO to json format and finally saved to an output folder.
	The main purpose is to show how cucumber-jvm can work with Camel test framework.
	
Scenario: CSV to JSON
	Given an input file with CSV data
	When client puts the file in the input directory
	Then the integration should get triggered and pick up the file
	Then the integration should map the CSV format to a POJO
	Then the integration should map the POJO to JSON format
	Then the integration should save the JSON string to an output file