#include <stdio.h>
#include <stdlib.h>
#include "Company.h"
#include "AirportManager.h"
#include "General.h"
#define BINARY_FILE "company.bin"
#define TEXT_FILE "airport_authority.txt"

typedef enum
{
	eAddFlight, eAddAirport, ePrintCompany, ePrintAirports,
	ePrintFlightOrigDest,eSortFlights,eSearchFlight, eNofOptions
} eMenuOptions;

const char* str[eNofOptions] = { "Add Flight", "Add Airport",
								"PrintCompany", "Print all Airports",
								"Print flights between origin-destination","Sort Flights","Search Flight "};

#define EXIT			-1
int menu();

int main()
{
	AirportManager	manager;
	Company			company;

	//Initialize from a given files	--> HW3
	if (initAirportFromFile(&manager, TEXT_FILE) != 1)
	{
		initManager(&manager);
	}
	if (initCompanyFromFile(&company, &manager, BINARY_FILE) != 1) {
		initCompany(&company);
	}
	
	int option;
	int stop = 0;

	do
	{
		option = menu();
		switch (option)
		{
		case eAddFlight:
			if (!addFlight(&company, &manager))
				printf("Error adding flight\n");
			break;
		case eAddAirport:
			if (!addAirport(&manager))
				printf("Error adding airport\n");
			break;
		case ePrintCompany:
			printCompany(&company);
			break;
		case ePrintAirports:
			printAirports(&manager);
			break;
		case ePrintFlightOrigDest:
			printFlightsCount(&company);
			break;
		case eSortFlights: 
			sortArrOfFlight(&company);
			break;
		case eSearchFlight:
			searchFlightInCompany(&company);
			break;
		case EXIT:
			printf("Bye bye\n");
			stop = 1;
			break;
		default:
			printf("Wrong option\n");
			break;
		}
	} while (!stop);
	writeAirportManagerToFile(&manager, TEXT_FILE);
	writeCompanyToFile(&company, BINARY_FILE);
	freeManager(&manager);
	freeCompany(&company);
	return 1;
}

int menu()
{
	int option;
	printf("\n\n");
	printf("Please choose one of the following options\n");
	for (int i = 0; i < eNofOptions; i++)
		printf("%d - %s\n", i, str[i]);
	printf("%d - Quit\n", EXIT);
	scanf("%d", &option);
	//clean buffer
	char tav;
	scanf("%c", &tav);
	return option;
}
