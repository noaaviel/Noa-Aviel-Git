#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Company.h"
#include "Airport.h"
#include "General.h"

void	initCompany(Company* pComp)
{
	printf("-----------  Init Airline Company\n");
	pComp->name = getStrExactName("Enter company name");
	pComp->flightArr = NULL;
	pComp->flightCount = 0;
	pComp->the_type = not_sorted;
	pComp->UniqueDates = convertFlightDateToStringsList(pComp);
}
int writeCompanyToFile(Company* company, char* fileName)
{
	int i;
	FILE* fp = fopen(fileName, "wb");
	if (!fp) 
	{
		printf("cant open file\n");
		return 0;
	}

	int sizeOfCompName = strlen(company->name)+1;
	if (!fwrite(&sizeOfCompName, sizeof(int), 1, fp))
		return 0;
	if (!fwrite(company->name, sizeof(char), sizeOfCompName, fp))
		return 0;
	if (!fwrite(&company->flightCount, sizeof(int), 1, fp))
		return 0;
	if (!fwrite(&company->the_type, sizeof(int), 1, fp))
		return 0;
	for (i = 0; i < company->flightCount; i++)
	{
		fwrite(&company->flightArr[i]->originCode, sizeof(char), CODE_LENGTH + 1, fp);
		fwrite(&company->flightArr[i]->destCode, sizeof(char), CODE_LENGTH + 1, fp);
		fwrite(&company->flightArr[i]->hour, sizeof(int), 1, fp);
		fwrite(&company->flightArr[i]->date, sizeof(Date), 1, fp);
		//if (fwrite(&company->flightArr[i], sizeof(Flight), 1, fp) != 1)
			//return 0;
	}

	fclose(fp);
	return 1;
}

//bsearch
void searchFlightInCompany(Company* company)
{
	Flight** retFlight = NULL;
	Flight* temp = (Flight*)malloc(sizeof(Flight));
	if (!temp) {
		free(temp);
		return;
	}
	if (company->the_type == not_sorted) {
		printf("You Have To Sort First!\n");
		return;
	}
	if (company->the_type == by_time) {
		int hour = 0;
		printf("Please Enter Hour To Search:\n");
		scanf("%d", &hour);
		temp->hour = hour;
		retFlight = (Flight**)bsearch(&temp, company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByHour);
	}
	else if (company->the_type == by_date) {
		Date date;
		getCorrectDate(&date);
		temp->date = date;
		retFlight = (Flight**)bsearch(&temp, company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByDate);
	}
	else if (company->the_type == by_iata_dest) {
		char IATA[4];
		printf("Please Enter Destination IATA To Search:\n");
		scanf("%s", &IATA);
		strcpy(temp->destCode, IATA);
		retFlight = (Flight**)bsearch(&temp, company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByIATADest);

	}
	else if (company->the_type == by_iata_source) {
		char IATA[4];
		printf("Please Enter Source IATA To Search:\n");
		scanf("%s", &IATA);
		if (!IATA) {
			return;
		}
		strcpy(temp->originCode, IATA);
		retFlight = (Flight**)bsearch(&temp, (Flight**)company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByIATASource);

	}
	if (retFlight == NULL) {
		free(temp);
		printf("Didn't Find It Lol Bye\n");
		return;
	}
	printFlight((Flight*)retFlight);
}

