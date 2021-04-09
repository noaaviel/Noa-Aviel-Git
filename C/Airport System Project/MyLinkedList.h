#ifndef _MYLINKEDLIST_H
#define _MYLINKEDLIST_H

typedef struct LLNode {

	void* data;
	struct LLNode* next;

}LLNode;


LLNode* initLinkedList(void* data);
LLNode* initHead(void* data);
void insertAfter(LLNode* prevNode, void* newData);
void deleteNode(LLNode** headRef, LLNode* toDelete);
void printList(LLNode* head, void print(const void*));
void append(LLNode** head, void* data);
void freeList(LLNode* head);



#endif // !_MY_LINKED_LIST_H
