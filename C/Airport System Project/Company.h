#ifndef __COMP__
#define __COMP__

#include "Flight.h"
#include "AirportManager.h"
#include "MyLinkedList.h"
typedef enum {not_sorted, by_time, by_date,by_iata_source,by_iata_dest}sort_type;

typedef struct
{
	char*		name;
	int			flightCount;
	Flight**	flightArr;
	LLNode*     UniqueDates;
	sort_type the_type;
}Company;


int compareFlightByHour(const void* f1, const void* f2);
int compareFlightByDate(const void* f1, const void* f2);
int compareFlightByIATASource(const void* f1, const void* f2);
int compareFlightByIATADest(const void* f1, const void* f2);
void initCompany(Company* pComp);
int writeCompanyToFile(Company* company, char* fileName);
int initCompanyFromFile(Company* company, AirportManager* airManager, char* fileName);
int initFlights(Company* company, AirportManager* airManager, FILE* fp);
void searchFlightInCompany(Company* company);
int     sortArrOfFlight(Company* company);
int		addFlight(Company* pComp, const AirportManager* pManager);
void	printCompany(const Company* pComp);
void	printFlightsCount(const Company* pComp);
void	printFlightArr(Flight** pFlight, int size);
void	freeFlightArr(Flight** arr, int size);
void	freeCompany(Company* pComp);
LLNode* convertFlightDateToStringsList(Company* company);

#endif