//qsorting
int sortArrOfFlight(Company* company)
{
	int choose = 0;
	printf("If You Want To Sort By Hour Press %d If By Date Press %d By IATA Source %d By IATA Destination %d:\n"
		, by_time, by_date, by_iata_source, by_iata_dest);
	scanf("%d", &choose);

	if (choose == by_time) {
		company->the_type = by_time;
		qsort(company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByHour);
		return 1;
	}
	else if (choose == by_date) {
		company->the_type = by_date;
		qsort(company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByDate);
		return 1;
	}
	else if (choose == by_iata_dest) {
		company->the_type = by_iata_dest;
		qsort(company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByIATADest);
		return 1;

	}
	else if (choose == by_iata_source) {
		company->the_type = by_iata_source;
		qsort(company->flightArr, company->flightCount, sizeof(Flight*), compareFlightByIATASource);
		return 1;

	}
	return 0;
}
//Initializing airline struct from a binary file 
int initCompanyFromFile(Company* company, AirportManager* airManager, char* fileName)
{
	int sizeOfCompName = 0;
	FILE* fp = fopen(fileName, "rb");
	if (!fp)
	{
		printf("cant open file\n");
		return 0;
	}
	company->flightArr = NULL;
	if (fread(&sizeOfCompName, sizeof(int), 1, fp) != 1)
		return 0;
	company->name = (char*)calloc((sizeOfCompName + 1), sizeof(char));
	if (!company->name)
		return 0;
	if (!fread(company->name, sizeof(char), sizeOfCompName, fp))
		return 0;
	if (!fread(&company->flightCount, sizeof(int), 1, fp))
		return 0;
	if (!fread(&company->the_type, sizeof(int), 1, fp))
		return 0;

	if (company->flightCount > 0) {
		company->flightArr = (Flight**)malloc(company->flightCount * sizeof(Flight*));
		if (!company->flightArr) {
			return 0;
		}
		initFlights(company, airManager, fp);
	}
	company->UniqueDates = convertFlightDateToStringsList(company);

	fclose(fp);
	return 1;
}

//add flight
int	addFlight(Company* pComp, const AirportManager* pManager)
{
	if (pManager->count < 2)
	{
		printf("There are not enoght airport to set a flight\n");
		return 0;
	}
	pComp->flightCount += 1;
	Flight** temp = (Flight**)realloc(pComp->flightArr, (pComp->flightCount) * sizeof(Flight*));
	if (!temp)
		return 0;
	pComp->flightArr = temp;
	pComp->flightArr[pComp->flightCount] = (Flight*)calloc(1, sizeof(Flight));
	if (!pComp->flightArr[pComp->flightCount])
		return 0;
	initFlight(pComp->flightArr[pComp->flightCount], pManager);
	pComp->flightCount++;
	return 1;
}
int initFlights(Company* company, AirportManager* airManager, FILE* fp)
{
	int i;
	char dest[MAX_STR_LEN], origin[MAX_STR_LEN];
	Airport* tempAirport1 = (Airport*)malloc(sizeof(Airport));
	if (!tempAirport1)
		return 0;
	Airport* tempAirport2 = (Airport*)malloc(sizeof(Airport));
	if (!tempAirport2)
		return 0;
	for (i = 0; i < company->flightCount; i++)
	{
		company->flightArr[i] = (Flight*)malloc(sizeof(Flight));
		if (!company->flightArr[i]) {
			return 0;
		}
		fread(dest, sizeof(char), CODE_LENGTH + 1, fp);
		fread(origin, sizeof(char), CODE_LENGTH + 1, fp);
		tempAirport1 = findAirportByCode(airManager, dest);
		if (!tempAirport1)
			return 0;
		tempAirport2 = findAirportByCode(airManager, origin);
		if (!tempAirport2)
			return 0;
		strcpy(company->flightArr[i]->originCode, origin);
		strcpy(company->flightArr[i]->destCode, dest);
		fread(&company->flightArr[i]->hour, sizeof(int), 1, fp);
		fread(&company->flightArr[i]->date, sizeof(Date), 1, fp);
	}
	return 1;
}
//extract date to string and make it to lists
LLNode* convertFlightDateToStringsList(Company* company) {

	LLNode* returnList = NULL; // = (LLNode*)malloc(sizeof(LLNode) * company->flightCount);
	int firstTime = 0;
	char day[3], month[3], year[5];
	for (int i = 0; i < company->flightCount; i++)
	{
		if (!returnList) {
			returnList = (LLNode*)malloc(sizeof(LLNode));
			firstTime = 1;
		}
		if (!returnList)
			return NULL;
		char* date = (char*)calloc(sizeof(char), 11);
		if (!date)
			return NULL;
		_itoa(company->flightArr[i]->date.day, day, 10);
		if (company->flightArr[i]->date.day < 10)
			strcat(date, "0");
		strcat(date, day);
		strcat(date, "/");
		_itoa(company->flightArr[i]->date.month, month, 10);
		if (company->flightArr[i]->date.month < 10)
			strcat(date, "0");
		strcat(date, month);
		strcat(date, "/");
		_itoa(company->flightArr[i]->date.year, year, 10);
		strcat(date, year);

		if (firstTime) {
			returnList->data = date;
			returnList->next = NULL;
			firstTime = 0;
		}
		else
			append(&returnList, date);

		LLNode* p = NULL;
		for (p = returnList; p->next && strcmp(date, p->data) != 0; p = p->next);
		if (strcmp(date, p->data) == 0)
			free(date);
		else {
			LLNode* newDate = (LLNode*)malloc(sizeof(LLNode));
			if (!newDate) {
				return NULL;
			}

			newDate->data = date;
			newDate->next = NULL;
			p->next = newDate;
		}
	}

	return returnList;

}

