#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "AirportManager.h"

int	initManager(AirportManager* pManager)
{
	printf("-----------  Init airport Manager\n");
	pManager->count = 0;
	pManager->arr = NULL;

	int count = 0;
	do {
		printf("How many airport?\t");
		scanf("%d", &count);
	} while (count < 0);
	//clean buffer
	char tav;
	scanf("%c", &tav);
	if (count == 0)
		return 1;

	pManager->arr = initLinkedList(NULL);
	Airport* ptrAirport = (Airport*)malloc(count * sizeof(Airport));
	if (!pManager->arr)
		return 0;

	for (int i = 0; i < count; i++)
	{
		setAirport(ptrAirport, pManager);
		pManager->arr->data = (Airport*)ptrAirport;
		pManager->arr->next = initLinkedList(NULL);
		pManager->arr = pManager->arr->next;
		pManager->count++;
	}

	return 1;
}

int	addAirport(AirportManager* pManager)
{
	Airport* ptrAirport = (Airport*)malloc(sizeof(Airport));
	if (!ptrAirport)
		return 0;
	setAirport(ptrAirport, pManager);
	append(&pManager->arr, (Airport*)ptrAirport);
	pManager->count++;
	return 1;
}

int compareByCode(const void* a, const void* b)
{
	Airport* air1 = (Airport*)((LLNode*)a)->data;
	Airport* air2 = (Airport*)((LLNode*)b)->data;

	return strcmp(air1->code, air2->code);
}

/* Bubble sort the given linked list */
void sortAirportList(LLNode* head)
{
	int swapped = 0, i = 0;
	LLNode* ptr1, * lptr = NULL;
	Airport* air1, * air2;
	/* Checking for empty list */
	if (head == NULL)
		return;
	do
	{
		swapped = 0;
		ptr1 = head;

		while (ptr1->next != lptr)
		{
			air1 = (Airport*)ptr1->data;
			air2 = (Airport*)ptr1->next->data;

			// air1->code needs to br after air2->code
			if (strcmp(air1->code, air2->code) > 0)
			{
				swap(ptr1, ptr1->next);
				swapped = 1;
			}
			ptr1 = ptr1->next;
		}
		lptr = ptr1;
	} while (swapped);
}

/* function to swap data of two nodes a and b*/
void swap(LLNode* air1, LLNode* air2)
{
	void* temp = air1->data;
	air1->data = air2->data;
	air2->data =temp;
}


int initAirportFromFile(AirportManager* pManager, char* fileName)
{
	int i;
	FILE* fp = fopen(fileName, "r");
	if (!fp)
		return 0;
	if (fscanf(fp, "%d", &pManager->count) != 1)
		return 0;
	pManager->arr = initLinkedList(NULL);

	for (i = 0; i < pManager->count; i++)
	{
		Airport* ptrAirport = (Airport*)malloc(sizeof(Airport));
		if (!ptrAirport)
			return 0;
		ptrAirport->name = (char*)malloc(sizeof(char) * MAX_STR_LEN);
		ptrAirport->country = (char*)malloc(sizeof(char) * MAX_STR_LEN);
		fscanf(fp, " %99[^\n]", ptrAirport->name);//Regular expression syntax
		fscanf(fp, "%s", ptrAirport->country);
		fscanf(fp, "%s", ptrAirport->code);
		if (i == 0) // first node init to head 
		{
			pManager->arr = initLinkedList((Airport*)ptrAirport);
		}
		else
			append(&pManager->arr->next, (Airport*)ptrAirport);
	}
	sortAirportList(pManager->arr);
	fclose(fp);
	return 1;
}

int writeAirportManagerToFile(AirportManager* pManager, char* fileName)
{
	int i;
	FILE* fp = fopen(fileName, "w");
	if (!fp)
		return 0;
	fprintf(fp, "%d\n", pManager->count);
	Airport* ptrAirport = (Airport*)malloc(sizeof(Airport));
	if (!ptrAirport)
		return 0;
	for (i = 0; i < pManager->count; i++)
	{
		ptrAirport = (Airport*)pManager->arr->data;
		fprintf(fp, "%s\n", ptrAirport->name);//Regular expression syntax
		fprintf(fp, "%s\n", ptrAirport->country);
		fprintf(fp, "%s\n", ptrAirport->code);
		pManager->arr = pManager->arr->next;
		//fprintf(fp, "\n");
	}
	fclose(fp);
	return 1;
}
void  setAirport(Airport* pPort, AirportManager* pManager)
{
	while (1)
	{
		getAirportCode(pPort->code);
		if (checkUniqeCode(pPort->code, pManager))
			break;

		printf("This code already in use - enter a different code\n");
	}

	initAirportNoCode(pPort);
}

Airport* findAirportByCode(AirportManager* pManager, const char* code)
{
	LLNode* temp = pManager->arr;
	while(temp)
	{
		if (isAirportCode(temp->data, code))
			return temp->data;
		temp = temp->next;
	}
	return NULL;
}

int checkUniqeCode(const char* code, AirportManager* pManager)
{
	Airport* port = (Airport*)findAirportByCode(pManager, code);

	if (port != NULL)
		return 0;

	return 1;
}

void printAirports(AirportManager* pManager)
{
	printf("there are %d airports\n", pManager->count);
	printList(pManager->arr, printAirport);


}

void	freeManager(AirportManager* pManager)
{
	freeList(pManager->arr);
}
