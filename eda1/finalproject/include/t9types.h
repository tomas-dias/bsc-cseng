#ifndef _T9types_H
#define _T9types_H

typedef struct T9 *ElementType;

struct T9
{
    long int digits;
    wchar_t* letters;
};

typedef struct ListNode *Position;

struct ListNode
{
    ElementType Element;
    Position    Next;
};

typedef Position List;

typedef unsigned int Index;

struct HashTbl;
typedef struct HashTbl *HashTable;

struct HashTbl
{
    int TableSize;
    List *TheLists;
};

#endif