//print company
void printCompany(const Company* pComp)
{
	printf("Company %s:\n", pComp->name);
	printf("Has %d flights\n", pComp->flightCount);
	printFlightArr(pComp->flightArr, pComp->flightCount);
}


//print flightsCount
void	printFlightsCount(const Company* pComp)
{
	char codeOrigin[CODE_LENGTH + 1];
	char codeDestination[CODE_LENGTH + 1];

	if (pComp->flightCount == 0)
	{
		printf("No flight to search\n");
		return;
	}

	printf("Origin Airport\n");
	getAirportCode(codeOrigin);
	printf("Destination Airport\n");
	getAirportCode(codeDestination);

	int count = countFlightsInRoute(pComp->flightArr, pComp->flightCount, codeOrigin, codeDestination);
	if (count != 0)
		printf("There are %d flights ", count);
	else
		printf("There are No flights ");

	printf("from %s to %s\n", codeOrigin, codeDestination);
}

// 4 COMPARATORS FOR BSEARCH AND QSORT

int compareFlightByHour(const void* f1, const void* f2) {

	Flight** pd1 = ((Flight**)f1);
	int h1 = (*pd1)->hour;
	Flight** pd2 = ((Flight**)f2);
	int h2 = (*pd2)->hour;
	int retVal = h1 - h2;
	return retVal;

}

int compareFlightByDate(const void* f1, const void* f2) {

	Flight** pd1 = ((Flight**)f1);
	Date d1 = (*pd1)->date;
	Flight** pd2 = ((Flight**)f2);
	Date d2 = (*pd2)->date;

	if (d1.year != d2.year)
		return d1.year - d2.year;
	if (d1.month != d2.month)
		return d1.month - d2.month;
	if (d1.day != d2.day)
		return d1.day - d2.day;
	return 0;
}

int compareFlightByIATASource(const void* f1, const void* f2) {

	Flight** pd1 = ((Flight**)f1);
	char* s1 = (*pd1)->originCode;

	Flight** pd2 = ((Flight**)f2);
	char* s2 = (*pd2)->originCode;

	return strcmp(s1, s2);
}

int compareFlightByIATADest(const void* f1, const void* f2) {
	Flight** pd1 = ((Flight**)f1);
	char* s1 = (*pd1)->destCode;
	Flight** pd2 = ((Flight**)f2);
	char* s2 = (*pd2)->destCode;

	return strcmp(s1, s2);
}
//PRINT ARRAY USING GENERAL FUNC
void printFlightArr(Flight** pFlight, int size)
{

	generalArrayFunction(pFlight, size, sizeof(*pFlight), printFlight);
}
//FREE USING GENERAL FUNC
void freeFlightArr(Flight** arr, int size)
{

	generalArrayFunction(arr, size, sizeof(*arr), freeFlight);
}

//FREE COMPANY
void freeCompany(Company* pComp)
{
	freeFlightArr(pComp->flightArr, pComp->flightCount);
	free(pComp->flightArr);
	free(pComp->name);
}
