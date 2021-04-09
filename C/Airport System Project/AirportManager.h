#ifndef __AIR_MANAGER__
#define __AIR_MANAGER__

#include "Airport.h"

typedef struct
{
	LLNode*	arr;
	int		count;
}AirportManager;

int		initManager(AirportManager* pManager);
int		addAirport(AirportManager* pManager);
int     initAirportFromFile(AirportManager* airManager, char* fileName);
void    setAirport(Airport* pPort, AirportManager* pManager);
Airport* findAirportByCode(AirportManager* pManager, const char* code);
int     writeAirportManagerToFile(AirportManager* pManager, char* fileName); 
int		checkUniqeCode(const char* code,AirportManager* pManager);
void    swap(LLNode *air1, LLNode *air2);
void    sortAirportList(LLNode *head);
void	printAirports(AirportManager* pManager);
void	freeManager(AirportManager* pManager);
int     compareByCode(const void* code1, const void* code2);
#endif