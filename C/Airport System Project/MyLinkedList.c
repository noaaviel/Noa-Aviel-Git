#include "MyLinkedList.h"
#include <stdio.h>
#include <stdlib.h>

//init list
LLNode* initLinkedList(void* data)
{
	LLNode* head = initHead(data);
	if (!head) {
		fprintf(stderr, "Failed to init a linked list\n");
		return NULL;
	}

	return head;
}

//init head of list
LLNode* initHead(void* data) {
	LLNode* newNode = (LLNode*)malloc(sizeof(LLNode));
	if (!newNode) {
		return NULL;
	}
	newNode->data = data;
	newNode->next = NULL;
	return newNode;
}

//insert after 
void insertAfter(LLNode* insertAfter, void* newData)
{
	if (insertAfter == NULL) {
		printf("the given previous node cannot be NULL");
		return;
	}

	LLNode* newNode = (struct LLNode*)malloc(sizeof(struct LLNode));
	if (!newNode) {
		return;
	}
	//insert data to new node
	newNode->data = newData;

	//Make next of new node as next of prev_node
	newNode->next = insertAfter->next;

	//move the next of prev_node as new_node
	insertAfter->next = newNode;

}


// Create a node with the data equal value and append it to the end of the list
void append(LLNode** head, void* data) {

	/* 1. allocate node */
	LLNode* new_node = (LLNode*)malloc(sizeof(LLNode));
	if (!new_node) {
		return;
	}
	LLNode* last = *head;  /* used in step 5*/

	/* 2. put in the data  */
	new_node->data = data;

	/* 3. This new node is going to be the last node, so make next of it as NULL*/
	new_node->next = NULL;

	/* 4. If the Linked List is empty, then make the new node as head */
	if (*head == NULL) {
		*head = new_node;
		return;
	}

	/* 5. Else traverse till the last node */
	while (last->next != NULL) {
		last = last->next;
	}


	/* 6. Change the next of last node */
	last->next = new_node;
	return;
}

//delete node in list
void deleteNode(LLNode** headRef, LLNode* toDelete)
{
	// Store head node 
	LLNode* temp = *headRef, * prev = NULL;

	// If head node itself holds the key to be deleted 
	if (temp != NULL && temp->data == toDelete->data)
	{
		*headRef = temp->next;   // Changed head 
		free(temp);               // free old head 
		return;
	}

	// Search for the key to be deleted, keep track of the 
	// previous node as we need to change 'prev->next' 
	while (temp != NULL && temp->data != toDelete->data)
	{
		prev = temp;
		temp = temp->next;
	}

	// If key was not present in linked list 
	if (temp == NULL) return;

	// Unlink the node from linked list 
	prev->next = temp->next;

	free(temp);  // Free memory 
}
//print list
void printList(LLNode* head, void print(const void*)) {
	LLNode* current = head;
	while (current != NULL) {
		print(current->data);
		current = current->next;
	}
}
//free list
void freeList(LLNode* head)
{
	LLNode* current = head, * next;
	while (current) {
		next = current->next;
		free(current);
		current = next;
	}
	head = NULL;
}

